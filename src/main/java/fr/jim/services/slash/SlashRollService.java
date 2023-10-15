package fr.jim.services.slash;

import fr.jim.config.ConstantesBot;
import fr.jim.utils.EmbedUtils;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
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
            event.getHook().sendMessage("Les options ne sont pas correctes, voici quelques exemples :\n" +
                    "/roll 1d6 2d12\n" +
                    "/roll 6 12-1 10+2\n" +
                    "/roll 1d6+1\n" +
                    "/roll 1d10-2\n").queue();
        } else {

            List<String> rolls = Arrays.stream(rollOptions.split(" ")).collect(Collectors.toList());


            List<String> resultats = new ArrayList<>();
            List<String> resultatsRolls = new ArrayList<>();
            int nbLancers = 1;
            int nbFaces = 6;
            int modificateur = 0;
            String symbole = "";

            for (String roll : rolls) {

                symbole = "";

                System.out.println("roll : " + roll);
                if (roll.contains("d")) {

                    // 1d6-1 1d12+2
                    if (roll.contains("-") || roll.contains("+")) {

                        String[] valeurs = roll.split("d");
                        nbLancers = Integer.parseInt(valeurs[0]);
                        nbFaces = Integer.parseInt(valeurs[1].split("[\\+\\-]")[0]);
                        modificateur = Integer.parseInt(valeurs[1].split("[\\+\\-]")[1]);
                        symbole = valeurs[1].contains("+") ? "+" : "-";


                        System.out.println("nbLancers : " + nbLancers
                                + "\nnbFaces: " + nbFaces
                                + "\nsymbole: " + symbole
                                + "\nmodificateur: " + modificateur);


                    } else {
                        // 1d6 2d12
                        String[] valeurs = roll.split("d");
                        nbLancers = Integer.parseInt(valeurs[0]);
                        nbFaces = Integer.parseInt(valeurs[1]);
                    }

                } else {
                    // 6 12
                    nbLancers = Integer.parseInt("1");
                    nbFaces = Integer.parseInt(roll.split("[\\+\\-]")[0]);

                    // 6-1 12+2
                    if (roll.contains("-") || roll.contains("+")) {

                        modificateur = Integer.parseInt(roll.split("[\\+\\-]")[1]);
                        symbole = roll.contains("+") ? "+" : "-";

                    }

                }

                for (int z = 0; z < nbLancers; z++) {

                    if (!roll.contains("d")) {
                        resultatsRolls.add("1d" + roll);
                    } else {

                        resultatsRolls.add("1d" + roll.split("d")[1]);
                    }
                }
                for (int i = 0; i < nbLancers; i++) {


                    if (StringUtils.isEmpty(symbole)) {

                        resultats.add((Integer.toString(random.nextInt(nbFaces) + 1)));
                        System.out.println("SYMBOLE vide");

                    } else {

                        if ("+".equals(symbole)) {

                            System.out.println("SYMBOLE +");
                            resultats.add((Integer.toString(random.nextInt(nbFaces) + modificateur + 1)));

                        } else {
                            System.out.println("SYMBOLE -");
                            resultats.add((Integer.toString(random.nextInt(nbFaces) - modificateur + 1)));
                        }
                    }


                }
            }


            StringBuilder sb = new StringBuilder("Voici les résultats :\n");

            System.out.println("Restultats size : " + resultats.size());
            System.out.println("RestultatsRolls size : " + resultatsRolls.size());

            for (int y = 0; y < resultats.size(); y++) {

                sb.append(resultatsRolls.get(y) + " = " + "**" + resultats.get(y) + "**").append("\n");
            }


            event.replyEmbeds(EmbedUtils.embedReponse("Lancer de dés", "Voici les résultats", sb.toString(),
                    ConstantesBot.FICHIER_ROLL_THUMBNAIL, Color.MAGENTA).build()).queue();

        }


    }
}
