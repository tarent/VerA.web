/*
 * Copyright (c) tarent GmbH
 * Bahnhofstrasse 13 . 53123 Bonn
 * www.tarent.de . info@tarent.de
 *
 * Created on 28.02.2006
 */

package de.tarent.octopus.cronjobs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.tarent.octopus.cronjobs.CronJob;

/**
 * This implements a Unix(tm) style cron job system. To submit a job, subclass
 * either ExactCronJob or IntervalCronJob and add them to the queue using
 * addJob(). Then start the cron system by running start() on the Cron instance.
 *
 * @author Michael Kleinhenz (m.kleinhenz@tarent.de)
 * @author Nils Neumaier (n.neumaier@tarent.de)
 */
public class Cron extends Thread
{
    public static final int EXACT_CRONJOB = 1;
    public static final int INTERVAL_CRONJOB = 2;    
    
    public static final String CRONJOBMAP_KEY_NAME              = "name";
    public static final String CRONJOBMAP_KEY_TYPE              = "type";
    public static final String CRONJOBMAP_KEY_PROCEDURE         = "procedure";
    public static final String CRONJOBMAP_KEY_ERRORPROCEDURE    = "errorprocedure";
    public static final String CRONJOBMAP_KEY_PROPERTIES        = "properties";
    public static final String CRONJOBMAP_KEY_STATUS            = "status";
    public static final String CRONJOBMAP_KEY_ERROR             = "error";
    public static final String CRONJOBMAP_KEY_LASTRUN           = "lastrun";
    
    private int CHECK_INTERVAL = 30000;
    private int TIMEBASE = 60000;
    
    private boolean stopped = false;
    private Map jobs = null;
    
    private CronExporter cronExporter;
    
    private static Logger logger = Logger.getLogger(Cron.class.getName());

    
    /**
     * Standard constructor. Creates a new instance of the cron system.
     */
    
    public Cron()
    {
        this.jobs = new HashMap();
        restoreBackup();
        this.start();
    }

    public Cron(int timeBase)
    {
    	this();
    	this.TIMEBASE = timeBase;
    	this.CHECK_INTERVAL = timeBase / 2;
    }
   
    
    /**
     * Stops the cron system. The system will not stop immediately, but when
     * reaching the next check time. 
     */
    public void deactivateCron()
    {
        this.stopped = true;
    }
    
    /**
     * Activates the cron system. The system will start immediately if it isnt already running.
     * If it is already running (State != RUNNABLE) it will proceed.
     */
     
    public void activateCron()
    {
        stopped = false;
        State state = this.getState();
        while (!state.equals(State.NEW) && !state.equals(State.RUNNABLE) && !state.equals(State.TERMINATED))
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        
        this.start();
    }
    
    /**
     * Adds a job to the list of jobs.
     * 
     * @param job Job to be added.
     */
    public boolean addJob(CronJob job)
    {
        // If a cronjob with the same name already exists, we will wait, until this job is finished 
        // before we replace it with the new cronjob
        if (job == null)            
            return false;
        
        if (jobs.containsKey(job.getName())){
            CronJob oldJob = (CronJob)jobs.get(job.getName());
            while(!oldJob.runnable()){
               try {
                Thread.sleep(CHECK_INTERVAL);
                } catch (InterruptedException e) {
                    // e.printStackTrace();
                }
            }   
        }
        
        jobs.put(job.getName(), job);
        logger.log(Level.FINEST, "New Cronjob added to queue: " + job.getName());
        
        return true;
    }
    
