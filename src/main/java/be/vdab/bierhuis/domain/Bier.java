package be.vdab.bierhuis.domain;

import java.math.BigDecimal;

public class Bier {
    private final int id;
    private final String naam;
    private final int brouwerid;
    private final int soortid;
    private final BigDecimal alcohol;
    private final BigDecimal prijs;
    private int besteld;

    public Bier(int id, String naam, int brouwer, int soort, BigDecimal alcohol, BigDecimal prijs, int besteld) {
        this.id = id;
        this.naam = naam;
        this.brouwerid = brouwer;
        this.soortid = soort;
        this.alcohol = alcohol;
        this.prijs = prijs;
        this.besteld = besteld;
    }

    public int getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public int getBrouwerid() {
        return brouwerid;
    }

    public int getSoortid() {
        return soortid;
    }

    public BigDecimal getAlcohol() {
        return alcohol;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public int getBesteld() {
        return besteld;
    }

    public void setBesteld(int besteld) {
        this.besteld = besteld;
    }
}
