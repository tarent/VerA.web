/**
 * veraweb, platform independent webservice-based event management
 * (Veranstaltungsmanagment VerA.web), is
 * Copyright © 2004–2008 tarent GmbH
 * Copyright © 2013–2015 tarent solutions GmbH
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

import de.tarent.aa.veraweb.beans.Person;
import de.tarent.aa.veraweb.beans.PersonCategorie;
import de.tarent.aa.veraweb.beans.PersonSearch;
import de.tarent.aa.veraweb.beans.facade.PersonConstants;
import de.tarent.aa.veraweb.utils.DatabaseHelper;
import de.tarent.dblayer.helper.ResultMap;
import de.tarent.dblayer.sql.Escaper;
import de.tarent.dblayer.sql.Format;
import de.tarent.dblayer.sql.SQL;
import de.tarent.dblayer.sql.SyntaxErrorException;
import de.tarent.dblayer.sql.clause.Clause;
import de.tarent.dblayer.sql.clause.Expr;
import de.tarent.dblayer.sql.clause.Limit;
import de.tarent.dblayer.sql.clause.RawClause;
import de.tarent.dblayer.sql.clause.StatementList;
import de.tarent.dblayer.sql.clause.Where;
import de.tarent.dblayer.sql.clause.WhereList;
import de.tarent.dblayer.sql.statement.Select;
import de.tarent.dblayer.sql.statement.Update;
import de.tarent.octopus.PersonalConfigAA;
import de.tarent.octopus.beans.Bean;
import de.tarent.octopus.beans.BeanException;
import de.tarent.octopus.beans.BeanFactory;
import de.tarent.octopus.beans.Database;
import de.tarent.octopus.beans.TransactionContext;
import de.tarent.octopus.beans.veraweb.ListWorkerVeraWeb;
import de.tarent.octopus.server.OctopusContext;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Diese Octopus-Worker-Klasse stellt Operationen zur Anzeige
 * von Personenlisten zur Verfügung.
 * Details bitte dem BeanListWorker entnehmen.
 *
 * @author Christoph
 * @author mikel
 */
public class PersonListWorker extends ListWorkerVeraWeb {
    //
    // Konstruktoren
    //
    /**
     * Der Konstruktor legt den Bean-Namen fest.
     */
	public PersonListWorker() {
		super("Person");
	}

    //
    // Oberklasse BeanListWorker
    //


	/**
	 * Should be after vera lifetime: 9999-12-01 GMT
	 */
	private static final Timestamp INFINITY_TIMESTAMP = new Timestamp(253399622400000L);

