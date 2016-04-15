/**
 * veraweb, platform independent webservice-based event management
 * (Veranstaltungsmanagment VerA.web), is
 * Copyright © 2004–2008 tarent GmbH
 * Copyright © 2013–2016 tarent solutions GmbH
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

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import de.tarent.aa.veraweb.beans.OrgUnit;
import de.tarent.aa.veraweb.beans.User;
import de.tarent.aa.veraweb.beans.UserConfig;
import de.tarent.aa.veraweb.utils.LocaleMessage;
import de.tarent.aa.veraweb.utils.VworConstants;
import de.tarent.aa.veraweb.utils.VworUtils;
import de.tarent.dblayer.sql.clause.Expr;
import de.tarent.dblayer.sql.clause.Where;
import de.tarent.dblayer.sql.statement.Select;
import de.tarent.octopus.PersonalConfigAA;
import de.tarent.octopus.beans.BeanException;
import de.tarent.octopus.beans.Database;
import de.tarent.octopus.beans.TransactionContext;
import de.tarent.octopus.beans.veraweb.DatabaseVeraWeb;
import de.tarent.octopus.server.OctopusContext;

/**
 * Dieser Octopus-Worker stellt Aktionen zum laden und speichern
 * von Benutzereinstellungen zur Verfügung.
 *
 * @author Christoph Jerolimov
 * @version $Revision: 1.1 $
 */
public class UserConfigWorker {
	public static final String PARAMS_STRING[] = {
		"personTab", "personMemberTab",
		"personAddresstypeTab", "personLocaleTab" };
	private static final String PARAMS_BOOLEAN[] = {
		"guestListFunction", "guestListCity", "guestListPhone", "personListState" };

	/** Octopus-Eingabe-Parameter für {@link #init(OctopusContext)} */
	public static final String INPUT_init[] = {};
	/** Octopus-Ausgabe-Parameter für {@link #init(OctopusContext)} */
	public static final String OUTPUT_init = "userConfig";
	/**
	 * Lädt die Konfiguration aus der Datenbank in die Session.
	 *
	 * @param octopusContext
	 * @throws BeanException
	 * @throws IOException
	 */
	public Map init(OctopusContext octopusContext) throws BeanException, IOException {
		Database database = new DatabaseVeraWeb(octopusContext);
		Integer userId = ((PersonalConfigAA)octopusContext.personalConfig()).getVerawebId();
		if (userId == null) return null;

		Select select = database.getSelect("UserConfig");
		select.where(Expr.equal("fk_user", userId));

		Map result = new HashMap();
		for (Iterator it = database.getList(select, database).iterator(); it.hasNext(); ) {
			Map data = (Map)it.next();
			result.put(data.get("key"), data.get("value"));
		}

		/* check if REST API is available */
		VworUtils vworUtils = new VworUtils();
		String vworAvailable;
		try {
			vworAvailable = LocaleMessage.formatTextToHtmlString(vworUtils.readResource(vworUtils.path(VworConstants.AVAILABLE)));
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			final String emsg = e.getMessage();
			final String elmsg = e.getLocalizedMessage();

			if (emsg != null && !emsg.equals(elmsg)) {
				sb.append(LocaleMessage.formatTextToHtmlString(e.toString() + " (" + emsg + ")"));
			} else {
				sb.append(LocaleMessage.formatTextToHtmlString(e.toString()));
			}

			for (StackTraceElement element : e.getStackTrace()) {
				sb.append("<br />");
				sb.append(LocaleMessage.formatTextToHtmlString(element.toString()));
			}
			vworAvailable = sb.toString();
		}
		result.put("restApiAvail", vworAvailable);

		/*
		 * modified to support display of orgunit as per change request for version 1.2.0
		 *
		 * cklein
		 * 2008-02-15
		 */
		User user = ( User ) database.getBean( "User", userId );
		OrgUnit orgUnit = new OrgUnit();

		if ( user != null && user.orgunit != null && user.orgunit.intValue() != 0 )
		{
			orgUnit = ( OrgUnit ) database.getBean( "OrgUnit", user.orgunit );
		}

		octopusContext.setContent( "orgUnit", orgUnit );
		octopusContext.setSession("userConfig", result);
		return result;
	}

	/** Octopus-Eingabe-Parameter für {@link #load(OctopusContext)} */
	public static final String INPUT_load[] = {};
	/** Octopus-Ausgabe-Parameter für {@link #load(OctopusContext)} */
	public static final String OUTPUT_load = "userConfig";
	/**
	 * Lädt die Konfiguration aus der Session in den Content.
	 *
	 * @param octopusContext
	 * @throws BeanException
	 * @throws IOException
	 */
	public Map load(OctopusContext octopusContext) throws BeanException, IOException {
		Map result = (Map)octopusContext.sessionAsObject("userConfig");
		if (result == null) {
			return init(octopusContext);
		}
		return result;
	}

	/** Octopus-Eingabe-Parameter für {@link #save(OctopusContext)} */
	public static final String INPUT_save[] = {};
	/**
	 * Speichert die Benutzer Einstellungen in der Datenbank.
	 *
	 * @param octopusContext
	 * @throws BeanException
	 * @throws IOException
	 */
	public void save(OctopusContext octopusContext) throws BeanException, IOException {
		if (!octopusContext.requestContains("save")) {
			return;
		}

		Database database = new DatabaseVeraWeb(octopusContext);
		Integer userId = ((PersonalConfigAA)octopusContext.personalConfig()).getVerawebId();
		Map userConfig = (Map)octopusContext.contentAsObject("userConfig");

		for (int i = 0; i < PARAMS_STRING.length; i++) {
			String key = PARAMS_STRING[i];
			String value = octopusContext.requestAsString(key);

			if (value == null) {
			    continue;
			}
			setUserSetting(database, userId, userConfig, key, value);
		}

		for (int i = 0; i < PARAMS_BOOLEAN.length; i++) {
			String key = PARAMS_BOOLEAN[i];
			boolean value = octopusContext.requestAsBoolean(key).booleanValue();
			if (value) {
				setUserSetting(database, userId, userConfig, key, "true");
			} else {
				removeUserSetting(database, userId, userConfig, key);
			}
		}

		octopusContext.setContent("saveSuccess", true);
	}

	protected void removeUserSetting(Database database, Integer userId, Map userConfig, String key) throws BeanException, IOException {
		String old = (String)userConfig.get(key);
		if (old != null) {

			final TransactionContext transactionContext = database.getTransactionContext();
			transactionContext.execute(database.getDelete("UserConfig").
					where(Where.and(
							Expr.equal("fk_user", userId),
							Expr.equal("name", key))));
			transactionContext.commit();

			userConfig.remove(key);
		}
	}

	protected void setUserSetting(Database database, Integer userId, Map userConfig, String key, String value) throws BeanException, IOException {
		String old = (String)userConfig.get(key);
		if (value == null) {
			removeUserSetting(database, userId, userConfig, key);
		} else if (old == null) {
			UserConfig config = new UserConfig();
			config.user = userId;
			config.key = key;
			config.value = value;
			database.saveBean(config);
			userConfig.put(key, value);
		} else if (!value.equals(old)) {

			final TransactionContext transactionContext = database.getTransactionContext();
			transactionContext.execute(database.getUpdate("UserConfig").
					update("value", value).
					where(Where.and(
							Expr.equal("fk_user", userId),
							Expr.equal("name", key))));
			transactionContext.commit();

			userConfig.put(key, value);
		}
	}
}
