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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.io.FileOutputStream;

/**
 * Used to convert a mandatory file to a java class source file containig a static byte array containig
 * the content of the first file.
 * <p>
 * The file name of the desination is constructed as follows:<br>
 * destinationDir + packagePath (according to the package name) + className + ".java"
 *
 * @author Tim Steffens
 */
public class FileToClassWithByteArray {

    private final static char DIR_SEPARATOR = '/';
    private final static int BUFFER_SIZE = 1024;
    private URI sourceFileURI;
    private String destinationDir;
    private String packageName;
    private String className;
    private String fieldName;

    public FileToClassWithByteArray(URI sourceFileURI, String destinationDir, String packageName, String className,
      String fieldName) {
        this.sourceFileURI = sourceFileURI;
        setDestinationDir(destinationDir);
        setPackageName(packageName);
        setClassName(className);
        setFieldName(fieldName);
    }

    public static void main(String[] args) {
        if (args.length != 5) {
            System.out.println("Invalid arguent count, expected 5 arguments:");
            System.out.println(" sourceFile destinationDir packageName className fieldName");
        } else {
            try {
                System.out.println("Starting conversion ...");
                (new FileToClassWithByteArray(new URI("file://" + args[0]), args[1], args[2], args[3], args[4]))
                  .performConversion();
                System.out.println("Successfull");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }

    public void performConversion() throws IOException {
        FileInputStream source = new FileInputStream(new File(sourceFileURI));
        PrintWriter writer = new PrintWriter(new FileOutputStream(new File(getDestinationFileName())));
        printHeader(writer);
        printContent(source, writer);
        printFooter(writer);
        source.close();
        writer.flush();
        writer.close();
    }

    private String getDestinationFileName() {
        StringBuffer buffer = new StringBuffer();
        if (!getDestinationDir().equals("")) {
            buffer.append(getDestinationDir());
        }
        if (!getPackageName().equals("")) {
            buffer.append(getPackageName().replace('.', getDirSeparator()));
            buffer.append(getDirSeparator());
        }
        buffer.append(getClassName());
        buffer.append(".java");
        return buffer.toString();
    }

    private void printHeader(PrintWriter writer) {
        writer.println("package " + getPackageName() + ";");
        writer.println();
        writer.println("/**");
        writer.println(" * Contains a byte array with the content of file '" + getSourceFileURI() + "'");
        writer.println(" *");
        writer.println(" * @author Tim Steffens' FileToClassWithByteArray");
        writer.println(" */");
        writer.println("class " + getClassName() + " {");
        writer.println();
    }

    private void printContent(FileInputStream source, PrintWriter writer) throws IOException {
        writer.println("\tpublic final static byte[] " + getFieldName() + " = new byte[] {");
        byte[] buffer = new byte[getBufferSize()];
        boolean firstRun = true;
        for (int length = source.read(buffer); length != -1; length = source.read(buffer)) {
            if (!firstRun) {
                writer.println(",");
            } else {
                firstRun = false;
            }
            writer.print("\t\t");
            for (int i = 0; i < length; i++) {
                if (i != 0) {
                    writer.print(", ");
                }
                writer.print(buffer[i]);
            }
        }
        writer.println();
        writer.println("\t};");
    }

    private int getBufferSize() {
        return BUFFER_SIZE;
    }

    private void printFooter(PrintWriter writer) {
        writer.println("}");
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className.trim();
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName.trim();
    }

    public URI getSourceFileURI() {
        return sourceFileURI;
    }

    public void setSourceFileURI(URI sourceFileURI) {
        this.sourceFileURI = sourceFileURI;
    }

    public String getDestinationDir() {
        return destinationDir;
    }

    public void setDestinationDir(String destinationDir) {
        if (destinationDir != null) {
            this.destinationDir = destinationDir.trim().endsWith((new Character(getDirSeparator())).toString())
              ? destinationDir.trim()
              : destinationDir.trim() + getDirSeparator();
        } else {
            this.destinationDir = "";
        }
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        if (packageName != null) {
            this.packageName = packageName.trim();
        } else {
            this.packageName = "";
        }
    }

    public char getDirSeparator() {
        return DIR_SEPARATOR;
    }
}
