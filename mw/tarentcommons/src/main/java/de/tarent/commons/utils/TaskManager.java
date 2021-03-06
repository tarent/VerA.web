package de.tarent.commons.utils;

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

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * <code>TaskManager</code> allows scheduling and running time-consuming
 * background tasks and provides infrastructure to allow displaying the task's
 * state.
 *
 * @author Robert Schuster
 */
public class TaskManager {
    public static final int DEFAULT_PRIORITY = 10;

    private List taskListeners = new ArrayList();

    private LinkedList contexts = new LinkedList();

    private static TaskManager instance = new TaskManager();

    private LinkedList threads = new LinkedList();

    private boolean running = true;

    private Blocker blocker;

    private TaskManager() {
        // Singleton.
        Thread t = new Thread() {
            public void run() {
                setName("TaskManager DispatchThread");
                processTasks();
            }
        };

        t.setDaemon(true);
        t.start();
    }

    /**
     * Processes all scheduled tasks or waits for them to be scheduled.
     *
     * This method is called on the <code<TaskManager</code>'s dispatch
     * thread.
     *
     * It processes the tasks in the following way:<ul>
     * <li>look up whether tasks to be run exist</li>
     * <li>if not go to sleep and check again after wakeup</li>
     * <li>if there is a task, run it according to its properties blocking,
     * exclusive or normal</li>
     * <li>start over again</li>
     * </ul>
     *
     * A check for ending the taskmanager itself is done right before sleeping
     * because of no pending tasks. This means that the taskmanager will always
     * process all tasks before going out of service. This behavior should
     * guarantee that work given to the taskmanager is really completed.
     */
    private void processTasks() {
        Context ctx = null;

        while (true) {
            synchronized (contexts) {
                try {
                    while (contexts.isEmpty()) {
                        // Bails out if awakened and TaskManager
                        // should not run anymore.
                        if (!running) {
                            return;
                        }

                        // Otherwise sleep a little longer.
                        contexts.wait();
                    }
                } catch (InterruptedException ie) {
                    // Interruption while waiting is not expected.
                    throw new IllegalStateException();
                }

                ctx = (Context) contexts.removeFirst();
            }

            if (ctx.blocking) {
                ctx.run();
            } else if (ctx.exclusive) {
                waitForAllTasks();

                // Informs the blocker if one is available.
                if (blocker != null) {
                    blocker.setBlocked(true);
                    ctx.run();
                    blocker.setBlocked(false);
                } else {
                    ctx.run();
                }
            } else {
                final Context copy = ctx;
                Thread t = new Thread() {
                    public void run() {
                        copy.run();

                        synchronized (threads) {
                            threads.remove(this);
                        }
                    }
                };

                synchronized (threads) {
                    threads.addLast(t);
                }

                t.start();
            }
        }
    }

    /**
     * Blocks until all scheduled tasks have been run.
     */
    private void waitForAllTasks() {
        Thread t = null;

        while (true) {
            synchronized (threads) {
                // Nothing to do if there are no threads.
                if (threads.isEmpty()) {
                    return;
                }

                t = (Thread) threads.removeFirst();
            }

            if (t != null) {
                try {
                    t.join();
                } catch (InterruptedException ie) {
                    // Unexpected.
                }
            } else {
                break;
            }
        }
    }

    /**
     * Returns the one and only instance of the <code>TaskManager</code>
     */
    public static TaskManager getInstance() {
        return instance;
    }

    /**
     * Installs a {@link Blocker} instance on the {@link TaskManager}.
     *
     * <p>When an exclusive task is run the {@link TaskManager} invokes
     * <code>setBlocked(true)</code> on it and does <code>setBlocked(false)</code>
     * when the task has finished.</p>
     *
     * <p>An implementor may use this to dissallow userinput when an exclusive task
     * is being run.</p>
     *
     * <p>Setting a {@link Blocker} is optional and does not affect executing exclusive
     * tasks any further.</p>
     *
     * @param b
     */
    public void setBlocker(Blocker b) {
        blocker = b;
    }

    /**
     * Registers a {@link TaskManager.TaskListener} instance which from now on
     * receives updates about the task manager's state.
     *
     * @param l
     */
    public void addTaskListener(TaskListener l) {
        taskListeners.add(l);
    }

    /**
     * Removes a {@link TaskManager.TaskListener} so it will not be notified
     * about changes to the task manager's state any more.
     *
     * @param l
     */
    public void removeTaskListener(TaskListener l) {
        taskListeners.remove(l);
    }

