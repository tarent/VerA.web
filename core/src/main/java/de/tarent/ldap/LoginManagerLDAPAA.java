package de.tarent.ldap;

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

import de.tarent.aa.veraweb.beans.Proxy;
import de.tarent.aa.veraweb.beans.User;
import de.tarent.dblayer.sql.clause.Expr;
import de.tarent.octopus.LoginManagerAA;
import de.tarent.octopus.PersonalConfigAA;
import de.tarent.octopus.beans.Database;
import de.tarent.octopus.beans.veraweb.DatabaseVeraWeb;
import de.tarent.octopus.config.TcCommonConfig;
import de.tarent.octopus.config.TcConfig;
import de.tarent.octopus.content.TcAll;
import de.tarent.octopus.content.TcContent;
import de.tarent.octopus.request.TcEnv;
import de.tarent.octopus.request.TcRequest;
import de.tarent.octopus.security.TcSecurityException;
import de.tarent.octopus.server.OctopusContext;
import de.tarent.octopus.server.PersonalConfig;
import lombok.extern.log4j.Log4j2;

import javax.naming.NamingException;
import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Diese Klasse dient als LoginManager über LDAP im Kontext des Auswärtigen Amts.
 * Insbesondere beachtet wird hier das Anmelden wahlweise als Benutzer oder als
 * Rolle. Ebenfalls beachtet wird das Anmelden mit noch zu erweiternden Namen.
 *
 * @author mikel
 */
@Log4j2
public class LoginManagerLDAPAA extends LoginManagerLDAPGeneric implements LoginManagerAA {
    //
    // Konstanten
    //
    /**
     * Schlüssel des Konfigurationseintrags für den Rollenfilter
     */
    public final static String KEY_ROLE_FILTER = "aarolefilter";

    /**
     * Schlüssel des Konfigurationseintrags für den Superadmin-Login
     */
    public final static String KEY_SYSTEM_ADMIN_LOGIN = "systemlogin";

    /**
     * Schlüssel des Konfigurationseintrags für das Superadmin-Passwort
     */
    public final static String KEY_SYSTEM_ADMIN_PASSWORD = "systempassword";

    //
    // Schnittstelle LoginManagerAA
    //

    /**
     * Diese Methode ändert die persönliche Konfiguration so ab, dass sie
     * in Vertretung der angegebenen Rolle handelt.<br>
     * TODO: Ablauf der Gültigkeit mitaufnehmen und bei Ablauf ungültig werden.
     *
     * @param octx             anzupassender Octopus-Kontext der Sitzung des Vertreters
     * @param proxyDescription Beschreibungs-Bean der Vertretung
     * @throws TcSecurityException Wenn keine authentisierte persönliche Konfiguration
     *                             vorliegt oder schon als Vertreter agiert wird.
     * @see LoginManagerAA#setProxy(OctopusContext, Proxy)
     */
    public void setProxy(OctopusContext octx, Proxy proxyDescription) throws TcSecurityException {
        PersonalConfigAA pConfig = (PersonalConfigAA) octx.personalConfig();
        if (pConfig == null || !pConfig.getGrants().isAuthenticated()) {
            throw new TcSecurityException("Missing personal config for proxying.");
        }
        if (pConfig.getProxy() != null) {
            throw new TcSecurityException("Proxying is not transitive.");
        }
        pConfig.setUserGroups(new String[0]);
        pConfig.setProxy(proxyDescription.proxy);
        pConfig.setRole(proxyDescription.userRole);
        fillInUserGroups(octx.commonConfig(), pConfig, octx.getRequestObject());
    }

    /**
     * Diese Methode liefert eine Auflistung verfügbarer AA-Rollen, aus denen
     * VerA.web-Benutzer ausgewählt werden können.
     *
     * @return Liste verfügbarer AA-Rollen.
     * @throws TcSecurityException
     * @see LoginManagerAA#getAARoles()
     */
    public Set getAARoles() throws TcSecurityException {
        if (ldapManager instanceof LDAPManagerAA) {
            try {
                return ((LDAPManagerAA) ldapManager).getPossibleRoles();
            } catch (NamingException e) {
                throw new TcSecurityException("Rollen konnten nicht bezogen werden", e);
            }
        } else {
            return null;
        }
    }

