package org.evolvis.veraweb.onlinereg.rest;

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

import de.tarent.octopus.base.AutoclosableList;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.evolvis.veraweb.common.RestPaths;
import org.evolvis.veraweb.export.ValidExportFilter;
import org.evolvis.veraweb.onlinereg.entities.PdfTemplate;
import org.evolvis.veraweb.onlinereg.entities.Person;
import org.evolvis.veraweb.onlinereg.entities.SalutationAlternative;
import org.evolvis.veraweb.onlinereg.utils.PdfTemplateUtilities;
import org.evolvis.veraweb.Constants;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author Atanas Alexandrov, tarent solutions GmbH
 */
@Path(RestPaths.REST_PDFTEMPLATE)
@Produces(MediaType.APPLICATION_JSON)
@Log4j2
public class PdfTemplateResource extends FormDataResource {
    private static final String TMPFILE_PFX = "veraweb-pdfexport.";
    private static final String TMPFILE_EXT = ".pdf";
    private final Integer DAYS_BACK = 1;
    private static final long MILLISECONDS_PER_DAY = 24L * 60 * 60 * 1000;
    private final long PURGE_TIME = System.currentTimeMillis() - (DAYS_BACK * MILLISECONDS_PER_DAY);
    private List<SalutationAlternative> alternativeSalutations;

    @POST
    @Path(RestPaths.REST_PDFTEMPLATE_EDIT)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response editPdfTemplateWithFile(FormDataMultiPart data) {
        Integer id = null;
        if (!data.getField("pdftemplate-id").getValue().isEmpty()) {
            id = Integer.parseInt(data.getField("pdftemplate-id").getValue());
        }

        String name = data.getField("pdftemplate-name").getValue();
        Integer mandantId = Integer.parseInt(data.getField("pdftemplate-orgunit").getValue());

        final File file;
        try {
            file = saveTempFile(data.getField("files"));
            logger.debug(file.exists());
        } catch (IOException e) {
            logger.error("could not write data to tmp file.", e);
            return Response.status(Status.BAD_REQUEST).build();
        }

        final byte[] content;
        try {
            content = IOUtils.toByteArray(new FileInputStream(file));
        } catch (IOException e) {
            logger.error("could not read tmp file.", e);
            return Response.status(Status.BAD_REQUEST).build();
        }

        //create || update: name & contetn
        return editPdfTemplate(id, name, mandantId, content);
    }

    @POST
    @Path(RestPaths.REST_PDFTEMPLATE_EDIT)
    public Response editPdfTemplateWithoutFile(@FormParam("pdftemplate-id") Integer id,
      @FormParam("pdftemplate-name") String name,
      @FormParam("pdftemplate-orgunit") Integer mandantId) {
        //catch when id is null (create new template) and content is null(which is implicit because of @consumes)
        if (id == null) {
            //create: without content
            return Response.status(Status.PRECONDITION_REQUIRED).build();
        }
        //update: name
        return editPdfTemplate(id, name, mandantId, null);
    }

    @POST
    @Path(RestPaths.REST_PDFTEMPLATE_DELETE)
    public Response deletePdfTemplate(@FormParam("templateId[]") List<Integer> idList) {
        if (idList == null || idList.isEmpty()) {
            return Response.status(Status.BAD_REQUEST).build();
        }
        Session session = openSession();
        session.beginTransaction();
        try {
            final Query query = session.getNamedQuery(PdfTemplate.DELETE_PDF_TEMPLATE);
            for (Integer id : idList) {
                query.setParameter(PdfTemplate.PARAM_PDF_ID, id);
                query.executeUpdate();
            }
            session.flush();
            session.getTransaction().commit();
            return Response.ok(idList).build();
        } finally {
            session.close();
        }
    }

    @GET
    @Path(RestPaths.REST_PDFTEMPLATE_GET_ALL)
    public Response listPdfTemplates(@QueryParam("mandantid") Integer mandantId) {
        Session session = openSession();
        try {
            final Query query = session.getNamedQuery(PdfTemplate.GET_PDF_TEMPLATE_LIST_BY_ORGUNIT);
            query.setParameter(PdfTemplate.PARAM_PDF_ORGUNIT, mandantId);
            final List<PdfTemplate> pdfTemplates = query.list();
            if (pdfTemplates.isEmpty()) {
                return Response.status(Status.NO_CONTENT).build();
            }
            return Response.ok(pdfTemplates).build();
        } finally {
            session.close();
        }
    }

