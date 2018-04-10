package de.tarent.commons.ui;

import javax.swing.JTabbedPane;

/**
 *
 * <p>The TabbedPaneMouseWheelNavigator is intended to be an extension to {@link JTabbedPane}s,
 * for using the mouse-wheel to navigate fast and convenient through the tabs of a {@link JTabbedPane}.</p>
 *
 * <p>Inspired by the KDE Konqueror (<a href="http://www.konqueror.org">konqueror.org</a>),
 * but can be found in several Applications and Desktop Environments nowadays.</p>
 *
 * TODO ensure that tabs are only scrollable if the mouse is over the tab-headers (these things with the tab-title on it) and not on the whole panel of a tab
 *
 * @author Fabian K&ouml;ster (f.koester@tarent.de), tarent GmbH Bonn
 *
 */

public class TabbedPaneMouseWheelNavigator extends AbstractMouseWheelNavigator
{
	private JTabbedPane tabbedPane;

	/**
	 * <p>Constructs a ComboBoxMouseWheelNavigator for the given JTabbedPane.</p>
	 * <p>Same as TabbedPaneMouseWheelNavigator(pTabbedPane, true)</p>
	 *
	 * @param pTabbedPane
	 */

	public TabbedPaneMouseWheelNavigator(JTabbedPane pTabbedPane)
	{
		super();
		tabbedPane = pTabbedPane;
	}

	/**
	 * Constructs a TabbedPaneMouseWheelNavigator for the given JTabbedPane. Allows you to determine whether we jump over the boundaries.
	 * @param pTabbedPane the JTabbedPane to navigate on
	 * @param pJumpOverBoundaries whether we jump from the last tab to the first and the other way around
	 */

	public TabbedPaneMouseWheelNavigator(JTabbedPane pTabbedPane, boolean pJumpOverBoundaries)
	{
		super(pJumpOverBoundaries);
		tabbedPane = pTabbedPane;
	}

	protected int getCurrentIndex()
	{
		if(tabbedPane != null) return tabbedPane.getSelectedIndex();
		return 0;
	}

	protected int getMaxIndex()
	{
		if(tabbedPane != null) return tabbedPane.getTabCount()-1;
		return 0;
	}

	protected boolean isValidIndex(int pIndex)
	{
		return (tabbedPane != null && tabbedPane.isEnabledAt(pIndex));
	}

	protected void setIndex(int pIndex)
	{
		if(tabbedPane != null) tabbedPane.setSelectedIndex(pIndex);
	}

	protected boolean isComponentEnabled()
	{
		return (tabbedPane != null && tabbedPane.isEnabled());
	}
}
