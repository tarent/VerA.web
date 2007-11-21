/*
 * veraweb,
 * Veranstaltungsmanagment veraweb
 * Copyright (c) 2005-2007 tarent GmbH
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License,version 2
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 *
 * tarent GmbH., hereby disclaims all copyright
 * interest in the program 'veraweb'
 * Signature of Elmar Geese, 21 November 2007
 * Elmar Geese, CEO tarent GmbH.
 */

package de.tarent.aa.veraweb.worker;

import java.util.List;

import de.tarent.dblayer.sql.SQL;
import de.tarent.dblayer.sql.clause.Expr;
import de.tarent.dblayer.sql.clause.Limit;
import de.tarent.dblayer.sql.clause.Order;
import de.tarent.octopus.custom.beans.BeanException;
import de.tarent.octopus.custom.beans.veraweb.DatabaseVeraWeb;
import de.tarent.octopus.server.OctopusContext;

/**
 * Worker der eine Autovervollständigung verschiedener Stammdaten zur
 * Verfügung stellt.
 *  
 * @author Christoph
 */
public class CompleteWorker {
	private static final Limit LIMIT = new Limit(new Integer(10), new Integer(0));
	private static final String COLUMN = "entry";
	private static final String QUERY = "query";
	private static final String RESULT = "list";

	/**
	 * @param cntx
	 * @param table
	 * @param column
	 * @param query
	 * @return Liste mit den 10 ersten Einträgen.
	 * @throws BeanException
	 */
	private List getList(OctopusContext cntx, String table, String column, String query) throws BeanException {
		cntx.setContent(QUERY, query);
		return new DatabaseVeraWeb(cntx).getList(SQL.Select().
				from(table).
				selectAs(column, COLUMN).
				where(Expr.like(column, query + '%')).
				orderBy(Order.asc(column)).
				Limit(LIMIT));
	}

	/** Octopus-Eingabeparameter der Aktion {@link #completeLocation(OctopusContext, String)} */
	public static final String INPUT_completeLocation[] = { QUERY };
	/** Octopus-Ausgabeparameter der Aktion {@link #completeLocation(OctopusContext, String)} */
	public static final String OUTPUT_completeLocation = RESULT;
	/**
	 * @param cntx Octopus-Context
	 * @param query Aktuelle Benutzereingabe
	 * @return Liste mit ähnlichen Locations.
	 * @throws BeanException 
	 */
	public List completeLocation(OctopusContext cntx, String query) throws BeanException {
		return getList(cntx, "veraweb.tlocation", "locationname", query);
	}
}
