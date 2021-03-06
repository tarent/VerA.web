package de.tarent.commons.utils;

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

import de.tarent.octopus.server.OctopusContext;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/**
 * This is again an Implementation of a generic tool for collecting version information.
 * The goal of this implementation is to support different environments like 'installed client application' or 'war/ear archive',
 * as well as different version info ressource like. Simple properties file, Manifest file.
 *
 * @author Sebastian Mancke (s.mancke@tarent.de) tarent GmbH Bonn
 */
@Log4j2
public class VersionTool {
    public static final String PREFIX_PACKAGE = "package:";
    public static final String PREFIX_RESOURCE = "resource:";
    public static final String PREFIX_JAR = "jar:";

    public static final String VERSION_NAME = "version_name";
    public static final String VERSION_DESCRIPTION = "version_description";
    public static final String VERSION_COPYRIGHT = "version_copyright";
    public static final String VERSION_VERSION = "version_version";
    public static final String VERSION_BUILD_INFO = "version_buildInfo";
    public static final String VERSION_VENDOR = "version_vendor";
    public static final String BuildID = "Build-ID";

    List versionInfos = new ArrayList();

    VersionInfo mainVersionInfo;

    /**
     * Search the searchPath for Version infos.
     * Depending on the type the search entries may have a describing prefix. Valid entries in the searchPath are:
     * <ul>
     * <li>Directory (prefix: none). The directoy will be searched for a META-INF/MANIFEST.MF, as well as for jar-files, which
     * are included with there manifest.</li>
     * <li>Package Names (prefix: 'package:')</li>
     * <li>Properties resource file (prefix: 'resource:')</li>
     * <li>Jar files (prefix: 'jar:')</li>
     * <ul>
     * Depending on the type, the version info is collected.
     */
    public void search(String[] searchPath) {
        for (int i = 0; i < searchPath.length; i++) {
            VersionInfo info = null;
            if (searchPath[i].startsWith(PREFIX_PACKAGE)) {
                info = getInfoFromPackage(searchPath[i].substring(PREFIX_PACKAGE.length()));
            } else if (searchPath[i].startsWith(PREFIX_RESOURCE)) {
                info = getInfoFromResourceFile(searchPath[i].substring(PREFIX_RESOURCE.length()));
            } else if (searchPath[i].startsWith(PREFIX_JAR)) {
                info = getInfoFromJar(searchPath[i].substring(PREFIX_JAR.length()));
            } else {
                info = getInfoFromDirectory(searchPath[i]);
                searchInDirectory(searchPath[i]);
            }
            if (info != null) {
                versionInfos.add(info);
            }
        }

        if (mainVersionInfo == null && versionInfos.size() > 0) {
            mainVersionInfo = (VersionInfo) versionInfos.get(0);
        }

        Collections.sort(versionInfos, new Comparator() {
            public int compare(Object o1, Object o2) {
                VersionInfo v1 = (VersionInfo) o1;
                VersionInfo v2 = (VersionInfo) o2;

                if (v1.getVendor() == null && v2.getVendor() == null) {
                    return 0;
                }

                if (v1.getVendor() != null && v1.getVendor().startsWith("tarent")) {
                    return -1;
                }
                if (v2.getVendor() != null && v2.getVendor().startsWith("tarent")) {
                    return 1;
                }

                if (v1.getVendor() != null && v2.getVendor() == null) {
                    return -1;
                }

                if (v2.getVendor() != null && v1.getVendor() == null) {
                    return 1;
                }

                return v1.getVendor().compareTo(v2.getVendor());
            }

            public boolean equals(Object obj) {
                return false;
            }
        });
    }

    public static String[] INPUT_getVersions = { "prependingSearchPath", "appendingSearchPath" };
    public static boolean[] MANDATORY_getVersions = { false, false };
    public static String OUTPUT_getVersions = "versionInfos";

    /**
     * Octopus action, which searches the module path and the supplied path for version information.
     *
     * @param prependingSearchPath Path string with ':' as delimiter, prepended for the default path, may be null
     * @param appendingSearchPath  Path string with ':' as delimiter, appended for the default path, may be null
     * @param cntx                 the octopus context
     */
    public static VersionTool getVersions(OctopusContext cntx, String prependingSearchPath, String appendingSearchPath) {
        VersionTool vt = new VersionTool();
        if (prependingSearchPath != null) {
            vt.search(prependingSearchPath.split(":"));
        }
        vt.search(new String[] {
          cntx.moduleRootPath().getParentFile().getAbsolutePath(),
          new File(new File(cntx.moduleRootPath().getParentFile(), "WEB-INF"), "lib").getAbsolutePath(),
          new File(new File(cntx.moduleRootPath().getParentFile(), "OCTOPUS"), "lib").getAbsolutePath()
        });
        if (appendingSearchPath != null) {
            vt.search(appendingSearchPath.split(":"));
        }
        return vt;
    }

    public static void main(String args[]) {
        VersionTool vt = new VersionTool();
        vt.search(args);
        for (Iterator iter = vt.getVersionInfos().iterator(); iter.hasNext(); ) {
            VersionInfo vi = (VersionInfo) iter.next();
            System.out.println(vi);
        }
    }

    /**
     * Returns the main VersionInfo (first in the path);
     *
     * @return list of VersionInfo objects from the search path.
     */
    public VersionInfo getMainVersionInfo() {
        return mainVersionInfo;
    }

    /**
     * Returns the list off all VersionInfos
     *
     * @return list of VersionInfo objects from the search path.
     */
    public List getVersionInfos() {
        return versionInfos;
    }

