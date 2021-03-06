package de.tarent.dblayer.sql.statement;

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

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import de.tarent.dblayer.engine.DB;
import de.tarent.dblayer.engine.DBContext;
import de.tarent.dblayer.engine.InsertKeys;
import de.tarent.dblayer.engine.SetDbContext;
import de.tarent.dblayer.sql.SQL;
import de.tarent.dblayer.sql.Statement;
import de.tarent.dblayer.sql.SyntaxErrorException;
import de.tarent.dblayer.sql.clause.Expr;

/**
 * This helper class allows to use a single class for both SQL <code>INSERTs</code>
 * and <code>UPDATEs</code>. Only when you want to execute the statement you have
 * to decide what you want to do.
 *
 * @author Wolfgang Klein
 */
public class InsertUpdate extends AbstractStatement {
    //
    // public methods
    //

    /**
     * This method sets the value for the given column of the data record to insert
     * or update.<br>
     * Actually this method only appends the pair (column, value) to a list and
     * doesn't check whether there already is a pair for the same column in the list.
     * Your code will have to keep an eye on this itself.
     *
     * @param column name of the column of the value; only the local part is used
     * @param value  value to insert; this value will be formatted fitting its type
     *               and the {@link DBContext} this {@link InsertUpdate} is operated in.
     * @return this {@link InsertUpdate} instance
     */
    public InsertUpdate add(String column, Object value) {
        _values.put(column, value);
        return this;
    }

    /**
     * This method sets the name of the table to insert into or update.
     */
    public InsertUpdate table(String table) {
        _table = table;
        return this;
    }

    /**
     * This method creates an {@link Insert} {@link Statement} and feeds it
     * with the data already put into this {@link InsertUpdate}.
     *
     * @return an {@link Insert} reflecting this {@link InsertUpdate}
     * @throws SyntaxErrorException
     */
    public Insert insert() throws SyntaxErrorException {
        Insert insert = SQL.Insert(getDBContext()).table(_table);

        Iterator k = _values.keySet().iterator();
        Iterator v = _values.values().iterator();
        while (k.hasNext()) {
            insert.insert((String) k.next(), v.next());
        }
        return insert;
    }

    /**
     * This method creates an {@link Insert} {@link Statement} and feeds it
     * with the data already put into this {@link InsertUpdate}.
     *
     * @return an {@link Insert} reflecting this {@link InsertUpdate}
     * @throws SyntaxErrorException
     */
    public Insert insert(DBContext dbx) throws SyntaxErrorException {
        Insert insert = SQL.Insert(dbx).table(_table);

        Iterator k = _values.keySet().iterator();
        Iterator v = _values.values().iterator();
        while (k.hasNext()) {
            insert.insert((String) k.next(), v.next());
        }
        return insert;
    }

    /**
     * This method creates an {@link Update} {@link Statement} and feeds it
     * with the data already put into this {@link InsertUpdate}.
     *
     * @return an {@link Update} reflecting this {@link InsertUpdate}
     */
    public Update update() {
        Update update = SQL.Update(getDBContext()).table(_table);

        Iterator k = _values.keySet().iterator();
        Iterator v = _values.values().iterator();
        while (k.hasNext()) {
            update.update((String) k.next(), v.next());
        }
        return update;
    }

    /**
     * This method creates an {@link Update} {@link Statement} and feeds it
     * with the data already put into this {@link InsertUpdate}.
     *
     * @return an {@link Update} reflecting this {@link InsertUpdate}
     */
    public Update update(DBContext dbx) {
        Update update = SQL.Update(dbx).table(_table);

        Iterator k = _values.keySet().iterator();
        Iterator v = _values.values().iterator();
        while (k.hasNext()) {
            update.update((String) k.next(), v.next());
        }
        return update;
    }

    /**
     * This method executes the modelled <code>INSERT</code> statement within the
     * db layer pool with the given name and returns a Map with Key "updateCount" and value
     * as the Number of affected rows or the Columns with autogenerated Keys as Keys
     * and the new Key as Value
     *
     * @param pool the connection pool in which to operate.
     */
    public int executeInsert(String pool) throws SQLException {
        if (_values.size() > 0) {
            Insert insert = insert();
            return DB.insert(pool, insert);
        }
        return 0;
    }

