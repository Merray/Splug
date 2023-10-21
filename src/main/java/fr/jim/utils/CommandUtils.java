package fr.jim.utils;

import fr.jim.config.ConstantesBot;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandUtils {

    private static final Logger LOGGER = LogManager.getLogger(CommandUtils.class);

    public static void upsertCommands(Guild guilde) {

        if (guilde != null) {

            guilde.updateCommands().addCommands(
                    // roll
                    Commands.slash(ConstantesBot.SLASH_ROLL, ConstantesBot.DESCRIPTION_SLASH_ROLL)
                            .addOption(OptionType.STRING, ConstantesBot.OPTION_SLASH_ROLL_OPTIONS
                                    , ConstantesBot.OPTION_SLASH_ROLL_OPTIONS_DESCRIPTION, true),
                    // Griffon
                    Commands.slash("griffon", "commandes associées au GN griffon")
                            .addOptions(
                                    new OptionData(OptionType.STRING, "action", "Action à faire pour ce GN", true)
                                            .addChoice("add", "add")
                                            .addChoice("remove", "remove")
                                            .addChoice("list", "list")
                            )
            ).queue();
            LOGGER.info("Update de la commande {} pour la guilde {}"
                    , ConstantesBot.SLASH_ROLL, guilde.getName());

            LOGGER.info("Update de la commande {} pour la guilde {}"
                    , "griffon", guilde.getName());

        }
    }
}
