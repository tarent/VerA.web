package de.tarent.commons.datahandling;

/*-
 * Veranstaltungsmanagement VerA.web, comprised of…
 * VerA.web, platform-independent webservice-based event management
 * tarent-commons, a set of common components and solutions
 * tarent-contact, platform-independent webservice-based contact management
 * tarent-database, jdbc database library
 * tarent-doctor, Document Generation Platform
 * tarent-octopus, Webservice Data Integrator and Application Server
 * … is newly MIT-licenced and Copyright
 *  © 2018 Атанас Александров (sirakov@gmail.com)
 *  © 2014, 2015, 2016, 2017 Атанас Александров (a.alexandrov@tarent.de)
 *  © 2013 Иванка Александрова (i.alexandrova@tarent.de)
 *  © 2005, 2006, 2007 asteban (s.mancke@tarent.de)
 *  © 2013 Patrick Apel (p.apel@tarent.de)
 *  © 2016 Eugen Auschew (e.auschew@tarent.de)
 *  © 2013 Andrei Boulgakov (a.boulgakov@tarent.de)
 *  © 2013 Valentin But (v.but@tarent.de)
 *  © 2016 Lukas Degener (l.degener@tarent.de)
 *  © 2017 Axel Dirla (a.dirla@tarent.de)
 *  © 2015 Julian Drawe (j.drawe@tarent.de)
 *  © 2009 Sven Frommeyer (s.frommeyer@tarent.de)
 *  © 2014, 2018 Dominik George (d.george@tarent.de)
 *  © 2013 Martin Gernhardt (m.gernhardt@tarent.de)
 *  © 2013 Sascha Girrulat (s.girrulat@tarent.de)
 *  © 2007, 2008 David Goemans (d.goemans@tarent.de)
 *  © 2018 Christian Gorski (c.gorski@tarent.de)
 *  © 2015 Viktor Hamm (v.hamm@tarent.de)
 *  © 2013 Katja Hapke (k.hapke@tarent.de)
 *  © 2006, 2007, 2010, 2013 Hendrik Helwich (h.helwich@tarent.de)
 *  © 2018 Thomas Hensel (t.hensel@tarent.de)
 *  © 2018, 2019 Benedict Hoeger (b.hoeger@tarent.de)
 *  © 2018, 2019 Titian Horvath (t.horvath@tarent.de)
 *  © 2005, 2006, 2007, 2008 Christoph Jerolimov (jerolimov@gmx.de)
 *  © 2018, 2019 Timo Kanera (t.kanera@tarent.de)
 *  © 2006 Philipp Kirchner (p.kirchner@tarent.de)
 *  © 2008, 2009, 2010 Carsten Klein (c.klein@tarent.de)
 *  © 2006 Michael Kleinhenz (m.kleinhenz@tarent.de)
 *  © 2006 Michael Klink (m.klink@tarent.de)
 *  © 2007 Fabian Köster (f.koester@tarent.de)
 *  © 2006, 2014 Martin Ley (m.ley@tarent.de)
 *  © 2007 Alex Maier (a.maier@tarent.de)
 *  © 2014, 2015 Max Marche (m.marche@tarent.de)
 *  © 2007 Jan Meyer (jan@evolvis.org)
 *  © 2007, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020
 *     mirabilos (t.glaser@tarent.de)
 *  © 2016 Cristian Molina (c.molina@tarent.de)
 *  © 2006, 2007 Jens Neumaier (j.neumaier@tarent.de)
 *  © 2006 Nils Neumaier (n.neumaier@tarent.de)
 *  © 2018 Yorka Neumann (y.neumann@tarent.de)
 *  © 2017 Michael Nienhaus (m.nienhaus@tarent.de)
 *  © 2013 Claudia Nuessle (c.nuessle@tarent.de)
 *  © 2014, 2015 Jon Nuñez Alvarez (j.nunez-alvarez@tarent.de)
 *  © 2016 Jens Oberender (j.oberender@tarent.de)
 *  © 2016, 2017 Miluška Pech (m.pech@tarent.de)
 *  © 2007, 2008, 2009 Martin Pelzer (m.pelzer@tarent.de)
 *  © 2008, 2009 Christian Preilowski (c.thiel@tarent.de)
 *  © 2013 Marc Radel (m.radel@tarent.de)
 *  © 2013 Sebastian Reimers (s.reimers@tarent.de)
 *  © 2015 Charbel Saliba (c.saliba@tarent.de)
 *  © 2006, 2008, 2009, 2010 Thomas Schmitz (t.schmitz@tarent.de)
 *  © 2013 Volker Schmitz (v.schmitz@tarent.de)
 *  © 2014 Sven Schumann (s.schumann@tarent.de)
 *  © 2007 Robert Schuster (r.schuster@tarent.de)
 *  © 2014 Sevilay Temiz (s.temiz@tarent.de)
 *  © 2013 Kevin Viola Schmitz (k.schmitz@tarent.de)
 *  © 2008, 2015 Stefan Walenda (s.walenda@tarent.de)
 *  © 2015, 2016, 2017 Max Weierstall (m.weierstall@tarent.de)
 *  © 2013 Rebecca Weinz (r.weinz@tarent.de)
 *  © 2015, 2016 Stefan Weiz (s.weiz@tarent.de)
 *  © 2015, 2016 Tim Zimmer (t.zimmer@tarent.de)
 * and older code, Copyright © 2001–2008 ⮡ tarent GmbH and contributors.
 * Licensor is tarent solutions GmbH, http://www.tarent.de/
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import de.tarent.commons.utils.StringTools;
import de.tarent.commons.utils.Tools;

/**
 * Class for accessing attributes via reflection
 *
 * @author tim
 */

