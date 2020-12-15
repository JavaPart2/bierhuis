package be.vdab.bierhuis.forms;

import java.util.List;

public class MandjeForm {

    private List<BestelLijnForm> bestelLijnen;
    private String naam;
    private String straat;
    private String huisNr;
    private int postcode;
    private String gemeente;

    public MandjeForm() {
    }

    public List<BestelLijnForm> getBestelLijnen() {
        return bestelLijnen;
    }

    public void setBestelLijnen(List<BestelLijnForm> bestelLijnen) {
        this.bestelLijnen = bestelLijnen;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getHuisNr() {
        return huisNr;
    }

    public void setHuisNr(String huisNr) {
        this.huisNr = huisNr;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public String getGemeente() {
        return gemeente;
    }

    public void setGemeente(String gemeente) {
        this.gemeente = gemeente;
    }
}
