/**
 *
 */
package de.tarent.dblayer.sql.statement;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import de.tarent.dblayer.engine.DB;
import de.tarent.dblayer.engine.DBContext;
import de.tarent.dblayer.engine.Result;
import de.tarent.dblayer.sql.SQL;
import de.tarent.dblayer.sql.SyntaxErrorException;

/**
 * Statement for encapsultion of a Stored Procedure-Call
 *
 * @author kirchner
 *
 */
public class Procedure extends AbstractStatement {

	protected String _name;

	protected List _params = new LinkedList();

	/**
	 * Constructs a new Wrapper for an SQL-Procedure.
	 * @param dbx DBContext to use
	 * @param name Name of Procedure
	 */
	public Procedure(DBContext dbx, String name){
		setDBContext(dbx);
		_name = name;
	}

	/**
	 * Executes the procedure on the given Context.
	 * @param dbx DBContext
	 * @return Result of SQL-Operation
	 * @throws SQLException if a problem occurs
	 */
	public void executeVoidProcedure(DBContext dbx) throws SQLException{
		if(SQL.isPostgres(dbx)){
			DB.result(dbx, this);
		}else{
			DB.update(dbx, this);
		}
	}

	/**
	 * Executes the procedure on the given Context.
	 * @param dbx DBContext
	 * @return Result of SQL-Operation
	 * @throws SQLException if a problem occurs
	 */
	public Result executeProcedure(DBContext dbx) throws SQLException{
		return DB.result(dbx, this);
	}

	/* (non-Javadoc)
	 * @see de.tarent.dblayer.sql.Statement#execute()
	 */
	public Object execute() throws SQLException {
		return executeProcedure(getDBContext());
	}

	/* (non-Javadoc)
	 * @see de.tarent.dblayer.sql.Statement#statementToString()
	 */
	public String statementToString() throws SyntaxErrorException {
		StringBuffer sb = new StringBuffer();
		//sb.append("execute ");
		sb.append(_name);
		sb.append(" ");
		Iterator it = _params.iterator();
		boolean first = true;
		while(it.hasNext()){
			if(!first){
				sb.append(", ");
			}
			else
				first=false;

			sb.append(SQL.format(getDBContext(), it.next()));
		}
		return sb.toString();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		try{
			return statementToString();
		}catch(SyntaxErrorException see){
			return see.getMessage();
		}
	}

	/**
	 * Sets the name of the Procedure to call
	 * @param name Name of Procedure
	 * @return
	 */
	public Procedure call(String name){
		_name = name;
		return this;
	}

	/**
	 * Adds Parameter to call. Parameters are applied
	 * in the order they are added via {@link Procedure#addParam(String)}.
	 * @param value Value of the Param
	 * @return
	 */
	public Procedure addParam(Object value){
		_params.add(value);
		return this;
	}
}
