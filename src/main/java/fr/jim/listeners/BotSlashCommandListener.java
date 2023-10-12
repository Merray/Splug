package fr.jim.listeners;

import fr.jim.config.ConstantesBot;
import fr.jim.services.slash.SlashGriffonService;
import fr.jim.services.slash.SlashRollService;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BotSlashCommandListener extends ListenerAdapter {

    private static final Logger LOGGER = LogManager.getLogger(BotSlashCommandListener.class);

    private SlashRollService slashRollService = new SlashRollService();

    private SlashGriffonService slashGriffonService = new SlashGriffonService();

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        switch (event.getName()) {
            case ConstantesBot.SLASH_ROLL:
                slashRollService.roll(event);
                break;

            case ConstantesBot.SLASH_GRIFFON:
                slashGriffonService.griffon(event);
                break;

            default:
                break;
        }


    }
}
