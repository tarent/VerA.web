/* $Id: PersonDoctype.java,v 1.1 2007/06/20 11:56:51 christoph Exp $
 * 
 * Created on 23.02.2005
 */
package de.tarent.aa.veraweb.beans;

import de.tarent.aa.veraweb.beans.facade.PersonConstants;
import de.tarent.octopus.PersonalConfigAA;
import de.tarent.octopus.custom.beans.BeanException;
import de.tarent.octopus.server.OctopusContext;


/**
 * <p>
 * Diese Bean stellt einen Eintrag der Tabelle veraweb.tperson_doctype dar,
 * eine Auflistung von Personen und Dokumenttypen mit Daten f�r die Dokumente.
 * </p>
 * 
 * <p>
 * <strong>Achtung - Verwendung in Datenbank-Worker:</strong>
 * Die Verkn�pfung mit entsprechendem {@link Doctype Dokumenttyp}
 * muss in dem entsprechendem Worker per Join auf <em>veraweb.tdoctype</em>
 * erfolgen. Siehe {@link de.tarent.aa.veraweb.worker.PersonDetailWorker}
 * </p>
 * 
 * @see de.tarent.aa.veraweb.worker.PersonDoctypeWorker
 * 
 * @author christoph
 * @author mikel
 */
public class PersonDoctype extends AbstractBean {
    /** Prim�rschl�ssel */
	public Integer id;
    /** Dokumentname */
	public String name;
	
    /** Fremdschl�ssel {@link Person} */
	public Integer person;
	
    /** Fremdschl�ssel {@link Doctype Dokumenttyp} */
	public Integer doctype;
	/** Doctype ID */
	public Integer doctypeId;
    /** {@link Doctype Dokumenttyp}: 1=Privat 2=Gesch�ftlich 3=Weitere */
	public Integer doctypeAddresstype;
    /** {@link Doctype Dokumenttyp}: 1=Latein 2=Z1 3=Z2 */
	public Integer doctypeLocale;
	
    /** 1=Privat 2=Gesch�ftlich 3=Weitere */
	public Integer addresstype;
    /** 1=Latein 2=Z1 3=Z2 */
	public Integer locale;
    /** Freitext */
	public String textfield;
    /** Freitext Partner */
	public String textfieldPartner;
    /** Freitext Verbinder */
	public String textfieldJoin;

    /**
     * Diese Methode testet, ob im aktuellen Kontext diese Bohne gelesen werden
     * darf.<br>
     * Test ist, ob der Benutzer Standard-Reader ist.
     * 
     * @param cntx Octopus-Kontext
     * @throws BeanException Wenn im angegebenen Kontext diese Bohne nicht gelesen werden darf.
     * @see de.tarent.aa.veraweb.beans.AbstractBean#checkRead(de.tarent.octopus.server.OctopusContext)
     */
    public void checkRead(OctopusContext cntx) throws BeanException {
        checkGroup(cntx, PersonalConfigAA.GROUP_READ_STANDARD);
    }

    /**
     * Diese Methode testet, ob im aktuellen Kontext diese Bohne geschrieben
     * werden darf.<br>
     * Test ist, ob der Benutzer Writer ist.
     * 
     * @param cntx Octopus-Kontext
     * @throws BeanException Wenn im angegebenen Kontext diese Bohne nicht geschrieben werden darf.
     * @see de.tarent.aa.veraweb.beans.AbstractBean#checkWrite(de.tarent.octopus.server.OctopusContext)
     */
    public void checkWrite(OctopusContext cntx) throws BeanException {
        checkGroup(cntx, PersonalConfigAA.GROUP_WRITE);
    }

    /**
     * �berpr�ft das Bean auf innere Vollst�ndigkeit und stellt sie gegebenenfalls her.
     * 
     * @throws BeanException bei Unvollst�ndigkeit
     */
    public void verify() throws BeanException {
        if (addresstype == null)
            addresstype = new Integer(PersonConstants.ADDRESSTYPE_BUSINESS);
        if (locale == null)
            locale = new Integer(PersonConstants.LOCALE_LATIN);
    }
}
