package fr.jim.utils;

import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
import java.time.Instant;

public class EmbedUtils {

    public static EmbedBuilder embedReponse(String titre, String enGras, String message, String thumbmail) {
        EmbedBuilder embedBuilder = new EmbedBuilder();

        embedBuilder.setTitle(titre);
        embedBuilder.setColor(Color.BLUE);
        embedBuilder.setThumbnail(thumbmail);
        embedBuilder.addField(enGras, message, true);
        embedBuilder.setTimestamp(Instant.now());

        return embedBuilder;
    }

    public static EmbedBuilder embedReponse(String titre, String enGras, String message, String thumbmail,
                                            Color color) {
        EmbedBuilder embedBuilder = new EmbedBuilder();

        embedBuilder.setTitle(titre);
        embedBuilder.setColor(color);
        embedBuilder.setThumbnail(thumbmail);
        embedBuilder.addField(enGras, message, true);
        embedBuilder.setTimestamp(Instant.now());

        return embedBuilder;
    }
}
