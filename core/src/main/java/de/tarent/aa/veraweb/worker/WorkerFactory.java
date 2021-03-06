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

import de.tarent.octopus.content.TcContentWorker;
import de.tarent.octopus.content.TcContentWorkerFactory;
import de.tarent.octopus.content.TcReflectedWorkerWrapper;
import de.tarent.octopus.server.OctopusContext;
import de.tarent.octopus.server.WorkerCreationException;

/**
 * Diese Klasse gibt Instanzen anderer Worker und direktem Arbeiten mit
 * diesen zurück. Dadurch verlaggert sich die Verwendung von statischen
 * Methoden ausschließlich in diese Klasse.
 *
 * @author Christoph
 */
public class WorkerFactory {
    private WorkerFactory() {
    }

    /**
     * Diese Methode liefert den Worker namens "PersonCategorieWorker".
     *
     * @param cntx Octopus-Kontext
     * @return {@link PersonCategorieWorker}-Instanz des Moduls
     */
    static public PersonCategorieWorker getPersonCategorieWorker(OctopusContext cntx) {
        return (PersonCategorieWorker) getWorker(cntx, "PersonCategorieWorker");
    }

    /**
     * Diese Methode liefert den Worker namens "PersonDetailWorker".
     *
     * @param cntx Octopus-Kontext
     * @return {@link PersonDetailWorker}-Instanz des Moduls
     */
    static public PersonDetailWorker getPersonDetailWorker(OctopusContext cntx) {
        return (PersonDetailWorker) getWorker(cntx, "PersonDetailWorker");
    }

    /**
     * Diese Methode liefert den Worker namens "PersonDuplicateSearchWorker".
     *
     * @param cntx Octopus-Kontext
     * @return {@link PersonDuplicateSearchWorker}-Instanz des Moduls
     */
    static public PersonDuplicateSearchWorker getPersonDuplicateSearchWorker(OctopusContext cntx) {
        return (PersonDuplicateSearchWorker) getWorker(cntx, "PersonDuplicateSearchWorker");
    }

    /**
     * Diese Methode liefert den Worker namens "PersonListWorker".
     *
     * @param cntx Octopus-Kontext
     * @return {@link PersonListWorker}-Instanz des Moduls
     */
    static public PersonListWorker getPersonListWorker(OctopusContext cntx) {
        return (PersonListWorker) getWorker(cntx, "PersonListWorker");
    }

    /**
     * Diese Methode liefert den Worker namens "ImportPersonsWorker".
     *
     * @param cntx Octopus-Kontext
     * @return {@link ImportPersonsWorker}-Instanz des Moduls
     */
    static public ImportPersonsWorker getImportPersonsWorker(OctopusContext cntx) {
        return (ImportPersonsWorker) getWorker(cntx, "ImportPersonsWorker");
    }

    /**
     * Diese Methode liefert den Worker namens "DataExchangeWorker".
     *
     * @param cntx Octopus-Kontext
     * @return {@link DataExchangeWorker}-Instanz des Moduls
     */
    static public DataExchangeWorker getDataExchangeWorker(OctopusContext cntx) {
        return (DataExchangeWorker) getWorker(cntx, "DataExchangeWorker");
    }

    /**
     * Diese Methode liefert den Worker namens "ConfigWorker".
     *
     * @param cntx Octopus-Kontext
     * @return {@link ConfigWorker}-Instanz des Moduls
     */
    static public ConfigWorker getConfigWorker(OctopusContext cntx) {
        return (ConfigWorker) getWorker(cntx, "ConfigWorker");
    }

    /**
     * Diese Methode liefert den Worker namens "VerifyWorker".
     *
     * @param cntx Octopus-Kontext
     * @return {@link VerifyWorker}-Instanz des Moduls
     */
    static public VerifyWorker getVerifyWorker(OctopusContext cntx) {
        return (VerifyWorker) getWorker(cntx, "VerifyWorker");
    }

    /**
     * Gets a new instance of the {@link GuestDetailWorker}.
     *
     * @param cntx the current octopus context
     * @return instance of {@link GuestDetailWorker}
     * @since 1.2.0
     */
    static public GuestDetailWorker getGuestDetailWorker(OctopusContext cntx) {
        return (GuestDetailWorker) getWorker(cntx, "GuestDetailWorker");
    }

    /**
     * Diese Methode liefert den Worker namens "GuestListWorker".
     *
     * @param cntx Octopus-Kontext
     * @return {@link GuestListWorker}-Instanz des Moduls
     */
    static public GuestListWorker getGuestListWorker(OctopusContext cntx) {
        return (GuestListWorker) getWorker(cntx, "GuestListWorker");
    }

    /**
     * Diese Methode liefert den Worker namens "GuestWorker".
     *
     * @param cntx Octopus-Kontext
     * @return {@link GuestWorker}-Instanz des Moduls
     */
    static public GuestWorker getGuestWorker(OctopusContext cntx) {
        return (GuestWorker) getWorker(cntx, "GuestWorker");
    }

    /**
     * Diese Methode liefert den Worker namens "LocationListWorker".
     *
     * @param cntx Octopus-Kontext
     * @return {@link LocationListWorker}-Instanz des Moduls
     */
    static public LocationListWorker getLocationListWorker(OctopusContext cntx) {
        return (LocationListWorker) getWorker(cntx, "LocationListWorker");
    }

    /**
     * Diese Methode liefert den Worker namens "LocationWorker".
     *
     * @param cntx Octopus-Kontext
     * @return {@link WorkAreaWorker}-Instanz des Moduls
     */
    static public WorkAreaWorker getWorkAreaWorker(OctopusContext cntx) {
        return (WorkAreaWorker) getWorker(cntx, "WorkAreaWorker");
    }

    /**
     * Diese statische Methode holt mittels des Octopus-Kontexts einen Worker.
     *
     * @param cntx Octopus-Kontext
     * @param name Worker-Name
     * @return Worker-Instanz
     */
    static private Object getWorker(OctopusContext cntx, String name) {
        try {
            TcContentWorker worker = TcContentWorkerFactory.getContentWorker(
              cntx.moduleConfig(), name,
              cntx.getRequestObject().getRequestID());
            return (worker instanceof TcReflectedWorkerWrapper) ?
              ((TcReflectedWorkerWrapper) worker).getWorkerDelegate() : worker;
        } catch (WorkerCreationException e) {
        }
        return null;
    }
}
