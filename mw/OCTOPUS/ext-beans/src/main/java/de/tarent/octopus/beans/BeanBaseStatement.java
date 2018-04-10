package de.tarent.octopus.beans;

/*-
 * VerA.web-Middleware, newly MIT licenced, is comprised of:
 * tarent-commons, a set of common components and solutions
 * tarent-contact, platform-independent webservice-based contact management
 * tarent-database, jdbc database library
 * tarent-doctor, Document Generation Platform
 * tarent-octopus, Webservice Data Integrator and Application Server
 *  © 2005, 2006, 2007 asteban <s.mancke@tarent.de>
 *  © 2007 David Goemans <d.goemans@tarent.de>
 *  © 2006, 2007, 2010 Hendrik Helwich <h.helwich@tarent.de>
 *  © 2005, 2006, 2007 Christoph Jerolimov <c.jerolimov@tarent.de>
 *  © 2006 Philipp Kirchner <p.kirchner@tarent.de>
 *  © 2010 Carsten Klein <c.klein@tarent.de>
 *  © 2006 Michael Kleinhenz <m.kleinhenz@tarent.de>
 *  © 2006 Michael Klink <m.klink@tarent.de>
 *  © 2007 Fabian Köster <f.koester@tarent.de>
 *  © 2006 Martin Ley <m.ley@tarent.de>
 *  © 2007 Alex Maier <a.maier@tarent.de>
 *  © 2007, 2015, 2017, 2018 mirabilos <t.glaser@tarent.de>
 *  © 2006, 2007 Jens Neumaier <j.neumaier@tarent.de>
 *  © 2006 Nils Neumaier <n.neumaier@tarent.de>
 *  © 2007, 2008 Martin Pelzer <m.pelzer@tarent.de>
 *  © 2008, 2009 Christian Preilowski <c.thiel@tarent.de>
 *  © 2006, 2008, 2009 Thomas Schmitz <t.schmitz@tarent.de>
 *  © 2007 Robert Schuster <r.schuster@tarent.de>
 * and older code, Copyright © 2001–2007 ⮡ tarent GmbH and contributors.
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

import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.tarent.dblayer.sql.Statement;
import de.tarent.dblayer.sql.SyntaxErrorException;

/**
 * This class is a base class for {@link BeanStatement} implementations. It holds
 * the statement in db layer, SQL, and prepared form.
 *
 * @author Michael Klink
 */
class BeanBaseStatement {
    //
    // Konstruktor
    //
    /**
     * This constructor prepares the given statement inside the given context.
     */
    public BeanBaseStatement(Statement statement, ExecutionContext context) throws BeanException {
        this.context = context;
        this.dblayerStatement = statement;
        this.preparedStatement = context.prepare(dblayerStatement);
        try {
            this.sqlStatement = dblayerStatement.statementToString();
        } catch (SyntaxErrorException e) {
            throw new BeanException("Syntax error in SQL statement", e);
        }
        if (logger.isLoggable(Level.FINE))
            logger.fine("Created PreparedStatement for SQL statament <" +sqlStatement + ">.");
    }

    //
    // geschätzte Variablen
    //
    /** The statement is to be executes inside this context. */
    final ExecutionContext context;

    /** This is the prepared form of the statement. */
    final PreparedStatement preparedStatement;

    /** This is the db layer form of the statement. */
    final Statement dblayerStatement;

    /** This is the SQL form of the statement. */
    final String sqlStatement;

    /** place holder object for variables inside the prepared statement. */
    final static Object PLACE_HOLDER = new Object() {
        public String toString() {
            return "?";
        }
    };

    /** logger of this class. */
    final static Logger logger = Logger.getLogger(BeanBaseStatement.class.getName());
}
