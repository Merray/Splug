package fr.jim.services.slash;

import fr.jim.config.ConstantesBot;
import fr.jim.entites.dto.ComparedRoll;
import fr.jim.entites.dto.Roll;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SlashRollService {

    private static final Logger LOGGER = LogManager.getLogger(SlashRollService.class);

    Pattern rollPattern = Pattern.compile("^(([0-9]+|([0-9]+)?[dD][0-9]+)(\\+|-)?[0-9]*\\s*)+$");

    Pattern rollComparePattern = Pattern
            .compile("^(([0-9]+|([0-9]+)?[dD][0-9]+)(\\+|-)?[0-9]*)\\s*(<|>)\\s*[0-9]+$");

    Matcher rollMatcher;
    Matcher rollCompareMatcher;

    public SlashRollService() {
    }

    public void roll(SlashCommandInteractionEvent event) {

        String rollOptions = event.getOption(ConstantesBot.OPTION_SLASH_ROLL_OPTIONS).getAsString();
        boolean isInvisible = event.getOption(ConstantesBot.OPTION_SLASH_ROLL_INVISIBLE) != null ?
                event.getOption(ConstantesBot.OPTION_SLASH_ROLL_INVISIBLE).getAsBoolean() : false;
        LOGGER.info("/roll " + rollOptions + " lancée par " + event.getMember().getEffectiveName());

        rollMatcher = rollPattern.matcher(rollOptions);
        rollCompareMatcher = rollComparePattern.matcher(rollOptions);


        if (!rollMatcher.matches() && !rollCompareMatcher.matches()) {

            event.deferReply(true).queue();

            event.getHook().sendMessage("Les options ne sont pas correctes, voici quelques exemples :\n" +
                    "/roll 1d6 2d12\n" +
                    "/roll 6 12-1 10+2\n" +
                    "/roll 1d6+1\n" +
                    "/roll 1d10-2\n" +
                    "/roll 1d10 > 2\n" +
                    "/roll 20 < 5").queue();
            return;
        }

        if (rollCompareMatcher.matches()) {

            event.deferReply().queue();

            ComparedRoll comparedRoll = new ComparedRoll(rollOptions);

            event.getHook().sendMessage(
                    "Résultat de " + rollOptions + " : " +
                            (comparedRoll.isSuccess() ? ":white_check_mark:" : ":x:") +
                            " (" +
                            comparedRoll.getRoll().getTotal() + ")").queue();

        } else {


            List<String> rolls = Arrays.stream(rollOptions.split(" ")).collect(Collectors.toList());

            if (rolls.size() > 20) {

                event.deferReply(true).queue();

                event.getHook().sendMessage("Nombre max de rolls : 20. Tu en as lancé " + rolls.size())
                        .setEphemeral(true).queue();
                LOGGER.info("Trop de rolls : " + rolls.size());
                return;
            }

            event.deferReply().queue();

            List<Roll> listeRolls = new ArrayList<>();
            Roll currentRoll;

            for (String roll : rolls) {

                currentRoll = new Roll(roll);

                // Ajout du roll complet à la liste des rolls de la commande
                listeRolls.add(currentRoll);
            }

            StringBuilder sb = new StringBuilder("==========\n\n**(/roll " + rollOptions + ")**\n\n");

            for (Roll roll : listeRolls) {

                sb.append(roll.toString());

            }

            event.getHook().sendMessage(sb.toString()).setEphemeral(isInvisible).queue();
        }


    }
}
