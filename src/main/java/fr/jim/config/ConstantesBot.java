package fr.jim.config;

public class ConstantesBot {

    // Token de l'application, ne pas commit si le git est publique
    public static String TOKEN = "MTE2MTE5ODcyMzM3Njg3MzUyNQ.GPlKoX.o_tmnPY-11c-CWO-b7oP1JbhZvwq1dIaWA5Rkc";
    public static boolean UPSERT_COMMANDS = true;

    // PLAYING/LISTENING etc..
    public static String PLAYING_D_AND_D = "D&D";


    // Commandes en slash
    public static final String SLASH_ROLL = "roll";

    // Description des commandes slash
    public static final String DESCRIPTION_SLASH_ROLL = "Permet de lancer un dé avec plusieurs options";

    // Option des commandes slash
    public static final String OPTION_SLASH_ROLL_NOMBRE_FACES = "nombre-faces";
    public static final String OPTION_SLASH_ROLL_NOMBRE_FACES_DESCRIPTION = "Nombre de faces du dé";
}
