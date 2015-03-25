/**
 * veraweb, platform independent webservice-based event management
 * (Veranstaltungsmanagment VerA.web), is
 * Copyright © 2004-2008 tarent GmbH
 * Copyright © 2013 tarent solutions GmbH
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, see: http://www.gnu.org/licenses/
 */
package de.tarent.aa.veraweb.worker;

import de.tarent.aa.veraweb.beans.OptionalField;
import de.tarent.dblayer.engine.DB;
import de.tarent.dblayer.engine.DBContext;
import de.tarent.dblayer.sql.SQL;
import de.tarent.dblayer.sql.clause.Clause;
import de.tarent.dblayer.sql.clause.Where;
import de.tarent.dblayer.sql.clause.WhereList;
import de.tarent.dblayer.sql.statement.Delete;
import de.tarent.dblayer.sql.statement.Insert;
import de.tarent.dblayer.sql.statement.Select;
import de.tarent.dblayer.sql.statement.Update;
import de.tarent.octopus.beans.BeanException;
import de.tarent.octopus.beans.Database;
import de.tarent.octopus.beans.TransactionContext;
import de.tarent.octopus.beans.veraweb.DatabaseVeraWeb;
import de.tarent.octopus.server.OctopusContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the optional fields labels.
 *
 * @author Max Marche, tarent solutions GmbH
 * @author Atanas Alexandrov, tarent solutions GmbH
 */
public class OptionalFieldsWorker {
	private static final String DELEGATON_FIELD_TABLE_NAME = "veraweb.toptional_fields";
	private static final String DELEGATON_FIELD_VALUE_TABLE_NAME = "veraweb.toptional_fields_delegation_content";
	

	private Database database;

    /**
     * Constructor.
     *
     * @param ctx The {@link OctopusContext}
     */
	public OptionalFieldsWorker(OctopusContext ctx) {
		this.database = new DatabaseVeraWeb(ctx);
	}
	
	/**
	 * Create ot update optional field.
     *
	 * @param optionalField The {@link OptionalField}
     *
     * @throws SQLException TODO
     * @throws BeanException TODO
	 */
	public void createOrUpdateOptionalField(OptionalField optionalField) throws SQLException, BeanException {
		if(this.checkOptionFieldExists(optionalField)) {
			this.updateOptionalField(optionalField);
		} else {
			this.createOptionalField(optionalField);
		}
	}
	
	/**
	 * Create optional field.
     *
	 * @param optionalField The {@link OptionalField}
	 *
     * @throws SQLException TODO
     * @throws BeanException TODO
	 */
	public void createOptionalField(OptionalField optionalField) throws SQLException, BeanException {
		final TransactionContext context = this.database.getTransactionContext();
        final Insert insert = getStatementInsertOptionalField(optionalField);

		DB.insert(context, insert.statementToString());
        context.commit();
	}

    /**
	 * Update optional field.
     *
	 * @param optionalField The {@link OptionalField}
	 *
	 * @throws SQLException TODO
	 * @throws BeanException TODO
	 */
	public void updateOptionalField(OptionalField optionalField) throws SQLException, BeanException {
        final TransactionContext context = this.database.getTransactionContext();
        final Update updateStatement = getStatementUpdateOptionalField(optionalField);
		DB.update(context, updateStatement.statementToString());
        context.commit();
	}

    /**
	 * Get all optional fields by event id.
     *
	 * @param eventId Event id
     *
	 * @return List with labels for the optional fields.
     *
     * @throws SQLException TODO
     * @throws BeanException TODO
	 */
	public List<OptionalField> getOptionalFieldsByEvent(Integer eventId) throws BeanException, SQLException {
        final Select select = getStatementSelectOptionalField(eventId);
        final ResultSet resultSet = database.result(select);

        return getOptionalFieldsAsList(resultSet);
	}

    /**
	 * Check if the given {@link }OptionalField} exist.
	 *
     * @param optionalField The {@link OptionalField}
     *
	 * @return True if the field exists, otherwise false.
     *
     * @throws SQLException TODO
     * @throws BeanException TODO
	 */
	public boolean checkOptionFieldExists(OptionalField optionalField) throws BeanException, SQLException {
        final Select select = getStatementCheckOptionalFieldExists(optionalField);
        final ResultSet resultSet = database.result(select);
	
		return resultSet.next();
	}

