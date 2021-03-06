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

import de.tarent.octopus.server.OctopusContext;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Diese Klasse stellt einen Octopus-Worker dar, der Anwendungskontexte verwalten kann.
 * Anwendungskontexte bestehen aus einem Task und einer Menge Bezeichner mit zugeordneten
 * Objekten. Solche Kontexte können auf einen Stapel ge-PUSH-t und von ihm ge-POP-t werden.<br>
 * TODO: Fertig umsetzen
 *
 * @author mikel
 */
public class ContextWorker {
    //
    // Octopus-Aktionen
    //
    /**
     * Eingabe-Parameter für die Octopus-Aktion {@link #pop(OctopusContext, String)}
     */
    public static final String[] INPUT_pop = { "contexts" };
    /**
     * Eingabe-Parameterzwang für die Octopus-Aktion {@link #pop(OctopusContext, String)}
     */
    public static final boolean[] MANDATORY_pop = { false };
    /**
     * Ausgabe-Parameter für die Octopus-Aktion {@link #pop(OctopusContext, String)}
     */
    public static final String OUTPUT_pop = "contexts";

    /**
     * Diese Octopus-Action holt den obersten Kontext aus der Serialisierung eines
     * Kontext-Stacks und setzt passend Werte im aktuellen Octopus-Content; dies
     * betrifft einerseits die enthaltenen Werte, die unter dem assoziierten Bezeichner
     * gesetzt werden, und andererseits den Tasknamen, der unter dem Bezeichner
     * "context" dort abgelegt wird, so dass ein im laufenden Task nachfolgender
     * Aufruf <code>&lt;doTask name="{context}"/&gt;</code> in den Kontext-Pfad
     * springt.
     *
     * @param oc       aktueller Octopus-Kontext
     * @param contexts Serialisierung eines Kontext-Stacks
     * @return Serialisierung des Kontext-Stacks ohne den bisherig obersten Kontext
     */
    public String pop(OctopusContext oc, String contexts) {
        StringBuffer contextsBuffer = new StringBuffer(contexts == null ? "" : contexts);
        Context context = Context.pop(contextsBuffer);
        if (context != null) {
            context.set(oc);
        }
        return contextsBuffer.toString();
    }

    /**
     * Eingabe-Parameter für die Octopus-Aktion {@link #push(OctopusContext, String, List)}
     */
    public static final String[] INPUT_push = { "contexts", "push" };
    /**
     * Eingabe-Parameterzwang für die Octopus-Aktion {@link #push(OctopusContext, String, List)}
     */
    public static final boolean[] MANDATORY_push = { false, false };
    /**
     * Ausgabe-Parameter für die Octopus-Aktion {@link #push(OctopusContext, String, List)}
     */
    public static final String OUTPUT_push = "contexts";

    /**
     * Diese Octopus-Aktion legt einen neuen Kontext-Eintrag auf den übergebenen
     * serialisierten Kontext-Stack. In diesem befinden sich der übergebene Taskname
     * und die aktuellen Werte der Octopus-Content-Variablen unter den übergebenen
     * Bezeichnern.
     *
     * @param oc       Octopus-Kontext
     * @param contexts Serialisierung eines Kontext-Stacks
     * @param data     Liste, deren erster Eintrag als Taskname und deren weitere Einträge
     *                 als Bezeichner von Octopus-Content-Variablen interpretiert werden, deren Werte
     *                 als Teil des Kontexts gesichert werden sollen.
     * @return Serialisierung des Kontext-Stacks erweitert um den übergebenen Kontext.
     */
    public String push(OctopusContext oc, String contexts, List data) {
        if (data == null || data.isEmpty()) {
            return contexts;
        }
        StringBuffer contextsBuffer = new StringBuffer(contexts == null ? "" : contexts);
        Context context = new Context(oc, data);
        context.push(contextsBuffer);
        return contextsBuffer.toString();
    }

    //
    // interne Methoden
    //

    //
    // interne Klassen
    //

