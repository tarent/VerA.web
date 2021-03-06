package de.tarent.aa.veraweb.utils;

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

import de.tarent.aa.veraweb.beans.Import;
import de.tarent.aa.veraweb.beans.ImportPerson;
import de.tarent.aa.veraweb.beans.ImportPersonExtra;
import de.tarent.aa.veraweb.beans.facade.PersonConstants;
import de.tarent.aa.veraweb.worker.DataExchangeWorker;
import de.tarent.octopus.beans.Bean;
import de.tarent.octopus.beans.BeanException;
import de.tarent.octopus.beans.Database;
import de.tarent.octopus.beans.ExecutionContext;
import de.tarent.octopus.server.OctopusContext;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Diese Klasse dient als Importziel für die Methode
 * {@link DataExchangeWorker#importToTransit(OctopusContext, Map, String, String, String, Integer, Integer, Map)}
 */
public class VerawebDigester implements ImportDigester {
    int personCount = 0;
    int importableCount = 0;
    int incorrectCount = 0;
    int duplicateCount = 0;

    final OctopusContext cntx;
    final Database db;
    final ExecutionContext context;
    final Import importInstance;
    final Integer orgUnit;
    final String importSource;
    final String importPersonCompareField1;
    final String stockBeanCompareField1;
    final String importPersonCompareField2;
    final String stockBeanCompareField2;

    final PersonDuplicateCheckHelper duplicateCheckHelper;

    /**
     * Dieser Konstruktor initialisiert die finalen Member.
     *
     * @param cntx             FIXME
     * @param context          FIXME
     * @param importInstance   FIXME
     * @param importProperties FIXME
     * @param importSource     FIXME
     */
    public VerawebDigester(OctopusContext cntx, ExecutionContext context, Map importProperties, String importSource,
      Import importInstance) {
        assert context != null;
        this.cntx = cntx;
        this.importSource = importSource;
        this.orgUnit = importInstance.orgunit;
        this.importInstance = importInstance;
        this.context = context;
        this.db = context.getDatabase();
        stockBeanCompareField1 = (String) importProperties.get("beanFieldEqual1");
        importPersonCompareField1 = (String) importProperties.get("fieldEqual1");
        stockBeanCompareField2 = (String) importProperties.get("beanFieldEqual2");
        importPersonCompareField2 = (String) importProperties.get("fieldEqual2");

        duplicateCheckHelper = new PersonDuplicateCheckHelper(context, importInstance);
    }

    //
    // Öffentliche Methoden
    //

    /**
     * Diese Methode liefert den aktuellen Stand dieses Imports in Form einer
     * {@link Map} mit speziellen Inhalten.
     *
     * @return Map mit Informationen zum Import, insbesondere der Anzahl gefundener
     * Datensätze unter "dsCount", der Anzahl Duplikate unter "dupCount", der Anzahl
     * importierter Datensätze unter "saveCount" und der Import-ID unter "id".
     */
    public Map getImportStats() {
        return DataExchangeWorker.createImportStats(incorrectCount, personCount,
          duplicateCount, importableCount, importInstance.id);
    }

    //
    // Schnittstelle ImportDigester
    //

    /**
     * Diese Methode wird zu Beginn eines Imports aufgerufen.
     */
    public void startImport() throws BeanException {
    }

    /**
     * Diese Methode wird zum Ende eines Imports aufgerufen.
     */
    public void endImport() throws BeanException {
        try {
            duplicateCount = duplicateCheckHelper.getDuplicatesCount(cntx);
            importableCount = personCount - duplicateCount;
        } catch (IOException e) {
            throw new BeanException("Fehler beim Zugriff auf Bean-Properties", e);
        }
    }

    /**
     * Diese Methode wird von einem {@link Importer} zu jeder zu importierenden
     * Person aufgerufen, übergeben wird die Person und eine Liste mit Beans,
     * die Zusätze zur Person darstellen.<br>
     * Falls Abhängigkeiten unter diesen Beans bestehen, stehen in der
     * Liste die Beans, von der eine bestimmte Bean abhängt, vor dieser.
     *
     * @param person eine {@link ImportPerson}-Instanz
     * @param extras eine Liste mit Beans, die Zusätze zur Person darstellen; es
     *               werden nur solche akzeptiert, die {@link ImportPersonExtra} implementieren.
     * @throws BeanException FIXME
     * @throws IOException   FIXME
     * @see ImportDigester#importPerson(ImportPerson, List)
     */
    public void importPerson(ImportPerson person, List extras) throws BeanException, IOException {
        assert person != null;

        // Verwaltungsdaten: ID; muß null sein
        person.id = null;
        // Verwaltungsdaten: Mandanten ID
        person.orgunit = orgUnit;
        // Verwaltungsdaten: Import-ID
        person.fk_import = new Long(importInstance.id.longValue());
        // Verwaltungsdaten: Import-Quelle
        person.importsource = importSource;
        // Verwaltungsdaten: noch nicht nach tperson importiert
        person.dupcheckaction = ImportPerson.FALSE;
        // Verwaltungsdaten: wenn Duplikat, dann nicht nach tperson importieren
        person.dupcheckstatus = ImportPerson.FALSE;
        // Verwaltungsdaten: keine Duplikate
        person.duplicates = null;
        // Verwaltungsdaten: nicht gelöscht
        person.deleted = PersonConstants.DELETED_FALSE;
        // Verwaltungsdaten: istFirma-Flag
        if (!PersonConstants.ISCOMPANY_TRUE.equals(person.iscompany)) {
            person.iscompany = PersonConstants.ISCOMPANY_FALSE;
        }
        AddressHelper.checkPersonSalutation(person, db, context);

        /*
         * fk_workarea must not be null, setting default workarea "Keine" with pk == 0
         * cklein 2008-03-27
         */
        person.workarea = new Integer(0);

        // Datensatz nicht berechtigte Felder entziehen.
        person.clearRestrictedFields(cntx);

        // Datensatz auf vollständigkeit testen.
        person.verify();
        if (person.isCorrect()) {
            // Zähler aktualisieren
            personCount++;

            // Datensatz speichern.
            if (extras == null) {
                db.saveBean(person, context, false); // neue ID wird nicht benötigt
            } else {
                db.saveBean(person, context, true);
                // Extras übernehmen
                for (Iterator itExtras = extras.iterator(); itExtras.hasNext(); ) {
                    Object extraObject = itExtras.next();
                    if (extraObject instanceof ImportPersonExtra) {
                        ((ImportPersonExtra) extraObject).associateWith(person);
                        if (extraObject instanceof Bean) {
                            db.saveBean((Bean) extraObject, context, false);
                        }
                    }
                }
            }
        } else {
            incorrectCount++;
        }
    }
}