    /**
     * returns a CronJob Object corresponding to the given cronjobmap
     * @param cronJobMap: specifies the wanted cronjob
     * @return cronJob: the corresponding cronjob or null if there is no 
     */
    public CronJob getCronJobByCronJobMap(Map cronJobMap){
        
        if (cronJobMap == null)
            return null;
        
        CronJob tmpJob = getCronJobByName(cronJobMap.get(Cron.CRONJOBMAP_KEY_NAME).toString());
        
        // If there is a cronjob, at least the parameters type, procedure and name have to be correct
        if (tmpJob.getProcedure().equals(cronJobMap.get(Cron.CRONJOBMAP_KEY_PROCEDURE))
                && tmpJob.getName().equals(cronJobMap.get(Cron.CRONJOBMAP_KEY_NAME))
                && new Integer(tmpJob.getType()).equals(cronJobMap.get(Cron.CRONJOBMAP_KEY_TYPE))){
            return tmpJob;
        }
        return null;        
    }
    
    
    /**
     * Removes a job from the list of jobs.
     * returns true if job could be delted or false if not
     * @param jobMap: cronjobmap that specifies the job
     * 
     */
    public boolean removeJob(Map jobMap)
    {
        // First check if there is a cronjob with the correct name
        if (jobMap != null && getCronJobByCronJobMap(jobMap) != null){
                jobs.remove(jobMap.get(Cron.CRONJOBMAP_KEY_NAME));
                return true;    
        }
        return false;
    }
    
