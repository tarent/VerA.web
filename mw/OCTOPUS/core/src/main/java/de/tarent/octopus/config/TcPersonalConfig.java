package de.tarent.octopus.config;

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
import de.tarent.octopus.content.TcContent;
import de.tarent.octopus.request.TcRequest;
import de.tarent.octopus.request.TcTask;
import de.tarent.octopus.request.TcTaskList;
import de.tarent.octopus.security.TcSecurityException;
import de.tarent.octopus.server.Context;
import de.tarent.octopus.server.OctopusContext;
import de.tarent.octopus.server.PersonalConfig;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Default Implementierung einer PersonalConfig ohne Erweiterungen.
 *
 * @author <a href="mailto:mancke@mancke-software.de">Sebastian Mancke</a>, <b>tarent GmbH</b>
 */
public class TcPersonalConfig implements PersonalConfig {
    private static final long serialVersionUID = 5206246870556882418L;

    HashMap sessionData = new HashMap();
    String userLogin;
    Integer userID;
    // Areas als String Keys, Listen mit Gruppen als Values
    HashMap areaGroups = new HashMap();
    // Groups der globalen Area
    String[] globalGroups;
    String userEmail;
    String userLastName;
    String userGivenName;
    String userName;

    public void userLoggedIn(String userName) {
        setUserLogin(userName);
    }

    public void userLoggedOut() {

    }

    /**
     * Speichert einen Wert innrhalb der aktuellen Session
     */
    public void setSessionValue(String key, Object value) {
        sessionData.put(key, value);
    }

    /**
     * Liefert einen Wert innerhalb der aktuellen Session
     */
    public Object getSessionValue(String key) {
        return sessionData.get(key);
    }

    /**
     * Gets the user preference by key
     */
    public String getUserPreference(String key) {
        String value;
        OctopusContext oc = Context.getActive();

        // Looking for a cookie with the name "key" in the cookies
        // given from the HttpServletRequest. These cookies has been put
        // into the TcRequest by the HttpHelper-Class.
        TcRequest tcRequest = oc.getRequestObject();
        Map cookiesMap = (Map) tcRequest.getParam(CookieMap.PREFIX_COOKIE_MAP);
        value = cookiesMap != null ? (String) cookiesMap.get(key) : null;

        // Looking for cookies that has been set within
        // this Octopus call and are stored in the TcContent.
        // It is also possible to set a Cookie-Object directly
        // by setting this Object to the field "cookie" in the content's
        // cookies-Map. Because we do not want the octopus to have the
        // Servlet-API as a dependency the values of this directly assigned
        // Cookies will not be found as a user preference during the request
        // in which this cookie has been set.
        TcContent tcContent = oc.getContentObject();
        Map unstoredCookies = (Map) tcContent.get(CookieMap.PREFIX_COOKIE_MAP);
        if (unstoredCookies != null && unstoredCookies.containsKey(key)) {
            return (String) ((Map) unstoredCookies.get(key)).get(CookieMap.COOKIE_MAP_FIELD_VALUE);
        }

        return value;
    }

    /**
     * Sets the user preference key to the given value
     */
    public void setUserPreference(String key, String value) {
        OctopusContext oc = Context.getActive();
        TcContent tcContent = oc.getContentObject();

        Map cookiesMap;
        if (tcContent.getAsObject(CookieMap.PREFIX_COOKIE_MAP) instanceof Map) {
            cookiesMap = (Map) tcContent.getAsObject(CookieMap.PREFIX_COOKIE_MAP);
        } else {
            cookiesMap = new HashMap();
        }

        // Each field in the cookies-Map is another Map with two fields: the field
        // "value" and the field "cookie". The cookies-Map in the request contains
        // all cookies already stored and both the value and a Cookie-Object are
        // available in the appropriate fields. When cookies are set over the user
        // preferences (this method) only the field "value" will be filled and the
        // Cookie-Object will be created after the dispatch is completed so the
        // octopus-core does not need to import the Servlet-API. Nevertheless you
        // can assign Cookie-Object directly by putting them into the "cookie"-field
        // in the cookies-Map in the octopus-content. The "cookie"-field will be
        // preferred to the "value"-field if both fields are present.
        // For cookie configuration setting see: de.tarent.octopus.content.CookieMap
        Map cookieMap = new HashMap(1);
        cookieMap.put(CookieMap.COOKIE_MAP_FIELD_VALUE, value);

        cookiesMap.put(key, cookieMap);
        tcContent.setField(CookieMap.PREFIX_COOKIE_MAP, cookiesMap);
    }

    /**
     * Liefert die Keys der aktuellen Session
     */
    public Iterator getSessionKeys(String key) {
        return sessionData.keySet().iterator();
    }