    /**
     * This method executes the modelled <code>INSERT</code> statement within the
     * db layer pool with the given name and returns a Map with Key "updateCount" and value
     * as the Number of affected rows or the Columns with autogenerated Keys as Keys
     * and the new Key as Value
     *
     * @param dbx the DBContexst on which to operate.
     */
    public int executeInsert(DBContext dbx) throws SQLException {
        if (_values.size() > 0) {
            Insert insert = insert(dbx);
            return DB.insert(dbx, insert);
        }
        return 0;
    }

    /**
     * This method executes the modelled <code>INSERT</code> statement within the
     * db layer pool with the given name and returns a Map with Key "updateCount" and value
     * as the Number of affected rows or the Columns with autogenerated Keys as Keys
     * and the new Key as Value
     *
     * @param dbx the DBContexst on which to operate.
     */
    public InsertKeys executeInsertKeys(DBContext dbx) throws SQLException {
        if (_values.size() > 0) {
            Insert insert = insert(dbx);
            return DB.insertKeys(dbx, insert);
        }
        return InsertKeys.EMPTY_INSTANCE;
    }

    /**
     * This method executes the modelled <code>UPDATE</code> statement within the
     * db layer pool with the given name and returns the number of updated rows.
     * If the given column name is not <code>null</code> the update is executed
     * only on those data records having the given value in the given column.
     *
     * @param pool     the connection pool in which to operate.
     * @param idColumn name of the column to check for the given value before update
     * @param idValue  value to check the given column for before update
     */
    public void executeUpdate(String pool, String idColumn, Integer idValue) throws SQLException {
        if (_values.size() > 0) {
            Update update = update();
            if (idColumn != null) {
                update.where(Expr.equal(idColumn, idValue));
            }
            DB.update(pool, update);
        }
    }

    /**
     * This method executes the modelled <code>UPDATE</code> statement within the
     * db layer pool with the given name and returns the number of updated rows.
     * If the given column name is not <code>null</code> the update is executed
     * only on those data records having the given value in the given column.
     *
     * @param dbx      the DBContext on which to operate
     * @param idColumn name of the column to check for the given value before update
     * @param idValue  value to check the given column for before update
     */
    public void executeUpdate(DBContext dbx, String idColumn, Integer idValue) throws SQLException {
        if (_values.size() > 0) {
            Update update = update();
            if (idColumn != null) {
                update.where(Expr.equal(idColumn, idValue));
            }
            DB.update(dbx, update);
        }
    }

    //
    // interface {@link Statement}
    //

    /**
     * This method executes the modelled <code>INSERT</code> statement within the
     * {@link DBContext} of this {@link InsertUpdate} instance.<br>
     * This method should only be used after setting the {@link DBContext}
     * using the {@link SetDbContext#setDBContext(DBContext)} method.
     * Otherwise a default db layer context is assumed which for now is
     * a PostgreSQL DBMS.<br>
     * This method actually calls {@link #executeInsert(String)} using the pool name
     * of the {@link DBContext}.
     *
     * @return the number of inserted rows
     * @see Statement#execute()
     */
    public Object execute() throws SQLException {
        return new Integer(executeInsert(getDBContext()));
    }

    /**
     * This method executes the modelled <code>INSERT</code> statement within the
     * {@link DBContext} of this {@link InsertUpdate} instance.<br>
     * This method should only be used after setting the {@link DBContext}
     * using the {@link SetDbContext#setDBContext(DBContext)} method.
     * Otherwise a default db layer context is assumed which for now is
     * a PostgreSQL DBMS.<br>
     * This method actually calls {@link #executeInsert(String)} using the pool name
     * of the {@link DBContext}.
     *
     * @return the number of inserted rows
     * @see Statement#execute()
     */
    public Object execute(DBContext dbx) throws SQLException {
        return new Integer(executeInsert(dbx));
    }

    /**
     * This method generally creates the {@link DBContext} sensitive {@link String}
     * representation of the modelled SQL {@link Statement}. In this case though
     * it just returns <code>null</code> as it is not clear which kind of
     * statement to use here.
     *
     * @see Statement#statementToString()
     */
    public String statementToString() throws SyntaxErrorException {
        return null;
    }

    //
    // protected member variables
    //
    /**
     * the name of the table to insert into or update
     */
    private String _table;
    /**
     * mapping column name ({@link String}) to value object representing the data to insert or update
     */
    private final Map _values = new HashMap();
}
