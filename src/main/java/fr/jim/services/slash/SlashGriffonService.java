package fr.jim.services.slash;

import fr.jim.config.ConstantesBot;
import fr.jim.entites.MembreGriffon;
import fr.jim.mappers.ModaleGriffon;
import fr.jim.services.db.MembreGriffonService;
import fr.jim.utils.EmbedUtils;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class SlashGriffonService {

    private static final Logger LOGGER = LogManager.getLogger(SlashGriffonService.class);

    private MembreGriffonService membreGriffonService = new MembreGriffonService();


    public void griffon(SlashCommandInteractionEvent event) {


        String action = event.getOption(ConstantesBot.OPTION_SLASH_GRIFFON_ACTION).getAsString();

        String nom = event.getMember().getEffectiveName();

        String idDiscord = event.getMember().getId();
        long idDiscordLong = event.getMember().getIdLong();

        boolean isInscription = false;


        String message = "";

        String enGras = "";

        switch (action) {

            case ConstantesBot.OPTION_SLASH_GRIFFON_ACTION_ADD:
                LOGGER.info("/roll add lancée par " + nom);
                isInscription = true;
                event.replyModal(this.creerModaleInscription(idDiscord, nom)).queue();
                break;

            case ConstantesBot.OPTION_SLASH_GRIFFON_ACTION_REMOVE:
                LOGGER.info("/roll remove lancée par " + nom);
                isInscription = false;
                if (membreGriffonService.findByIdDiscord(idDiscordLong) != null) {
                    membreGriffonService.deleteByIdDiscord(idDiscordLong);
                    message = "<@" + idDiscord + "> vient de se désinscrire de Griffon !";
                } else {
                    message = "<@" + idDiscord + "> n'est pas inscrit(e) à Griffon";
                }
                enGras = "Désinscription";
                break;

            case ConstantesBot.OPTION_SLASH_GRIFFON_ACTION_LIST:
                LOGGER.info("/roll list lancée par " + nom);
                isInscription = false;
                List<MembreGriffon> listeInscrits = membreGriffonService.findAll();

                String noms = listeInscrits.stream().map(MembreGriffon::getInfos).collect(Collectors.joining("\n"));
                message = noms;
                enGras = "Liste des membres inscrits";
                break;
            default:
                break;
        }


        if (!isInscription) {
            event.deferReply().queue();
            event.getHook().sendMessageEmbeds(EmbedUtils
                    .embedReponse("Griffon", enGras, message, ConstantesBot.FICHIER_GRIFFON_THUMBNAIL).build()).queue();
        }
    }

    private Modal creerModaleInscription(String idDiscord, String nomJoueur) {

        TextInput discordId = TextInput
                .create(ConstantesBot.MODALE_GRIFFON_ID_DISCORD,
                        ConstantesBot.MODALE_GRIFFON_ID_DISCORD_LABEL, TextInputStyle.SHORT)
                .setMinLength(1)
                .setMaxLength(50).setValue(idDiscord).build();

        TextInput nom = TextInput
                .create(ConstantesBot.MODALE_GRIFFON_NOM,
                        ConstantesBot.MODALE_GRIFFON_NOM_LABEL, TextInputStyle.SHORT)
                .setMinLength(1)
                .setMaxLength(50).setValue(nomJoueur).build();

        TextInput classe = TextInput
                .create(ConstantesBot.MODALE_GRIFFON_CLASSE,
                        ConstantesBot.MODALE_GRIFFON_CLASSE_LABEL, TextInputStyle.SHORT)
                .setPlaceholder(ConstantesBot.MODALE_GRIFFON_CLASSE_PLACEHOLDER).setMinLength(1)
                .setMaxLength(50).setRequired(true).build();

        TextInput race = TextInput
                .create(ConstantesBot.MODALE_GRIFFON_RACE,
                        ConstantesBot.MODALE_GRIFFON_RACE_LABEL, TextInputStyle.SHORT)
                .setPlaceholder(ConstantesBot.MODALE_GRIFFON_RACE_PLACEHOLDER).setMinLength(1)
                .setMaxLength(50).setRequired(true).build();

        return Modal
                .create(ConstantesBot.MODALE_GRIFFON,
                        ConstantesBot.MODALE_GRIFFON_TITRE)
                .addActionRows(ActionRow.of(discordId), ActionRow.of(nom), ActionRow.of(classe),
                        ActionRow.of(race)).build();

    }

    public void inscrirePersonne(ModaleGriffon modaleGriffon, ModalInteractionEvent event) {


        event.deferReply().queue();
        String message = "";

        if (membreGriffonService.findByIdDiscord(Long.parseLong(modaleGriffon.getDiscordId())) != null) {
            message = "<@" + modaleGriffon.getDiscordId() + "> est déjà inscrit(e) à Griffon !";
        } else {
            membreGriffonService.create(
                    new MembreGriffon(Long.parseLong(modaleGriffon.getDiscordId()), modaleGriffon.getNom(),
                            modaleGriffon.getClasse(),
                            modaleGriffon.getRace()));
            message = "<@" + modaleGriffon.getDiscordId() + "> vient de s'inscrire à Griffon en tant que " +
                    modaleGriffon.getClasse() + " " + modaleGriffon.getRace();
        }

        event.getHook().sendMessageEmbeds(EmbedUtils
                        .embedReponse("Griffon", "Inscription", message, ConstantesBot.FICHIER_GRIFFON_THUMBNAIL).build())
                .queue();
    }
}
