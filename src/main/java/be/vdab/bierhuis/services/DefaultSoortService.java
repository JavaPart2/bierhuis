package be.vdab.bierhuis.services;

import be.vdab.bierhuis.domain.Soort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultSoortService implements SoortService{
    private final SoortService soortService;

    public DefaultSoortService(SoortService soortService) {
        this.soortService = soortService;
    }

    @Override
    public Optional<Soort> findById(int id) {
        return soortService.findById(id);
    }
}
