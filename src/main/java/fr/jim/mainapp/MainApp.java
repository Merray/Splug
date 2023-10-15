package fr.jim.mainapp;

import fr.jim.config.ConstantesBot;
import fr.jim.exceptions.MissingTokenException;
import fr.jim.listeners.BotModalListener;
import fr.jim.listeners.BotSlashCommandListener;
import fr.jim.utils.CommandUtils;
import fr.jim.utils.PropertiesUtils;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class MainApp {
    private static final Logger LOGGER = LogManager.getLogger(MainApp.class);


    static Properties APP_PROPERTIES = PropertiesUtils.getAppPropsProperties();

    public static void main(String[] args)
            throws InterruptedException, MissingTokenException {


        JDA bot = JDABuilder.createDefault(recupererToken())
//				.enableIntents(EnumSet.allOf(GatewayIntent.class))
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .setActivity(Activity.playing(ConstantesBot.PLAYING_D_AND_D))
                .addEventListeners(new BotSlashCommandListener(), new BotModalListener())
                .build().awaitReady();

        LOGGER.info("BOT PRET");


        if (Boolean.parseBoolean(APP_PROPERTIES.getProperty(ConstantesBot.UPDATE_COMMANDES))) {

            Guild guildeTest = bot.getGuildById(1024955895781806081L);
//            Guild guildeVouivrarium = bot.getGuildById(673141124944363530L);

            CommandUtils.upsertCommands(guildeTest);
//            CommandUtils.upsertCommands(guildeVouivrarium);
        }


    }

    private static String recupererToken() {

        return APP_PROPERTIES.getProperty(ConstantesBot.APP_TOKEN);
    }
}