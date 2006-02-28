/* $Id: TcRequestDispatcher.java,v 1.5 2006/02/28 09:34:45 christoph Exp $
 * 
 * tarent-octopus, Webservice Data Integrator and Applicationserver
 * Copyright (C) 2002 tarent GmbH
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 * 
 * tarent GmbH., hereby disclaims all copyright
 * interest in the program 'tarent-octopus'
 * (which makes passes at compilers) written
 * by Sebastian Mancke and Michael Klink.
 * signature of Elmar Geese, 1 June 2002
 * Elmar Geese, CEO tarent GmbH
 */

package de.tarent.octopus.request;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.tarent.octopus.config.TcCommonConfig;
import de.tarent.octopus.config.TcConfig;
import de.tarent.octopus.config.TcConfigException;
import de.tarent.octopus.config.TcModuleConfig;
import de.tarent.octopus.content.TcAll;
import de.tarent.octopus.content.TcContent;
import de.tarent.octopus.content.TcContentProzessException;
import de.tarent.octopus.content.TcContentWorker;
import de.tarent.octopus.content.TcContentWorkerFactory;
import de.tarent.octopus.resource.Resources;
import de.tarent.octopus.response.ResponseProcessingException;
import de.tarent.octopus.response.TcResponseCreator;
import de.tarent.octopus.response.TcResponseDescription;
import de.tarent.octopus.security.TcSecurityException;
import de.tarent.octopus.server.LoginManager;
import de.tarent.octopus.server.OctopusContext;
import de.tarent.octopus.server.PersonalConfig;
import de.tarent.octopus.soap.TcSOAPException;
import de.tarent.octopus.util.Threads;
import de.tarent.octopus.server.Context;

/** 
 * Wichtigste Steuerkomponente f�r den Ablauf und die Abarbeitung einer Anfrage.
 * <br><br>
 * Initialisiert das ganze System mit dem Konstructor
 * und arbeitet eine Anfrage mit dispatch() ab.
 * 
 * @author <a href="mailto:mancke@mancke-software.de">Sebastian Mancke</a>, <b>tarent GmbH</b>
 * @author Michael Klink, tarent GmbH
 */
public class TcRequestDispatcher {
    private static final String DEFAULT_TASK_NAME = "default";

    private TcResponseCreator responseCreator;
    private TcCommonConfig commonConfig;
    private static Logger logger = Logger.getLogger(TcRequestDispatcher.class.getName());

    /**
     * Initialisierung des Systes.
     * Die Komponenten TcResponseCreator, TcSecurityManager und TcCommonConfig
     * werden aufgebaut und initialisiert.
     *
     * @param common gemeinsames Basiskonfigurationsobjekt
     */
    public TcRequestDispatcher(TcCommonConfig common) throws TcConfigException {
        commonConfig = common;
        responseCreator = new TcResponseCreator();
    }