    /**
     * Checks whether the current thread is the the Swing event dispatch thread
     * and if so throws an <code>IllegalStateException</code>.
     *
     * <p>
     * This is used by various method of the task manager which dissallow being
     * called on the Swing event dispatch thread.
     * </p>
     */
    private void checkDispatchThread() {
        if (EventQueue.isDispatchThread()) {
            throw new IllegalStateException(
              "Task registration is not to be done on the Swing dispatch thread.");
        }
    }

    /**
     * Notifies all registered {@link TaskManager.TaskListener} instances about
     * a new task registration.
     *
     * @param t
     * @param description
     */
    private void fireTaskRegistered(Context t, String description) {
        Iterator ite = taskListeners.iterator();
        while (ite.hasNext()) {
            ((TaskListener) ite.next()).taskRegistered(t, description);
        }
    }

    /**
     * Notifies all registered {@link TaskManager.TaskListener} instances about
     * a new blocking task registration.
     *
     * @param t
     * @param description
     */
    private void fireBlockingTaskRegistered(Context t, String description) {
        Iterator ite = taskListeners.iterator();
        while (ite.hasNext()) {
            ((TaskListener) ite.next()).blockingTaskRegistered(t, description);
        }
    }

    /**
     * Notifies all registered {@link TaskManager.TaskListener} instances about
     * a new exclusive task registration.
     *
     * @param t
     * @param description
     */
    private void fireExclusiveTaskRegistered(Context t, String description) {
        Iterator ite = taskListeners.iterator();
        while (ite.hasNext()) {
            ((TaskListener) ite.next()).exclusiveTaskRegistered(t, description);
        }
    }

    /**
     * Notifies all registered {@link TaskManager.TaskListener} instances about
     * an update of a task's activity description.
     *
     * @param t
     * @param description
     */
    private void fireActivityDescriptionUpdated(Context t, String description) {
        Iterator ite = taskListeners.iterator();
        while (ite.hasNext()) {
            ((TaskListener) ite.next()).activityDescriptionSet(t, description);
        }
    }

    /**
     * Notifies all registered {@link TaskManager.TaskListener} instances about
     * an update of a task's goal value.
     *
     * @param t
     * @param amount
     */
    private void fireGoalUpdated(Context t, int amount) {
        Iterator ite = taskListeners.iterator();
        while (ite.hasNext()) {
            ((TaskListener) ite.next()).goalUpdated(t, amount);
        }
    }

    /**
     * Notifies all registered {@link TaskManager.TaskListener} instances about
     * an update of a task's current progress.
     */
    private void fireCurrentUpdated(Context t, int amount) {
        Iterator ite = taskListeners.iterator();
        while (ite.hasNext()) {
            ((TaskListener) ite.next()).currentUpdated(t, amount);
        }
    }

    /**
     * Notifies all registered {@link TaskManager.TaskListener} instances about
     * a task being completed.
     */
    private void fireTaskCompleted(Context t) {
        Iterator ite = taskListeners.iterator();
        while (ite.hasNext()) {
            ((TaskListener) ite.next()).taskCompleted(t);
        }
    }

    /**
     * Notifies all registered {@link TaskManager.TaskListener} instances about
     * a task being cancelled.
     */
    private void fireTaskCancelled(Context t) {
        Iterator ite = taskListeners.iterator();
        while (ite.hasNext()) {
            ((TaskListener) ite.next()).taskCancelled(t);
        }
    }

    /**
     * Notifies all registered {@link TaskManager.TaskListener} instances about
     * a task being started.
     */
    private void fireTaskStarted(Context t) {
        Iterator ite = taskListeners.iterator();
        while (ite.hasNext()) {
            ((TaskListener) ite.next()).taskStarted(t);
        }
    }

    private Context register(Context ctx) {
        synchronized (contexts) {
            contexts.add(ctx);

            contexts.notifyAll();
        }

        return ctx;
    }

    /**
     * Registers a normal (concurrent) task with the supplied priotiry.
     * The has no effect to the computation order of the Tasks, but is supplied for e.g. the view order.
     */
    public void register(Task t, String taskDescription, boolean cancelable, int priority) {

        fireTaskRegistered(register(new Context(t, false, false, cancelable, priority)),
          taskDescription);
    }

    /**
     * Registers a normal (concurrent) task.
     */
    public void register(Task t, String taskDescription, boolean cancelable) {
        register(t, taskDescription, cancelable, DEFAULT_PRIORITY);
    }

    /**
     * Registers a task which blocks running following tasks until it is
     * completed.
     */
    public void registerBlocking(Task t, String taskDescription,
      boolean cancelable) {
        // TODO: realy check this?
        //checkDispatchThread();

        fireBlockingTaskRegistered(register(new Context(t, true, false, cancelable, DEFAULT_PRIORITY)),
          taskDescription);
    }

