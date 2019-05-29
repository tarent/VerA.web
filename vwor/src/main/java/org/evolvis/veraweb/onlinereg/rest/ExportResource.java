package org.evolvis.veraweb.onlinereg.rest;

/*-
 * Veranstaltungsmanagement VerA.web (platform-independent
 * webservice-based event management) is Copyright
 *  © 2018 Атанас Александров (sirakov@gmail.com)
 *  © 2014, 2015, 2016, 2017 Атанас Александров (a.alexandrov@tarent.de)
 *  © 2013 Иванка Александрова (i.alexandrova@tarent.de)
 *  © 2013 Patrick Apel (p.apel@tarent.de)
 *  © 2016 Eugen Auschew (e.auschew@tarent.de)
 *  © 2013 Andrei Boulgakov (a.boulgakov@tarent.de)
 *  © 2013 Valentin But (v.but@tarent.de)
 *  © 2016 Lukas Degener (l.degener@tarent.de)
 *  © 2017 Axel Dirla (a.dirla@tarent.de)
 *  © 2015 Julian Drawe (j.drawe@tarent.de)
 *  © 2014 Dominik George (d.george@tarent.de)
 *  © 2013 Sascha Girrulat (s.girrulat@tarent.de)
 *  © 2008 David Goemans (d.goemans@tarent.de)
 *  © 2018 Christian Gorski (c.gorski@tarent.de)
 *  © 2015 Viktor Hamm (v.hamm@tarent.de)
 *  © 2013 Katja Hapke (k.hapke@tarent.de)
 *  © 2013 Hendrik Helwich (h.helwich@tarent.de)
 *  © 2018 Thomas Hensel (t.hensel@tarent.de)
 *  © 2018, 2019 Benedict Hoeger (b.hoeger@tarent.de)
 *  © 2018, 2019 Titian Horvath (t.horvath@tarent.de)
 *  © 2005, 2006, 2007, 2008 Christoph Jerolimov (jerolimov@gmx.de)
 *  © 2018, 2019 Timo Kanera (t.kanera@tarent.de)
 *  © 2008, 2009, 2010 Carsten Klein (c.klein@tarent.de)
 *  © 2014 Martin Ley (m.ley@tarent.de)
 *  © 2014, 2015 Max Marche (m.marche@tarent.de)
 *  © 2007 Jan Meyer (jan@evolvis.org)
 *  © 2013, 2014, 2015, 2016, 2017, 2018, 2019
 *     mirabilos (t.glaser@tarent.de)
 *  © 2016 Cristian Molina (c.molina@tarent.de)
 *  © 2018 Yorka Neumann (y.neumann@tarent.de)
 *  © 2017 Michael Nienhaus (m.nienhaus@tarent.de)
 *  © 2013 Claudia Nuessle (c.nuessle@tarent.de)
 *  © 2014, 2015 Jon Nuñez Alvarez (j.nunez-alvarez@tarent.de)
 *  © 2016 Jens Oberender (j.oberender@tarent.de)
 *  © 2016, 2017 Miluška Pech (m.pech@tarent.de)
 *  © 2009 Martin Pelzer (m.pelzer@tarent.de)
 *  © 2013 Marc Radel (m.radel@tarent.de)
 *  © 2013 Sebastian Reimers (s.reimers@tarent.de)
 *  © 2015 Charbel Saliba (c.saliba@tarent.de)
 *  © 2008, 2009, 2010 Thomas Schmitz (t.schmitz@tarent.de)
 *  © 2013 Volker Schmitz (v.schmitz@tarent.de)
 *  © 2014 Sven Schumann (s.schumann@tarent.de)
 *  © 2014 Sevilay Temiz (s.temiz@tarent.de)
 *  © 2013 Kevin Viola Schmitz (k.schmitz@tarent.de)
 *  © 2015 Stefan Walenda (s.walenda@tarent.de)
 *  © 2015, 2016, 2017 Max Weierstall (m.weierstall@tarent.de)
 *  © 2013 Rebecca Weinz (r.weinz@tarent.de)
 *  © 2015, 2016 Stefan Weiz (s.weiz@tarent.de)
 *  © 2015, 2016 Tim Zimmer (t.zimmer@tarent.de)
 * and older code, Copyright © 2004–2008 ⮡ tarent GmbH and contributors.
 * Licensor is tarent solutions GmbH, http://www.tarent.de/
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, see: http://www.gnu.org/licenses/
 */