	/**
	 * Octopus-Aktion die eine <strong>blätterbare</strong> Liste
	 * mit Beans aus der Datenbank in den Content stellt. Kann durch
	 * {@link #extendColumns(OctopusContext, Select)} erweitert bzw.
	 * {@link #extendWhere(OctopusContext, Select)} eingeschränkt werden.
	 *
	 * Lenkt hier die entsprechende getSelect - Anfrage an eine
	 * spezialisierte Form.
	 *
	 * @see #getSelection(OctopusContext, Integer)
	 *
	 * @param cntx Octopus-Context
	 * @return Liste mit Beans, nie null.
	 * @throws BeanException
	 * @throws IOException
	 */
	public List showList(OctopusContext cntx) throws BeanException, IOException {
		Database database = getDatabase(cntx);
		cntx.setContent("searchTask", cntx.requestAsObject("searchTask"));

		/* modified (refactored part of behaviour to prepareShowList for additional reuse) as per change request for version 1.2.0
		 * cklein
		 * 2008-02-21
		 */
//		cntx.setContent( "action", ( String ) null ); // reset action
		Select personSelect = this.prepareShowList( cntx, database );
		Map param = ( Map )cntx.contentAsObject( OUTPUT_showListParams );
		personSelect.Limit(new Limit((Integer)param.get("limit"), (Integer)param.get("start")));

		/* FIXME remove this temporary fix ASAP
		 * cklein 2009-09-16
		 * Temporary workaround for NPE Exception in Conjunction with temporary Connection Pooling Fix in tarent-database
		 * Somehow the resultlist returned by getResultList or its underlying ResultSet will be NULL when entering the view
		 * although, upon exiting this method the first time that it is called, will return the correct resultlist with at most
		 * 10 entries in the underlying resultset as is defined by the query.
		 */
		Map<Integer, Map> result = new LinkedHashMap<Integer, Map>();
		List personList = getResultList( database, personSelect );

		for ( int i = 0; i < personList.size(); i++ ) {
			HashMap< String, Object > tmp = new HashMap< String, Object >();
			Set< String > keys = ((ResultMap) personList.get(i)).keySet();

			Integer id = null;
			for ( String key : keys ) {
				Object val = ((ResultMap) personList.get(i)).get(key) ;
				if ("id".equals(key)) {
					id = (Integer) val;
					tmp.put(key, val);
				} else {
					tmp.put(key, val);
				}
			}

			/* select all relevant event/task information for each person */
			Integer personId = (Integer) tmp.get("id");
			Select eventSelect = SQL.SelectDistinct(database).from("veraweb.tperson")
			            .selectAs("tperson.pk", "id")
                        .selectAs("tevent.dateend", "eventenddate")
                        .selectAs("event2.dateend", "taskeventenddate")
                        .selectAs("tevent.datebegin", "eventbegindate")
                        .selectAs("event2.datebegin", "taskeventbegindate")
                        .joinOuter("veraweb.tguest", "tguest.fk_person", "tperson.pk")
                        .joinOuter("veraweb.tevent", "tevent.pk", "tguest.fk_event")
                        .joinOuter("veraweb.ttask", "ttask.fk_person", "tperson.pk")
                        .joinOuter("veraweb.tevent event2", "event2.pk", "ttask.fk_event")
                        .where(Expr.equal("tperson.pk", personId));


			Timestamp eventBeginDate = null;
            Timestamp eventEndDate = null;
            Timestamp taskEventBeginDate = null;
            Timestamp taskEventEndDate = null;

			List eventList = getResultList(database, eventSelect);
			for ( int j = 0; j < eventList.size(); j++ ) {
    			keys = ((ResultMap) eventList.get(j)).keySet();
    			for (String key : keys) {
                    Object val = ((ResultMap) eventList.get(j)).get(key) ;
                    if ("id".equals(key)) {
                        id = (Integer) val;
                        tmp.put(key, val);
                    } else if ("eventbegindate".equals(key)) {
                        eventBeginDate = (Timestamp) val;
                    } else if ("eventenddate".equals(key)) {
                        eventEndDate = (Timestamp) val;
                    } else if ("taskeventbegindate".equals(key)) {
                        taskEventBeginDate = (Timestamp) val;
                    } else if ("taskeventenddate".equals(key)) {
                        taskEventEndDate = (Timestamp) val;
                    }
                }
			}


			if (eventBeginDate != null && eventEndDate == null) { // end = infinity
				eventEndDate = INFINITY_TIMESTAMP;
			}
			if (taskEventBeginDate != null && taskEventEndDate == null) { // end = infinity
				taskEventEndDate = INFINITY_TIMESTAMP;
			}
			if (eventEndDate != null) {
				tmp.put("eventmaxenddate", eventEndDate);
			}
			if (taskEventEndDate != null) {
				tmp.put("taskeventmaxenddate", taskEventEndDate);
			}
			if (!result.containsKey(id)) {
				result.put(id, tmp);
			} else {
				Map map = result.get(id);
				Timestamp date = (Timestamp) map.get("eventmaxenddate");
				if (eventEndDate != null && (date == null || eventEndDate.after(date))) {
					map.put("eventmaxenddate", eventEndDate);
				}
				date = (Timestamp) map.get("taskeventmaxenddate");
				if (taskEventEndDate != null && (date == null || taskEventEndDate.after(date))) {
					map.put("taskeventmaxenddate", taskEventEndDate);
				}
			}
		}

		cntx.setContent(OUTPUT_getSelection, getSelection(cntx, getCount(cntx, database)));

		cntx.setContent("deleted", cntx.getRequestObject().getParamAsInt("deleted"));

		cntx.setContent("workareaAssigned",  cntx.requestAsObject("workareaAssigned"));

		cntx.setContent("categoryAssigned",  cntx.requestAsObject("categoryAssigned"));

		return new ArrayList(result.values());
	}

	@Override
	public void saveList(OctopusContext cntx) throws BeanException, IOException {
		String categoryAssignmentAction = cntx.requestAsString("categoryAssignmentAction");
		String workareaAssignmentAction = cntx.requestAsString("workareaAssignmentAction");

		// does the user request categories to be assigned or unassigned?
		if (categoryAssignmentAction != null && categoryAssignmentAction.length() > 0) {
			Database database = getDatabase(cntx);
			TransactionContext context = database.getTransactionContext();
			PersonCategorieWorker personCategoryWorker = WorkerFactory.getPersonCategorieWorker(cntx);
			Integer categoryId = cntx.requestAsInteger("categoryAssignmentId");
			List selection = this.getSelection(cntx, this.getCount(cntx, database));
			Iterator iter = selection.iterator();
			PersonCategorie category = null;
			while (iter.hasNext()) {
				Integer personId = (Integer) iter.next();
				if ("assign".compareTo(categoryAssignmentAction) == 0 && categoryId.intValue() > 0) {
					category = personCategoryWorker.addCategoryAssignment(cntx, categoryId, personId, database, context, false);
					if (category != null) {
						database.saveBean(category, context, false);
					}
				} else {
					if (categoryId.intValue() == 0) {
						personCategoryWorker.removeAllCategoryAssignments(cntx, personId, database, context);
					} else {
						personCategoryWorker.removeCategoryAssignment(cntx, categoryId, personId, database, context);
					}
				}
				iter.remove();
			}
			try {
				context.commit();
			} catch (BeanException e) {
				context.rollBack();
				throw e;
			}
		}

		// does the user request workareas to be assigned or unassigned?
		else if (workareaAssignmentAction != null && workareaAssignmentAction.length() > 0) {
			Database database = getDatabase(cntx);
			List<Integer> selection = getSelection(cntx, getCount(cntx, database));
			if (!selection.isEmpty()) {
				cntx.setContent("deleted", selection.size());
				Integer workareaId = cntx.requestAsInteger("workareaAssignmentId");
				if ("assign".compareTo(workareaAssignmentAction) == 0) {
					assignWorkArea(cntx, selection, workareaId);
				} else if ("unassign".compareTo(workareaAssignmentAction) == 0) {
					unassignWorkArea(cntx, selection, workareaId);
				}
				selection.clear();
			}
		} else {
			super.saveList(cntx);
		}
	}

