package be.vdab.bierhuis.services;

import be.vdab.bierhuis.domain.Bier;
import be.vdab.bierhuis.domain.Brouwer;

import java.util.List;
import java.util.Optional;

public interface BierService {
    int countAll();
    List<Bier> findByBrouwer(Brouwer brouwer);
    Optional<Bier> findById(int id);
    void updateBier(Bier bier);
}