    /**
     * Liefert den Login Namen
     */
    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String login) {
        this.userLogin = login;
    }

    /**
     * Liefert die User ID
     */
    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer id) {
        this.userID = id;
    }

    /**
     * Liefert eine Liste aller Gruppen, die einem Benutzer
     * für den Moludweit globalen Bereich zugeordnet sind.
     */
    public String[] getUserGroups() {
        return globalGroups;
    }

    /**
     * Setzt eine Liste der Gruppen, denen ein User im
     * globalen Bereich zugeordnet ist.
     */
    public void setUserGroups(String[] newGroups) {
        globalGroups = newGroups;
        areaGroups.put("", newGroups);
    }

    /**
     * Testet, ob ein User für den Moludweit globalen Bereich
     * in einer Gruppe ist.
     *
     * @param group Bezeichner der Gruppe, auf die getestet wird
     */
    public boolean isUserInGroup(String group) {
        return arrayContains(globalGroups, group);
    }

    protected boolean arrayContains(String[] array, String item) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Liefert eine Liste aller Gruppen, die einem Benutzer
     * für den Bereich area zugeordnet sind.
     * Wenn für die Area selbst keine Gruppen definiert sind,
     * werden die Gruppen der übergeordneten Area zurück gegeben,
     * die ab weitesten spezialisiert ist.
     *
     * @param area Ein Bezeichner eines Zugriffsbereiches
     */
    public String[] getUserGroups(String area) {
        String[] bestMatchingGroups = null;
        int bestMatchingRating = -1;

        // Get best matching area-Group-List
        // Bestimmt die Area, mit dem längsten Bezeichner der Präfix von area ist
        for (Iterator iter = areaGroups.keySet().iterator(); iter.hasNext(); ) {
            String key = (String) iter.next();
            if (area.startsWith(key) && key.length() > bestMatchingRating) {
                bestMatchingRating = key.length();
                bestMatchingGroups = (String[]) areaGroups.get(key);
            }
        }
        return bestMatchingGroups;
    }

    /**
     * Setzt eine Liste der Gruppen, denen ein User im
     * Bereich area zugeordnet ist.
     */
    public void setUserGroups(String[] newGroups, String area) {
        areaGroups.put(area, newGroups);
    }

    /**
     * Testet, ob ein User für den Bereich area
     * in einer Gruppe ist.
     *
     * @param group Bezeichner der Gruppe, auf die getestet wird
     * @param area  Ein Bezeichner eines Zugriffsbereiches
     */
    public boolean isUserInGroup(String group, String area) {
        return arrayContains(getUserGroups(area), group);
    }

    /**
     * Testet, ob der User das gewollte Task ausführen darf.
     * Wenn kein Zugriff gewäht wird muss eine TcSecurityException geworfen werden.
     */
    public void testTaskAccess(TcCommonConfig config, TcRequest tcRequest)
      throws TcSecurityException {
        TcTaskList taskList = config.getTaskList(tcRequest.getModule());
        TcTask task = taskList.getTask(tcRequest.getTask());
        String[] taskGroups = task.getGroups();

        for (int i = 0; i < taskGroups.length; i++) {
            if (isUserInGroup(taskGroups[i])) {
                return;
            }
        }
        throw new TcSecurityException(TcSecurityException.ERROR_WRONG_GROUP_FOR_TASK);
    }

    /**
     * @return Returns the email.
     */
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String mail) {
        this.userEmail = mail;
    }

    /**
     * Liefert den Nachnamen des Users
     */
    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String lastName) {
        this.userLastName = lastName;
    }

    /**
     * Liefert den Vornamen
     */
    public String getUserGivenName() {
        return userGivenName;
    }

    public void setUserGivenName(String givenName) {
        this.userGivenName = givenName;
    }

    public String getUserName() {
        if (userName != null) {
            return userName;
        }
        return getUserGivenName() + " " + getUserLastName();
    }

    /**
     * @param name The name to set.
     */
    public void setUserName(String name) {
        userName = name;
    }

    ///////////// Kompatibilität:

    /**
     * @return Returns the email.
     */
    public String getEmail() {
        return getUserEmail();
    }

    /**
     * @param email The email to set.
     */
    public void setEmail(String email) {
        setUserEmail(email);
    }

    /**
     * @return Returns the nachname.
     */
    public String getNachname() {
        return getUserLastName();
    }

    /**
     * @param nachname The nachname to set.
     */
    public void setNachname(String nachname) {
        setUserLastName(nachname);
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return getUserName();
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        setUserName(name);
    }

    /**
     * @return Returns the loginname.
     */
    public String getLoginname() {
        return getUserLogin();
    }

    /**
     * @param userName The loginname to set.
     */
    public void setLoginname(String userName) {
        setUserLogin(userName);
    }

    /**
     * @return Returns the vorname.
     */
    public String getVorname() {
        return getUserGivenName();
    }

    /**
     * @param vorname The vorname to set.
     */
    public void setVorname(String vorname) {
        setUserGivenName(vorname);
    }

    /**
     * @return Returns the admin.
     */
    public boolean isAdmin() {
        return isUserInGroup(GROUP_ADMINISTRATOR);
    }

    /**
     * @param admin The admin to set.
     */
    public void setAdmin(boolean admin) {
        if (!isAdmin()) {
            String[] newGroups = new String[getUserGroups().length + 1];
            System.arraycopy(getUserGroups(), 0, newGroups, 0, getUserGroups().length + 1);
        }
    }
}
