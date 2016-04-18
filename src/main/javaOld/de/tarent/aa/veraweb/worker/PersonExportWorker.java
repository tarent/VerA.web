/**
 * veraweb, platform independent webservice-based event management
 * (Veranstaltungsmanagment VerA.web), is
 * Copyright © 2004–2008 tarent GmbH
 * Copyright © 2013–2016 tarent solutions GmbH
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, see: http://www.gnu.org/licenses/
 */
package de.tarent.aa.veraweb.worker;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.transform.TransformerFactoryConfigurationError;

import de.tarent.aa.veraweb.utils.i18n.LanguageProvider;
import de.tarent.aa.veraweb.utils.i18n.LanguageProviderHelper;
import org.apache.log4j.Logger;

import de.tarent.aa.veraweb.beans.Color;
import de.tarent.aa.veraweb.beans.Grants;
import de.tarent.aa.veraweb.beans.facade.PersonConstants;
import de.tarent.aa.veraweb.utils.ExportHelper;
import de.tarent.aa.veraweb.utils.OctopusHelper;
import de.tarent.commons.spreadsheet.export.SpreadSheet;
import de.tarent.commons.spreadsheet.export.SpreadSheetFactory;
import de.tarent.dblayer.helper.ResultList;
import de.tarent.dblayer.sql.clause.Expr;
import de.tarent.dblayer.sql.clause.RawClause;
import de.tarent.dblayer.sql.clause.Where;
import de.tarent.dblayer.sql.statement.Select;
import de.tarent.octopus.PersonalConfigAA;
import de.tarent.octopus.beans.BeanException;
import de.tarent.octopus.beans.Database;
import de.tarent.octopus.response.TcBinaryResponseEngine;
import de.tarent.octopus.server.OctopusContext;

/**
 * Dieser Octopus-Worker dient dem Export von Personendaten.
 */
public class PersonExportWorker extends PersonListWorker {
    /** Logger dieser Klasse */
	private final Logger logger = Logger.getLogger(getClass());

	/**
	 * Filtert nach der aktuellen Selektion oder den vom Benutzer eingegeben Filter.
	 */
	protected void extendWhere(OctopusContext cntx, Select select) throws BeanException {
		super.extendWhere(cntx, select);

		List selection = (List)cntx.sessionAsObject("selectionPerson");
		if (selection != null && selection.size() != 0) {
			select.where(Where.and(
					Expr.in("tperson.pk", selection),
					getPersonListFilter(cntx, false)));
		}
	}

    //
    // Octopus-Aktionen
    //
    /** Eingabe-Parameter der Octopus-Aktion {@link #exportFormat(OctopusContext)} */
	public static final String INPUT_exportFormat[] = {};
    /** Ausgabe-Parameter der Octopus-Aktion {@link #exportFormat(OctopusContext)} */
	public static final String OUTPUT_exportFormat = "exportFormat";
	/**
     * Diese Octopus-Aktion liefert die Standard-Dateinamenerweiterung zu dem Format,
     * das beim Standard-Label-Dokumenttyp für einen Spreadsheet-Export eingestellt ist.
     *
     * @param cntx Octopus-Kontext
     * @return Standard-Dateinamenerweiterung
	 */
	public String exportFormat(OctopusContext cntx) throws BeanException, IOException {
		return ExportHelper.getExtension(SpreadSheetFactory.getSpreadSheet(SpreadSheetFactory.TYPE_CSV_DOCUMENT).getFileExtension());
	}

