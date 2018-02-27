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
import de.tarent.octopus.security.TcSecurityException;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import java.lang.reflect.Constructor;
import java.math.BigInteger;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * für Zugriff auf ein LDAP-Verzeichnis
 *
 * @author philipp
 */
public class LDAPManager {
    //
    // Konstanten
    //
    public final static String KEY_BASE_DN = "base-dn";
    public final static String KEY_RELATIVE = "relative";
    public final static String KEY_RELATIVE_USER = "relative-user";

    /**
     * Parameter-Schluessel fuer die Objekt Klasse, welche von den in
     * LDAP definierten Benutzern implementiert wird.
     */
    public final static String KEY_USER_OBJECT_CLASS = "user-object-class";
    protected String defaultUserObjectClass = null;

    /**
     * Schluessel des Konfigurationseintrags fuer rekursive LDAP lookups.
     */
    public final static String KEY_RECURSIVE_LOOKUPS = "recursive-lookups";

    //
    // Variablen
    //
	// Der nötige LDAPContext
	protected InitialLdapContext	lctx;
	// Base DN für's LDAP
	protected String			baseDN;
	// ein relativer DN, falls alles, was behandelt wird relativ zum Base DN
	// gesehen werden soll
    protected final Map params;
	protected String			relative;
	protected String			relativeUser;
	private static Logger		logger	= Logger.getLogger(LDAPManager.class.getName());
    private String[]            defaultObjectClasses = null;

    //
    // Factory-Methoden
    //
    /**
     * Anonymer Login
     *
     * @throws LDAPException
     *             wenn etwas schiefläuft
     */
    public static LDAPManager login(Class managerClass, String ldapurl, Map params) throws LDAPException {
        Hashtable env = createEnvironment(ldapurl); //Hashtable mit Zugriffsdaten
        env.put(Context.SECURITY_AUTHENTICATION, "none"); //$NON-NLS-1$
        return instantiate(managerClass, env, params);
    }

	/**
	 * Methode, die einen gegebenen Benutzer einloggt.
	 *
	 * @param username Benutzername als komplette DN des Users
	 * @param passwort Passwort
	 * @param authType Authentifizierungstyp gegenüber dem LDAP (im Moment nur
	 *                 "simple" ausprobiert &amp; supported)
	 * @throws LDAPException wenn etwas schiefläuft
	 */
	public static LDAPManager login(Class managerClass, String ldapurl, Map params, String username, String passwort, String authType) throws LDAPException {
        Hashtable env = createEnvironment(ldapurl); //Hashtable mit Zugriffsdaten
        env.put(Context.SECURITY_AUTHENTICATION, authType);
        env.put(Context.SECURITY_PRINCIPAL, username);
        env.put(Context.SECURITY_CREDENTIALS, passwort);
        return instantiate(managerClass, env, params);
    }

    /**
     * Diese statische Methode erzeugt eine neue LDAP-Manager-Instanz.
     *
     * @param managerClass Klasse des zu erzeugenden LDAPManagers.
     * @param env Umgebungsparameter für den zu erzeugenden LdapContext.
     * @param params Parameter für den neu zu instantiierenden LDAPManager, zumindest
     *  für {@link #KEY_BASE_DN}, {@link #KEY_RELATIVE} und {@link #KEY_RELATIVE_USER}
     *  müssen Werte vorliegen.
     * @return LDAPManager
     */
    public static LDAPManager instantiate(Class managerClass, Hashtable env, Map params) throws LDAPException
    {
    	LDAPManager result =  null;
    	InitialLdapContext lctx = null;

        try
        {
            lctx = new InitialLdapContext( env, null );
        }
        catch ( CommunicationException e )
        {
            throw new LDAPException( Messages.getString( "LDAPManager.ldap_not_reachable_01" ), e ); //$NON-NLS-1$
        }
        catch ( NamingException e )
        {
            throw new LDAPException( Messages.getString( "LDAPManager.commication_error_01" ), e ); //$NON-NLS-1$
        }

        try
        {
            Constructor constructor = managerClass.getConstructor(InitialLdapContext.class, Map.class);
        	result = ( LDAPManager ) constructor.newInstance(lctx, params);
        	LDAPManager.ldapEnvironment = env;
        }
    	catch ( Exception e )
    	{
            throw new LDAPException( "Kann LDAPManager nicht instantiieren, der spezifizierte Konstruktor ist nicht verf\u00fcgbar." );
    	}
    	return result;
    }

    private static Hashtable ldapEnvironment = null;
    protected void recreateInitialContext() throws LDAPException
    {
        try
        {
            lctx = new InitialLdapContext( LDAPManager.ldapEnvironment, null );
        }
        catch ( CommunicationException e )
        {
            throw new LDAPException( Messages.getString( "LDAPManager.ldap_not_reachable_01" ), e ); //$NON-NLS-1$
        }
        catch ( NamingException e )
        {
            throw new LDAPException( Messages.getString( "LDAPManager.commication_error_01" ), e ); //$NON-NLS-1$
        }
    }

