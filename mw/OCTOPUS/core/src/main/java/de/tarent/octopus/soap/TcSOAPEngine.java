package de.tarent.octopus.soap;

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

import de.tarent.octopus.request.TcEnv;
import de.tarent.octopus.request.TcRequest;
import de.tarent.octopus.resource.Resources;
import lombok.extern.log4j.Log4j2;
import org.apache.axis.AxisEngine;
import org.apache.axis.AxisFault;
import org.apache.axis.ConfigurationException;
import org.apache.axis.Constants;
import org.apache.axis.EngineConfiguration;
import org.apache.axis.Message;
import org.apache.axis.MessageContext;
import org.apache.axis.configuration.FileProvider;
import org.apache.axis.encoding.DefaultSOAPEncodingTypeMappingImpl;
import org.apache.axis.encoding.TypeMapping;
import org.apache.axis.encoding.TypeMappingRegistry;
import org.apache.axis.encoding.ser.SimpleDeserializerFactory;
import org.apache.axis.encoding.ser.SimpleSerializerFactory;
import org.apache.axis.message.MessageElement;
import org.apache.axis.message.RPCElement;
import org.apache.axis.message.RPCParam;
import org.apache.axis.message.SOAPEnvelope;
import org.apache.axis.message.SOAPHeaderElement;
import org.apache.axis.server.AxisServer;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.zip.GZIPInputStream;

/**
 * Bereitstellung und Kapselung von SOAP Funktionalität
 *
 * @author <a href="mailto:mancke@mancke-software.de">Sebastian Mancke</a>, <b>tarent GmbH</b>
 */
@Log4j2
public class TcSOAPEngine implements Serializable {
    private static final long serialVersionUID = 2864111953752691905L;

    public static final String NAMESPACE_URI = "http://schemas.tarent.de/";
    public static final String NAMESPACE_URI_TC = NAMESPACE_URI + "tccontact";

    boolean useSOAPNSAsModule = true;

    public static AxisEngine engine;

    TcEnv env;

    public TcSOAPEngine(TcEnv env) {
        this.env = env;

        if (null != env.getValue(TcEnv.KEY_USE_SOAP_NS_AS_MODULE)) {
            useSOAPNSAsModule = env.getValueAsBoolean(TcEnv.KEY_USE_SOAP_NS_AS_MODULE);
        }

        // TODO: It would be better to bind the SOAP-Engine to a specific module
        //       and configure the type mapping for the modules of the octopus.
        final String axisConfigFile = env.getValueAsString(TcEnv.KEY_PATHS_ROOT) + "axis-config.wsdd";
        if (new File(axisConfigFile).exists()) {
            logger.info(Resources.getInstance()
              .get("SOAPENGINE_LOG_USING_AXIS_CONFIGURATION_FILE", axisConfigFile));
            EngineConfiguration engineConfiguration = new FileProvider(
              axisConfigFile) {  //new FileProvider(axisConfigFile);
                public TypeMappingRegistry getTypeMappingRegistry() {
                    try {
                        return super.getTypeMappingRegistry();
                    } catch (ConfigurationException e) {
                        // log the configuration error
                        logger.error(Resources.getInstance()
                          .get("SOAPENGINE_LOG_AXIS_CONFIGURATION_ERROR", axisConfigFile), e);
                    }
                    return null;
                }
            };
            engine = new AxisServer(engineConfiguration);
        } else {
            logger.debug(Resources.getInstance().get("SOAPENGINE_LOG_USING_AXIS_DEFAULT_CONFIGURATION"));
            engine = new AxisServer();
            registerTypes(engine.getTypeMappingRegistry(), env);
        }
    }

    /**
     * Registrieren der gewollten Default Type Mappings, wenn keine Axis Konfiguration angegeben wurde
     *
     * @deprecated not used since engine configuration is read from an engine configuration file
     */
    protected void registerTypes(TypeMappingRegistry reg, TcEnv env) {

        TypeMapping mapping = DefaultSOAPEncodingTypeMappingImpl.createWithDelegate();
        mapping.register(StringBuffer.class,
          Constants.XSD_STRING,
          new SimpleSerializerFactory(StringBuffer.class, Constants.XSD_STRING),
          new SimpleDeserializerFactory(String.class, Constants.XSD_STRING));

        //mapping.setSupportedEncodings(Constants.URIS_SOAP_ENC);
        for (int i = 0; i < Constants.URIS_SOAP_ENC.length; i++) {
            reg.register(Constants.URIS_SOAP_ENC[i], mapping);
        }
        reg.registerDefault(mapping);
    }

