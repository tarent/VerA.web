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
 * Created on 18.10.2005
 */

package de.tarent.commons.ui;

import java.util.Map;

import javax.swing.text.ViewFactory;
import javax.swing.text.html.HTMLEditorKit;

/**
 * This class inherits from the original Swing HTMLEditorKit. It brings
 * our own JHTMLFactory into the flow of generating the widget components
 * for the HTML panel.
 *
 * @author Michael Kleinhenz (m.kleinhenz@tarent.de)
 */
public class JHTMLEditorKit extends HTMLEditorKit
{
    /** serialVersionUID */
	private static final long serialVersionUID = 1137520399475537744L;

	private JHTMLPanel controller = null;
    private Map widgetMap = null;
    
    /**
     * This constructs a new JHTMLEditorKit. It stores the
     * controller and calls the constructor of the superclass.
     * <p>
     * The widgetMap associates the name of a widget (used in the
     * name attribute of the input element of a JHTML source) and the class
     * implementing this widget. For example it may contain the association
     * <p>
     * <ul>String "jslider" -&gt; Class javax.swing.JSlider</ul>
     * <p>
     * Then you can use this code
     * <p>
     * <ul>&lt;input type="jslider" name="myname"&gt;</ul>
     * <p>
     * To display a JSlider. The name of the JSlider will be "myname". The name
     * and the instantiated object of the displayed JSlider will be announced
     * to the JHTMLPanel implementing class via the componentCreated() method.
     * 
     * @param controller HTMLPanel that hosts this HTML panel and acts as a controller for it.
     * @param widgetMap Map containing name to component associations.
     */
    public JHTMLEditorKit(JHTMLPanel controller, Map widgetMap)
    {
        super();
        this.controller = controller;
        this.widgetMap = widgetMap;
    }

    /**
     * Returns our JHTMLViewFactory. This is called from Swing.
     * 
     * @return Our custom ViewFactory.
     */
    public ViewFactory getViewFactory() 
    {
        return new JHTMLFactory(controller, widgetMap);
    }
}
