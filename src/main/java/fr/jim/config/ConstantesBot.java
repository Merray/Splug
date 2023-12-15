package fr.jim.config;

public class ConstantesBot {

    public static String APP_TOKEN = "app.token";
    public static String UPDATE_COMMANDES = "app.update.commands";

    // PLAYING/LISTENING etc..
    public static String PLAYING_D_AND_D = "D&D";


    // Commandes en slash
    public static final String SLASH_ROLL = "roll";

    public static final String SLASH_GRIFFON = "griffon";

    // Description des commandes slash
    public static final String DESCRIPTION_SLASH_ROLL = "Permet de lancer un dé avec plusieurs options";

    // Option des commandes slash
    public static final String OPTION_SLASH_ROLL_OPTIONS = "roll-options";
    public static final String OPTION_SLASH_ROLL_OPTIONS_DESCRIPTION = "ex: 1d6 2d12 10";
    public static final String OPTION_SLASH_ROLL_INVISIBLE = "invisible";
    public static final String OPTION_SLASH_ROLL_INVISIBLE_DESCRIPTION = "Si true, seul le lanceur voit le résultat";


    public static final String OPTION_SLASH_GRIFFON_ACTION = "action";
    public static final String OPTION_SLASH_GRIFFON_ACTION_ADD = "add";
    public static final String OPTION_SLASH_GRIFFON_ACTION_REMOVE = "remove";
    public static final String OPTION_SLASH_GRIFFON_ACTION_LIST = "list";

    // Modales


    public static final String MODALE_GRIFFON = "griffon-modale-inscription";
    public static final String MODALE_GRIFFON_TITRE = "Inscription à Griffon";

    public static final String MODALE_GRIFFON_ID_DISCORD = "griffon-modale-inscription-id-discord";
    public static final String MODALE_GRIFFON_ID_DISCORD_LABEL = "Id Discord (ne pas modifier svp)";

    public static final String MODALE_GRIFFON_NOM = "griffon-modale-inscription-nom";
    public static final String MODALE_GRIFFON_NOM_LABEL = "Nom";

    public static final String MODALE_GRIFFON_CLASSE = "griffon-modale-inscription-classe";
    public static final String MODALE_GRIFFON_CLASSE_LABEL = "Classe";
    public static final String MODALE_GRIFFON_CLASSE_PLACEHOLDER = "Guerrier de la lumière";

    public static final String MODALE_GRIFFON_RACE = "griffon-modale-inscription-race";
    public static final String MODALE_GRIFFON_RACE_LABEL = "Race";
    public static final String MODALE_GRIFFON_RACE_PLACEHOLDER = "Demi-Orc";

    // URL images

    public static final String FICHIER_GRIFFON_THUMBNAIL = "https://i.postimg.cc/KjNZ1ymM/logo-griffon.png";
    public static final String FICHIER_ROLL_THUMBNAIL = "https://i.postimg.cc/NMJkNd3V/dice.png";
}