    /**
     * Diese Methode analysiert eine SOAP-Anfrage.
     *
     * @param inStream    Die Anfrage
     * @param requestType der Anfragetyp
     * @param requestID   die Anfrage-ID
     * @return ein generiertes Anfrage-Objekt
     */
    public TcRequest[] readSoapRequests(InputStream inStream, int requestType, String requestID)
      throws TcSOAPException {
        logger.trace(TcSOAPEngine.class.getName() + " readSoapRequests " +
          new Object[] { inStream, new Integer(requestType), requestID });

        if (inStream != null) {
            List moduleList = new ArrayList();
            List taskList = new ArrayList();
            List paramsList = new ArrayList();
            List headerList = new ArrayList();
            analyseSoapRequest(inStream, headerList, moduleList, taskList, paramsList);
            TcRequest[] requests = new TcRequest[paramsList.size()];
            Iterator itModules = moduleList.iterator();
            Iterator itTasks = taskList.iterator();
            Iterator itParams = paramsList.iterator();
            for (int i = 0; itParams.hasNext(); i++) {
                Map params = (Map) itParams.next();
                TcRequest octRequest = new TcRequest(requestID);
                octRequest.setRequestType(requestType);
                octRequest.setRequestParameters(params);
                octRequest.setModule(itModules.next().toString());
                octRequest.setTask(itTasks.next().toString());
                if (!headerList.isEmpty()) {
                    octRequest.setHeaders(new SOAPRequestHeaders(headerList));
                }
                requests[i] = octRequest;
            }
            return requests;
        } else {
            return new TcRequest[0];
        }
    }

    /**
     * Interpretiert die Eingabe als SOAP Message im RPC Style
     *
     * Alle Bestandteile der Aufrufe werden in Maps zurückgegeben.
     * Dabei wird der Methodenname unter dem Key "task" abgelegt,
     * der Namespace der Methode unter "taskNamespaceURI" und
     * die Übergabeparameter unter ihren Namen. Sie können auch Maps
     * und Vektoren sein.
     */
    public void analyseSoapRequest(InputStream message, List headers, List modules, List tasks, List params)
      throws TcSOAPException {
        if (headers == null) {
            headers = new ArrayList();
        }
        if (modules == null) {
            modules = new ArrayList();
        }
        if (tasks == null) {
            tasks = new ArrayList();
        }
        if (params == null) {
            params = new ArrayList();
        }

        // TODO: Logging der Eingangsnachricht an eine andere Stelle verlagern.
        //       z.B. TcRequestProxy
        //         try {
        //             message = HttpHelper.logInput(message, env.get(TcEnv.KEY_LOG_SOAP_REQUEST_LEVEL), "SOAPENGINE_LOG_INPUT");
        //         } catch (IOException e1) {
        //             logger.warn(Resources.getInstance().get("SOAPENGINE_LOG_INPUT_LOG_ERROR"), e1);
        //             throw new TcSOAPException(e1);
        // 		}

        Message requestMsg = new Message(message, false);
        MessageContext msgContext = createMessageContext(engine);
        requestMsg.setMessageContext(msgContext);

        SOAPEnvelope env = null;
        List bodyElements = null;
        Iterator itElements = null;
        List headerElements = null;
        try {
            env = requestMsg.getSOAPEnvelope();
            bodyElements = env.getBodyElements();
            headerElements = env.getHeaders();
        } catch (AxisFault e) {
            logger.warn(Resources.getInstance().get("SOAPENGINE_LOG_SOAP_MSG_ERROR"), e);
            throw new TcSOAPException(e);
        }
        if (bodyElements != null) {
            itElements = bodyElements.iterator();
        }
        while (itElements != null && itElements.hasNext()) {
            try {
                Object element = itElements.next();
                if (!(element instanceof RPCElement)) {
                    continue;
                }
                RPCElement rpcElem = (RPCElement) element;
                String namespace = rpcElem.getNamespaceURI();
                if (namespace == null) {
                    namespace = "";
                }
                modules.add(getModuleNameFromNamespace(namespace));
                //request.put(TcRequest.PARAM_TASK_NAMESPACE_URI, namespace);
                tasks.add(rpcElem.getMethodName());
                params.add(bodyPartToMap(rpcElem));
            } catch (SAXException ex) {
                logger.warn(Resources.getInstance().get("SOAPENGINE_LOG_SAX_BODY_ERROR"), ex);
                throw new TcSOAPException(ex);
            } catch (ClassCastException cce) {
                logger.warn(Resources.getInstance().get("SOAPENGINE_LOG_CAST_BODY_ERROR"), cce);
                throw new TcSOAPException(cce);
            }
        }
        logger.trace("BODIES: " + params);
        if (headerElements != null) {
            itElements = headerElements.iterator();
        }
        while (itElements != null && itElements.hasNext()) {
            try {
                SOAPHeaderElement hdrElem = (SOAPHeaderElement) itElements.next();
                headers.add(headerPartToMap(hdrElem));
            } catch (SAXException ex) {
                logger.warn(Resources.getInstance().get("SOAPENGINE_LOG_SAX_HEADER_ERROR"), ex);
                throw new TcSOAPException(ex);
            } catch (ClassCastException cce) {
                logger.warn(Resources.getInstance().get("SOAPENGINE_LOG_CAST_HEADER_ERROR"), cce);
                throw new TcSOAPException(cce);
            }
        }
        logger.trace("HEADERS: " + headers);
    }

