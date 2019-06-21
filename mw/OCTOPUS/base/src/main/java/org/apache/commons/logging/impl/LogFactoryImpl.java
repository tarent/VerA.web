package org.apache.commons.logging.impl;

/*-
 * VerA.web-Middleware, newly MIT licenced, is comprised of:
 * tarent-commons, a set of common components and solutions
 * tarent-contact, platform-independent webservice-based contact management
 * tarent-database, jdbc database library
 * tarent-doctor, Document Generation Platform
 * tarent-octopus, Webservice Data Integrator and Application Server
 *  © 2018 Атанас Александров (sirakov@gmail.com)
 *  © 2005, 2006, 2007 asteban (s.mancke@tarent.de)
 *  © 2018 Dominik George (d.george@tarent.de)
 *  © 2007 David Goemans (d.goemans@tarent.de)
 *  © 2018 Christian Gorski (c.gorski@tarent.de)
 *  © 2006, 2007, 2010 Hendrik Helwich (h.helwich@tarent.de)
 *  © 2018 Benedict Hoeger (b.hoeger@tarent.de)
 *  © 2005, 2006, 2007 Christoph Jerolimov (c.jerolimov@tarent.de)
 *  © 2018 Timo Kanera (t.kanera@tarent.de)
 *  © 2006 Philipp Kirchner (p.kirchner@tarent.de)
 *  © 2010 Carsten Klein (c.klein@tarent.de)
 *  © 2006 Michael Kleinhenz (m.kleinhenz@tarent.de)
 *  © 2006 Michael Klink (m.klink@tarent.de)
 *  © 2007 Fabian Köster (f.koester@tarent.de)
 *  © 2006 Martin Ley (m.ley@tarent.de)
 *  © 2007 Alex Maier (a.maier@tarent.de)
 *  © 2007, 2015, 2017, 2018 mirabilos (t.glaser@tarent.de)
 *  © 2006, 2007 Jens Neumaier (j.neumaier@tarent.de)
 *  © 2006 Nils Neumaier (n.neumaier@tarent.de)
 *  © 2007, 2008 Martin Pelzer (m.pelzer@tarent.de)
 *  © 2008, 2009 Christian Preilowski (c.thiel@tarent.de)
 *  © 2006, 2008, 2009 Thomas Schmitz (t.schmitz@tarent.de)
 *  © 2007 Robert Schuster (r.schuster@tarent.de)
 * and older code, Copyright © 2001–2007 ⮡ tarent GmbH and contributors.
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

import lombok.extern.log4j.Log4j2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogConfigurationException;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;

/**
 * Subset of commons-logging 1.2 LogFactory implementation
 * FQCN hardcoded via constant copying, into its users
 *
 * Use @Log4j2 for all code; this is for dependencies *only*!
 *
 * @author mirabilos (t.glaser@tarent.de)
 */
@Log4j2
public class LogFactoryImpl extends LogFactory {
    /**
     * Internal constructor, only accessed by the superclass…
     * except the Axis lookup requires this as public even if
     * it does not even use it.
     */
    public LogFactoryImpl() {
        logger.debug("instantiating");
    }

    /* do not even THINK of adding yourselves here or using anything but @Log4j2 */
    @SuppressWarnings("NonAsciiCharacters")
    private static String[] whitelistedPræficēs = {
      "org.apache.axis.",
      "org.springframework."
    };

    private static boolean doWarn(final Object arg, final String name) {
        /* always warn for nil class */
        if (arg == null) {
            return true;
        }
        /* do not warn for whitelisted classes */
        for (String prefix : whitelistedPræficēs) {
            if (name.startsWith(prefix)) {
                return false;
            }
        }
        /* otherwise warn */
        return true;
    }

    @Override
    public Log getInstance(final Class clazz) throws LogConfigurationException {
        final String name = clazz == null ? "(nil)" : clazz.getName();
        if (doWarn(clazz, name)) {
            logger.warn("Class {} used with commons-logging 1.2 LogFactory interface", name,
              new LogConfigurationException("dummy exception for generating a stack trace"));
        }
        try {
            return new LogBridge(LogManager.getLogger(clazz));
        } catch (Exception e) {
            throw new LogConfigurationException("could not get logger for class " + name, e);
        }
    }

    @Override
    public Log getInstance(final String clazzName) throws LogConfigurationException {
        final String name = clazzName == null ? "(nil)" : clazzName;
        if (doWarn(clazzName, name)) {
            logger.warn("Class name {} used for commons-logging 1.2 LogFactory interface", name,
              new LogConfigurationException("dummy exception for generating a stack trace"));
        }
        try {
            return new LogBridge(LogManager.getLogger(clazzName));
        } catch (Exception e) {
            throw new LogConfigurationException("could not get logger for class name " + name, e);
        }
    }

    /**
     * In this implementation, does nothing.
     * We’re not keeping any references to the {@link LogBridge} instances around.
     */
    @Override
    public void release() {
    }

    @Override
    public Object getAttribute(final String name) {
        return null;
    }

    @Override
    public String[] getAttributeNames() {
        return new String[0];
    }

    @Override
    public void removeAttribute(final String name) {
    }

    @Override
    public void setAttribute(final String name, final Object value) {
    }
}