	/**
	 * Entfernt die Zuordnungen von Arbeitsbereichen der übergebenen Personen (IDs).
	 *
	 * @param cntx Octopus-Context
	 * @param personIds Liste von Personen IDs für die das entfernen der Zuordnung gilt
	 * @param workAreaId ID des Arbeitsbereiches deren Zuordnung entfernt werden soll
	 * @throws BeanException
	 * @throws IOException
	 */
	public void unassignWorkArea(OctopusContext cntx, List<Integer> personIds, Integer workAreaId) throws BeanException, IOException {
		Database database = getDatabase(cntx);
		TransactionContext context = database.getTransactionContext();
		PersonListWorker.unassignWorkArea(context, workAreaId, personIds);
		try {
			context.commit();
		} catch (Exception e) {
			context.rollBack();
		}
	}

	/**
	 * Ordnet den übergebenen Arbeitsbereich der Liste von Personen hinzu.
	 *
	 * @param cntx OctopusContext
	 * @param personIds Liste von Personen IDs für die die neue Zuordnung gilt
	 * @param workAreaId ID des Arbeitsbereiches der zugeordnet werden soll
	 * @throws BeanException
	 * @throws IOException
	 */
	public void assignWorkArea(OctopusContext cntx, List<Integer> personIds, Integer workAreaId) throws BeanException, IOException {
		Database database = getDatabase(cntx);
		TransactionContext context = database.getTransactionContext();
		PersonListWorker.assignWorkArea(context, workAreaId, personIds);
		try {
			context.commit();
		} catch (Exception e) {
			context.rollBack();
		}
	}

	/*
	 * 2009-05-12 cklein
	 * introduced as part of fix for issue #1530 - removal of orgunits, and subsequently also individual workareas
	 *
	 * unassigns from all persons the given workArea. Will not commit the query as this is left to the caller.
	 */
	public static void unassignWorkArea(TransactionContext context, Integer workAreaId, List<Integer> personIds) throws BeanException, IOException {
		Update stmt = context.getDatabase().getUpdate("Person");
		stmt.update("tperson.fk_workarea", 0);
		stmt.where(Expr.equal("tperson.fk_workarea", workAreaId));
		if (personIds != null && personIds.size() > 0) {
			stmt.whereAnd(Expr.in("tperson.pk", personIds));
		}
		context.execute(stmt);
	}

	public static void assignWorkArea(TransactionContext context, Integer workAreaId, List<Integer> personIds) throws BeanException, IOException {
		Update stmt = context.getDatabase().getUpdate("Person");
		stmt.update("tperson.fk_workarea", workAreaId);
		if (personIds != null && personIds.size() > 0) {
			stmt.whereAnd(Expr.in("tperson.pk", personIds));
		}
		context.execute(stmt);
	}

	public Select prepareShowList( OctopusContext cntx, Database database ) throws BeanException, IOException
	{
		Integer start = getStart(cntx);
		Integer limit = getLimit(cntx);
		Integer count = getCount(cntx, database);
		Map param = getParamMap(start, limit, count);
		Select select = getSelect(getSearch(cntx), database);
		extendColumns(cntx, select);
		extendWhere(cntx, select);
		cntx.setContent(OUTPUT_showListParams, param);
		return select;
	}

	protected void extendColumns(OctopusContext cntx, Select select) throws BeanException, IOException {
	    PersonSearch search = getSearch(cntx);
		select.selectAs( "tworkarea.name", "workarea_name" );
		select.selectAs( "dateexpire", "dateexpire" );

		/*
		 * modified to support workarea display in the search result list as per change request for version 1.2.0
		 * cklein 2008-02-12
		 */
		String searchFiled = cntx.getRequestObject().getParamAsString("searchField");
		if (searchFiled == null) {
			select.join( "veraweb.tworkarea", "tworkarea.pk", "tperson.fk_workarea" );
		}

		List<String> order = new ArrayList<String>();

		/*
		 * TODO: Needed to optimise that snippet and move it to a
		 * location where all List can use it for sortation
		 */
		if(search.sortList) {
            if (search.sort == null || search.lastlistorder == null || !search.lastlistorder.equals(search.listorder)) {
                search.sort = "ASC";
            } else if ("ASC".equals(search.sort) && search.lastlistorder.equals(search.lastlistorder)) {
                search.sort = "DESC";
            } else if ("DESC".equals(search.sort) && search.lastlistorder.equals(search.lastlistorder)){
                search.sort = "ASC";
            }
        }
        cntx.getContentObject().setField("personSearchOrder", search.sort);

        if (search != null && search.listorder != null && search.listorder != "") {
            order.add(search.listorder);
            order.add(search.sort);
        }

        select.orderBy(DatabaseHelper.getOrder(order));
	}

