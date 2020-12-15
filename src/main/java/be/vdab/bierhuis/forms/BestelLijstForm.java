package be.vdab.bierhuis.forms;

import java.util.List;

public class BestelLijstForm {

    private List<BestelLijnForm> bestelLijnen;

    public BestelLijstForm() {
    }

    public List<BestelLijnForm> getBestelLijnen() {
        return bestelLijnen;
    }

    public void setBestelLijnen(List<BestelLijnForm> bestelLijnen) {
        this.bestelLijnen = bestelLijnen;
    }

}
