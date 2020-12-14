package be.vdab.bierhuis.services;

import be.vdab.bierhuis.domain.Bier;
import be.vdab.bierhuis.domain.Brouwer;
import be.vdab.bierhuis.repositories.BierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultBierService implements BierService{
    private final BierRepository bierRepository;

    public DefaultBierService(BierRepository bierRepository) {
        this.bierRepository = bierRepository;
    }

    @Override
    public int countAll() {
        return bierRepository.countAll();
    }

    @Override
    public List<Bier> findByBrouwer(Brouwer brouwer) {
        return bierRepository.findByBrouwer(brouwer);
    }

    @Override
    public Optional<Bier> findById(int id) {
        return bierRepository.findById(id);
    }

    @Override
    public void updateBier(Bier bier) {
        bierRepository.updateBier(bier);
    }
}
