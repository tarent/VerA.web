package de.tarent.octopus.request.servlet;

/*-
 * tarent-octopus, Webservice Data Integrator and Application Server
 * Copyright © 2002–2018 tarent solutions GmbH and its contributors
 * Copyright © 2015, 2017, 2018 mirabilos (t.glaser@tarent.de)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

import de.tarent.octopus.client.OctopusConnection;
import de.tarent.octopus.client.OctopusConnectionFactory;
import de.tarent.octopus.logging.LogFactory;
import de.tarent.octopus.request.*;
import de.tarent.octopus.request.directCall.OctopusDirectCallConnection;
import de.tarent.octopus.request.internal.OctopusInternalStarter;
import de.tarent.octopus.resource.Resources;
import de.tarent.octopus.soap.TcSOAPEngine;
import de.tarent.octopus.soap.TcSOAPException;
import org.apache.commons.logging.Log;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

/**
 * Eintrittspunkt der Anfrage und Starter des Systems,
 * bei Verwendung des Octopus in einem Servlet.
 *
 * 1. Dieses Servlet startet das System indem es in seiner init() Methode
 * die dauerhaft bestehenden Komponenten aufbaut und initialisiert.
 *
 * 2. Es nimmt Anfragen entgegen und leitet diese an den RequestDispatcher weiter.
 * Dabei muss es unterscheiden, ob diese Anfragen normale WEB- oder SOAP-Anfrgen sind.
 * Beide werden dann in eine unabhängige Repräsentaition gebracht.
 *
 * @author <a href="mailto:mancke@mancke-software.de">Sebastian Mancke</a>, <b>tarent GmbH</b>
 */
public class OctopusServlet extends HttpServlet {
	private static final long serialVersionUID = 3257852090755133745L;

	private Octopus octopus = null;
	private TcEnv env;

	private TcSOAPEngine soapEngine;
	private static final Log LOGGER = LogFactory.getLog(OctopusServlet.class);

	// Fehler, der während des Initialisierens auftritt
	private Exception initError = null;

	/**
	 * Name dieser Webapp aus dem ContextPath (==ContextPath ohne '/' am Anfang).
	 * Wird beim ersten Request oder ueber die Servlet-Konfiguration gesetzt, da er nicht über den Servlet-Context
	 * abgerufen werden kann
	 */
	protected String webappContextPathName = null;

	/**
	 * Bit field with request types which are allowed for connections with this octopus servlet
	 */
	protected int allowedRequestTypes = 0x00000000;

	/**
	 * Inititalisiert die Komponenten, die für alle Aufrufe gleich sind.
	 *
	 * <ul></ul>
	 * <li>Env Objekt durch createEnvObject()</li>
	 * <li>Dem RequestDispatcher</li>
	 * <li>Dem Logger.</li>
	 * </ul>
	 */
	public void init() throws ServletException {
		initError = null;
		try {
			env = createEnvObject();

			System.setProperty(TcEnv.KEY_PATHS_ROOT, env.getValueAsString(TcEnv.KEY_PATHS_ROOT));
			LogFactory.initOctopusLogging(env);

			// set the webappContextPathName, if it was configured in the octopus-server config
			webappContextPathName = env.getValueAsString(TcEnv.KEY_WEBAPP_CONTEXT_PATH_NAME);

			String types = env.getValueAsString(TcEnv.KEY_REQUEST_ALLOWED_TYPES);
			if (types == null || types.length() == 0)
				types = TcEnv.VALUE_REQUEST_TYPE_ANY;

			if (types.indexOf(TcEnv.VALUE_REQUEST_TYPE_ANY) != -1)
				allowedRequestTypes = 0xffffffff;
			if (types.indexOf(TcEnv.VALUE_REQUEST_TYPE_WEB) != -1)
				allowedRequestTypes |= TcRequest.REQUEST_TYPE_WEB;
			if (types.indexOf(TcEnv.VALUE_REQUEST_TYPE_SOAP) != -1)
				allowedRequestTypes |= TcRequest.REQUEST_TYPE_SOAP;
			if (types.indexOf(TcEnv.VALUE_REQUEST_TYPE_XMLRPC) != -1)
				allowedRequestTypes |= TcRequest.REQUEST_TYPE_XML_RPC;
			if (types.indexOf(TcEnv.VALUE_REQUEST_TYPE_DIRECTCALL) != -1)
				allowedRequestTypes |= TcRequest.REQUEST_DIRECT_CALL;

			soapEngine = new TcSOAPEngine(env);
			octopus = new Octopus();
			octopus.init(env);
			octopus.init(new ServletModuleLookup(getServletContext(), this, octopus.getCommonConfig()));
			//Octopus für lokale Connections bekannt machen
			OctopusConnectionFactory.getInstance().setInternalOctopusInstance(octopus);

		} catch (Exception e) {
			LOGGER.error(Resources.getInstance().get("REQUESTPROXY_LOG_INIT_EXCEPTION"), e);
			initError = e;
		}

		LOGGER.trace("TcRequextProxy init");
	}

