package be.vdab.bierhuis.forms;

import java.math.BigDecimal;

public class BestelLijnForm {

    private int bierId;
    private String bierNaam;
    private BigDecimal bierPrijs;
    private int aantalBakken;

    public BestelLijnForm() {
    }

    public int getBierId() {
        return bierId;
    }

    public void setBierId(int bierId) {
        this.bierId = bierId;
    }

    public String getBierNaam() {
        return bierNaam;
    }

    public void setBierNaam(String bierNaam) {
        this.bierNaam = bierNaam;
    }

    public BigDecimal getBierPrijs() {
        return bierPrijs;
    }

    public void setBierPrijs(BigDecimal bierPrijs) {
        this.bierPrijs = bierPrijs;
    }

    public int getAantalBakken() {
        return aantalBakken;
    }

    public void setAantalBakken(int aantalBakken) {
        this.aantalBakken = aantalBakken;
    }
}
