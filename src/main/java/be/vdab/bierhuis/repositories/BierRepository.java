package be.vdab.bierhuis.repositories;

import be.vdab.bierhuis.domain.Bier;
import be.vdab.bierhuis.domain.Brouwer;

import java.util.List;
import java.util.Optional;

public interface BierRepository {
    int countAll();
    List<Bier> findByBrouwer(Brouwer brouwer);
    Optional<Bier> findById(int id);
    void updateBier(Bier bier);
}
