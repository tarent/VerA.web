package de.tarent.octopus.request;

/*-
 * VerA.web-Middleware, newly MIT licenced, is comprised of:
 * tarent-commons, a set of common components and solutions
 * tarent-contact, platform-independent webservice-based contact management
 * tarent-database, jdbc database library
 * tarent-doctor, Document Generation Platform
 * tarent-octopus, Webservice Data Integrator and Application Server
 *  © 2005, 2006, 2007 asteban (s.mancke@tarent.de)
 *  © 2007 David Goemans (d.goemans@tarent.de)
 *  © 2006, 2007, 2010 Hendrik Helwich (h.helwich@tarent.de)
 *  © 2005, 2006, 2007 Christoph Jerolimov (c.jerolimov@tarent.de)
 *  © 2006 Philipp Kirchner (p.kirchner@tarent.de)
 *  © 2010 Carsten Klein (c.klein@tarent.de)
 *  © 2006 Michael Kleinhenz (m.kleinhenz@tarent.de)
 *  © 2006 Michael Klink (m.klink@tarent.de)
 *  © 2007 Fabian Köster (f.koester@tarent.de)
 *  © 2006 Martin Ley (m.ley@tarent.de)
 *  © 2007 Alex Maier (a.maier@tarent.de)
 *  © 2007, 2015, 2017, 2018 mirabilos (t.glaser@tarent.de)
 *  © 2006, 2007 Jens Neumaier (j.neumaier@tarent.de)
 *  © 2006 Nils Neumaier (n.neumaier@tarent.de)
 *  © 2007, 2008 Martin Pelzer (m.pelzer@tarent.de)
 *  © 2008, 2009 Christian Preilowski (c.thiel@tarent.de)
 *  © 2006, 2008, 2009 Thomas Schmitz (t.schmitz@tarent.de)
 *  © 2007 Robert Schuster (r.schuster@tarent.de)
 * and older code, Copyright © 2001–2007 ⮡ tarent GmbH and contributors.
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

import de.tarent.octopus.config.TcCommonConfig;
import de.tarent.octopus.config.TcConfig;
import de.tarent.octopus.config.TcModuleConfig;
import de.tarent.octopus.content.*;
import de.tarent.octopus.logging.LogFactory;
import de.tarent.octopus.resource.Resources;
import de.tarent.octopus.response.ResponseProcessingException;
import de.tarent.octopus.response.TcResponseCreator;
import de.tarent.octopus.response.TcResponseDescription;
import de.tarent.octopus.security.TcSecurityException;
import de.tarent.octopus.server.*;
import de.tarent.octopus.soap.TcSOAPException;
import de.tarent.octopus.util.Threads;
import org.apache.commons.logging.Log;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Wichtigste Steuerkomponente für den Ablauf und die Abarbeitung einer Anfrage.
 *
 * Initialisiert das ganze System mit dem Konstructor
 * und arbeitet eine Anfrage mit dispatch() ab.
 *
 * @author <a href="mailto:mancke@mancke-software.de">Sebastian Mancke</a>, <b>tarent GmbH</b>
 * @author Michael Klink, tarent GmbH
 */
public class TcRequestDispatcher /*implements Serializable*/ {
    //XXX TODO: TcCommonConfig is not serialisable
    private static final long serialVersionUID = -777103062849130446L;
    private static final Log logger = LogFactory.getLog(TcRequestDispatcher.class);

    private static final String DEFAULT_TASK_NAME = "default";

    private TcResponseCreator responseCreator;
    private TcCommonConfig commonConfig;

    /**
     * Initialisierung des Systes.
     * Die Komponenten TcResponseCreator, TcSecurityManager und TcCommonConfig
     * werden aufgebaut und initialisiert.
     *
     * @param common gemeinsames Basiskonfigurationsobjekt
     */
    public TcRequestDispatcher(TcCommonConfig common) {
        commonConfig = common;
        responseCreator = new TcResponseCreator();
    }