    //
    // Konstruktoren
    //
	/**
	 * Dieser Konstruktor fischt die Werte für {@link #KEY_BASE_DN}, {@link #KEY_RELATIVE}
     * und {@link #KEY_RELATIVE_USER} heraus und legt sie neben den übergebenen Parametern
     * explizit ab.
	 *
     * @param lctx initialer LDAP-Kontext, auf dem dieser LDAP-Manager arbeitet
     * @param params Map, die zumindest für {@link #KEY_BASE_DN}, {@link #KEY_RELATIVE}
     *  und {@link #KEY_RELATIVE_USER} Werte enthält.
	 */
	public LDAPManager(InitialLdapContext lctx, Map params) {
        this.lctx = lctx;
        this.params = params;
		this.baseDN = (String) params.get(KEY_BASE_DN);
		this.relative = surroundWithCommas((String) params.get(KEY_RELATIVE));
		this.relativeUser = surroundWithCommas((String) params.get(KEY_RELATIVE_USER));
        this.defaultUserObjectClass = ( String ) params.get( LDAPManager.KEY_USER_OBJECT_CLASS );
        setDefaultObjectClasses( new String[] { this.defaultUserObjectClass } );
	}

    //
    // Getter und Setter
    //
    /**
     * Diese Methode liefert die aktuellen Vorgabe-Objektklassen für Benutzer.
     *
     * @return Vorgabe-Objektklassen für Benutzer
     * @see #fullUserDN(String)
     */
    protected String[] getDefaultObjectClasses() {
        return defaultObjectClasses;
    }

    /**
     * Diese Methode setzt die aktuellen Vorgabe-Objektklassen für Benutzer.
     *
     * @param newDefault neue Vorgabe-Objektklassen für Benutzer
     * @see #fullUserDN(String)
     */
    protected void setDefaultObjectClasses(String[] newDefault) {
        defaultObjectClasses = newDefault;
    }

    /**
	 * Methode, die einen gegebenen Benutzer unter der aktuellen BaseDN einloggt.
	 *
	 * @param username Benutzername nur als Benutzername
	 * @param passwort Passwort
	 * @param authType Authentifizierungstyp gegenüber dem LDAP (im Moment nur simple ausprobiert &amp; supported)
	 * @throws LDAPException wenn etwas schiefläuft
	 */
	public LDAPManager login(String username, String passwort, String authType) throws LDAPException {
        try {
            String security_principal = fullUserDN(username, null);
            Object ldapUrl = lctx.getEnvironment().get(Context.PROVIDER_URL);
            if (ldapUrl == null)
                throw new LDAPException("Konnte LDAP-Url nicht ermitteln");
            return login(getClass(), ldapUrl.toString(), params, security_principal, passwort, authType);
        } catch(NamingException e) {
            throw new LDAPException(Messages.getString("LDAPManager.commication_error_01"), e); //$NON-NLS-1$
        }
    }

    /**
	 * @param ou
	 *            ou in der Form "ou=blahbla"
	 * @throws LDAPException
	 */
	public void createOU(String ou) throws LDAPException {
		//angegebene ou anlegen
		try {
			BasicAttributes attr = new BasicAttributes();
			BasicAttribute objectclass = new BasicAttribute("objectclass", "top"); //$NON-NLS-1$ //$NON-NLS-2$
			objectclass.add("organizationalUnit"); //$NON-NLS-1$
			attr.put(objectclass);
			lctx.createSubcontext(ou + relative + baseDN, attr);
		} catch (Exception e) {
			throw new LDAPException(Messages.getString("LDAPManager.Konnte_OU_nicht_anlegen_01") + e.getMessage(), e); //$NON-NLS-1$
		}
	}

	/**
	 * Legt eine OU an
	 *
	 * @param ou
	 *            ou, die angelegt werden soll
	 * @param user
	 *            uid's der User die Zugriff auf die OU erhalten sollen
	 * @throws LDAPException
	 *             wenn etwas schiefläuft
	 */
	public void createOU(String ou, List user) throws LDAPException {
		//angegebene ou anlegen
		try {
			BasicAttributes attr = new BasicAttributes();
			BasicAttribute objectclass = new BasicAttribute("objectclass", "top"); //$NON-NLS-1$ //$NON-NLS-2$
			//objectclass.add("organizationalUnit");
			objectclass.add("groupOfNames"); //$NON-NLS-1$
			attr.put(objectclass);
			attr.put("cn", ou); //$NON-NLS-1$
			attr.put("ou", ou); //$NON-NLS-1$
			BasicAttribute users = new BasicAttribute("member"); //$NON-NLS-1$
			Iterator it = user.iterator();
			while (it.hasNext()) {
				String adduser = (String) it.next();
				try {
					String adduser2 = fullUserDN(adduser);
					users.add(adduser2);
				} catch (LDAPException le) {
					logger.log(Level.WARNING, Messages.getString("LDAPManager.76") + adduser
							+ Messages.getString("LDAPManager.77")); //$NON-NLS-1$
				}
			}
			attr.put(users);
			lctx.createSubcontext("ou=" + ou + relative + baseDN, attr); //$NON-NLS-1$
		} catch (NamingException e) {
			throw new LDAPException(Messages.getString("LDAPManager.Konnte_OU_nicht_anlegen_01")
					+ Messages.getString("LDAPManager.78") + ou + relative + baseDN
					+ Messages.getString("LDAPManager.79") + e.getMessage(), e); //$NON-NLS-1$
		}
	}

