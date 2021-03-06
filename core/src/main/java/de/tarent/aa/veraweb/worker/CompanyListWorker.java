package de.tarent.aa.veraweb.worker;

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

import de.tarent.aa.veraweb.beans.Person;
import de.tarent.aa.veraweb.beans.facade.PersonConstants;
import de.tarent.aa.veraweb.utils.AddressHelper;
import de.tarent.dblayer.sql.Escaper;
import de.tarent.dblayer.sql.clause.Expr;
import de.tarent.dblayer.sql.clause.Order;
import de.tarent.dblayer.sql.clause.RawClause;
import de.tarent.dblayer.sql.clause.Where;
import de.tarent.dblayer.sql.statement.Select;
import de.tarent.octopus.PersonalConfigAA;
import de.tarent.octopus.beans.BeanException;
import de.tarent.octopus.beans.Database;
import de.tarent.octopus.beans.Request;
import de.tarent.octopus.beans.TransactionContext;
import de.tarent.octopus.beans.veraweb.DatabaseVeraWeb;
import de.tarent.octopus.beans.veraweb.ListWorkerVeraWeb;
import de.tarent.octopus.server.OctopusContext;

import java.io.IOException;

/**
 * Diese Octopus-Worker-Klasse lädt eine Liste von Firmen,
 * diese werden in dem Popup zur Firmen-Auswahl angezeigt.
 *
 * Details bitte dem {@link de.tarent.octopus.beans.BeanListWorker BeanListWorker} entnehmen.
 *
 * @author Christoph Jerolimov
 */
public class CompanyListWorker extends ListWorkerVeraWeb {
    //
    // Konstruktoren
    //

    /**
     * Der Konstruktor legt den Bean-Namen fest.
     */
    public CompanyListWorker() {
        super("Person");
    }

    //
    // Oberklasse BeanListWorker
    //

    /**
     * Schränkt das Suchergebnis auf nicht gelöschte Firmen ein.
     *
     * @param cntx   octupus context
     * @param select select
     * @throws BeanException beanException
     * @throws IOException   ioException
     */
    @Override
    protected void extendWhere(OctopusContext cntx, Select select) throws BeanException, IOException {
        select.where(Where.and(
          Expr.equal("fk_orgunit", ((PersonalConfigAA) cntx.personalConfig()).getOrgUnitId()), Where.and(
            Expr.equal("deleted", PersonConstants.DELETED_FALSE),
            Expr.equal("iscompany", PersonConstants.ISCOMPANY_TRUE))));
    }

    @Override
    protected void extendColumns(OctopusContext cntx, Select select) throws BeanException, IOException {
        select.orderBy(Order.asc("lastname_a_e1"));
    }

    @Override
    protected Integer getAlphaStart(OctopusContext cntx, String start) throws BeanException, IOException {
        Database database = getDatabase(cntx);

        Where where = Where.and(
          Expr.equal("fk_orgunit", ((PersonalConfigAA) cntx.personalConfig()).getOrgUnitId()), Where.and(
            Expr.equal("deleted", PersonConstants.DELETED_FALSE),
            Expr.equal("iscompany", PersonConstants.ISCOMPANY_TRUE)));

        StringBuffer buffer = new StringBuffer();
        buffer.append("(");
        buffer.append(where.clauseToString());
        buffer.append(") AND lastname_a_e1 < '");
        Escaper.escape(buffer, start);
        buffer.append("'");

        Select select = database.getCount(BEANNAME);
        select.where(new RawClause(buffer));

        Integer i = database.getCount(select);
        return new Integer(i.intValue() - (i.intValue() % getLimit(cntx).intValue()));
    }

    //
    // Octopus-Aktionen
    //
    /**
     * Octopus-Parameter für die Aktion {@link #copyCompanyData(OctopusContext, String, String)}
     */
    public static final String INPUT_copyCompanyData[] = { "company", "companyfield" };