    //
    // LoginManagerLDAPGeneric überschreibungen
    //

    /**
     * Diese Methode setzt nach einem erfolgreichen Login in der PersonalConfig in
     * eigener Weise Attribute.
     *
     * @param pConfig  PersonalConfig des neu eingelogten Benutzers
     * @param userName Benutzer-ID des neu eingeloggten Benutzers
     * @throws LDAPException
     * @see #doLogin(TcCommonConfig, PersonalConfig, TcRequest)
     * @see LoginManagerLDAPGeneric#initPersonalConfig(de.tarent.octopus.server.PersonalConfig, java.lang.String)
     */
    @Override
    protected void initPersonalConfig(PersonalConfig pConfig, String userName) throws LDAPException {
        if (ldapManager instanceof LDAPManagerAA) {
            Map userdata = ((LDAPManagerAA) ldapManager).getUserData(userName);
            pConfig.setUserEmail(safeFirstToString(userdata.get("mail")));
            pConfig.setUserGivenName(safeFirstToString(userdata.get("givenName")));
            pConfig.setUserID(safeFirstToInteger(userdata.get("uidNumber")));
            pConfig.setUserLastName(safeFirstToString(userdata.get("sn")));
            pConfig.setUserName(safeFirstToString(userdata.get("cn")));
            if (pConfig instanceof PersonalConfigAA) {
                PersonalConfigAA aaConfig = (PersonalConfigAA) pConfig;
                aaConfig.setRole(safeFirstToString(userdata.get("uid")));
            }
        } else {
            super.initPersonalConfig(pConfig, userName);
        }
    }

    /**
     * Diese Methode erzeugt den zu verwendenden LDAPManager.
     *
     * @throws LDAPException
     * @see #doLogin(TcCommonConfig, PersonalConfig, TcRequest)
     * @see LoginManagerLDAPGeneric#initLDAPManager()
     */
    @Override
    protected void initLDAPManager() throws LDAPException {
        Map params = new HashMap();
        params.put(LDAPManager.KEY_BASE_DN, getConfigurationString(TcEnv.KEY_LDAP_BASE_DN));
        params.put(LDAPManager.KEY_RELATIVE, getConfigurationString(TcEnv.KEY_LDAP_RELATIVE));
        params.put(LDAPManager.KEY_RELATIVE_USER, getConfigurationString(TcEnv.KEY_LDAP_RELATIVE));
        params.put(LDAPManagerAA.KEY_ROLE_FILTER, getConfigurationString(KEY_ROLE_FILTER));
        params.put(LDAPManager.KEY_USER_OBJECT_CLASS, getConfigurationString(LoginManagerLDAPGeneric.KEY_USER_OBJECT_CLASS));
        params.put(LDAPManager.KEY_RECURSIVE_LOOKUPS, getConfigurationString(LoginManagerLDAPGeneric.KEY_RECURSIVE_LOOKUPS));
        ldapManager = LDAPManager.login(
          LDAPManagerAA.class,
          getConfigurationString(TcEnv.KEY_LDAP_URL),
          params
        );
    }