    public void searchInDirectory(String dirname) {
        File f = new File(dirname);
        if (!f.exists() || !f.isDirectory()) {
            logger.warn("directory '" + dirname + "' for version info not found");
        }
        File[] files = f.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].getName().endsWith(".jar")) {
                    VersionInfo info = getInfoFromJar(files[i].getAbsolutePath());
                    if (info != null) {
                        versionInfos.add(info);
                    }
                }
            }
        }
    }

    public static VersionInfo getInfoFromClass(Class klass) {
        //      return getInfoFromPackage(klass.getPackage());
        return getInfoFromJar(klass.getProtectionDomain().getCodeSource().getLocation().getFile());
    }

    public static VersionInfo getInfoFromPackage(String packageName) {
        return getInfoFromPackage(Package.getPackage(packageName));
    }

    public static VersionInfo getInfoFromPackage(Package packageDescriptor) {
        String packageName = packageDescriptor.getName();

        if (packageDescriptor == null) {
            logger.warn("package '" + packageName + "' for version info not found");
            return null;
        }
        VersionInfo info = new VersionInfo();
        info.setResourceName("Package: " + packageName);
        info.setName(specImplConcat(packageDescriptor.getSpecificationTitle(), packageDescriptor.getImplementationTitle()));
        info.setVersion(
          specImplConcat(packageDescriptor.getSpecificationVersion(), packageDescriptor.getImplementationVersion()));
        info.setVendor(specImplConcat(packageDescriptor.getSpecificationVendor(), packageDescriptor.getImplementationVendor()));
        return info;
    }

    public static VersionInfo getInfoFromResourceFile(String resName) {
        InputStream is = VersionTool.class.getResourceAsStream(resName);
        if (is == null) {
            logger.warn("resource '" + resName + "' for version info not found in classpath");
            return null;
        }
        try {
            Properties props = new Properties();
            props.load(is);
            is.close();
            VersionInfo info = new VersionInfo();
            info.setResourceName("Resource file: " + resName);
            info.setName(props.getProperty(VERSION_NAME));
            info.setDescription(props.getProperty(VERSION_DESCRIPTION));
            info.setCopyright(props.getProperty(VERSION_COPYRIGHT));
            info.setVersion(props.getProperty(VERSION_VERSION));
            info.setBuildInfo(props.getProperty(VERSION_BUILD_INFO));
            info.setVendor(props.getProperty(VERSION_VENDOR));
            info.setBuildID(props.getProperty(BuildID));
            return info;
        } catch (IOException ioe) {
            logger.error("cant read '" + resName + "'", ioe);
            return null;
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException ioe) {
            }
        }
    }

    public static VersionInfo getInfoFromJar(String jarPathName) {
        File f = new File(jarPathName);
        if (!f.exists()) {
            logger.warn("file '" + jarPathName + "' for version info not found");
            //            return null;
            // return new VersionInfo instead of null
            return new VersionInfo();
        }
        JarFile jar = null;
        try {
            jar = new JarFile(f);
            VersionInfo info = getInfoFromManifest(jar.getManifest());
            info.setResourceName("jar: " + jarPathName);
            if (info.getName() == null || info.getName().trim().length() == 0) {
                info.setName(f.getName());
            }
            return info;
        } catch (IOException ioe) {
            logger.info("cant read '" + jarPathName + "'. probably not running a jar-build");
            //          return new VersionInfo instead of null
            return new VersionInfo();
        } finally {
            try {
                if (jar != null) {
                    jar.close();
                }
            } catch (IOException ioe) {
            }
        }
    }

    public static VersionInfo getInfoFromDirectory(String dirname) {
        File f = new File(new File(dirname, "META-INF"), "MANIFEST.MF");
        if (!f.exists()) {
            logger.info("file '" + f.getAbsolutePath() + "' for version info not found");
            return null;
        }
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(f);
            VersionInfo info = getInfoFromManifest(new Manifest(fis));
            info.setResourceName("Directory: " + dirname);
            return info;
        } catch (IOException ioe) {
            logger.error("cant read '" + f.getAbsolutePath() + "'", ioe);
            return null;
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ioe) {
            }
        }
    }

    public static VersionInfo getInfoFromManifest(Manifest manifest) {
        Attributes att = manifest.getMainAttributes();
        VersionInfo info = new VersionInfo();

        // use standard attributes as defaiult
        info.setName(specImplConcat(att.getValue("Specification-Title"), att.getValue("Implementation-Title")));
        info.setVersion(specImplConcat(att.getValue("Specification-Version"), att.getValue("Implementation-Version")));
        info.setVendor(specImplConcat(att.getValue("Specification-Vendor"), att.getValue("Implementation-Vendor")));
        info.setBuildInfo(att.getValue("Implementation-Build"));

        if (att.getValue(VERSION_NAME) != null) {
            info.setName(att.getValue(VERSION_NAME));
        }
        if (att.getValue(VERSION_DESCRIPTION) != null) {
            info.setDescription(att.getValue(VERSION_DESCRIPTION));
        }
        if (att.getValue(VERSION_COPYRIGHT) != null) {
            info.setCopyright(att.getValue(VERSION_COPYRIGHT));
        }
        if (att.getValue(VERSION_VERSION) != null) {
            info.setVersion(att.getValue(VERSION_VERSION));
        }
        if (att.getValue(VERSION_BUILD_INFO) != null) {
            info.setBuildInfo(att.getValue(VERSION_BUILD_INFO));
        }
        if (att.getValue(VERSION_VENDOR) != null) {
            info.setVendor(att.getValue(VERSION_VENDOR));
        }
        if (att.getValue(BuildID) != null) {
            info.setBuildID(att.getValue(BuildID));
        }
        return info;
    }

    static String specImplConcat(String specText, String implText) {
        if ((implText == null || implText.trim().length() == 0)
          && specText != null) {
            return "Specification: " + specText;
        }
        return implText;
    }
}
