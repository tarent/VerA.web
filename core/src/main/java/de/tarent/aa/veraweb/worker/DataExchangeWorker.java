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

import de.tarent.aa.veraweb.beans.Import;
import de.tarent.aa.veraweb.beans.Person;
import de.tarent.aa.veraweb.beans.facade.PersonConstants;
import de.tarent.aa.veraweb.utils.Exporter;
import de.tarent.aa.veraweb.utils.Importer;
import de.tarent.aa.veraweb.utils.OctopusHelper;
import de.tarent.aa.veraweb.utils.VerawebDigester;
import de.tarent.data.exchange.ExchangeFormat;
import de.tarent.data.exchange.Exchanger;
import de.tarent.dblayer.engine.Result;
import de.tarent.dblayer.sql.clause.Expr;
import de.tarent.dblayer.sql.clause.Order;
import de.tarent.dblayer.sql.clause.RawClause;
import de.tarent.dblayer.sql.clause.WhereList;
import de.tarent.dblayer.sql.statement.Select;
import de.tarent.octopus.PersonalConfigAA;
import de.tarent.octopus.beans.Bean;
import de.tarent.octopus.beans.BeanException;
import de.tarent.octopus.beans.Database;
import de.tarent.octopus.beans.DatabaseUtilizer;
import de.tarent.octopus.beans.TransactionContext;
import de.tarent.octopus.beans.veraweb.DatabaseVeraWeb;
import de.tarent.octopus.config.TcModuleConfig;
import de.tarent.octopus.config.TcPersonalConfig;
import de.tarent.octopus.content.TcContentProzessException;
import de.tarent.octopus.exchange.ConfiguredExchangeFormat;
import de.tarent.octopus.response.TcBinaryResponseEngine;
import de.tarent.octopus.server.Context;
import de.tarent.octopus.server.OctopusContext;
import lombok.extern.log4j.Log4j2;
import lombok.val;

import java.io.BufferedInputStream;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Diese Klasse stellt einen Octopus-Worker für den Im- und Export von
 * Personendaten im VerA.web-eigenen Format dar.
 *
 * @author mikel
 */
@Log4j2
public class DataExchangeWorker {
    //
    // Konstanten
    //
    /**
     * Vorgabewert für den Parameter <code>formatEnumKey</code> von {@link #getFormats(OctopusContext, String)}
     */
    public final static String KEY_FORMAT_NAMES = "exchangeFormats";

    /**
     * Exportfilterwert: Filtrierung nach Kategorie
     */
    public final static String EXPORT_FILTER_CATEGORY = "category";

    /**
     * Exportfilterwert: Filtrierung nach Veranstaltung
     */
    public final static String EXPORT_FILTER_EVENT = "event";

    /**
     * Parameterwert: beliebige Personen
     */
    public final static String PARAM_DOMAIN_VALUE_ALL = "all";
    /**
     * Parameterwert: Personen des gleichen Mandanten
     */
    public final static String PARAM_DOMAIN_VALUE_OU = "ou";

    //
    // Octopus-Aktionen
    //
    /**
     * Octopus-Eingabe-Parameter für {@link #getFormats(OctopusContext, String)}
     */
    public static final String[] INPUT_getFormats = { "formatEnumKey" };
    /**
     * Octopus-Eingabepflicht-Parameter für {@link #getFormats(OctopusContext, String)}
     */
    public static final boolean[] MANDATORY_getFormats = { false };
    /**
     * Octopus-Ausgabe-Parameter für {@link #getFormats(OctopusContext, String)}
     */
    public static final String OUTPUT_getFormats = "formats";

    /**
     * Diese Octopus-Aktion liefert eine {@link Map} mit verfügbaren Austauschformaten.
     *
     * führt einen Export von Personendaten durch. Dies geschieht
     * je nach Parameter <code>fieldMapping</code> in eine XML-Darstellung von VerA.web
     * oder eine CSV-Datei. Der Exportdatenstrom wird in den Content geschrieben.
     * Zusätzlich kann er an eine Stelle im Dateisystem kopiert werden.
     *
     * @param cntx          Octopus-Kontext
     * @param formatEnumKey optionaler Schlüssel der verfügbaren Schlüssel, Default ist
     *                      {@link #KEY_FORMAT_NAMES}.
     * @return Abbildung von Schlüsselbezeichnern auf {@link ExchangeFormat}-Instanzen.
     */
    @SuppressWarnings("unchecked")
    public Map getFormats(OctopusContext cntx, String formatEnumKey) {
        Map result = Collections.EMPTY_MAP;
        TcModuleConfig moduleConfig = cntx.moduleConfig();
        if (moduleConfig != null) {
            result = new LinkedHashMap();
            Object formatNamesObject = moduleConfig.getParamAsObject(formatEnumKey != null ? formatEnumKey : KEY_FORMAT_NAMES);
            if (formatNamesObject instanceof List) {
                for (Object o : ((List) formatNamesObject)) {
                    String key = o.toString();
                    ExchangeFormat format = getExchangeFormat(moduleConfig.getParams(), key, null);
                    if (format != null) {
                        if (logger.isDebugEnabled()) {
                            logger.debug("Format " + key + ": " + format);
                        }
                        result.put(key, format);
                    }
                }
            }
        }
        return result;
    }