    /**
     * Will be called from the thread subsystem to start the cron system.
     */
    public void run()
    {
        while (!stopped)
        {
            
            try
            {
                Thread.sleep(CHECK_INTERVAL);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            
            logger.log(Level.FINEST, "Cron is checking for Jobs to Start. " + new Date() );
            
            Thread storeThread = new Thread(new CronExporter()); 
            storeThread.start();
            
            List clonedJobs = new ArrayList(jobs.values());
            Iterator iter = clonedJobs.iterator();
            while (iter.hasNext())
            {
                CronJob thisJob = (CronJob)iter.next();

                // Check if job can be started
                if (!thisJob.runnable())
                {
                    continue;
                }
                
                                
                switch (thisJob.getType())
                {
                    case EXACT_CRONJOB:
                        runExactCronJob(thisJob);
                        break;
                    case INTERVAL_CRONJOB:
                        runIntervalCronJob(thisJob);
                        break;
                }
            }
        }
    }

    private void runIntervalCronJob(CronJob job)
    {
        IntervalCronJob thisJob = (IntervalCronJob)job;

        // Check time constraints
        int intervalMinutes = thisJob.getIntervalMinutes();
        if (intervalMinutes<1)
            return;
        

        Date lastRun = thisJob.getLastRun();
        Date currentDate = new Date();

        if (lastRun == null || (currentDate.getTime() - lastRun.getTime()) > intervalMinutes * TIMEBASE)
        {
            thisJob.setLastRun(currentDate);
            logger.log(Level.INFO, "Cron starts Interval CronJob " + thisJob.getName() + " at " + currentDate );
            thisJob.start();
        }
    }

    private void runExactCronJob(CronJob job)
    {
        logger.log(Level.FINEST, "Cron checks Exact CronJob " + job.getName() + " at " + new Date() );
        
        ExactCronJob thisJob = (ExactCronJob)job;
        
       
        // Check if this job was already run in this minute
        Date lastRun = thisJob.getLastRun();
        Date currentRunDate = new Date();
        if (lastRun != null && currentRunDate.getTime() - lastRun.getTime() < TIMEBASE)
        {	
            return;
        }
       
        // Check time constraints
        Calendar currentDate = new GregorianCalendar();
        boolean run = true;
        run = run && (thisJob.getHour()==-1 || (thisJob.getHour()==currentDate.get(Calendar.HOUR_OF_DAY)));
        run = run && (thisJob.getMinute()==-1 || (thisJob.getMinute()==currentDate.get(Calendar.MINUTE)));
        run = run && (thisJob.getMonth()==-1 || (thisJob.getMonth()==currentDate.get(Calendar.MONTH)));
        run = run && (thisJob.getDayOfMonth()==-1 || (thisJob.getDayOfMonth()==currentDate.get(Calendar.DAY_OF_MONTH)));
        run = run && (thisJob.getDayOfWeek()==-1 || (thisJob.getDayOfWeek()==currentDate.get(Calendar.DAY_OF_WEEK)));
     

        // Run it..
        if (run && job.runnable())
        {
            logger.log(Level.INFO, "Cron starts Exact CronJob " + job.getName() + " at " + new Date() );
            
            job.setLastRun(currentRunDate);
            job.start();
        }
    }
    
    /**
     * returns the cronjobp corresponding to the given name
     * returns null if there is no corresponding job in queue 
     * @param name
     * @return cronjob
     */
    
    public CronJob getCronJobByName(String name){
        return (CronJob)jobs.get(name);
    }
    
    /**
     * returns the cronjobmap corresponding to the given name
     * returns null if there is no corresponding job in queue 
     * @param name
     * @return Map: cronjobmap of the cronjob
     */
    public Map getCronJobMapByName(String name){
        CronJob cronjob = (CronJob)jobs.get(name);
        if (cronjob != null)
            return cronjob.getCronJobMap();
        return null;
    }
    
    /**
     * starts a cronjob immediately if it is runnable
     * @param job
     * @return
     */
    public boolean forceRun(CronJob job){
        
        if (job.runnable()){
            job.setLastRun(new Date());
            job.start();
            return true;
        }
        return false;
    }
    
    /**
     * returns a Map with names (key) and cronjobmaps (value) of all cronjobs
     * @return Map: Map with cronjobmaps
     */
    public Map getCronJobMaps(){
        
        Map cronJobMaps = new HashMap();
        for (Iterator iter = jobs.values().iterator(); iter.hasNext();){
            CronJob job = (CronJob) iter.next();
            cronJobMaps.put(job.getName(), job.getCronJobMap());
        }
        return cronJobMaps;
    }
    
    /**
     * activates a cronjob
     * cronjob will be possibly runnable next time its started
     * 
     * @param cronJobMap: specifies the cronjob
     * @return activated: flag to show if activation was succesful
     */
    
    public boolean activateCronJob(Map cronJobMap){
        CronJob job = getCronJobByCronJobMap(cronJobMap);
        
        if (job != null){
            job.activate();
            return true;
        }
        return false;
    }
    
    /**
     * deactivates a cronjob
     * cronjob wont stop immediately but wont be runnable until it is activated again.
     *  
     * @param cronJobMap: specifies the cronjob
     * @return deactivated: flag to show if deactivation was succesful
     */
    public boolean deactivateCronJob(Map cronJobMap){
        CronJob job = getCronJobByCronJobMap(cronJobMap);
        
        if (job != null){
            job.deactivate();
            return true;
        }
        return false;
    }
    
    /**
     * This method gets a cronjobmap and/or a name of a cronjob. 
     * If both parameters are set, the algorithms checks if the name
     * matches the cronjobmap, returns the map if they fit and null if not.
     * If only a name is given, the corresponding job is found and its map returned. 
     * If only a map is given, it will be returned instantly.
     * 
     * @param cronJobMap: Map that specifies a cronjob
     * @param cronJobName: Name of the cronjob
     * @return
     */
    public Map mergeCronJobMapAndName(Map cronJobMap, String cronJobName){
        
        // cronjobmap is set but name is null or empty
        if (cronJobMap != null && (cronJobName == null || cronJobName.equals("")))
            return cronJobMap;
        
        // name is set and cronjobmap is null
        if (cronJobName != null && !cronJobName.equals("") && cronJobMap == null && getCronJobByName(cronJobName) != null )
            return getCronJobByName(cronJobName).getCronJobMap();
        
        if (cronJobMap != null && cronJobName != null && cronJobMap.get(Cron.CRONJOBMAP_KEY_NAME).equals(cronJobName))
            return cronJobMap;
        
        return null;
    }
    
    
    /**
     * This routine stores the actual list of cronjobs persistent on harddrive
     *
     */
    
   class CronExporter implements Runnable{
       
        public CronExporter(){            
        }
        
        public void run(){
            try {
                
                FileOutputStream fileOut = new FileOutputStream(new File(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "cronJobBackup"));
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            
                objectOut.writeObject(getCronJobMaps());
                objectOut.flush();
                objectOut.close();
            
            
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
   
   private void restoreBackup(){
       
       Map result = null;
       File backupFile = new File (System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "cronJobBackup");
       if (backupFile.exists()){
           try {
                long filoeSize = backupFile.length();
                FileInputStream fileIn = new FileInputStream(backupFile);
                ObjectInputStream objectInput = new ObjectInputStream(fileIn);
                
                result = (Map) objectInput.readObject();
                
                fileIn.close();
                objectInput.close();
                
                for (Iterator iter = result.values().iterator(); iter.hasNext();){
                    
                    Map tmpCronJobMap = (Map) iter.next();
                    CronJob tmpJob = null;
                    try {
                        tmpJob = createCronJobFromCronJobMap(tmpCronJobMap);
                    } catch (Exception e) {
                        logger.log(Level.WARNING, "An error occured trying to restore an old cron backup.\n" + e.getMessage());
                        e.printStackTrace();
                    }
                    
                    if (tmpJob != null){
                        logger.log(Level.INFO, "Restoring Cronjob \""+ tmpJob.getName() + "\": (type = " + tmpJob.getType() + ", procedure = " + tmpJob.getProcedure() + ")"); 
                        addJob(tmpJob);
                    }
                }
                
                if (getCronJobMaps().size() > 0) logger.log(Level.INFO, "Cronjobs restored from backup: " + getCronJobMaps());
                
                
           } catch (FileNotFoundException e) { 
               logger.log(Level.SEVERE, "No backup file found to restore old cron.\n" + e.getMessage());
               
           } catch (IOException e) {
               logger.log(Level.SEVERE, "Error trying to restore backup of old cron.\n" + e.getMessage());
               
           } catch (ClassNotFoundException e) {
               logger.log(Level.SEVERE, "Error trying to restore backup of old cron.\n" + e.getMessage());
           }
          
       }           
   }
   
   public CronJob createCronJobFromCronJobMap(Map cronJobMap) throws Exception{
       
       String exceptionErrorMessage = "";
       
       cronJobMap = correctCronJobMap(cronJobMap);
       
       String name             = (String)cronJobMap.get(Cron.CRONJOBMAP_KEY_NAME);
       Integer type            = new Integer(Integer.parseInt(cronJobMap.get(Cron.CRONJOBMAP_KEY_TYPE).toString()));
       String procedure        = (String)cronJobMap.get(Cron.CRONJOBMAP_KEY_PROCEDURE);
       String errorProcedure   = (String)cronJobMap.get(Cron.CRONJOBMAP_KEY_ERRORPROCEDURE);
       Map properties          = (Map)cronJobMap.get(Cron.CRONJOBMAP_KEY_PROPERTIES);
       String errorMessage     = (String)cronJobMap.get(Cron.CRONJOBMAP_KEY_ERROR);
       
       
       // Some Entries must be set or we will return null 
       if (name == null || name.equals("")|| procedure == null || procedure.equals("")|| properties == null || type == null){
           exceptionErrorMessage += "Error in Task setCronJob. One of the following Map entries has not been set or could not be used: " + "name: " + name + ", procedure: " + procedure + ", properties: " + properties + ", type: " + type + ". ";
           throw new Exception(exceptionErrorMessage);
       }
       
       Integer alreadyRunning  = (Integer) properties.get(CronJob.PROPERTIESMAP_KEY_ALREADYRUNNING);
       
       CronJob cronJob = null;
       
       // If cronjob is of type exact cronjob
       if (type.intValue() == Cron.EXACT_CRONJOB){
           
           int hour        = -1;
           int minute      = -1;
           int month       = -1;
           int dayOfMonth  = -1;
           int dayOfWeek   = -1;
           
           // search in properties map for entries specific for an exact cronjob
           //  and store them in variables 
           for (Iterator iter = properties.entrySet().iterator(); iter.hasNext();){

               Entry e = (Entry) iter.next();
               String key = e.getKey().toString();
               
              if (key.equals(ExactCronJob.PROPERTIESMAP_KEY_HOUR)){
                   hour = ((Integer)e.getValue()).intValue();
              } 
              else if (key.equals(ExactCronJob.PROPERTIESMAP_KEY_MINUTE)){
                  minute = ((Integer)e.getValue()).intValue();
              } 
              else if (key.equals(ExactCronJob.PROPERTIESMAP_KEY_MONTH)){
                  month = ((Integer)e.getValue()).intValue();
              }                     
              else if (key.toLowerCase().equals(ExactCronJob.PROPERTIESMAP_KEY_DAYOFMONTH)){
                  dayOfMonth = ((Integer)e.getValue()).intValue();
              } 
              else if (key.toLowerCase().equals(ExactCronJob.PROPERTIESMAP_KEY_DAYOFWEEK)){
                  dayOfWeek = ((Integer)e.getValue()).intValue();
              }
           }
           
           // At least one parameter has to be set (!= -1)
           if (hour != -1 || minute != -1 || month != -1 || dayOfMonth != -1 || dayOfWeek != -1)
               cronJob = new ExactCronJob(hour, minute, month, dayOfMonth, dayOfWeek);
           
       }
       
       // If cronjob is of type interval cronjob
       else if (type.intValue() == Cron.INTERVAL_CRONJOB){
           
           // Get interval value from properties map, store it and delete it in the map
           // so that later routines that iterate on the properties dont have to handle it
           
           Integer interval = (Integer) properties.get(IntervalCronJob.PROPERTIESMAP_KEY_INTERVAL);
           int intervalTime = interval.intValue();
            
           // intervalTime has to be set and must be greater than zero
           if (intervalTime  > 0) 
               cronJob = new IntervalCronJob(intervalTime);
           else
               logger.log(Level.WARNING, "Error trying to create an IntervalCronJob. Entry '" + IntervalCronJob.PROPERTIESMAP_KEY_INTERVAL + "' in properties map has not been set or is lower than one.");
           
       }
       else if (type.intValue() != Cron.INTERVAL_CRONJOB && type.intValue() != Cron.EXACT_CRONJOB){
           exceptionErrorMessage += "Unknown cronjob type: " + type;
           throw new Exception(exceptionErrorMessage);
       }
       
       // If a cronjob has been created using the type-specific parameters we have to set the general parameters
       if (cronJob != null){
           cronJob.setName(name);
           cronJob.setProcedure(procedure);
           if (errorProcedure != null)
               cronJob.setErrorProcedure(errorProcedure);
           else
               cronJob.setErrorProcedure(loadStandardErrorProcedure());
           if (errorMessage != null && errorMessage.length() > 0)
               cronJob.setErrorMessage(errorMessage);
           cronJob.setProperties(properties);
           if (alreadyRunning != null)
               cronJob.setAlreadyRunning(alreadyRunning.intValue());
       }
       
       return cronJob;
   }
   
   private String loadStandardErrorProcedure() {
       // TODO Auto-generated method stub
       return null;
   }
   
   
   private Map correctCronJobMap(Map input){
       
       Map resultMap = input;
       Map properties = input.get(Cron.CRONJOBMAP_KEY_PROPERTIES) != null ? (Map) input.get(Cron.CRONJOBMAP_KEY_PROPERTIES): new HashMap();
       
       for (Iterator iter = input.entrySet().iterator(); iter.hasNext();){
           Entry entry = (Entry) iter.next();
           Object key = entry.getKey();
           
           // if there is an entry in the cronjobmap, that doesnt belong there
           // we remove it from the cronjobmap and add it to the internal properties map
           if (!key.equals(Cron.CRONJOBMAP_KEY_NAME) && !key.equals(Cron.CRONJOBMAP_KEY_PROPERTIES)
               && !key.equals(Cron.CRONJOBMAP_KEY_TYPE) && !key.equals(Cron.CRONJOBMAP_KEY_STATUS)
               && !key.equals(Cron.CRONJOBMAP_KEY_PROCEDURE) && !key.equals(Cron.CRONJOBMAP_KEY_ERRORPROCEDURE)
               && !key.equals(Cron.CRONJOBMAP_KEY_LASTRUN) && !key.equals(Cron.CRONJOBMAP_KEY_ERROR)){
           
                   properties.put(entry.getKey(), entry.getValue());
                   resultMap.remove(key);
            
           }
       }
       
       resultMap.put(Cron.CRONJOBMAP_KEY_PROPERTIES, properties);
       return resultMap;
   }
}