    /**
     * Registers a task which can be run only when all other tasks have been
     * completed and then blocks running following tasks until it is completed.
     */
    public void registerExclusive(Task t, String taskDescription,
      boolean cancelable) {
        //checkDispatchThread();

        fireExclusiveTaskRegistered(register(new Context(t, false, true, cancelable, DEFAULT_PRIORITY)),
          taskDescription);
    }

    /**
     * A <code>Context</code> is a registered task's interface with the task
     * manager.
     * <p>
     * The {@link TaskManager} hands out <code>Context</code> instance to its
     * listeners allowing them to cancel tasks which support this feature.
     * </p>
     * TODO: Separate Task→Context and Listener→Context through two interfaces
     * which Context will implement.
     */
    public final class Context {
        boolean blocking;

        boolean exclusive;

        boolean cancelable;

        Task t;

        volatile Thread thread;

        volatile boolean cancelled;

        int priority;

        Context(Task t, boolean blocking, boolean exclusive, boolean cancelable, int priority) {
            this.t = t;
            this.blocking = blocking;
            this.exclusive = exclusive;
            this.cancelable = cancelable;
            this.priority = priority;
        }

        public int getPriority() {
            return priority;
        }

        public boolean isCancelable() {
            return cancelable;
        }

        /**
         * Returns whether the context's task has been cancelled.
         */
        public boolean isCancelled() {
            return cancelled;
        }

        public void setActivityDescription(String description) {
            fireActivityDescriptionUpdated(this, description);
        }

        public void setGoal(int amount) {
            fireGoalUpdated(this, amount);
        }

        public void setCurrent(int amount) {
            fireCurrentUpdated(this, amount);
        }

        void run() {
            fireTaskStarted(this);

            thread = Thread.currentThread();

            t.run(this);

            if (!cancelled) {
                fireTaskCompleted(this);
            }
        }

        /**
         * Cancels the underlying task if it is cancelable.
         *
         * This method <em>blocks</em> until the task has finished its
         * cancelation. For this reason it is wrong to call the method from
         * within the Swing dispatch thread as it may freeze the UI.
         *
         * @throws IllegalStateException if the task is not cancelable or the method is called
         *                               from the Swing dispatch thread
         */
        public void cancel() throws IllegalStateException {
            if (!cancelable) {
                throw new IllegalStateException("Task is not cancelable");
            }

            checkDispatchThread();

            cancelled = true;

            t.cancel();

            if (!blocking && !exclusive) {
                try {
                    thread.join();
                } catch (InterruptedException ie) {
                    // Unexpected.
                    throw new IllegalStateException();
                }
            }

            fireTaskCancelled(this);
        }
    }

    /**
     * A {@link Blocker} implementation registered to the {@link TaskManager}
     * may provide means to block user-input while an exclusive task is running.
     *
     * @author Robert Schuster
     */
    public static interface Blocker {
        void setBlocked(boolean b);
    }

    /**
     * This is the interface to be implemented for long running background tasks
     * which should be subject to a specific scheduling policy.
     *
     * Cancellation can be implemented in two ways:
     *
     * The first variant regularly checks the {@link TaskManager.Context#isCancelled()}
     * method from inside the {@link #run(Context)} method. This is suitable for tasks
     * which perform a loop or iterate through the work units.
     *
     * The second variant involves reacting to a call to the {@link #cancel()} method.
     * Which should wakeup the {@link #run(Context)} method (e.g. via {@link Thread#interrupt()}).
     * This variant is suitable for tasks which enter a blocking system call in their <code>run</code>
     * method and cannot regulary check the <code>Context</code> object.
     */
    public static interface Task {
        /**
         * This method should do the tasks work.
         *
         * Keep the following threading issues in mind: <ul>
         * <li>you are not on the Swing dispatch thread (use
         * {@link EventQueue#invokeLater} for GUI manipulations)
         * </p>
         * <li>you are on an arbitrary thread, not the one that started the
         * application</li>
         * </ul>
         *
         * @param ctx
         */
        void run(Context ctx);

        /**
         * Cancelable tasks should implement this method in a way that stops the
         * work done by {@link #run(Context)} quickly.
         *
         * If cancelation is not possible just provide an empty implementation.
         * It will not be called.
         */
        void cancel();
    }

    /**
     * This class does nothing else than providing an implementation where
     * the {@link #cancel()} method is implemented empty, removing the need
     * to define it.
     *
     * @author Robert Schuster.
     */
    public static abstract class AbstractTask implements Task {

        public void cancel() {
            // Does nothing.
        }
    }

