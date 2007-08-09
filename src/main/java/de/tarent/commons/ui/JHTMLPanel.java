/*
 * tarent commons,
 * a set of common components and solutions
 * Copyright (c) 2006-2007 tarent GmbH
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
 * interest in the program 'tarent commons'
 * Signature of Elmar Geese, 14 June 2007
 * Elmar Geese, CEO tarent GmbH.
 */

/*
 * Copyright (c) tarent GmbH
 * Bahnhofstrasse 13 . 53123 Bonn
 * www.tarent.de . info@tarent.de
 *
 * Created on 09.12.2005
 */

package de.tarent.commons.ui;

import java.awt.Component;
import javax.swing.text.Element;

/**
 * This interface has to be implemented by all classes that wish to
 * use the JHTML widget. It is the communication link between the
 * internal engine of the JHTML widget and the main application
 * using this technology.
 * <p>
 * To use this technology, you have to follow these steps:
 * <ul>
 * <li>Implement this interface.
 * <li>Implement a mechanism to keep track of the created components inside
 * the JHTML widget. For each created component, the JHTMLEditorKit will call the
 * componentCreated() method. Store this information and use it to access the
 * components for common control tasks such as reading data, writing data or setting
 * parameters for the components. Access each component like it is a common Swing created
 * component.
 * <li>Implement some sort of configuration that maps a component name (String) to
 * a Swing component class (Class). Provide this map to the JHTMLEditorKit
 * <li>
 * </ul>
 *
 * @author Michael Kleinhenz (m.kleinhenz@tarent.de)
 */
public interface JHTMLPanel
{    
    /**
     * When the JHTML widget has created an actual instance of a widget, it is
     * announced with it's given name (taken from the name attribute in the JHTML source)
     * to the JHTMLPanel implementing object via this method.
     * <p>
     * This is needed for synchronization purposes between the JHTMLEditorKit display thread
     * that actually creates the view and the main thread.
     * 
     * @param nameAttribute Name of the component as given by the name attribute in the JHTML source.
     * @param component The widget itself.
     * @param componentElement The XML element for this component.
     */
    public void componentCreated(String nameAttribute, Component component, Element componentElement);
}
