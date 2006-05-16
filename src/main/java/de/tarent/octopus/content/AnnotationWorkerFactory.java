/* $Id: AnnotationWorkerFactory.java,v 1.3 2006/05/16 12:56:03 christoph Exp $
 * 
 * tarent-octopus, Webservice Data Integrator and Applicationserver
 * Copyright (C) 2002 tarent GmbH
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 * 
 * tarent GmbH., hereby disclaims all copyright
 * interest in the program 'tarent-octopus'
 * (which makes passes at compilers) written
 * by Sebastian Mancke
 * signature of Elmar Geese, 1 June 2002
 * Elmar Geese, CEO tarent GmbH
 */

package de.tarent.octopus.content;

import de.tarent.octopus.config.ContentWorkerDeclaration;
import de.tarent.octopus.resource.Resources;
import java.util.logging.Logger;
import de.tarent.octopus.server.WorkerCreationException;
import de.tarent.octopus.server.SpecialWorkerFactory;
import de.tarent.octopus.config.TcModuleConfig;

import de.tarent.octopus.content.annotation.AnnotationWorkerWrapper;


/** 
 * Instantiiert AnnotationWorker nach der ReflectedWorkerWrapper Konvention.
 * 
 * 
 * @see TcContentWorker
 * @author <a href="mailto:mancke@mancke-software.de">Sebastian Mancke</a>, <b>tarent GmbH</b>
 */
public class AnnotationWorkerFactory implements SpecialWorkerFactory {

    private static Logger logger = Logger.getLogger(ReflectedWorkerFactory.class.getName());

    /**
     * L�d die als ImplementationSource angegebene Klasse und Kapselt sie mit einem AnnotationWorkerWrapper.
     * 
     * @param workerDeclaration Beschreibung zur Instanziierung des Workers.
     */
    public TcContentWorker createInstance(TcModuleConfig config, ContentWorkerDeclaration workerDeclaration)
        throws WorkerCreationException {
        try {
            logger.fine(Resources.getInstance().get("WORKERFACTORY_LOADING_WORKER", getClass().getName(), workerDeclaration.getWorkerName(), workerDeclaration.getImplementationSource()));
            Class workerClass = config.getClassLoader().loadClass(workerDeclaration.getImplementationSource());
            return new AnnotationWorkerWrapper(workerClass.getConstructor().newInstance());
        } catch (Exception reflectionException) {
            throw new WorkerCreationException(Resources.getInstance().get("WORKERFACTORY_EXC_LOADING_WORKER", getClass().getName(), workerDeclaration.getWorkerName(), workerDeclaration.getImplementationSource()), reflectionException);
        }
    }
}