package de.tarent.ldap;

/*-
 * Veranstaltungsmanagement VerA.web (platform-independent
 * webservice-based event management) is Copyright
 *  © 2014, 2015, 2016, 2017 Атанас Александров <a.alexandrov@tarent.de>
 *  © 2018 Атанас Александров <sirakov@gmail.com>
 *  © 2013 Иванка Александрова <i.alexandrova@tarent.de>
 *  © 2013 Patrick Apel <p.apel@tarent.de>
 *  © 2016 Eugen Auschew <e.auschew@tarent.de>
 *  © 2013 Andrei Boulgakov <a.boulgakov@tarent.de>
 *  © 2013 Valentin But <v.but@tarent.de>
 *  © 2016 Lukas Degener <l.degener@tarent.de>
 *  © 2017 Axel Dirla <a.dirla@tarent.de>
 *  © 2015 Julian Drawe <j.drawe@tarent.de>
 *  © 2014 Dominik George <d.george@tarent.de>
 *  © 2013 Sascha Girrulat <s.girrulat@tarent.de>
 *  © 2008 David Goemans <d.goemans@tarent.de>
 *  © 2015 Viktor Hamm <v.hamm@tarent.de>
 *  © 2013 Katja Hapke <k.hapke@tarent.de>
 *  © 2013 Hendrik Helwich <h.helwich@tarent.de>
 *  © 2007 jan <jan@evolvis.org>
 *  © 2005, 2006, 2007, 2008 Christoph Jerolimov <jerolimov@gmx.de>
 *  © 2008, 2009, 2010 Carsten Klein <c.klein@tarent.de>
 *  © 2014 Martin Ley <m.ley@tarent.de>
 *  © 2014, 2015 Max Marche <m.marche@tarent.de>
 *  © 2013, 2014, 2015, 2016, 2017, 2018 mirabilos <t.glaser@tarent.de>
 *  © 2016 Cristian Molina <c.molina@tarent.de>
 *  © 2017 Michael Nienhaus <m.nienhaus@tarent.de>
 *  © 2013 Claudia Nuessle <c.nuessle@tarent.de>
 *  © 2014, 2015 Jon Nunez Alvarez <j.nunez-alvarez@tarent.de>
 *  © 2016 Jens Oberender <j.oberender@tarent.de>
 *  © 2016, 2017 Miluška Pech <m.pech@tarent.de>
 *  © 2009 Martin Pelzer <m.pelzer@tarent.de>
 *  © 2013 Marc Radel <m.radel@tarent.de>
 *  © 2013 Sebastian Reimers <s.reimers@tarent.de>
 *  © 2015 Charbel Saliba <c.saliba@tarent.de>
 *  © 2008, 2009, 2010 Thomas Schmitz <t.schmitz@tarent.de>
 *  © 2013 Volker Schmitz <v.schmitz@tarent.de>
 *  © 2014 Sven Schumann <s.schumann@tarent.de>
 *  © 2014 Sevilay Temiz <s.temiz@tarent.de>
 *  © 2013 Kevin Viola Schmitz <k.schmitz@tarent.de>
 *  © 2015 Stefan Walenda <s.walenda@tarent.de>
 *  © 2015, 2016, 2017 Max Weierstall <m.weierstall@tarent.de>
 *  © 2013 Rebecca Weinz <r.weinz@tarent.de>
 *  © 2015, 2016 Stefan Weiz <s.weiz@tarent.de>
 *  © 2015, 2016 Tim Zimmer <t.zimmer@tarent.de>
 * and older code, Copyright © 2004–2008 ⮡ tarent GmbH and contributors.
 * Licensor is tarent solutions GmbH, http://www.tarent.de/
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, see: http://www.gnu.org/licenses/
 */

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Diese Klasse
 *
 * @author mikel
 */
public class LDAPManagerAA extends LDAPManager {
    //
    // Konstanten
    //
    /** LDAP-Objektklasse fuer AA-Rollen */
    /**
     * Parameter-Schluessel fuer den AA-Rollen-Filter
     */
    public final static String KEY_ROLE_FILTER = "role-filter";

    //
    // Konstruktor
    //

