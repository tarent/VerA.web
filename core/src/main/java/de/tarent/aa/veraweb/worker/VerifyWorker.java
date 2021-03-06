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

import de.tarent.aa.veraweb.beans.Config;
import de.tarent.aa.veraweb.utils.i18n.LanguageProvider;
import de.tarent.aa.veraweb.utils.i18n.LanguageProviderHelper;
import de.tarent.dblayer.sql.clause.Expr;
import de.tarent.octopus.beans.BeanException;
import de.tarent.octopus.beans.Database;
import de.tarent.octopus.beans.veraweb.DatabaseVeraWeb;
import de.tarent.octopus.server.OctopusContext;

import javax.xml.transform.TransformerFactory;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

/**
 * Dieser Worker schreibt Fehlerberichte oder Warnungen
 * als Liste in den Content.
 *
 * @author Christoph
 */
public class VerifyWorker {
    /**
     * Octopus-Eingabeparameter für die Aktion {@link #addError(OctopusContext, String)}
     */
    public static final String INPUT_addError[] = { "message" };

    /**
     * Octopus-Aktion die einen Error in den Content stellt.
     *
     * @param cntx    Octopus-Context
     * @param message Fehlermeldung
     */
    public void addError(OctopusContext cntx, String message) {
        if (message != null && message.length() != 0) {
            Collection errors = null;
            if (cntx.contentAsObject("errors") instanceof Collection) {
                errors = (Collection) cntx.contentAsObject("errors");
            }
            if (errors == null) {
                errors = new ArrayList();
            }
            errors.add(message);
            cntx.setContent("errors", errors);
        }
    }

    /**
     * Octopus-Eingabeparameter für die Aktion {@link #addWarning(OctopusContext, String)}
     */
    public static final String INPUT_addWarning[] = { "message" };

    /**
     * Octopus-Aktion die eine Warnung in den Content stellt.
     *
     * @param cntx    Octopus-Context
     * @param message Warnmeldung
     */
    public void addWarning(OctopusContext cntx, String message) {
        if (message != null && message.length() != 0) {
            Collection warnings = null;
            if (cntx.contentAsObject("warnings") instanceof Collection) {
                warnings = (Collection) cntx.contentAsObject("warnings");
            }
            if (warnings == null) {
                warnings = new ArrayList();
            }
            warnings.add(message);
            cntx.setContent("warnings", warnings);
        }
    }

    /**
     * Octopus-Eingabeparameter für die Action {@link #verifySchemaVersion(OctopusContext)}
     */
    public final static String INPUT_verifySchemaVersion[] = {};

    /**
     * Testet ob die Version des Datenbank-Schemas mit der Version in der
     * veraweb.properties übereinstimmt und erweitert ggf. die Liste mit
     * Fehlern und stellt diese in den Content.
     *
     * @param cntx OctopusContext
     * @throws BeanException BeanException
     * @throws IOException   IOException
     */
    public void verifySchemaVersion(OctopusContext cntx) throws BeanException, IOException {
        LanguageProviderHelper languageProviderHelper = new LanguageProviderHelper();
        LanguageProvider languageProvider = languageProviderHelper.enableTranslation(cntx);
        Database database = new DatabaseVeraWeb(cntx);

        Config config = (Config)
          database.getBean("Config",
            database.getSelect("Config").
              where(Expr.equal("cname", "SCHEMA_VERSION")));
        ResourceBundle properties = (ResourceBundle) cntx.contentAsObject("properties");

        if (config == null || config.value == null) {
            addError(cntx, languageProvider.getProperty("VERIFY_ERROR_DB_VERSION_NOT_DETERMINED"));
        } else if (!config.value.equals(properties.getString("schema-version"))) {
            addError(cntx, languageProvider.getProperty("VERIFY_ERROR_DB_VERSION_INCONSISTENCE_ONE") + config.value +
              languageProvider.getProperty("VERIFY_ERROR_DB_VERSION_INCONSISTENCE_TWO") +
              properties.getString("schema-version") +
              languageProvider.getProperty("VERIFY_ERROR_DB_VERSION_INCONSISTENCE_THREE"));
        }
    }

    /**
     * Octopus-Eingabeparameter für die Action {@link #getDatabaseCharset(OctopusContext)}
     */
    public final static String INPUT_getDatabaseCharset[] = {};

    /**
     * Testet ob der Zeichensatz der Datenbank richtig konfiguriert ist.
     *
     * @param cntx OctopusContext
     */
    public void getDatabaseCharset(OctopusContext cntx) {
        DatabaseVeraWeb database = new DatabaseVeraWeb(cntx);

        String LC_CTYPE = getResult(database, "SHOW LC_CTYPE");
        String LC_COLLATE = getResult(database, "SHOW LC_COLLATE");
        cntx.setContent("LC_CTYPE", LC_CTYPE);
        cntx.setContent("LC_COLLATE", LC_COLLATE);
        database.close();
    }

    /**
     * Octopus-Eingabeparameter für die Action {@link #verifyDatabaseCharset(OctopusContext)}
     */
    public final static String INPUT_verifyDatabaseCharset[] = {};// "LC_CTYPE", "LC_COLLATE" };

    /**
     * Testet ob der Zeichensatz der Datenbank richtig konfiguriert ist.
     *
     * @param cntx OctopusContext
     */
    public void verifyDatabaseCharset(OctopusContext cntx) {
        LanguageProviderHelper languageProviderHelper = new LanguageProviderHelper();
        LanguageProvider languageProvider = languageProviderHelper.enableTranslation(cntx);
        getDatabaseCharset(cntx);
        String LC_CTYPE = cntx.contentAsString("LC_CTYPE");
        String LC_COLLATE = cntx.contentAsString("LC_COLLATE");
        cntx.setContent("LC_CTYPE", LC_CTYPE);
        cntx.setContent("LC_COLLATE", LC_COLLATE);

        if (LC_CTYPE == null || LC_CTYPE.toUpperCase().indexOf("UTF-8") == -1) {
            addError(cntx, languageProvider.getProperty("VERIFY_ERROR_DB_LC_CTYPE_NOT_WANTED") + LC_CTYPE + ").");
        }
        if (LC_COLLATE == null || LC_COLLATE.toUpperCase().indexOf("UTF-8") == -1) {
            addError(cntx, languageProvider.getProperty("VERIFY_ERROR_DB_LC_COLLATE_NOT_WANTED") + LC_COLLATE + ").");
        }
    }

    protected String getResult(Database database, String statement) {
        ResultSet rs = null;
        try {
            rs = database.result(statement);
            return rs.next() ? rs.getString(1) : null;
        } catch (BeanException e) {
            return null;
        } catch (SQLException e) {
            return null;
        } finally {
            if (rs != null) {
                try {
                    database.close(rs);
                } catch (BeanException e) {
                }
            }
        }
    }

    /**
     * Octopus-Eingabeparameter für die Aktion {@link #verifyXMLTransformer(OctopusContext)}
     */
    public final static String INPUT_verifyXMLTransformer[] = {};

    /**
     * Testet ob der XML Transformer (für OpenOffice) korrektes XML erzeugt.
     *
     * @param cntx OctopusContext
     */
    public void verifyXMLTransformer(OctopusContext cntx) {
        try {
            TransformerFactory.newInstance().newTransformer();
        } catch (Throwable e) {
            LanguageProviderHelper languageProviderHelper = new LanguageProviderHelper();
            LanguageProvider languageProvider = languageProviderHelper.enableTranslation(cntx);
            addError(cntx, languageProvider.getProperty("VERIFY_ERROR_XML") + e.toString() + ")");
        }
    }
}
