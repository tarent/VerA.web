package de.tarent.aa.veraweb.utils;
/**
 * Diese Klasse enthält statische Hilfsmethoden für die Behandlung von
 * Export -Dateinamen und -Content-Typen. Diese werden hier zentral
 * "entstandardisiert" um sicherzustellen das diese vom Browser
 * als Download angeboten werden statt diese z.B. als Active-X-Controll
 * einzubinden.
 */
public class ExportHelper {
    /**
     * Erweitert die Standard-Dateiendung um den Zusatz <code>.export</code>.
     *
     * @param extension Original Dateiendung
     * @return angepasste Dateiendung
     */
    static public String getExtension(String extension) {
        return extension;
        //		return extension.endsWith(".export") ? extension : extension + ".export";
    }

    /**
     * Erweitert den Standard-Dateinamen um den Zusatz <code>.export</code>.
     *
     * @param filename Original Dateiname
     * @return angepassten Dateinamen
     */
    static public String getFilename(String filename) {
        return filename;
        //		return filename.endsWith(".export") ? filename : filename + ".export";
    }

    /**
     * Ersetzt den Standard-Content-Type durch den allgemeinen Standard
     * für beliebige Datenströme: <code>application/octet-stream</code>
     *
     * @param contentType Original Content-Type
     * @return angepassten Content-Type
     */
    static public String getContentType(String contentType) {
        return contentType;
        //		return "application/octet-stream";
    }

    /**
     * Anschrifttyp als P,G,S zurückgeben.
     *
     * Vorgabe aus PersonDoctype, überschreibbar in GuestDoctype
     * Muss auch in anderen Bereichen umgesetzt werden.
     * Z.B. beim "Neu Laden" in Worker und Template.
     *
     * @param addresstype adresstype
     * @return addresstype
     */
    public static String getAddresstype(Integer addresstype) {
        if (addresstype == null) {
            return null;
        } else if (addresstype.intValue() == 2) {
            return "P";
        } else if (addresstype.intValue() == 3) {
            return "W";
        } else { // addresstype.intValue() == 1
            return "G";
        }
    }

    /**
     * Zeichensatz als L,F1,F2 zurückgeben.
     *
     * Vorgabe aus PersonDoctype, überschreibbar in GuestDoctype
     * Muss auch in anderen Bereichen umgesetzt werden.
     * Z.B. beim "Neu Laden" in Worker und Template.
     *
     * @param locale locale
     * @return locale
     */
    public static String getLocale(Integer locale) {
        if (locale == null) {
            return null;
        } else if (locale.intValue() == 2) {
            return "ZS1";
        } else if (locale.intValue() == 3) {
            return "ZS2";
        } else { // locale.intValue() == 1
            return "L";
        }
    }

    /**
     * Diese Methode liefert eine String-Darstellung eines Gender-Werts
     *
     * @param gender gender
     * @return gender
     */
    public static String getGender(String gender) {
        return gender;
    }
}
