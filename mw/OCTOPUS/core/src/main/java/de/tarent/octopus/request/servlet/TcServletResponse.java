package de.tarent.octopus.request.servlet;

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

import de.tarent.octopus.content.CookieMap;
import de.tarent.octopus.content.TcContentProzessException;
import de.tarent.octopus.request.TcEnv;
import de.tarent.octopus.request.TcRequest;
import de.tarent.octopus.request.TcResponse;
import de.tarent.octopus.resource.Resources;
import de.tarent.octopus.response.ResponseProcessingException;
import de.tarent.octopus.security.TcSecurityException;
import de.tarent.octopus.soap.TcSOAPEngine;
import de.tarent.octopus.soap.TcSOAPException;
import de.tarent.octopus.util.RootCauseException;
import de.tarent.octopus.util.Xml;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Stellt Funktionen zur Ausgabe an den Client bereit.
 * Diese werden an die HttpServletResponse weiter geleitet.
 *
 * Bevor etwas ausgegeben werden kann, muss der ContentType gesetzt werden.
 *
 * Es mag verwirren, daß die Klasse TcResponse im Package tcRequest ist und nicht im Package tcResponse
 * wo sie dem Namen nach hin gehört. Das macht aber so Sinn, da sie wie auch TcRequestProxy und TcRequest
 * die Schnittstelle zum Client kapselt und somit protokollspezifisches Verhalten hat, vondem in
 * allen anderen Packages völlig abstrahiert wird.
 *
 * @author <a href="mailto:mancke@mancke-software.de">Sebastian Mancke</a>, <b>tarent GmbH</b>
 */
@Log4j2
public class TcServletResponse implements TcResponse {
    public static final long MILLISECONDS_PER_YEAR = 1000l * 60l * 60l * 24l * 365l;

    public static final String WWW_AUTHENTICATE = "wwwAuthenticate";
    protected HttpServletResponse response;
    protected PrintWriter writer;
    private TcSOAPEngine soapEngine;
    private String taskName;
    private String moduleName;
    private int cachingTime = -1;
    private List cachingParam = null;
    // set default error level to 'development'
    private String errorLevel = TcEnv.VALUE_RESPONSE_ERROR_LEVEL_DEVELOPMENT;

    private OutputStream outputStream;

    /**
     * Initialisierung mit den Servletparametern.
     *
     * @param response Die Response des Servletkontainers,
     */
    public TcServletResponse(HttpServletResponse response) throws IOException {
        this.response = response;
        outputStream = response.getOutputStream();
        writer = new PrintWriter(outputStream, true);
    }

    /**
     * Gibt einen Writer für die Ausgabe zurück.
     *
     * Bevor etwas ausgegeben werden kann, muss der ContentType gesetzt werden.
     */
    public PrintWriter getWriter() {
        return writer;
    }

    /**
     * Setzt den Mime-Type für die Ausgabe.
     * Das muss passiert sein, bevor etwas ausgegeben wurde.
     */
    public void setContentType(String contentType) {
        response.setContentType(contentType);
        logger.trace("Habe ContentType: '" + contentType + "' gesetzt.");
    }

    /**
     * Setzt den Status für die Ausgabe.
     * Das muss passiert sein, bevor etwas ausgegeben wurde.
     */
    public void setStatus(int code) {
        response.setStatus(code);
        logger.trace("Habe Status: '" + code + "' gesetzt.");
    }

    /**
     * Setzt einen Header-Parameter.
     * Das muss passiert sein, bevor etwas ausgegeben wurde.
     */
    public void setHeader(String key, String value) {
        response.setHeader(key, value);
        if (logger.isTraceEnabled()) {
            logger.trace("Habe Header-Info '" + key + "' auf '" + value + "' gesetzt.");
        }
    }

    public void setSoapEngine(TcSOAPEngine soapEngine) {
        this.soapEngine = soapEngine;
    }

