package de.tarent.aa.veraweb.worker;

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

import de.tarent.aa.veraweb.beans.Person;
import de.tarent.aa.veraweb.beans.Task;
import de.tarent.aa.veraweb.utils.DateHelper;
import de.tarent.dblayer.sql.statement.Insert;
import de.tarent.dblayer.sql.statement.Update;
import de.tarent.octopus.PersonalConfigAA;
import de.tarent.octopus.beans.BeanException;
import de.tarent.octopus.beans.Database;
import de.tarent.octopus.beans.Request;
import de.tarent.octopus.beans.TransactionContext;
import de.tarent.octopus.beans.veraweb.BeanChangeLogger;
import de.tarent.octopus.beans.veraweb.DatabaseVeraWeb;
import de.tarent.octopus.beans.veraweb.DatabaseVeraWebFactory;
import de.tarent.octopus.beans.veraweb.RequestVeraWeb;
import de.tarent.octopus.server.OctopusContext;

import java.io.IOException;

/**
 * Functions needed by webapp to handle event tasks.
 */
public class EventTaskDetailWorker {
    private static final String PARAM_TASK = "task";
    private static final String PARAM_EVENT_ID = "eventId";

    private final DatabaseVeraWebFactory databaseVeraWebFactory;

    public EventTaskDetailWorker(DatabaseVeraWebFactory databaseVeraWebFactory) {
        this.databaseVeraWebFactory = databaseVeraWebFactory;
    }

    public EventTaskDetailWorker() {
        this(new DatabaseVeraWebFactory());
    }

    public static final String[] INPUT_loadReferencePerson = { "refPersId" };
    public static final boolean[] MANDATORY_loadReferencePerson = { false };

    public void loadReferencePerson(OctopusContext cntx, String refPersId) throws BeanException, IOException {
        if (refPersId == null || refPersId.trim().length() == 0) {
            return;
        }

        Integer pk = Integer.parseInt(refPersId);

        cntx.getContentObject().setField("refPerson", getPersonFromDB(cntx, pk));
    }

    public static final String[] INPUT_copyTaskAndEventId = { PARAM_EVENT_ID, "taskId" };
    public static final boolean[] MANDATORY_copyTaskAndEventId = { false, false };

    public void copyTaskAndEventId(OctopusContext oc, String eventId, String taskId) {
        oc.setContent(PARAM_EVENT_ID, eventId);
        oc.setContent("taskId", taskId);
    }

    public static final String[] INPUT_setEventTaskId = { PARAM_EVENT_ID };
    public static final String OUTPUT_setEventTaskId = PARAM_EVENT_ID;

    /**
     * Assigns the eventId from the task list to a new created task to link
     * the task to the event
     *
     * @param cntx    FIXME
     * @param eventId FIXME
     * @return eventId
     */
    public Integer setEventTaskId(OctopusContext cntx, Integer eventId) {
        cntx.setContent(PARAM_EVENT_ID, eventId);
        return eventId;
    }

    public static final String[] INPUT_showDetail = { PARAM_EVENT_ID, "id" };
    public static final boolean[] MANDATORY_showDetail = { false, false };
    public static final String OUTPUT_showDetail = PARAM_TASK;

    public Task showDetail(OctopusContext context, Integer eventId, Integer id) throws BeanException, IOException {
        if (eventId != null) {
            setEventTaskId(context, eventId);
        }
        Task task = getTaskFromDB(context, id);

        if (task != null && task.personId != null) {
            Person person = getPersonFromDB(context, task.personId);
            context.getContentObject().setField("refPerson", person);
        }

        return task;
    }

    /**
     * Eingabe-Parameter der Octopus-Aktion {@link #saveTemp(OctopusContext)}
     */
    public static final String INPUT_saveTemp[] = {};

    /**
     * Diese Octopus-Aktion holt eine Aufgabe unter "task" aus dem Octopus-Request und legt sie unter "task" in
     * den Octopus-Content und unter "tasktemp" in die Session.
     *
     * @param cntx Octopus-Kontext
     * @throws BeanException FIXME
     */
    public void saveTemp(OctopusContext cntx) throws BeanException {
        Request request = new RequestVeraWeb(cntx);
        Task task = (Task) request.getBean("Task", "task");
        cntx.setSession("tasktemp", task);
        cntx.setContent("task", task);
    }

    /**
     * Eingabe-Parameter der Octopus-Aktion {@link #loadTemp(OctopusContext)}
     */
    public static final String INPUT_loadTemp[] = {};

    /**
     * Diese Octopus-Aktion holt eine Aufgabe unter "tasktemp" aus der Session und
     * legt sie unter "task" und Hilfsflags unter "task-beginhastime" und "task-endhastime"
     * im Octopus-Content ab.
     *
     * @param cntx Octopus-Kontext
     */
    public void loadTemp(OctopusContext cntx) {
        Task task = (Task) cntx.sessionAsObject("tasktemp");
        cntx.setContent("task", task);
    }

    public static final String INPUT_saveDetail[] = { "savetask" };

    public static final boolean MANDATORY_saveDetail[] = { false };