    /**
     * Dieser Konstruktor reicht die übergebenen Parameter durch und setzt die
     * Vorgabe-Objektklassen.
     *
     * @param lctx   initialer LDAP-Kontext, auf dem dieser LDAP-Manager arbeitet
     * @param params LDAP-Manager-Parameter, vergleiche
     *               {@link LDAPManager#LDAPManager(InitialLdapContext, Map)}
     */
    public LDAPManagerAA(InitialLdapContext lctx, Map params) {
        super(lctx, params);
        String roleFilter = (String) params.get(KEY_ROLE_FILTER);
        // setup a valid filter template, by default we only search for the uid
        String objFilter = "(uid={0})";
        if (this.defaultUserObjectClass != null && this.defaultUserObjectClass.length() > 0) {
            // if set, we extend the default search filter template by the defined user object class
            objFilter = "(&(objectclass=" + this.defaultUserObjectClass + ")" + objFilter + ")";
        }
        filterTemplate = new MessageFormat(roleFilter != null ? roleFilter : objFilter);
    }

    //
    // Öffentliche AA-spezifische Methoden
    //

    /**
     * Diese Methode erzeugt zu einem Benutzer eine {@link Map} seiner Attribute und
     * Attributswerte. Wenn ein Attribut mehrere Werte hat, so wird in der {@link Map}
     * der Attributname auf eine {@link java.util.List} der Werte abgebildet.
     *
     * @param userName Name des Benutzers
     * @return {@link Map} der Attribute des Benutzers
     * @throws LDAPException
     */
    public Map getUserData(String userName) throws LDAPException {
        try {
            String dn = fullUserDN(userName);
            return LDAPUtil.toMap(lctx.getAttributes(dn));
        } catch (NamingException e) {
            throw new LDAPException("Es ist ein Fehler beim Holen der Userdaten aufgetreten!", e);
        }
    }

    /**
     * Diese Methode ermittelt alle verfügbaren Rollen.
     *
     * @return Sammlung verfügbarer Rollen
     * @throws NamingException
     */
    public Set getPossibleRoles() throws NamingException {
        SearchControls cons = new SearchControls();
        this.initializeSearchControls(cons);
        Set roleUids = getPossibleRoles("(objectclass=" + this.defaultUserObjectClass + ")", cons);
        logger.fine("Alle verfügbaren Rollen: " + roleUids);
        return roleUids;
    }

    /**
     * Diese Methode ermittelt die verfügbaren Rollen zu einem Vorname.Nachname-Login.
     * Dies geschieht über eine Suche in den Rollen, die über den Rollenfilter (Parameter
     * mit Schlüssel {@link #KEY_ROLE_FILTER}) gefiltert wird. Dieser Rollenfilter ist
     * ein LDAP-Suchfilter, in dem aber {0} als Variable erlaubt ist, in die der Login
     * eingetragen wird.<br>
     * Zum Beispiel sucht folgender Filter die AARole-Knoten, in denen das login in
     * sinnvoller Weise im person-Attribut steht:<br>
     * (&amp;(|(person=uid={0}&#64;auswaertiges-amt.de,ou=Personen,dc=aa)(person=uid={0}.auswaertiges-amt.de,ou=Personen,dc=aa)
     * (person=uid={0},
     * ou=Personen,dc=aa))(objectclass=AARole))
     *
     * @param login Login, zu dem mögliche Rollen gesucht werden sollen
     * @return Sammlung möglicher Rollen zu dem Login
     * @throws NamingException
     */
    public Set getPossibleRoles(String login) throws NamingException {
        SearchControls cons = new SearchControls();
        this.initializeSearchControls(cons);
        Set roleUids = getPossibleRoles(filterTemplate.format(new Object[] { login }), cons);
        logger.fine("Rollen für " + login + ": " + roleUids);
        return roleUids;
    }

    /**
     * Diese Methode ermittelt die verfügbaren Rollen zu einem Filter.
     *
     * @param filter JNDI-Suchfilter
     * @param cons   SearchControls
     * @return Sammlung möglicher Rollen zu dem Filter
     * @throws NamingException
     */
    public Set getPossibleRoles(String filter, SearchControls cons) throws NamingException {
        Set roleUids = new HashSet();
        try {
            this.recreateInitialContext();
            NamingEnumeration ergebnis = lctx.search(relativeUser.substring(1) + baseDN, filter, cons);
            while (ergebnis.hasMore()) {
                Attributes result = ((SearchResult) ergebnis.nextElement()).getAttributes();
                // TODO: hier wird einer der uid-Einträge genommen, nicht alle. Nach Anpassung andere getPossibleRoles
                // entsprechend anpassen
                roleUids.add(result.get("uid").get());
            }
            return roleUids;
        } catch (LDAPException e) {
            throw new NamingException("Die Verbindung zum LDAP Server wurde geschlossen.");
        }
    }

    //
    // Members
    //

    /**
     * eigener statischer Logger
     */
    private static Logger logger = Logger.getLogger(LDAPManagerAA.class.getName());
}