    /**
     * Abarbeitung einer Anfrage.
     * <b>Ablauf:</b><ol>
     * <li>Wenn der Request als Task 'login' oder 'logout' hat,
     * wird über den SecutityManager versucht, dies zu tun.</li>
     * <li>Als nächstes wird kontrolliert, ob der Benutzer eingeloggt ist,
     * gegebenenfalls wird versucht, einen Login zu erzwingen. Wenn dies
     * der Fall ist wird die Config mit der Personal- und CommonConfig
     * erstellt.</li>
     * <li>Nun wird das Task abgearbeitet. Dazu werden abhängig vom TaskManager,
     * der die Tasksteuerung übernimmt, die ActionWorker gestartet, die den
     * Content verarbeiten.</li>
     * <li>Als letztes wird die Ausgabe über den ResponseCreator erstellt und
     * ausgegeben. Welche Ausgabe gemacht werden soll, bestimmt der
     * TaskManager.</li>
     * </ol>
     *
     * @param tcRequest  Anfrageparameter.
     * @param tcResponse Objekt, das Methoden für die Ausgabe des Ergebnisses
     *                   bereit stellt. Es wird an den ResponseCreator weiter gegeben.
     * @param theSession Sessionobjekt, das die PersonalConfig aufnehmen kann.
     */
    public void dispatch(TcRequest tcRequest, TcResponse tcResponse, TcSession theSession)
            throws ResponseProcessingException {

        String requestID = tcRequest.getRequestID();

        //==================================================
        // 1. Task- und Modulname feststellen und verifizieren

        String module = tcRequest.getModule();
        String task = tcRequest.getTask();

        if ((module == null || module.length() == 0)
                && commonConfig.getDefaultModuleName() != null) {
            module = commonConfig.getDefaultModuleName();
            tcRequest.setModule(module);
        }

        TcModuleConfig moduleConfig = commonConfig.getModuleConfig(module);
        if (moduleConfig == null) {
            logger.error(Resources.getInstance().get("REQUESTDISPATCHER_LOG_NO_MODULE", requestID, module));
            throw new ResponseProcessingException(
                    Resources.getInstance().get("REQUESTDISPATCHER_EXC_NO_MODULE", module));
        }

        if (task == null || task.length() == 0) {
            task = DEFAULT_TASK_NAME;
            tcRequest.setTask(task);
        }
        if (moduleConfig.getTaskList().getTask(task) == null) {
            logger.error(
                    Resources.getInstance().get("REQUESTDISPATCHER_LOG_NO_TASK", requestID, task, module));
            throw new ResponseProcessingException(
                    Resources.getInstance().get("REQUESTDISPATCHER_EXC_NO_TASK", task, module));
        }

        tcResponse.setModuleName(module);
        tcResponse.setTaskName(task);

        logger.info(Resources.getInstance().get("REQUESTDISPATCHER_LOG_DISPATCHING", requestID, module, task));

        //
        // Wirklich auszuführendes Module und Task sind nun identifiziert.
        //

        //==================================================
        // 2. Security
        PersonalConfig personalConfig = null;
        TcConfig config = new TcConfig(commonConfig, personalConfig, module);
        TcContent content = new TcContent();
        OctopusContext context = new TcAll(tcRequest, content, config);
        ClassLoader outerLoader = null;

        try {
            Context.addActive(context);
            outerLoader = Threads.setContextClassLoader(moduleConfig.getClassLoader());
            LoginManager loginManager = commonConfig.getLoginManager(moduleConfig);
            loginManager.handleAuthentication(commonConfig, tcRequest, theSession);
            personalConfig = loginManager.getPersonalConfig(commonConfig, tcRequest, theSession);
            personalConfig.testTaskAccess(commonConfig, tcRequest);
            Threads.setContextClassLoader(outerLoader);
        } catch (Exception securityException) {
            // Für diese Aktion ist eine andere Berechtigung nötig
            if (logger.isInfoEnabled()) {
                logger.info(Resources.getInstance()
                        .get("REQUESTDISPATCHER_LOG_SESSION_ERROR", requestID, module, task));
            }
            if (logger.isInfoEnabled()) {
                logger.info(Resources.getInstance()
                                .get("REQUESTDISPATCHER_LOG_SESSION_ERROR", requestID, module, task),
                        securityException);
            }

            if (securityException instanceof TcSecurityException) {
                sendAuthenticationError(moduleConfig, commonConfig, tcRequest, tcResponse,
                        (TcSecurityException) securityException);
            } else {
                sendAuthenticationError(moduleConfig, commonConfig, tcRequest, tcResponse,
                        new TcSecurityException(TcSecurityException.ERROR_SERVER_AUTH_ERROR,
                                securityException));
            }

            if (logger.isInfoEnabled()) {
                logger.debug("Authentication Error wurde an den Client gesendet. Kehre nun zurück.");
            }

            return;
        } finally {
            Threads.setContextClassLoader(outerLoader);
            Context.clear();
        }

        // Berechtigung erfolgreich geprüft!
        config.setPersonalConfig(personalConfig);

        //
        // Authentifizierung ist beendet.
        //

        //==================================================
        // 3. Abarbeitung des auszuführenden Task
        TcTaskManager taskManager = new TcTaskManager(context);
        try {
            Context.addActive(context);
            outerLoader = Threads.setContextClassLoader(moduleConfig.getClassLoader());

            putStandardParams(moduleConfig, config, tcResponse, tcRequest, content);

            taskManager.start(tcRequest.getModule(), tcRequest.getTask(), true);

            // Abarbeitung aller Schritte
            // durch den TaskManager
            while (taskManager.doNextStep()) { /* Do nothing here */ }

            TcResponseDescription responseDescription = taskManager.getCurrentResponseDescription();
            // Sending the response
            responseCreator.sendResponse(moduleConfig,
                    config,
                    tcResponse,
                    content,
                    responseDescription,
                    tcRequest);

        } catch (TcContentProzessException cpe) {
            logger.error(Resources.getInstance().get("REQUESTDISPATCHER_LOG_TASK_ERROR", requestID, task),
                    cpe);
            if ("soapFault".equalsIgnoreCase(taskManager.getCurrentOnErrorAction())) {
                tcResponse.sendError(TcRequest.REQUEST_TYPE_SOAP, requestID, null, cpe);
            } else {
                sendError(moduleConfig, config, tcResponse, tcRequest,
                        Resources.getInstance().get("REQUESTDISPATCHER_OUT_TASK_ERROR", task),
                        cpe);
            }

        } catch (ResponseProcessingException rpe) {
            logger.error(Resources.getInstance().get("REQUESTDISPATCHER_LOG_RESPONSE_ERROR", requestID),
                    rpe);
            sendError(moduleConfig, config, tcResponse, tcRequest,
                    Resources.getInstance().get("REQUESTDISPATCHER_OUT_RESPONSE_ERROR"),
                    rpe);
        } catch (Exception e) {
            logger.error(Resources.getInstance().get("REQUESTDISPATCHER_LOG_TASK_ERROR", requestID, task),
                    e);
            sendError(moduleConfig, config, tcResponse, tcRequest,
                    Resources.getInstance().get("REQUESTDISPATCHER_OUT_TASK_ERROR", task),
                    e);
        } finally {
            // run the cleanup hooks; this should never cause an exception
            processCleanupCode(requestID, content);

            Threads.setContextClassLoader(outerLoader);
            Context.clear();
        }
    }

