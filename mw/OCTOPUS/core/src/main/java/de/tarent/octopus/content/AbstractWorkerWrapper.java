package de.tarent.octopus.content;

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

import de.tarent.octopus.config.TcConfig;
import de.tarent.octopus.config.TcModuleConfig;
import de.tarent.octopus.logging.LogFactory;
import de.tarent.octopus.request.TcRequest;
import de.tarent.octopus.resource.Resources;
import de.tarent.octopus.server.InOutParam;
import de.tarent.octopus.server.OctopusContext;
import org.apache.commons.logging.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Basisklasse für Worker-Wrapper nach dem Template-Method Pattern.
 *
 * @author Sebastian Mancke
 */
public abstract class AbstractWorkerWrapper implements TcContentWorker, DelegatingWorker {
    private static Log logger = LogFactory.getLog(AbstractWorkerWrapper.class);

    /**
     * Worker, an den Aufrufe delegiert werden sollen.
     */
    Object workerDelegate;

    /**
     * Klasse des Workers, an den Aufrufe delegiert werden sollen.
     */
    Class workerClass;

    /**
     * Cache für die Metainformationen zu den Actions
     */
    HashMap actionDataLookup = new HashMap();

    private static Class[] emptyClassArray = new Class[] {};
    private static Object[] emptyObjectArray = new Object[] {};

    public AbstractWorkerWrapper(Object workerDelegate) {
        this.workerDelegate = workerDelegate;
        workerClass = workerDelegate.getClass();
    }

    /**
     * @see de.tarent.octopus.content.DelegatingWorker
     */
    public Object getWorkerDelegate() {
        return workerDelegate;
    }

    /**
     * Returns the Class object from the target worker
     */
    public Class getWorkerClass() {
        return workerClass;
    }

    /**
     * Template Methode zur lieferung der Metadaten einer Actions
     *
     * @param actionName Name der Action
     * @return Metadaten-Objekt
     */
    public abstract ActionData getActionData(String actionName) throws TcActionDeclarationException;

    /**
     * Liefert die Namen aller von dem Worker bereit gestellten Actions zurück
     */
    public abstract String[] getActionNames() throws TcActionDeclarationException;

    /**
     * Liefert die Version, in der der Worker vorliegt.
     */
    public abstract String getVersion();

    /**
     * Wrap the value with an InOutParam Container
     */
    public abstract EnrichedInOutParam wrapWithInOutParam(Object value);

    /**
     * Initialisierung des Workers
     */
    public void init(TcModuleConfig config) {
        try {
            Method m = workerClass.getMethod("init", new Class[] { TcModuleConfig.class });
            m.invoke(workerDelegate, new Object[] { config });
        } catch (SecurityException e) {
            logger.error(e.getMessage(), e);
        } catch (NoSuchMethodException e) {
            //DO NOTHING
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("Fehler bei Ausführung der init() Methode von " + workerClass.getName(), e);
        }
    }