    /**
     * Simple task implementation for tasks that interact with Swing components.
     *
     * The implementation provides all the infrastructure for dealing correctly
     * with the Swing event dispatch thread.
     *
     * Access Swing components only from {@link #prepare(Context)},
     * {@link #succeeded(Context)} and {@link #failed(Context)} method.
     *
     * Do time consuming task in the {@link #runImpl(Context)} method.
     */
    public static abstract class SwingTask extends AbstractTask {

        public final void run(final Context ctx) {
            // Runs 'prepare' on the Swing thread.
            try {
                EventQueue.invokeAndWait(new Runnable() {
                    public void run() {
                        prepare(ctx);
                    }
                });
            } catch (InterruptedException ie) {
                throw (IllegalStateException) new IllegalStateException()
                  .initCause(ie);
            } catch (InvocationTargetException ite) {
                throw (IllegalStateException) new IllegalStateException()
                  .initCause(ite);
            }

            // Runs the task directly and then 'suceeded' or 'failed' on the
            // Swing
            // thread.
            try {
                runImpl(ctx);

                try {
                    EventQueue.invokeAndWait(new Runnable() {
                        public void run() {
                            succeeded(ctx);
                        }
                    });
                } catch (InterruptedException ie) {
                    throw (IllegalStateException) new IllegalStateException()
                      .initCause(ie);
                } catch (InvocationTargetException ite) {
                    throw (IllegalStateException) new IllegalStateException()
                      .initCause(ite);
                }
            } catch (SwingTask.Exception ste) {
                try {
                    EventQueue.invokeAndWait(new Runnable() {
                        public void run() {
                            failed(ctx);
                        }
                    });
                } catch (InterruptedException ie) {
                    throw (IllegalStateException) new IllegalStateException()
                      .initCause(ie);
                } catch (InvocationTargetException ite) {
                    throw (IllegalStateException) new IllegalStateException()
                      .initCause(ite);
                }
            }
        }

        /**
         * Override this implementation for task setup.
         *
         * This method is called right before the task is run and should be used
         * to set up everything to run the task.
         */
        protected void prepare(Context ctx) {
        }

        /**
         * Implement this method according to the task you want to run.
         *
         * This method is <em>not</em> being run on the Swing thread and is
         * allowed to be time consuming.
         *
         * Throw a {@link SwingTask.Exception} to indicate that the task failed.
         */
        protected abstract void runImpl(Context ctx) throws SwingTask.Exception;

        /**
         * Implement this method with the necessary behavior when the task has
         * succeeded.
         *
         * This method is called on the Swing thread.
         */
        protected abstract void succeeded(Context ctx);

        /**
         * Implement this method with the necessary behavior when the task has
         * failed.
         *
         * This method is called on the Swing thread.
         */
        protected abstract void failed(Context ctx);

        /**
         * Simple exception class of which instances should be thrown inside
         * {@link SwingTask#runImpl(Context)}.
         */
        public static class Exception extends java.lang.Exception {
            public Exception(String msg) {
                super(msg);
            }

            public Exception(String msg, Throwable cause) {
                super(msg, cause);
            }
        }
    }

    /**
     * This interface is to be implemented when information about the
     * {@link TaskManager}s activity is wanted.
     *
     * A usual implementation would be a graphical representation of the
     * {@link TaskManager}.
     *
     * Via the {@link Context} instances an implementor can issue cancellation
     * of tasks supporting this.
     *
     * @author Robert Schuster
     */
    public static interface TaskListener {
        /**
         * This method is called whenever a new concurrent task
         * is registered at the <code>TaskManager</code>.
         */
        void taskRegistered(Context t, String description);

        /**
         * This method is called whenever a new blocking task
         * is registered at the <code>TaskManager</code>.
         */
        void blockingTaskRegistered(Context t, String description);

        /**
         * This method is called whenever a new exclusive task
         * is registered at the <code>TaskManager</code>.
         */
        void exclusiveTaskRegistered(Context t, String description);

        /**
         * This method is called whenever a task's activity description
         * changes.
         */
        void activityDescriptionSet(Context t, String description);

        /**
         * This method is called whenever a task starts.
         */
        void taskStarted(Context t);

        /**
         * This method is called whenever a task is completed.
         *
         * <p>A task is completed after its {@link TaskManager.Task#run(Context)}
         * method returns.</p>
         */
        void taskCompleted(Context t);

        /**
         * This method is called whenever a task is cancelled.
         *
         * <p>A task is cancelled when the <code>cancel</code> method of the
         * task's <code>Context</code> was called and after that returns from
         * the {@link TaskManager.Task#run(Context)} method.
         */
        void taskCancelled(Context t);

        /**
         * This method is called whenever a task's progress is updated.
         */
        void currentUpdated(Context t, int amount);

        /**
         * This method is called whenever a task's progress goal is updated.
         */
        void goalUpdated(Context t, int amount);
    }
}
