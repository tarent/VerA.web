package de.tarent.octopus.beans.veraweb;

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

import de.tarent.aa.veraweb.beans.ChangeLogEntry;
import de.tarent.aa.veraweb.beans.Event;
import de.tarent.aa.veraweb.beans.Guest;
import de.tarent.aa.veraweb.beans.Person;
import de.tarent.aa.veraweb.beans.Task;
import de.tarent.dblayer.sql.statement.Insert;
import de.tarent.octopus.beans.Bean;
import de.tarent.octopus.beans.BeanException;
import de.tarent.octopus.beans.Database;
import de.tarent.octopus.beans.ExecutionContext;
import de.tarent.octopus.beans.TransactionContext;

import java.io.IOException;
import java.sql.Date;
import java.util.Iterator;

/**
 * The class BeanChangeLogger represents a facility
 * for logging changes to individual beans.
 *
 * The changes logged will be written to the same
 * database. For this to work, the database schema
 * must include the following schema extensions:
 *
 * CREATE TABLE tchangelog
 * (
 * pk serial NOT NULL,
 * username varchar(100) NOT NULL,
 * objtype varchar(30) NOT NULL,		-- the type of the object
 * objid int4 NOT NULL,				-- the id of the object
 * op varchar(6) NOT NULL,				-- the sql operation: insert|delete|update
 * attributes text NOT NULL,			-- a list of comma separated attribute names
 * date timestamptz,
 * CONSTRAINT tchangelog_pkey PRIMARY KEY (pk)
 * );
 *
 * In order to create an actual entry in the log, the ChangeLogEntry bean will
 * be used for storing the information to and for retrieving the informatiom
 * from the database.
 *
 * @author cklein
 * @since 1.2
 */
public class BeanChangeLogger {

    private Database database;
    private TransactionContext context;

    /**
     * Creates a new instance of the logger.
     *
     * @param database the database to which we will be logging changes to
     * @param context  the current transaction context
     */
    public BeanChangeLogger(Database database, TransactionContext context) {
        this.database = database;
        this.context = context;
    }

    public BeanChangeLogger(Database database) {
        this.database = database;
    }

    /**
     * Logs the update change to the database. if any.
     * For this, the method will compare the old version o and the new
     * version n of a bean of the same class.
     *
     * @param username the username who is committing the change
     * @param o        the bean's old state from the database
     * @param n        the bean's new state from the request
     */
    @SuppressWarnings("unchecked")
    public void logUpdate(String username, Bean o, Bean n) throws BeanException, IOException {
        // FIXME dirty hack due to some routines taking too much memory, resulting
        // in null objects being passed
        if (o == null || n == null) {
            return;
        }
        if (!o.getClass().equals(n.getClass())) {
            throw new IllegalArgumentException("Beans o and n must be of the same type.");
        }

        // compile a comma separated list of attributes that were changed
        // from version o to version n of the bean.
        StringBuffer changedAttributes = new StringBuffer();
        Iterator<String> i = o.getFields().iterator();
        while (i.hasNext()) {
            String k = i.next();

            if (k.compareTo("id") == 0) {
                // we skip the primary key a/o id field
                continue;
            }

            Comparable nv = (Comparable) n.getField(k);
            Comparable ov = (Comparable) o.getField(k);
            try {
                if
                  (
                  ((ov != null) && (ov.compareTo(nv) != 0))
                    || ((ov == null) && (nv != null))
                  ) {
                    if (changedAttributes.length() > 0) {
                        changedAttributes.append(',');
                    }
                    changedAttributes.append(k);
                }
            } catch (NullPointerException e) {
                // just catch
            }
        }

        // are there any differences from o to n?
        if (changedAttributes.length() > 0) {
            ChangeLogEntry entry =
              this.createNewChangeLogEntryInstance("update", username, determineObjectName(o), o.getClass().getName(),
                (Integer) o.getField("id"), changedAttributes.toString());
            this.insertLogEntry(entry);
        }
    }

    /**
     * Logs the insert change to the database. if any.
     *
     * @param username the username who is committing the change
     * @param o        the bean's old state from the database
     */
    public void logInsert(String username, Bean o) throws BeanException, IOException {
        // FIXME dirty hack due to some routines taking too much memory, resulting
        // in null objects being passed
        if (o == null) {
            return;
        }
        ChangeLogEntry entry =
          this.createNewChangeLogEntryInstance("insert", username, determineObjectName(o), o.getClass().getName(),
            (Integer) o.getField("id"),
            "*");
        this.insertLogEntry(entry);
    }

    /**
     * Logs the delete change to the database.
     *
     * @param username the username who is committing the change
     * @param o        the bean's old state from the database
     */
    public void logDelete(String username, Bean o) throws BeanException, IOException {
        // FIXME dirty hack due to some routines taking too much memory, resulting
        // in null objects being passed
        if (o == null) {
            return;
        }
        ChangeLogEntry entry =
          this.createNewChangeLogEntryInstance("delete", username, determineObjectName(o), o.getClass().getName(),
            (Integer) o.getField("id"),
            "*");
        this.insertLogEntry(entry);
    }

    private String determineObjectName(Bean o) throws BeanException, IOException {
        String result = "";

        if (o instanceof Event) {
            result = ((Event) o).shortname; // shortname is mandatory
        } else {
            if (o instanceof Guest) {
                o = this.database.getBean("Person", ((Guest) o).person);
            }
            if (o instanceof Person) {
                Person p = (Person) o;
                if (p != null) {
                    result = p.lastname_a_e1; // lastname is mandatory, even for companies
                    if (p.firstname_a_e1 != null) {
                        result += ", " + p.firstname_a_e1;
                    }
                }
            } else if (o instanceof Task) {
                Task task = (Task) o;
                if (task != null) {
                    result = task.title; // title is mandatory
                }
            }
        }
        if (result == null || result.length() == 0) {
            result = "Kein Name vergeben";
        }

        return result;
    }

    private void insertLogEntry(ChangeLogEntry entry)
      throws BeanException, IOException {
        entry.verify();
        if (entry.isCorrect()) {
            ExecutionContext c = this.database;
            if (this.context != null) {
                c = this.context;
            }

            this.database.getNextPk(entry, c);
            Insert insert = this.database.getInsert(entry);
            c.execute(insert);
        }
    }

    private ChangeLogEntry createNewChangeLogEntryInstance(String op, String username, String oname, String otype, Integer oid,
      String attributes)
      throws BeanException, IOException {
        ChangeLogEntry result = new ChangeLogEntry();
        result.username = username;
        result.objectname = oname;
        result.objecttype = otype;
        result.objectid = oid;
        result.op = op;
        result.attributes = attributes;
        result.created = new Date(System.currentTimeMillis());
        return result;
    }
}
