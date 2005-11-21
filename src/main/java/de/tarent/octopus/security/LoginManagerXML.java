/* $Id: LoginManagerXML.java,v 1.1.1.1 2005/11/21 13:33:38 asteban Exp $
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
package de.tarent.octopus.security;

import java.io.File;
import java.net.PasswordAuthentication;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.HandlerBase;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import de.tarent.octopus.config.TcCommonConfig;
import de.tarent.octopus.request.TcRequest;
import de.tarent.octopus.resource.Resources;
import de.tarent.octopus.server.PersonalConfig;

/** 
 * Implementierung eines LoginManagers, �ber eine XML Datei
 * <br><br>
 * 
 * @author <a href="mailto:mancke@mancke-software.de">Sebastian Mancke</a>, <b>tarent GmbH</b>
 */
public class LoginManagerXML extends AbstractLoginManager {

    private static Logger logger = Logger.getLogger(LoginManagerXML.class.getName());

    public static final String KEY_USER_FILE = "userFile";
    public static final String DEFAULT_USER_FILE_NAME = "user.xml";
    
    protected void doLogin(TcCommonConfig commonConfig, PersonalConfig pConfig, TcRequest tcRequest) 
        throws TcSecurityException {
        
        File userFile = null;
        File base = commonConfig.getModuleConfig(tcRequest.getModule()).getRealPath();
        if (getConfigurationString(KEY_USER_FILE) != null) {
            userFile = new File(base,getConfigurationString(KEY_USER_FILE));
        } else {
            userFile = new File(base,DEFAULT_USER_FILE_NAME);
        }        
               
        String fileUrl = Resources.getInstance().get("LOGINMANAGERXML_URL_USER_FILE", userFile.getAbsolutePath());
        MyContentHandler ch = new MyContentHandler();
        try {
            SAXParserFactory.newInstance().newSAXParser().parse(fileUrl, ch);
        } catch (SAXParseException e) {
            logger.log(
                       Level.WARNING,
                       Resources.getInstance().get(
                                                   "LOGINMANAGERXML_LOG_USER_PARSE_SAX_EXCEPTION",
                                                   new Integer(e.getLineNumber()),
                                                   new Integer(e.getColumnNumber())),
                       e);
            throw new TcSecurityException(Resources.getInstance().get("LOGINMANAGERXML_EXC_USER_PARSE_ERROR", userFile));
        } catch (Exception e) {
            logger.log(Level.WARNING, Resources.getInstance().get("LOGINMANAGERXML_LOG_USER_PARSE_ERROR"), e);
            throw new TcSecurityException(Resources.getInstance().get("LOGINMANAGERXML_EXC_USER_PARSE_ERROR", userFile));
        }

        PasswordAuthentication pwdAuth = tcRequest.getPasswordAuthentication();
        if (pwdAuth == null
            || ! ch.getUsermap().containsKey(pwdAuth.getUserName())
            || ! ch.getUsermap().get(pwdAuth.getUserName()).toString().equals(new String(pwdAuth.getPassword()))) {
            throw new TcSecurityException(TcSecurityException.ERROR_AUTH_ERROR);
        }

        pConfig.setUserGroups((String[])ch.getGroupmap().get(pwdAuth.getUserName()));
        pConfig.userLoggedIn(pwdAuth.getUserName());
    }
    
    protected void doLogout(TcCommonConfig commonConfig, PersonalConfig pConfig, TcRequest tcRequest)
        throws TcSecurityException {
        pConfig.setUserGroups(new String[]{PersonalConfig.GROUP_LOGGED_OUT});
        pConfig.userLoggedOut();
    }


    private class MyContentHandler extends HandlerBase {
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
                if (arg3.getValue("groups") != null)
                    groupmap.put(arg3.getValue("name"), arg3.getValue("groups").split("[:]"));
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