package be.vdab.bierhuis.domain;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

public class BestelbonLijn {
    private final int bestelbon;
    private final int bier;
    @Min(2)
    private int aantal;
    private BigDecimal prijs;

    public BestelbonLijn(int bestelbon, int bier, int aantal, BigDecimal prijs) {
        this.bestelbon = bestelbon;
        this.bier = bier;
        this.aantal = aantal;
        this.prijs = prijs;
    }

    public int getBestelbon() {
        return bestelbon;
    }

    public int getBier() {
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
