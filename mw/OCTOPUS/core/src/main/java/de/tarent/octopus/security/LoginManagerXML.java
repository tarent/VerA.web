package de.tarent.octopus.security;

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

import de.tarent.octopus.config.TcCommonConfig;
import de.tarent.octopus.request.TcRequest;
import de.tarent.octopus.resource.Resources;
import de.tarent.octopus.server.PersonalConfig;
import lombok.extern.log4j.Log4j2;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.net.PasswordAuthentication;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementierung eines LoginManagers, über eine XML Datei
 * <br><br>
 *
 * @author <a href="mailto:mancke@mancke-software.de">Sebastian Mancke</a>, <b>tarent GmbH</b>
 */
@Log4j2
public class LoginManagerXML extends AbstractLoginManager {
    public static final String KEY_USER_FILE = "userFile";
    public static final String DEFAULT_USER_FILE_NAME = "user.xml";

    protected void doLogin(TcCommonConfig commonConfig, PersonalConfig pConfig, TcRequest tcRequest)
      throws TcSecurityException {

        File userFile = null;
        File base = commonConfig.getModuleConfig(tcRequest.getModule()).getRealPath();
        if (getConfigurationString(KEY_USER_FILE) != null) {
            userFile = new File(base, getConfigurationString(KEY_USER_FILE));
        } else {
            userFile = new File(base, DEFAULT_USER_FILE_NAME);
        }

        String fileUrl = Resources.getInstance().get("LOGINMANAGERXML_URL_USER_FILE", userFile.getAbsolutePath());
        MyContentHandler ch = new MyContentHandler();
        try {
            SAXParserFactory.newInstance().newSAXParser().parse(fileUrl, ch);
        } catch (SAXParseException e) {
            logger.warn(Resources.getInstance().get(
              "LOGINMANAGERXML_LOG_USER_PARSE_SAX_EXCEPTION",
              new Integer(e.getLineNumber()),
              new Integer(e.getColumnNumber())),
              e);
            throw new TcSecurityException(Resources.getInstance().get("LOGINMANAGERXML_EXC_USER_PARSE_ERROR", userFile));
        } catch (Exception e) {
            logger.warn(Resources.getInstance().get("LOGINMANAGERXML_LOG_USER_PARSE_ERROR"), e);
            throw new TcSecurityException(Resources.getInstance().get("LOGINMANAGERXML_EXC_USER_PARSE_ERROR", userFile));
        }

        PasswordAuthentication pwdAuth = tcRequest.getPasswordAuthentication();
        if (pwdAuth == null
          || !ch.getUsermap().containsKey(pwdAuth.getUserName())
          || !ch.getUsermap().get(pwdAuth.getUserName()).toString().equals(new String(pwdAuth.getPassword()))) {
            throw new TcSecurityException(TcSecurityException.ERROR_AUTH_ERROR);
        }

        pConfig.setUserGroups((String[]) ch.getGroupmap().get(pwdAuth.getUserName()));
        pConfig.userLoggedIn(pwdAuth.getUserName());
    }

    protected void doLogout(TcCommonConfig commonConfig, PersonalConfig pConfig, TcRequest tcRequest)
      throws TcSecurityException {
        pConfig.setUserGroups(new String[] { PersonalConfig.GROUP_LOGGED_OUT });
        pConfig.userLoggedOut();
    }

    private class MyContentHandler extends DefaultHandler {
        private Map usermap = new HashMap();
        private Map groupmap = new HashMap();

        public void setDocumentLocator(Locator arg0) {
        }

        public void startDocument() throws SAXException {
        }

        public void endDocument() throws SAXException {
        }

        public void startPrefixMapping(String arg0, String arg1) throws SAXException {
        }

        public void endPrefixMapping(String arg0) throws SAXException {
        }

        public void startElement(String arg0, String arg1, String arg2, Attributes arg3) throws SAXException {
            if ("user".equals(arg2)) {
                usermap.put(arg3.getValue("name"), arg3.getValue("password"));
                if (arg3.getValue("groups") != null) {
                    groupmap.put(arg3.getValue("name"), arg3.getValue("groups").split("[:]"));
                }
            }
        }

        public void endElement(String arg0, String arg1, String arg2) throws SAXException {
        }

        public void characters(char[] arg0, int arg1, int arg2) throws SAXException {
        }

        public void ignorableWhitespace(char[] arg0, int arg1, int arg2) throws SAXException {
        }

        public void processingInstruction(String arg0, String arg1) throws SAXException {
        }

        public void skippedEntity(String arg0) throws SAXException {
        }

        public Map getUsermap() {
            return usermap;
        }

        public Map getGroupmap() {
            return groupmap;
        }
    }
}