    /**
     * Octopus-Eingabe-Parameter für {@link #export(OctopusContext, String, String, String, Integer, Integer, String)}
     */
    public static final String[] INPUT_export = { "format", "filenc", "exportFilter", "exportEvent", "exportCategory", "domain" };
    /**
     * Octopus-Eingabepflicht-Parameter für {@link #export(OctopusContext, String, String, String, Integer, Integer, String)}
     */
    public static final boolean[] MANDATORY_export = { true, true, false, false, false, false };
    /**
     * Octopus-Ausgabe-Parameter für {@link #export(OctopusContext, String, String, String, Integer, Integer, String)}
     */
    public static final String OUTPUT_export = "stream";

    /**
     * Diese Octopus-Aktion führt einen Export von Personendaten durch. Dies geschieht
     * in einem konfigurierten Format. Der Exportdatenstrom wird in den Content geschrieben.
     * Zusätzlich kann er an eine Stelle im Dateisystem kopiert werden.
     *
     * @param cntx      Octopus-Kontext
     * @param formatKey Schlüssel der Datenformatbeschreibung in der Modulkonfiguration
     * @param filenc    Encoding der zu schreibenden Datei, ggfs. mit „+BOM“ am Ende
     * @param filter    {@link #EXPORT_FILTER_CATEGORY} oder {@link #EXPORT_FILTER_EVENT},
     *                  je nach anzuwendenen Filter
     * @param event     Veranstaltungsfilter
     * @param category  Kategorienfilter
     * @param domain    Domäne, aus der die Personen stammen ("all" oder "ou")
     * @return exportierter Datenstrom
     * @throws TcContentProzessException bei ungültigen Parameterwerten.
     * @throws IOException               ioException
     */
    public Map export(final OctopusContext cntx, final String formatKey, final String filenc, final String filter,
      final Integer event,
      final Integer category, final String domain) throws TcContentProzessException, IOException {
        TcModuleConfig moduleConfig = cntx.moduleConfig();
        assert moduleConfig != null;
        // Zunächst mal die benötigten Objekte erstellen
        final ExchangeFormat format =
          getExchangeFormat(moduleConfig.getParams(), formatKey, cntx.getRequestObject().getRequestParameters());
        if (format == null) {
            throw new TcContentProzessException("Unbekannter Exportformatschlüssel '" + formatKey + "'.");
        }
        final Database database = new DatabaseVeraWeb(cntx);

        final PipedInputStream pis = new PipedInputStream();
        final PipedOutputStream pos = new PipedOutputStream(pis);

        final Charset ocs;
        if ("UTF-8".equals(filenc)) {
            ocs = StandardCharsets.UTF_8;
        } else if ("UTF-8+BOM".equals(filenc)) {
            ocs = StandardCharsets.UTF_8;
            pos.write(new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF });
        } else if ("UTF-16BE+BOM".equals(filenc)) {
            ocs = StandardCharsets.UTF_16BE;
            pos.write(new byte[] { (byte) 0xFE, (byte) 0xFF });
        } else if ("UTF-16LE+BOM".equals(filenc)) {
            ocs = StandardCharsets.UTF_16LE;
            pos.write(new byte[] { (byte) 0xFF, (byte) 0xFE });
        } else if ("UTF-32BE+BOM".equals(filenc)) {
            if (!Charset.isSupported("UTF-32BE")) {
                throw new TcContentProzessException("JVM unterstützt Encoding nicht: " + filenc);
            }
            ocs = Charset.forName("UTF-32BE");
            pos.write(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0xFE, (byte) 0xFF });
        } else if ("UTF-32LE+BOM".equals(filenc)) {
            if (!Charset.isSupported("UTF-32LE")) {
                throw new TcContentProzessException("JVM unterstützt Encoding nicht: " + filenc);
            }
            ocs = Charset.forName("UTF-32LE");
            pos.write(new byte[] { (byte) 0xFF, (byte) 0xFE, (byte) 0x00, (byte) 0x00 });
        } else {
            try {
                if (!Charset.isSupported(filenc)) {
                    throw new TcContentProzessException("JVM unterstützt Encoding nicht: " + filenc);
                }
            } catch (IllegalCharsetNameException icne) {
                throw new TcContentProzessException("Ungültiger Encoding-Name: " + filenc);
            }
            ocs = Charset.forName(filenc);
        }

        new Thread(new Runnable() {
            public void run() {
                Context.addActive(cntx);

                Exporter exporter = null;
                try {
                    exporter = createExporter(format, database, pos, ocs);

                    // Mandantenbeschränkung
                    TcPersonalConfig pConfig = cntx.personalConfig();
                    Integer orgUnit = null;
                    if (pConfig instanceof PersonalConfigAA) {
                        PersonalConfigAA aaConfig = (PersonalConfigAA) pConfig;
                        if (!(PARAM_DOMAIN_VALUE_ALL.equals(domain) && pConfig.isUserInGroup(PersonalConfigAA.GROUP_ADMIN))) {
                            orgUnit = aaConfig.getOrgUnitId();
                        }
                    } else {
                        throw new BeanException("Missing user information");
                    }

                    // Beschränkung auf Kategorie, wenn Benutzer eine ausgewählt hat
                    Integer categoryId = null;
                    if (EXPORT_FILTER_CATEGORY.equals(filter) && category > 0) {
                        // category == 0 (wird intern zu null) bedeutet: in irgendeiner Kategorie, = -1 bedeutet: in keiner
                        // Kategorie
                        categoryId = category;
                    }

                    //Den Exporter auf Mandant und Kategorie einschränken. Ist für den CSV-Exporter notwendig, damit keine
                    // überflüssigen
                    // überschriften erzeugt werden.
                    exporter.setOrgUnitId(orgUnit);
                    exporter.setCategoryId(categoryId);

                    // Dann exportieren
                    exporter.startExport();
                    if (EXPORT_FILTER_EVENT.equals(filter)) {
                        // event == 0 (wird intern zu null) bedeutet: in irgendeiner Veranstaltung, = -1 bedeutet: in keiner
                        // Veranstaltung
                        exportEvent(database, event, exporter, orgUnit);
                    } else if (EXPORT_FILTER_CATEGORY.equals(filter)) {
                        // category == 0 (wird intern zu null) bedeutet: in irgendeiner Kategorie, = -1 bedeutet: in keiner
                        // Kategorie
                        exportCategory(database, category, exporter, orgUnit);
                    } else {
                        // guter Default?
                        exportAll(database, exporter, orgUnit);
                    }
                    exporter.endExport();
                } catch (Throwable t) {
                    logger.error("Fehler beim Erstellen des Exports aufgetreten.", t);
                    // This will force a log output.
                    t.printStackTrace(System.out);
                    t.printStackTrace(System.err);
                    try {
                        pos.close();
                    } catch (IOException e) {
                        logger.error("Fehler beim Schließen", e);
                    }
                } finally {
                    try {
                        pos.close();
                    } catch (IOException t) {
                        logger.error("Fehler beim Schließen", t);
                    }
                }
            }
        }).start();

        val contentType = REMOVE_CHARSET_FROM_CONTENT_TYPE.matcher(format.getMimeType()).replaceAll("") +
          "; charset=\"" + ocs.name() + "\"";

        return createBinaryResponse(getFilename(cntx, format), contentType, pis);
    }

    private static final Pattern REMOVE_CHARSET_FROM_CONTENT_TYPE = Pattern.compile("\\s*;\\s*charset\\s*=" +
        "(?:\\s*(?:\"(?:\\\\.|[^\"\\\\])*(?:\"|\\z)|'(?:\\\\.|[^'\\\\])*('|\\Z)|[^\"';]*))*(?=;|\\z)",
      Pattern.CASE_INSENSITIVE);

    /**
     * Octopus-Eingabe-Parameter für {@link #importToTransit(OctopusContext, Map, String, String, String, Integer, Integer, Map)}
     */
    public static final String[] INPUT_importToTransit =
      { "importfile", "format", "filenc", "importSource", "orgUnit", "targetOrgUnit", "CONFIG:importProperties" };
    /**
     * Octopus-Eingabe-Parameter-Pflicht für
     * {@link #importToTransit(OctopusContext, Map, String, String, String, Integer, Integer, Map)}
     */
    public static final boolean[] MANDATORY_importToTransit = { false, false, true, false, false, false, false };
    /**
     * Octopus-Ausgabe-Parameter für {@link #importToTransit(OctopusContext, Map, String, String, String, Integer, Integer, Map)}
     */
    public static final String OUTPUT_importToTransit = "importStatus";

    /**
     * Diese Octopus-Aktion importiert die Personen einer Datei in den Transit-Bereich,
     * also die Tabelle <code>timportperson</code>.
     *
     * @param octopusContext   Octopus-Kontext
     * @param stream           Datei-Upload-Map (enthält unter "ContentStream" einen <code>InputStream</code>)
     * @param formatKey        Schlüssel der Datenformatbeschreibung in der Modulkonfiguration
     * @param filenc           Encoding der Importdatei oder „_auto“
     * @param importSource     Importquellenbeschreibung
     * @param orgUnit          Ziel-Mandant; nur bei Super-Admins beachtetg
     * @param targetOrgUnit    Ziel-Mandant, wenn das Import über das CLI-Tool erfolgt
     * @param importProperties Einstellungen zum Import
     * @return Map mit Informationen zum Import, insbesondere der Anzahl gefundener
     * Datensätze unter "dsCount", der Anzahl Duplikate unter "dupCount", der Anzahl
     * importierter Datensätze unter "saveCount" und der Import-ID unter "id".
     * @throws IOException               FIXME
     * @throws TcContentProzessException FIXME
     * @throws BeanException             beanException
     */
    public Map importToTransit(OctopusContext octopusContext,
      Map stream,
      String formatKey,
      String filenc,
      String importSource,
      Integer orgUnit,
      Integer targetOrgUnit,
      Map importProperties)
      throws BeanException, IOException, TcContentProzessException {
        stream = getStream(octopusContext, stream);
        if (!octopusContext.getStatus().equals("streamClose")) {
            formatKey = getFormatKey(octopusContext, formatKey);
            importSource = getImportSource(octopusContext, importSource);
            Integer mandantId;
            if (targetOrgUnit != null) {
                mandantId = targetOrgUnit;
            } else {
                mandantId = getOrgUnit(octopusContext, orgUnit);
            }
            importProperties = getImportProperties(octopusContext, importProperties);

            TcModuleConfig moduleConfig = octopusContext.moduleConfig();
            assert moduleConfig != null;
            // Zunächst mal die benötigten Objekte erstellen
            ExchangeFormat format = getExchangeFormat(moduleConfig.getParams(), formatKey,
              octopusContext.getRequestObject().getRequestParameters());
            if (format == null) {
                throw new TcContentProzessException("Unbekannter Importformatschl\u00fcssel '" + formatKey + "'.");
            }
            if (importSource == null || importSource.length() == 0) {
                Map<String, String> status = new HashMap<>();
                status.put("invalidData", "importSource");
                octopusContext.setStatus("invalidData");
                return status;
            }

            String filename = (String) stream.get("ContentName");
            if (filename != null && filename.length() != 0) {
                String suffix = filename.lastIndexOf(".") == -1 ? null :
                  filename.substring(filename.lastIndexOf(".") + 1);
                if (suffix != null) {
                    suffix.toLowerCase();
                }

                if (suffix == null || suffix.length() == 0) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Endung der Import-Datei '" + filename + "' konnte nicht festgestellt werden.");
                    }
                } else if (
                  suffix.equals("ods") ||
                    suffix.equals("sxc") ||
                    suffix.equals("xls") ||
                    suffix.equals("pdf") ||
                    suffix.equals("zip") ||
                    suffix.equals("exe")) {
                    Map<String, String> status = new HashMap<>();
                    status.put("invalidData", "fileExtension");
                    status.put("fileextension", suffix);
                    octopusContext.setStatus("invalidData");
                    return status;
                }
            }

            InputStream istream = (InputStream) stream.get("ContentStream");

            if (istream == null || istream.available() <= 0) {
                Map<String, String> status = new HashMap<>();
                status.put("invalidData", "inputStream");
                octopusContext.setStatus("invalidData");
                return status;
            }

            Database database = new DatabaseVeraWeb(octopusContext);
            TransactionContext transactionContext = database.getTransactionContext();
            try {
                if (octopusContext.personalConfig() instanceof PersonalConfigAA) {
                    final PersonalConfigAA aaConfig = (PersonalConfigAA) octopusContext.personalConfig();
                    if (mandantId == null || mandantId == 0 || !aaConfig.isUserInGroup(PersonalConfigAA.GROUP_ADMIN)) {
                        mandantId = aaConfig.getOrgUnitId();
                    }
                } else {
                    throw new TcContentProzessException("Fehlende Benutzerinformation.");
                }

                Charset ics;
                if ("UTF-8".equals(filenc)) {
                    // ensure we can peek at the input octets
                    if (!istream.markSupported()) {
                        istream = new BufferedInputStream(istream);
                    }
                    // skip Byte Order Mark if present
                    istream.mark(8);
                    if (istream.read() != 0xEF || istream.read() != 0xBB || istream.read() != 0xBF) {
                        // no BOM
                        istream.reset();
                    }
                    ics = StandardCharsets.UTF_8;
                } else if ("_auto".equals(filenc)) {
                    // ensure we can peek at the input octets
                    if (!istream.markSupported()) {
                        istream = new BufferedInputStream(istream);
                    }
                    // skip Byte Order Mark if present
                    istream.mark(8);
                    int b0 = istream.read();
                    int b1 = istream.read();
                    int b2 = istream.read();
                    int b3 = istream.read();
                    int bs = 0;
                    if (b0 == 0xEF && b1 == 0xBB && b2 == 0xBF) {
                        // UTF-8 with BOM
                        bs = 3;
                        ics = StandardCharsets.UTF_8;
                    } else if (b0 == 0xFF && b1 == 0xFE && b2 == 0 && b3 == 0) {
                        // UTF-32LE with BOM
                        bs = 4;
                        if (!Charset.isSupported("UTF-32LE")) {
                            throw new TcContentProzessException("JVM unterstützt Encoding nicht: " + "UTF-32LE");
                        }
                        ics = Charset.forName("UTF-32LE");
                    } else if (b0 == 0xFF && b1 == 0xFE) {
                        // UTF-16LE with BOM
                        bs = 2;
                        ics = StandardCharsets.UTF_16LE;
                    } else if (b0 == 0 && b1 == 0 && b2 == 0xFE && b3 == 0xFF) {
                        // UTF-32BE with BOM
                        bs = 4;
                        if (!Charset.isSupported("UTF-32BE")) {
                            throw new TcContentProzessException("JVM unterstützt Encoding nicht: " + "UTF-32BE");
                        }
                        ics = Charset.forName("UTF-32BE");
                    } else if (b0 == 0xFE && b1 == 0xFF) {
                        // UTF-16BE with BOM
                        bs = 2;
                        ics = StandardCharsets.UTF_16BE;
                    } else if (b0 == 0 && b1 == 0 && Charset.isSupported("UTF-32BE")) {
                        // guess UTF-32BE without BOM
                        ics = Charset.forName("UTF-32BE");
                    } else if (b0 == 0) {
                        // guess UTF-16BE without BOM
                        ics = StandardCharsets.UTF_16BE;
                    } else if (b1 == 0 && b2 == 0 && Charset.isSupported("UTF-32LE")) {
                        // guess UTF-32LE without BOM
                        ics = Charset.forName("UTF-32LE");
                    } else if (b1 == 0) {
                        // guess UTF-16LE without BOM
                        ics = StandardCharsets.UTF_16LE;
                    } else if (!Charset.isSupported("cp1252")) {
                        // default to closest thing to cp1252
                        logger.error(
                          "JVM does not support \"cp1252\", falling back to latin1 standard encoding; some characters " +
                            "will be lost!");
                        ics = StandardCharsets.ISO_8859_1;
                    } else {
                        // default to cp1252
                        ics = Charset.forName("cp1252");
                    }
                    istream.reset();
                    istream.skip(bs);
                } else {
                    try {
                        if (!Charset.isSupported(filenc)) {
                            throw new TcContentProzessException("JVM unterstützt Encoding nicht: " + filenc);
                        }
                    } catch (IllegalCharsetNameException icne) {
                        throw new TcContentProzessException("Ungültiger Encoding-Name: " + filenc);
                    }
                    ics = Charset.forName(filenc);
                }

                Importer importer = createImporter(format, transactionContext, istream, ics);
                Import importInstance = createImport(transactionContext, formatKey, importSource, mandantId);
                VerawebDigester digester =
                  new VerawebDigester(octopusContext, transactionContext, importProperties, importSource, importInstance);

                importer.importAll(digester, transactionContext);

                transactionContext.commit();

                // force gc after import
                System.gc();

                return digester.getImportStats();
            } catch (Exception e) {
                logger.error("Fehler beim Import aufgetreten.", e);
                CharArrayWriter caw = new CharArrayWriter();
                PrintWriter pw = new PrintWriter(caw);
                e.printStackTrace(pw);
                pw.close();

                Map<String, String> status = new HashMap<>();
                status.put("invalidData", "errorOnImport");
                status.put("exception", caw.toString());
                status.put("message", e.getLocalizedMessage());
                octopusContext.setStatus("invalidData");
                return status;
            } finally {
                transactionContext.rollBack();
            }
        }
        return null;
    }

    private Map getImportProperties(OctopusContext cntx, Map importProperties) {
        if (importProperties != null) {
            cntx.setSession("importProperties", importProperties);
        } else {
            importProperties = (HashMap) cntx.sessionAsObject("importProperties");
        }
        return importProperties;
    }

    private Integer getOrgUnit(OctopusContext cntx, Integer orgUnit) {
        if (orgUnit != null) {
            cntx.setSession("orgUnit", orgUnit);
        } else {
            orgUnit = (Integer) cntx.sessionAsObject("orgUnit");
        }
        return orgUnit;
    }

    private String getImportSource(OctopusContext cntx, String importSource) {
        if (importSource != null) {
            cntx.setSession("importSource", importSource);
        } else {
            importSource = cntx.sessionAsString("importSource");
        }
        return importSource;
    }

    private String getFormatKey(OctopusContext cntx, String formatKey) {
        if (formatKey != null) {
            cntx.setSession("formatKey", formatKey);
        } else {
            formatKey = cntx.sessionAsString("formatKey");
        }
        return formatKey;
    }

    private Map getStream(OctopusContext cntx, Map stream) {
        if (stream != null) {
            cntx.setSession("stream", stream);
        } else {
            cntx.setStatus("streamClose");
        }
        return stream;
    }

    //
    // geschützte Hilfsmethoden
    //

    /**
     * Diese Methode erstellt eine {@link Map}, aus der die
     * {@link TcBinaryResponseEngine} die Daten für ihre
     * Octopus-Response-Erstellung entnimmt.
     *
     * @param filename    Dateiname, den die Response tragen soll
     * @param mimetype    MIME-Typ, den die Response haben soll
     * @param inputstream Datenstrom, der die Response bilden soll
     * @return eine {@link Map}, in der die Parameter eingetragen sind
     */
    static Map createBinaryResponse(String filename, String mimetype, InputStream inputstream) {
        Map<String, Object> binaryResponse = new HashMap<>();
        binaryResponse.put(TcBinaryResponseEngine.PARAM_TYPE, TcBinaryResponseEngine.BINARY_RESPONSE_TYPE_STREAM);
        binaryResponse.put(TcBinaryResponseEngine.PARAM_FILENAME, filename);
        binaryResponse.put(TcBinaryResponseEngine.PARAM_MIMETYPE, mimetype);
        binaryResponse.put(TcBinaryResponseEngine.PARAM_STREAM, inputstream);
        binaryResponse.put(TcBinaryResponseEngine.PARAM_IS_ATTACHMENT, Boolean.TRUE);
        return binaryResponse;
    }

    /**
     * Diese Methode liest aus einer Konfigurations-{@link Map} ein {@link ExchangeFormat}.
     *
     * @param config    Konfiguration
     * @param formatKey Schlüssel zum Format
     * @param params    {@link Map} mit Parametern, aus denen diejenigen ermittelt werden,
     *                  die dem {@link ExchangeFormat} zuzuordnen sind; dies sind genau die, die einen
     *                  Schlüssel der Form <code>"format-" + formatKey + '-' + choiceKey</code> haben,
     *                  wobei <code>choiceKey</code> über die Schlüssel der {@link Map} iteriert, die
     *                  in den {@link ExchangeFormat#getProperties() Properties} unter dem Schlüssel
     *                  <code>"choices"</code> liegt. Sie werden diesen Properties unter dem jeweiligen
     *                  Schlüssel <code>choiceKey</code> hinzugefügt.
     *                  XXX choices-Support wurde entfernt
     * @return zugehöriges {@link ExchangeFormat} oder <code>null</code>
     */
    static ExchangeFormat getExchangeFormat(Map config, String formatKey, Map params) {
        if (formatKey == null) {
            return null;
        }
        Object formatObject = config.get(formatKey);
        if (!(formatObject instanceof Map)) {
            return null;
        }
        ConfiguredExchangeFormat format = new ConfiguredExchangeFormat((Map) formatObject);
        return format;
    }

    /**
     * Diese Methode liefert zu einem Octopus-Kontext den zu einem bestimmten
     * Format passenden Dateinamen für Dateirückgaben.
     *
     * @param octx   Octopus-Kontext
     * @param format {@link ExchangeFormat}-Instanz
     * @return Dateiname mit Endung
     */
    static String getFilename(OctopusContext octx, ExchangeFormat format) {
        assert octx != null;
        assert format != null;
        String ext = format.getDefaultExtension();
        if (ext == null) {
            ext = "export";
        } else if (ext.length() > 0 && ext.charAt(0) == '.') {
            ext = ext.substring(1);
        }
        String def = "veraweb." + ext;
        return OctopusHelper.getFilename(octx, ext, def);
    }

    /**
     * Diese Methode erstellt und Initialisiert einen {@link Exporter}.
     *
     * @param format   basierendes {@link ExchangeFormat}
     * @param database zu benutzende {@link Database}
     * @param os       Ausgabestrom
     * @param cs       Ausgabe-Encoding (Unix, nicht VerA.web-„Zeichensatz“)
     * @return ein passender {@link Exporter}
     * @throws TcContentProzessException bei Fehlern beim Instanziieren des Exporters.
     */
    static Exporter createExporter(ExchangeFormat format, Database database, OutputStream os, Charset cs)
      throws TcContentProzessException {
        assert format != null;
        assert database != null;
        try {
            Exporter exporter = (Exporter) format.getExporterClass().newInstance();
            if (exporter instanceof Exchanger) {
                Exchanger exchanger = (Exchanger) exporter;
                exchanger.setExchangeFormat(format);
                exchanger.setOutputStream(os);
                exchanger.setFileEncoding(cs);
            }
            if (exporter instanceof DatabaseUtilizer) {
                DatabaseUtilizer dbUtilizer = (DatabaseUtilizer) exporter;
                dbUtilizer.setDatabase(database);
            }
            return exporter;
        } catch (Exception e) {
            throw new TcContentProzessException("Fehler beim Instanziieren des Exporters", e);
        }
    }

    /**
     * Diese Methode exportiert alle Personen, die Gast bei einer bestimmten /
     * irgendeiner / keiner Veranstaltung sind.
     *
     * @param database zu benutzende Datenverbindung
     * @param event    Veranstaltung, deren Gäste exportiert werden sollen; <code>null</code>
     *                 wird interpretiert als "Personen, die bei irgendeiner Veranstaltung Gast sind",
     *                 <code>0</code> als "Personen, die bei keiner Veranstaltung Gast sind".
     * @param exporter zu benutzender {@link Exporter}
     * @param orgUnit  Mandanten-ID, wenn danach gefiltert werden soll
     */
    void exportEvent(Database database, Integer event, Exporter exporter, Integer orgUnit) throws BeanException, IOException {
        assert database != null;
        assert exporter != null;
        Bean samplePerson = database.createBean("Person");
        Bean sampleGuest = database.createBean("Guest");

        Select outer = database.getSelect("Person");
        Select inner = new Select(false).
          from(database.getProperty(sampleGuest, "table")).
          selectAs(database.getProperty(sampleGuest, "person"), "person");

        WhereList outerWhere = new WhereList();
        outerWhere.addAnd(Expr.equal("deleted", PersonConstants.DELETED_FALSE));
        if (orgUnit != null) {
            outerWhere.addAnd(Expr.equal("tperson.fk_orgunit", orgUnit));
        }

        /*
         * cklein 2009-07-16: fixes issue 1815 - although option "Alle" yielded no return value
         * jetty used for testing made it a valid integer object of value 0, which broke
         * existing code relying on the fact that the request parameter would be null.
         * Keine/None now equals -1.
         */
        if (event == null || event == 0) {
            outerWhere.addAnd(Expr.in(
              database.getProperty(samplePerson, "id"),
              new RawClause('(' + inner.toString() + ')')));
        } else if (event == -1) {
            outerWhere.addAnd(new RawClause("NOT " + Expr.in(
              database.getProperty(samplePerson, "id"),
              new RawClause('(' + inner.toString() + ')')).clauseToString()));
        } else {
            inner.where(Expr.equal(
              database.getProperty(sampleGuest, "event"),
              event));
            outerWhere.addAnd(Expr.in(
              database.getProperty(samplePerson, "id"),
              new RawClause('(' + inner.toString() + ')')));
        }

        exportSelect(database, outer.where(outerWhere), exporter);
    }

    /**
     * Diese Methode exportiert alle Personen, die in einer bestimmten /
     * irgendeiner / keiner Kategorie sind.<br>
     * Achtung: hier wird davon ausgegangen, dass Personen des eigenen Mandanten nicht
     * in Veranstaltungen anderer Mandanten auftauchen.
     *
     * @param database zu benutzende Datenverbindung
     * @param category Kategorie, deren Personen exportiert werden sollen; <code>null</code>
     *                 wird interpretiert als "Personen, die in irgendeiner Kategorie sind",
     *                 <code>0</code> als "Personen, die in keiner Kategorie sind".
     * @param exporter zu benutzender {@link Exporter}
     * @param orgUnit  Mandanten-ID, wenn danach gefiltert werden soll
     */
    void exportCategory(Database database, Integer category, Exporter exporter, Integer orgUnit)
      throws BeanException, IOException {
        assert database != null;
        assert exporter != null;
        Bean samplePerson = database.createBean("Person");
        Bean samplePersonCategory = database.createBean("PersonCategorie");

        Select outer = database.getSelect(samplePerson);
        Select inner = new Select(false).
          from(database.getProperty(samplePersonCategory, "table")).
          selectAs(database.getProperty(samplePersonCategory, "person"), "person");

        WhereList outerWhere = new WhereList();
        outerWhere.addAnd(Expr.equal("deleted", PersonConstants.DELETED_FALSE));
        if (orgUnit != null) {
            outerWhere.addAnd(Expr.equal("tperson.fk_orgunit", orgUnit));
        }

        /*
         * cklein 2009-07-16: fixes issue 1815 - although option "Alle" yielded no return value
         * the jetty used for testing made it a valid integer object of value 0, which broke
         * existing code relying on the fact that the request parameter would be null.
         * Keine/None now equals -1.
         */
        if (category == null || category == 0) {
            outerWhere.addAnd(Expr.in(
              database.getProperty(samplePerson, "id"),
              new RawClause('(' + inner.toString() + ')')));
        } else if (category == -1) {
            outerWhere.addAnd(new RawClause("NOT " + Expr.in(
              database.getProperty(samplePerson, "id"),
              new RawClause('(' + inner.toString() + ')')).clauseToString()));
        } else {
            inner.where(Expr.equal(
              database.getProperty(samplePersonCategory, "categorie"),
              category));
            outerWhere.addAnd(Expr.in(
              database.getProperty(samplePerson, "id"),
              new RawClause('(' + inner.toString() + ')')));
        }

        exportSelect(database, outer.where(outerWhere).orderBy(Order.asc("tperson.pk")), exporter);
    }

    /**
     * Diese Methode exportiert alle Personen.
     *
     * @param database zu benutzende Datenverbindung
     * @param exporter zu benutzender {@link Exporter}
     * @param orgUnit  Mandanten-ID, wenn danach gefiltert werden soll
     */
    void exportAll(Database database, Exporter exporter, Integer orgUnit) throws BeanException, IOException {
        assert database != null;
        assert exporter != null;

        Select outer = database.getSelect("Person");
        WhereList outerWhere = new WhereList();
        outerWhere.addAnd(Expr.equal("deleted", PersonConstants.DELETED_FALSE));
        if (orgUnit != null) {
            outerWhere.addAnd(Expr.equal("tperson.fk_orgunit", orgUnit));
        }

        exportSelect(database, outer.where(outerWhere), exporter);
    }

    /**
     * Diese Methode exportiert alle Personen, die ein bestimmtes {@link Select} liefert.
     *
     * @param database zu benutzende Datenverbindung
     * @param select   DB-Select; dieses muß auf <code>database.getSelect("Person")</code>
     *                 beruhen, insbesondere zumindest mindestens die darin vorgegebenen Spalten haben.
     * @param exporter zu benutzender {@link Exporter}
     */
    void exportSelect(Database database, Select select, Exporter exporter) throws BeanException, IOException {
        assert database != null;
        assert select != null;
        assert exporter != null;

        try {
            ResultSet rs = ((Result) select.execute()).resultSet();
            ResultSetMetaData rsm = rs.getMetaData();
            Set<String> keys = new HashSet<>();
            for (int i = 1; i <= rsm.getColumnCount(); i++) {
                keys.add(rsm.getColumnName(i));
            }
            while (rs.next()) {
                Person person = new Person();
                for (String key : keys) {
                    person.setField(key, rs.getObject(key));
                }
                exporter.exportPerson(person);
            }
        } catch (SQLException e) {
            throw new BeanException(e.getMessage(), e);
        }
    }

    /**
     * Diese Methode erstellt einen Importvorgangeintrag.
     *
     * @param formatKey    Schlüssel des Formats des Importvorgangs
     * @param importSource Bezeichner der Importquelle
     * @param orgunit      Mandanten-ID, in der der Import erfolgt
     * @return neue {@link Import}-Instanz zu den angegebenen Daten
     * @throws TcContentProzessException FIXME
     */
    static Import createImport(TransactionContext context, String formatKey, String importSource, Integer orgunit)
      throws TcContentProzessException {
        try {
            Database database = context.getDatabase();
            Import importInstance = (Import) database.createBean("Import");
            importInstance.orgunit = orgunit;
            importInstance.importformat = formatKey;
            importInstance.importsource = importSource;
            database.saveBean(importInstance, context, true);
            return importInstance;
        } catch (BeanException e) {
            throw new TcContentProzessException("Fehler beim Erstellen eines Importvorgangs", e);
        } catch (IOException e) {
            throw new TcContentProzessException("Fehler beim Speichern eines Importvorgangs", e);
        }
    }

    /**
     * Diese Methode erstellt und Initialisiert einen {@link Importer}.
     *
     * @param format basierendes {@link ExchangeFormat}
     * @return ein passender {@link Importer}
     * @throws TcContentProzessException bei Fehlern beim Instanziieren des Exporters.
     */
    static Importer createImporter(ExchangeFormat format, TransactionContext context, InputStream is, final Charset cs)
      throws TcContentProzessException {
        assert format != null;
        assert context != null;
        try {
            Importer importer = (Importer) format.getImporterClass().newInstance();
            if (importer instanceof Exchanger) {
                Exchanger exchanger = (Exchanger) importer;
                exchanger.setExchangeFormat(format);
                exchanger.setInputStream(is);
                exchanger.setFileEncoding(cs);
            }
            if (importer instanceof DatabaseUtilizer) {
                DatabaseUtilizer dbUtilizer = (DatabaseUtilizer) importer;
                dbUtilizer.setDatabase(context.getDatabase());
            }
            return importer;
        } catch (Exception e) {
            throw new TcContentProzessException("Fehler beim Instanziieren des Importers", e);
        }
    }

    /**
     * Diese Methode erzeugt eine <code>Map</code>, in der Statistiken zu einem Import
     * kodiert sind.
     *
     * @param dsCount   Anzahl Datensätze insgesamt
     * @param dupCount  Anzahl Duplikate
     * @param id        Import-ID
     * @param saveCount save Count
     * @return Map mit Informationen zum Import, insbesondere der Anzahl gefundener
     * Datensätze unter "dsCount", der Anzahl Duplikate unter "dupCount", der Anzahl
     * importierter Datensätze unter "saveCount" und der Import-ID unter "id".
     */
    public static Map<String, Integer> createImportStats(int dsCount, int dupCount, int saveCount, Number id) {
        Map<String, Integer> rMap = new HashMap<>();
        rMap.put("dsCount", dsCount);
        rMap.put("dupCount", dupCount);
        rMap.put("saveCount", saveCount);
        rMap.put("id", (Integer) id);
        return rMap;
    }

    /**
     * Diese Methode erzeugt eine <code>Map</code>, in der Statistiken zu einem Import
     * kodiert sind. In dieser Variante werden auch die Datensätze gezählt, die wegen
     * Unkorrektheit ignoriert wurden.
     *
     * @param igCount   Anzahl ignorierter (unkorrekter) Datensätze
     * @param dsCount   Anzahl nicht ignorierter Datensätze insgesamt
     * @param dupCount  Anzahl Duplikate
     * @param id        Import-ID
     * @param saveCount saveCount
     * @return Map mit Informationen zum Import, insbesondere der Anzahl gefundener
     * Datensätze unter "dsCount", der Anzahl Duplikate unter "dupCount", der Anzahl
     * importierter Datensätze unter "saveCount" und der Import-ID unter "id".
     */
    public static Map createImportStats(int igCount, int dsCount, int dupCount, int saveCount, Number id) {
        final Map<String, Integer> rMap = createImportStats(dsCount, dupCount, saveCount, id);
        rMap.put("igCount", igCount);
        return rMap;
    }
}
