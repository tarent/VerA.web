/*
 * $Id: MadlanConstants.java,v 1.1 2007/06/20 11:56:52 christoph Exp $
 * 
 * Created on 09.11.2005
 */
package de.tarent.aa.veraweb.utils;

/**
 * Diese Schnittstelle enth�lt Konstanten f�r das Behandeln von
 * MAdLAN-Office-Steuerdateien. 
 * 
 * @author mikel
 */
public interface MadlanConstants {
    //
    // Property-Bezeichner und -Defaults f�r die MAdLAN-Formate
    //
    /** Property-Schl�ssel f�r die Liste der kyrillischen Felder */
    public final static String KEY_KYRILLIC_FIELDS = "kyrillicFields";
    
    /** Property-Schl�ssel f�r das Zeichen-Mapping A (normale Felder) */
    public final static String KEY_MAPPING_A = "encodingA";
    
    /** Property-Schl�ssel f�r das Zeichen-Mapping B (kyrillische Felder) */
    public final static String KEY_MAPPING_B = "encodingB";
    
    //
    // Schl�ssel f�r die verschiedenen m�glichen Zeichenencodings
    // (Diese wurden an AAKonv.INI des Textkonverters des AA orientiert.)
    //
    /** Zeichensatz: Baltisch (BaltRoman, BaltHelv) */
    public final static String CHARS_BALT = "balt";

    /** Zeichensatz: Baltisch (BalticRoman, BalticHlv) */
    public final static String CHARS_BALTIC = "baltic";

    /** Zeichensatz: Kyrillisch (CyrExtRoman, CyrExtHlv) */
    public final static String CHARS_CYR_EXT = "cyrext";

    /** Zeichensatz: Kyrillisch (CyrillicRoman, CyrillicHlv) */
    public final static String CHARS_CYRILLIC = "cyrillic";

    /** Zeichensatz: �stlich (EastRoman, EastHlv) */
    public final static String CHARS_EAST = "east";

    /** Zeichensatz: Griechisch (GreekRoman, GreekHlv) */
    public final static String CHARS_GREEK = "greek";

    /** Zeichensatz: Turkmenistan? (TurAsbRoman, TurAsbHlv) */
    public final static String CHARS_TUR_ASB = "turasb";

    /** Zeichensatz: T�rkisch (TurkishRoman, TurkishHlv) */
    public final static String CHARS_TURKISH = "turkish";

    /** Zeichensatz: Westlich (WestRoman, WestHlv) */
    public final static String CHARS_WEST = "west";
    
    /** Zeichensatz: Latin-1 */
    public final static String CHARS_LATIN = "latin";
    
    //
    // Tabellen byte -> char (MAdLAN-Zeichen -> Unicode)
    // (Diese wurden an AAKonv.INI des Textkonverters des AA orientiert.)
    //
    /**
     * Baltische Zeichentabelle aus AAConv.ini: [Balt] f�r BaltRoman, BaltHelv
     */
    final static char[] charsBalt = {
          0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15,
         16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31,
         32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47,
         48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63,
         64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79,
         80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95,
         96, 97, 98, 99,100,101,102,103,104,105,106,107,108,109,110,111,
        112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,
        
          32,  32,8218,  32,8222,8230,8224,8225,
          32,8240, 352,8249,  32,  32,  32,  32,
          32,8216,8217,8220,8221,8226,8211,8212,
          32,8482, 353,8250,  32,  32,  32,  32,
          32,  32, 162, 163, 164,  32, 166, 167,
         168, 169, 342, 171, 172, 173, 174, 198,
         176, 177, 178, 179, 180, 181, 182, 183,
         184, 185, 343, 187, 188, 189, 190, 230,
         260, 302, 256, 262, 196, 197, 280, 274,
         268, 201, 377, 278, 290, 310, 298, 315,
         352, 323, 325, 211, 323, 213, 214, 215,
         370, 321, 346, 362, 220, 379, 381, 223,
         261, 303, 257, 263, 228, 229, 281, 275,
         269, 233, 378, 279, 291, 311, 299, 316,
         353, 324, 326, 243, 333, 245, 246, 247,
         371, 322, 347, 363, 252, 380, 382,  32
    };
    