	/**
	 * Schreibt die überschriften des Export-Dokumentes.
	 *
	 * @param spreadSheet {@link SpreadSheet}, in das geschrieben werden soll.
	 */
	protected void exportHeader(SpreadSheet spreadSheet, OctopusContext octopusContext) {

		LanguageProviderHelper languageProviderHelper = new LanguageProviderHelper();
		LanguageProvider languageProvider = languageProviderHelper.enableTranslation(octopusContext);
		//
		// Gast spezifische Daten
		//


		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_DOCUMENT_TYPE"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_FREETEXT_FIELD"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_PARTNER_FREETEXT_FIELD"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_INTERCONNECTS"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_ADDRESS"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_CHARSET"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_FUNCTION"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_TITLE"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_FIRSTNAME"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_LASTNAME"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_PARTNER_TITLE"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_PARTNER_ACADEMIC_TITLE"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_PARTNER_FIRSTNAME"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_PARTNER_LASTNAME"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_POSTALCODE"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_CITY"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_STREET"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_COUNTRY"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_ADDITIONAL_ADDRESS_ONE"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_ADDITIONAL_ADDRESS_TWO"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_CITY_OF_BORN"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_TELEPHONE"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_FAX"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_EMAIL"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_WWW"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_MOBILE"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_COMPANY"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_POST_OFFICE_BOX_NUMBER"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_POST_OFFICE_BOX_PLZ"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_WORK_AREA"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_CATEGORY"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_CATEGORY_RANG"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_RANG"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_RESERVE"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_STATUS"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_TABLE"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_SEAT"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_CURRENT_NUMBER"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_COLOUR"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_IN_COUNTRY"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_LANGUAGES"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_SEX"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_NATIONALITY"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_HINT_RESPONSIBLE"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_HINT_ORG_TEAM"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_PARTNER_STATUS"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_PARTNER_TABLE"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_PARTNER_SEAT"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_PARTNER_CURRENT_NUMBER"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_PARTNER_COLOUR"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_PARTNER_IN_COUNTRY"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_PARTNER_LANGUAGES"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_PARTNER_SEX"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_PARTNER_NATIONALITY"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_PARTNER_HINT_RESPONSIBLE"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_PARTNER_HINT_ORG_TEAM"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_ACCEPT"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_DECLINE"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_OPEN"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_GUEST_IN_PLACE"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_GUEST_IN_RESERVE"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_EVENT_NAME"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_EVENT_BEGIN"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_EVENT_END"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_EVENT_LOCATION"));
		spreadSheet.addCell(languageProvider.getProperty("EXPORT_HEADER_OBSERVATION"));

	}

    /**
     * Diese Methode exportiert die Personen aus dem übergebenen Select in das
     * übergebene Spreadsheet
     *
     * @param spreadSheet {@link SpreadSheet}, in das exportiert werden soll.
     * @param database Datenbank, in der das Select ausgeführt werden soll.
     * @param select Select-Statement, das die zu exportierenden Personen selektiert.
     * @param data Zusatzdaten unter den Schlüsseln "doctype", "color1", "color2",
     *  "color3" und "color4".
     * @param memberAEx Attributschlüsselsuffix der Hauptperson
     * @param memberBEx Attributschlüsselsuffix der Partnerperson
     * @param addressEx Attributschlüsselsuffix der Adressdaten
     */
	protected void exportSelect(SpreadSheet spreadSheet, Database database, Grants grants, Select select, Map data, String memberAEx, String memberBEx, String addressEx) throws BeanException {
		try
		{
			for (Iterator it = ( new ResultList( select.executeSelect( database ).resultSet() ) ).iterator(); it.hasNext(); ) {
				Map person = (Map)it.next();

				boolean showA =
					(person.get("lastname_a_e1") != null && ((String)person.get("lastname_a_e1")).length() != 0) ||
					(person.get("lastname_a_e2") != null && ((String)person.get("lastname_a_e2")).length() != 0) ||
					(person.get("lastname_a_e3") != null && ((String)person.get("lastname_a_e3")).length() != 0) ||
					(person.get("firstname_a_e1") != null && ((String)person.get("firstname_a_e1")).length() != 0) ||
					(person.get("firstname_a_e2") != null && ((String)person.get("firstname_a_e2")).length() != 0) ||
					(person.get("firstname_a_e3") != null && ((String)person.get("firstname_a_e3")).length() != 0);

				boolean showB =
					(person.get("lastname_b_e1") != null && ((String)person.get("lastname_b_e1")).length() != 0) ||
					(person.get("lastname_b_e2") != null && ((String)person.get("lastname_b_e2")).length() != 0) ||
					(person.get("lastname_b_e3") != null && ((String)person.get("lastname_b_e3")).length() != 0) ||
					(person.get("firstname_b_e1") != null && ((String)person.get("firstname_b_e1")).length() != 0) ||
					(person.get("firstname_b_e2") != null && ((String)person.get("firstname_b_e2")).length() != 0) ||
					(person.get("firstname_b_e3") != null && ((String)person.get("firstname_b_e3")).length() != 0);
				boolean showRemarks = grants.mayReadRemarkFields();

				// Gleiches Dokument
				if (showA && showB) {
					spreadSheet.openRow();
					exportBothInOneLine(spreadSheet, showA, showB, showRemarks, person, data, memberAEx, memberBEx, addressEx);
					spreadSheet.closeRow();
				} else if (showA) {
					spreadSheet.openRow();
					exportOnlyPerson(spreadSheet, showA, showB, showRemarks, person, data, memberAEx, memberBEx, addressEx);
					spreadSheet.closeRow();
				} else if (showB) {
					spreadSheet.openRow();
					exportOnlyPartner(spreadSheet, showA, showB, showRemarks, person, data, memberAEx, memberBEx, addressEx);
					spreadSheet.closeRow();
				}
			}
		}
		catch( SQLException e ) {
			throw new BeanException( "Der Export kann nicht ausgef\u00fchrt werden.", e );
		}
	}