    /**
     * Diese Worker-Aktion lädt einen Person Eintrag und kopiert
     * entsprechende Firmen-Daten in die aktuell geöffnete Person.
     *
     * @param cntx         Octopus-Context
     * @param company      Person-PK der Firma dessen Daten übernommen werden sollen.
     * @param companyfield Name des HTML-Firmenfeldes, zu dem Firmendaten geladen werden sollen.
     * @throws BeanException bean Exception
     * @throws IOException   io Exception
     */
    public void copyCompanyData(OctopusContext cntx, String company, String companyfield) throws BeanException, IOException {
        cntx.setContent("tab", cntx.requestAsObject("tab"));
        Database database2 = new DatabaseVeraWeb(cntx);
        TransactionContext context = database2.getTransactionContext();

        final boolean copyAll = false;

        Request request = getRequest(cntx);
        Database database = getDatabase(cntx);

        Person person = (Person) request.getBean("Person", "person");
        Person personcompany = (Person)
          database.getBean("Person",
            database.getSelect(person).
              where(Expr.equal("pk", company)));
        if (personcompany == null) {
            personcompany = new Person();
        }

        boolean copyBusinessLatin = copyAll || "person-company_a_e1".equals(companyfield);
        boolean copyBusinessExtra1 = copyAll || "person-company_a_e2".equals(companyfield);
        boolean copyBusinessExtra2 = copyAll || "person-company_a_e3".equals(companyfield);
        boolean copyPrivateLatin = copyAll || "person-company_b_e1".equals(companyfield);
        boolean copyPrivateExtra1 = copyAll || "person-company_b_e2".equals(companyfield);
        boolean copyPrivateExtra2 = copyAll || "person-company_b_e3".equals(companyfield);
        boolean copyOtherLatin = copyAll || "person-company_c_e1".equals(companyfield);
        boolean copyOtherExtra1 = copyAll || "person-company_c_e2".equals(companyfield);
        boolean copyOtherExtra2 = copyAll || "person-company_c_e3".equals(companyfield);

        //Name der Firma steht in Lastname. Der wird bei der Person nach Company kopiert
        String companyNameLatin, companyNameExtra1, companyNameExtra2;
        companyNameLatin = personcompany.getMainLatin().getCompany();
        companyNameExtra1 = personcompany.getMainExtra1().getCompany();
        if (AddressHelper.empty(companyNameExtra1)) {
            companyNameExtra1 = companyNameLatin;
        }
        companyNameExtra2 = personcompany.getMainExtra2().getCompany();
        if (AddressHelper.empty(companyNameExtra2)) {
            companyNameExtra2 = companyNameLatin;
        }

        if (copyBusinessLatin || copyBusinessExtra1 || copyBusinessExtra2) {
            AddressHelper.copyAddressData(personcompany.getBusinessLatin(), person.getBusinessLatin(), true, true, true, true);
            person.getBusinessLatin().setCompany(companyNameLatin);
            AddressHelper.copyAddressData(personcompany.getBusinessExtra1(), person.getBusinessExtra1(), true, true, true, true);
            person.getBusinessExtra1().setCompany(companyNameExtra1);
            AddressHelper.copyAddressData(personcompany.getBusinessExtra2(), person.getBusinessExtra2(), true, true, true, true);
            person.getBusinessExtra2().setCompany(companyNameExtra2);
        }

        if (copyPrivateLatin || copyPrivateExtra1 || copyPrivateExtra2) {
            AddressHelper.copyAddressData(personcompany.getBusinessLatin(), person.getPrivateLatin(), true, true, true, true);
            person.getPrivateLatin().setCompany(companyNameLatin);
            AddressHelper.copyAddressData(personcompany.getBusinessExtra1(), person.getPrivateExtra1(), true, true, true, true);
            person.getPrivateExtra1().setCompany(companyNameExtra1);
            AddressHelper.copyAddressData(personcompany.getBusinessExtra2(), person.getPrivateExtra2(), true, true, true, true);
            person.getPrivateExtra2().setCompany(companyNameExtra2);
        }

        if (copyOtherLatin || copyOtherExtra1 || copyOtherExtra2) {
            AddressHelper.copyAddressData(personcompany.getBusinessLatin(), person.getOtherLatin(), true, true, true, true);
            person.getOtherLatin().setCompany(companyNameLatin);
            AddressHelper.copyAddressData(personcompany.getBusinessExtra1(), person.getOtherExtra1(), true, true, true, true);
            person.getOtherExtra1().setCompany(companyNameExtra1);
            AddressHelper.copyAddressData(personcompany.getBusinessExtra2(), person.getOtherExtra2(), true, true, true, true);
            person.getOtherExtra2().setCompany(companyNameExtra2);
        }

        AddressHelper.checkPersonSalutation(person, database, context);
        cntx.setContent("person", person);
        cntx.setContent("showAdressTab", "true");
        cntx.setContent("personAddresstypeTab", cntx.requestAsString("personAddresstypeTab"));
    }
}
