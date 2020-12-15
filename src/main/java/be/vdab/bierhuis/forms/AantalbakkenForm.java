package be.vdab.bierhuis.forms;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AantalbakkenForm {

    private int aantalBakken;

    public AantalbakkenForm() {
    }

    public AantalbakkenForm(int aantalBakken) {
        this.aantalBakken = aantalBakken;
    }

    public void setAantalBakken(int aantalBakken) {
        this.aantalBakken = aantalBakken;
    }

    public int getAantalBakken() {
        return aantalBakken;
    }
}