	/**
	 * Methode die testet, ob User name bestimmtes Attribut hat
	 *
	 * @param name
	 *            UserID, des zu testenden User
	 * @param attribut
	 *            Attribut, auf welches getestet werden soll
	 * @return true, wenn vorhanden, false sonst
	 */
	protected boolean checkAttribute(String name, String attribut) {
		String relative = this.relative.substring(1);
		boolean vorhanden = false;
		try {
			//Baue zu suchendes Attribut
			BasicAttributes zusuchendeAttribute = new BasicAttributes(true);
			//Suche nach uid
			zusuchendeAttribute.put(new BasicAttribute("uid", name)); //$NON-NLS-1$
			//nach folgendem Attribut
			zusuchendeAttribute.put(new BasicAttribute(attribut));
			//ausführen!
			NamingEnumeration ergebnis = lctx.search(relative + baseDN, zusuchendeAttribute);
			//Wenn was kommt, gibt es das Attribut
			if (ergebnis.hasMore()) {
				vorhanden = true;
			}
		} catch (NamingException e) {
			//Wenn was schief läuft, ist Attribut nicht vorhanden
			vorhanden = false;
		}
		return vorhanden;
	}

	/**
	 * Methode, die testet, ob User name im LDAP das bestimmte Attribut mit dem
	 * bestimmten Wert hat.
	 *
	 * @param name
	 *            uid des Objekts
	 * @param attribute
	 *            zu suchendes Attribut
	 * @param wert
	 *            Wert des Attributes
	 * @return true, wenn Wert vorhanden, false sonst, auch bei anderen Fehlern
	 */
	protected boolean checkAttribute(String name, String attribute, String wert) {
		boolean vorhanden = false;
		try {
			//Zu suchende Attribute zusammenbauen
			Attributes attributes = lctx.getAttributes(fullUserDN(name));
			//hole Attrbute
			Attribute tester = attributes.get(attribute);
			NamingEnumeration liste = null;
			if (tester != null) {
				//hole alle
				liste = tester.getAll();
				while (liste.hasMore()) {
					String testwert = (String) liste.next();
					//Teste durch, ob wert vorhanden
					if (testwert.equals(wert)) {
						vorhanden = true;
					}
				}
			}
		} catch (NamingException e) {
			// Im Fehlerfall ist der Wert natürlich nicht vorhanden
			vorhanden = false;
		} catch (LDAPException e) {
			// Im Fehlerfall ist der Wert natürlich nicht vorhanden
			vorhanden = false;
		}
		return vorhanden;
	}

	/**
	 * Methode, die testet, ob OU name im LDAP das bestimmte Attribut mit dem
	 * bestimmten Wert hat.
	 *
	 * @param name
	 *            ou des Objekts
	 * @param attribute
	 *            zu suchendes Attribut
	 * @param wert
	 *            Wert des Attributes
	 * @return true, wenn Wert vorhanden, false sonst, auch bei anderen Fehlern
	 */
	private boolean checkAttributeOU(String name, String attribute, String wert) {
		boolean vorhanden = false;
		try {
			//Zu suchende Attribute zusammenbauen
			Attributes attributes = lctx.getAttributes("ou=" + name + relative + baseDN); //$NON-NLS-1$
			//hole Attrbute
			Attribute tester = attributes.get(attribute);
			NamingEnumeration liste = null;
			if (tester != null) {
				//hole alle
				liste = tester.getAll();
				while (liste.hasMore()) {
					String testwert = (String) liste.next();
					//Teste durch, ob wert vorhanden
					if (testwert.equals(wert)) {
						vorhanden = true;
					}
				}
			}
		} catch (NamingException e) {
			// Im Fehlerfall ist der Wert natürlich nicht vorhanden
			vorhanden = false;
		}
		return vorhanden;
	}

	/**
	 * Testet, ob userid im LDAP vorhanden
	 *
	 * @param userid
	 *            UserID, die getestet werden soll
	 * @return true, wenn vorhanden, false sonst
	 * @throws LDAPException
	 */
	public boolean checkUid(String userid) throws LDAPException {
		boolean vorhanden = false;
		try {
			BasicAttributes zusuchendeAttribute = new BasicAttributes(true);
			zusuchendeAttribute.put("uid", userid); //$NON-NLS-1$
			NamingEnumeration ergebnis = lctx.search(relative.substring(1) + baseDN, zusuchendeAttribute);
			if (ergebnis.hasMore()) {
				vorhanden = true;
			}
		} catch (NamingException e) {
			vorhanden = false;
		}
		return vorhanden;
	}