    /**
     * Diese Methode Überprüft die Credentials im Request und setzt im Erfolgsfall die
     * entsprechenden Daten in der übergebenen PersonalConfig.<br>
     * Hier wird auf die Funktionalität, die schon in {@link LoginManagerLDAPGeneric}
     * vorliegt, zurückgegriffen, wenn diese nicht zum Erfolg führt, wird aber versucht,
     * über den Inhalt des person-Attributs den Login zu schaffen.<br>
     * Zusätzlich gibt es die Möglichkeit, sich als ein vorgegebener System-Admin ohne
     * LDAP-Authentisierung anzumelden
     *
     * @param commonConfig Konfigurationsdaten des Octopus
     * @param pConfig      persönliche Konfiguration des einzuloggenden Benutzers
     * @param tcRequest    Benutzeranfrage mit Authentisierungsdaten
     * @throws TcSecurityException bei fehlgeschlagener Authorisierung
     * @see de.tarent.ldap.LoginManagerLDAPGeneric#doLogin(de.tarent.octopus.config.TcCommonConfig,
     * de.tarent.octopus.server.PersonalConfig, de.tarent.octopus.request.TcRequest)
     */
    @Override
    protected void doLogin(TcCommonConfig commonConfig, PersonalConfig pConfig, TcRequest tcRequest) throws TcSecurityException {
        PasswordAuthentication origAuth = tcRequest.getPasswordAuthentication();
        // http://www.ietf.org/internet-drafts/draft-ietf-ldapbis-authmeth-18.txt
        // Clients SHOULD disallow an empty password input to a Name/Password Authentication user interface.
        if (origAuth != null && (origAuth.getPassword() == null || origAuth.getPassword().length == 0)) {
            throw new TcSecurityException("Leere Passw\u00f6rter sind nicht zul\u00e4ssig.");
        }
        try {
            /* the password authentication returned by TcRequest contains the fully
             * qualified username. This will break with the current implementation
             * of the LoginManagerLDAPGeneric.
             * Therefore we will simply rewrite the username request parameter in
             * tcRequest.
             *
             * Change Request 2.11 for the next release version 1.2.0
             * requires that users may now use qualified names when logging in
             * (i.e. users may specify their at-domain, e.g. username@domain.tld)
             * instead of just their ldap name.
             *
             * cklein
             * 2008-02-14
             *
             * we will try the login twice, if the first try fails, then we
             * will retry using the username without the appended at domain
             */
            try {
                super.doLogin(commonConfig, pConfig, tcRequest);
            } catch (TcSecurityException se) {
                String username = (String) tcRequest.getParam("username");
                String[] parts = username.split("@");
                tcRequest.setParam("username", parts[0]);
                super.doLogin(commonConfig, pConfig, tcRequest);
            }

            if (pConfig instanceof PersonalConfigAA) {
                PersonalConfigAA aaConfig = (PersonalConfigAA) pConfig;
                // In doLogin wird initPersonalConfig aufgerufen, und dabei sollte die Rolle
                // bereits korrekt auf die erste vom LDAP gelieferte uid gesetzt sein, also
                // auf die uid, die auch von getAARoles zur Bearbeitung geliefert wird.
                if (aaConfig.getRole() == null) {
                    logger.warn("Rolle nicht aus uid gesetzt, Login wird genutzt.");
                    aaConfig.setRole(tcRequest.getPasswordAuthentication().getUserName());
                }
                aaConfig.setRoles(null);
                logger.debug("Login unmittelbar: Rolle = " + aaConfig.getRole() + ", Rollen nicht ermittelt");
                fillInUserGroups(commonConfig, aaConfig, tcRequest);
            }
        } catch (TcSecurityException se) {
            if (origAuth != null && ldapManager instanceof LDAPManagerAA) {
                Collection possibleRoles = null;
                try {
                    possibleRoles = ((LDAPManagerAA) ldapManager).getPossibleRoles(origAuth.getUserName());
                } catch (NamingException e) {
                    throw se;
                }
                Collection authorizedRoles = authorized(commonConfig, pConfig, tcRequest, possibleRoles);
                Iterator itRoles = authorizedRoles.isEmpty() ? possibleRoles.iterator() : authorizedRoles.iterator();
                while (itRoles.hasNext()) {
                    try {
                        PasswordAuthentication newAuth =
                          new PasswordAuthentication(itRoles.next().toString(), origAuth.getPassword());
                        tcRequest.setPasswordAuthentication(newAuth);
                        super.doLogin(commonConfig, pConfig, tcRequest);
                        pConfig.setUserLogin(origAuth.getUserName());
                        if (pConfig instanceof PersonalConfigAA) {
                            PersonalConfigAA aaConfig = (PersonalConfigAA) pConfig;
                            switch (authorizedRoles.size()) {
                            case 0:
                                aaConfig.setRole(null);
                                aaConfig.setRoles(null);
                                logger.debug("Login mittelbar: Login = " + origAuth.getUserName() +
                                  ", keine Rolle, keine Rollen");
                                break;
                            case 1:
                                // In doLogin wird initPersonalConfig aufgerufen, und dabei sollte die Rolle
                                // bereits korrekt auf die erste vom LDAP gelieferte uid gesetzt sein, also
                                // auf die uid, die auch von getAARoles zur Bearbeitung geliefert wird.
                                if (aaConfig.getRole() == null) {
                                    logger.warn("Rolle nicht aus uid gesetzt, Pr\u00fcfrolle wird genutzt.");
                                    aaConfig.setRole(newAuth.getUserName());
                                }
                                aaConfig.setRoles(null);
                                logger.debug("Login mittelbar: Login = " + origAuth.getUserName() +
                                  ", Rolle = " + aaConfig.getRole() + ", Rollen nicht ermittelt");
                                break;
                            default:
                                aaConfig.setRole(null);
                                aaConfig.setRoles(new ArrayList(authorizedRoles));
                                logger.debug("Login mittelbar: Login = " + origAuth.getUserName() +
                                  ", Rolle nicht ermittelt, Rollen = " +
                                  aaConfig.getRoles());
                            }
                            fillInUserGroups(commonConfig, aaConfig, tcRequest);
                        }
                        return;
                    } catch (TcSecurityException se2) {
                    }
                }
            }
            tcRequest.setPasswordAuthentication(origAuth);
            if (pConfig instanceof PersonalConfigAA && isSystemUser(tcRequest.getPasswordAuthentication())) {
                logger.warn("Login als Superadmin");
                PersonalConfigAA aaConfig = (PersonalConfigAA) pConfig;
                pConfig.setUserEmail("root@localhost");
                pConfig.setUserGivenName("System");
                pConfig.setUserID(new Integer(-1));
                pConfig.setUserLastName("Manager");
                pConfig.setUserName("System Manager");
                pConfig.setUserGroups(new String[] { PersonalConfigAA.GROUP_SYSTEM_USER });
                /*
                pConfig.setUserGroups(new String[]{PersonalConfigAA.GROUP_ADMIN,
                        PersonalConfigAA.GROUP_PARTIAL_ADMIN, PersonalConfigAA.GROUP_WRITE,
                        PersonalConfigAA.GROUP_READ_REMARKS, PersonalConfigAA.GROUP_EXPORT,
                        PersonalConfigAA.GROUP_READ_STANDARD, PersonalConfig.GROUP_USER,
                        PersonalConfigAA.GROUP_IN_PERSON});
                */
                pConfig.userLoggedIn(tcRequest.getPasswordAuthentication().getUserName());
                aaConfig.setRole(tcRequest.getPasswordAuthentication().getUserName());
                aaConfig.setRoles(null);
                aaConfig.setVerawebId(new Integer(-1));
                aaConfig.setOrgUnitId(new Integer(-1));
                return;
            }
            throw se;
        }
    }