    @GET
    @Path(RestPaths.REST_PDFTEMPLATE_EXPORT)
    @Produces(Constants.CONTENT_TYPE_PDF)
    public Response generatePdf(@QueryParam("templateId") Integer pdfTemplateId,
      @QueryParam("eventId") Integer eventId, @javax.ws.rs.core.Context UriInfo ui)
      throws IOException, AutoclosableList.ClosingException {
        if (pdfTemplateId == null || eventId == null) {
            return Response.status(Status.BAD_REQUEST).build();
        }

        final List<Person> people = getPersons(eventId, ui);
        if (people.isEmpty()) {
            return Response.status(Status.NO_CONTENT).build();
        }

        alternativeSalutations = getAlternativeSalutations(pdfTemplateId);
        final String basename = TMPFILE_PFX + new Date().getTime();
        final File dstfile = File.createTempFile(basename + "." + UUID.randomUUID().toString() + ".", TMPFILE_EXT);
        generateFromTemplate(dstfile, people, pdfTemplateId);
        return Response.ok(dstfile).header(Constants.HDR_CONT_DISP,
          "attachment; filename=\"" + basename + TMPFILE_EXT + "\"").build();
    }

    private void generateFromTemplate(final File dstfile, final List<Person> people, final Integer pdfTemplateId)
      throws AutoclosableList.ClosingException, IOException {
        try (final AutoclosableList toClose = new AutoclosableList()) {
            PDDocument finalDoc = toClose.record(new PDDocument());
            final String tempFileWithPdfTemplateContent = writePdfContentFromDbToTempFile(pdfTemplateId, UUID.randomUUID());
            File file = new File(tempFileWithPdfTemplateContent);

            //create new arraylist to cache fields
            //(fields can only be set afterwards, because no duplicates are allowed)
            //so we have to rename all of them and fill them afterwards
            List<PDField> fields = new ArrayList<>();
            int i = 0;

            //XXX adding the entire template for each person makes the output huge
            for (Person person : people) {
                PDDocument template = toClose.record(PDDocument.load(file));
                PDDocumentCatalog docCatalog = template.getDocumentCatalog();
                PDAcroForm acroForm = docCatalog.getAcroForm();
                final Map<String, String> substitutions = getSubstitutions(person);
                for (PDField field : acroForm.getFields()) {
                    field.setValue(substitutions.get(field.getPartialName()));
                    field.setPartialName(field.getPartialName() + i);
                    fields.add(field);
                }
                finalDoc.addPage(docCatalog.getPages().get(0));
                i++;
            }
            PDAcroForm finalForm = new PDAcroForm(finalDoc);
            finalForm.setNeedAppearances(true);
            finalDoc.getDocumentCatalog().setAcroForm(finalForm);
            finalForm.setFields(fields);
            finalDoc.save(dstfile);
        }
        deleteOldPdfFiles();
    }