	/**
	 * Export die Gast- und Partner Daten in eine Zeile.
	 *
	 * @param spreadSheet In das geschrieben werden soll.
	 * @param person Map mit den Gastdaten.
	 * @param data Zusatzinformationen.
	 */
	protected void exportBothInOneLine(SpreadSheet spreadSheet, boolean showA, boolean showB, boolean showRemarks, Map person, Map data, String memberAEx, String memberBEx, String addressEx) {
		//
		// Gast spezifische Daten
		//
		spreadSheet.addCell(data.get("doctype")); // Name des Dokument-Typs

		String text_a = (String)person.get("textfield");
		String text_b = (String)person.get("textfield_p");
		if (showA) {
			spreadSheet.addCell(text_a);
		} else {
			spreadSheet.addCell(null);
		}
		if (showB) {
			spreadSheet.addCell(text_b);
		} else {
			spreadSheet.addCell(null);
		}
		if (showA && showB && text_a != null && text_a.length() != 0 && text_b != null && text_b.length() != 0) {
			spreadSheet.addCell(person.get("textjoin"));
		} else {
			spreadSheet.addCell(null);
		}

		spreadSheet.addCell(GuestExportWorker.getAddresstype((Integer)person.get("addresstype")));
		spreadSheet.addCell(GuestExportWorker.getLocale((Integer)person.get("locale")));

		if (showA) {
			spreadSheet.addCell(person.get("function" + addressEx));
			spreadSheet.addCell(person.get("salutation" + memberAEx));
			spreadSheet.addCell(person.get("title" + memberAEx));
			spreadSheet.addCell(person.get("firstname" + memberAEx));
			spreadSheet.addCell(person.get("lastname" + memberAEx));
		} else {
			spreadSheet.addCell(null);
			spreadSheet.addCell(null);
			spreadSheet.addCell(null);
			spreadSheet.addCell(null);
			spreadSheet.addCell(null);
		}

		if (showB) {
			spreadSheet.addCell(person.get("salutation" + memberBEx));
			spreadSheet.addCell(person.get("title" + memberBEx));
			spreadSheet.addCell(person.get("firstname" + memberBEx));
			spreadSheet.addCell(person.get("lastname" + memberBEx));
		} else {
			spreadSheet.addCell(null);
			spreadSheet.addCell(null);
			spreadSheet.addCell(null);
			spreadSheet.addCell(null);
		}

		spreadSheet.addCell(person.get("zipcode" + addressEx));
		spreadSheet.addCell(person.get("city" + addressEx));
		spreadSheet.addCell(person.get("street" + addressEx));
		spreadSheet.addCell(person.get("country" + addressEx));
		spreadSheet.addCell(person.get("suffix1" + addressEx));
		spreadSheet.addCell(person.get("suffix2" + addressEx));

		/*
		 * modified to support birthplace as per change request for version 1.2.0
		 * cklein
		 * 2008-02-26
		 */
		spreadSheet.addCell(person.get("birthplace" + addressEx));
		spreadSheet.addCell(person.get("fon" + addressEx));
		spreadSheet.addCell(person.get("fax" + addressEx));
		spreadSheet.addCell(person.get("mail" + addressEx));
		spreadSheet.addCell(person.get("url" + addressEx));
		spreadSheet.addCell(person.get("mobil" + addressEx));
		spreadSheet.addCell(person.get("company" + addressEx));
		spreadSheet.addCell(person.get("pobox" + addressEx));
		spreadSheet.addCell(person.get("poboxzipcode" + addressEx));

		/*
		 * modified to support work areas as per change request for version 1.2.0
		 * cklein
		 * 2008-02-26
		 */
		spreadSheet.addCell(null); // Verweis auf Arbeitsbereich, der zur Auswahl führte
		//
		// Kategorie spezifische Daten, wenn nach Kategorie gefilter wurde.
		//
		spreadSheet.addCell(null); // Verweis auf Kategorie, die zur Auswahl führte
		spreadSheet.addCell(null); // Der Rang der Kategorie innerhalb der Kategorien
		spreadSheet.addCell(null); // Der Rang der Person innerhalb der Kategorie
		spreadSheet.addCell(null); // 0 = Tisch, 1 = Reservce

		//
		// Veranstaltungsspezifische Attribute für Person
		//
		if (showA) {
			spreadSheet.addCell(null); // 0 = Offen, 1 = Zusage, 2 = Absage
			spreadSheet.addCell(null);
			spreadSheet.addCell(null);
			spreadSheet.addCell(null);
			spreadSheet.addCell(GuestExportWorker.getColor(getColor(person, data, true))); // Verweiss auf Farbe die verwendet werden soll.
			spreadSheet.addCell(getDomestic((String)person.get("domestic_a_e1"))); // Inland Ja / Nein
			spreadSheet.addCell(person.get("languages_a_e1"));
			spreadSheet.addCell(GuestExportWorker.getGender((String)person.get("sex_a_e1"))); // M oder F
			spreadSheet.addCell(person.get("nationality_a_e1"));
			spreadSheet.addCell(person.get("notehost_a_e1"));
			spreadSheet.addCell(person.get("noteorga_a_e1"));
		} else {
			spreadSheet.addCell(null);
			spreadSheet.addCell(null);
			spreadSheet.addCell(null);
			spreadSheet.addCell(null);
			spreadSheet.addCell(GuestExportWorker.getColor(null));
			spreadSheet.addCell(null);
			spreadSheet.addCell(null);
			spreadSheet.addCell(null);
			spreadSheet.addCell(null);
			spreadSheet.addCell(null);
			spreadSheet.addCell(null);
		}

		//
		// Veranstaltungsspezifische Attribute für Partner der Person
		//
		if (showB) {
			spreadSheet.addCell(null); // 0 = Offen, 1 = Zusage, 2 = Absage
			spreadSheet.addCell(null);
			spreadSheet.addCell(null);
			spreadSheet.addCell(null);
			spreadSheet.addCell(GuestExportWorker.getColor(getColor(person, data, false))); // Verweiss auf Farbe die verwendet werden soll.
			spreadSheet.addCell(getDomestic((String)person.get("domestic_b_e1"))); // Inland Ja / Nein
			spreadSheet.addCell(person.get("languages_b_e1"));
			spreadSheet.addCell(GuestExportWorker.getGender((String)person.get("sex_b_e1"))); // M oder F
			spreadSheet.addCell(person.get("nationality_b_e1"));
			spreadSheet.addCell(showRemarks ? person.get("notehost_b_e1") : null);
			spreadSheet.addCell(showRemarks ? person.get("noteorga_b_e1") : null);
		} else {
			spreadSheet.addCell(null);
			spreadSheet.addCell(null);
			spreadSheet.addCell(null);
			spreadSheet.addCell(null);
			spreadSheet.addCell(GuestExportWorker.getColor(null));
			spreadSheet.addCell(null);
			spreadSheet.addCell(null);
			spreadSheet.addCell(null);
			spreadSheet.addCell(null);
			spreadSheet.addCell(null);
			spreadSheet.addCell(null);
		}

		//
		// Sonstiges
		//
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);
	}

	/**
	 * Export ausschließlich die Gast-Daten in eine Zeile.
	 *
	 * @param spreadSheet In das geschrieben werden soll.
	 * @param person Map mit den Gastdaten.
	 * @param data Zusatzinformationen.
	 */
	protected void exportOnlyPerson(SpreadSheet spreadSheet, boolean showA, boolean showB, boolean showRemarks, Map person, Map data, String memberAEx, String memberBEx, String addressEx) {
		//
		// Gast spezifische Daten
		//
		spreadSheet.addCell(data.get("doctype")); // Name des Dokument-Typs
		spreadSheet.addCell(person.get("textfield"));
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);

		spreadSheet.addCell(GuestExportWorker.getAddresstype((Integer)person.get("addresstype")));
		spreadSheet.addCell(GuestExportWorker.getLocale((Integer)person.get("locale")));

		spreadSheet.addCell(person.get("function" + addressEx));
		spreadSheet.addCell(person.get("salutation" + memberAEx));
		spreadSheet.addCell(person.get("title" + memberAEx));
		spreadSheet.addCell(person.get("firstname" + memberAEx));
		spreadSheet.addCell(person.get("lastname" + memberAEx));

		spreadSheet.addCell(null);
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);

		spreadSheet.addCell(person.get("zipcode" + addressEx));
		spreadSheet.addCell(person.get("city" + addressEx));
		spreadSheet.addCell(person.get("street" + addressEx));
		spreadSheet.addCell(person.get("country" + addressEx));
		spreadSheet.addCell(person.get("suffix1" + addressEx));
		spreadSheet.addCell(person.get("suffix2" + addressEx));

		/*
		 * modified to support birthplace as per change request for version 1.2.0
		 * cklein
		 * 2008-02-26
		 */
		spreadSheet.addCell(person.get("birthplace" + addressEx));
		spreadSheet.addCell(person.get("fon" + addressEx));
		spreadSheet.addCell(person.get("fax" + addressEx));
		spreadSheet.addCell(person.get("mail" + addressEx));
		spreadSheet.addCell(person.get("url" + addressEx));
		spreadSheet.addCell(person.get("mobil" + addressEx));
		spreadSheet.addCell(person.get("company" + addressEx));
		spreadSheet.addCell(person.get("pobox" + addressEx));
		spreadSheet.addCell(person.get("poboxzipcode" + addressEx));

		/*
		 * modified to support work areas as per change request for version 1.2.0
		 * cklein
		 * 2008-02-26
		 */
		spreadSheet.addCell(null); // Verweis auf Arbeitsbereich, der zur Auswahl führte
		//
		// Kategorie spezifische Daten, wenn nach Kategorie gefilter wurde.
		//
		spreadSheet.addCell(null); // Verweis auf Kategorie, die zur Auswahl führte
		spreadSheet.addCell(null); // Der Rang der Kategorie innerhalb der Kategorien
		spreadSheet.addCell(null); // Der Rang der Person innerhalb der Kategorie
		spreadSheet.addCell(null); // 0 = Tisch, 1 = Reservce

		//
		// Veranstaltungsspezifische Attribute für Person
		//
		spreadSheet.addCell(null); // 0 = Offen, 1 = Zusage, 2 = Absage
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);
		spreadSheet.addCell(GuestExportWorker.getColor(getColor(person, data, true))); // Verweiss auf Farbe die verwendet werden soll.
		spreadSheet.addCell(getDomestic((String)person.get("domestic_a_e1"))); // Inland Ja / Nein
		spreadSheet.addCell(person.get("languages_a_e1"));
		spreadSheet.addCell(GuestExportWorker.getGender((String)person.get("sex_a_e1"))); // M oder F
		spreadSheet.addCell(person.get("nationality_a_e1"));
		spreadSheet.addCell(showRemarks ? person.get("notehost_a_e1") : null);
		spreadSheet.addCell(showRemarks ? person.get("noteorga_a_e1") : null);

		//
		// Veranstaltungsspezifische Attribute für Partner der Person
		//
		spreadSheet.addCell(null); // 0 = Offen, 1 = Zusage, 2 = Absage
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);
		spreadSheet.addCell(null); // Verweiss auf Farbe die verwendet werden soll.
		spreadSheet.addCell(null); // Inland Ja / Nein
		spreadSheet.addCell(null);
		spreadSheet.addCell(null); // M oder F
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);

		//
		// Sonstiges
		//
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);
	}

	/**
	 * Export ausschließlich die Partner-Daten in eine Zeile.
	 *
	 * @param spreadSheet In das geschrieben werden soll.
	 * @param person Map mit den Personendaten.
	 * @param data Zusatzinformationen.
	 */
	protected void exportOnlyPartner(SpreadSheet spreadSheet, boolean showA, boolean showB, boolean showRemarks, Map person, Map data, String memberAEx, String memberBEx, String addressEx) {
		//
		// Gast spezifische Daten
		//
		spreadSheet.addCell(data.get("doctype")); // Name des Dokument-Typs
		spreadSheet.addCell(person.get("textfield_p"));
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);

		spreadSheet.addCell(GuestExportWorker.getAddresstype((Integer)person.get("addresstype")));
		spreadSheet.addCell(GuestExportWorker.getLocale((Integer)person.get("locale")));

		spreadSheet.addCell(null);
		spreadSheet.addCell(person.get("salutation" + memberBEx));
		spreadSheet.addCell(person.get("title" + memberBEx));
		spreadSheet.addCell(person.get("firstname" + memberBEx));
		spreadSheet.addCell(person.get("lastname" + memberBEx));

		spreadSheet.addCell(null);
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);

		spreadSheet.addCell(person.get("zipcode" + addressEx));
		spreadSheet.addCell(person.get("city" + addressEx));
		spreadSheet.addCell(person.get("street" + addressEx));
		spreadSheet.addCell(person.get("country" + addressEx));
		spreadSheet.addCell(person.get("suffix1" + addressEx));
		spreadSheet.addCell(person.get("suffix2" + addressEx));

		/*
		 * modified to support birthplace as per change request for version 1.2.0
		 * cklein
		 * 2008-02-26
		 */
		spreadSheet.addCell(""); // partners do not have a birthplace + addressEx attribute
		spreadSheet.addCell(person.get("fon" + addressEx));
		spreadSheet.addCell(person.get("fax" + addressEx));
		spreadSheet.addCell(person.get("mail" + addressEx));
		spreadSheet.addCell(person.get("url" + addressEx));
		spreadSheet.addCell(person.get("mobil" + addressEx));
		spreadSheet.addCell(person.get("company" + addressEx));
		spreadSheet.addCell(person.get("pobox" + addressEx));
		spreadSheet.addCell(person.get("poboxzipcode" + addressEx));

		// fixing bug here, workarea cell was missing

		spreadSheet.addCell(null);
		//
		// Kategorie spezifische Daten, wenn nach Kategorie gefilter wurde.
		//
		spreadSheet.addCell(null); // Verweis auf Kategorie, die zur Auswahl führte
		spreadSheet.addCell(null); // Der Rang der Kategorie innerhalb der Kategorien
		spreadSheet.addCell(null); // Der Rang der Person innerhalb der Kategorie
		spreadSheet.addCell(null); // 0 = Tisch, 1 = Reservce

		//
		// Veranstaltungsspezifische Attribute für Person
		//
		spreadSheet.addCell(null); // 0 = Offen, 1 = Zusage, 2 = Absage
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);
		spreadSheet.addCell(GuestExportWorker.getColor(getColor(person, data, false))); // Verweiss auf Farbe die verwendet werden soll.
		spreadSheet.addCell(getDomestic((String)person.get("domestic_b_e1"))); // Inland Ja / Nein
		spreadSheet.addCell(person.get("languages_b_e1"));
		spreadSheet.addCell(GuestExportWorker.getGender((String)person.get("sex_b_e1"))); // M oder F
		spreadSheet.addCell(person.get("nationality_b_e1"));
		spreadSheet.addCell(showRemarks ? person.get("notehost_b_e1") : null);
		spreadSheet.addCell(showRemarks ? person.get("noteorga_b_e1") : null);

		//
		// Veranstaltungsspezifische Attribute für Partner der Person
		//
		spreadSheet.addCell(null); // 0 = Offen, 1 = Zusage, 2 = Absage
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);
		spreadSheet.addCell(null); // Verweiss auf Farbe die verwendet werden soll.
		spreadSheet.addCell(null); // Inland Ja / Nein
		spreadSheet.addCell(null);
		spreadSheet.addCell(null); // M oder F
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);

		//
		// Sonstiges
		//
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);
		spreadSheet.addCell(null);
	}

    /**
     * Diese Methode liefert eine RGB-Farbe zu einer Person.
     *
     * @param person Person, zu der die Farbe gesucht wird.
     * @param data Zusatzdaten unter den Schlüsseln "color1", "color2",
     *  "color3" und "color4".
     * @param main Flag, ob es um die Hauptperson (<code>true</code>) oder
     *  den Partner (<code>false</code>) geht
     * @return RGB-Wert einer Farbe
     */
	private Integer getColor(Map person, Map data, boolean main) {
		Color color = null;
		if (main) {
			color = (Color)data.get("color" + ColorWorker.getColor((String)person.get("domestic_a_e1"), (String)person.get("sex_a_e1")));
		} else {
			color = (Color)data.get("color" + ColorWorker.getColor((String)person.get("domestic_b_e1"), (String)person.get("sex_b_e1")));
		}
		return color == null ? null : color.rgb;
	}

    /** Diese Methode liefert eine String-Darstellung zu einem Domestic-Attribut */
	private String getDomestic(String domestic) {
		return PersonConstants.DOMESTIC_AUSLAND.equals(domestic) ? "Nein" : "Ja";
	}
}
