/* $Id: TcContent.java,v 1.2 2006/06/21 13:44:37 jens Exp $
 * tarent-octopus, Webservice Data Integrator and Applicationserver
 * Copyright (C) 2002 tarent GmbH
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 * 
 * tarent GmbH., hereby disclaims all copyright
 * interest in the program 'tarent-octopus'
 * (which makes passes at compilers) written
 * by Sebastian Mancke and Michael Klink.
 * signature of Elmar Geese, 1 June 2002
 * Elmar Geese, CEO tarent GmbH
 */

package de.tarent.octopus.content;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/** 
 * Kontainer zur Speicherung der Daten die von den ContentWorkern besorgt wurden.
 * <br>
 * Der Container kann auch Fehlermeldungen aufnehmen.
 * <br>
 * Datenfelder, die hierin gespeichert werden, sollten einen zusammen gesetzten Key
 * bekommen, damit Konflikte durch gleiche Namen aus verschiedenen Kontexten vermieden werden.
 * <br>
 * Der Kontainer ist als Baum von Maps, Arrays und Strings organisiert. Um trotzdem einen
 * einfachen Zugriff darauf zu erm�glichen, unterst�tzen alle Methoden eine Punktnotation bei den Keys.
 * <br><br>
 * Beispiel: address.name bezeichnet den Wert, der in der Map address zu 'name' abgelegt ist. 
 * <br>Beispiel: address.fon.2 bezeichnet der 2. Element des Array, das unter 'fon' in der Map address abgelegt ist.
 * <br><br>
 * Wenn ein Wert unter einem solchen Key abgelegt wird und die daruterliegende Struktur noch nicht existiert, wird sie automatisch erstellt.
 * Bei dieser automatischen erstellung werden f�r jede Stufe immer Map angelegt, nie Arrays.
 * <br><br>
 * Da die benutzten Speicherstrukturen nicht mit null-Pointern umgehen k�nnen, wird �berall darauf getestet.
 * Wenn ein key oder value ein null-Pointer ist, kehrt die Methode einfach zur�ck und meldet keinen Fehler.
 * 
 * 
 * @author <a href="mailto:mancke@mancke-software.de">Sebastian Mancke</a>, <b>tarent GmbH</b>
 */
public class TcContent {
    /**
     * Enth�lt ein K�rzel, da� das Ergebniss der Aktion charakterisiert.
     */
    private String status;

    /** Die Content Daten */
    private Map theContent;

    public Map getContent() {
        return theContent;
    }

    /**
     * Initialisiert den Content mit leeren Feldern und dem Status "ok".
     */
    public TcContent() {
        theContent = new HashMap();
        setStatus(TcContentWorker.RESULT_ok);
    }

    /**
     * Initialisiert den Content mit dem Status "error"
     * und setzt die Felder 'status.message', sowie 'status.detailMessage'
     * auf den Meldungen aus der Exception
     *
     * @param e Exception, deren Meldungen aufgetreten sind.
     */
    public TcContent(Exception e) {
        theContent = new HashMap();
        setError(e);
    }

    /**
     * Setzt dem Status "error"
     * und setzt die Felder 'status.message', sowie 'status.detailMessage'
     * auf den Meldungen aus der Exception
     *
     * @param e Exception, deren Meldungen aufgetreten sind.
     */
    public void setError(Exception e) {
        if (e == null) {
            setField("status.message", "Es ist ein unbekannter Fehler auf getreten!");
            setField("status.detailMessage", "Es ist ein unbekannter Fehler auf getreten!");
        } else {
            setField("status.detailMessage", "Es ist ein Fehler auf getreten: " + e);
            setField("status.exception", e);
            if (e.getMessage() != null)
                setField("status.message", e.getMessage());
            else
                setField("status.message", e);
        }
        setStatus("error");
    }

    /**
     * Setzt dem Status "error"
     * und setzt die Felder 'status.message', sowie 'status.detailMessage'
     * auf den Meldungen aus des Strings
     *
     * @param message Message, die gesetzt werden soll.
     */
    public void setError(String message) {
        setField("status.message", message);
        setField("status.detailMessage", message);
        setStatus("error");
    }

    /**
     * Setzt das Ergebniss der Aktion
     * @param status K�rzel f�r die Aktion. (z.B. "ok" oder "error")
     */
    public void setStatus(String status) {
        this.status = status;
        setField("status.code", status);
    }

    /**
     * Gibt ein K�rzel zur�ck, da� das Ergebniss der Aktion charakterisiert.
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * Liefert die Keys der belegten Felder
     */
    public Iterator getKeys() {
        return theContent.keySet().iterator();
    }

    /**
     * Gibt ein Feld als String zur�ck.
     * 
     * @param key Der Key des Fedes
     */
    public String getAsString(String key) {
        Object field = getAsObject(key);
        if (field != null)
            return field.toString();
        else
            return null;
    }

    /**
     * Gibt ein Feld als String zur�ck.
     * kurzform f�r getAsObject()
     *
     * @param key Der Key des Fedes
     */
    public Object get(String key) {
        return getAsObject(key);
    }

