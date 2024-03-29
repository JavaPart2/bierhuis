package be.vdab.bierhuis.services;

import be.vdab.bierhuis.domain.Soort;
import be.vdab.bierhuis.repositories.SoortRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DefaultSoortService implements SoortService{
    private final SoortRepository soortRepository;

    public DefaultSoortService(SoortRepository soortRepository) {
        this.soortRepository = soortRepository;
    }

    @Override
    public Optional<Soort> findById(int id) {
        return soortRepository.findById(id);
    }
}