    /**
     * Baltische Zeichentabelle aus AAConv.ini: [Baltic] f�r BalticRoman, BalticHlv
     */
    final static char[] charsBaltic = {
         0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15,
        16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31,
        32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47,
        48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63,
        64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79,
        80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95,
        96, 97, 98, 99,100,101,102,103,104,105,106,107,108,109,110,111,
       112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,356,
        
        310,  32,8218,  32,8222,8230,8224,8225,
         32,8240, 352,8249, 346, 356, 381, 377,
        311,8216,8217,8220,8221,8226,8211,8212,
         32,8482, 353,8250, 347, 357, 382, 378,
         32, 711, 325, 321, 164, 260, 166, 167,
        168, 169, 302, 171, 172, 173, 174, 379,
        176, 177, 837, 322, 180, 181, 182, 183,
        326, 261, 303, 187, 317, 175, 318, 380,
        340, 193, 315, 256, 196, 317, 262, 278,
        268, 201, 280, 274, 282, 205, 298, 270,
        272, 323, 327, 211, 290, 213, 214, 215,
        344, 366, 218, 362, 220, 221, 370, 223,
        341, 225, 316, 257, 228, 318, 263, 279,
        269, 233, 281, 275, 283, 237, 299, 271,
        273, 324, 328, 243, 291, 245, 246, 247,
        345, 367, 250, 363, 252, 253, 371, 729
    };
    
    /**
     * Kyrillische Zeichentabelle aus AAConv.ini: [CyrExt] f�r CyrExtRoman, CyrExtHlv
     */
    final static char[] charsCyrExt = {
         0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15,
        16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31,
        32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47,
        48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63,
        
         64,1126,1174,1217, 199,1208,1180,1170,
       1206,1037,1250,1178,1198,1240, 196, 214,
       1186,1256,1200, 163,1210,1138, 209, 923,
       1202,1262, 374,  91,  92,  93,  94,  95,
         96,1127,1175,1218, 231,1209,1181,1171,
       1207,1117,1251,1179,1199,1241, 228, 246,
       1187,1257,1201, 402,1211,1139, 241,  32,
       1203,1263, 375, 123, 124, 125, 126,1036,
       1026,1027,8218,1107,8222,8230,8224,8225,
        220,8240,1033,8249,1028,1036,1035,1039,
       1106,8216,8217,8220,8221,8226,8211,8212,
        252,8482,1113,8250,1114,1116,1115,1119,
         32,1038,1118,1032, 164,1168, 166, 167,
       1025, 169,1028, 171, 172, 173, 174,1031,
        176, 177,1030,1110,1169, 181, 182, 183,
       1105,8470,1108, 187,1112,1029,1109,1111,
       1040,1041,1042,1043,1044,1045,1046,1047,
       1048,1049,1050,1051,1052,1053,1054,1055,
       1056,1057,1058,1059,1060,1061,1062,1063,
       1064,1065,1066,1067,1068,1069,1070,1071,
       1072,1073,1074,1075,1076,1077,1078,1079,
       1080,1081,1082,1083,1084,1085,1086,1087,
       1088,1089,1090,1091,1092,1093,1094,1095,
       1096,1097,1098,1099,1100,1101,1102,1103
//         353=1113
//         339=1114
//         402=1107
//         382=1115
//         376=1119
//         8364=1026
//         352=1033
//         338=1034
//         381=1035
    };
    
    /**
     * Kyrillische Zeichentabelle aus AAConv.ini: [Cyrillic] f�r CyrillicRoman, CyrillicHlv
     */
    final static char[] charsCyrillic = {
         0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15,
        16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31,
        32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47,
        48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63,
        64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79,
        80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95,
        96, 97, 98, 99,100,101,102,103,104,105,106,107,108,109,110,111,
       112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,1036,
       
       1026,1027,8218,1107,8222,8230,8224,8225,
         32,8240,1033,8249,1034,1036,1035,1039,
       1106,8216,8217,8220,8221,8226,8211,8212,
         32,8482,1113,8250,1114,1116,1115,1119,
         32,1038,1118,1032, 164,1168, 166, 167,
       1025, 169,1028, 171, 172, 173, 174,1031,
        176, 177,1030,1111,1169, 181, 182, 183,
       1105,8470,1108, 187,1112,1029,1109,1111,
       1040,1041,1042,1043,1044,1045,1046,1047,
       1048,1049,1050,1051,1052,1053,1054,1055,
       1056,1057,1058,1059,1060,1061,1062,1063,
       1064,1065,1066,1067,1068,1069,1070,1071,
       1072,1073,1074,1075,1076,1077,1078,1079,
       1080,1081,1082,1083,1084,1085,1086,1087,
       1088,1089,1090,1091,1092,1093,1094,1095,
       1096,1097,1098,1099,1100,1101,1102,1103
//        353=1113
//        339=1114
//        402=1107
//        382=1115
//        376=1119
//        8364=1026
//        352=1033
//        338=1034
//        381=1035
    };
    
