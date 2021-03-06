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

import de.tarent.dblayer.engine.DBContext;

import java.util.Collection;
import java.util.Iterator;

/**
 * This utility class serves as a factory for {@link Where} {@link Clause}
 * parts.
 *
 * @author Wolfgang Klein
 */
public class Expr {
    //
    // public constants
    //
    /**
     * the String "<code>=</code>"
     */
    final static public String EQUAL = "=";
    /**
     * the String "<code>!=</code>"
     */
    final static public String NOTEQUAL = "!=";
    /**
     * the String "<code>&lt;</code>"
     */
    final static public String LESS = "<";
    /**
     * the String "<code>&lt;=</code>"
     */
    final static public String LESSOREQUAL = "<=";
    /**
     * the String "<code>&gt;</code>"
     */
    final static public String GREATER = ">";
    /**
     * the String "<code>&gt;=</code>"
     */
    final static public String GREATEROREQUAL = ">=";
    /**
     * the String "<code> LIKE </code>"
     */
    final static public String LIKE = " LIKE ";
    /**
     * the String "<code> NOT LIKE </code>"
     */
    final static public String NOTLIKE = " NOT LIKE ";
    /**
     * the String "<code> ~ </code>"
     */
    final static public String REGEX = " ~ ";
    /**
     * the String "<code> !~ </code>"
     */
    final static public String NOTREGEX = " !~ ";
    /**
     * the String "<code> ~* </code>"
     */
    final static public String REGEX_I = " ~* ";
    /**
     * the String "<code> !~* </code>"
     */
    final static public String NOTREGEX_I = " !~* ";
    /**
     * the String "<code> IS NULL</code>"
     */
    final static public String ISNULL = " IS NULL";
    /**
     * the String "<code> IS NOT NULL</code>"
     */
    final static public String ISNOTNULL = " IS NOT NULL";
    /**
     * the String "<code> IN </code>"
     */
    final static public String IN = " IN ";
    /**
     * the String "<code> NOT IN </code>"
     */
    final static public String NOTIN = " NOT IN ";
    /**
     * the String "<code> EXISTS </code>"
     */
    final static public String EXISTS = " EXISTS ";
    /**
     * the String "<code>NOT EXISTS </code>"
     */
    final static public String NOTEXISTS = " NOT EXISTS ";

    //
    // public static factory methods
    //

    /**
     * This method returns a {@link Where} {@link Clause} testing for
     * equality of a column's content and a value.
     */
    static public Where equal(String column, Object value) {
        return new Where(column, value, EQUAL);
    }

    /**
     * This method returns a {@link Where} {@link Clause} testing for
     * equality of the values of two columns.
     */
    static public Where equalColumns(String column, String referenceColumn) {
        return new Where(column, referenceColumn, EQUAL, true);
    }

    /**
     * This method returns a {@link Where} {@link Clause} testing for
     * inequality of a column's content and a value.
     */
    static public Where notEqual(String column, Object value) {
        return new Where(column, value, NOTEQUAL);
    }

    /**
     * This method returns a {@link Where} {@link Clause} testing whether
     * a column's content is less than a value.
     */
    static public Where less(String column, Object value) {
        return new Where(column, value, LESS);
    }

    /**
     * This method returns a {@link Where} {@link Clause} testing whether
     * a column's content is less than or equal to a value.
     */
    static public Where lessOrEqual(String column, Object value) {
        return new Where(column, value, LESSOREQUAL);
    }

    /**
     * This method returns a {@link Where} {@link Clause} testing whether
     * a column's content is greater than a value.
     */
    static public Where greater(String column, Object value) {
        return new Where(column, value, GREATER);
    }

    /**
     * This method returns a {@link Where} {@link Clause} testing whether
     * a column's content is greater than or equal to a value.
     */
    static public Where greaterOrEqual(String column, Object value) {
        return new Where(column, value, GREATEROREQUAL);
    }

    /**
     * This method returns a {@link Where} {@link Clause} testing whether
     * a column's content is like a value.
     */
    static public Where like(String column, Object value) {
        return new Where(column, value, LIKE);
    }

    /**
     * This method returns a {@link Where} {@link Clause} testing whether
     * a column's content is unlike a value.
     */
    static public Where notLike(String column, Object value) {
        return new Where(column, value, NOTLIKE);
    }

    /**
     * This method returns a {@link Where} {@link Clause} testing whether
     * a column’s content matches a regular expression, case-sensitively.
     * This is PostgreSQL-specific.
     */
    static public Where regex(String column, Object value) {
        return new Where(column, value, REGEX);
    }

    /**
     * This method returns a {@link Where} {@link Clause} testing whether
     * a column’s content does not match a regular expression, case-sensitively.
     * This is PostgreSQL-specific.
     */
    static public Where notRegex(String column, Object value) {
        return new Where(column, value, NOTREGEX);
    }

