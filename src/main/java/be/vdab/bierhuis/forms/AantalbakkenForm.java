package be.vdab.bierhuis.forms;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AantalbakkenForm {

    private int aantalBakken;

    public AantalbakkenForm() {
    }

    public int getAantalBakken() {
        return aantalBakken;
    }
}
