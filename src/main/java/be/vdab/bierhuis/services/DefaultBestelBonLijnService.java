package be.vdab.bierhuis.services;

import be.vdab.bierhuis.domain.BestelbonLijn;
import be.vdab.bierhuis.repositories.BestelBonLijnRepository;
import org.springframework.stereotype.Service;

@Service
public class DefaultBestelBonLijnService implements BestelBonLijnService{
    private final BestelBonLijnRepository bestelBonLijnRepository;

    public DefaultBestelBonLijnService(BestelBonLijnRepository bestelBonLijnRepository) {
        this.bestelBonLijnRepository = bestelBonLijnRepository;
    }

    @Override
    public void insertBestelBonLijn(BestelbonLijn bestelbonLijn) {
        bestelBonLijnRepository.insertBestelBonLijn(bestelbonLijn);
    }
}