    /**
     * This method returns a {@link Where} {@link Clause} testing whether
     * a column’s content matches a regular expression, case-insensitively.
     * This is PostgreSQL-specific.
     */
    static public Where regexI(String column, Object value) {
        return new Where(column, value, REGEX_I);
    }

    /**
     * This method returns a {@link Where} {@link Clause} testing whether
     * a column’s content does not match a regular expression, case-insensitively.
     * This is PostgreSQL-specific.
     */
    static public Where notRegexI(String column, Object value) {
        return new Where(column, value, NOTREGEX_I);
    }

    /**
     * This method returns a {@link Where} {@link Clause} testing whether
     * a column's content is <code>NULL</code>.
     */
    static public Where isNull(String column) {
        return new Where(column, null, ISNULL);
    }

    /**
     * This method returns a {@link Where} {@link Clause} testing whether
     * a column's content is <code>NULL</code> or a numeric <code>0</code>.
     */
    static public Where nullOrInt0(String column) {
        return
          Where.or(
            Expr.isNull(column),
            Expr.equal(column, new Integer(0))
          );
    }

    /**
     * This method returns a {@link Where} {@link Clause} testing whether
     * a column's content is <code>NULL</code> or <code>'0'</code>.
     */
    static public Where nullOrString0(String column) {
        return
          Where.or(
            Expr.isNull(column),
            Expr.equal(column, "0")
          );
    }

    /**
     * This method returns a {@link Where} {@link Clause} testing whether
     * a column's content is not <code>NULL</code>.
     */
    static public Where isNotNull(String column) {
        return new Where(column, null, ISNOTNULL);
    }

    /**
     * This method returns a {@link Where} {@link Clause} testing whether
     * a column's content is in the given {@link Collection}.
     */
    static public Where in(String column, Collection list) {
        StatementList sList = new StatementList(list);
        if (sList.isEmpty()) {
            return falseExpression();
        }
        return new Where(column, sList, IN);
    }

    /**
     * This method returns a {@link Where} {@link Clause} testing whether
     * a column's content is in the given {@link Collection}. In contrast to in(),
     * this method trys to use (e.g. db) specific optimizations for the handling of the in list.
     *
     * <p><b>MSSQL:</b> This method was introduced for MSSQL, which has problems with long IN lists. For MSSQL
     * a special funtion tarent_database_csvtable('2,3,5,6,8,42') has to be available in the same schema as the query.
     * This function has to return a table with the content of a comma separated list.</p>
     */
    static public Where optimizedIn(DBContext dbc, String column, Collection list) {
        if (list.isEmpty()) {
            return falseExpression();
        }

        return in(column, list);
    }

    /**
     * This method returns a {@link Where} {@link Clause} testing whether
     * a column's content is in the collection represented by the given
     * {@link Iterator}.
     */
    static public Where in(String column, Iterator it) {
        StatementList sList = new StatementList(it);
        if (sList.isEmpty()) {
            return falseExpression();
        }
        return new Where(column, sList, IN);
    }

    /**
     * This method returns a {@link Where} {@link Clause} testing whether
     * a column's content is in a collection represented by a generic
     * object.
     */
    static public Where in(String column, Object value) {
        return new Where(column, value, IN);
    }

    /**
     * This method returns a {@link Where} {@link Clause} testing whether
     * a column's content is not in the given {@link Collection}.
     */
    static public Where notIn(String column, Collection list) {
        StatementList sList = new StatementList(list);
        if (sList.isEmpty()) {
            return trueExpression();
        }
        return new Where(column, sList, NOTIN);
    }

    /**
     * This method returns a {@link Where} {@link Clause} testing whether
     * a column's content is not in the collection represented by the given
     * {@link Iterator}.
     */
    static public Where notIn(String column, Iterator it) {
        StatementList sList = new StatementList(it);
        if (sList.isEmpty()) {
            return trueExpression();
        }
        return new Where(column, it, NOTIN);
    }

    /**
     * This method returns a {@link Where} {@link Clause} testing whether
     * a column's content is not in a collection represented by a generic
     * object.
     */
    static public Where notIn(String column, Object value) {
        return new Where(column, value, NOTIN);
    }

    /**
     * This method returns a {@link Where} {@link Clause} testing if the given subselect
     * returns at least one row.
     */
    static public Where exists(SubSelect select) {
        return new Where(select, EXISTS);
    }

    /**
     * This method returns a {@link Where} {@link Clause} testing if the given subselect
     * returns at least one row and negates the statement.
     */
    static public Where notExists(SubSelect select) {
        return new Where(select, NOTEXISTS);
    }

    /**
     * This method returns a {@link Where} {@link Clause} which always is <b>true</b>.
     */
    static public Where trueExpression() {
        return new Where(new RawClause("1 = 1"), null);
    }

    /**
     * This method returns a {@link Where} {@link Clause} which always is <b>false</b>.
     */
    static public Where falseExpression() {
        return new Where(new RawClause("1 = 2"), null);
    }
}