    private void deleteOldPdfFiles() {
        final File directory = new File(FileUtils.getTempDirectoryPath());
        if (directory.exists()) {
            final File[] listFiles = directory.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                for (File listFile : listFiles) {
                    deleteOldPdfFile(listFile);
                }
            }
        }
    }

    private void deleteOldPdfFile(final File listFile) {
        if (!listFile.getName().startsWith(TMPFILE_PFX)) {
            return;
        }
        if (listFile.lastModified() < PURGE_TIME) {
            if (!listFile.delete()) {
                logger.error("Unable to delete file: " + listFile);
            }
        }
    }

    private Response editPdfTemplate(Integer id, String name, Integer mandantId, byte[] content) {
        if (name == null || name.trim().equals("")) {
            return Response.status(Status.PRECONDITION_FAILED).build();
        } else {
            try {
                final PdfTemplate pdfTemplate = createOrUpdatePdfTemplate(id, name, mandantId, content);
                return Response.ok(pdfTemplate).build();
            } catch (Exception e) {
                logger.error("Creating pdf template failed.", e);
                return Response.status(Status.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

    private String writePdfContentFromDbToTempFile(Integer pdfTemplateId, UUID uuid) throws IOException {
        final PdfTemplate pdfTemplate = getPdfTemplate(pdfTemplateId);
        final byte[] content = pdfTemplate.getContent();
        final File tempFileForPdfTemplate = File.createTempFile(TMPFILE_PFX + new Date().getTime() +
          "." + uuid.toString() + ".", TMPFILE_EXT);
        final OutputStream outputStream = new FileOutputStream(tempFileForPdfTemplate);
        try {
            outputStream.write(content);
        } finally {
            outputStream.close();
        }

        return tempFileForPdfTemplate.toString();
    }

    private Map<String, String> getSubstitutions(Person person) {
        final Map<String, String> substitutions;
        updateSalutation(person);

        substitutions = PlaceholderSubstitution.createMap(person);
        //TODO check if using titel instead of 'title' works or it is an unknown bug
        substitutions.put("titel", person.getTitle_a_e1());

        final String salutationCompleteOne = getSalutationCompleteOne(person);
        substitutions.put("salutationComplete1", salutationCompleteOne);
        final String salutationCompleteTwo = getSalutationCompleteTwo(person);
        substitutions.put("salutationComplete2", salutationCompleteTwo);
        final String envelopeOne = getEnvelopeOne(person);
        substitutions.put("envelope1", envelopeOne);

        return substitutions;
    }

    private void updateSalutation(Person person) {
        for (SalutationAlternative alternativeSalutation : alternativeSalutations) {
            if (alternativeSalutation.getSalutation_id().equals(person.getFk_salutation_a_e1())) {
                person.setSalutation_a_e1(alternativeSalutation.getContent());
            }
        }
    }

    private List<SalutationAlternative> getAlternativeSalutations(Integer pdfTemplateId) {
        final Session session = openSession();
        try {
            final Query query = session.getNamedQuery(SalutationAlternative.GET_SALUTATION_ALTERNATIVE_BY_PDF_ID);
            query.setParameter(SalutationAlternative.PARAM_PDFTEMPLATE_ID, pdfTemplateId);
            if (query.list().isEmpty()) {
                return new ArrayList<>();
            }
            return (List<SalutationAlternative>) query.list();
        } finally {
            session.close();
        }
    }

    private String getEnvelopeOne(Person person) {
        final PdfTemplateUtilities pdfTemplateUtilities = new PdfTemplateUtilities(person);
        return pdfTemplateUtilities.getEnvelopeOne();
    }

    private String getSalutationCompleteOne(Person person) {
        final PdfTemplateUtilities pdfTemplateUtilities = new PdfTemplateUtilities(person);
        return pdfTemplateUtilities.getSalutationCompleteOne();
    }

    private String getSalutationCompleteTwo(Person person) {
        final PdfTemplateUtilities pdfTemplateUtilities = new PdfTemplateUtilities(person);
        return pdfTemplateUtilities.getSalutationCompleteTwo();
    }

    private PdfTemplate getPdfTemplate(Integer pdfTemplateId) {
        final Session session = openSession();
        try {
            final Query query = session.getNamedQuery(PdfTemplate.GET_PDF_TEMPLATE);
            query.setParameter(PdfTemplate.PARAM_PDF_ID, pdfTemplateId);
            return (PdfTemplate) query.uniqueResult();
        } finally {
            session.close();
        }
    }

    private List<Person> getPersons(Integer eventId, UriInfo ui) {
        final Session session = openSession();
        try {
            MultivaluedMap<String, String> params = ui.getQueryParameters();

            Map<String, String> filterSettings = new HashMap<>();
            params.keySet().stream()
              .filter(key -> key.startsWith("filter"))
              .filter(key -> ValidExportFilter.isValidFilterSetting(key, params.getFirst(key)))
              .forEach(key -> filterSettings.put(key, params.getFirst(key)));

            Query query = session.createQuery(buildQuery(filterSettings));

            query.setParameter("eventid", eventId);

            filterSettings.entrySet().forEach(entry -> {

                if (!ValidExportFilter.INVITATIONSTATUS_FILTER.key.equals(entry.getKey()) &&
                  !ValidExportFilter.SEARCHWORD_FILTER.key.equals(entry.getKey())) {
                    query.setParameter(entry.getKey(), Integer.valueOf(entry.getValue()));
                }
            });
            return (List<Person>) query.list();
        } finally {
            session.close();
        }
    }

    private String buildQuery(Map<String, String> filterSettings) {
        String baseQuery = "SELECT p FROM Person p JOIN Guest g ON (p.pk = g.fk_person) WHERE g.fk_event=:eventid";

        StringBuilder sqlWithAdditionalFilters = new StringBuilder(baseQuery);

        for (Map.Entry<String, String> entry : filterSettings.entrySet()) {
            sqlWithAdditionalFilters.append(" AND ");
            String filterValue;
            if (ValidExportFilter.INVITATIONSTATUS_FILTER.key.equals(entry.getKey())
              || ValidExportFilter.SEARCHWORD_FILTER.key.equals(entry.getKey())) {
                filterValue = entry.getValue();
            } else {
                filterValue = ":" + entry.getKey();
            }
            String partial = ValidExportFilter.buildDBPathPartial(entry.getKey(), filterValue);
            if (partial != null) {
                sqlWithAdditionalFilters.append(partial);
            }
        }
        sqlWithAdditionalFilters.append(" ORDER BY p.lastname_a_e1 ASC ");
        return sqlWithAdditionalFilters.toString();
    }

    private PdfTemplate createOrUpdatePdfTemplate(Integer id, String name, Integer mandantId, byte[] content) {
        PdfTemplate pdfTemplate;
        if (id != null) {
            pdfTemplate = handlePdfTemplateUpdate(id, name, content);
        } else {
            pdfTemplate = handlePdfTemplateCreate(name, mandantId, content);
        }
        return pdfTemplate;
    }

    private PdfTemplate handlePdfTemplateCreate(String name, Integer mandantId, byte[] content) {
        final Session session = openSession();
        session.beginTransaction();
        try {
            PdfTemplate pdfTemplate = initPdfTemplate(name, mandantId, content);
            session.save(pdfTemplate);
            session.flush();
            session.getTransaction().commit();
            return pdfTemplate;
        } finally {
            session.close();
        }
    }

    private PdfTemplate handlePdfTemplateUpdate(Integer id, String name, byte[] content) {
        final Session session = openSession();
        session.beginTransaction();
        try {
            PdfTemplate pdfTemplate = getExistingTemplate(id, session);
            if (content == null) {
                updatePdfTemplate(session, id, name);
            } else {
                updatePdfTemplateWithContent(session, id, name, content);
            }

            session.flush();
            session.getTransaction().commit();
            return pdfTemplate;
        } finally {
            session.close();
        }
    }

    private PdfTemplate getExistingTemplate(Integer id, Session session) {
        final Query query = session.getNamedQuery(PdfTemplate.GET_PDF_TEMPLATE);
        query.setParameter(PdfTemplate.PARAM_PDF_ID, id);
        return (PdfTemplate) query.uniqueResult();
    }

    private PdfTemplate initPdfTemplate(String name, Integer mandantId, byte[] content) {
        PdfTemplate pdfTemplate = new PdfTemplate();
        pdfTemplate.setName(name);
        pdfTemplate.setContent(content);
        pdfTemplate.setFk_orgunit(mandantId);

        return pdfTemplate;
    }

    private void updatePdfTemplate(Session session, Integer id, String name) {
        final Query query = session.getNamedQuery(PdfTemplate.UPDATE_PDF_TEMPLATE);
        setAndExecuteQuery(query, id, name);
    }

    private void updatePdfTemplateWithContent(Session session, Integer id, String name, byte[] content) {
        final Query query = session.getNamedQuery(PdfTemplate.UPDATE_PDF_TEMPLATE_CONTENT);
        query.setParameter(PdfTemplate.PARAM_PDF_CONTENT, content);
        setAndExecuteQuery(query, id, name);
    }

    private void setAndExecuteQuery(Query query, Integer id, String name) {
        query.setParameter(PdfTemplate.PARAM_PDF_ID, id);
        query.setParameter(PdfTemplate.PARAM_PDF_NAME, name);
        query.executeUpdate();
    }
}