    /**
     * Gibt ein Feld als Object zur�ck.
     *
     * @param key Der Key des Feldes
     */
    public Object getAsObject(String key) {

        if (key == null || key.length() == 0)
            return null;

        StringTokenizer st = new StringTokenizer(key, ".");
        String token = st.nextToken();
        Object node = theContent;
        Object newNode;
        int index;
        while (st.hasMoreTokens()) {

            if (node instanceof Map) {
                newNode = ((Map) node).get(token);
            } else if (node instanceof List) {
                index = 0;
                try { // Wenn der key nicht g�ltig ist, geben wir einfach zur�ck
                    index = Integer.parseInt(token);
                } catch (Exception e) {
                    return null;
                }
                if (index < 0 || index >= ((List) node).size())
                    return null;
                newNode = ((List) node).get(index);
            } else
                return null;

            if (newNode == null)
                return null;

            node = newNode;
            token = st.nextToken();
        }

        if (node instanceof Map)
            return ((Map) node).get(token);
        else if (node instanceof List) {
            index = 0;
            try { // Wenn der key nicht g�ltig ist, geben wir einfach null zur�ck 
                index = Integer.parseInt(token);
            } catch (Exception e) {
                return null;
            }
            if (index < 0 || index >= ((List) node).size())
                return null;
            return ((List) node).get(index);
        } else
            return null;
    }

    /**
     * Setzt ein String Feld.
     * 
     * @param key Der Key, unter dem die Daten gespeichert werden sollen.
     * @param value Der Inhalt
     */
    public void setField(String key, String value) {
        setField(key, (Object) value);
    }

    /**
     * Setzt ein Feld von Maps.
     * 
     * @param key Der Key, unter dem die Daten gespeichert werden sollen.
     * @param data Die Daten
     */
    public void setField(String key, Map data) {
        setField(key, (Object) data);
    }

    /**
     * Setzt ein Feld mit einem Vector.
     * 
     * @param key Der Key, unter dem die Daten gespeichert werden sollen.
     * @param data Die Daten
     */
    public void setField(String key, List data) {
        setField(key, (Object) data);
    }
    
    /**
     * Setzt ein Feld mit einem Integer.
     * 
     * @param key Der Key, unter dem die Daten gespeichert werden sollen.
     * @param data Die Daten
     */
    public void setField(String key, Integer data) {
        setField(key, (Object) data);
    }

    
    /**
     * Setzen eines Feldes von einem beliebigen Typ.
     * Ist Private, da nur Lists, Maps und Strings gesetz werden sollen.
     * bei Fehlern wird einfach zur�ck gekehrt.
     */
    public void setField(String key, Object data) {
        if (key == null || "".equals(key))
            return;

        StringTokenizer st = new StringTokenizer(key, ".");
        String token = st.nextToken();
        Object node = theContent;
        Object newNode;
        int index;
                
        while (st.hasMoreTokens()) {

            if (node instanceof Map) {
                newNode = ((Map) node).get(token);
                if (newNode == null) {
                    newNode = new HashMap();
                    ((Map) node).put(token, newNode);
                }
            } else if (node instanceof List) {
                index = 0;
                try { // Wenn der key nicht g�ltig ist, geben wir einfach zur�ck
                    index = Integer.parseInt(token);
                } catch (Exception e) {
                    return;
                }
                if (index < 0)
                    return;
//                if (index >= ((List) node).size())
//                     ((Vector) node).setSize(index + 1);
                newNode = ((List) node).get(index);
                if (newNode == null) {
                    newNode = new HashMap();
                    ((List) node).set(index, newNode);
                }
            } else
                return;

            node = newNode;
            token = st.nextToken();
        }

        if (node instanceof Map)
             ((Map) node).put(token, data);
        else if (node instanceof List) {
            index = 0;
            try { // Wenn der key nicht g�ltig ist, h�ngen wir einfach an.
                index = Integer.parseInt(token);
            } catch (Exception e) {
                index = ((List) node).size();
            }
            if (index < 0)
                return;
//            if (index >= ((Vector) node).size())
//                 ((Vector) node).setSize(index + 1);
            ((List) node).set(index, data);
        }
    }

    /**
     * String r�pr�sentation z.B. f�r Debugausgaben.
     */
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("TcContent:\n");
        sb.append("Bearbeitungsstatus Status: " + status + "\n");
        sb.append("Daten:\n" + toString("", theContent));

        return "" + sb;
    }

    private static String toString(String prefix, Object o) {

        if (o == null)
            return "null";

        StringBuffer sb = new StringBuffer();
        if (o instanceof Map) {
            sb.append("\n" + prefix + "{\n");
            Map theContent = (Map) o;
            for (Iterator e = theContent.keySet().iterator(); e.hasNext();) {
               	Object key = e.next();
				Object val = theContent.get(key);
				sb.append("     " + prefix + key + " => " + toString(prefix + "     ", val));
            }
            sb.append("\n" + prefix + "}\n");
        } else if (o instanceof List) {
            sb.append("\n" + prefix + "[\n");
            List vector = (List) o;
            for (int i = 0; i < vector.size(); i++) {
                sb.append("     " + prefix + i + " => " + toString(prefix + "     ", vector.get(i)));
            }
            sb.append("\n" + prefix + "]\n");
        } else {
            sb.append(o.toString() + "\n");
        }

        return "" + sb;
    }
}
