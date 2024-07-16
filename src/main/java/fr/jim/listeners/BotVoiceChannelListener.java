package fr.jim.listeners;

import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class BotVoiceChannelListener extends ListenerAdapter {

    private final Map<String, VoiceChannel> tempChannels = new HashMap<>();
    private final String addChannelName = "Add";
    private final String tempChannelsCategoryName = "TEMP CHANNELS";

    @Override public void onGuildVoiceUpdate(GuildVoiceUpdateEvent event) {

        if (event.getChannelJoined() != null) {
            onJoinChannel(event);
        }

        if (event.getChannelLeft() != null) {
            onLeaveChannel(event);
        }

    }

    private void onJoinChannel(@NotNull GuildVoiceUpdateEvent event) {
        VoiceChannel joinedChannel = event.getChannelJoined().asVoiceChannel();

        if (joinedChannel.getName().equals(addChannelName)) {
            String channelName = event.getMember().getEffectiveName() + "'s Room";
            Category category = joinedChannel.getGuild().getCategoriesByName(tempChannelsCategoryName, true)
                    .get(0);
            if (category != null) {
                category.createVoiceChannel(channelName)
                        .queue(newChannel -> {
                            // Déplace dans le channel temporaire
                            event.getGuild().moveVoiceMember(event.getMember(), newChannel).queue();
                            // Ajout dans la map des temp channels
                            tempChannels.put(newChannel.getId(), newChannel);
                        });
            }
        }
    }

    private void onLeaveChannel(@NotNull GuildVoiceUpdateEvent event) {
        VoiceChannel leftChannel = event.getChannelLeft().asVoiceChannel();

        // Si le channel est un channel temporaire et si il est vide, on le supprime
        if (tempChannels.containsKey(leftChannel.getId()) && leftChannel.getMembers().isEmpty()) {
            leftChannel.delete().queue();
            tempChannels.remove(leftChannel.getId());
        }
    }
}
