package de.tarent.aa.veraweb.utils;

import java.util.List;

import de.tarent.dblayer.sql.Format;
import de.tarent.dblayer.sql.clause.Clause;
import de.tarent.dblayer.sql.clause.Expr;
import de.tarent.dblayer.sql.clause.Order;
import de.tarent.dblayer.sql.clause.RawClause;
import de.tarent.dblayer.sql.clause.WhereList;

/**
 * Datenbank-Hilfsklasse, erstellt u.a. Where-Bedingungen.
 * 
 * @author Christoph Jerolimov
 */
public class DatabaseHelper {
	/** Datenbankseitiges GROSS schreiben: MethodenName( */
	public static final String UPPER_PRE = "veraweb.upper_fix(";
	/** Datenbankseitiges GROSS schreiben: ) */
	public static final String UPPER_POST = ")";
	/** Datenbankseitiges klein schreiben: MethodenName( */
	public static final String LOWER_PRE = "veraweb.upper_fix(";
	/** Datenbankseitiges klein schreiben: ) */
	public static final String LOWER_POST = ")";

	/**
	 * Gibt eine Where-Clause zur�ck, die den �bergebenen Suchbegriff
	 * (<code>search</code>) in allen �bergebenen Spalten (<code>column</code>)
	 * sucht. Wenn im �bergebenem Suchbegriff ein * oder ? vorkommt wird
	 * ein entsprechendes SQL LIKE mit % und _ verwendet. Mehere Spalten werden
	 * werden mit ORs verkn�pft.
	 * 
	 * @param search Suchbegriff
	 * @param column Liste mit Spaltennamen
	 * @return Where-Clause
	 */
	public static Clause getWhere(String search, String column[]) {
		WhereList list = new WhereList();
		if (search.indexOf('*') == -1 && search.indexOf('?') == -1) {
			for (int i = 0; i < column.length; i++)
				list.addOr(Expr.equal(
						UPPER_PRE + column[i] + UPPER_POST, new RawClause(
						UPPER_PRE + Format.format(search) + UPPER_POST)));
		} else {
			search = search.replaceAll("[*]", "%").replaceAll("[?]", "_");
			for (int i = 0; i < column.length; i++)
				list.addOr(Expr.like(
						UPPER_PRE + column[i] + UPPER_POST, new RawClause(
						UPPER_PRE + Format.format(search) + UPPER_POST)));
		}
		return list;
	}

	/**
	 * Gibt eine Order-Clause zur�ck, schaut jeweils nach der Spalte ob
	 * der Wert ASC oder DESC ist und wendet dieses Attribut entsprechend an.
	 * 
	 * @param list
	 * @return Order-Clause
	 */
	public static Order getOrder(List list) {
		if (list != null && list.size() > 0) {
			int pos = 0;
			Order order = null;
			if (list.size() > 1) {
				if ("DESC".equalsIgnoreCase((String)list.get(1))) {
					order = Order.desc((String)list.get(0));
					pos += 2;
				} else if ("ASC".equalsIgnoreCase((String)list.get(1))) {
					order = Order.asc((String)list.get(0));
					pos += 2;
				} else {
					order = Order.asc((String)list.get(0));
					pos += 1;
				}
			} else {
				order = Order.asc((String)list.get(0));
				pos++;
			}
			while (pos < list.size()) {
				if (pos < list.size() - 1) {
					if ("DESC".equalsIgnoreCase((String)list.get(pos + 1))) {
						order = order.andDesc((String)list.get(pos));
						pos += 2;
					} else if ("ASC".equalsIgnoreCase((String)list.get(pos + 1))) {
						order = order.andAsc((String)list.get(pos));
						pos += 2;
					} else {
						order = order.andAsc((String)list.get(pos));
						pos += 1;
					}
				} else {
					order = order.andAsc((String)list.get(pos));
					pos++;
				}
			}
			return order;
		} else {
			return null;
		}
	}
}