	/**
	 * Deinitialisiert das Servlet.
	 *
	 * @see javax.servlet.GenericServlet#destroy()
	 */
	public void destroy() {
		if (octopus != null)
			try {
				octopus.deInit();
			} catch (Exception e) {
				LOGGER.warn(Resources.getInstance().get("REQUESTPROXY_LOG_CLEANUP_EXCEPTION"), e);
			}
		LogFactory.deInitOctopusLogging();

	}

	/**
	 * Gibt die Anfrage an handleTheRequest weiter
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException {
		handleTheRequest(request, response, false);
	}

	/**
	 * Gibt die Anfrage an handleTheRequest weiter
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException {
		handleTheRequest(request, response, true);
	}

	/**
	 * Startet die Abarbeitung einer Anfrage dadurch, daß diese an den die dispatch() Methode des RequestDispatchers
	 * weiter gegeben wird.
	 *
	 * Dazu werden ein TcRequest Objekt mit createRequestObject(),
	 * ein TcResponse Objekt,
	 * sowie ein TcSession mit der HttpSession initialisiert und an den RequestDispatcher übergeben.
	 *
	 * Wenn der Request den Parameter debug=true enthält und Debugging über den DeploymentDescriptor erlaubt wurde,
	 * werden noch Debugausgaben aus gegeben.
	 */
	public void handleTheRequest(HttpServletRequest request, HttpServletResponse response, boolean post)
	throws IOException {
		if (webappContextPathName == null && request.getContextPath().length() >= 1)
			webappContextPathName = request.getContextPath().substring(1);

		String requestID = TcRequest.createRequestID();
		{
			String reqString = request.getPathInfo();
			if (reqString == null)
				reqString = "";
			String params = request.getQueryString();
			if (params != null)
				reqString += '?' + params;
			LOGGER.info(Resources.getInstance()
			    .get("REQUESTPROXY_LOG_REQUEST_URI", requestID, request.getRemoteAddr(), reqString));
		}

		TcSession tcSession;
		if (!env.getValueAsBoolean(TcEnv.KEY_OMIT_SESSIONS)) {
			HttpSession session = request.getSession(true);

			// Anmeldung erzwingen wen task = login
			boolean sessionWasNew = (session == null) || session.isNew();

			if ("login".equals(request.getParameter("task"))) {
				// TODO: eigene Sessiondaten entnehmen, aber valid lassen
				if (session != null)
					session.invalidate();
				session = request.getSession(true);
			}

			if (sessionWasNew && !post) {
				String queryString = request.getQueryString();
				if (queryString == null) {
					queryString = "";
					Iterator params = request.getParameterMap().entrySet().iterator();
					while (params.hasNext()) {
						Map.Entry param = (Map.Entry)params.next();
						String key = param.getKey().toString();
						Object value = param.getValue();
						if (value instanceof String)
							queryString += key + '=' + value + '&';
						else if (value instanceof String[]) {
							String[] values = (String[])value;
							for (int i = 0; i < values.length; i++)
								queryString += key + '=' + values[i] + '&';
						}
					}
				}

				// This relativ (manual) redirect fix a problem in the catalina
				// implementation of HttpServletResponse.sendRedirect which
				// use a wrong ("http://") absolut path for "https:/Immobilien Schulz/" urls.
				// It also fix a problem with forwarder-servlets which do not
				// include the orignal request url.
				String redirectURI = (String)request.getAttribute("javax.servlet.forward.request_uri");
				if (redirectURI == null)
					redirectURI = request.getRequestURI();
				if (queryString.length() != 0)
					redirectURI = redirectURI + '?' + queryString;
				redirectURI = response.encodeRedirectURL(redirectURI);

				response.setStatus(302);
				response.setHeader("Location", redirectURI);
				LOGGER.info(Resources.getInstance()
				    .get("REQUESTPROXY_LOG_REDIRECT_REQUEST", requestID, redirectURI));
				return;
			}
			tcSession = new TcServletSession(session);
		} else {//Servlet Session Ignorieren und Dummy erzeugen
			tcSession = new TcServletDummySession();
		}

		LOGGER.debug(Resources.getInstance().get("REQUESTPROXY_LOG_REQUEST_PROCESSING_START", requestID));

		TcResponse tcResponse = null;
		int requestType = TcRequest.REQUEST_TYPE_WEB;
		try {
			tcResponse = new TcServletResponse(response);
			tcResponse.setSoapEngine(soapEngine);
			tcResponse.setErrorLevel(env.getValueAsString(TcEnv.KEY_RESPONSE_ERROR_LEVEL));
			LOGGER.trace(
			    Resources.getInstance().get("REQUESTPROXY_LOG_RESPONSE_OBJECT_CREATED", requestID));

			requestType = HttpHelper.discoverRequestType(request);
			LOGGER.info(Resources.getInstance().get("REQUESTPROXY_LOG_REQUEST_TYPE", requestID,
			    TcRequest.getRequestTypeName(requestType)));

			// test if request type is not allowed
			if (!isRequstTypeAllowed(requestType)) {
				LOGGER.error(Resources.getInstance().get("REQUESTPROXY_LOG_ILLEGAL_REQUEST_TYPE",
				    new String[] {
					requestID,
					String.valueOf(requestType),
					String.valueOf(allowedRequestTypes),
				    }), null);
				tcResponse.sendError(requestType,
				    requestID,
				    Resources.getInstance().get("ERROR_MESSAGE_ILLEGAL_REQUEST_TYPE"),
				    new Exception(Resources.getInstance().get("ERROR_MESSAGE_ILLEGAL_REQUEST_TYPE")));
				return;
			}

			if (initError != null) {
				LOGGER.warn(Resources.getInstance().get("REQUESTPROXY_LOG_INITERROR_STOP", requestID),
				    initError);
				tcResponse.sendError(requestType, requestID,
				    Resources.getInstance().get("REQUESTPROXY_OUT_INITERROR_STOP"), initError);
				return;
			}

			LOGGER.trace(Resources.getInstance().get("REQUESTPROXY_LOG_SESSION_OBJECT_CREATED", requestID));

			TcRequest[] octRequests = extractRequests(request, requestType, requestID);
			LOGGER.trace(Resources.getInstance().get("REQUESTPROXY_LOG_REQUEST_OBJECT_CREATED", requestID));

			for (int i = 0; i < octRequests.length; i++) {
				TcRequest octRequest = octRequests[i];
				String askForCookies = octRequest.getParamAsString(TcRequest.PARAM_ASK_FOR_COOKIES);
				octRequest.setAskForCookies(
				    askForCookies == null ? false : askForCookies.equalsIgnoreCase("true"));

				octRequest.setParam(TcRequest.PARAM_ENCODED_URL,
				    response.encodeURL(request.getRequestURL().toString()));
				octRequest.setParam(TcRequest.PARAM_SESSION_ID, tcSession.getId());
				octRequest.setOctopusConnection(createOctopusConnection(octRequest, tcSession));

				octopus.dispatch(octRequest, tcResponse, tcSession);

				// Debug Messages wurden seit Octopus 1.2.0 entfernt: Für Debugging bitte Logging-Api verwenden!
				if (octRequest.getParameterAsBoolean(TcRequest.PARAM_DEBUG))
					tcResponse.sendError(requestType,
					    octRequest.getRequestID(),
					    Resources.getInstance()
						.get("REQUEST_UNKNOWN_REQUEST_PARAM", TcRequest.PARAM_DEBUG,
						    "Direkte Debug Messages wurden seit Octopus 1.2.0 entfernt: Für Debugging bitte Logging-Api verwenden!"),
					    null);
			}
		} catch (Exception e) {
			LOGGER.error(Resources.getInstance().get("REQUESTPROXY_LOG_PROCESSING_EXCEPTION", requestID),
			    e);
			if (tcResponse != null)
				tcResponse.sendError(requestType, requestID,
				    Resources.getInstance().get("REQUESTPROXY_OUT_PROCESSING_EXCEPTION"), e);
		}
		if (tcResponse != null)
			tcResponse.flush();
	}

