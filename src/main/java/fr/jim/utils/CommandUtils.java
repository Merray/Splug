package fr.jim.utils;

import fr.jim.config.ConstantesBot;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandUtils {

    private static final Logger LOGGER = LogManager.getLogger(CommandUtils.class);

    public static void upsertCommands(Guild guilde) {

        if (guilde != null) {

            // /roll
//            guilde.upsertCommand(ConstantesBot.SLASH_ROLL,
//                    ConstantesBot.DESCRIPTION_SLASH_ROLL).queue();
            guilde.updateCommands().addCommands(
                    Commands.slash(ConstantesBot.SLASH_ROLL, ConstantesBot.DESCRIPTION_SLASH_ROLL)
                            .addOption(OptionType.INTEGER, ConstantesBot.OPTION_SLASH_ROLL_NOMBRE_FACES
                                    , ConstantesBot.OPTION_SLASH_ROLL_NOMBRE_FACES_DESCRIPTION, true)
            ).queue();
            LOGGER.info("Update de la commande {} pour la guilde {}"
                    , ConstantesBot.SLASH_ROLL, guilde.getName());

        }
    }
}