    /**
     * This helper method executes all {@link Runnable} and {@link Closeable} objects
     * in the cleanup list within the context. This list is expected to be associated
     * with the key {@link OctopusContext#CLEANUP_OBJECT_LIST "octopus.cleanup.objects"}.
     * During this process the cleanup list is emptied.
     */
    public static void processCleanupCode(String requestID, TcContent theContent) {
        Object cleanupObjectList = theContent.getAsObject(OctopusContext.CLEANUP_OBJECT_LIST);
        if (cleanupObjectList instanceof Collection) {
            for (Iterator iter = ((Collection) cleanupObjectList).iterator(); iter.hasNext(); ) {
                Object cleanupObject = iter.next();
                try {
                    if (cleanupObject instanceof Runnable) {
                        ((Runnable) cleanupObject).run();
                        // remove after processing
                        iter.remove();
                    } else if (cleanupObject instanceof Closeable) {
                        ((Closeable) cleanupObject).close();
                        // remove after processing
                        iter.remove();
                    } else {
                        logger.error(Resources.getInstance()
                                .get("REQUESTDISPATCHER_LOG_CLEANUPOBJECT_WRONG_TYPE", requestID,
                                        (cleanupObject == null ? null :
                                                cleanupObject.getClass().getName()), cleanupObject));
                    }
                } catch (Exception t) {
                    logger.error(Resources.getInstance()
                            .get("REQUESTDISPATCHER_LOG_CLEANUPOBJECT_CLOSE_ERROR", requestID,
                                    cleanupObject), t);
                }
            }
        } else if (cleanupObjectList != null) {
            logger.warn(Resources.getInstance().get("REQUESTDISPATCHER_LOG_INVALID_CLEANUPLIST", requestID,
                    cleanupObjectList.getClass().getName()));
        }
    }

