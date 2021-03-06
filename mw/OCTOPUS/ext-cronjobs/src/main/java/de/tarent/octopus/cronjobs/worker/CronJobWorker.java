package de.tarent.octopus.cronjobs.worker;

/*-
 * Veranstaltungsmanagement VerA.web, comprised of…
 * VerA.web, platform-independent webservice-based event management
 * tarent-commons, a set of common components and solutions
 * tarent-contact, platform-independent webservice-based contact management
 * tarent-database, jdbc database library
 * tarent-doctor, Document Generation Platform
 * tarent-octopus, Webservice Data Integrator and Application Server
 * … is newly MIT-licenced and Copyright
 *  © 2018 Атанас Александров (sirakov@gmail.com)
 *  © 2014, 2015, 2016, 2017 Атанас Александров (a.alexandrov@tarent.de)
 *  © 2013 Иванка Александрова (i.alexandrova@tarent.de)
 *  © 2005, 2006, 2007 asteban (s.mancke@tarent.de)
 *  © 2013 Patrick Apel (p.apel@tarent.de)
 *  © 2016 Eugen Auschew (e.auschew@tarent.de)
 *  © 2013 Andrei Boulgakov (a.boulgakov@tarent.de)
 *  © 2013 Valentin But (v.but@tarent.de)
 *  © 2016 Lukas Degener (l.degener@tarent.de)
 *  © 2017 Axel Dirla (a.dirla@tarent.de)
 *  © 2015 Julian Drawe (j.drawe@tarent.de)
 *  © 2009 Sven Frommeyer (s.frommeyer@tarent.de)
 *  © 2014, 2018 Dominik George (d.george@tarent.de)
 *  © 2013 Martin Gernhardt (m.gernhardt@tarent.de)
 *  © 2013 Sascha Girrulat (s.girrulat@tarent.de)
 *  © 2007, 2008 David Goemans (d.goemans@tarent.de)
 *  © 2018 Christian Gorski (c.gorski@tarent.de)
 *  © 2015 Viktor Hamm (v.hamm@tarent.de)
 *  © 2013 Katja Hapke (k.hapke@tarent.de)
 *  © 2006, 2007, 2010, 2013 Hendrik Helwich (h.helwich@tarent.de)
 *  © 2018 Thomas Hensel (t.hensel@tarent.de)
 *  © 2018, 2019 Benedict Hoeger (b.hoeger@tarent.de)
 *  © 2018, 2019 Titian Horvath (t.horvath@tarent.de)
 *  © 2005, 2006, 2007, 2008 Christoph Jerolimov (jerolimov@gmx.de)
 *  © 2018, 2019 Timo Kanera (t.kanera@tarent.de)
 *  © 2006 Philipp Kirchner (p.kirchner@tarent.de)
 *  © 2008, 2009, 2010 Carsten Klein (c.klein@tarent.de)
 *  © 2006 Michael Kleinhenz (m.kleinhenz@tarent.de)
 *  © 2006 Michael Klink (m.klink@tarent.de)
 *  © 2007 Fabian Köster (f.koester@tarent.de)
 *  © 2006, 2014 Martin Ley (m.ley@tarent.de)
 *  © 2007 Alex Maier (a.maier@tarent.de)
 *  © 2014, 2015 Max Marche (m.marche@tarent.de)
 *  © 2007 Jan Meyer (jan@evolvis.org)
 *  © 2007, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020
 *     mirabilos (t.glaser@tarent.de)
 *  © 2016 Cristian Molina (c.molina@tarent.de)
 *  © 2006, 2007 Jens Neumaier (j.neumaier@tarent.de)
 *  © 2006 Nils Neumaier (n.neumaier@tarent.de)
 *  © 2018 Yorka Neumann (y.neumann@tarent.de)
 *  © 2017 Michael Nienhaus (m.nienhaus@tarent.de)
 *  © 2013 Claudia Nuessle (c.nuessle@tarent.de)
 *  © 2014, 2015 Jon Nuñez Alvarez (j.nunez-alvarez@tarent.de)
 *  © 2016 Jens Oberender (j.oberender@tarent.de)
 *  © 2016, 2017 Miluška Pech (m.pech@tarent.de)
 *  © 2007, 2008, 2009 Martin Pelzer (m.pelzer@tarent.de)
 *  © 2008, 2009 Christian Preilowski (c.thiel@tarent.de)
 *  © 2013 Marc Radel (m.radel@tarent.de)
 *  © 2013 Sebastian Reimers (s.reimers@tarent.de)
 *  © 2015 Charbel Saliba (c.saliba@tarent.de)
 *  © 2006, 2008, 2009, 2010 Thomas Schmitz (t.schmitz@tarent.de)
 *  © 2013 Volker Schmitz (v.schmitz@tarent.de)
 *  © 2014 Sven Schumann (s.schumann@tarent.de)
 *  © 2007 Robert Schuster (r.schuster@tarent.de)
 *  © 2014 Sevilay Temiz (s.temiz@tarent.de)
 *  © 2013 Kevin Viola Schmitz (k.schmitz@tarent.de)
 *  © 2008, 2015 Stefan Walenda (s.walenda@tarent.de)
 *  © 2015, 2016, 2017 Max Weierstall (m.weierstall@tarent.de)
 *  © 2013 Rebecca Weinz (r.weinz@tarent.de)
 *  © 2015, 2016 Stefan Weiz (s.weiz@tarent.de)
 *  © 2015, 2016 Tim Zimmer (t.zimmer@tarent.de)
 * and older code, Copyright © 2001–2008 ⮡ tarent GmbH and contributors.
 * Licensor is tarent solutions GmbH, http://www.tarent.de/
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

import de.tarent.octopus.cronjobs.Cron;
import de.tarent.octopus.cronjobs.CronJob;
import de.tarent.octopus.cronjobs.ExactCronJob;
import de.tarent.octopus.cronjobs.IntervalCronJob;
import de.tarent.octopus.server.Context;
import de.tarent.octopus.server.OctopusContext;
import lombok.extern.log4j.Log4j2;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Log4j2
public class CronJobWorker {
    private Cron cronjobQueue;
    private Thread cronThread;
    private static long cronThreadCount = 0L;

    final static public String[] INPUT_setCronJob = { "cronjob" };
    final static public boolean[] MANDATORY_setCronJob = { true };
    final static public String OUTPUT_setCronJob = "cronjob";

    /**
     * this method creates a new cronjob. If a cronjob with the same name already exists,
     * the old one is replaced by the new cronjob.
     *
     * @return cronjob: cronjob-map of edited or created cronjob
     */
    public Map setCronJob(Map cronJobMap) {
        // If cronjob already exists and "active" or "errormessage" are not set in the new cronjobmap
        // we take the entries of the old cronjob that should get overwritten
        CronJob oldCronJob = cronjobQueue.getCronJobByName(cronJobMap.get(Cron.CRONJOBMAP_KEY_NAME).toString());
        if (oldCronJob != null) {
            Map oldCronJobMap = oldCronJob.getCronJobMap();
            if (cronJobMap.get(Cron.CRONJOBMAP_KEY_ACTIVE) == null) {
                cronJobMap.put(Cron.CRONJOBMAP_KEY_ACTIVE, oldCronJobMap.get(Cron.CRONJOBMAP_KEY_ACTIVE));
            }
            if (cronJobMap.get(Cron.CRONJOBMAP_KEY_ERROR) == null) {
                cronJobMap.put(Cron.CRONJOBMAP_KEY_ERROR, oldCronJobMap.get(Cron.CRONJOBMAP_KEY_ERROR));
            }
            if (cronJobMap.get(Cron.CRONJOBMAP_KEY_LASTRUN) == null) {
                cronJobMap.put(Cron.CRONJOBMAP_KEY_LASTRUN, oldCronJobMap.get(Cron.CRONJOBMAP_KEY_LASTRUN));
            }
        }

        try {
            CronJob cronJob = cronjobQueue.createCronJobFromCronJobMap(cronJobMap);
            if (cronJob != null && cronjobQueue.addJob(cronJob)) {
                Map newCronJobMap = cronJob.getCronJobMap();
                return newCronJobMap;
            }
        } catch (Exception e) {
            logger.warn(e.getMessage() + "\n\n" + e.getCause());
            e.printStackTrace();
            setError(e.getMessage());
        }
        return null;
    }

    final static public String[] INPUT_GETCRONJOBNAMES = { "name", "type", "sort" };
    final static public boolean[] MANDATORY_GETCRONJOBNAMES = { false, false, false };
    final static public String OUTPUT_GETCRONJOBNAMES = "cronjobnames";

    /**
     * this method returns a List of the cronjob-names as simple Strings
     *
     * @param name can be set to get the name of a single cronjob and check if it exists
     * @param type can be set to get names of just one type of cronjobs
     * @param sort optional flag to sort the returned list alphabetically
     */
    public List getCronJobNames(String name, Integer type, Boolean sort) {
        try {
            List cronjobnames = new ArrayList();
            Map cronJobMaps = cronjobQueue.getCronJobMaps();
            for (Iterator iter = cronJobMaps.values().iterator(); iter.hasNext(); ) {
                Map tmpMap = (Map) iter.next();
                if (name == null || name.equals("") || (tmpMap.get(Cron.CRONJOBMAP_KEY_NAME)).equals(name)) {
                    if (type == null || ((Integer) tmpMap.get(Cron.CRONJOBMAP_KEY_TYPE)).equals(type)) {
                        cronjobnames.add(tmpMap.get(Cron.CRONJOBMAP_KEY_NAME));
                    }
                }
            }

            if (sort != null && sort.booleanValue()) {
                Object[] tmpArray = cronjobnames.toArray();
                Arrays.sort(tmpArray);
                cronjobnames = Arrays.asList(tmpArray);
            }

            return cronjobnames.size() > 0 ? cronjobnames : null;
        } catch (Exception e) {

            logger.warn(e.getMessage());
            setError(e.getMessage());
            return null;
        }
    }

    final static public String[] INPUT_GETCRONJOBS = { "name", "type" };
    final static public boolean[] MANDATORY_GETCRONJOBS = { false, false };
    final static public String OUTPUT_GETCRONJOBS = "cronjobs";

    /**
     * This action returns a list of cronjob-maps
     *
     * @param name can be set to get the cronjob-map of a single cronjob
     * @param type can be set to get cronjob-maps of just one type of cronjobs
     */
    public List getCronJobs(String name, Integer type) {
        try {
            List filteredCronJobMaps = new ArrayList();

            Map cronJobMaps = cronjobQueue.getCronJobMaps();
            for (Iterator iter = cronJobMaps.values().iterator(); iter.hasNext(); ) {

                Map tmpCronJobMap = (Map) iter.next();

                if (name == null || (tmpCronJobMap.get(Cron.CRONJOBMAP_KEY_NAME)).equals(name)) {
                    if (type == null || ((Integer) tmpCronJobMap.get(Cron.CRONJOBMAP_KEY_TYPE)).equals(type)) {
                        filteredCronJobMaps.add(tmpCronJobMap);
                    }
                }
            }
            return filteredCronJobMaps;
        } catch (Exception e) {

            logger.warn(e.getMessage());
            setError(e.getMessage());
            return null;
        }
    }

    final static public String[] INPUT_GETCRONJOB = { "cronjobname" };
    final static public boolean[] MANDATORY_GETCRONJOB = { true };
    final static public String OUTPUT_GETCRONJOB = "cronjob";

    /**
     * This action returns a cronjob-maps for a specific cronjob
     *
     * @param name the name of the specified cronjob
     */
    public Map getCronJob(String name) {
        return cronjobQueue.getCronJobMapByName(name);
    }

    final static public String[] INPUT_RUNCRONJOB = { "cronjob", "cronjobname" };
    final static public boolean[] MANDATORY_RUNCRONJOB = { false, false };
    final static public String OUTPUT_RUNCRONJOB = "cronjob";

    public Map runCronJob(Map inputCronJobMap, String cronJobName) {
        String errMsg = "";
        try {
            Map cronJobMap = cronjobQueue.mergeCronJobMapAndName(inputCronJobMap, cronJobName);

            if (cronJobMap != null) {
                CronJob job = cronjobQueue.getCronJobByName(cronJobMap.get(Cron.CRONJOBMAP_KEY_NAME).toString());

                if (job != null) {
                    cronjobQueue.forceRun(job, true);
                    Map returnMap = job.getCronJobMap();
                    return returnMap;
                }
            }

            errMsg =
              "An Error occured trying to run a cronjob. The transfered parameters either reference different cronjobs, " +
                "both parameters are null or there is no cronjob in queue that matches. ";
            errMsg += "\n inputCronJobMap: " + inputCronJobMap;
            errMsg += "\n cronJobName: " + cronJobName;
        } catch (Exception e) {
            setError(e.getMessage() + "\n" + errMsg);
            logger.warn(e.getMessage() + "\n" + errMsg);
        }
        return null;
    }

    final static public String[] INPUT_GETCRONJOBSTATUS = { "cronjob", "cronjobname" };
    final static public boolean[] MANDATORY_GETCRONJOBSTATUS = { false, false };
    final static public String OUTPUT_GETCRONJOBSTATUS = "status";

    /**
     * This action returns the status of a cronjob as String representing an object of Thread.State
     */
    public String getCronJobStatus(Map inputCronJobMap, String cronJobName) {
        String errMsg = "";
        try {
            Map cronJobMap = cronjobQueue.mergeCronJobMapAndName(inputCronJobMap, cronJobName);

            if (cronJobMap != null) {
                CronJob job = cronjobQueue.getCronJobByCronJobMap(cronJobMap);
                return job.getStatus() != null ? (job.getStatus()).toString() : "";
            }

            errMsg =
              "An Error occured trying get the status of a cronjob. The transfered parameters either reference different " +
                "cronjobs, both parameters are null or there is no cronjob in queue that matches. ";
            errMsg += "\n inputCronJobMap: " + inputCronJobMap;
            errMsg += "\n cronJobName: " + cronJobName;
        } catch (Exception e) {
            setError(e.getMessage() + "\n" + errMsg);
            logger.warn(e.getMessage() + "\n" + errMsg);
        }

        return null;
    }

    final static public String[] INPUT_REMOVECRONJOB = { "cronjob", "cronjobname" };
    final static public boolean[] MANDATORY_REMOVECRONJOB = { false, false };
    final static public String OUTPUT_REMOVECRONJOB = "cronjob";

    /**
     * This action removes a cronjob from the cronjobqueue
     */
    public Map removeCronJob(Map inputCronJobMap, String cronJobName) {
        String errMsg = "";
        try {
            Map cronJobMap = cronjobQueue.mergeCronJobMapAndName(inputCronJobMap, cronJobName);

            if (cronJobMap != null && cronjobQueue.removeJob(cronJobMap)) {
                //inputCronJobMap.put(Cron.CRONJOBMAP_KEY_STATUS, Thread.State.TERMINATED);
                logger.info("Cronjob has been removed from queue: " + cronJobMap.get(Cron.CRONJOBMAP_KEY_NAME));
                return cronJobMap;
            }
            errMsg =
              "An Error occured trying to remove Cronjob from queue. The transfered parameters either reference different" +
                " cronjobs, both parameters are null or there is no cronjob in queue that matches. ";
            errMsg += "\n inputCronJobMap: " + inputCronJobMap;
            errMsg += "\n cronJobName: " + cronJobName;
        } catch (Exception e) {
            setError(e.getMessage() + "\n" + errMsg);
            logger.warn(e.getMessage() + "\n" + errMsg);
        }

        return null;
    }

    /**
     * This action returns a list of all types of cronjobs
     */

    final static public String[] INPUT_GETAVAILABLECRONJOBTYPES = {};
    final static public boolean[] MANDATORY_GETAVAILABLECRONJOBTYPES = {};
    final static public String OUTPUT_GETAVAILABLECRONJOBTYPES = "availableTypes";

    public List getAvailableCronJobTypes() {
        List types = new ArrayList();
        types.add(new Integer(Cron.EXACT_CRONJOB));
        types.add(new Integer(Cron.INTERVAL_CRONJOB));
        return types;
    }

    final static public String[] INPUT_GETAVAILABLECRONJOBPROPERTIES = { "type" };
    final static public boolean[] MANDATORY_GETAVAILABLECRONJOBPROPERTIES = { true };
    final static public String OUTPUT_GETAVAILABLECRONJOBPROPERTIES = "availableProperties";

    /**
     * This action returns a map that contains the available properties for a special type of cronjob
     *
     * @return Map availableProperties: key = the name of the property, value = the type of the property
     */
    public Map getAvailableCronJobProperties(Integer type) {
        Map properties = new HashMap();

        properties.put(CronJob.PROPERTIESMAP_KEY_ALREADYRUNNING, Integer.class.getName());

        if (type.equals(new Integer(Cron.EXACT_CRONJOB))) {
            properties.put(ExactCronJob.PROPERTIESMAP_KEY_HOUR, Integer.class.getName());
            properties.put(ExactCronJob.PROPERTIESMAP_KEY_MINUTE, Integer.class.getName());
            properties.put(ExactCronJob.PROPERTIESMAP_KEY_MONTH, Integer.class.getName());
            properties.put(ExactCronJob.PROPERTIESMAP_KEY_DAYOFWEEK, Integer.class.getName());
            properties.put(ExactCronJob.PROPERTIESMAP_KEY_DAYOFMONTH, Integer.class.getName());
        } else if (type.equals(new Integer(Cron.INTERVAL_CRONJOB))) {
            properties.put(IntervalCronJob.PROPERTIESMAP_KEY_INTERVAL, Integer.class.getName());
        }
        return properties;
    }

    final static public String[] INPUT_ACTIVATECRONJOB = { "cronjob", "cronjobname" };
    final static public boolean[] MANDATORY_ACTIVATECRONJOB = { false, false };
    final static public String OUTPUT_ACTIVATECRONJOB = "cronjob";

    /**
     * This action activates a cronjob
     *
     * @param inputCronJobMap cronjobmap that specifies the cronjob
     * @param cronJobName     name of the cronjob as String
     */
    public Map activateCronJob(Map inputCronJobMap, String cronJobName) {
        String errMsg = "";

        try {
            Map cronJobMap = cronjobQueue.mergeCronJobMapAndName(inputCronJobMap, cronJobName);

            if (cronJobMap != null) {
                if (cronjobQueue.activateCronJob(cronJobMap)) {
                    logger.info(
                      "CronJob " + cronJobMap.get(Cron.CRONJOBMAP_KEY_NAME) + "has been activated and will be runnable.");
                }
                return cronJobMap;
            }

            errMsg =
              "An Error occured trying to activate a cronjob. The transfered parameters either reference different " +
                "cronjobs, both parameters are null or there is no cronjob in queue that matches. ";
            errMsg += "\n inputCronJobMap: " + inputCronJobMap;
            errMsg += "\n cronJobName: " + cronJobName;
        } catch (Exception e) {
            setError(e.getMessage() + "\n" + errMsg);
            logger.warn(e.getMessage() + "\n" + errMsg);
        }
        return null;
    }

    final static public String[] INPUT_DEACTIVATECRONJOB = { "cronjob", "cronjobname" };
    final static public boolean[] MANDATORY_DEACTIVATECRONJOB = { false, false };
    final static public String OUTPUT_DEACTIVATECRONJOB = "cronjob";

    public Map deactivateCronJob(Map inputCronJobMap, String cronJobName) {
        String errMsg = "";

        try {
            Map cronJobMap = cronjobQueue.mergeCronJobMapAndName(inputCronJobMap, cronJobName);

            if (cronJobMap != null) {
                if (cronjobQueue.deactivateCronJob(cronJobMap)) {
                    return cronJobMap;
                }
            }

            errMsg =
              "An Error occured trying to deactivate a cronjob. The transfered parameters either reference different " +
                "cronjobs, both parameters are null or there is no cronjob in queue that matches. ";
            errMsg += "\n inputCronJobMap: " + inputCronJobMap;
            errMsg += "\n cronJobName: " + cronJobName;
        } catch (Exception e) {
            setError(e.getMessage() + "\n" + errMsg);
            logger.warn(e.getMessage() + "\n" + errMsg);
        }
        return null;
    }

    final static public String[] INPUT_STARTCRONJOBROUTINE = {};
    final static public boolean[] MANDATORY_STARTCRONJOBROUTINE = {};
    final static public String OUTPUT_STARTCRONJOBROUTINE = null;

    public void startCronJobRoutine(OctopusContext oc) {

        if (cronjobQueue == null) {
            cronjobQueue = new Cron(oc.cloneContext(), oc.moduleRootPath());
        }

        if (cronThread == null || cronThread.getState().equals(State.TERMINATED) || !cronjobQueue.isActivated()) {
            cronjobQueue.activateCron();
            cronThread = new Thread(cronjobQueue);
            cronThread.setName("Cron Managment Thread #" + (cronThreadCount++));
            cronThread.start();
            logger.info("Cron routine started.");
        }
    }

    final static public String[] INPUT_STOPCRONJOBROUTINE = {};
    final static public boolean[] MANDATORY_STOPCRONJOBROUTINE = {};
    final static public String OUTPUT_STOPCRONJOBROUTINE = null;

    public void stopCronJobRoutine() {
        if (cronjobQueue != null) {
            cronjobQueue.deactivateCron();
            logger.info("Cron routine stopped.");
        }
    }

    /**
     * Set an error message into the current content.
     *
     * @param text Error message
     */
    public static void setError(String text) {
        Context.getActive().setContent("error", text);
    }
}
