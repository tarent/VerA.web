package de.tarent.aa.veraweb.beans;

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

/**
 * @author cklein
 */
public class Duration extends AbstractBean {
    public static String DEFAULT_FORMAT = "%Y%M%D";

    public Integer years;
    public Integer months;
    public Integer days;

    /**
     *
     */
    public Duration() {
        super();
        this.years = new Integer(0);
        this.months = new Integer(0);
        this.days = new Integer(0);
    }

    /**
     * @param value a string value representing a serialized period in the form P[y]Y[m]M[d]D, e.g. P5Y2M1D or P2M3D
     * @return FIXME
     */
    public static Duration fromString(String value) {
        String regex = "^P([0-9]+Y)?([0-9]+M)?([0-9]+D)?$";
        Duration result = new Duration();
        if (value == null) {
            value = "P0";
        }
        if (value.matches(regex)) {
            int amount = 0;
            for (int i = 1; i < value.length(); i++) {
                if (Character.isDigit(value.charAt(i))) {
                    amount *= 10;
                    amount += value.charAt(i) - 48;
                } else {
                    Integer nval = new Integer(amount);
                    amount = 0;
                    switch (value.charAt(i)) {
                    case 'Y': {
                        result.years = nval;
                        amount = 0;
                        break;
                    }
                    case 'M': {
                        result.months = nval;
                        break;
                    }
                    case 'D': {
                        result.days = nval;
                        break;
                    }
                    }
                }
            }
        }
        return result;
    }

    /**
     * The format string fmt is defined as follows (all output strings are in german, no localization is supported!):
     *
     * %d - the number of days plus the word Tag(e) in its either singular or plural form
     * %m - the number of months plus the word Monat(e) in its either singular or plural form
     * %y - the number of years plus the word Jahr(e) in its either singular or plural form
     * %D - same as %d but the resulting string will not contain the number of days if these are equal to 0
     * %M - same as %m but the resulting string will not contain the number of months if these are equal to 0
     * %Y - same as %y but the resulting string will not contain the number of years if these are equal to 0
     * %% - the percentage character
     *
     * @param fmt The format
     * @return String
     */
    public String toFormattedString(String fmt) {
        StringBuffer temp = new StringBuffer();
        char c = 0;
        String s = "";
        int val = 0;
        for (int i = 0; i < fmt.length(); i++) {
            c = fmt.charAt(i);
            switch (c) {
            case '%': {
                i++;
                c = fmt.charAt(i);
                switch (c) {
                case '%': {
                    temp.append('%');
                    break;
                }
                case 'y':
                case 'Y':
                case 'm':
                case 'M':
                case 'd':
                case 'D': {
                    if (c == 'y' || c == 'Y') {
                        val = this.years.intValue();
                        s = " Jahr";
                    } else if (c == 'm' || c == 'M') {
                        val = this.months.intValue();
                        s = " Monat";
                    } else {
                        val = this.days.intValue();
                        s = " Tag";
                    }
                    if (val != 0 || c == 'y' || c == 'm' || c == 'd') {
                        if (temp.length() > 1 && temp.charAt(temp.length() - 1) != ' ') {
                            temp.append(' ');
                        }
                        temp.append(val);
                        temp.append(s);
                        if (val > 1 || val == 0) {
                            temp.append("e");
                        }
                    }
                    break;
                }
                default: {
                    // unsupported
                    temp.append('%');
                    temp.append(fmt.charAt(i));
                    break;
                }
                }
                break;
            }
            default: {
                temp.append(fmt.charAt(i));
                break;
            }
            }
        }
        return temp.toString();
    }

    public String toFormattedString() {
        return this.toFormattedString(Duration.DEFAULT_FORMAT);
    }

    @Override
    public String toString() {
        StringBuffer temp = new StringBuffer();
        temp.append('P');
        if (this.years.intValue() != 0) {
            temp.append(this.years.intValue());
            temp.append('Y');
        }
        if (this.months.intValue() != 0) {
            temp.append(this.months.intValue());
            temp.append('M');
        }
        if (this.days.intValue() != 0) {
            temp.append(this.days.intValue());
            temp.append('D');
        }
        // is this a zero length duration?
        if (temp.length() == 1) {
            temp.append('0');
        }
        return temp.toString();
    }
}
