package be.vdab.bierhuis.domain;

public class Soort {
    private final int id;
    private final String naam;

    public Soort(int id, String naam) {
        this.id = id;
        this.naam = naam;
    }

    public int getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
}
