package fr.jim.mappers;

public class ModaleGriffon {

    private String discordId;

    private String nom;

    private String classe;

    private String race;

    public ModaleGriffon(String discordId, String nom, String classe, String race) {
        this.discordId = discordId;
        this.nom = nom;
        this.classe = classe;
        this.race = race;
    }

    public String getDiscordId() {
        return discordId;
    }

    public void setDiscordId(String discordId) {
        this.discordId = discordId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    @Override public String toString() {
        return "ModaleGriffon{" +
                "discordId='" + discordId + '\'' +
                ", nom='" + nom + '\'' +
                ", classe='" + classe + '\'' +
                ", race='" + race + '\'' +
                '}';
    }
}