/*
 * Aus der Java-Beans Spezifikation, Version 1.01:
 *
 *   8.3.1 Simple properties
 *
 *   By default, we use design patterns to locate properties by looking for methods of the form:
 *
 *      public < PropertyType> get< PropertyName>();
 *      public void set< PropertyName>(< PropertyType> a);
 *
 *   If we discover a matching pair of get<PropertyName> and set<PropertyName> methods
 *   that take and return the same type, then we regard these methods as defining a read-write property
 *   whose name will be <propertyName>. We will use the get<PropertyName> method
 *   to get the property value and the set<PropertyName> method to set the property value. The
 *   pair of methods may be located either in the same class or one may be in a base class and the
 *   other may be in a derived class.
 *
 *   If we find only one of these methods, then we regard it as defining either a read-only or a writeonly
 *   property called <propertyName>
 *
 *   By default we assume that properties are neither bound nor constrained (see Section 7).
 *   So a simple read-write property foo might be represented by a pair of methods:
 *
 *      public Wombat getFoo();
 *      public void setFoo(Wombat w);
 *
 *   8.3.2 Boolean properties
 *
 *   In addition, for boolean properties, we allow a getter method to match the pattern:
 *   public boolean is< PropertyName>();
 *
 *   This is<PropertyName> method may be provided instead of a get<PropertyName> method,
 *   or it may be provided in addition to a get<PropertyName> method.
 *   In either case, if the is<PropertyName> method is present for a boolean property then we will
 *   use the is<PropertyName> method to read the property value.
 *
 *   An example boolean property might be:
 *
 *      public boolean isMarsupial();
 *      public void setMarsupial(boolean m);
 */

public class BeanMap implements Map {

    private class Property {
        String name;
        Class type;
        boolean canBeRead;
        boolean canBeWritten;

        public Property(String name, Class type, boolean canBeRead, boolean canBeWritten) {
            this.name = name;
            this.type = type;
            this.canBeRead = canBeRead;
            this.canBeWritten = canBeWritten;
        }

        public boolean isCanBeRead() {
            return canBeRead;
        }

        public void setCanBeRead(boolean canBeRead) {
            this.canBeRead = canBeRead;
        }

        public boolean isCanBeWritten() {
            return canBeWritten;
        }

        public void setCanBeWritten(boolean canBeWritten) {
            this.canBeWritten = canBeWritten;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Class getType() {
            return type;
        }

        public void setType(Class type) {
            this.type = type;
        }
    }

    /**
     * Alle Methoden dieser Bean.
     */
    protected Method[] methods = null;

    /**
     * HashSet-Repraesentation dieser Bean
     */
    protected HashMap properties;

    public BeanMap() {
        super();
        this.methods = this.getMethods();
        this.properties = this.getPropertyMap();
    }

