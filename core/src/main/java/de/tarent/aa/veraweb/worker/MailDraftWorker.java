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

import de.tarent.aa.veraweb.beans.MailDraft;
import de.tarent.dblayer.sql.Escaper;
import de.tarent.dblayer.sql.SQL;
import de.tarent.dblayer.sql.clause.Expr;
import de.tarent.dblayer.sql.clause.RawClause;
import de.tarent.dblayer.sql.clause.Where;
import de.tarent.dblayer.sql.statement.Select;
import de.tarent.octopus.PersonalConfigAA;
import de.tarent.octopus.beans.BeanException;
import de.tarent.octopus.beans.Database;
import de.tarent.octopus.beans.TransactionContext;
import de.tarent.octopus.beans.veraweb.DatabaseVeraWeb;
import de.tarent.octopus.beans.veraweb.ListWorkerVeraWeb;
import de.tarent.octopus.server.OctopusContext;
import org.evolvis.veraweb.common.Placeholder;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Dieser Octopus-Worker repräsentiert eine übersichtsseite
 * sowie die Detailseiten zu eMail-Vorlagen.
 * Siehe Task MailDraftList und MailDraftDetail.<br><br>
 *
 * @author Christoph Jerolimov
 * @version $Revision: 1.1 $
 */
public class MailDraftWorker extends ListWorkerVeraWeb {
    //
    // Konstruktoren
    //

    /*
     * Der Konstruktor legt den Bean-Namen fest.
     */
    public MailDraftWorker() {
        super("MailDraft");
    }

    //
    // Oberklasse BeanListWorker
    //
    @Override
    protected Integer getAlphaStart(OctopusContext octopusContext, String start) throws BeanException, IOException {
        Database database = getDatabase(octopusContext);

        StringBuffer buffer = new StringBuffer();
        buffer.append("name < '");
        Escaper.escape(buffer, start);
        buffer.append("'");

        Select select = database.getCount(BEANNAME);
        select.where(new RawClause(buffer));
        select.whereAnd(getOrgUnitFilter(octopusContext));

        Integer i = database.getCount(select);
        return new Integer(i.intValue() - (i.intValue() % getLimit(octopusContext).intValue()));
    }

    /*
     * Updatet ausschließlich den Namen der in der Liste angezeigt wird.
     */
    @Override
    protected int updateBeanList(OctopusContext octopusContext, List errors, List beanlist, TransactionContext transactionContext)
            throws BeanException {
        int count = 0;
        for (Iterator it = beanlist.iterator(); it.hasNext(); ) {
            MailDraft mailDraft = (MailDraft) it.next();
            if (mailDraft.isModified()) {
                Database db = transactionContext.getDatabase();
                transactionContext.execute(
                        SQL.Update(db).
                                table("veraweb.tmaildraft").
                                update("name", mailDraft.name).
                                where(Expr.equal("pk", mailDraft.id)).
                                whereAnd(getOrgUnitFilter(octopusContext)));
                count++;
                transactionContext.commit();
            }
        }
        return count;
    }

    @Override
    protected void extendAll(OctopusContext octopusContext, Select select) {
        select.where(getOrgUnitFilter(octopusContext));
    }

    @Override
    protected void extendWhere(OctopusContext octopusContext, Select select) {
        select.where(getOrgUnitFilter(octopusContext));
    }

    protected Where getOrgUnitFilter(OctopusContext octopusContext) {
        return Expr.equal("fk_orgunit", ((PersonalConfigAA) octopusContext.personalConfig()).getOrgUnitId());
    }

    //
    // Octopus-Aktionen
    //
    /**
     * Octopus-Eingabe-Parameter für {@link #showDetail(OctopusContext, Integer, MailDraft)}
     */
    public static final String INPUT_showDetail[] = {"id", "maildraft"};
    /**
     * Octopus-Eingabe-Parameter für {@link #showDetail(OctopusContext, Integer, MailDraft)}
     */
    public static final boolean MANDATORY_showDetail[] = {false, false};
    /**
     * Octopus-Ausgabe-Parameter für {@link #showDetail(OctopusContext, Integer, MailDraft)}
     */
    public static final String OUTPUT_showDetail = "maildraft";

