/*
 * $Id: GroupBy.java,v 1.4 2007/06/14 14:51:56 dgoema Exp $
 */
package de.tarent.dblayer.sql.clause;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.tarent.dblayer.engine.DBContext;
import de.tarent.dblayer.engine.SetDbContext;

/**
 * This class represents the <code>GROUP BY</code> part of a <code>SELECT</code>
 * statement.
 */
public class GroupBy {
    //
    // public constants
    //
    /** the String "<code> GROUP BY </code>" */
	static public final String GROUPBY = " GROUP BY ";

    //
    // protected member variables
    //
    /** list of <code>GROUP BY</code> column names */
    ArrayList groupby = new ArrayList();

    //
    // constructors
    //
    /** This constructor creates an empty <code>GROUP BY</code> part. */
	public GroupBy() {
	}

    /** This constructor creates an <code>GROUP BY</code> part having one column. */
	public GroupBy(String column) {
		add(column);
	}

    //
    // public static factory methods
    //
    /** This method returns an <code>GROUP BY</code> part having one column. */
    public static GroupBy groupBy(String column) {
	return new GroupBy(column);
    }

    //
    // public methods
    //
    /** This method adds a column to the <code>GROUP BY</code> part. */
	public GroupBy add(String column) {
		groupby.add(column);
		return this;
	}

    /**
     * This method generates a string representation of the <code>GROUP BY</code>
     * part for use in SQL statements.<br>
     * This method should only be used after setting the {@link DBContext}
     * using the {@link SetDbContext#setDBContext(DBContext)} method.
     * Otherwise a default db layer context is assumed which for now is
     * a PostgresQL DBMS.
     *
     * @return string representation of the <code>GROUP BY</code> part
     */
	public String clauseToString() {
		StringBuffer sb = new StringBuffer();
		sb.append(GROUPBY);
		appendClause(sb);
		return sb.toString();
	}

    /**
     * This method appends a string representation of the <code>GROUP BY</code>
     * part for use in SQL statements to a {@link StringBuffer}.<br>
     * This method should only be used after setting the {@link DBContext}
     * using the {@link SetDbContext#setDBContext(DBContext)} method.
     * Otherwise a default db layer context is assumed which for now is
     * a PostgresQL DBMS.
     */
	public void clauseToString(StringBuffer sb) {
		sb.append(GROUPBY);
		appendClause(sb);
	}

    //
    // protected helper methods
    //
    /**
     * This method appends a string representation of the <code>GROUP BY</code>
     * columns for use in SQL statements to a {@link StringBuffer}.<br>
     * This method should only be used after setting the {@link DBContext}
     * using the {@link SetDbContext#setDBContext(DBContext)} method.
     * Otherwise a default db layer context is assumed which for now is
     * a PostgresQL DBMS.
     */
	protected void appendClause(StringBuffer sb) {
		for (Iterator it = groupby.iterator(); it.hasNext(); ) {
			sb.append(it.next());
			if (it.hasNext())
				sb.append(", ");
		}
	}

    /**
     * Returns an independent clone of this statement.
     * @see java.lang.Object#clone()
     */
    public Object clone() {
	try {
	    GroupBy theClone = (GroupBy)super.clone();
	    theClone.groupby = (ArrayList)groupby.clone();
	    return theClone;
	}
	catch(CloneNotSupportedException e) {
		throw new InternalError();
	}
      }

}