	protected void extendWhere(OctopusContext cntx, Select select) throws BeanException
	{
		PersonSearch search = getSearch( cntx );
		select.whereAnd( getPersonListFilter( cntx , true) );

		select.setDistinct(true);
		String searchFiled = cntx.getRequestObject().getParamAsString("searchField");


		/*
		 * extension to support for multiple categories at once
		 *
		 * cklein
		 * 2008-02-20/26
		 */
 		this.extendSelectByMultipleCategorySearch( cntx, search, select );
		if ( search.categorie2 != null )
		{
			select.join( "veraweb.tperson_categorie cat2", "cat2.fk_person", "tperson.pk" );
		} else if (searchFiled != null) {
			select.joinOuter( "veraweb.tperson_categorie cat2", "cat2.fk_person", "tperson.pk" );

		}
		if (searchFiled != null) {

			select.joinOuter("veraweb.tworkarea", "tworkarea.pk", "tperson.fk_workarea");
//			select.joinOuter("veraweb.torgunit", "torgunit.pk", "tperson.fk_orgunit");
			select.joinOuter("veraweb.tcategorie", "tcategorie.pk", "cat2.fk_categorie");
		}
	}

	/**
	 * Extends the select statement in order to allow search for multiple
	 * categories at once using either AND or OR.
	 *
	 * @param cntx
	 * @param search
	 * @param select
	 */
	protected void extendSelectByMultipleCategorySearch(OctopusContext cntx, PersonSearch search, Select select) {
		if
				(
				(search.categoriesSelection != null) &&
						(search.categoriesSelection.size() >= 1) &&
						(search.categoriesSelection.get(0).toString().length() > 0) // workaround for octopus behaviour
				) {
			if (((Integer) search.categoriesSelection.get(0)).intValue() != 0) {
				// FUTURE extension for supporting OR a/o AND
				boolean isOr = false;
				if (cntx.contentContains("disjunctCategorySearch")) {
					isOr = cntx.requestAsBoolean("disjunctCategorySearch").booleanValue();
				}
				if (isOr) {
					// FIXME does not work, misses join on tperson_categorie
					// any of the selected categories (OR clause)
					select.whereAnd(new RawClause("tperson.pk=cat1.fk_person"));
					select.whereAnd(Expr.in("cat1.fk_categorie", search.categoriesSelection));
				} else {
					// all of the selected categories (AND clause)
					Iterator iter = search.categoriesSelection.iterator();
					int count = 0;
					while (iter.hasNext()) {
						String alias = "cat" + count;
						select.joinLeftOuter("veraweb.tperson_categorie " + alias, "tperson.pk", alias + ".fk_person");
						select.whereAnd(new RawClause(alias + ".fk_categorie=" + iter.next()));
						count++;
					}
				}
			} else {
				// no categories assigned
				Select subSelect = new Select(true);
				subSelect.from("veraweb.tperson_categorie");
				subSelect.selectAs("tperson_categorie.fk_person");
				try {
					select.whereAnd(Expr.notIn("tperson.pk", new RawClause("(" + subSelect.statementToString() + ")")));
				} catch (SyntaxErrorException e) {
					;
					; // just catch, will never happen
				}
			}
		} else {
			;
			; // search in all categories
		}
	}

	protected Integer getAlphaStart(OctopusContext cntx, String start) throws BeanException, IOException {
		Database database = getDatabase(cntx);
		Select select = database.getCount(BEANNAME);
		this.extendWhere(cntx, select);
		if (start != null && start.length() > 0) {
			select.whereAnd(Expr.less("tperson.lastname_a_e1", Escaper.escape(start)));
		}

		Integer i = database.getCount(select);
		return new Integer(i.intValue() - (i.intValue() % getLimit(cntx).intValue()));
	}

	protected Select getSelect(Database database) throws BeanException, IOException {
		return getSelect(null, database);
	}

	protected Select getSelect(PersonSearch search, Database database) throws BeanException, IOException {
		Select select;
		if (search != null) {
			select = new Select(search.categoriesSelection != null || search.categorie2 != null);
		} else {
			select = SQL.SelectDistinct( database );
		}

		return select.
				from("veraweb.tperson").
				selectAs("tperson.pk", "id").
				select("firstname_a_e1").
				select("lastname_a_e1").
				select("firstname_b_e1").
				select("lastname_b_e1").
				select("function_a_e1").
				select("company_a_e1").
				select("street_a_e1").
				select("zipcode_a_e1").
				select("state_a_e1").
				select("city_a_e1").
                select("iscompany");
	}

