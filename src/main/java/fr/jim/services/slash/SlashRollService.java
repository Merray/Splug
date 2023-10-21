package fr.jim.services.slash;

import fr.jim.config.ConstantesBot;
import fr.jim.entites.dto.Roll;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SlashRollService {

    private static final Logger LOGGER = LogManager.getLogger(SlashRollService.class);
    private Random random = new Random();

    Pattern rollPattern = Pattern.compile("^(([0-9]+|([0-9]+)?d[0-9]+)(\\+|-)?[0-9]*\\s*)+$");

    Matcher rollMatcher;

    public SlashRollService() {
    }

    public void roll(SlashCommandInteractionEvent event) {


        String rollOptions = event.getOption(ConstantesBot.OPTION_SLASH_ROLL_OPTIONS).getAsString();
        LOGGER.info("/roll " + rollOptions + " lancée par " + event.getMember().getEffectiveName());

        rollMatcher = rollPattern.matcher(rollOptions);


        if (!rollMatcher.matches()) {
            event.reply("Les options ne sont pas correctes, voici quelques exemples :\n" +
                    "/roll 1d6 2d12\n" +
                    "/roll 6 12-1 10+2\n" +
                    "/roll 1d6+1\n" +
                    "/roll 1d10-2\n").queue();
        } else {

            List<String> rolls = Arrays.stream(rollOptions.split(" ")).collect(Collectors.toList());


            if (rolls.size() > 20) {

                event.reply("Nombre max de rolls : 20. Tu en as lancé " + rolls.size()).setEphemeral(true).queue();
                LOGGER.info("Trop de rolls : " + rolls.size());
                return;
            }


            List<Roll> listeRolls = new ArrayList<>();
            Roll currentRoll;
            int res;
            int total;

            for (String roll : rolls) {


                currentRoll = new Roll();

                total = 0;
                currentRoll.setSymbole("");

                if (roll.contains("d")) {

                    // 1d6-1 1d12+2
                    if (roll.contains("-") || roll.contains("+")) {

                        String[] valeurs = roll.split("d");
                        currentRoll.setNbLancers(Integer.parseInt(valeurs[0]));
                        currentRoll.setNbFaces(Integer.parseInt(valeurs[1].split("[\\+\\-]")[0]));
                        currentRoll.setModificateur(Integer.parseInt(valeurs[1].split("[\\+\\-]")[1]));
                        currentRoll.setSymbole(valeurs[1].contains("+") ? "+" : "-");


                    } else {
                        // 1d6 2d12
                        String[] valeurs = roll.split("d");
                        currentRoll.setNbLancers(Integer.parseInt(valeurs[0]));
                        currentRoll.setNbFaces(Integer.parseInt(valeurs[1]));
                    }

                } else {
                    // 6 12
                    currentRoll.setNbLancers(Integer.parseInt("1"));
                    currentRoll.setNbFaces(Integer.parseInt(roll.split("[\\+\\-]")[0]));

                    // 6-1 12+2
                    if (roll.contains("-") || roll.contains("+")) {

                        currentRoll.setModificateur(Integer.parseInt(roll.split("[\\+\\-]")[1]));
                        currentRoll.setSymbole(roll.contains("+") ? "+" : "-");

                    }

                }

                for (int z = 0; z < currentRoll.getNbLancers(); z++) {

                    if (!roll.contains("d")) {
                        currentRoll.getResultatsRolls().add("1d" + roll);
                    } else {

                        currentRoll.getResultatsRolls().add("1d" + roll.split("d")[1]);
                    }
                }

                for (int i = 0; i < currentRoll.getNbLancers(); i++) {


                    if (StringUtils.isEmpty(currentRoll.getSymbole())) {

                        res = random.nextInt(currentRoll.getNbFaces()) + 1;

                        total += (res);
                        currentRoll.getResultats().add(Integer.toString(res));

                    } else {

                        if ("+".equals(currentRoll.getSymbole())) {

                            res = random.nextInt(currentRoll.getNbFaces()) + currentRoll.getModificateur() + 1;
                            total += res;
                            currentRoll.getResultats().add((Integer.toString(res)));

                        } else {

                            res = random.nextInt(currentRoll.getNbFaces()) - currentRoll.getModificateur() + 1;
                            total += res;
                            currentRoll.getResultats().add((Integer.toString(res)));


                        }
                    }


                }

                currentRoll.getTotal().append(total + "**");
                listeRolls.add(currentRoll);

            }

            StringBuilder sb = new StringBuilder("==========\n\n**(/roll " + rollOptions + ")**\n\n");

            for (Roll roll : listeRolls) {

                sb.append(roll.toString());

            }

            event.reply(sb.toString()).queue();

        }


    }
}