    /**
     * Diese Klasse stellt einen Kontext mit Tasknamen und Sammlung von Bezeichnern
     * mit assoziierten Werten dar.
     */
    public static class Context {
        //
        // Konstruktoren
        //

        /**
         * Dieser Konstruktor führt keine weiteren Initialisierungen durch; ein hiermit
         * erstellter {@link ContextWorker.Context Kontext} muß noch befüllt werden,
         * zum Beispiel mittels eines {@link #pop(StringBuffer)}-Aufrufs.
         */
        public Context() {
        }

        /**
         * Dieser Konstruktor legt den Kontext-Task fest, speichert jedoch keine Werte
         * aus dem Octopus-Content.
         *
         * @param context Name des Kontext-Tasks
         */
        public Context(String context) {
            this();
            this.context = context;
        }

        /**
         * Dieser Konstruktor legt Kontext-Task und -Werte fest.
         *
         * @param oc   Octopus-Kontext, aus dem Octopus-Content-Werte gelesen werden sollen.
         * @param data Liste, deren erster Eintrag als Taskname und deren weitere Einträge
         *             als Bezeichner von Octopus-Content-Variablen interpretiert werden, deren Werte
         *             in den Kontext übernommen werden sollen.
         */
        public Context(OctopusContext oc, List data) {
            this();
            if (data != null && data.size() > 0) {
                Iterator it = data.iterator();
                context = safeToString(it.next());
                if (it.hasNext()) {
                    values = new HashMap();
                    do {
                        String key = safeToString(it.next());
                        if (key != null) {
                            values.put(key, oc.contentAsObject(key));
                        }
                    } while (it.hasNext());
                }
            }
        }
        //
        // Öffentliche Methoden
        //

        /**
         * Name des Kontext-Tasks
         *
         * @return context
         */
        public String getContextTask() {
            return context;
        }

        /**
         * Diese Methode setzt im Content des übergebenen Octopus-Kontexts diesen
         * Kontext ein; dies betrifft einerseits die enthaltenen Werte, die unter
         * dem assoziierten Bezeichner gesetzt werden, und andererseits den Tasknamen,
         * der unter dem Bezeichner "context" dort abgelegt wird, so dass ein im
         * laufenden Task nachfolgender Aufruf <code>&lt;doTask name="{context}"/&gt;</code>
         * in den Kontext-Pfad springt.<br>
         * TODO: Setzen der Variablen
         *
         * @param oc Octopus-Kontext, in dem dieser {@link ContextWorker.Context Kontext}
         *           gesetzt werden soll
         */
        public void set(OctopusContext oc) {
            oc.setContent("context", getContextTask());
        }

        /**
         * Diese Methode holt den obersten Kontext aus der Serialisierung eines
         * Kontext-Stacks und entfernt diesen aus der Serialisierung.<br>
         * TODO: Werte übernehmen
         *
         * @param contexts Serialisierung eines Kontext-Stacks, wird um den obersten
         *                 Eintrag verringert
         * @return vom Stack geholter {@link ContextWorker.Context Kontext}.
         */
        public static Context pop(StringBuffer contexts) {
            if (contexts == null || contexts.length() == 0) {
                return null;
            }
            int colon = contexts.lastIndexOf(":");
            String context;
            if (colon < 0) {
                context = contexts.toString();
                contexts.setLength(0);
            } else {
                context = contexts.substring(colon + 1);
                contexts.setLength(colon);
            }
            return new Context(context);
        }

        /**
         * Diese Methode ergänzt die übergebene Serialisierung eines Kontext-Stacks
         * um diesen {@link ContextWorker.Context Kontext}.<br>
         * TODO: Werte einfügen
         *
         * @param contexts Serialisierung eines Kontext-Stacks, wird um den diesen
         *                 Eintrag erweitert
         */
        public void push(StringBuffer contexts) {
            if (contexts == null) {
                return;
            }
            if (contexts.length() != 0) {
                contexts.append(':');
            }
            contexts.append(context);
        }

        String context = null;
        Map values = null;

        final static String safeToString(Object orig) {
            return orig == null ? null : orig.toString();
        }
    }
}
