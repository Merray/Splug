package fr.jim.entites;

public class MembreGriffon {

    private int id;
    private long idDiscord;
    private String nom;

    public MembreGriffon() {
    }

    public MembreGriffon(int id, long idDiscord, String nom) {
        this.id = id;
        this.idDiscord = idDiscord;
        this.nom = nom;
    }

    public MembreGriffon(long idDiscord, String nom) {
        this.idDiscord = idDiscord;
        this.nom = nom;
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
}
