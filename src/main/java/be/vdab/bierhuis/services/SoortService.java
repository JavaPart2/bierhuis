package be.vdab.bierhuis.services;

import be.vdab.bierhuis.domain.Brouwer;
import be.vdab.bierhuis.domain.Soort;

import java.util.List;
import java.util.Optional;

public interface SoortService {
    Optional<Soort> findById(int id);
}