    /**
     * Returns all methods of this class, except those that are inheritet from Map and Object.
     */
    private Method[] getMethods() {
        Method[] allMethods = this.getClass().getMethods();
        Vector filteredMethods = new Vector();
        for (int i = 0; i < allMethods.length; i++) {
            if (!Tools.arrayContains(BeanMap.class.getMethods(), allMethods[i])) {
                filteredMethods.add(allMethods[i]);
            }
        }
        Method[] returnMethods = new Method[filteredMethods.size()];
        for (int i = 0; i < returnMethods.length; i++) {
            returnMethods[i] = (Method) filteredMethods.get(i);
        }
        return returnMethods;
    }

    /**
     * Returns the get method appropriate to {@code attribute}.
     *
     * @param attribute  the attribute corresponding to the searched get method
     * @param ignoreCase true, if the case should be ignored and false otherwise
     * @return the get-method appropriate to {@code attribute} or null, if none is found.
     */
    private Method getMatchingGetMethod(String attribute, boolean ignoreCase) {
        for (int i = 0; i < methods.length; i++) {
            String methodCandidate = methods[i].getName();
            if ((!ignoreCase &&
              methodCandidate.equals("get" + StringTools.capitalizeFirstLetter(attribute))) ||
              (ignoreCase &&
                methodCandidate.toLowerCase().equals("get" + attribute.toLowerCase()))) {
                //System.out.println("Returned " + methods[i].getName());
                return methods[i];
            }
        }

        return null;
    }

    /**
     * Liefert den Typ eines Attributs in dieser Bean zurück.
     *
     * @param attribute - Gesuchtes Attribut
     * @return Typ des Attributs
     * @throws NoSuchFieldException
     * @throws SecurityException
     */
    public Class getValueType(String attribute) throws SecurityException, NoSuchFieldException {
        if (!containsKey(attribute)) {
            throw new ClassCastException("Key not found.");
        }
        return this.getClass().getDeclaredField(attribute).getType();
    }

    /**
     * Diese Methode gibt den korrespondierenden Wert eines übergebenen Keys
     * in der (virtuellen) Map zurück.
     *
     * @param key - Key des gesuchten Werts.
     * @return Wert, oder null, falls Key nicht gefunden.
     */
    private Object getValue(Object key) throws NoSuchFieldException {
        return getValueCase(key, false);
    }

