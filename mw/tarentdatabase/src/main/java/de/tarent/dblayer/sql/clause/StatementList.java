package de.tarent.dblayer.sql.clause;

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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import de.tarent.dblayer.engine.DBContext;
import de.tarent.dblayer.engine.SetDbContext;
import de.tarent.dblayer.engine.SetDbContextImpl;
import de.tarent.dblayer.sql.SQL;

/**
 * This class represents literal enumeration parts of a SQL statement.
 *
 * @author Christoph Jerolimov
 */
public final class StatementList extends SetDbContextImpl implements Clause {

    //
    // protected members
    //
    /**
     * separator characters for list entries
     */
    private final static String SEPARATOR = ", ";
    /**
     * literal enumeration
     */
    Collection _list;

    //
    // constructors
    //

    /**
     * This constructor accepts a {@link Collection} as enumeration.
     */
    public StatementList(Collection list) {
        _list = list;
    }

    /**
     * This constructor accepts a {@link Iterator} as enumeration.
     */
    public StatementList(Iterator it) {
        _list = new ArrayList();
        while (it.hasNext()) {
            _list.add(it.next());
        }
    }

    public boolean isEmpty() {
        return _list == null || _list.size() == 0;
    }

    //
    // interface {@link Clause}
    //

    /**
     * This method generates a string representation of the clause model
     * for use in SQL statements.<br>
     * This method should only be used after setting the {@link DBContext}
     * using the {@link SetDbContext#setDBContext(DBContext)} method.
     * Otherwise a default db layer context is assumed which for now is
     * a PostgreSQL DBMS.
     *
     * @return string representation of the clause model
     * @see de.tarent.dblayer.sql.clause.Clause#clauseToString()
     * @deprecated use {@link #clauseToString(DBContext)} instead
     */
    final public String clauseToString() {
        return clauseToString(getDBContext());
    }

    /**
     * This method generates a string representation of the clause model
     * for use in SQL statements.
     *
     * @param dbContext the db layer context to use for formatting parameters
     * @return string representation of the clause model
     * @see de.tarent.dblayer.sql.clause.Clause#clauseToString(DBContext)
     */
    public String clauseToString(DBContext dbContext) {
        StringBuffer sb = new StringBuffer();
        statementListToString(dbContext, this, sb);
        return sb.toString();
    }

    //
    // class {@link Object}
    //

    /**
     * This method generates a string representation of the clause model
     * for use in SQL statements.<br>
     * This method should only be used after setting the {@link DBContext}
     * using the {@link SetDbContext#setDBContext(DBContext)} method.
     * Otherwise a default db layer context is assumed which for now is
     * a PostgreSQL DBMS.
     *
     * @return string representation of the clause model
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return clauseToString(getDBContext());
    }

    //
    // helper methods
    //

    /**
     * This method generates a string representation of a {@link StatementList}
     * for use in SQL statements.<br>
     * This method should only be used after setting the {@link DBContext}
     * using the {@link SetDbContext#setDBContext(DBContext)} method.
     * Otherwise a default db layer context is assumed which for now is
     * a PostgreSQL DBMS.
     *
     * @return string representation of the clause model
     * @deprecated use {@link #statementListToString(DBContext, StatementList, StringBuffer)} instead
     */
    public final static StringBuffer statementListToString(StatementList sl, StringBuffer sb) {
        return statementListToString(sl.getDBContext(), sl, sb);
    }

    /**
     * This method generates a string representation of a {@link StatementList}
     * for use in SQL statements.
     *
     * @param dbContext the db layer context to use for formatting parameters
     * @return string representation of the clause model
     */
    public final static StringBuffer statementListToString(DBContext dbContext, StatementList sl, StringBuffer sb) {
        sb.append("(");
        if (sl._list != null) {
            Iterator it = sl._list.iterator();
            while (it.hasNext()) {
                sb.append(SQL.format(dbContext, it.next()));
                if (it.hasNext()) {
                    sb.append(SEPARATOR);
                }
            }
        }
        sb.append(")");
        return sb;
    }

    /**
     * Returns an independent clone of this statement.
     *
     * @see java.lang.Object#clone()
     */
    public Object clone() {
        try {
            StatementList theClone = (StatementList) super.clone();
            theClone._list = new ArrayList(_list);
            return theClone;
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }
}
