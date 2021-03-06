package de.tarent.aa.veraweb.beans;

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

import de.tarent.aa.veraweb.utils.VerawebUtils;
import de.tarent.dblayer.sql.Escaper;
import de.tarent.dblayer.sql.clause.Expr;
import de.tarent.dblayer.sql.clause.RawClause;
import de.tarent.dblayer.sql.clause.WhereList;

/**
 * Bean zum Filtern einer Gästeliste und navigieren innerhalb der Detailansicht.
 *
 * Entspricht keiner Datenbank-Tabelle und wird ausschließlich in der Session
 * gehalten oder aus dem Request geladen.
 */
public class GuestSearch extends AbstractBean {
    public Integer event;
    public Integer offset;
    public Integer count;
    /**
     * Gibt an ob nach Platz / Reserve gefiltert werden soll.
     */
    public Integer reserve;
    /**
     * Gibt an ob nach Offene / Zusagen / Absagen gefiltert werden soll.
     */
    public Integer invitationstatus;
    /**
     * Gibt an in welcher Sortierreihenfolge die Gästeliste angezeigt werden
     * soll.
     */
    public String listorder; // orderno, lastname, firstname, email
    public String lastlistorder;
    public String sortDirection; // ASC, DESC
    /**
     * Gibt an nach welcher kategorie gefiltert werden soll
     */
    public String category;
    public Boolean sortList;
    public String keywords;

    public void addGuestListFilter(WhereList where) {
        where.addAnd(Expr.equal("tguest.fk_event", this.event));

        if (this.category != null && !this.category.trim().equals("")) {
            where.addAnd(
              new RawClause("fk_category IN (SELECT pk FROM veraweb.tcategorie WHERE catname = '" +
                Escaper.escape(this.category) + "')"));
        }

        if (this.reserve != null) {
            switch (this.reserve.intValue()) {
            case 1:
                where.addAnd(Expr.nullOrInt0("reserve"));
                break;
            case 2:
                where.addAnd(Expr.equal("reserve", new Integer(1)));
                break;
            }
        }

        if (this.invitationstatus != null) {
            switch (this.invitationstatus.intValue()) {
            case 1:
                // nur Offen
                where.addAnd(new RawClause("(" +
                  // Mit Partner
                  "(invitationtype = 1 AND (invitationstatus IS NULL OR invitationstatus=0 OR "
                  + "invitationstatus_p IS NULL OR invitationstatus_p=0)) OR" +
                  // Ohne Partner
                  "(invitationtype = 2 AND (invitationstatus IS NULL OR invitationstatus=0)) OR" +
                  // Nur Partner
                  "(invitationtype = 3 AND (invitationstatus_p IS NULL OR invitationstatus_p=0))" + ")"));
                break;
            case 2:
                // nur Zusagen
                where.addAnd(new RawClause("(" +
                  // Mit Partner
                  "(invitationtype = 1 AND (invitationstatus=1 OR invitationstatus_p=1)) OR" +
                  // Ohne Partner
                  "(invitationtype = 2 AND invitationstatus=1) OR" +
                  // Nur Partner
                  "(invitationtype = 3 AND invitationstatus_p=1)" + ")"));
                break;
            case 3:
                // nur Absagen
                where.addAnd(new RawClause("(" +
                  // Mit Partner
                  "(invitationtype = 1 AND (invitationstatus=2 OR invitationstatus_p=2)) OR" +
                  // Ohne Partner
                  "(invitationtype = 2 AND invitationstatus=2) OR" +
                  // Nur Partner
                  "(invitationtype = 3 AND invitationstatus_p=2)" + ")"));
                break;
            case 4:
                // nur Teilnahmen
                where.addAnd(new RawClause("(" +
                  // Mit Partner
                  "(invitationtype = 1 AND (invitationstatus=3 OR invitationstatus_p=3)) OR" +
                  // Ohne Partner
                  "(invitationtype = 2 AND invitationstatus=3) OR" +
                  // Nur Partner
                  "(invitationtype = 3 AND invitationstatus_p=3)" + ")"));
                break;
            }
        }
        if (this.keywords != null && !this.keywords.trim().isEmpty()) {
            final String[] words = VerawebUtils.splitKeywords(keywords);
            for (String word : words) {
                where.addAnd(Expr.like("tguest.keywords", "%" + word + "%"));
            }
        }
    }
}