    /**
     * Diese Methode führt ein Ausloggen des Benutzers durch. Insbesondere werden
     * entsprechende Markierungen in seiner persönlichen Konfiguration gesetzt.<br>
     * Hier werden die speziellen Rollen- und Stellvertreterfelder geleert.
     *
     * @param commonConfig Konfigurationsdaten des Octopus
     * @param pConfig      persönliche Konfiguration des auszuloggenden Benutzers
     * @param tcRequest    Benutzeranfrage
     * @see de.tarent.ldap.LoginManagerLDAPGeneric#doLogout(de.tarent.octopus.config.TcCommonConfig,
     * de.tarent.octopus.server.PersonalConfig, de.tarent.octopus.request.TcRequest)
     */
    @Override
    protected void doLogout(TcCommonConfig commonConfig, PersonalConfig pConfig, TcRequest tcRequest) {
        if (pConfig instanceof PersonalConfigAA) {
            PersonalConfigAA aaConfig = (PersonalConfigAA) pConfig;
            aaConfig.setRole(null);
            aaConfig.setRoles(null);
            aaConfig.setProxy(null);
            aaConfig.setOrgUnitId(null);
            aaConfig.setVerawebId(null);
        }
        super.doLogout(commonConfig, pConfig, tcRequest);
    }

    //
    // Hilfsmethoden
    //