	protected List getResultList(Database database, Select select) throws BeanException, IOException {
		return database.getList(select, database);
	}

	/**
	 * Überprüft ob eine Person die nötigen Berechtigungen hat um Personen
	 * zu löschen und ob diese ggf. noch Veranstaltungen zugeordent sind.
	 *
	 * Bei Veränderungen an dieser Methode müssen diese ggf. auch in der
	 * personList.vm übernommen werden, dort werden entsprechende JavaScript
	 * Meldungen ausgegeben.
	 *
	 * siehe Anwendungsfall: UC.PERSON.LOESCH
	 */
	protected int removeSelection(OctopusContext cntx, List errors, List selection, TransactionContext context) throws BeanException, IOException {
		int count = 0;
		if (selection == null || selection.size() == 0) return count;
		List selectionRemove = new ArrayList(selection);

		Database database = context.getDatabase();
		Map questions = new HashMap();

		List groups = Arrays.asList(cntx.personalConfig().getUserGroups());
		boolean user = groups.contains(PersonalConfigAA.GROUP_WRITE);
		boolean admin = groups.contains(PersonalConfigAA.GROUP_ADMIN) ||
				groups.contains(PersonalConfigAA.GROUP_PARTIAL_ADMIN);
		if (admin) user = false;

		if (!(user || admin)) {
			errors.add("Sie haben keine Berechtigung Personen zu l\u00f6schen.");
			return count;
		}
		/** User dürfen immer nur eine Person gleichzeitig löschen. */
		if (user && selectionRemove.size() > 1) {
			errors.add("Sie d\u00fcrfen immer nur eine Person l\u00f6schen.\n" +
					"Bitte markieren Sie nur einen Eintrag, oder wenden Sie sich an Ihren Administrator.");
			cntx.setContent("listerrors", errors);
			return count;
		}


		int maxquestions = 0;
		int subselectsize = 1000;

		/** Test ob Personen noch gültig sind und nicht gelöscht werden dürfen. */
		if ((user || admin) && !selectionRemove.isEmpty()) {
			for (int i = 0; i < selectionRemove.size(); i += subselectsize) {
				List subList = selectionRemove.subList(i, i + subselectsize < selectionRemove.size() ? i + subselectsize : selectionRemove.size());
				List personExpireInFuture =
						database.getBeanList("Person",
						database.getSelect("Person").
		                where(new RawClause(
		                        "dateexpire >= " + Format.format(new Date()) +
		                        " AND pk IN " + new StatementList(subList))));
				for (Iterator it = personExpireInFuture.iterator(); it.hasNext(); ) {
					Person person = (Person)it.next();
					if (getContextAsBoolean(cntx, "remove-expire-" + person.id)) {
						cntx.setContent("remove-person", Boolean.TRUE);
					} else {
						if (maxquestions == 0 || questions.size() < maxquestions) {
							/**questions.put("remove-expire-" + person.id, "Das Gültigkeitsdatum der Person \"" + person.getMainLatin().getSaveAs()  + "\" liegt in der Zukunft. Soll die Person trotzdem gelöscht werden?");*/
							questions.put("remove-expire-" + person.id, person.getMainLatin().getSaveAs());
						}
						selectionRemove.remove(person.id);
						i--;
					}
				}
			}
		}

		/** Fragen ob alle Personen wirklich gelöscht werden sollen. */
		if (!getContextAsBoolean(cntx, "remove-person")) {
			questions.put("remove-person", "Sollen alle markierten Personen gel\u00f6scht werden?");
		}

		if (!questions.isEmpty()) {
			cntx.setContent("listquestions", questions);
		}

		/** Löscht Personen aus VerA.web */
		if ((user || admin) && !selectionRemove.isEmpty() && getContextAsBoolean(cntx, "remove-person")) {
			try
			{
				PersonDetailWorker personDetailWorker = WorkerFactory.getPersonDetailWorker(cntx);
				for (Iterator it = selectionRemove.iterator(); it.hasNext(); ) {
					Integer id = (Integer)it.next();

					/*
					 * updated to reflect interface changes on removePerson
					 * cklein 2008-02-12
					 */
					Person person = (Person) database.getBean("Person", id);
					personDetailWorker.removePerson( cntx, context, id, person.username);
					it.remove();
					selection.remove( id );
					count++;
				}
				context.commit();


			}
			catch( BeanException e )
			{
				context.rollBack();
				throw new BeanException( "Die ausgew\u00e4hlten Personen konnten nicht gel\u00f6scht werden.", e );
			}
		}

		/* fix: selection remained active in session, causing lists to autoselect
		 * individual entries in the lists, will remove the session variable
		 * additionally, non-deletable entries remained selected, will reset the
		 * session variable with the new selection list
		 * cklein 2008-03-12
		 */
		cntx.setSession( "selection" + BEANNAME, selection );

		return count;
	}