    public TcSOAPEngine getSoapEngine() {
        return soapEngine;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleName() {
        return moduleName;
    }

    /**
     * Returns the outputStream.
     *
     * @return OutputStream
     */
    public OutputStream getOutputStream() {
        return outputStream;
    }

    /**
     * Sets the outputStream.
     *
     * @param outputStream The outputStream to set
     */
    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    /**
     * Gibt einen String auf den Weiter aus.
     */
    public void print(String responseString) {
        writer.print(responseString);
    }

    /**
     * Gibt einen String + "\n" auf den Weiter aus.
     */
    public void println(String responseString) {
        writer.println(responseString);
    }

    /**
     * Diese Methode sendet gepufferte Ausgaben.
     */
    public void flush() throws IOException {
        response.flushBuffer();
        outputStream.flush();
        writer.flush();
    }

    /**
     * Diese Methode schließt die Ausgaben ab.
     */
    public void close() throws IOException {
        outputStream.close();
        writer.close();
    }

    public void setAuthorisationRequired(String authorisationAction) {
        if (WWW_AUTHENTICATE.equalsIgnoreCase(authorisationAction)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setHeader("WWW-Authenticate", "Basic realm=\"" + getModuleName() + "\"");
        }
    }

    /**
     * Diese Methode gibt eine einfache Fehlermeldung aus. Je nach übergebenen
     * Typ geschieht dies in HTML- oder in SOAP-Form.
     *
     * @param responseType Antwortart, vergleiche {@link HttpHelper}.
     * @param requestID    die ID der Anfrage
     * @param header       eine Überschrift (nur für HTML benutzt)
     * @param e            eine Exception
     */
    public void sendError(int responseType, String requestID, String header, Exception e) {
        setCachingTime(0);
        if (TcRequest.isWebType(responseType)) {
            // HTML Ausgabe
            logger.debug(Resources.getInstance().get("RESPONSE_LOG_HTML_FAULT_BEGIN", requestID));
            printHtmlError(requestID, header, e);
            logger.debug(Resources.getInstance().get("RESPONSE_LOG_HTML_FAULT_FINISH", requestID));
        } else {
            //SOAP Ausgabe
            logger.debug(Resources.getInstance().get("RESPONSE_LOG_FAULT_BEGIN", requestID));
            printSoapException(requestID, e);
            logger.debug(Resources.getInstance().get("RESPONSE_LOG_FAULT_FINISH", requestID));
        }
        // TODO: Type auch auf SOAP testen, ansonsten Default-Weg nehmen
    }

    /**
     * Diese Methode gibt eine einfache HTML-Fehlermeldung zurück.
     *
     * @param requestID die ID der Anfrage
     * @param header    eine Überschrift
     * @param e         eine Exception
     */
    public void printHtmlError(String requestID, String header, Exception e) {
        response.setContentType(HttpHelper.CONTENT_TYPE_HTML);

        if (isErrorLevelRelease()) {
            println(Resources.getInstance().get("RESPONSE_OUT_ERROR_HEAD", header,
              Resources.getInstance().get("ERROR_MESSAGE_GENERAL_ERROR")));
        } else {
            println(Resources.getInstance().get("RESPONSE_OUT_ERROR_HEAD", header, e));
            e.printStackTrace(writer);
            if (e instanceof RootCauseException) {
                Throwable rootCause = ((RootCauseException) e).getRootCause();
                if (rootCause != null) {
                    println(Resources.getInstance().get("RESPONSE_OUT_ERROR_ROOT", rootCause));
                    rootCause.printStackTrace(writer);
                }
            }
        }
        println(Resources.getInstance().get("RESPONSE_OUT_ERROR_TAIL", requestID));
    }

    /**
     * Diese Methode gibt eine Ausnahme als SOAP-Fault zurück.
     *
     * @param requestID die ID der Anfrage
     * @param e         eine Exception
     */
    private void printSoapException(String requestID, Exception e) {
        TcSOAPException soapException;

        if (e instanceof ResponseProcessingException &&
          ((ResponseProcessingException) e).getRootCause() instanceof Exception) {
            e = (Exception) ((ResponseProcessingException) e).getRootCause();
        } else if (e instanceof TcContentProzessException &&
          ((TcContentProzessException) e).getCause() instanceof Exception) {
            e = (Exception) ((TcContentProzessException) e).getCause();
        }

        // TcSOAPException and TcSecurityException may pass in each case
        // other exceptions should only come out, if we are not in release-mode
        if (e instanceof TcSOAPException) {
            soapException = (TcSOAPException) e;
        } else if (e instanceof TcSecurityException) {
            soapException = new TcSOAPException(e);
        } else {
            if (isErrorLevelRelease()) {
                soapException = new TcSOAPException(
                  Resources.getInstance().get("ERROR_MESSAGE_GENERAL_ERROR"));
            } else {
                soapException = new TcSOAPException(e);
            }
        }

        response.setContentType(HttpHelper.CONTENT_TYPE_XML);
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        try {
            soapException.writeTo(outputStream);
        } catch (Exception e2) {
            logger.error(Resources.getInstance().get("RESPONSE_LOG_FAULT_OUT_EXCEPTION", requestID), e2);
            printSimpleSOAPFault(requestID, e2);
        }
    }

    /**
     * Diese Methode gibt eine einfache SOAP-Fehlermeldung zurück.
     *
     * @param requestID die ID der Anfrage
     * @param e         eine Exception
     */
    private void printSimpleSOAPFault(String requestID, Exception e) {
        response.setContentType(HttpHelper.CONTENT_TYPE_XML);

        if (isErrorLevelRelease()) {
            println(Resources.getInstance().get("RESPONSE_OUT_SOAPFAULT_HEAD",
              Xml.escape(Resources.getInstance().get("ERROR_MESSAGE_GENERAL_ERROR"))));
        } else {
            println(Resources.getInstance().get("RESPONSE_OUT_SOAPFAULT_HEAD", Xml.escape(e.toString())));
            e.printStackTrace(writer);
            if (e instanceof RootCauseException) {
                Throwable rootCause = ((RootCauseException) e).getRootCause();
                if (rootCause != null) {
                    println(Resources.getInstance().get("RESPONSE_OUT_SOAPFAULT_ROOT",
                      Xml.escape(rootCause.toString())));
                    rootCause.printStackTrace(writer);
                }
            }
        }
        println(Resources.getInstance().get("RESPONSE_OUT_SOAPFAULT_TAIL", requestID));
    }

    /**
     * Diese Methode gibt je nach Anfragetyp einen Debug-Code-Block aus.
     * TODO: SOAP-spezifische Variante implementieren.
     *
     * @param responseType Antwortart, vergleiche {@link HttpHelper}.
     * @param octRequest   auszugebene Anfrage
     * @param env          Umgebung
     * @deprecated Use logging API for Debug outputs
     */
    public void printDebug(int responseType, TcRequest octRequest, TcEnv env) {
        println(Resources.getInstance().get("RESPONSE_OUT_DEBUG_INFO", octRequest, env));
    }

    public void setCachingTime(int millis) {
        setCachingTime(millis, null);
    }

    public void setCachingTime(int millis, String param) {
        cachingTime = millis;
        cachingParam = param != null ? Arrays.asList(param.split(",")) : null;
        long now = System.currentTimeMillis();
        if (cachingTime <= 0) {
            if (cachingParam == null || cachingParam.indexOf("nocachecontrol") == -1) {
                response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
                response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            }
            response.setDateHeader("Expires", 0);
            response.setDateHeader("Last-Modified", now);
            if (cachingParam == null || cachingParam.indexOf("nopragma") == -1) {
                response.setHeader("Pragma", "no-cache");
            }
        } else if (cachingTime < Integer.MAX_VALUE) {
            if (cachingParam == null || cachingParam.indexOf("nocachecontrol") == -1) {
                response.setHeader("Cache-Control", "max-age=" + (cachingTime / 1000));
            }
            response.setDateHeader("Expires", now + cachingTime);
            response.setDateHeader("Last-Modified", now - now % cachingTime);
            response.setHeader("Pragma", "");
        } else {
            if (cachingParam == null || cachingParam.indexOf("nocachecontrol") == -1) {
                response.setHeader("Cache-Control", "");
            }
            response.setDateHeader("Expires", now + MILLISECONDS_PER_YEAR);
            response.setDateHeader("Last-Modified", 0);
            response.setHeader("Pragma", "");
        }
    }

    /* (non-Javadoc)
     * @see de.tarent.octopus.request.TcResponse#getCachingTime()
     */
    public int getCachingTime() {
        return cachingTime;
    }

    /**
     * Adds a cookie to the response.
     * Default cookie setting can be set in the config.
     * See {@link de.tarent.octopus.content.CookieMap} for detailed settings.
     */
    public void addCookie(String name, String value, Map settings) {
        Cookie cookie = new Cookie(name, value);
        cookie.setSecure(true);
        if (settings.get(CookieMap.CONFIG_MAXAGE) != null) {
            cookie.setMaxAge(Integer.valueOf((String) settings.get(CookieMap.CONFIG_MAXAGE)));
        }
        response.addCookie(cookie);
    }

    /**
     * Adds a cookie to the response.
     *
     * Because the dispatched classes in the octopus-core
     * does not know the Servlet-API and the Cookie-Object
     * this method accepts an Object as parameter and
     * adds this to cookies in case it is a Cookie-Object.
     */
    public void addCookie(Object cookie) {
        if (cookie instanceof Cookie) {
            response.addCookie((Cookie) cookie);
        }
    }

    /**
     * Set output level for errors.
     */
    public void setErrorLevel(String errorLevel) {
        if (errorLevel != null && errorLevel.length() > 0) {
            this.errorLevel = errorLevel;
        }
    }

    public boolean isErrorLevelRelease() {
        return errorLevel.equals(TcEnv.VALUE_RESPONSE_ERROR_LEVEL_RELEASE);
    }
}
