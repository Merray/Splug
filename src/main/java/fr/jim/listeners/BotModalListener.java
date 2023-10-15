package fr.jim.listeners;

import fr.jim.config.ConstantesBot;
import fr.jim.mappers.ModaleGriffon;
import fr.jim.services.slash.SlashGriffonService;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BotModalListener extends ListenerAdapter {

    private static final Logger LOGGER = LogManager.getLogger(BotModalListener.class);

    SlashGriffonService slashGriffonService = new SlashGriffonService();

    @Override
    public void onModalInteraction(ModalInteractionEvent event) {

        switch (event.getModalId()) {

            case ConstantesBot.MODALE_GRIFFON:

                ModaleGriffon modaleGriffon =
                        new ModaleGriffon(event.getValue(ConstantesBot.MODALE_GRIFFON_ID_DISCORD).getAsString()
                                , event.getValue(ConstantesBot.MODALE_GRIFFON_NOM).getAsString()
                                , event.getValue(ConstantesBot.MODALE_GRIFFON_CLASSE).getAsString()
                                , event.getValue(ConstantesBot.MODALE_GRIFFON_RACE).getAsString());

                LOGGER.info("Modale inscription griffon récupérée avec les infos:" + modaleGriffon);

                slashGriffonService.inscrirePersonne(modaleGriffon, event);
                break;


        }
    }
}
