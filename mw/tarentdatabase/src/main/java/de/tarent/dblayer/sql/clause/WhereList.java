package de.tarent.dblayer.sql.clause;
import de.tarent.dblayer.engine.DBContext;
import de.tarent.dblayer.engine.SetDbContext;
import de.tarent.dblayer.engine.SetDbContextImpl;
import de.tarent.dblayer.sql.ParamHolder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This {@link Clause} represents a collection of {@link Clause Clauses}
 * connected by boolean operators to form the condition of the <code>WHERE</code>
 * part of a <code>SELECT</code> or <code>UPDATE</code> statement.
 *
 * @author Wolfgang Klein
 */
public class WhereList extends SetDbContextImpl implements Clause, ParamHolder {
    //
    // protected members
    //
    /**
     * list of {@link Clause Clauses} and boolean operators
     */
    ArrayList _list = new ArrayList();

    //
    // public methods
    //

    /**
     * Appends the parameters of the paramHolder to the supplied list.
     * The order of the params is determined by the order of appearance
     * of the params in the holder object.
     *
     * @param paramList A list to take up ParamValue ebjects.
     * @see ParamHolder#getParams(List)
     */
    public void getParams(List paramList) {
        for (Iterator iter = _list.iterator(); iter.hasNext(); ) {
            Object item = iter.next();
            if (item instanceof ParamHolder) {
                ((ParamHolder) item).getParams(paramList);
            }
        }
    }

    /**
     * This method adds a {@link Clause} to the list of <code>WHERE</code>
     * clauses.<br>
     * This method may only be called to add the first {@link Clause} to
     * the list.
     *
     * @param clause the {@link Clause} to add
     * @return this {@link WhereList} instance
     */
    public WhereList add(Clause clause) {
        _list.add(clause);
        return this;
    }

    /**
     * This method adds a {@link Clause} to the list of <code>WHERE</code>
     * clauses. If there already are {@link Clause Clauses} in the list
     * the new one is added after an <code>OR</code>.
     *
     * @param clause the {@link Clause} to add
     * @return this {@link WhereList} instance
     */
    public WhereList addOr(Clause clause) {
        if (_list.size() != 0) {
            _list.add(Where.OR);
        }
        _list.add(clause);
        return this;
    }

    /**
     * This method adds a {@link Clause} to the list of <code>WHERE</code>
     * clauses. If there already are {@link Clause Clauses} in the list
     * the new one is added after an <code>AND</code>.
     *
     * @param clause the {@link Clause} to add
     * @return this {@link WhereList} instance
     */
    public WhereList addAnd(Clause clause) {
        if (_list.size() != 0) {
            _list.add(Where.AND);
        }
        _list.add(clause);
        return this;
    }

    /**
     * This method returns the size of the list of <code>WHERE</code>
     * clauses including {@link Clause Clauses} and boolean operators.
     *
     * @return size of the list of <code>WHERE</code> clauses
     */
    public int size() {
        return _list.size();
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
    public String clauseToString() {
        return clauseToString(getDBContext());
    }

    /**
     * This method generates a string representation of the clause model
     * for use in SQL statements.
     *
     * @param dbContext the db layer context to use for formatting parameters
     * @return string representation of the clause model, never <code>null</code>
     * @see de.tarent.dblayer.sql.clause.Clause#clauseToString(de.tarent.dblayer.engine.DBContext)
     */
    public String clauseToString(DBContext dbContext) {
        if (_list.size() == 0) {
            return "";
        }

        StringBuffer sb = new StringBuffer();
        sb.append('(');
        for (Iterator it = _list.iterator(); it.hasNext(); ) {
            Object next = it.next();
            if (next instanceof Where) {
                Where.clauseToString((Where) next, sb, false, dbContext);
            } else if (next instanceof Clause) {
                sb.append(((Clause) next).clauseToString(dbContext));
            } else if (next instanceof String) {
                sb.append(next);
            }
        }
        sb.append(')');
        return sb.toString();
    }

    /**
     * Returns an independent clone of this statement.
     *
     * @see java.lang.Object#clone()
     */
    public Object clone() {
        try {
            WhereList theClone = (WhereList) super.clone();
            theClone._list = (ArrayList) _list.clone();
            return theClone;
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }
}