    /**
     * Abarbeitung einer Anfrage.
     * <ol><b>Ablauf:</b>
     *  <li>Wenn der Request als Task 'login' oder 'logout' hat,
     *      wird �ber den SecutityManager versucht, dies zu tun.</li>
     *  <li>Als n�chstes wird kontrolliert, ob der Benutzer eingeloggt ist,
     *      gegebenenfalls wird versucht, einen Login zu erzwingen. Wenn dies
     *      der Fall ist wird die Config mit der Personal- und CommonConfig
     *      erstellt.</li>
     *  <li>Nun wird das Task abgearbeitet. Dazu werden abh�ngig vom TaskManager,
     *      der die Tasksteuerung �bernimmt, die ActionWorker gestartet, die den
     *      Content verarbeiten.</li>
     *  <li>Als letztes wird die Ausgabe �ber den ResponseCreator erstellt und
     *      ausgegeben. Welche Ausgabe gemacht werden soll, bestimmt der
     *      TaskManager.</li>
     * </ol>
     * 
     * @param tcRequest Anfrageparameter.
     * @param tcResponse Objekt, das Methoden f�r die Ausgabe des Ergebnisses
     *      bereit stellt. Es wird an den ResponseCreator weiter gegeben.
     * @param theSession Sessionobjekt, das die PersonalConfig aufnehmen kann.
     */
    public void dispatch(TcRequest tcRequest, TcResponse tcResponse, TcSession theSession)
        throws ResponseProcessingException {

        String requestID = tcRequest.getRequestID();

        //==================================================
        // 1. Task- und Modulname feststellen und verifizieren 

        String module = tcRequest.getModule();
        String task = tcRequest.getTask();

        if ( (module == null || module.length() == 0) 
             && commonConfig.getDefaultModuleName() != null) {
            module = commonConfig.getDefaultModuleName();
            tcRequest.setModule(module);
        }

        TcModuleConfig moduleConfig = commonConfig.getModuleConfig(module);
        if (moduleConfig == null) {
            logger.log(Level.SEVERE, Resources.getInstance().get("REQUESTDISPATCHER_LOG_NO_MODULE", requestID, module));
            throw new ResponseProcessingException(
                Resources.getInstance().get("REQUESTDISPATCHER_EXC_NO_MODULE", module));
        }

        if (task == null || task.length() == 0) {
            task = DEFAULT_TASK_NAME;
            tcRequest.setTask(task);
        }
        if (moduleConfig.getTaskList().getTask(task) == null) {
            logger.log(Level.SEVERE, Resources.getInstance().get("REQUESTDISPATCHER_LOG_NO_TASK", requestID, task, module));
            throw new ResponseProcessingException(
                Resources.getInstance().get("REQUESTDISPATCHER_EXC_NO_TASK", task, module));
        }

        tcResponse.setModuleName(module);
        tcResponse.setTaskName(task);

        logger.info(Resources.getInstance().get("REQUESTDISPATCHER_LOG_DISPATCHING", requestID, module, task));


        //
        // Wirklich auszuf�hrendes Module und Task sind nun identifiziert.
        //
        
        

        //==================================================
        // 2. Security
        PersonalConfig personalConfig = null;
    	TcConfig config = new TcConfig(commonConfig, personalConfig, module);
    	TcContent content = new TcContent();
        OctopusContext context = new TcAll(tcRequest, content, config);
    	ClassLoader outerLoader = null;
    	
    	Context.setActive(context);
        try {
            outerLoader = Threads.setContextClassLoader(moduleConfig.getClassLoader());
            LoginManager loginManager = commonConfig.getLoginManager(moduleConfig);
            loginManager.handleAuthentication(commonConfig, tcRequest, theSession);
            personalConfig = loginManager.getPersonalConfig(commonConfig, tcRequest, theSession);
            personalConfig.testTaskAccess(commonConfig, tcRequest);
            Threads.setContextClassLoader(outerLoader);
        } catch (Exception securityException) {
            // F�r diese Aktion ist eine andere Berechtigung n�tig
            if (logger.isLoggable(Level.INFO))
                logger.log(Level.INFO, Resources.getInstance().get("REQUESTDISPATCHER_LOG_SESSION_ERROR", requestID, module, task));
            if (logger.isLoggable(Level.INFO))
                logger.log(Level.INFO, Resources.getInstance().get("REQUESTDISPATCHER_LOG_SESSION_ERROR", requestID, module, task), securityException);
                
            if (securityException instanceof TcSecurityException)
                sendAuthenticationError(moduleConfig, commonConfig, tcRequest, tcResponse, (TcSecurityException)securityException);
            else
                sendAuthenticationError(moduleConfig, commonConfig, tcRequest, tcResponse, new TcSecurityException(TcSecurityException.ERROR_SERVER_AUTH_ERROR, securityException));
                
            if (logger.isLoggable(Level.INFO))
                logger.log(Level.FINE, "Authentication Error wurde an den Client gesendet. Kehre nun zur�ck.");
            
        	Context.clear();
            return;
        } finally {
            Threads.setContextClassLoader(outerLoader);
        }
        
        // Berechtigung erfolgreich gepr�ft!
        config.setPersonalConfig(personalConfig);
        
        
        //
        // Authentifizierung ist beendet.
        //

        //==================================================
        // 3. Abarbeitung des auszuf�hrenden Task
        TcTaskManager taskManager = new TcTaskManager(context);
        try {
            Context.setActive(context);
            outerLoader = Threads.setContextClassLoader(moduleConfig.getClassLoader());
            
            putStandardParams(moduleConfig, config, tcResponse, tcRequest, content);

            taskManager.start(tcRequest.getModule(), tcRequest.getTask(), true);

            // Abarbeitung aller Schritte
            // durch den TaskManager
            while (taskManager.doNextStep()) { /* Do nothing here */ }

            TcResponseDescription responseDescription  = taskManager.getCurrentResponseDescription();            
            // Sending the response
            responseCreator.sendResponse(moduleConfig,
                                         config,
                                         tcResponse,
                                         content,
                                         responseDescription,
                                         tcRequest);
            
        } catch (TcContentProzessException cpe) {
            logger.log(Level.SEVERE, Resources.getInstance().get("REQUESTDISPATCHER_LOG_TASK_ERROR", requestID, task), cpe);
        	if ("soapFault".equalsIgnoreCase(taskManager.getCurrentOnErrorAction()))
        		tcResponse.sendError(TcRequest.REQUEST_TYPE_SOAP, requestID, null, cpe);
            else
                sendError(moduleConfig,config,tcResponse,tcRequest,
                          Resources.getInstance().get("REQUESTDISPATCHER_OUT_TASK_ERROR", task),
                          cpe);

        } catch (ResponseProcessingException rpe) {
            logger.log(Level.SEVERE, Resources.getInstance().get("REQUESTDISPATCHER_LOG_RESPONSE_ERROR", requestID), rpe);
            sendError(moduleConfig,config,tcResponse,tcRequest,
                      Resources.getInstance().get("REQUESTDISPATCHER_OUT_RESPONSE_ERROR"),
                      rpe);
        } catch (Exception e) {
            logger.log(Level.SEVERE, Resources.getInstance().get("REQUESTDISPATCHER_LOG_TASK_ERROR", requestID, task), e);
            sendError(moduleConfig,config,tcResponse,tcRequest,
                      Resources.getInstance().get("REQUESTDISPATCHER_OUT_TASK_ERROR", task),
                      e);
        } finally {
            Threads.setContextClassLoader(outerLoader);
            Context.clear();
        }
    }