    /**
     * Wirft eine BeanException, die Personen werden mit ihren Abhängigkeiten
     * direkt in der Methode @link #removeSelection(OctopusContext, List, List)
     * gelöscht.
     */
    protected boolean removeBean(OctopusContext cntx, Bean bean) throws BeanException, IOException {
        throw new BeanException("PersonListWorker#removeBean is deprecated");
    }

    //
    // Octopus-Aktionen
    //
    /** Octopus-Eingabeparameter für {@link #getSearch(OctopusContext)} */
    public static final String INPUT_getSearch[] = {};
    /** Octopus-Ausgabeparameter für {@link #getSearch(OctopusContext)} */
    public static final String OUTPUT_getSearch = "search";
    /**
     * Diese Octopus-Aktion liefert ein aktuelles {@link PersonSearch}-Objekt.
     * Dies wird aus dem Request geholt geholt, falls ein Requestparameter
     * "search" den Wert "reset" hat. Beim Wert "clear" wird ein leeres
     * Objekt zurück gegeben. Ausgewichen wird dann auf ein entsprechendes
     * Session-Objekt. Das Ergebnis wird in der Session gesetzt.
     *
     * @param cntx Octopus-Kontext
     * @throws BeanException
     */
    public PersonSearch getSearch(OctopusContext cntx) throws BeanException {

        if (cntx.contentContains("search") && cntx.contentAsObject("search") instanceof PersonSearch) {
			return (PersonSearch) cntx.contentAsObject("search");
		}

        String param = cntx.requestAsString("search");
        Boolean sortList = cntx.requestAsBoolean("sortList");
        PersonSearch search = null;

        if ("clear".equals(param))
            search = new PersonSearch();
        else if ("reset".equals(param))
        {
            search = (PersonSearch)getRequest(cntx).getBean("PersonSearch");
            /*
             * modified to support category multi selection
             * cklein
             * 2008-02-26
             */
            List list = ( List ) BeanFactory.transform( cntx.requestAsObject( "categoriesSelection" ), List.class );
            ArrayList< Integer > selection = new ArrayList< Integer >( list.size() );
            if ( list.size() > 0 && list.get( 0 ).toString().length() > 0 )
            {
            	Iterator iter = list.iterator();
            	while( iter.hasNext() )
            	{
            		selection.add( new Integer( ( String ) iter.next() ) );
            	}
            }
            search.categoriesSelection = selection;
        } else {
            search = (PersonSearch)getRequest(cntx).getBean("PersonSearch", "search");
            PersonSearch searchFromRequest = search;
            search = (PersonSearch)cntx.sessionAsObject("search" + BEANNAME);
            if (searchFromRequest != null && searchFromRequest.listorder != null) {
            	search.listorder = searchFromRequest.listorder;
            }
        }
        if (search == null) {
            search = new PersonSearch();
        }

        PersonSearch sessionSearchPerson = (PersonSearch)cntx.sessionAsObject("search" + BEANNAME);

        if(sessionSearchPerson != null) {
            search.lastlistorder = sessionSearchPerson.listorder; /* Gets the last string order of the session SearchPerson object
            and set it to the new session. */
            search.sort = sessionSearchPerson.sort;
        }
        search.sortList = sortList;

        cntx.setSession("search" + BEANNAME, search);
        cntx.getContentObject().setField("personSearchField", search.listorder);
        cntx.getContentObject().setField("personSearchOrder", search.sort);
        return search;
    }

    //
    // geschützte Hilfsmethoden
    //
	private boolean getContextAsBoolean(OctopusContext cntx, String key) {
		return Boolean.valueOf(cntx.contentAsString(key)).booleanValue() ?
				true : cntx.requestAsBoolean(key).booleanValue();
	}

	/**
	 * Gibt eine Person-List-Filter Bedinung inkl. Mandanten Einschränkung zurück.
	 *
	 * @param cntx
	 * @throws BeanException
	 */
	protected Clause getPersonListFilter(OctopusContext cntx, boolean status) throws BeanException {
		WhereList list = new WhereList();

		String searchFiled = cntx.getRequestObject().getParamAsString("searchField");
		if (searchFiled == null) {
			addPersonListFilter(cntx, list);
		} else {
			addPersonListFilterSimple(cntx, searchFiled, list, status);
		}

		Where orgunitFilter = Expr.equal("tperson.fk_orgunit", ((PersonalConfigAA)cntx.personalConfig()).getOrgUnitId());
		if (list.size() == 0) {
		    return orgunitFilter;
		} else {
		    return Where.and(orgunitFilter, list);
		}
	}

