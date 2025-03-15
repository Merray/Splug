package fr.jim.entites.dto;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Roll {

    private List<String> resultats = new ArrayList<>();
    private List<String> intitules = new ArrayList<>();

    private StringBuilder totalString = new StringBuilder("**Total = ");

    private int total;

    private int nbLancers;

    private int nbFaces;

    private int modificateur = 0;

    private String symbole = "";

    private boolean isComparedRoll = false;

    public Roll(String stringRoll) {
        this.construireRoll(stringRoll);
        this.alimenterIntitules(stringRoll);
        this.alimenterResultatsEtTotal(stringRoll);
    }

    public Roll(String stringRoll, boolean isComparedRoll) {
        this.isComparedRoll = isComparedRoll;
        this.construireRoll(stringRoll);
        this.alimenterIntitules(stringRoll);
        this.alimenterResultatsEtTotal(stringRoll);
    }

    private void construireRoll(String stringRoll) {

        if (this.isComparedRoll) {
            stringRoll = stringRoll.split("[<>]")[0].trim();
        }

        this.setSymbole("");

        // Déterminer tous les attributs du stringRoll
        if (stringRoll.contains("d") || stringRoll.contains("D")) {

            // 1d6-1 1d12+2
            if (stringRoll.contains("-") || stringRoll.contains("+")) {

                String[] valeurs = stringRoll.split("[dD]");
                this.setNbLancers(Integer.parseInt(valeurs[0]));
                this.setNbFaces(Integer.parseInt(valeurs[1].split("[\\+\\-]")[0]));
                this.setModificateur(Integer.parseInt(valeurs[1].split("[\\+\\-]")[1]));
                this.setSymbole(valeurs[1].contains("+") ? "+" : "-");


            } else {
                // 1d6 2d12
                String[] valeurs = stringRoll.split("[dD]");
                this.setNbLancers(Integer.parseInt(valeurs[0]));
                this.setNbFaces(Integer.parseInt(valeurs[1]));
            }

        } else {
            // 6 12
            this.setNbLancers(Integer.parseInt("1"));
            this.setNbFaces(Integer.parseInt(stringRoll.split("[\\+\\-]")[0]));

            // 6-1 12+2
            if (stringRoll.contains("-") || stringRoll.contains("+")) {

                this.setModificateur(Integer.parseInt(stringRoll.split("[\\+\\-]")[1]));
                this.setSymbole(stringRoll.contains("+") ? "+" : "-");

            }

        }

    }

    private void alimenterIntitules(String stringRoll) {
        // Alimenter les intitulés des rolls.
        for (int z = 0; z < this.getNbLancers(); z++) {

            if (!stringRoll.contains("d") && !stringRoll.contains("D")) {

                if (this.getModificateur() != 0) {

                    this.getIntitules().add("1d" + stringRoll.split("[\\+\\-]")[0]);

                } else {

                    this.getIntitules().add("1d" + stringRoll);

                }


            } else {

                if (this.getModificateur() != 0) {

                    this.getIntitules().add("1d" + stringRoll.split("[dD]")[1].split("[\\+\\-]")[0]);

                } else {

                    this.getIntitules().add("1d" + stringRoll.split("[dD]")[1]);

                }


            }
        }
    }

    private void alimenterResultatsEtTotal(String stringRoll) {

        int res;
        int total = 0;
        Random random = new Random();

        // Alimenter les résultats des rolls
        for (int i = 0; i < this.getNbLancers(); i++) {

            res = random.nextInt(this.getNbFaces()) + 1;

            if (StringUtils.isEmpty(this.getSymbole())) {

                total += (res);
                this.getResultats().add(Integer.toString(res));

            } else {

                if ("+".equals(this.getSymbole())) {

                    total += res + this.getModificateur();
                    this.getResultats().add((Integer.toString(res)));

                } else {

                    total += res - this.getModificateur();
                    this.getResultats().add((Integer.toString(res)));


                }
            }


        }

        // Alimenter le total du Roll
        if (this.getNbLancers() > 1) {

            if ("+".equals(this.getSymbole())) {

                total -= this.getModificateur() * (this.getNbLancers() - 1);

                this.getTotalString()
                        .append((total - this.getModificateur()) + " " + this.getSymbole() + " " +
                                this.getModificateur() + " = " + total + "**");
                this.total = total;

            } else if ("-".equals(this.getSymbole())) {

                total += this.getModificateur() * (this.getNbLancers() - 1);

                this.getTotalString()
                        .append((total + this.getModificateur()) + " " + this.getSymbole() + " " +
                                this.getModificateur() + " = " + total + "**");

                this.total = total;

            } else {

                this.getTotalString().append(total + "**");

                this.total = total;
            }
        } else {

            if ("+".equals(this.getSymbole())) {
                this.getTotalString()
                        .append((total - this.getModificateur()) + " " + this.getSymbole() + " " +
                                this.getModificateur() + " = " + total + "**");
                this.total = total;

            } else if ("-".equals(this.getSymbole())) {

                total += this.getModificateur() * (this.getNbLancers() - 1);

                this.getTotalString()
                        .append((total + this.getModificateur()) + " " + this.getSymbole() + " " +
                                this.getModificateur() + " = " + total + "**");
                this.total = total;

            } else {

                this.total = total;
            }

        }
    }


    public List<String> getResultats() {
        return resultats;
    }

    public List<String> getIntitules() {
        return intitules;
    }

    public StringBuilder getTotalString() {
        return totalString;
    }

    public int getTotal() {
        return total;
    }

    public int getNbLancers() {
        return nbLancers;
    }

    public void setNbLancers(int nbLancers) {
        this.nbLancers = nbLancers;
    }

    public int getNbFaces() {
        return nbFaces;
    }

    public void setNbFaces(int nbFaces) {
        this.nbFaces = nbFaces;
    }

    public int getModificateur() {
        return modificateur;
    }

    public void setModificateur(int modificateur) {
        this.modificateur = modificateur;
    }

    public String getSymbole() {
        return symbole;
    }

    public void setSymbole(String symbole) {
        this.symbole = symbole;
    }

    @Override public String toString() {

        if (this.getIntitules().size() == 1 && this.getModificateur() == 0) {
            if (this.nbFaces == 20 && "20".equals(this.getResultats().get(0))) {
                return ":crossed_swords: " + this.getIntitules().get(0) + " = " + this.getResultats().get(0) +
                        " :crossed_swords:\n-------------------\n";
            } else if (this.nbFaces == 20 && "1".equals(this.getResultats().get(0))) {

                return ":csob: " + this.getIntitules().get(0) + " = " + this.getResultats().get(0) +
                        " :sob:\n-------------------\n";
            } else {

                return this.getIntitules().get(0) + " = " + this.getResultats().get(0) +
                        "\n-------------------\n";
            }
        } else {

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < this.getIntitules().size(); i++) {

                if (this.nbFaces == 20 && "20".equals(this.getResultats().get(i))) {

                    sb.append(":crossed_swords: " + this.getIntitules().get(i) + " = " + this.getResultats().get(i)
                            + " :crossed_swords:\n");
                } else if (this.nbFaces == 20 && "1".equals(this.getResultats().get(i))) {

                    sb.append(":sob: " + this.getIntitules().get(i) + " = " + this.getResultats().get(i)
                            + " :sob:\n");
                } else {
                    sb.append(this.getIntitules().get(i) + " = " + this.getResultats().get(i) + "\n");
                }
            }

            sb.append(this.getTotalString() + "\n-------------------\n");

            return sb.toString();

        }
    }
}
