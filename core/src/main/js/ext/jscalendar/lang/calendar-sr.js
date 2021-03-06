// ** I18N Serbian (Latin)

// Author: Mihai Bazon, <mishoo@infoiasi.ro>
// Translation: Nenad Nikolic <shone@europe.com>
// Author Srle Doberman <srleiqnet@hotmail.com>, September 2005.
// Feel free to use this script under the terms of the GNU Lesser General
// Public License, as long as you do not remove or alter this notice.

// full day names
Calendar._DN = new Array
("Nedelja",
 "Ponedeljak",
 "Utorak",
 "Sreda",
 "Četvrtak",
 "Petak",
 "Subota",
 "Nedelja");

// short day names
Calendar._SDN = new Array
("Ned",
 "Pon",
 "Uto",
 "Sre",
 "Čet",
 "Pet",
 "Sub",
 "Ned");

// full month names
Calendar._MN = new Array
("Januar",
 "Februar",
 "Mart",
 "April",
 "Maj",
 "Jun",
 "July",
 "Avgust",
 "Septembar",
 "Oktobar",
 "Novembar",
 "Decembar");

// short month names
Calendar._SMN = new Array
("Jan",
 "Feb",
 "Mar",
 "Apr",
 "Maj",
 "Jun",
 "Jul",
 "Avg",
 "Sep",
 "Okt",
 "Nov",
 "Dec");

// tooltips
Calendar._TT = {};
Calendar._TT["INFO"] = "O kalendaru";

Calendar._TT["ABOUT"] =
"DHTML Kalendar\n" +
"(c) dynarch.com 2002-2003\n" + // don't translate this this ;-)
"Najnovija verzija kontrole nalazi se http://dynarch.com/mishoo/calendar.epl\n" +
"Distribuirano po GNU LGPL licencom.  Za detalje pogledaj http://gnu.org/licenses/lgpl.html." +
"\n\n" +
/// either this {
"Izbor datuma:\n" +
"- Koristi dugmiće \xab, \xbb za izbor godine\n" +
"- Koristi dugmiće " + String.fromCharCode(0x2039) + ", " + String.fromCharCode(0x203a) + " za izbor meseca\n" +
"- Za brži izbor, držati pritisnut taster miša iznad bilo kog od pomenutih dugmića";
/// } or this {
//"Selekcija datuma:\n" +
//"- Koristite \xab, \xbb dugmiće za selekciju godine\n" +
//"- Koristite " + String.fromCharCode(0x2039) + ", " +
//String.fromCharCode(0x203a) + " dugmiće za selekciju meseca\n" +
//"- Dugi pritisak na dugmiće iznad za bržu selekciju.";
/// }
Calendar._TT["ABOUT_TIME"] = "\n\n" +
/// either this {
"Izbor vremena:\n" +
"- Kliktaj na sate ili minute povećava njihove vrednosti\n" +
"- Shift-klik smanjuje njihove vrednosti\n" +
"- klikni i vuci za brži izbor.";
/// } or this {
//"Selekcija vremena:\n" +
//"- Pritisnite na bilo koji deo vremena za njegovo povećanje\n" +
//"- ili (Shift-)Pritisnite za umanjivanje vremena\n" +
//"- ili pritisnite i povucite za bržu selekciju.";
/// }

Calendar._TT["PREV_YEAR"] = "Prethodna godina (dugi pritisak za meni)";
Calendar._TT["PREV_MONTH"] = "Prethodni mesec (dugi pritisak za meni)";
Calendar._TT["GO_TODAY"] = "Idi na današnji dan";
//Calendar._TT["GO_TODAY"] = "Idite do današnjeg datuma";
Calendar._TT["NEXT_MONTH"] = "Sledeći mesec (dugi pritisak za meni)";
Calendar._TT["NEXT_YEAR"] = "Sledeća godina (dugi pritisak za meni)";
Calendar._TT["SEL_DATE"] = "Izaberi datum";
//Calendar._TT["SEL_DATE"] = "Izaberite datum";
Calendar._TT["DRAG_TO_MOVE"] = "Pritisni i vuci za promenu pozicije";
//Calendar._TT["DRAG_TO_MOVE"] = "Pritisnite i povucite za promenu pozicije";
Calendar._TT["PART_TODAY"] = " (danas)";

// Choose first day of week.
Calendar._TT["DAY_FIRST"] = "%s kao prvi dan u nedelji";
Calendar._TT["MON_FIRST"] = "Prikaži ponedeljak kao prvi dan nedelje";
Calendar._TT["SUN_FIRST"] = "Prikaži nedelju kao prvi dan nedelje";

// Weekend is usual: Sunday (0) and Saturday (6).
Calendar._TT["WEEKEND"] = "0,6";

Calendar._TT["CLOSE"] = "Zatvori";
//Calendar._TT["CLOSE"] = "Zatvorite";
Calendar._TT["TODAY"] = "Danas";
Calendar._TT["TIME_PART"] = "(Shift-)klikni i vuci za promenu vrednosti";
//Calendar._TT["TIME_PART"] = "(Shift-)Pritisnite ili povucite za promenu vrednosti";

// date formats
Calendar._TT["DEF_DATE_FORMAT"] = "%d.%m.%Y";
Calendar._TT["TT_DATE_FORMAT"] = "%A %d.%m.%Y";

Calendar._TT["WK"] = "Nd";
Calendar._TT["TIME"] = "Vreme:";
