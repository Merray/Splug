package fr.jim.utils;

import fr.jim.config.ConstantesBot;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
import java.time.Instant;

public class EmbedUtils {

    public static EmbedBuilder embedReponse(String titre, String enGras, String message) {
        EmbedBuilder embedBuilder = new EmbedBuilder();

        embedBuilder.setTitle(titre);
        embedBuilder.setColor(Color.BLUE);
        embedBuilder.setThumbnail(ConstantesBot.FICHIER_GRIFFON_THUMBNAIL);
        embedBuilder.addField(enGras, message, true);
        embedBuilder.setTimestamp(Instant.now());

        return embedBuilder;
    }
}