    /**
     * Osteurop�ische Zeichentabelle aus AAConv.ini: [East] f�r EastRoman, EastHlv
     */
    final static char[] charsEast = {
         0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15,
        16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31,
        32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47,
        48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63,
        64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79,
        80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95,
        96, 97, 98, 99,100,101,102,103,104,105,106,107,108,109,110,111,
       112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,286,
       
         32,  32,8218,  32,8222,8230,8224,8225,
        710,8240, 352,8249, 346, 356, 381, 377,
         32,8216,8217,8220,8221,8226,8211,8212,
        732,8482, 353,8250, 347, 357, 382, 378,
         32, 711, 728, 321, 164, 260, 166, 167,
        168, 169, 350, 171, 172, 173, 174, 379,
        176, 177, 731, 322, 180, 181, 182, 183,
        184, 261, 351, 187, 317, 733, 318, 380,
        340, 193, 194, 258, 196, 313, 262, 199,
        268, 201, 280, 203, 282, 205, 206, 270,
        272, 323, 327, 211, 212, 336, 214, 215,
        344, 366, 218, 368, 220, 221, 354, 223,
        341, 225, 226, 259, 228, 314, 263, 231,
        269, 233, 281, 235, 283, 237, 238, 271,
        273, 324, 328, 243, 244, 337, 246, 247,
        345, 367, 250, 369, 252, 253, 355, 729
//        338=346
//        339=347
//        376=378
    };
    
    /**
     * Grieschiche Zeichentabelle aus AAConv.ini: [Greek] f�r GreekRoman, GreekHlv
     */
    final static char[] charsGreek = {
         0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15,
        16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31,
        32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47,
        48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63,
        64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79,
        80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95,
        96, 97, 98, 99,100,101,102,103,104,105,106,107,108,109,110,111,
       112,113,114,115,116,117,118,119,120,121,122,123,124,125,126, 32,
        
         32,  32,8218, 402,8222,8230,8224,8225,
        710,8240, 352,8249, 338,  32,  32,  32,
         32,8216,8217,8220,8208,8226,8211,8212,
        732,8482, 353,8250, 339,  32,  32, 376,
         32, 901, 902, 163, 164, 165, 166, 167,
        168, 169, 170, 171, 172, 173, 174, 175,
        176, 177, 178, 179, 180, 181, 182, 183,
        904, 905, 906, 187, 908, 189, 910, 911,
        912, 913, 914, 915, 916, 917, 918, 919,
        920, 921, 922, 923, 924, 925, 926, 927,
        928, 929, 986, 931, 932, 933, 934, 935,
        936, 937, 938, 939, 940, 941, 942, 943,
        944, 945, 946, 947, 948, 949, 950, 951,
        952, 953, 954, 955, 956, 957, 958, 959,
        960, 961, 962, 963, 964, 965, 966, 967,
        968, 969, 970, 971, 972, 974, 974,  32
    };
    
    /**
     * Turkmenische (?) Zeichentabelle aus AAConv.ini: [TurAsb] f�r TurAsbRoman, TurAsbHlv
     */
    final static char[] charsTurAsb = {
         0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15,
        16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31,
        32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47,
        48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63,
        64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79,
        80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95,
        96, 97, 98, 99,100,101,102,103,104,105,106,107,108,109,110,111,
       112,113,114,115,116,117,118,119,120,121,122,123,124,125,126, 32,
        
         32,  32,8218,  32,8222,8230,8224,8225,
         32,8240,  32,8249,  32,  32,  32,  32,
         32,8216,8217,8220,8221,8226,8211,8212,
         32,8482,  32,8250,  32,  32,  32,  32,
         32, 161, 162, 163, 164, 165, 166, 167,
        168, 169, 170, 171, 172, 173, 174, 175,
        176, 177, 178, 179, 180, 181, 182, 183,
        184, 185, 186, 187, 188, 189, 190, 191,
        192, 193, 194, 195, 196, 399, 198, 199,
        200, 201, 202, 203, 204, 205, 206, 207,
        286, 209, 210, 211, 212, 213, 214, 215,
        216, 217, 218, 219, 220, 304, 350, 223,
        224, 225, 226, 227, 228, 477, 230, 231,
        232, 233, 234, 235, 236, 237, 238, 239,
        287, 241, 242, 243, 244, 245, 246, 247,
        248, 249, 250, 251, 252, 305, 351, 255
    };
    
