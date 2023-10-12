package fr.jim.services.slash;

import fr.jim.config.ConstantesBot;
import fr.jim.entites.MembreGriffon;
import fr.jim.services.db.MembreGriffonService;
import fr.jim.utils.EmbedUtils;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class SlashGriffonService {

    private static final Logger LOGGER = LogManager.getLogger(SlashGriffonService.class);

    private MembreGriffonService membreGriffonService = new MembreGriffonService();


    public void griffon(SlashCommandInteractionEvent event) {

        event.deferReply().queue();

        String action = event.getOption(ConstantesBot.OPTION_SLASH_GRIFFON_ACTION).getAsString();

        String nom = event.getMember().getEffectiveName();

        long idDiscord = event.getMember().getIdLong();

        String message = "";

        String enGras = "";

        switch (action) {

            case ConstantesBot.OPTION_SLASH_GRIFFON_ACTION_ADD:
                if (membreGriffonService.findByIdDiscord(idDiscord) != null) {
                    message = "<@" + idDiscord + "> est déjà inscrit(e) à Griffon !";
                } else {
                    membreGriffonService.create(new MembreGriffon(idDiscord, nom));
                    message = "<@" + idDiscord + "> vient de s'inscrire à Griffon !";
                }
                enGras = "Inscription";
                break;

            case ConstantesBot.OPTION_SLASH_GRIFFON_ACTION_REMOVE:
                if (membreGriffonService.findByIdDiscord(idDiscord) != null) {
                    membreGriffonService.deleteByIdDiscord(idDiscord);
                    message = "<@" + idDiscord + "> vient de se désinscrire de Griffon !";
                } else {
                    message = "<@" + idDiscord + "> n'est pas inscrit(e) à Griffon";
                }
                enGras = "Désinscription";
                break;

            case ConstantesBot.OPTION_SLASH_GRIFFON_ACTION_LIST:

                List<MembreGriffon> listeInscrits = membreGriffonService.findAll();

                String noms = listeInscrits.stream().map(MembreGriffon::getNom).collect(Collectors.joining(", "));
                message = noms;
                enGras = "Liste des membres inscrits";
                break;
            default:
                break;
        }


        event.getHook().sendMessageEmbeds(EmbedUtils
                .embedReponse("Griffon", enGras, message).build()).queue();
    }
}
