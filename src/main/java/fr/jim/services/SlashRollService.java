package fr.jim.services;

import fr.jim.config.ConstantesBot;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class SlashRollService {

    private static final Logger LOGGER = LogManager.getLogger(SlashRollService.class);
    private Random random = new Random();

    public SlashRollService() {
    }

    public void roll(SlashCommandInteractionEvent event) {

        event.deferReply().queue();

        int nbFaces = event.getOption(ConstantesBot.OPTION_SLASH_ROLL_NOMBRE_FACES).getAsInt();

        int resultat = this.random.nextInt(nbFaces + 1);

        event.getHook().sendMessage("Tu as lancé un dé " + nbFaces + " et tu as fait un " + resultat + " !").queue();
    }
}