    /**
     * Diese Methode macht aus einem RPC-Element eine Map mit Parametern
     * eines Octopus-Requests.
     *
     * @param bodyPart Body-RPC-Element
     * @return Map der Parameter, des Moduls und des Tasks im RPC-Element
     */
    private Map bodyPartToMap(RPCElement bodyPart) throws SAXException {
        Map request = new TreeMap();
        List params = bodyPart.getParams();
        for (int i = 0; i < params.size(); i++) {
            RPCParam rpcParam = (RPCParam) params.get(i);
            Object value = replaceArrayWithList(rpcParam.getObjectValue());
            request.put(rpcParam.getName(), value);
        }
        return request;
    }

    /**
     * Traversiert die den Map-List-Baum und ersetzt alle Vorkommen von Array durch Listen.
     * <br>
     * Vorsicht: Es werden nur Maps, Listen und Arrays traversiert.
     * Wenn ein Array in einem anderen Datencontainer enthalten ist, wird es nicht gefunden
     * <br>
     * TODO: Besser wäre natürlich ein direktes Deserialisieren als List durch Axis (derzeit nicht unterstützt).
     */
    protected Object replaceArrayWithList(Object o) {
        Object out = o;
        if (out instanceof Object[]) {
            out = Arrays.asList((Object[]) out);
        }

        if (out instanceof List) {
            List list = (List) out;
            for (int i = 0; i < list.size(); i++) {
                Object element = list.get(i);
                Object replacement = replaceArrayWithList(element);

                // Hier ist ein echtes == gemeint, kein equals,
                // da nur ausgetauscht werden muss, wenn sich die Objektinstanz wirklich geändert hat.
                if (replacement != element) {
                    list.set(i, replacement);
                }
            }
        } else if (out instanceof Map) {
            Map map = (Map) out;
            for (Iterator iter = map.entrySet().iterator(); iter.hasNext(); ) {
                Map.Entry entry = (Map.Entry) iter.next();

                Object element = entry.getValue();
                Object replacement = replaceArrayWithList(element);

                // Hier ist ein echtes == gemeint, kein equals,
                // da nur ausgetauscht werden muss, wenn sich die Objektinstanz wirklich geändert hat.
                if (replacement != element) {
                    map.put(entry.getKey(), replacement);
                }
            }
        }
        return out;
    }