    /**
     * Einige Parameter, die fast immer in der Ausgabe ben�tigt werden,
     * schonmal in den Content schieben
     */
    public void putStandardParams(
        TcModuleConfig moduleConfig,
        TcConfig config,
        TcResponse tcResponse,
        TcRequest request,
        TcContent theContent) {
        String standardParamWorker = Resources.getInstance().get("REQUESTDISPATCHER_CLS_PARAM_WORKER");
        try {
            
            TcContentWorker worker = TcContentWorkerFactory.getContentWorker(moduleConfig, standardParamWorker, request.getRequestID());
            worker.doAction(config, "putMinimal", request, theContent);
        } catch (Exception e) {
            logger.log(
                Level.SEVERE,
                Resources.getInstance().get(
                    "REQUESTDISPATCHER_LOG_PARAM_SET_ERROR",
                    request.getRequestID(),
                    standardParamWorker),
                e);
        }
    }

    /**
     * Diese Methode liefert das enthaltene Konfigurationsobjekt.
     * 
     * @return Konfigurationsobjekt.
     */
    public TcCommonConfig getCommonConfig() {
        return commonConfig;
    }

    private void sendError(
        TcModuleConfig moduleConfig,
        TcConfig config,
        TcResponse tcResponse,
        TcRequest request,
        String message,
        Exception e)
        throws ResponseProcessingException {

        if (e instanceof de.tarent.octopus.security.TcSecurityException
            && ! TcRequest.isWebType(request.getRequestType()) ) {
            sendAuthenticationError(moduleConfig, config.getCommonConfig(), request, tcResponse, (TcSecurityException)e);
            return;
        }

        TcResponseDescription responseDescription;
        if (e instanceof TcSecurityException)
            responseDescription = config.getLoginResponseDescription();
        else
            responseDescription = config.getDefaultResponseDescription();
        
        TcContent theContent = new TcContent(e);
        Map responseParams = new HashMap();
        responseParams.put("message", message);
        theContent.setField("responseParams", responseParams);
        putStandardParams(moduleConfig, config, tcResponse, request, theContent);
        logger.log(
                   Level.FINE,
                   Resources.getInstance().get("REQUESTDISPATCHER_LOG_SENDING_ERROR", request.getRequestID(), message),
                   e);

        responseCreator.sendResponse(
            moduleConfig,
            config,
            tcResponse,
            theContent,
            responseDescription,
            request);
    }

    /** 
     * Senden von Informationen zu den Fehlern, die bei der Authentifizierung aufgetreten sind.
     */
    private void sendAuthenticationError(TcModuleConfig moduleConfig, TcCommonConfig config, TcRequest tcRequest, TcResponse tcResponse, TcSecurityException securityException) 
        throws ResponseProcessingException {

        //String message = securityException.getMessage();

        tcResponse.setAuthorisationRequired(moduleConfig.getOnUnauthorizedAction());

        TcConfig cfg = new TcConfig(config, null, tcRequest.getModule());

        if (TcRequest.isWebType(tcRequest.getRequestType()) && ! "soap".equalsIgnoreCase(cfg.getDefaultResponseType())) {
            // Web-Type �ber sendResponse abwickeln.
            sendError(moduleConfig, cfg, tcResponse, tcRequest, securityException.getMessage(), securityException);
        } else {            
            // Eine SOAP Anfrage bekommt auch eine SOAP Fehlermeldung
            //throw new ResponseProcessingException(message, new TcSOAPException(message));
            if (logger.isLoggable(Level.FINE))
                logger.log(Level.FINE, Resources.getInstance().get("REQUESTDISPATCHER_LOG_SENDING_ERROR", tcRequest.getRequestID(), securityException.getMessage()));
            if (logger.isLoggable(Level.FINEST))
                logger.log(Level.FINEST, Resources.getInstance().get("REQUESTDISPATCHER_LOG_SENDING_ERROR", tcRequest.getRequestID(), securityException.getMessage()), securityException);
            tcResponse.sendError(TcRequest.REQUEST_TYPE_SOAP, tcRequest.getRequestID(), securityException.getMessage(), new TcSOAPException(securityException));
            return;
        }

// TODO: Wieder einbinden
//         if (tcRequest.askForCookies() && !tcRequest.supportCookies()) {
//             sendError(
//                       moduleConfig,
//                       config,
//                       tcResponse,
//                       tcRequest,
//                       Resources.getInstance().get("REQUESTDISPATCHER_OUT_NO_COOKIES"),
//                       new TcSecurityException(Resources.getInstance().get("REQUESTDISPATCHER_EXC_NO_COOKIES")));
//             return;
//         }                        
    }
}
