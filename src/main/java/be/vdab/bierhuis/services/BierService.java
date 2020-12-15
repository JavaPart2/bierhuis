package be.vdab.bierhuis.services;

import be.vdab.bierhuis.domain.Bier;
import be.vdab.bierhuis.domain.Brouwer;
import be.vdab.bierhuis.forms.BestelLijnForm;
import be.vdab.bierhuis.forms.BestelLijstForm;
import be.vdab.bierhuis.forms.MandjeForm;
import be.vdab.bierhuis.sessions.Mandje;

import java.util.List;
import java.util.Optional;

public interface BierService {
    int countAll();
    List<Bier> findByBrouwer(Brouwer brouwer);
    Optional<Bier> findById(int id);
    void updateBier(Bier bier);
    MandjeForm composeBestelLijstForm(Mandje mandje);
    int insertBestelling(MandjeForm form);
}