    boolean isSystemUser(PasswordAuthentication pwdAuth) {
        String systemLogin = getConfigurationString(KEY_SYSTEM_ADMIN_LOGIN);
        String systemPassword = getConfigurationString(KEY_SYSTEM_ADMIN_PASSWORD);
        return pwdAuth != null &&
          systemLogin != null && systemLogin.equals(pwdAuth.getUserName()) &&
          pwdAuth.getPassword() != null && new String(pwdAuth.getPassword()).equals(systemPassword);
    }

    /**
     * Diese Methode filtert aus einer Liste von AA-Rollen diejenigen heraus,
     * die im VerA.web-Kontext autorisiert sind.
     *
     * @param commonConfig aktuelle allgemeine Konfiguration
     * @param pConfig      aktuelle persönliche Konfiguration
     * @param tcRequest    aktueller Request
     * @param roles        Sammlung von AA-Rollen
     * @return Sammlung der AA-Rollen aus <code>roles</code>, die VerA.web-autorisiert sind.
     */
    Collection authorized(TcCommonConfig commonConfig, PersonalConfig pConfig, TcRequest tcRequest, Collection roles) {
        if (roles == null || roles.size() == 0) {
            return roles;
        }
        Collection result = new ArrayList(roles.size());
        OctopusContext cntx = new TcAll(tcRequest, new TcContent(),
          new TcConfig(commonConfig, pConfig, tcRequest.getModule()));
        Database database = new DatabaseVeraWeb(cntx);
        Iterator itRoles = roles.iterator();
        while (itRoles.hasNext()) {
            String role = safeFirstToString(itRoles.next());
            try {
                User user = (User) database.getBean("User",
                  database.getSelect("User").where(Expr.equal("username", role)));
                if (user != null) {
                    result.add(role);
                }
            } catch (Exception e) {
                logger.warn("Fehler beim Einlesen von Benutzerdaten zu " + role, e);
            }
        }
        logger.debug("Autorisierte Rollen: " + result);
        return result;
    }

