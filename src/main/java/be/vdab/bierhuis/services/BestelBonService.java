package be.vdab.bierhuis.services;

import be.vdab.bierhuis.domain.Bestelbon;
import be.vdab.bierhuis.domain.Brouwer;

import java.util.List;
import java.util.Optional;

public interface BestelBonService {
    int insertBestelBon(Bestelbon bestelbon);
}
