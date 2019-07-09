package de.tarent.aa.veraweb.worker;
import de.tarent.dblayer.engine.DB;
import de.tarent.octopus.server.OctopusContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

/**
 * System Worker initalisiert den Datenbank-Connection-Pool
 * und das Logging.
 *
 * @author Christoph Jerolimov c.jerolimov@tarent.de
 * @version 1.1
 */
public class SystemWorker {
    //
    // Octopus-Aktionen
    //
    /**
     * Eingabe-Parameter der Octopus-Aktion {@link #openPool(OctopusContext)}
     */
    public final static String INPUT_openPool[] = {};

    /**
     * Diese Octopus-Aktion initialisiert den dblayer-DB-Pool dieses Moduls.
     *
     * @param cntx Octopus-Kontext
     */
    public void openPool(OctopusContext cntx) throws IOException {
        File file = new File(
          cntx.moduleConfig().getRealPath(),
          cntx.moduleConfig().getParam("dblayer"));

        Properties properties = new Properties();
        properties.load(new FileInputStream(file));

        DB.openPool(cntx.getModuleName(), new HashMap(properties));
    }

    /**
     * Eingabe-Parameter der Octopus-Aktion {@link #closePool(OctopusContext)}
     */
    public final static String INPUT_closePool[] = {};

    /**
     * Diese Octopus-Aktion schließt den dblayer-DB-Pool dieses Moduls.
     *
     * @param cntx Octopus-Kontext
     */
    public void closePool(OctopusContext cntx) {
        DB.closePool(cntx.getModuleName());
    }

    /**
     * Eingabe-Parameter der Octopus-Aktion
     */
    public final static String INPUT_initLogging[] = {};
    /**
     * Diese Octopus-Aktion initialisiert das Log4J-Logging dieses Moduls.
     *
     * @param cntx Octopus-Kontext
     */
    //	public void initLogging(OctopusContext cntx) {
    //		DOMConfigurator.configure(cntx.moduleConfig().getOtherNode("log4j:configuration"));
    //
    //		String path = cntx.moduleConfig().getRealPath() + "/log/";
    //		new File(path).mkdirs();
    //		changeLogDir(Category.getInstance("SQL"), path);
    //		changeLogDir(Category.getInstance("WORKER"), path);
    //		changeLogDir(Category.getInstance("ERROR"), path);
    //		changeLogDir(Category.getInstance("de.tarent.dblayer"), path);
    //		changeLogDir(Category.getInstance("de.tarent.aa.veraweb"), path);
    //	}

    //
    // Hilfsmethoden
    //
    /**
     * Diese Methode ändert den Pfad der Datei der übergebenen Log4J-Kategorie
     * ab, indem der übergebene Pfad vorangestellt wird, wenn der bisherige Pfad
     * keinen Verzeichnisanteil hat.
     *
     * @param category abzuändernde Log4J-Kategorie
     * @param path zu benutzender Dateipfad
     */
    //	protected void changeLogDir(Category category, String path) {
    //		if (category == null) return;
    //		for (Enumeration e = category.getAllAppenders(); e.hasMoreElements(); ) {
    //			Object o = e.nextElement();
    //			if (o != null && o instanceof FileAppender) {
    //				FileAppender a = (FileAppender)o;
    //				if (a.getFile() != null && a.getFile().indexOf('/') == -1 && a.getFile().indexOf('\\') == -1) {
    //					a.setFile(path + a.getFile());
    //					a.activateOptions();
    //				}
    //			}
    //		}
    //	}
}
