package be.vdab.bierhuis.services;

import be.vdab.bierhuis.domain.Bestelbon;
import be.vdab.bierhuis.repositories.BestelBonRepository;
import org.springframework.stereotype.Service;

@Service
public class DefaultBestelBonService implements BestelBonService{
    private final BestelBonRepository bestelBonRepository;

    public DefaultBestelBonService(BestelBonRepository bestelBonRepository) {
        this.bestelBonRepository = bestelBonRepository;
    }

    @Override
    public int insertBestelBon(Bestelbon bestelbon) {
        return bestelBonRepository.insertBestelBon(bestelbon);
    }
}
