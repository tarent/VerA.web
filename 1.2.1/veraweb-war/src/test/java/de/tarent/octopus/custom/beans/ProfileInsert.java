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

/*
 * $Id: ProfileInsert.java,v 1.1 2007/06/20 11:56:52 christoph Exp $
 * 
 * Created on 18.11.2005
 */
package de.tarent.octopus.custom.beans;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import junit.framework.TestCase;
import de.tarent.octopus.client.OctopusConnection;
import de.tarent.octopus.client.OctopusResult;

/**
 * Diese Testklasse dient dem Profilen des Zeitverhaltens beim Erstellen
 * von Insert-Statements, das deutlich l�nger braucht als das Erstellen von
 * analogen Where-Klauseln.
 * 
 * @author mikel
 */
public class ProfileInsert extends TestCase {
    private OctopusConnection con;
    private static Logger baseLogger = null;
    private FileHandler fileLogHandler = null;
    
    public void testProfile() {
    	if (con == null)
    		return;
    	
    	Map taskParamsTestProfile = new TreeMap();
    	taskParamsTestProfile.put("module", "veraweb");
        taskParamsTestProfile.put("username", "pol-2");
        taskParamsTestProfile.put("password", "Benutzer99");
        taskParamsTestProfile.put("count", "10");
        
        ProfileLogger pLog = new ProfileLogger();
        OctopusResult res = con.callTask("testProfile", taskParamsTestProfile);
        pLog.log("testProfile");
        printResult(res, "testProfile-Result:" );
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        baseLogger = Logger.getLogger("de.tarent");
        baseLogger.setLevel(Level.ALL);
        baseLogger.addHandler(new ConsoleHandler());
//        con = OctopusConnectionFactory.getInstance().getConnection("test");
    }

    @Override
    protected void tearDown() throws Exception {
    	con = null;
        super.tearDown();
    }

    

    static void printResult(OctopusResult res, String title) {
        if (res == null)
            return;
        System.out.println(title);
        for (Iterator iter = res.getDataKeys(); iter.hasNext();) {
            String key = (String)iter.next();
            System.out.println("  "+key+ "="+res.getData(key));
        }
        System.out.println();        
    }
}