    /**
     * Returns the value of the attribute specified by {@code key}. If {@code ignoreCase}
     * is true, the case is ignored, otherwise not.
     *
     * @param key        the name of the attribute
     * @param ignoreCase true, if the case schould be ignored, otherwise false.
     * @return the value of the attribute specified by {@code key}
     * @throws NoSuchFieldException if no corresponding attribtue could be found
     */
    private Object getValueCase(Object key, boolean ignoreCase) throws NoSuchFieldException {
        Iterator iter = properties.keySet().iterator();
        while (iter.hasNext()) {
            String aPropertyName = (String) iter.next();
            if ((!ignoreCase && aPropertyName.equals(key)) ||
              (ignoreCase && aPropertyName.toLowerCase().equals(
                ((String) key).toLowerCase()))) {
                try {
                    Method thisMethod = this.getMatchingGetMethod(aPropertyName, ignoreCase);
                    Object thisValue = thisMethod.invoke(this, new Object[] {});
                    return thisValue;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        // Key wurde nicht gefunden
        throw new NoSuchFieldException();
    }

    /**
     * Diese Methode gibt den korrespondierenden Wert eines übergebenen Keys
     * in der (virtuellen) Map zurück und ignoriert dabei die Groß-Kleinschreibung.
     *
     * @param key - Key des gesuchten Werts.
     * @return Wert, oder null, falls Key nicht gefunden.
     */
    private Object getValueIgnoreCase(Object key) throws NoSuchFieldException {
        return getValueCase(key, true);
    }

    /**
     * Setzt die Bean-Properties in ein HashSet um.
     */
    private HashMap getPropertyMap() {
        // Erster Pass - holen der Kandidaten
        HashMap properties = new HashMap();
        for (int i = 0; i < methods.length; i++) {
            Method thisMethod = methods[i];
            if (thisMethod.getName().startsWith("get")) {
                String pureName = StringTools.minusculizeFirstLetter(thisMethod.getName().replaceFirst("get", ""));
                properties.put(pureName, new Property(pureName, methods[i].getReturnType(), true, false));
            } else if (thisMethod.getName().startsWith("is")) {
                String pureName = StringTools.minusculizeFirstLetter(thisMethod.getName().replaceFirst("is", ""));
                properties.put(pureName, new Property(pureName, methods[i].getReturnType(), true, false));
            } else if (thisMethod.getName().startsWith("set")) {
                String pureName = StringTools.minusculizeFirstLetter(thisMethod.getName().replaceFirst("set", ""));
                if (properties.containsKey(pureName) &&
                  ((Property) properties.get(pureName)).getType().equals(methods[i].getParameterTypes()[0])) {
                    ((Property) properties.get(pureName)).setCanBeWritten(true);
                } else {
                    properties.put(pureName, new Property(pureName, methods[i].getParameterTypes()[0], false, true));
                }
            }
        }

        // Zweiter Pass - Korrelieren
        //System.out.println(properties.keySet().toString());
        return properties;
    }

    /**
     * @see java.util.Map#size()
     */
    public int size() {
        return this.properties.size();
    }

    /**
     * @see java.util.Map#isEmpty()
     */
    public boolean isEmpty() {
        return this.properties.isEmpty();
    }

    /**
     * @see java.util.Map#containsKey(java.lang.Object)
     */
    public boolean containsKey(Object key) {
        return this.properties.containsKey(key);
    }

    /**
     * @see java.util.Map#keySet()
     */
    public Set keySet() {
        return properties.keySet();
    }

    /**
     * @see java.util.Map#containsValue(java.lang.Object)
     */
    public boolean containsValue(Object value) {
        if (value == null) {
            return true;
        }

        Iterator iter = properties.keySet().iterator();
        while (iter.hasNext()) {
            String aPropertyName = (String) iter.next();
            try {
                Method thisMethod = this.getMatchingGetMethod(aPropertyName, false);
                if (thisMethod != null) {
                    Object thisValue = thisMethod.invoke(this, new Object[] {});
                    if (thisValue != null && thisValue.equals(value)) {
                        return true;
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return false;
    }

    /**
     * @see java.util.Map#get(java.lang.Object)
     */
    public Object get(Object key) {
        Object thisValue = null;
        try {
            thisValue = this.getValue(key);
        } catch (NoSuchFieldException e) {
            throw new ClassCastException("Key not found.");
        }
        return thisValue;
    }

    /**
     * Diese Operation ist nicht unterstützt, weil sich lt. Standard
     * die Werte in der zurückgegebenen Collection synchron ändern, wenn sich
     * in der zugrundeliegenden Map etwas ändert.
     *
     * @see java.util.Map#values()
     */
    public Collection values() {
        throw new UnsupportedOperationException();
    }

    /**
     * Diese Operation ist nicht unterstützt, weil sich lt. Standard
     * die Werte im zurückgegebenen Set synchron ändern, wenn sich
     * in der zugrundeliegenden Map etwas ändert.
     *
     * @see java.util.Map#entrySet()
     */
    public Set entrySet() {
        throw new UnsupportedOperationException();
    }

    /**
     * @see java.util.Map#put(java.lang.Object, java.lang.Object)
     */
    public Object put(Object arg0, Object arg1) {
        throw new UnsupportedOperationException();
    }

    /**
     * @see java.util.Map#remove(java.lang.Object)
     */
    public Object remove(Object key) {
        throw new UnsupportedOperationException();
    }

    /**
     * @see java.util.Map#putAll(java.util.Map)
     */
    public void putAll(Map arg0) {
        throw new UnsupportedOperationException();
    }

    /**
     * @see java.util.Map#clear()
     */
    public void clear() {
        throw new UnsupportedOperationException();
    }

    /**
     * @see java.util.Map#get(java.lang.Object)
     */
    public Object getIgnoreCase(Object key) {
        Object thisValue = null;
        try {
            thisValue = this.getValueIgnoreCase(key);
        } catch (NoSuchFieldException e) {
            throw new ClassCastException("Key not found.");
        }
        return thisValue;
    }
}
