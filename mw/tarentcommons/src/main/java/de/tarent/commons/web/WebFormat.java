package de.tarent.commons.web;

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

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.MissingResourceException;

import de.tarent.commons.messages.MessageHelper;
import de.tarent.commons.utils.VariableDateFormat;

/**
 * Diese Klasse holt (potentiell lokalisiert) ausgelagerte Mitteilungen; diese
 * können so roh oder mittels {@link MessageFormat} mit Parametern gefüllt
 * abgefragt werden.
 *
 * @author Christoph Jerolimov, tarent GmbH;
 * Tim Steffens, tarent GmbH
 */
public class WebFormat {
    /**
     * Wohlformatiertes Datum.
     */
    public static String DATE;
    /**
     * Wohlformatiertes Datum inkl. Uhrzeit.
     */
    public static String DATE_TIME;
    /**
     * Wohlformatierte Uhrzeit
     */
    public static String TIME;
    /**
     * Tag im Monat, 1 bis n.
     */
    public static String DAY;
    /**
     * Monat im Jahr, 1 bis 12.
     */
    public static String MONTH;
    /**
     * Jahr
     */
    public static String YEAR;

    /**
     * Encodet einen Text zur direkten Darstellung im HTML-Inhalt.
     *
     * @param input Original String
     * @return Encodeter String
     */
    public Object plain(Object input) {
        if (input != null && input instanceof String) {
            return formatTextToHtmlString((String) input).replaceAll("\n", "<br>");
        }
        return input;
    }

    /**
     * Encodet einen Text zur direkten Darstellung im HTML-Input-Feldern.
     *
     * @param input Original String
     * @return Encodeter String
     */
    public Object input(Object input) {
        if (input != null && input instanceof String) {
            return formatTextToHtmlString((String) input);
        }
        return input;
    }

    /**
     * Encodet einen Text zur direkten Darstellung in HTML-Textarea-Feldern.
     *
     * @param input Original String
     * @return Encodeter String
     */
    public Object textarea(Object input) {
        if (input != null && input instanceof String) {
            return formatTextToHtmlString((String) input);
        }
        return input;
    }

    /**
     * Converts a date of the form yyyy-MM-dd hh:mm:ss.S to the form dd.MM.yyy and returns it.
     */
    public String brokerDateToHumanDate(String brokerDate) throws ParseException {
        VariableDateFormat vdf = new VariableDateFormat();
        return date(vdf.analyzeString(brokerDate));
    }

    /**
     * Encodet ein Datum zur direkten Darstellung in HTML-Textarea-Feldern.
     *
     * @param input Darzustellendes Datum als {@link Date}- oder {@link Long}-Instanz.
     *              Bei anderen Klassen wird die Stringdarstellung genommen und als {@link Long}
     *              interpretiert.
     * @return Encodetes Datum oder <code>null</code>
     */
    public String date(Object input) {
        return formatCalendar(DATE, getCalendar(input));
    }

    /**
     * Encodet ein Datum zur direkten Darstellung in HTML-Textarea-Feldern.
     *
     * @param input Darzustellendes Datum als {@link Date}- oder {@link Long}-Instanz.
     *              Bei anderen Klassen wird die Stringdarstellung genommen und als {@link Long}
     *              interpretiert.
     * @return Encodetes Datum oder <code>null</code>
     */
    public Object dateTime(Object input) {
        return formatCalendar(DATE_TIME, getCalendar(input));
    }

    /**
     * Encodet ein Datum zur direkten Darstellung in HTML-Textarea-Feldern.
     *
     * @param input Darzustellendes Datum als {@link Date}- oder {@link Long}-Instanz.
     *              Bei anderen Klassen wird die Stringdarstellung genommen und als {@link Long}
     *              interpretiert.
     * @return Encodetes Datum oder <code>null</code>
     */
    public Object time(Object input) {
        return formatCalendar(TIME, getCalendar(input));
    }

