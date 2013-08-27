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

import de.tarent.octopus.server.OctopusContext;

/**
 * Einfacher Worker, der User-Aktionen verwaltet und dazu den
 * Parameter 'action' aus dem Request in der Session verwaltet
 * und in den Content stellt.
 * 
 * @author christoph
 */
public class ActionWorker {
    /** Octopus-Eingabe-Parameter f�r {@link #load(OctopusContext, String)} */
	public static final String[] INPUT_load = { "action" };
    /** Octopus-Eingabepflicht-Parameter f�r {@link #load(OctopusContext, String)} */
	public static final boolean[] MANDATORY_load = { false };
    /** Octopus-Ausgabe-Parameter f�r {@link #load(OctopusContext, String)} */
	public static final String OUTPUT_load = "action";
    /**
     * Diese Worker-Aktion ermittelt aus Request und Session die aktuelle
     * Aktion, legt sie in der Session ab und gibt sie zur�ck.<br>
     * TODO: action Parameter Hack entfernen, siehe eventDetail.vm,
     *       dort kann man sonst die form-action nicht setzten!!
     * 
     * @param cntx Octopus-Kontext
     * @param action optionaler Parameter "action"
     * @return aktuelle Aktion
     */
	public String load(OctopusContext cntx, String action) {
		String a = cntx.requestAsString("a");
		if (a != null && a.length() != 0)
			action = a;
		if (action == null)
			action = cntx.sessionAsString("action");
		if (action == null)
			action = "";
		cntx.setSession("action", action);
		return action;
	}

    /** Octopus-Eingabe-Parameter f�r {@link #remove(OctopusContext, String)} */
	public static final String[] INPUT_remove = { "action" };
    /** Octopus-Eingabepflicht-Parameter f�r {@link #remove(OctopusContext, String)} */
	public static final boolean[] MANDATORY_remove = { false };
    /** Octopus-Ausgabe-Parameter f�r {@link #remove(OctopusContext, String)} */
	public static final String OUTPUT_remove = "action";
    /**
     * Diese Methode l�scht die aktuelle Aktion und ersetzt sie gegebenenfalls
     * durch die �bergebene. 
     * 
     * @param cntx Octopus-Kontext
     * @param action optionaler Parameter "action"
     * @return aktuelle Aktion
     */
	public String remove(OctopusContext cntx, String action) {
		cntx.setSession("action", action);
		return action;
	}
}
