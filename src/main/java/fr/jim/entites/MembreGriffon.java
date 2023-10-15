package fr.jim.entites;

public class MembreGriffon {

    private int id;
    private long idDiscord;
    private String nom;

    private String classe;

    private String race;

    public MembreGriffon() {
    }

    public MembreGriffon(int id, long idDiscord, String nom, String classe, String race) {
        this.id = id;
        this.idDiscord = idDiscord;
        this.nom = nom;
        this.classe = classe;
        this.race = race;
    }

    public MembreGriffon(long idDiscord, String nom, String classe, String race) {
        this.idDiscord = idDiscord;
        this.nom = nom;
        this.classe = classe;
        this.race = race;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getIdDiscord() {
        return idDiscord;
    }

    public void setIdDiscord(long idDiscord) {
        this.idDiscord = idDiscord;
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

    public String getInfos() {

        return this.nom + ", " + this.classe + ", " + this.race;
    }
}