	/**
	 * Testet, ob userid im LDAP vorhanden
	 *
	 * @param userid
	 *            UserID, die getestet werden soll
	 * @param kategorie
	 *            Kategorie, in der der Kontakt gesucht werden soll
	 * @return true, wenn vorhanden, false sonst
	 * @throws LDAPException
	 */
	public boolean checkContact(String userid, String kategorie) throws LDAPException {
		boolean vorhanden = false;
		try {
			BasicAttributes zusuchendeAttribute = new BasicAttributes(true);
			zusuchendeAttribute.put("uid", userid); //$NON-NLS-1$
			NamingEnumeration ergebnis = lctx.search("ou=" + kategorie + relative + baseDN, zusuchendeAttribute); //$NON-NLS-1$
			if (ergebnis.hasMore()) {
				vorhanden = true;
			}
		} catch (NamingException e) {
			vorhanden = false;
		}
		return vorhanden;
	}

	/**
	 * Testet, ob ou im LDAP vorhanden
	 *
	 * @param ou
	 *            ou, die getestet werden soll
	 * @return true, wenn vorhanden, false sonst
	 * @throws LDAPException
	 */
	public boolean checkOu(String ou) throws LDAPException {
		boolean vorhanden = false;
		try {
			BasicAttributes zusuchendeAttribute = new BasicAttributes(true);
			zusuchendeAttribute.put("ou", ou); //$NON-NLS-1$
			NamingEnumeration ergebnis = lctx.search(relative.substring(1) + baseDN, zusuchendeAttribute);
			//System.out.print("Test ou=" + ou + relative + baseDN);
			if (ergebnis.hasMore()) {
				vorhanden = true;
				//System.out.print(" - gefunden!");
			}
		} catch (NamingException e) {
			//System.out.print("Test ou=" + ou + relative + baseDN);
			vorhanden = false;
		}
		//System.out.println();
		return vorhanden;
	}

	/**
	 * Stellt fest, ob gegebener DN vorhanden ist
	 * @param dn FIXME
	 * @return FIXME
	 * @throws LDAPException
	 */
	public boolean checkDN(String dn) throws LDAPException {
		boolean vorhanden = false;
		try {
			Object test = lctx.lookup(dn);
			//System.out.print("Test ou=" + ou + relative + baseDN);
			if(test!=null){
				vorhanden = true;
			}
		} catch (NamingException e) {
			//System.out.print("Test ou=" + ou + relative + baseDN);
			vorhanden = false;
		}
		//System.out.println();
		return vorhanden;
	}

