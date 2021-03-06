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

import de.tarent.aa.veraweb.beans.Categorie;
import de.tarent.aa.veraweb.beans.PersonCategorie;
import de.tarent.aa.veraweb.utils.VerawebMessages;
import de.tarent.aa.veraweb.utils.i18n.LanguageProvider;
import de.tarent.aa.veraweb.utils.i18n.LanguageProviderHelper;
import de.tarent.dblayer.sql.SQL;
import de.tarent.dblayer.sql.clause.Expr;
import de.tarent.dblayer.sql.statement.Select;
import de.tarent.dblayer.sql.statement.Update;
import de.tarent.octopus.beans.Bean;
import de.tarent.octopus.beans.BeanException;
import de.tarent.octopus.beans.Database;
import de.tarent.octopus.beans.TransactionContext;
import de.tarent.octopus.beans.veraweb.DatabaseVeraWeb;
import de.tarent.octopus.beans.veraweb.ListWorkerVeraWeb;
import de.tarent.octopus.server.OctopusContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Dieser Octopus-Worker bearbeitet Personen-Kategorien-Listen.
 */
public class PersonCategorieWorker extends ListWorkerVeraWeb {
    /**
     * Der Konstruktor legt den Bean-Namen fest.
     */
    public PersonCategorieWorker() {
        super("PersonCategorie");
    }

    //
    // Oberklasse BeanListWorker
    //
    @Override
    protected void extendWhere(OctopusContext octopusContext, Select select) throws BeanException, IOException {
        Bean bean = (Bean) octopusContext.contentAsObject("person");
        select.where(Expr.equal("fk_person", bean.getField("id")));
    }

    @Override
    protected void extendColumns(OctopusContext octopusContext, Select select) throws BeanException, IOException {
        select.join("veraweb.tcategorie", "fk_categorie", "tcategorie.pk");
        select.selectAs("tcategorie.rank", "catrank");
        select.selectAs("tcategorie.catname", "name");
        select.selectAs("tcategorie.flags", "flags");
    }

    @Override
    public void saveList(OctopusContext octopusContext) throws BeanException, IOException {
        handleRemoveCategoriesFromGuest(octopusContext);

        super.saveList(octopusContext);

        String addRank = octopusContext.requestAsString("add-rank");
        String addCategorie = octopusContext.requestAsString("add-categorie");
        if (addRank != null && addRank.length() != 0 && (addCategorie == null || addCategorie.length() == 0)) {
            List errors = (List) octopusContext.contentAsObject(OUTPUT_saveListErrors);
            if (errors == null) {
                errors = new ArrayList();
                octopusContext.setContent(OUTPUT_saveListErrors, errors);
            }

            LanguageProviderHelper languageProviderHelper = new LanguageProviderHelper();
            LanguageProvider languageProvider = languageProviderHelper.enableTranslation(octopusContext);

            errors.add(languageProvider.getProperty("PERSON_CATEGORIES_SELECT_WARNING"));
        }

        Integer countRemove = (Integer) octopusContext.getContextField("countRemove");
        Integer countUpdate = (Integer) octopusContext.getContextField("countUpdate");
        Integer countInsert = (Integer) octopusContext.getContextField("countInsert");

        if (countRemove != null || countUpdate != null || countInsert != null) {
            VerawebMessages verawebMessages = new VerawebMessages(octopusContext);
            octopusContext.setContent("noChangesMessage", verawebMessages.getMessageNoChanges());
        }

        String entity = octopusContext.requestAsString("entity");
        if (entity != null && entity.length() != 0) {
            octopusContext.setContent("noChangesMessage", false);
            octopusContext.setContent("entityOverwrite", entity);

            if ("PERSON".equals(entity)) {
                octopusContext.setContextField("countInsert", null);
                octopusContext.setContextField("countUpdate", countInsert);
            }
        }
    }

    private void handleRemoveCategoriesFromGuest(OctopusContext octopusContext) throws BeanException {
        Map requestParameters = octopusContext.getRequestObject().getRequestParameters();

        // First we check, if we have an "delete" action
        if (requestParameters.get("remove") != null && requestParameters.get("remove").toString().equals("Entfernen")) {
            handleDeleteAction(octopusContext, requestParameters);
        }
    }

    private void handleDeleteAction(OctopusContext octopusContext, Map requestParameters) throws BeanException {
        for (Object requestParameter : requestParameters.entrySet()) {
            // We need to filter the request parameter(s) by pattern "bean.id-select".
            // The bean.id is the category id from the table tperson_category
            if (requestParameter.toString().contains("-")) {
                deleteCategoryFromGuest(octopusContext, requestParameters, requestParameter);
            }
        }
    }

