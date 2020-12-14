package be.vdab.bierhuis.domain;

import java.math.BigDecimal;

public class BestelbonLijn {
    private final Bestelbon bestelbon;
    private final Bier bier;
    private int aantal;
    private BigDecimal prijs;

    public BestelbonLijn(Bestelbon bestelbon, Bier bier, int aantal, BigDecimal prijs) {
        this.bestelbon = bestelbon;
        this.bier = bier;
        this.aantal = aantal;
        this.prijs = prijs;
    }

    public Bestelbon getBestelbon() {
        return bestelbon;
    }

    public Bier getBier() {
        return bier;
    }

    public int getAantal() {
        return aantal;
    }

    public void setAantal(int aantal) {
        this.aantal = aantal;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public void setPrijs(BigDecimal prijs) {
        this.prijs = prijs;
    }
}
