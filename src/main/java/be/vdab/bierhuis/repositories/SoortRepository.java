package be.vdab.bierhuis.repositories;

import be.vdab.bierhuis.domain.Brouwer;
import be.vdab.bierhuis.domain.Soort;

import java.util.List;
import java.util.Optional;

public interface SoortRepository {
    Optional<Soort> findById(int id);
}