import org.evolvis.veraweb.export.CsvExporter;
import org.evolvis.veraweb.onlinereg.entities.Event;
import org.evolvis.veraweb.onlinereg.entities.OptionalField;
import org.evolvis.veraweb.onlinereg.utils.KeepOpenWriter;
import org.evolvis.veraweb.onlinereg.utils.VworConstants;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.*;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import org.evolvis.veraweb.common.RestPaths;

/**
 * Created by mweier on 23.03.16.
 */
@Path(RestPaths.REST_EXPORT)
@Produces(VworConstants.TEXT_CSV_CONTENT_TYPE)
public class ExportResource extends AbstractResource {

    @javax.ws.rs.core.Context
    ResourceContext resourceContext;

    private InitialContext initContext;
    private static final String CONFIG_FILE_NAME = "config.jsn";
    private static final String CONFIG_PLACEHOLDER = "__event_id_placeholder__";

    private Event getEvent(int eventId) {
        final Session session = openSession();
        try {
            final Query query = session.getNamedQuery("Event.getEvent");
            query.setParameter("pk", eventId);
            return (Event) query.uniqueResult();
        } finally {
            session.close();
        }
    }

    @POST
    @Path(RestPaths.REST_EXPORT_GET_GUESTLIST)
    public Response getGuestList(@PathParam("eventId") final int eventId,
                                 MultivaluedMap<String, String> params,
                                 @FormParam("selectedFields[]") List<String> selList)
            throws NamingException, UnsupportedEncodingException {
        final Event event = getEvent(eventId);
        final String downloadFilename = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "_export.csv";
        if (initContext == null) {
            initContext = new InitialContext();
        }
        final Context namingContext = (Context) initContext.lookup("java:comp/env");
        final DataSource dataSource = (DataSource) namingContext.lookup("jdbc/vwonlinereg");

        final Properties properties = new Properties();
        properties.setProperty("event.shortname", event.getShortname());
        properties.setProperty("event.begin", String.valueOf(event.getDatebegin().getTime()));

        params.keySet().forEach(key -> properties.setProperty(key, params.getFirst(key)));

        Map<String, String> filterSettings = new HashMap<>();
        params.keySet().stream().filter(key -> key.startsWith("filter")).forEach(key -> filterSettings.put(key, params.getFirst(key)));

        final InputStream configFileAsStream = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE_NAME);
        final Reader reader = new InputStreamReader(configFileAsStream, "utf-8");
        final Map<String, String> substitutions = new HashMap<>();
        substitutions.put(CONFIG_PLACEHOLDER, String.valueOf(eventId));

        addOptionalFieldsSubstitutions(eventId, substitutions);

        StreamingOutput stream = os -> {
            final Writer writer = new BufferedWriter(new OutputStreamWriter(os));
            final CsvExporter csvExporter = new CsvExporter(reader, new KeepOpenWriter(writer), dataSource, properties, selList);

            csvExporter.export(substitutions, filterSettings);

            writer.flush();
        };

        return Response.ok(stream).header("Content-Disposition", "attachment;filename=" + downloadFilename + ";charset=Unicode")
                .build();
    }

    private void addOptionalFieldsSubstitutions(@PathParam("eventId") int eventId, Map<String, String> substitutions) {
        final OptionalFieldResource optionalFieldResource = resourceContext.getResource(OptionalFieldResource.class);
        final List<OptionalField> optionalFields = optionalFieldResource.getOptionalFields(eventId);

        for (int i = 0; i < optionalFields.size(); i++) {
            final OptionalField currentField = optionalFields.get(i);
            if (i < 10) {
                substitutions.put("__OPTIONAL_FIELD_LABEL_0" + i + "__", currentField.getLabel());
                substitutions.put("__optional_field_0" + i + "_id_placeholder__", currentField.getPk().toString());
            } else {
                substitutions.put("__OPTIONAL_FIELD_LABEL_" + i + "__", currentField.getLabel());
                substitutions.put("__optional_field_" + i + "_id_placeholder__", currentField.getPk().toString());
            }
        }
    }

}
