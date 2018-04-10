package de.tarent.commons.ui;

/*-
 * VerA.web-Middleware, newly MIT licenced, is comprised of:
 * tarent-commons, a set of common components and solutions
 * tarent-contact, platform-independent webservice-based contact management
 * tarent-database, jdbc database library
 * tarent-doctor, Document Generation Platform
 * tarent-octopus, Webservice Data Integrator and Application Server
 *  © 2005, 2006, 2007 asteban <s.mancke@tarent.de>
 *  © 2007 David Goemans <d.goemans@tarent.de>
 *  © 2006, 2007, 2010 Hendrik Helwich <h.helwich@tarent.de>
 *  © 2005, 2006, 2007 Christoph Jerolimov <c.jerolimov@tarent.de>
 *  © 2006 Philipp Kirchner <p.kirchner@tarent.de>
 *  © 2010 Carsten Klein <c.klein@tarent.de>
 *  © 2006 Michael Kleinhenz <m.kleinhenz@tarent.de>
 *  © 2006 Michael Klink <m.klink@tarent.de>
 *  © 2007 Fabian Köster <f.koester@tarent.de>
 *  © 2006 Martin Ley <m.ley@tarent.de>
 *  © 2007 Alex Maier <a.maier@tarent.de>
 *  © 2007, 2015, 2017, 2018 mirabilos <t.glaser@tarent.de>
 *  © 2006, 2007 Jens Neumaier <j.neumaier@tarent.de>
 *  © 2006 Nils Neumaier <n.neumaier@tarent.de>
 *  © 2007, 2008 Martin Pelzer <m.pelzer@tarent.de>
 *  © 2008, 2009 Christian Preilowski <c.thiel@tarent.de>
 *  © 2006, 2008, 2009 Thomas Schmitz <t.schmitz@tarent.de>
 *  © 2007 Robert Schuster <r.schuster@tarent.de>
 * and older code, Copyright © 2001–2007 ⮡ tarent GmbH and contributors.
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
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.tarent.commons.utils.TaskManager;
import de.tarent.commons.utils.TaskManager.Context;

/**
 * Simple implementation of the {@link TaskManager.TaskListener} interface which
 * displays the latest task with a label and a progress bar and provides a
 * cancel button for cancelable tasks.
 *
 * @author Robert Schuster
 */
public class SmallTaskManagerPanel extends JComponent implements
        TaskManager.TaskListener {
    /**
     *
     */
    private static final long serialVersionUID = -1129992957849564059L;

    /**
     * A label showing the description of the currently active task.
     */
    private JLabel label = new JLabel();

    /**
     * A progress bar showing the progress of the currently active task.
     */
    private JProgressBar progressBar = new JProgressBar(0, 0);

    /**
     * A button allowing cancellation of the currently active task.
     */
    private AbstractButton cancelButton = new JButton();

    private Context showingContext;

    private String description;

    List contexts = Collections.synchronizedList(new ArrayList());

    public SmallTaskManagerPanel() {
        cancelButton.setIcon(new ImageIcon(
                Toolkit.getDefaultToolkit().getImage(getClass().getResource("/de/tarent/commons/gfx/process-stop.png"))));

        FormLayout layout = new FormLayout("3dlu, pref, 3dlu, pref, 3dlu, pref",
                "12dlu");
        setLayout(layout);

        CellConstraints cc = new CellConstraints();
        add(label, cc.xy(2, 1));
        add(progressBar, cc.xy(4, 1));
        add(cancelButton, cc.xy(6, 1));

        progressBar.setVisible(false);
        cancelButton.setVisible(false);

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                // Make the button unclickable instantly because a task
                // should not be cancelled twice.
                cancelButton.setEnabled(false);

                new Thread() {
                    public void run() {
                        if (showingContext != null) {
                            showingContext.cancel();
                        }
                    }
                }.start();
            }
        });
    }

    /**
     * Puts the newly registered task as the active one and copies
     * the description.
     */
    public void taskRegistered(Context t, final String description) {
        addContext(t);
        this.description = description;
        cancelButton.setToolTipText(Messages.getFormattedString("SmallTaskManagerPanel_CancelProgress", description));
    }

    /**
     * Calls {@link #taskRegistered(Context, String)}.
     */
    public void blockingTaskRegistered(Context t, String description) {
        taskRegistered(t, description);
    }

    /**
     * Calls {@link #taskRegistered(Context, String)}.
     */
    public void exclusiveTaskRegistered(Context t, String description) {
        taskRegistered(t, description);
    }

    /**
     * Sets the label's text (on the Swing thread).
     *
     * @param text
     */
    private void setLabelLater(final String text) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                label.setText(text);
            }
        });
    }

    /**
     * Sets the progress bar on the Swing thread.
     *
     * <p>If <code>progress</code> is <code>0</code> this
     * is interpreted as the task's start which results in
     * the label, the progress bar and the cancel button (if
     * applicable) being made visible.</p>
     *
     * <p>If the progress bar's maximum value is zero it is
     * set into indeterminate mode.</p>
     *
     * <p>In case <code>progress</code> is non-zero the progress
     * bar's value is updated accordingly.</p>
     *
     * @param progress
     */
    private void setProgressLater(final int progress) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (progress == 0) {
                    label.setText(description);
                    progressBar.setVisible(true);
                    cancelButton.setVisible(showingContext != null && showingContext.isCancelable());
                    cancelButton.setEnabled(true);

                    if (progressBar.getMaximum() == 0) {
                        progressBar.setIndeterminate(true);
                    }

                    getRootPane().revalidate();
                } else {
                    progressBar.setValue(progress);
                }
            }
        });
    }

    private void setMaximumLater(final int progress) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                progressBar.setIndeterminate(false);
                progressBar.setMaximum(progress);
            }
        });
    }

    public void activityDescriptionSet(Context t, String description) {
        if (t == showingContext) {
            setLabelLater(description);
        }
    }

    public void taskStarted(Context t) {
        if (t == showingContext) {
            setProgressLater(0);
        }
    }

    /**
     * Makes the child components invisible and the set
     * the progress bar's maximum value back to zero.
     */
    public void taskCompleted(Context t) {
        if (t == showingContext) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    label.setText("");
                    progressBar.setVisible(false);
                    cancelButton.setVisible(false);
                    progressBar.setMaximum(0);

                    getRootPane().revalidate();
                }
            });
        }

        removeContext(t);
    }

    /**
     * Calls {@link #taskCompleted(Context)}.
     */
    public void taskCancelled(Context t) {
        taskCompleted(t);
    }

    /**
     * Updates the progress bar.
     */
    public void currentUpdated(Context t, int amount) {
        if (t == showingContext) {
            setProgressLater(amount);
        }
    }

    /**
     * Updates the progress bar's maximum value.
     */
    public void goalUpdated(Context t, int amount) {
        if (t == showingContext) {
            setMaximumLater(amount);
        }
    }

    private Context getMostImportantContext() {
        Context mostImportant = null;
        for (Iterator iter = contexts.iterator(); iter.hasNext(); ) {
            Context cntx = (Context) iter.next();
            // if both are equal, the later one wins
            if (mostImportant == null || mostImportant.getPriority() <= cntx.getPriority()) {
                mostImportant = cntx;
            }
        }
        return mostImportant;
    }

    private void addContext(Context cntx) {
        contexts.add(cntx);
        showingContext = getMostImportantContext();
    }

    private void removeContext(Context cntx) {
        contexts.remove(cntx);
        showingContext = getMostImportantContext();
    }
}