    /**
     * Lädt einen eMail-Entwurf aus der Datenbank und stellt
     * diesen in den Content, wenn eine ID übergeben wurde
     * und sich noch kein Entwurf im Content befindet.
     *
     * @param octopusContext Octopus-Context
     * @param id             Datenbank ID
     * @param mailDraft      eMail-Entwurf aus dem Content.
     * @return eMail-Entwurf oder null
     * @throws BeanException BeanException
     * @throws IOException   IOException
     */
    public MailDraft showDetail(OctopusContext octopusContext, Integer id, MailDraft mailDraft)
            throws BeanException, IOException {
        if (mailDraft == null && id != null) {
            final Select select = getDatabase(octopusContext).getSelect("MailDraft").
                    where(Expr.equal("pk", id)).
                    whereAnd(getOrgUnitFilter(octopusContext));
            return (MailDraft) getDatabase(octopusContext).getBean("MailDraft", select);
        }
        return mailDraft;
    }

    /**
     * Octopus-Eingabe-Parameter für {@link #saveDetail(OctopusContext, Boolean)}
     */
    public static final String INPUT_saveDetail[] = {"save"};
    /**
     * Octopus-Eingabe-Parameter für {@link #saveDetail(OctopusContext, Boolean)}
     */
    public static final boolean MANDATORY_saveDetail[] = {false};
    /**
     * Octopus-Ausgabe-Parameter für {@link #saveDetail(OctopusContext, Boolean)}
     */
    public static final String OUTPUT_saveDetail = "maildraft";

    /**
     * Speichert den übergebenen eMail-Entwurf bzw. lädt diesen aus dem
     * Request und speichert diesen im Content und in der Datenbank,
     * wenn im Request der Parameter save auf true gesetzt ist.
     *
     * @param octopusContext Octopus-Context
     * @param save           Gibt an ob eMail-Entwurf gespeichert werden soll.
     * @return eMail-Entwurf
     * @throws BeanException BeanException
     */
    public MailDraft saveDetail(final OctopusContext octopusContext, final Boolean save) throws BeanException {
        if (save != null && save.booleanValue()) {
            MailDraft mailDraft = (MailDraft) getRequest(octopusContext).getBean("MailDraft", "maildraft");
            mailDraft.text = octopusContext.requestAsString("maildrafttext");
            TransactionContext context = (new DatabaseVeraWeb(octopusContext)).getTransactionContext();

            mailDraft.orgunit = ((PersonalConfigAA) octopusContext.personalConfig()).getOrgUnitId();
            mailDraft.verify(octopusContext);

            if (mailDraft.isCorrect()) {
                try {
                    if (mailDraft.id == null) {
                        octopusContext.setContent("countInsert", new Integer(1));
                    } else {
                        octopusContext.setContent("countUpdate", new Integer(1));
                    }

                    saveBean(octopusContext, mailDraft, context);
                    context.commit();
                } catch (Throwable e) {
                    context.rollBack();
                    throw new BeanException("Der Maildraft konnte nicht gespeichert werden.", e);
                }
            }
            return mailDraft;
        }
        return null;
    }

    /**
     * Octopus-Eingabe-Parameter für {@link #addPlaceholderMap(OctopusContext)}
     */
    public static final String[] INPUT_addPlaceholderMap = {};
    /**
     * Octopus-Eingabe-Parameter für {@link #addPlaceholderMap(OctopusContext)}
     */
    public static final boolean[] MANDATORY_addPlaceholderMap = {};
    /**
     * Octopus-Ausgabe-Parameter für {@link #addPlaceholderMap(OctopusContext)}
     */
    public static final String OUTPUT_addPlaceholderMap = "placeholder";

    public Map<String, String> addPlaceholderMap(OctopusContext context) {
        return Placeholder.toStringMap();
    }

}