    /**
     * Aufruf einer Action des Workers
     */
    public String doAction(TcConfig tcConfig, String actionName, TcRequest tcRequest, TcContent tcContent)
            throws TcContentProzessException {
        try {

            ActionData actionData = getActionDataCached(actionName);

            Object[] args = new Object[actionData.args.length];
            int argsPos = 0;
            List inOutParams = new LinkedList();
            OctopusContext octopusContext = new TcAll(tcRequest, tcContent, tcConfig);

            if (actionData.passOctopusContext) {
                args[argsPos++] = octopusContext;
            }

            for (int i = 0; i < actionData.genericArgsCount; i++) {

                // If there is no name for the parameter,
                // it schould be filled with an null-Value
                if (null == actionData.inputParams[i]) {
                    args[argsPos++] = null;
                    if (logger.isTraceEnabled()) {
                        logger.trace(i + ". generic param is not declared as WebParam, applying null");
                    }
                } else {
                    Object paramValue = octopusContext.getContextField(actionData.inputParams[i]);
                    if (logger.isTraceEnabled()) {
                        logger.trace("Filling " + i + ". generic param with context-field: " + actionData.inputParams[i] +
                                " paramValue=" + paramValue);
                    }

                    if (paramValue == null && actionData.mandatoryFlags[i]) {
                        throw new TcActionInvocationException(Resources.getInstance()
                                .get("WORKER_WRAPPER_EXC_MISSING_PARAM",
                                        new Object[] {
                                                tcRequest.getRequestID(),
                                                actionData.inputParams[i],
                                                actionData.getArgTargetType(argsPos).getName(),
                                                workerClass.getName(),
                                                actionName
                                        }));
                    }
                    // type conversion
                    if (!actionData.getArgTargetType(argsPos).isInstance(paramValue)) {
                        paramValue = tryToConvert(paramValue, actionData.getArgTargetType(argsPos));
                        if (logger.isTraceEnabled()) {
                            logger.trace("Applying type conversion for param " + actionData.inputParams[i] + " to type " +
                                    actionData.getArgTargetType(argsPos));
                            logger.trace("New value: " + paramValue);
                        }
                    }

                    if (actionData.isInOutParam(argsPos)) {
                        if (logger.isTraceEnabled()) {
                            logger.trace("Wrapping param " + actionData.inputParams[i] + " as InOutParam.");
                        }
                        EnrichedInOutParam iop = wrapWithInOutParam(paramValue);
                        iop.setContextFieldName(actionData.inputParams[i]);
                        inOutParams.add(iop);
                        paramValue = iop;
                    }
                    args[argsPos++] = paramValue;
                }
            }
            Object result;

            result = actionData.method.invoke(workerDelegate, args);
            if (actionData.outputParam != null) {
                octopusContext.setContextField(actionData.outputParam, result);
                if (logger.isTraceEnabled()) {
                    logger.trace("Action result [" + actionData.outputParam + "]:" + result);
                }
            }

            for (Iterator iter = inOutParams.iterator(); iter.hasNext(); ) {
                EnrichedInOutParam ioParam = (EnrichedInOutParam) iter.next();
                octopusContext.setContextField(ioParam.getContextFieldName(), ioParam.get());
                if (logger.isTraceEnabled()) {
                    logger.trace("Action result from InOutParam [" + ioParam.getContextFieldName() + "]:" + ioParam.get());
                }
            }

            return (octopusContext.getStatus() != null) ?
                    octopusContext.getStatus() : TcContentWorker.RESULT_ok;
        } catch (TcContentProzessException e) {
            throw e;
        } catch (IllegalArgumentException e) {
            throw new TcActionInvocationException(
                    "Anfragefehler: Fehler beim Aufruf einer Worker-Action: (" + workerClass.getName() + "#" + actionName + ")",
                    e);
        } catch (IllegalAccessException e) {
            throw new TcActionInvocationException(
                    "Anfragefehler: Fehler beim Aufruf einer Worker-Action: (" + workerClass.getName() + "#" + actionName + ")",
                    e);
        } catch (InvocationTargetException e) {
            Throwable t = e.getTargetException();
            if (t instanceof TcContentProzessException) {
                throw (TcContentProzessException) t;
            } else {
                throw new TcActionInvocationException(
                        "Anfragefehler: Fehler beim Aufruf einer Worker-Action: (" + workerClass.getName() + "#" + actionName +
                                ")", t);
            }
        }
    }

    /**
     * Convertiert ein Object.
     * Falls dies fehl schlägt oder der Parameter==null ist wird <code>null</code> zurückgegeben.
     *
     * TODO: Unterstützung für long => Date
     */
    protected Object tryToConvert(Object param, Class targetType) throws TcContentProzessException {
        try {
            if (param == null && !targetType.isPrimitive()) {
                return null;
            }

            if (targetType.equals(Boolean.class) || targetType.equals(Boolean.TYPE)) {
                if (param == null) {
                    return Boolean.FALSE;
                }
                return Boolean.valueOf(param.toString());
            } else if (targetType.equals(Integer.class) || targetType.equals(Integer.TYPE)) {
                if (param == null || param.toString().length() == 0) {
                    return new Integer(0);
                }
                return Integer.valueOf(param.toString());
            } else if (targetType.equals(Long.class) || targetType.equals(Long.TYPE)) {
                if (param == null || param.toString().length() == 0) {
                    return new Long(0);
                }
                return Long.valueOf(param.toString());
            } else if (targetType.equals(Double.class) || targetType.equals(Double.TYPE)) {
                if (param == null || param.toString().length() == 0) {
                    return new Double(0);
                }
                return Double.valueOf(param.toString());
            } else if (targetType.equals(Float.class) || targetType.equals(Float.TYPE)) {
                if (param == null || param.toString().length() == 0) {
                    return new Float(0);
                }
                return Float.valueOf(param.toString());
            } else if (Collection.class.isAssignableFrom(targetType) && param instanceof Object[]) {
                return Arrays.asList((Object[]) param);
            } else if (Collection.class.isAssignableFrom(targetType)) {
                if (param == null) {
                    return null;
                }
                return Collections.singletonList(param);
            } else if (targetType.equals(String.class)) {
                if (param == null) {
                    return null;
                }
                return param.toString();
            }

            // The Method param is an special Implementation of Map e.g. MapBean
            // and the Param is a Map. Then we create a BeanMap with the key=>values from the Map
            else if (param instanceof Map && Map.class.isAssignableFrom(targetType)) {
                try {
                    Map newSpectialMap = (Map) targetType.getConstructor(emptyClassArray).newInstance(emptyObjectArray);
                    newSpectialMap.putAll((Map) param);
                    return newSpectialMap;
                } catch (Exception e) {
                    logger.warn("Fehler beim Konvertieren eines Übergabeparamters (Map nach " + targetType.getName() + ")", e);
                    throw new TcContentProzessException(
                            "Fehler beim Konvertieren eines Übergabeparamters (Map nach " + targetType.getName() + ")", e);
                }
            }
        } catch (NumberFormatException e) {
            logger.warn("Formatfehler beim Konvertieren eines Übergabeparamters (von " +
                    ((param != null) ? param.getClass().toString() : "null") + " nach " +
                    ((targetType != null) ? targetType.getName() : "null") + ")", e);
            //Altes Verhalten wird wiederhergestellt, die TcContentProcessException
            //Macht z.b. im Broker(evtl. alle anderen SBK-Projekte) Probleme
            //throw new TcContentProzessException("Formatfehler Fehler beim Konvertieren eines Übergabeparamters (von "+param
            // .getClass()+" nach "+targetType.getName()+")", e);
            return null;
        }
        throw new TcContentProzessException("Keine Konvertierungsregel für die Umwandlung von " +
                (param != null ? param.getClass() : null) +
                " nach " + targetType.getName() + " vorhanden.");
    }