	protected boolean isRequstTypeAllowed(int requestType) {
		return (allowedRequestTypes & requestType) == requestType;
	}

	/**
	 * Creates an internal OctopusConnection to the same target module as in the request with the same session.
	 */
	protected OctopusConnection createOctopusConnection(TcRequest request, TcSession session) {
		OctopusDirectCallConnection con = new OctopusDirectCallConnection();
		con.setModuleName(request.getModule());
		con.setOctopusStarter(new OctopusInternalStarter(octopus, session));
		return con;
	}

	/**
	 * Diese Methode extrahiert aus einer Http-Anfrage die zugehörigen
	 * Octopus-Anfragen.<br>
	 * TODO: genauer testen und ggfs eine Fehlerrückgabe
	 *
	 * @param request     HTTP-Anfrage
	 * @param requestType Anfragetyp
	 * @param requestID   ID der Gesamtanfrage
	 *
	 * @return Array von Octopus-Anfragen
	 */
	private TcRequest[] extractRequests(HttpServletRequest request, int requestType, String requestID)
	throws TcSOAPException {
		TcRequest[] requests = null;
		if (TcRequest.isWebType(requestType))
			// Normaler WEB-Request
			requests = new TcRequest[] { HttpHelper.readWebRequest(request, requestType, requestID) };
		else if (TcRequest.isSoapType(requestType))
			// SOAP Request
			requests = HttpHelper.readSoapRequests(request, requestType, requestID, soapEngine);
		else if (TcRequest.isXmlRpcType(requestType))
			// XML-RPC Request
			requests = HttpHelper.readXmlRpcRequests(request, requestType, requestID);
		else
			return null;
		HttpHelper.addHttpMetaDataEx(requests, request, requestID, env);
		return requests;
	}

