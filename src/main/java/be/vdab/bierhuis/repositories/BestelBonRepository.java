package be.vdab.bierhuis.repositories;

import be.vdab.bierhuis.domain.Bestelbon;
import be.vdab.bierhuis.domain.Brouwer;

import java.util.List;
import java.util.Optional;

public interface BestelBonRepository {
    int insertBestelBon(Bestelbon bestelbon);
}