	/**
	 * @param modifications FIXME
	 * @param contact FIXME
	 * @param attribut FIXME
	 * @param wert FIXME
	 */
	protected List modifyAttributes_restricted(List modifications, Attributes contact, String attribut, String wert) {
		if ((wert != null) & (!wert.equals(""))) { //$NON-NLS-1$
			if (!this.checkAttribute(contact, attribut)) {
				modifications.add(new ModificationItem(DirContext.ADD_ATTRIBUTE, new BasicAttribute(attribut, wert)));
			} else if (!this.checkAttribute(contact, attribut, wert)) {
				modifications
						.add(new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute(attribut, wert)));
			}
		} else {
			//Wenn nicht mehr vorhanden, checke, ob es im LDAP noch
			// drinsteht...
			if (this.checkAttribute(contact, attribut)) {
				modifications.add(new ModificationItem(DirContext.REMOVE_ATTRIBUTE, new BasicAttribute(attribut)));
			}
		}
		return modifications;
	}

	/**
	 * @param contact FIXME
	 * @param attribut FIXME
	 */
	private boolean checkAttribute(Attributes contact, String attribut) {
		Attribute tester = contact.get(attribut);
        return (tester != null);
	}

	/**
	 * @param contact FIXME
	 * @param attribute FIXME
	 * @param value FIXME
	 */
	protected boolean checkAttribute(Attributes contact, String attribute, String value) {
		boolean vorhanden = false;
		//hole Attribute
		Attribute tester = contact.get(attribute);
		NamingEnumeration liste = null;
		if (tester != null) {
			//hole alle
			try {
				liste = tester.getAll();
				while (liste.hasMore()) {
					String testwert = (String) liste.next();
					//Teste durch, ob wert vorhanden
					if (testwert.equals(value)) {
						vorhanden = true;
					}
				}
			} catch (NamingException e) {
				vorhanden = false;
			}
		}
		return vorhanden;
	}

	/**
	 * Kleiner Helper, der aufpasst, das bei relative am Anfang und am Ende ein
	 * Komma ist.
	 *
	 * @param string FIXME
	 * @return Argument getrimt und ggf vorne und/oder hinten um Kommas ergänzt
	 */
	public static String surroundWithCommas(String string) {
		if (string == null)
            return ",";
        string = string.trim();
        //testen, ob am Anfang kein Komma, sonst hinzufügen
		if (!string.startsWith(",")) {
			string = "," + string;
		}
		//das ganze nochmal am Ende
		if (!string.endsWith(",")) {
			string = string + ",";
		}
		return string;
	}

	/**
	 * getter für Relative
	 *
	 * @return relativen DN
	 */
	public String getRelative() {
		return relative;
	}

	/**
	 * Setze Relative
	 *
	 * @param string
	 *            neuer relativer DN
	 */
	public void setRelative(String string) {
		relative = surroundWithCommas(string);
	}

	/**
	 * Modifiert eine OU
	 *
	 * @param value
	 *            Name der OU
	 * @param user
	 *            Liste mit Usern, die Zugriff erhalten sollen
	 */
	public void modifyOU(String value, List user) throws LDAPException {
		List modifications = new ArrayList();
		//Hole objectclass aus dem LDAP
		Attribute objectclass = null;
		String[] objectClassList = {"top", "groupOfNames"}; //$NON-NLS-1$ //$NON-NLS-2$
		try {
			String[] objectClassA = {"objectclass"}; //$NON-NLS-1$
			objectclass = lctx.getAttributes("ou=" + value + relative + baseDN, objectClassA).get("objectClass"); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (NamingException e) {
			throw new LDAPException(Messages.getString("LDAPManager.Konnte_objectClass_nicht_sichern_01")
					+ e.getMessage(), e);
		}
		boolean modified = false;
		//Checke objectclass
		for (int i = 0; i < objectClassList.length; i++) {
			if (!checkAttributeOU(value, "objectClass", objectClassList[i])) { //$NON-NLS-1$
				modified = true;
				objectclass.add(objectClassList[i]);
			}
		}
		if (modified) {
			//Ersetze Objectclass
			modifications.add(new ModificationItem(DirContext.REPLACE_ATTRIBUTE, objectclass));
		}
		Attribute member = new BasicAttribute("member"); //$NON-NLS-1$
		//member neu bauen
		for (int i = 0; i < user.size(); i++) {
			try {
				member.add(fullUserDN(user.get(i).toString()));
			} catch (LDAPException le) {
				logger.log(Level.WARNING, Messages.getString("LDAPManager.87")); //$NON-NLS-1$
			}
		}
		modifications.add(new ModificationItem(DirContext.REPLACE_ATTRIBUTE, member));
		//System.out.println(modifications);
		//Führe Änderungen durch
		try {
			lctx.modifyAttributes("ou=" + value + relative + baseDN, (ModificationItem[]) modifications
					.toArray(new ModificationItem[1]));
		} catch (NamingException e2) {
			throw new LDAPException(Messages.getString("LDAPManager.OU_konnte_nicht_modifiziert_werden_01")
					+ e2.toString(), e2);
		}
	}

	/**
	 * Getter für alle OU's
	 *
	 * @param base
	 *            Basis unter der gesucht werden soll
	 * @return Liste mit allen OU's
	 * @throws LDAPException
	 *             wenn etwas schief läuft
	 */
	public List getOUs(String base) throws LDAPException {
		List ou = new ArrayList();
		try {
			SearchControls cons = new SearchControls();
			this.initializeSearchControls( cons );
			NamingEnumeration en = lctx.search(base, "(&(objectClass=groupOfNames))", cons); //$NON-NLS-1$
			while (en.hasMoreElements()) {
				Attributes result = ((SearchResult) en.nextElement()).getAttributes();
				ou.add(result.get("ou").toString().substring(4)); //$NON-NLS-1$
			}
		} catch (Exception e) {
			throw new LDAPException(e.getMessage(), e);
		}
		return ou;
	}

	protected void initializeSearchControls( SearchControls cons )
	{
		int scope = Boolean.parseBoolean( ( String ) params.get( KEY_RECURSIVE_LOOKUPS ) ) ? SearchControls.SUBTREE_SCOPE : SearchControls.ONELEVEL_SCOPE;
		cons.setSearchScope( scope );
	}

	/**
	 * Holt alle Kontakte unterhalb einer Basis aus dem LDAP
	 *
	 * @param base
	 *            Basis, unterhalb der gesucht werden sollen
	 * @return Liste mit uid's der Kontakte
	 * @throws LDAPException
	 */
	public List getUIDs(String base) throws LDAPException {
		List uid = new ArrayList();
		try {
			SearchControls cons = new SearchControls();
			this.initializeSearchControls( cons );
			NamingEnumeration en = lctx.search(base, "(&(objectClass=mozillaOrgPerson))", cons); //$NON-NLS-1$
			while (en.hasMoreElements()) {
				Attributes result = ((SearchResult) en.nextElement()).getAttributes();
				uid.add(result.get("uid").toString().substring(4)); //$NON-NLS-1$
			}
		} catch (Exception e) {}
		return uid;
	}

	/**
	 * liest den BaseDN aus
	 *
	 * @return BaseDN
	 */
	public String getBaseDN() {
		return baseDN;
	}
	public Attributes getEntry(String dn) throws LDAPException {
		Attributes attrs = null;
		try {
			attrs = lctx.getAttributes(dn);
		} catch (NamingException e) {
			throw new LDAPException(Messages.getString("LDAPManager.91"), e); //$NON-NLS-1$
		}
		return attrs;
	}

	public String fullUserDN(String uid) throws LDAPException {
		return fullUserDN(uid, defaultObjectClasses);
	}

	public String fullUserDN(String uid, String[] objectClasses) throws LDAPException {
		String dn = null;
		Attributes attr = new BasicAttributes();
		attr.put("uid", uid); //$NON-NLS-1$
		if (objectClasses != null && objectClasses.length > 0) {
			Attribute att = new BasicAttribute("objectclass");
			for (int i = 0; i < objectClasses.length; i++)
				att.add(objectClasses[i]);
			attr.put(att);
		}
		try {
			String name = relativeUser.substring(1) + baseDN;
			String filter = filterTemplate.format(new Object[]{uid});
			SearchControls cons = new SearchControls();
			this.initializeSearchControls( cons );
			if (logger.isLoggable(Level.INFO))
				logger.log(Level.INFO, "Search LDAP account in \"" + name + "\" with filter \"" + filter + "\".");
			NamingEnumeration ne = lctx.search(name, filter, cons);
			if (!ne.hasMore()) {
				throw new LDAPException(Messages.getString("LDAPManager.95")); //$NON-NLS-1$
			}
			SearchResult search = (SearchResult) ne.next();
			dn = search.getNameInNamespace();
			dn = dn.replace("/", "\\2F");
			if (logger.isLoggable(Level.INFO))
				logger.log(Level.INFO, "Found LDAP DN \"" + dn + "\".");
			if (ne.hasMore()) {
				throw new LDAPException(Messages.getString("LDAPManager.96")); //$NON-NLS-1$
			}
		} catch (NamingException e) {
			throw new LDAPException(Messages.getString("LDAPManager.97"), e); //$NON-NLS-1$
		}
		return dn;
	}

	public String searchSystemPreferenceNode(String key, String value) throws LDAPException {
		String dn = null;
		Attributes attr = new BasicAttributes();
		attr.put(key, value);
		try {
			NamingEnumeration ne = lctx.search(relative.substring(1) + baseDN, attr);
			if (ne.hasMoreElements()) {
				SearchResult search = (SearchResult) ne.next();
				if (!ne.hasMoreElements()) {
					dn = search.getName();
				}
			}
		} catch (NamingException e) {
			throw new LDAPException("Es ist ein Fehler bei der Suche nach " + key + "=" + value + " aufgetreten!", e);
		}
		return dn;
	}

	public void createSystemPreferenceNode(String dn) throws LDAPException {
		//angegebene ou anlegen
		try {
			BasicAttributes attr = new BasicAttributes();
			BasicAttribute objectclass = new BasicAttribute("objectclass", "Preference"); //$NON-NLS-1$ //$NON-NLS-2$
			objectclass.add("PreferenceNode");
			attr.put(objectclass);
			StringTokenizer st = new StringTokenizer(dn, "=");
			attr.put(st.nextToken(), st.nextToken());
			lctx.createSubcontext(dn + relative + baseDN, attr);
		} catch (Exception e) {
			throw new LDAPException(Messages.getString("Konnte Preference nicht anlegen"), e); //$NON-NLS-1$
		}
	}

	/**
	 * @param key FIXME
	 * @param value FIXME
	 */
	public void updateOrCreateSystemPreferenceKey(String key, String value, BigInteger modified) throws LDAPException {
		//Feststellen, ob Preference schon existiert...
		Attributes attr = null;
		try {
			attr = getSystemPreferenceKey(key);
		} catch (NamingException e) {
			//Key gibt es noch nicht...
		}
		if (attr == null) {
			createSystemPreferenceKey(key, value, modified);
		} else {
			//Stelle letzte änderung fest
			BigInteger lastmodified = null;
			try {
				String modifieds = (String) attr.get("PreferenceLastModified").get(0);
				lastmodified = new BigInteger(modifieds);
			} catch (NamingException e1) {
				throw new LDAPException(e1);
			}
			if (!modified.equals(lastmodified)) {
				updateSystemPreferenceKey(key, value, modified);
			}
		}
	}

	/**
	 * @param key FIXME
	 * @param value FIXME
	 */
	private void updateSystemPreferenceKey(String key, String value, BigInteger modified) throws LDAPException {
		ModificationItem[] mods = new ModificationItem[2];
		mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("PreferenceLastModified",
				modified.toString()));
		mods[1] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("PreferenceValue", value));
		try {
			lctx.modifyAttributes("PreferenceKey=" + key + relative + baseDN, mods);
		} catch (NamingException e) {
			throw new LDAPException(e);
		}
	}

	/**
	 * @param key FIXME
	 */
	public Attributes getSystemPreferenceKey(String key) throws NamingException {
		Attributes attr = null;
		attr = lctx.getAttributes("PreferenceKey=" + key + relative + baseDN);
		return attr;
	}

	/**
	 * @param key FIXME
	 * @param value FIXME
	 * @throws LDAPException
	 */
	public void createSystemPreferenceKey(String key, String value, BigInteger modified) throws LDAPException {
		//angegebene Preference anlegen
		try {
			BasicAttributes attr = new BasicAttributes();
			BasicAttribute objectclass = new BasicAttribute("objectclass", "Preference"); //$NON-NLS-1$ //$NON-NLS-2$
			objectclass.add("PreferenceAttribute");
			attr.put(objectclass);
			attr.put("PreferenceKey", key);
			attr.put("PreferenceValue", value);
			attr.put("PreferenceLastModified", String.valueOf(modified));
			lctx.createSubcontext("PreferenceKey=" + key + relative + baseDN, attr);
		} catch (Exception e) {
			throw new LDAPException(Messages.getString("Konnte Preference nicht anlegen"), e); //$NON-NLS-1$
		}
	}

	/**
	 * @param key FIXME
	 */
	public String getSystemPreferenceValue(String key) throws LDAPException {
		String result = null;
		try {
			Attributes attr = getSystemPreferenceKey(key);
			result = (String) attr.get("PreferenceValue").get(0);
		} catch (NamingException e) {
			throw new LDAPException(e);
		}
		return result;
	}

	public String[] getSystemPreferenceKeys() throws LDAPException {
		BasicAttributes search = new BasicAttributes();
		search.put("objectclass", "PreferenceAttribute");
		Vector result = new Vector();
		try {
			NamingEnumeration ne = lctx.search(relative.substring(1) + baseDN, search);
			while (ne.hasMoreElements()) {
				SearchResult sr = (SearchResult) ne.nextElement();
				result.add(sr.getAttributes().get("PreferenceKey").get(0));
			}
		} catch (NamingException e) {
			throw new LDAPException("Es ist ein Fehler beim Suchen der Keys aufgetreten!", e);
		}
		int count = result.size();
		String returnString[] = new String[count];
		for (int i = 0; i < count; i++) {
			returnString[i] = (String) result.get(i);
		}
		return returnString;
	}

	public String[] getSystemPreferenceChildren() throws LDAPException {
		BasicAttributes search = new BasicAttributes();
		search.put("objectclass", "PreferenceNode");
		Vector result = new Vector();
		try {
			NamingEnumeration ne = lctx.search(relative.substring(1) + baseDN, search);
			while (ne.hasMoreElements()) {
				SearchResult sr = (SearchResult) ne.nextElement();
				result.add(sr.getAttributes().get("PreferenceKey").get(0));
			}
		} catch (NamingException e) {
			throw new LDAPException("Es ist ein Fehler beim Suchen der Keys aufgetreten!", e);
		}
		int count = result.size();
		String returnString[] = new String[count];
		for (int i = 0; i < count; i++) {
			returnString[i] = (String) result.get(i);
		}
		return returnString;
	}

	/**
	 * @param key FIXME
	 */
	public void deleteSystemPreferenceKey(String key) throws LDAPException {
		try {
			lctx.destroySubcontext("PreferenceKey=" + key + relative + baseDN);
		} catch (NamingException e) {
			throw new LDAPException("Es ist ein Fehler beim Löschen der Preference aufgetreten", e);
		}
	}

	/**
	 *
	 */
	public void deleteSystemPreferenceNode() throws LDAPException {
		try {
			lctx.destroySubcontext(relative.substring(1) + baseDN);
		} catch (NamingException e) {
			throw new LDAPException("Fehler beim Löschen des PreferenceNode!", e);
		}
	}

	/**
	 * @return Returns the relativeUser.
	 */
	public String getRelativeUser() {
		return relativeUser;
	}

	/**
	 * @param relativeUser
	 *            The relativeUser to set.
	 */
	public void setRelativeUser(String relativeUser) {
		this.relativeUser = surroundWithCommas(relativeUser);
	}

	/**
	 * Testet, ob userid im LDAP vorhanden
	 * @param userid	UserID, die getestet werden soll
	 * @return	true, wenn vorhanden, false sonst
	 * @throws LDAPException
	 */
	public boolean checkuid(String userid) throws LDAPException{
		boolean vorhanden = false;
		try
		{
			BasicAttributes zusuchendeAttribute = new BasicAttributes(true);
			zusuchendeAttribute.put("uid",userid);
			NamingEnumeration ergebnis = lctx.search(relative.substring(1)+ baseDN, zusuchendeAttribute);
			if(ergebnis.hasMore()){
				vorhanden = true;
			}
		}
		catch (NamingException e)
		{
			vorhanden = false;
		}
		return vorhanden;
	}

	public boolean checkcn(String vorname, String nachname){
		boolean vorhanden = false;
		try
		{
			BasicAttributes zusuchendeAttribute = new BasicAttributes(true);
			zusuchendeAttribute.put("cn",nachname + " " + vorname);
			NamingEnumeration ergebnis = lctx.search(relative.substring(1)+ baseDN, zusuchendeAttribute);
			if(ergebnis.hasMore()){
				vorhanden = true;
			}
		}
		catch (NamingException e)
		{
			vorhanden = false;
		}
		return vorhanden;

	}

	protected String byteArrayToBase64(byte[] a, boolean alternate)
	{
		int aLen = a.length;
		int numFullGroups = aLen / 3;
		int numBytesInPartialGroup = aLen - 3 * numFullGroups;
		int resultLen = 4 * ((aLen + 2) / 3);
		StringBuffer result = new StringBuffer(resultLen);
		char[] intToAlpha = (alternate ? intToAltBase64 : intToBase64);

		// Translate all full groups from byte array elements to Base64
		int inCursor = 0;
		for (int i = 0; i < numFullGroups; i++)
		{
			int byte0 = a[inCursor++] & 0xff;
			int byte1 = a[inCursor++] & 0xff;
			int byte2 = a[inCursor++] & 0xff;
			result.append(intToAlpha[byte0 >> 2]);
			result.append(intToAlpha[(byte0 << 4) & 0x3f | (byte1 >> 4)]);
			result.append(intToAlpha[(byte1 << 2) & 0x3f | (byte2 >> 6)]);
			result.append(intToAlpha[byte2 & 0x3f]);
		}

		// Translate partial group if present
		if (numBytesInPartialGroup != 0)
		{
			int byte0 = a[inCursor++] & 0xff;
			result.append(intToAlpha[byte0 >> 2]);
			if (numBytesInPartialGroup == 1)
			{
				result.append(intToAlpha[(byte0 << 4) & 0x3f]);
				result.append("==");
			} else
			{
				// assert numBytesInPartialGroup == 2;
				int byte1 = a[inCursor++] & 0xff;
				result.append(intToAlpha[(byte0 << 4) & 0x3f | (byte1 >> 4)]);
				result.append(intToAlpha[(byte1 << 2) & 0x3f]);
				result.append('=');
			}
		}
		// assert inCursor == a.length;
		// assert result.length() == resultLen;
		return result.toString();
	}
	/**
	 * This array is a lookup table that translates 6-bit positive integer
	 * index values into their "Base64 Alphabet" equivalents as specified
	 * in Table 1 of RFC 2045.
	 */
	private final char intToBase64[] =
		{
			'A',
			'B',
			'C',
			'D',
			'E',
			'F',
			'G',
			'H',
			'I',
			'J',
			'K',
			'L',
			'M',
			'N',
			'O',
			'P',
			'Q',
			'R',
			'S',
			'T',
			'U',
			'V',
			'W',
			'X',
			'Y',
			'Z',
			'a',
			'b',
			'c',
			'd',
			'e',
			'f',
			'g',
			'h',
			'i',
			'j',
			'k',
			'l',
			'm',
			'n',
			'o',
			'p',
			'q',
			'r',
			's',
			't',
			'u',
			'v',
			'w',
			'x',
			'y',
			'z',
			'0',
			'1',
			'2',
			'3',
			'4',
			'5',
			'6',
			'7',
			'8',
			'9',
			'+',
			'/' };

	/**
	 * This array is a lookup table that translates 6-bit positive integer
	 * index values into their "Alternate Base64 Alphabet" equivalents.
	 * This is NOT the real Base64 Alphabet as per in Table 1 of RFC 2045.
	 * This alternate alphabet does not use the capital letters.  It is
	 * designed for use in environments where "case folding" occurs.
	 */
	private final char intToAltBase64[] =
		{
			'!',
			'"',
			'#',
			'$',
			'%',
			'&',
			'\'',
			'(',
			')',
			',',
			'-',
			'.',
			':',
			';',
			'<',
			'>',
			'@',
			'[',
			']',
			'^',
			'`',
			'_',
			'{',
			'|',
			'}',
			'~',
			'a',
			'b',
			'c',
			'd',
			'e',
			'f',
			'g',
			'h',
			'i',
			'j',
			'k',
			'l',
			'm',
			'n',
			'o',
			'p',
			'q',
			'r',
			's',
			't',
			'u',
			'v',
			'w',
			'x',
			'y',
			'z',
			'0',
			'1',
			'2',
			'3',
			'4',
			'5',
			'6',
			'7',
			'8',
			'9',
			'+',
			'?' };


	/* (non-Javadoc)
	 * @see de.tarent.octopus.server.UserManager#getUserParam(java.lang.String, java.lang.String)
	 */
	public Object getUserParam(String userID, String paramname) throws TcSecurityException{
        Attribute temp = null;
        try {
        	String dn = fullUserDN(userID);
            Attributes attr = lctx.getAttributes(dn);
            if(attr.get(paramname)!=null) {temp = attr.get(paramname);}
			return temp!=null?temp.get():null;
        } catch (NamingException e) {
            throw new TcSecurityException("Es ist ein Fehler beim Holen der Userdaten aufgetreten! ", e);
        } catch (LDAPException e){
        	throw new TcSecurityException(TcSecurityException.ERROR_SERVER_USERMANAGEMENT_ERROR, e);
        }
	}

    //
    // private Hilfsmethoden
    //
    private static Hashtable createEnvironment(String ldapurl) {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory"); //$NON-NLS-1$
        env.put(Context.PROVIDER_URL, ldapurl);
        return env;
    }

    protected MessageFormat filterTemplate;
}