    /**
     * Delete optional field.
     *
     * @param optionalField The {@link OptionalField}
     *
     * @throws SQLException TODO
     * @throws BeanException TODO
     */
	public void removeOptionalField(OptionalField optionalField) throws SQLException, BeanException {
		final TransactionContext context = this.database.getTransactionContext();
		deleteOprionalFieldDependencies(context, optionalField);
        deleteOptionalFieldFromDB(optionalField, context);
	}

    private void deleteOprionalFieldDependencies(TransactionContext context,
			OptionalField optionalField) throws BeanException, SQLException {
    	 final Delete deleteStatement = getDeleteDependenciesStatement(optionalField);
         deleteStatement.executeDelete(context);
         context.commit();
	}

	private Delete getDeleteDependenciesStatement(OptionalField optionalField) {
        final Delete deleteStatement = SQL.Delete(this.database);
        final WhereList whereCriterias = new WhereList();
        
        whereCriterias.add(new Where("fk_delegation_field", optionalField.getPk(), "="));
        
        deleteStatement.from(DELEGATON_FIELD_VALUE_TABLE_NAME);
        deleteStatement.where(whereCriterias);
        return deleteStatement;
	}

	private void deleteOptionalFieldFromDB(OptionalField optionalField, TransactionContext context)
            throws SQLException, BeanException {
        final Delete deleteStatement = getDeleteStatement(optionalField);
        deleteStatement.executeDelete(context);
        context.commit();
    }

    private Delete getDeleteStatement(OptionalField optionalField) {
        final Delete deleteStatement = SQL.Delete(this.database);

        final WhereList whereCriterias = getWhereCriterialsForOptionalField(optionalField);
        deleteStatement.from(DELEGATON_FIELD_TABLE_NAME);
        deleteStatement.where(whereCriterias);
        return deleteStatement;
    }

    private WhereList getWhereCriterialsForOptionalField(OptionalField optionalField) {
        final WhereList whereCriterias = new WhereList();
        if(optionalField.getPk() == -1) {
            whereCriterias.addAnd(new Where("label", optionalField.getLabel(), "="));
            whereCriterias.addAnd(new Where("fk_event", optionalField.getLabel(), "="));
        } else {
            whereCriterias.addAnd(new Where("pk", optionalField.getPk(), "="));
        }
        return whereCriterias;
    }

    private List<OptionalField> getOptionalFieldsAsList(ResultSet resultSet) throws SQLException {
        final List<OptionalField> result = new ArrayList<OptionalField>();
        while(resultSet.next()) {
            final OptionalField delegation = new OptionalField(resultSet);
            result.add(delegation);
        }

        return result;
    }

    private Select getStatementCheckOptionalFieldExists(OptionalField optionalField) {
        final WhereList whereCriterias = getWhereCriterialsForOptionalField(optionalField);
        final Select select = SQL.Select(this.database);
        select.from(DELEGATON_FIELD_TABLE_NAME);
        select.where(whereCriterias);
        select.select("pk");
        return select;
    }

    private Select getStatementSelectOptionalField(Integer eventId) {
        final WhereList whereCriterias = new WhereList();
        whereCriterias.addAnd(new Where("fk_event", eventId, "="));

        final Select select = SQL.Select(this.database);
        select.from(DELEGATON_FIELD_TABLE_NAME);
        select.select("pk");
        select.select("label");
        select.select("fk_event");
        select.where(whereCriterias);
        return select;
    }

    private Insert getStatementInsertOptionalField(OptionalField optionalField) {
        final Insert insert = SQL.Insert(this.database);

        insert.table(DELEGATON_FIELD_TABLE_NAME);
        insert.insert("label", optionalField.getLabel());
        insert.insert("fk_event", optionalField.getFkEvent());
        return insert;
    }

    private Update getStatementUpdateOptionalField(OptionalField optionalField) {
        final WhereList whereCriterias = new WhereList();
        whereCriterias.addAnd(new Where("pk", optionalField.getPk(), "="));

        final Update updateStatement = SQL.Update(this.database);
        updateStatement.table(DELEGATON_FIELD_TABLE_NAME);
        updateStatement.where(whereCriterias);
        if(optionalField.getFkEvent() != -1) {
            updateStatement.update("fk_event", optionalField.getFkEvent());
        }
        updateStatement.update("label", optionalField.getLabel());

        return updateStatement;
    }
}