    /**
     * T�rkische Zeichentabelle aus AAConv.ini: [Turkish] f�r TurkishRoman, TurkishHlv
     */
    final static char[] charsTurkish = {
         0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15,
        16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31,
        32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47,
        48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63,
        64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79,
        80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95,
        96, 97, 98, 99,100,101,102,103,104,105,106,107,108,109,110,111,
       112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,
       
         32,  32,8218, 402,8222,8230,8224,8225,
        710,8240, 352,8249, 338,  32,  32,  32,
         32,8216,8217,8220,8221,8226,8211,8212,
        732,8482, 353,8250, 339,  32,  32, 376,
         32, 161, 162, 163, 164, 165, 166, 167,
        168, 169, 170, 171, 172, 173, 174, 175,
        176, 177, 178, 179, 180, 181, 182, 183,
        184, 185, 186, 187, 188, 189, 190, 191,
        192, 193, 194, 195, 196, 197, 198, 199,
        200, 201, 202, 203, 204, 205, 206, 207,
        286, 209, 210, 211, 212, 213, 214, 215,
        216, 217, 218, 219, 220, 304, 350, 223,
        224, 225, 226, 227, 228, 229, 230, 231,
        232, 233, 234, 235, 236, 237, 238, 239,
        287, 241, 242, 243, 244, 245, 246, 247,
        248, 249, 250, 251, 252, 305, 351, 255
    };
    
    /**
     * Westeurop�ische Zeichentabelle aus AAConv.ini: [West] f�r WestRoman, WestHlv
     */
    final static char[] charsWest = {
        0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15,
        16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31,
        32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47,
        48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63,
        64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79,
        80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95,
        96, 97, 98, 99,100,101,102,103,104,105,106,107,108,109,110,111,
       112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,
       
         32,  32,8218, 402,8222,8230,8224,8225,
        710,8240, 352,8249, 338,  32,  32,  32,
         32,8216,8217,8220,8221,8226,8211,8212,
        732,8482, 353,8250, 339,  32,  32, 376,
         32, 161, 162, 163, 164, 165, 166, 167,
        168, 169, 170, 171, 172, 173, 174, 175,
        176, 177, 178, 179, 180, 181, 182, 183,
        184, 185, 186, 187, 188, 189, 190, 191,
        192, 193, 194, 195, 196, 197, 198, 199,
        200, 201, 202, 203, 204, 205, 206, 207,
        208, 209, 210, 211, 212, 213, 214, 215,
        216, 217, 218, 219, 220, 221, 222, 223,
        224, 225, 226, 227, 228, 229, 230, 231,
        232, 233, 234, 235, 236, 237, 238, 239,
        240, 241, 242, 243, 244, 245, 246, 247,
        248, 249, 250, 251, 252, 253, 254, 255
    };

    /**
     * Latin-1-Zeichentabelle: Einbettung in Unicode
     */
    final static char[] charsLatin = {
        0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15,
        16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31,
        32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47,
        48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63,
        64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79,
        80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95,
        96, 97, 98, 99,100,101,102,103,104,105,106,107,108,109,110,111,
       112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,
       128,129,130,131,132,133,134,135,136,137,138,139,140,141,142,143,
       144,145,146,147,148,149,150,151,152,153,154,155,156,157,158,159,
       160,161,162,163,164,165,166,167,168,169,170,171,172,173,174,175,
       176,177,178,179,180,181,182,183,184,185,186,187,188,189,190,191,
       192,193,194,195,196,197,198,199,200,201,202,203,204,205,206,207,
       208,209,210,211,212,213,214,215,216,217,218,219,220,221,222,223,
       224,225,226,227,228,229,230,231,232,233,234,235,236,237,238,239,
       240,241,242,243,244,245,246,247,248,249,250,251,252,253,254,255
    };
}