	/**
	 * Erweitert die übergebene WhereList um Bedingungen der Suche.
	 * Die WhereList ist danach <strong>niemals</strong> leer.
	 *
	 * @param cntx
	 * @param list
	 * @throws BeanException
	 */
	private void addPersonListFilter(OctopusContext cntx, WhereList list) throws BeanException {
		PersonSearch search = getSearch(cntx);

		list.addAnd(Expr.equal("tperson.deleted", PersonConstants.DELETED_FALSE));

		/*
		 * modified to support search for individual workareas as per change request for version 1.2.0
		 * cklein
		 * 2008-02-21
		 */
        if (search.workarea != null) {
            list.addAnd(Expr.equal("tperson.fk_workarea", search.workarea));
        }

		if (search.categorie2 != null) {
			list.addAnd(Expr.equal("cat2.fk_categorie", search.categorie2));
		}
		if (search.city != null && search.city.length() != 0) {
			list.addAnd(DatabaseHelper.getWhere(search.city, new String[] {
					"city_a_e1",
					"city_a_e2",
					"city_a_e3",
					"city_b_e1",
					"city_b_e2",
					"city_b_e3",
					"city_c_e1",
					"city_c_e2",
					"city_c_e3" }));
		}
		if (search.country != null && search.country.length() != 0) {
			list.addAnd(DatabaseHelper.getWhere(search.country, new String[] {
					"country_a_e1",
					"country_a_e2",
					"country_a_e3",
					"country_b_e1",
					"country_b_e2",
					"country_b_e3",
					"country_c_e1",
					"country_c_e2",
					"country_c_e3" }));
		}
		if (search.company != null && search.company.length() != 0) {
			list.addAnd(DatabaseHelper.getWhere(search.company, new String[] {
					"company_a_e1",
					"company_a_e2",
					"company_a_e3",
					"company_b_e1",
					"company_b_e2",
					"company_b_e3",
					"company_c_e1",
					"company_c_e2",
					"company_c_e3" }));
		}
		if (search.importsource != null && search.importsource.length() != 0) {
            list.addAnd(DatabaseHelper.getWhere(search.importsource, new String[]{"importsource"}));
		}
		if (search.firstname != null && search.firstname.length() != 0) {
			list.addAnd(DatabaseHelper.getWhere(search.firstname, new String[] {
					"firstname_a_e1",
					"firstname_a_e2",
					"firstname_a_e3",
					"firstname_b_e1",
					"firstname_b_e2",
					"firstname_b_e3" }));
		}
		if (search.function != null && search.function.length() != 0) {
			list.addAnd(DatabaseHelper.getWhere(search.function, new String[] {
					"function_a_e1",
					"function_a_e2",
					"function_a_e3",
					"function_b_e1",
					"function_b_e2",
					"function_b_e3",
					"function_c_e1",
					"function_c_e2",
					"function_c_e3" }));
		}
		if (search.iscompany != null && search.iscompany.booleanValue()) {
			list.addAnd(Expr.equal("iscompany", PersonConstants.ISCOMPANY_TRUE));
		} else {
			list.addAnd(Expr.equal("iscompany", PersonConstants.ISCOMPANY_FALSE));
		}
		if (search.lastname != null && search.lastname.length() != 0) {
			list.addAnd(DatabaseHelper.getWhere(search.lastname, new String[] {
					"lastname_a_e1",
					"lastname_a_e2",
					"lastname_a_e3",
					"lastname_b_e1",
					"lastname_b_e2",
					"lastname_b_e3" }));
		}
		if (search.street != null && search.street.length() != 0) {
			list.addAnd(DatabaseHelper.getWhere(search.street, new String[] {
					"street_a_e1",
					"street_a_e2",
					"street_a_e3",
					"street_b_e1",
					"street_b_e2",
					"street_b_e3",
					"street_c_e1",
					"street_c_e2",
					"street_c_e3" }));
		}
		if (search.validdate != null && search.validtype != null) {
			Date end = new Date(search.validdate.getTime() + 86400000 - 1000);
			switch (search.validtype.intValue()) {
			case 1:
				list.addAnd(Expr.lessOrEqual("dateexpire", end));
				break;
			case 2:
				list.addAnd(Expr.greaterOrEqual("dateexpire", search.validdate));
				break;
			case 3:
				list.addAnd(Where.and(
						Expr.greaterOrEqual("dateexpire", search.validdate),
						Expr.lessOrEqual("dateexpire", end)));
				break;
			}
		}
		if (search.zipcode != null && search.zipcode.length() != 0) {
			list.addAnd(DatabaseHelper.getWhere(search.zipcode, new String[] {
					"zipcode_a_e1",
					"zipcode_a_e2",
					"zipcode_a_e3",
					"zipcode_b_e1",
					"zipcode_b_e2",
					"zipcode_b_e3",
					"zipcode_c_e1",
					"zipcode_c_e2",
					"zipcode_c_e3" }));
		}
		if (search.state != null && search.state.length() != 0) {
			list.addAnd(DatabaseHelper.getWhere(search.state, new String[] {
					"state_a_e1",
					"state_a_e2",
					"state_a_e3",
					"state_b_e1",
					"state_b_e2",
					"state_b_e3",
					"state_c_e1",
					"state_c_e2",
					"state_c_e3" }));
		}
		if (search.onlyhosts != null && search.onlyhosts.booleanValue()) {
			list.addAnd(Expr.in("tperson.pk", new RawClause(
					"(SELECT fk_host FROM veraweb.tevent)")));
		}
	}