    /**
     * This methods returns a map with the header's name as key and a MessageElement as value.
     * These maps will be put in a list structure and not directly in a map because multiple
     * headers of the same name may exists. The load overhead should not be relevant because
     * normaly headers should only be delivered if needed.
     * If the header has DOM-Elements as children the will be recursively processed by this
     * method and provided in a <code>List</code> of Singleton-Maps as well.
     *
     * @param headerPart header to process
     * @return a singleton map with the header's name as key and a <code>MessageElement</code> or a
     * <code>List</code> of subheaders as value
     */
    private Map headerPartToMap(MessageElement headerPart) throws SAXException {
        // test if child elements are present, pretending no elements follow if the first child is a text node
        Node firstChild = headerPart.getFirstChild();
        if (firstChild == null) {
            return null;
        }
        if (firstChild.getNodeType() != Node.TEXT_NODE) {
            List subheaderNodes = headerPart.getChildren();
            List subheaderList = new ArrayList(subheaderNodes.size());
            Iterator iter = subheaderNodes.iterator();
            while (iter.hasNext()) {
                MessageElement subheader = (MessageElement) iter.next();
                subheaderList.add(headerPartToMap(subheader));
            }
            return Collections.singletonMap(headerPart.getName(), subheaderList);
        }
        return Collections.singletonMap(headerPart.getName(), headerPart.getRealElement());
    }

    /**
     * Erstellt einen Context mit Parametern der Nachricht
     * Da unser System die Informationen, für die der MessageContext
     * ist anderweitig bereit stellt, müssen wir da nicht viel rein tun.
     */
    private MessageContext createMessageContext(AxisEngine engine) {
        MessageContext msgContext = new MessageContext(engine);

        msgContext.setTransportName("http");
        return msgContext;
    }

    /**
     * Leitet an createMessageContext( AxisEngine engine )
     * mit engine = this.engine weiter;
     */
    public MessageContext createMessageContext() {
        return createMessageContext(engine);
    }

    /**
     * Hängt einen GZIP Filter über den übergebenen Stream
     */
    public static InputStream addGZIPFilterToInputStream(InputStream inStream) throws java.io.IOException {
        return new GZIPInputStream(inStream);
    }

    /**
     * Soll später einen PGP-Filter über den übergebenen Stream legen.
     * Ist aber nocht nicht implementiert
     */
    public static InputStream addPGPFilterToInputStream(InputStream inStream) {
        return inStream;
    }

    /**
     * Liefert den Namespacebezeichner,
     * unter dem das Schema eines Modules festgelegt ist.
     *
     * Im Moment wird einfach der Modulname an eine bestimmte URL angehängt
     *
     * @param module Der Name eines Modules
     * @return Namespacebezeichner zu dem Modul
     */
    public String getNamespaceByModuleName(String module) {
        if (module == null || module.equals("") || module.equals("default")) {
            return NAMESPACE_URI_TC;
        }
        return NAMESPACE_URI + module;
    }

    /**
     * Liefert den Modulnamen, des Modules, das für einen bestimmter Namespace zuständig ist.
     *
     * Im Moment wird der Modulname einfach aus der URI extrahiert.
     *
     * @param namespaceUri Namespacebezeichner zu dem Modul
     * @return Zugehöriger Modulname
     */
    public String getModuleNameFromNamespace(String namespaceUri) {
        logger.debug("namespace: " + namespaceUri + "\n TcPrefix: " + NAMESPACE_URI_TC);

        String out = ""; // default module

        if (useSOAPNSAsModule) {
            if (namespaceUri.startsWith(NAMESPACE_URI_TC)) {
                out = namespaceUri.substring(NAMESPACE_URI_TC.length());
            } else if (namespaceUri.startsWith(NAMESPACE_URI)) {
                out = namespaceUri.substring(NAMESPACE_URI.length());
            } else {
                logger.debug("Namespace startet nicht mit erlaubten Präfixen");
            }
        }

        if (out.startsWith("/")) {
            out = out.substring(1);
        }

        logger.debug("Modulname: " + out);
        return out;
    }

    //     protected class SingletonSerializerFactory
    //         implements SerializerFactory {

    //         Serializer serializer;
    //         List mechanisms;
    //         public SingletonSerializerFactory(Serializer serializer) {
    //             this.serializer = serializer;
    //         }
    //         public javax.xml.rpc.encoding.Serializer getSerializerAs(java.lang.String mechanismType) {
    //             return serializer;
    //         }
    //         public Iterator getSupportedMechanismTypes() {
    //             if (mechanisms == null) {
    //                 mechanisms = new ArrayList(1);
    //                 mechanisms.add(Constants.AXIS_SAX);
    //             }
    //             return mechanisms.iterator();
    //         }

    //     }
}