	/**
	 * Erstellt ein TcEnv aus den Konfigurationsparametern Servlets
	 *
	 * Die Informationen aus dem ServletContext werden mit dem prefix 'servletContext.' abgelegt.
	 * Die Parameter aus dem Deployment Descriptor werden so übernommen, wie sie sind und können die
	 * des ServletContextes gegebenenfalls überschreiben.
	 */
	protected TcEnv createEnvObject() {
		// Env Objekt mit Umgebungsvariablen und Einstellungsparametern
		TcEnv env = new TcEnv();

		// Default-Module auf den ContextPath setzen.
		// Dies kann an späterer Stelle überschrieben werden.
		//env.setValue(TcEnv.KEY_DEFAULT_MODULE, getServletContext().getContextPath());

		ServletConfig config = getServletConfig();
		for (Enumeration e = config.getInitParameterNames(); e.hasMoreElements(); ) {
			String key = (String)e.nextElement();
			String val = config.getInitParameter(key);
			if (val != null)
				env.setValue(key, val);
		}

		ServletContext context = getServletContext();
		for (Enumeration e = context.getAttributeNames(); e.hasMoreElements(); ) {
			String name = (String)e.nextElement();
			env.setValue("servletContext", name, "" + context.getAttribute(name));
		}

		env.setValue(TcEnv.KEY_PATHS_ROOT,
		    context.getRealPath("/WEB-INF") + System.getProperty("file.separator"));

		return env;
	}
}