    /**
     * Diese Methode liest zu der AA-Rolle der Anmeldung eines Benutzers die
     * zugehörigen Octopus-Benutzergruppen aus und setzt diese in der persönlichen
     * Konfiguration.
     *
     * @param commonConfig aktuelle allgemeine Konfiguration
     * @param pConfig      aktuelle persönliche Konfiguration, der Octopus-Benutzergruppen
     *                     zugeordnet werden.
     * @param tcRequest    aktueller Request
     */
    void fillInUserGroups(TcCommonConfig commonConfig, PersonalConfigAA pConfig, TcRequest tcRequest) {
        pConfig.setVerawebId(null);
        pConfig.setOrgUnitId(null);
        if (pConfig.getRole() == null || pConfig.getRole().length() == 0) {
            List aaRoles = pConfig.getRoles();
            String group = (aaRoles != null && aaRoles.size() > 0) ?
              PersonalConfigAA.GROUP_UNCLEAR_ROLE : PersonalConfigAA.GROUP_UNAUTHORIZED;
            pConfig.setUserGroups(new String[] { group });
        } else {
            OctopusContext cntx = new TcAll(tcRequest, new TcContent(),
              new TcConfig(commonConfig, pConfig, tcRequest.getModule()));
            Database database = new DatabaseVeraWeb(cntx);
            List groups = new ArrayList();
            try {
                User user = (User) database.getBean("User",
                  database.getSelect("User").where(Expr.equal("username", pConfig.getRole())));
                if (user != null) {
                    pConfig.setVerawebId(user.id);
                    pConfig.setOrgUnitId(user.orgunit != null ? user.orgunit : new Integer(-1));
                    switch (user.role.intValue()) {
                    case User.ROLE_READ_RESTRICTED:
                        groups.add(PersonalConfig.GROUP_USER);
                        groups.add(PersonalConfigAA.GROUP_READ_STANDARD);
                        groups.add(PersonalConfigAA.GROUP_EXPORT);
                        break;
                    case User.ROLE_READ_FULL:
                        groups.add(PersonalConfig.GROUP_USER);
                        groups.add(PersonalConfigAA.GROUP_READ_STANDARD);
                        groups.add(PersonalConfigAA.GROUP_READ_REMARKS);
                        groups.add(PersonalConfigAA.GROUP_EXPORT);
                        break;
                    case User.ROLE_READ_WRITE_RESTRICTED:
                        groups.add(PersonalConfig.GROUP_USER);
                        groups.add(PersonalConfigAA.GROUP_READ_STANDARD);
                        groups.add(PersonalConfigAA.GROUP_WRITE);
                        groups.add(PersonalConfigAA.GROUP_EXPORT);
                        break;
                    case User.ROLE_READ_WRITE_FULL:
                        groups.add(PersonalConfig.GROUP_USER);
                        groups.add(PersonalConfigAA.GROUP_READ_STANDARD);
                        groups.add(PersonalConfigAA.GROUP_READ_REMARKS);
                        groups.add(PersonalConfigAA.GROUP_WRITE);
                        groups.add(PersonalConfigAA.GROUP_EXPORT);
                        break;
                    case User.ROLE_PARTIAL_ADMIN:
                        groups.add(PersonalConfig.GROUP_USER);
                        groups.add(PersonalConfigAA.GROUP_READ_STANDARD);
                        groups.add(PersonalConfigAA.GROUP_READ_REMARKS);
                        groups.add(PersonalConfigAA.GROUP_WRITE);
                        groups.add(PersonalConfigAA.GROUP_EXPORT);
                        groups.add(PersonalConfigAA.GROUP_PARTIAL_ADMIN);
                        break;
                    case User.ROLE_GLOBAL_ADMIN:
                        groups.add(PersonalConfig.GROUP_USER);
                        groups.add(PersonalConfigAA.GROUP_READ_STANDARD);
                        groups.add(PersonalConfigAA.GROUP_READ_REMARKS);
                        groups.add(PersonalConfigAA.GROUP_WRITE);
                        groups.add(PersonalConfigAA.GROUP_EXPORT);
                        groups.add(PersonalConfigAA.GROUP_PARTIAL_ADMIN);
                        groups.add(PersonalConfigAA.GROUP_ADMIN);
                        break;
                    }
                    groups.add(pConfig.getProxy() != null ? PersonalConfigAA.GROUP_BY_PROXY : PersonalConfigAA.GROUP_IN_PERSON);
                }
            } catch (Exception e) {
                logger.warn("Fehler beim Einlesen von Benutzerdaten zu " + pConfig.getRole(), e);
            }
            if (groups.size() == 0) {
                groups.add(PersonalConfigAA.GROUP_UNAUTHORIZED);
            }
            pConfig.setUserGroups((String[]) groups.toArray(new String[groups.size()]));
            logger.debug("Ermittelte Benutzergruppen: " + groups);
        }
    }

    /**
     * Diese Methode führt ein toString aus, sichert dies aber durch einen <code>null</code>-Test
     * vorher ab. Sollte das Objekt eine Liste sein, so wird toString des ersten enthaltenen Objekts
     * ausgeführt.
     *
     * @param o Objekt
     * @return {@link String}-Darstellung des Objekts, gegebenenfalls des ersten enthaltenen Objekts
     */
    final static String safeFirstToString(Object o) {
        if (o instanceof List) {
            List l = (List) o;
            o = l.isEmpty() ? null : l.get(0);
        }
        return o == null ? null : o.toString();
    }

    /**
     * Diese Methode versucht, das übergebene Objekt als Integer zu interpretieren, wobei
     * dezimale, oktale und hexadezimale Darstellungen akzeptiert werden. Sollte das Objekt
     * eine Liste sein, so wird versucht, das erste enthaltene Objekt als Integer zu interpretieren.
     *
     * @param o Objekt
     * @return {@link Integer}-Darstellung des Objekts, gegebenenfalls des ersten enthaltenen Objekts
     */
    final static Integer safeFirstToInteger(Object o) {
        if (o instanceof List) {
            List l = (List) o;
            o = l.isEmpty() ? null : l.get(0);
        }
        if (o == null) {
            return null;
        }
        if (o instanceof Integer) {
            return (Integer) o;
        }
        try {
            return Integer.decode(o.toString());
        } catch (NumberFormatException nfe) {
            return null;
        }
    }
}