    public void saveDetail(final OctopusContext octopusContext, Boolean savetask) throws BeanException, IOException {
        savetask = getSaveTaskFlag(octopusContext, savetask);

        if (savetask == null || !savetask.booleanValue()) {
            return;
        }

        final Request request = new RequestVeraWeb(octopusContext);
        final Database database = databaseVeraWebFactory.createDatabaseVeraWeb(octopusContext);
        final TransactionContext transactionContext = database.getTransactionContext();

        try {
            Task task = (Task) octopusContext.contentAsObject(PARAM_TASK);
            if (task == null) {
                task = (Task) request.getBean("Task", PARAM_TASK);
                DateHelper.addTimeToDate(task.startdate, task.starttime, task.getErrors());
                DateHelper.addTimeToDate(task.enddate, task.endtime, task.getErrors());
            }

            task.verify(octopusContext);

            final Task existingTask = (Task) database.getBean("Task", task.getId(), transactionContext);

            /** Aufgabe speichern */
            if (task.isModified() && task.isCorrect()) {
                createOrUpdateTask(octopusContext, database, transactionContext, task, existingTask);
            } else {
                octopusContext.setStatus("notsaved");
                octopusContext.setContent("taskNoChangesMade", true);
            }

            task = getTask(octopusContext, task);

            octopusContext.setContent(PARAM_TASK, task);
            octopusContext.setContent("eventId", task.getEventId());
            octopusContext.setContent("task-starthastime", Boolean.valueOf(DateHelper.isTimeInDate(task.getStartdate())));
            octopusContext.setContent("task-endhastime", DateHelper.isTimeInDate(task.getEnddate()));

            if (task != null && task.personId != null) {
                final Person person = getPersonFromDB(octopusContext, task.personId);
                octopusContext.getContentObject().setField("refPerson", person);
            }

            transactionContext.commit();
        } catch (BeanException e) {
            createTaskFailure(transactionContext, e);
        }
    }

    private Task getTask(OctopusContext octopusContext, Task task) {
        if (task.id != null) {
            octopusContext.setSession("task", task);
        } else {
            task = (Task) octopusContext.sessionAsObject("task");
        }
        return task;
    }

    private Boolean getSaveTaskFlag(OctopusContext octopusContext, Boolean savetask) {
        if (savetask != null) {
            octopusContext.setSession("savetask", savetask);
        } else {
            savetask = (Boolean) octopusContext.sessionAsObject("savetask");
        }
        return savetask;
    }

    private void createOrUpdateTask(OctopusContext octopusContext, Database database, TransactionContext transactionContext,
      Task task, Task oldTask)
      throws BeanException, IOException {
        BeanChangeLogger clogger = new BeanChangeLogger(database, transactionContext);
        if (task.getId() == null) {
            createTask(octopusContext, database, transactionContext, task, clogger);
        } else {
            updateTask(octopusContext, database, transactionContext, task, oldTask, clogger);
        }
    }

    private void createTask(OctopusContext octopusContext, Database database, TransactionContext transactionContext, Task task,
      BeanChangeLogger clogger) throws BeanException, IOException {
        octopusContext.setContent("countInsert", Integer.valueOf(1));
        database.getNextPk(task, transactionContext);
        task.updateHistoryFields(((PersonalConfigAA) octopusContext.personalConfig()).getRoleWithProxy());

        final Insert insert = database.getInsert(task);
        insert.insert("pk", task.getId());

        if (!((PersonalConfigAA) octopusContext.personalConfig()).getGrants().mayReadRemarkFields()) {
            insert.remove("note");
        }

        addEventIdToInsertStatement(octopusContext, task, insert);
        transactionContext.execute(insert);
        transactionContext.commit();
        clogger.logInsert(octopusContext.personalConfig().getLoginname(), task);
    }

    private void updateTask(OctopusContext octopusContext, Database database, TransactionContext transactionContext, Task task,
      Task existingTask,
      BeanChangeLogger clogger) throws BeanException, IOException {
        octopusContext.setContent("countUpdate", Integer.valueOf(1));
        final Update update = database.getUpdate(task);
        if (!((PersonalConfigAA) octopusContext.personalConfig()).getGrants().mayReadRemarkFields()) {
            update.remove("note");
        }

        transactionContext.execute(update);
        transactionContext.commit();
        clogger.logUpdate(octopusContext.personalConfig().getLoginname(), existingTask, task);
    }

    private void createTaskFailure(TransactionContext transactionContext, BeanException e) throws BeanException {
        transactionContext.rollBack();
        // must report error to user
        throw new BeanException("Die Taskdetails konnten nicht gespeichert werden.", e);
    }

    private void addEventIdToInsertStatement(OctopusContext octopusContext, Task task, Insert insert) {
        if (task.getEventId() == null) {
            insert.remove("fk_event");
            insert.insert("fk_event", octopusContext.requestAsInteger("id"));
        }
    }

    static public Task getTaskFromDB(OctopusContext context, Integer id) throws BeanException, IOException {
        if (id == null) {
            return null;
        }

        Database database = new DatabaseVeraWeb(context);
        Task task = (Task) database.getBean("Task", id);

        if (task != null) {
            context.setContent("task-starthastime", Boolean.valueOf(DateHelper.isTimeInDate(task.startdate)));
            context.setContent("task-endhastime", Boolean.valueOf(DateHelper.isTimeInDate(task.enddate)));
            task.getErrors().clear();
        }

        return task;
    }

    static public Person getPersonFromDB(OctopusContext context, Integer id) throws BeanException, IOException {
        if (id == null) {
            return null;
        }

        Database database = new DatabaseVeraWeb(context);
        Person person = (Person) database.getBean("Person", id);

        return person;
    }
}