    /**
     * Holt die Metadaten zu der Action aus dem Cache,
     * oder stellt sie über getActionData neu zusammen.
     *
     * @param actionName Name der Action
     * @return Metadaten die beschreiben, wie die Action-Methode aufgerufen werden soll.
     */
    protected ActionData getActionDataCached(String actionName) throws TcActionDeclarationException {
        ActionData action = (ActionData) actionDataLookup.get(actionName);
        if (null == action) {
            action = getActionData(actionName);
            actionDataLookup.put(actionName, action);
        }
        return action;
    }

    /**
     * TODO: Berücksichtigen von:
     * - Datentypen der Signatur
     * - InOutParams
     * - Descriptions
     * - Mögliche Exceptions
     */
    public TcPortDefinition getWorkerDefinition() {
        try {
            TcPortDefinition port = new TcPortDefinition(workerClass.getName(), "n/a");

            String[] actionNames = getActionNames();
            for (int i = 0; i < actionNames.length; i++) {

                String operationName = actionNames[i];

                Method[] methods = workerClass.getMethods();
                for (int k = 0; k < methods.length; k++) {
                    if (operationName.equalsIgnoreCase(methods[k].getName())) {
                        operationName = methods[k].getName();
                        break;
                    }
                }

                ActionData actionData = getActionDataCached(operationName);
                TcOperationDefinition operation = new TcOperationDefinition(operationName, "n/a");
                TcMessageDefinition in = new TcMessageDefinition();
                TcMessageDefinition out = new TcMessageDefinition();
                for (int j = 0; j < actionData.inputParams.length; j++) {

                    int argsPos = actionData.passOctopusContext ? j + 1 : j;
                    boolean isInOutParameter = actionData.args[argsPos].equals(InOutParam.class);
                    String type = "xsd:anyType";

                    if (!isInOutParameter) {
                        type = actionData.args[argsPos].getName();
                    }

                    in.addPart(actionData.inputParams[j], type, "n/a", !actionData.mandatoryFlags[j]);
                    if (isInOutParameter) {
                        out.addPart(actionData.inputParams[j], type, "n/a");
                    }
                }
                if (actionData.outputParam != null) {
                    out.addPart(actionData.outputParam, actionData.method.getReturnType().getName(), "n/a");
                }
                operation.setInputMessage(in);
                operation.setOutputMessage(out);
                port.addOperation(operation);
            }

            return port;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("Fehler beim Ermitteln der Selbstbeschreibung von " + workerClass.getName(), e);
        }
    }

    public interface EnrichedInOutParam extends InOutParam {
        public String getContextFieldName();

        public void setContextFieldName(String newContextFieldName);
    }

    /**
     * Structure for storing the metadata of an action
     */
    public class ActionData {
        /**
         * Description of the method
         */
        public String description;

        /**
         * Corresponding method
         */
        public Method method;

        /**
         * Types of all the method parameters
         */
        public Class[] args;

        /**
         * Returns the type for the parameter to use.
         * Normaly, this ist the paramtype. In the case of InOutParams
         * this is the Type ob the InOutParam Value.
         *
         * @throws TcActionDeclarationException
         */
        public Class getArgTargetType(int pos) {
            if (isInOutParam(pos)) {
                return Object.class;
            }
            return args[pos];
        }

        /**
         * Determines wether the param at pos is an in-out Param, or not.
         */
        public boolean isInOutParam(int pos) {
            return args[pos].isAssignableFrom(InOutParam.class);
        }

        /**
         * Count of the parameters without counting the OctopusContext-parameter
         */
        public int genericArgsCount;

        /**
         * Pass OctopusContext as first Parameter
         */
        public boolean passOctopusContext;

        /**
         * Field names in the OctopusContext, to fill the input params. length = genericArgsCount
         */
        public String[] inputParams;

        /**
         * Flag for each param, to indicate, if it ist optional or not. length = genericArgsCount
         */
        public boolean[] mandatoryFlags;

        /**
         * Descriptions for the parameters. length = genericArgsCount
         */
        public String[] descriptions;

        /**
         * Field name in the OctopusContext wich is used to store the output param
         */
        public String outputParam;
    }
}