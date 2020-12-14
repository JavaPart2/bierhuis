package be.vdab.bierhuis.domain;

import java.math.BigDecimal;

public class Bier {
    private final int id;
    private final String naam;
    private final Brouwer brouwer;
    private final Soort soort;
    private final BigDecimal alcohol;
    private final BigDecimal prijs;
    private int besteld;

    public Bier(int id, String naam, Brouwer brouwer, Soort soort, BigDecimal alcohol, BigDecimal prijs, int besteld) {
        this.id = id;
        this.naam = naam;
        this.brouwer = brouwer;
        this.soort = soort;
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

    public Brouwer getBrouwer() {
        return brouwer;
    }

    public Soort getSoort() {
        return soort;
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
