package de.tarent.octopus.content;

/*
 * tarent-octopus, Webservice Data Integrator and Application Server
 * Copyright © 2002–2017 tarent solutions GmbH and its contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

import org.apache.commons.logging.Log;

import de.tarent.octopus.config.ContentWorkerDeclaration;
import de.tarent.octopus.logging.LogFactory;
import de.tarent.octopus.resource.Resources;
import de.tarent.octopus.server.SpecialWorkerFactory;
import de.tarent.octopus.server.WorkerCreationException;

/** 
 * Instantiiert eine Klasse, die das TcContentWorker-Interface direkt unterstützt.
 * 
 * 
 * @see TcContentWorker
 * @author <a href="mailto:mancke@mancke-software.de">Sebastian Mancke</a>, <b>tarent GmbH</b>
 */
public class DirectWorkerFactory implements SpecialWorkerFactory {

    private static Log logger = LogFactory.getLog(DirectWorkerFactory.class);
    private static Class[] emptyClassArray = new Class[]{};
    private static Object[] emptyObjectArray = new Object[]{};

    /**
     * Läd die als ImplementationSource angegebene Klasse und gibt sie gecastet TcContentWorker zurück.
     * 
     * @param workerDeclaration Beschreibung zur Instanziierung des Workers.
     */
    public TcContentWorker createInstance(ClassLoader classLoader, ContentWorkerDeclaration workerDeclaration) 
        throws WorkerCreationException {
        try {
            logger.debug(Resources.getInstance().get("WORKERFACTORY_LOADING_WORKER", getClass().getName(), workerDeclaration.getWorkerName(), workerDeclaration.getImplementationSource()));
            Class workerClass = classLoader.loadClass(workerDeclaration.getImplementationSource());
            return (TcContentWorker)workerClass.getConstructor(emptyClassArray).newInstance(emptyObjectArray);
        } catch (ClassCastException castException) {
            throw new WorkerCreationException(Resources.getInstance().get("WORKERFACTORY_CAST_EXC_LOADING_WORKER", getClass().getName(), workerDeclaration.getWorkerName(), workerDeclaration.getImplementationSource()));
        } catch (Exception reflectionException) {
            throw new WorkerCreationException(Resources.getInstance().get("WORKERFACTORY_EXC_LOADING_WORKER", getClass().getName(), workerDeclaration.getWorkerName(), workerDeclaration.getImplementationSource()), reflectionException);
        }
    }
}