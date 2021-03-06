package de.tarent.commons.fileformats;

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

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.filechooser.FileFilter;

/**
 * @author niko
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FileFormatAdapter implements FileFormat {
    private String m_sKey;
    private String m_sShortName;
    private String m_sLongName;
    private String m_sDescription;
    private boolean m_bCanLoad = true;
    private boolean m_bCanSave = true;
    private boolean m_bTemplateFormat = false;
    private boolean m_bXMLFormat = false;

    private ImageIcon m_oIcon = null;
    private List m_oSuffixes;

    // -----------------------------------

    /**
     * Erzeugt ein OfficeFileFormat
     *
     * @param key         - der Schlüssel über den auf das FileFormat zugegriffen werden kann
     * @param shortname   - eine Kurzbezeichnung des Dateiformats
     * @param longname    - die vollständige Bezeichnung des Dateiformats
     * @param description - eine genaue Erklärung des Dateiformats
     * @param icon        - ein Icon für das Dateiformat
     * @param suffixes    - ein String oder ein String[] mit de(r/n) Dateiendung(en)
     */
    public FileFormatAdapter(String key, String shortname, String longname, String description, Object icon, Object suffixes) {
        this(key, shortname, longname, description, icon, suffixes, true, true);
    }

    /**
     * Erzeugt ein OfficeFileFormat
     *
     * @param key         - der Schlüssel über den auf das FileFormat zugegriffen werden kann
     * @param shortname   - eine Kurzbezeichnung des Dateiformats
     * @param longname    - die vollständige Bezeichnung des Dateiformats
     * @param description - eine genaue Erklärung des Dateiformats
     * @param icon        - ein Icon für das Dateiformat
     * @param suffixes    - ein String oder ein String[] mit de(r/n) Dateiendung(en)
     * @param canLoad     - gibt an ob die API dieses Format lesen kann
     * @param canSave     - gibt an ob die API dieses Format schreiben kann
     */
    public FileFormatAdapter(String key, String shortname, String longname, String description, Object icon, Object suffixes,
      boolean canLoad, boolean canSave) {
        m_sKey = key;
        m_sShortName = shortname;
        m_sLongName = longname;
        m_sDescription = description;
        m_bCanLoad = canLoad;
        m_bCanSave = canSave;

        //    if (icon instanceof ImageIcon)
        //    {
        //      m_oIcon = (ImageIcon)icon;
        //    }
        //    else if (icon instanceof String)
        //    {
        //      m_oIcon = loadIcon((String)icon);
        //    }
        //    else m_oIcon = null;

        m_oSuffixes = new ArrayList();
        if (suffixes != null) {
            if (suffixes instanceof String[]) {
                for (int i = 0; i < (((String[]) suffixes).length); i++) {
                    addSuffix(((String[]) suffixes)[i]);
                }
            } else if (suffixes instanceof String) {
                addSuffix((String) suffixes);
            }
        }
    }

    // -----------------------------------

    public String getKey() {
        return m_sKey;
    }

    public void setKey(String key) {
        m_sKey = key;
    }

    public String getDescription() {
        return m_sDescription;
    }

    public void setDescription(String description) {
        m_sDescription = description;
    }

    public ImageIcon getIcon() {
        return m_oIcon;
    }

    public void setIcon(ImageIcon icon) {
        m_oIcon = icon;
    }

    public String getLongName() {
        return m_sLongName;
    }

    public void setLongName(String longName) {
        m_sLongName = longName;
    }

    public String getShortName() {
        return m_sShortName;
    }

    public void setShortName(String shortName) {
        m_sShortName = shortName;
    }

    public String getSuffix(int index) {
        return (String) (m_oSuffixes.get(index));
    }

    public void addSuffix(String suffix) {
        m_oSuffixes.add(suffix);
    }

    public int getNumberOfSuffixes() {
        return m_oSuffixes.size();
    }

    public boolean endsWithSuffix(String filename) {
        Iterator it = m_oSuffixes.iterator();
        while (it.hasNext()) {
            String suffix = (String) (it.next());
            if (filename.endsWith(suffix)) {
                return true;
            }
        }
        return false;
    }

    private class OfficeFileFilter extends FileFilter {
        public boolean accept(File f) {
            for (int i = 0; i < (getNumberOfSuffixes()); i++) {
                String suffix = getSuffix(i).toUpperCase();
                if (f.getName().toUpperCase().endsWith(suffix)) {
                    return true;
                }
            }

            return false;
        }

        public String getDescription() {
            return FileFormatAdapter.this.getDescription();
        }
    }

    public FileFilter getFileFilter() {
        return new OfficeFileFilter();
    }

    public boolean canLoad() {
        return m_bCanLoad;
    }

    public boolean canSave() {
        return m_bCanSave;
    }

    public void setCanLoad(boolean canLoad) {
        m_bCanLoad = canLoad;
    }

    public void setCanSave(boolean canSave) {
        m_bCanSave = canSave;
    }

    public void setTemplateFormat(boolean pTemplate) {
        m_bTemplateFormat = pTemplate;
    }

    public boolean isTemplateFormat() {
        return m_bTemplateFormat;
    }

    public void setXMLFormat(boolean pXMLFormat) {
        m_bXMLFormat = pXMLFormat;
    }

    public boolean isXMLFormat() {
        return m_bXMLFormat;
    }
}