    /**
     * Encodet ein Datum zur direkten Darstellung in HTML-Textarea-Feldern.
     *
     * @param input Darzustellendes Datum als {@link Date}- oder {@link Long}-Instanz.
     *              Bei anderen Klassen wird die Stringdarstellung genommen und als {@link Long}
     *              interpretiert.
     * @return Encodetes Datum oder <code>null</code>
     */
    public Object day(Object input) {
        return formatCalendar(DAY, getCalendar(input));
    }

    /**
     * Encodet ein Datum zur direkten Darstellung in HTML-Textarea-Feldern.
     *
     * @param input Darzustellendes Datum als {@link Date}- oder {@link Long}-Instanz.
     *              Bei anderen Klassen wird die Stringdarstellung genommen und als {@link Long}
     *              interpretiert.
     * @return Encodetes Datum oder <code>null</code>
     */
    public Object month(Object input) {
        return formatCalendar(MONTH, getCalendar(input));
    }

    /**
     * Encodet ein Datum zur direkten Darstellung in HTML-Textarea-Feldern.
     *
     * @param input Darzustellendes Datum als {@link Date}- oder {@link Long}-Instanz.
     *              Bei anderen Klassen wird die Stringdarstellung genommen und als {@link Long}
     *              interpretiert.
     * @return Encodetes Datum oder <code>null</code>
     */
    public Object year(Object input) {
        return formatCalendar(YEAR, getCalendar(input));
    }

    protected Calendar getCalendar(Object input) {
        try {
            Calendar calendar = Calendar.getInstance();
            if (input instanceof Date) {
                calendar.setTime((Date) input);
            } else if (input instanceof Long) {
                calendar.setTimeInMillis(((Long) input).longValue());
            } else {
                calendar.setTimeInMillis(new Long(input.toString()).longValue());
            }
            return calendar;
        } catch (MissingResourceException e) {
            return null;
        } catch (NumberFormatException e) {
            return null;
        } catch (NullPointerException e) {
            return null;
        }
    }

    protected String formatCalendar(String format, Calendar calendar) {
        if (calendar == null) {
            return null;
        }
        return new SimpleDateFormat(format).format(calendar.getTime());
    }

    /**
     * Encodet einen String in einen HTML-String.
     *
     * @param string Original String
     * @return Encodeted HTML
     */
    public static String formatTextToHtmlString(String string) {
        if (string == null) {
            return null;
        }
        return string
          .replaceAll("\u0026", "&amp;")
          .replaceAll("\u005c\u0022", "&quot;")
          //			.replaceAll("\u0027", "&apos;")
          .replaceAll("\u003c", "&lt;")
          .replaceAll("\u003e", "&gt;");
        //			.replaceAll("\u00e4", "&auml;")
        //			.replaceAll("\u00c4", "&Auml;")
        //			.replaceAll("\u00fc", "&uuml;")
        //			.replaceAll("\u00dc", "&Uuml;")
        //			.replaceAll("\u00f6", "&ouml;")
        //			.replaceAll("\u00d6", "&Ouml;")
        //			.replaceAll("\u00df", "&szlig;");
    }

    /**
     * Decodet einen String in einen Plaintext-String.
     *
     * @param string HTML String
     * @return Plaintext
     */
    public static String formatHtmlToTextString(String string) {
        if (string == null) {
            return null;
        }
        return string
          .replaceAll("&quot;", "\u005c\u0022")
          //			.replaceAll("&apos;", "\u0027")
          .replaceAll("&lt;", "\u003c")
          .replaceAll("&gt;", "\u003e")
          //			.replaceAll("&auml;", "\u00e4")
          //			.replaceAll("&Auml;", "\u00c4")
          //			.replaceAll("&uuml;", "\u00fc")
          //			.replaceAll("&Uuml;", "\u00dc")
          //			.replaceAll("&ouml;", "\u00f6")
          //			.replaceAll("&Ouml;", "\u00d6")
          //			.replaceAll("&szlig;", "\u00df")
          .replaceAll("&amp;", "\u0026")
          .replaceAll("<br>", "\n");
    }

    static {
        MessageHelper.init(WebFormat.class.getName(), "de.tarent.commons.messages.WebFormat");
    }
}