    /**
     * Einige Parameter, die fast immer in der Ausgabe benötigt werden,
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

            TcContentWorker worker = TcContentWorkerFactory.getContentWorker(moduleConfig,
                    standardParamWorker, request.getRequestID());
            worker.doAction(config, "putMinimal", request, theContent);
        } catch (Exception e) {
            logger.error(Resources.getInstance().get(
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
                && !TcRequest.isWebType(request.getRequestType())) {
            sendAuthenticationError(moduleConfig, config.getCommonConfig(), request, tcResponse,
                    (TcSecurityException) e);
            return;
        }

        TcResponseDescription responseDescription;
        if (e instanceof TcSecurityException) {
            responseDescription = config.getLoginResponseDescription();
        } else {
            responseDescription = config.getDefaultResponseDescription();
        }

        TcContent theContent = new TcContent(e);
        Map responseParams = new HashMap();
        responseParams.put("message", message);
        theContent.setField("responseParams", responseParams);
        putStandardParams(moduleConfig, config, tcResponse, request, theContent);
        logger.debug(
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
    private void sendAuthenticationError(TcModuleConfig moduleConfig, TcCommonConfig config, TcRequest tcRequest,
            TcResponse tcResponse, TcSecurityException securityException)
            throws ResponseProcessingException {

        //String message = securityException.getMessage();

        tcResponse.setAuthorisationRequired(moduleConfig.getOnUnauthorizedAction());

        TcConfig cfg = new TcConfig(config, null, tcRequest.getModule());

        if (TcRequest.isWebType(tcRequest.getRequestType()) && !"soap".equalsIgnoreCase(
                cfg.getDefaultResponseType())) {
            // Web-Type über sendResponse abwickeln.
            sendError(moduleConfig, cfg, tcResponse, tcRequest, securityException.getMessage(),
                    securityException);
        } else {
            // Eine SOAP Anfrage bekommt auch eine SOAP Fehlermeldung
            //throw new ResponseProcessingException(message, new TcSOAPException(message));
            if (logger.isDebugEnabled()) {
                logger.debug(Resources.getInstance()
                        .get("REQUESTDISPATCHER_LOG_SENDING_ERROR", tcRequest.getRequestID(),
                                securityException.getMessage()));
            }
            if (logger.isTraceEnabled()) {
                logger.trace(Resources.getInstance()
                        .get("REQUESTDISPATCHER_LOG_SENDING_ERROR", tcRequest.getRequestID(),
                                securityException.getMessage()), securityException);
            }
            tcResponse.sendError(TcRequest.REQUEST_TYPE_SOAP, tcRequest.getRequestID(),
                    securityException.getMessage(), new TcSOAPException(securityException));
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