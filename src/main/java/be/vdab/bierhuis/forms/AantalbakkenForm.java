package be.vdab.bierhuis.forms;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class AantalbakkenForm {

    @Min(2)
    private final int aantalBakken;

    public AantalbakkenForm(int aantalBakken) {
        this.aantalBakken = aantalBakken;
    }

    public int getAantalBakken() {
        return aantalBakken;
    }
}