    /**
     * Handle request parameter conform with the pattern bean.id-select. The bean.id is the category id from the table
     * tperson_category.
     *
     * @param octopusContext    The {@link de.tarent.octopus.server.OctopusContext}
     * @param requestParameters All request parameters
     * @param requestParameter  The current request parameter, which is inspected and potentially used for deletion
     * @throws BeanException BeanException
     */
    private void deleteCategoryFromGuest(OctopusContext octopusContext, Map requestParameters, Object requestParameter)
      throws BeanException {
        final String[] requestParameterParts = requestParameter.toString().split("-");
        final String[] selectParts = requestParameterParts[1].split("=");
        if (selectParts[0].equals("select") && selectParts[1].equals("true")) {
            final Integer categoryId = getCategoryId(requestParameters, requestParameterParts[0]);
            final Integer personId = new Integer(requestParameters.get("add-person").toString());
            updateGuestData(octopusContext, categoryId, personId);
        }
    }

    private void updateGuestData(OctopusContext octopusContext, Integer categoryId, Integer personId)
      throws BeanException {
        final DatabaseVeraWeb database = new DatabaseVeraWeb(octopusContext);
        final Update update = SQL.Update(database).
          table("veraweb.tguest").
          update("fk_category", null).
          where(Expr.equal("fk_category", categoryId)).
          whereAnd(Expr.equal("fk_person", personId));

        final TransactionContext transactionalContext = new DatabaseVeraWeb(octopusContext).getTransactionContext();
        transactionalContext.execute(update);
    }

    private Integer getCategoryId(Map requestParameters, String requestParameterPart) {
        final Integer personCategoryId = new Integer(requestParameterPart);
        return new Integer(requestParameters.get(personCategoryId + "-categorie").toString());
    }

    @Override
    protected void saveBean(OctopusContext octopusContext, Bean bean, TransactionContext transactionContext)
      throws BeanException, IOException {
        super.saveBean(octopusContext, bean, transactionContext);
        WorkerFactory.getPersonDetailWorker(octopusContext).
          updatePerson(octopusContext, null, ((PersonCategorie) bean).person);
    }

    public void addCategoryAssignment(OctopusContext octopusContext, Integer categoryId, Integer personId,
      Database database, TransactionContext transactionContext)
      throws BeanException, IOException {
        addCategoryAssignment(octopusContext, categoryId, personId, database, transactionContext, true);
    }

    public PersonCategorie addCategoryAssignment(final OctopusContext octopusContext, Integer categoryId,
      Integer personId, Database database,
      TransactionContext transactionContext, boolean save)
      throws BeanException, IOException {
        Categorie category =
          (Categorie) database.getBean("Categorie", categoryId, transactionContext == null ? database : transactionContext);
        if (category != null) {
            Select select = database.getCount("PersonCategorie");
            select.where(Expr.equal("fk_person", personId));
            select.whereAnd(Expr.equal("fk_categorie", categoryId));
            Integer count = database.getCount(select);

            if (count.intValue() == 0) {
                PersonCategorie personCategory = (PersonCategorie) database.createBean("PersonCategorie");
                personCategory.categorie = categoryId;
                personCategory.person = personId;
                personCategory.rank = category.rank;

                personCategory.verify(octopusContext);

                if (personCategory.isCorrect()) {
                    if (save) {
                        this.saveBean(octopusContext, personCategory, transactionContext);
                    }
                    return personCategory;
                }
            }
        }

        return null;
    }

    public void removeAllCategoryAssignments(OctopusContext octopusContext, Integer personId, Database database,
      TransactionContext transactionContext) throws BeanException, IOException {
        try {
            transactionContext.execute(
              SQL.Delete(database).from("veraweb.tperson_categorie").where(Expr.equal("fk_person", personId))
            );
            transactionContext.commit();
        } catch (BeanException e) {
            throw new BeanException("Die Kategoriezuweisungen konnte nicht aufgehoben werden.", e);
        }
    }

    public void removeCategoryAssignment(OctopusContext octopusContext, Integer categoryId, Integer personId,
      Database database, TransactionContext transactionContext)
      throws BeanException, IOException {
        Categorie category =
          (Categorie) database.getBean("Categorie", categoryId, transactionContext == null ? database : transactionContext);
        if (category != null) {
            Select select = database.getSelect("PersonCategorie");
            select.where(Expr.equal("fk_person", personId));
            select.whereAnd(Expr.equal("fk_categorie", categoryId));
            PersonCategorie personCategory = (PersonCategorie) database.getBean("PersonCategorie", select, transactionContext);
            if (personCategory != null) {
                database.removeBean(personCategory, transactionContext);
            }
        }
    }
}