	/**
	 * Erweitert die übergebene WhereList um Bedingungen der Suche.
	 * Die WhereList ist danach <strong>niemals</strong> leer.
	 *
	 * @param cntx
	 * @param searchField
	 * @throws BeanException
	 */
	private void addPersonListFilterSimple(OctopusContext cntx, String searchField, WhereList list2, boolean status) throws BeanException {
		PersonSearch search = getSearch(cntx);

		/*
		 * modified to support search for individual workareas as per change request for version 1.2.0
		 * cklein
		 * 2008-02-21
		 */

		WhereList list = new WhereList();



		list.addOr(DatabaseHelper.getWhere(searchField, new String[] {
					"firstname_a_e1",
					"firstname_a_e2",
					"firstname_a_e3",
					"firstname_b_e1",
					"firstname_b_e2",
					"firstname_b_e3" }));

		list.addOr(DatabaseHelper.getWhere(searchField, new String[] {
					"lastname_a_e1",
					"lastname_a_e2",
					"lastname_a_e3",
					"lastname_b_e1",
					"lastname_b_e2",
					"lastname_b_e3" }));

		list.addOr(DatabaseHelper.getWhere(searchField, new String[] {
					"company_a_e1",
					"company_a_e2",
					"company_a_e3",
					"company_b_e1",
					"company_b_e2",
					"company_b_e3",
					"company_c_e1",
					"company_c_e2",
					"company_c_e3" }));

		list.addOr(DatabaseHelper.getWhere(searchField, new String[] {
					"fon_a_e1",
					"fon_a_e2",
					"fon_a_e3",
					"fon_b_e1",
					"fon_b_e2",
					"fon_b_e3",
					"fon_c_e1",
					"fon_c_e2",
					"fon_c_e3" }));

		list.addOr(DatabaseHelper.getWhere(searchField, new String[] {
					"fax_a_e1",
					"fax_a_e2",
					"fax_a_e3",
					"fax_b_e1",
					"fax_b_e2",
					"fax_b_e3",
					"fax_c_e1",
					"fax_c_e2",
					"fax_c_e3" }));

		list.addOr(DatabaseHelper.getWhere(searchField, new String[] {
					"mobil_a_e1",
					"mobil_a_e2",
					"mobil_a_e3",
					"mobil_b_e1",
					"mobil_b_e2",
					"mobil_b_e3",
					"mobil_c_e1",
					"mobil_c_e2",
					"mobil_c_e3" }));

		list.addOr(DatabaseHelper.getWhere(searchField, new String[] {
					"mail_a_e1",
					"mail_a_e2",
					"mail_a_e3",
					"mail_b_e1",
					"mail_b_e2",
					"mail_b_e3",
					"mail_c_e1",
					"mail_c_e2",
					"mail_c_e3" }));

		list.addOr(DatabaseHelper.getWhere(searchField, new String[] {
		"tcategorie.catname" }));

		if (status) {
			list.addOr(DatabaseHelper.getWhere(searchField, new String[] {
						"tworkarea.name" }));
		}

		list.addOr(DatabaseHelper.getWhere(searchField, new String[] {
				"function_a_e1",
				"function_a_e2",
				"function_a_e3",
				"function_b_e1",
				"function_b_e2",
				"function_b_e3",
				"function_c_e1",
				"function_c_e2",
				"function_c_e3" }));

		list.addOr(DatabaseHelper.getWhere(searchField, new String[] {
					"note_a_e1",
					"note_b_e1" }));

		list.addOr(DatabaseHelper.getWhere(searchField, new String[] {
				"city_a_e1",
				"city_a_e2",
				"city_a_e3",
				"city_b_e1",
				"city_b_e2",
				"city_b_e3",
				"city_c_e1",
				"city_c_e2",
				"city_c_e3" }));

		list.addOr(DatabaseHelper.getWhere(searchField, new String[] {
				"street_a_e1",
				"street_a_e2",
				"street_a_e3",
				"street_b_e1",
				"street_b_e2",
				"street_b_e3",
				"street_c_e1",
				"street_c_e2",
				"street_c_e3" }));

		list.addOr(DatabaseHelper.getWhere(searchField, new String[] {
				"zipcode_a_e1",
				"zipcode_a_e2",
				"zipcode_a_e3",
				"zipcode_b_e1",
				"zipcode_b_e2",
				"zipcode_b_e3",
				"zipcode_c_e1",
				"zipcode_c_e2",
				"zipcode_c_e3" }));



		list2.addAnd(Where.and(Expr.equal("tperson.deleted", PersonConstants.DELETED_FALSE), list));

//		list.addAnd(Expr.in("tperson.fk_workarea", new RawClause(
//				"(SELECT name FROM veraweb.tworkarea)")));

//		list.addOr( Expr.equal( "tperson.fk_workarea", searchField ) );

		return;
	}



}