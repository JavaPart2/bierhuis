package be.vdab.bierhuis.sessions;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.*;

@Component
@SessionScope
public class Mandje implements Serializable {
    private static final int serialVersionUID = 1;
    private final List<Integer> bierIds = new ArrayList<>();
    private List<Integer> bakken = new ArrayList<>();
    private final Map<Integer,Integer> bestelLijnen = new LinkedHashMap<>();

    public Mandje() {
    }

    public Map<Integer, Integer> getBestelLijnen() {
        return bestelLijnen;
    }

    public List<Integer> getBierIds() {
        return bierIds;
    }

    public List<Integer> getBakken() {
        return bakken;
    }

    public void setBakken(List<Integer> bakken) {
        this.bakken = bakken;
    }

    public void voegToe(int id, int bakken){
        if (bierIds.contains(id)){
            int index = bierIds.indexOf(id);
            int aantalBakken = this.bakken.get(index) + bakken;
            this.bakken.set(index,aantalBakken);
        }else {
            this.bierIds.add(id);
            this.bakken.add(bakken);
        }
    }

    public void leegMaken() {
        this.bierIds.clear();
        this.bakken.clear();

    }
}
