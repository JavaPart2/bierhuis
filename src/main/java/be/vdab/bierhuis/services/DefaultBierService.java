package be.vdab.bierhuis.services;

import be.vdab.bierhuis.domain.Bier;
import be.vdab.bierhuis.domain.Brouwer;
import be.vdab.bierhuis.forms.BestelLijnForm;
import be.vdab.bierhuis.forms.BestelLijstForm;
import be.vdab.bierhuis.forms.MandjeForm;
import be.vdab.bierhuis.repositories.BierRepository;
import be.vdab.bierhuis.sessions.Mandje;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public List<BestelLijnForm> composeBestelLijstForm(Mandje mandje) {
        List<BestelLijnForm> bestelLijnFormList = new ArrayList<>();
        BestelLijnForm bestelLijnForm = new BestelLijnForm();

        for (int i = 0; i < mandje.getBierIds().size(); i++) {
            bierRepository.findById(mandje.getBierIds().get(i))
                    .ifPresent(bier -> {
                        bestelLijnForm.setBierId(bier.getId());
                        bestelLijnForm.setBierNaam(bier.getNaam());
                        bestelLijnForm.setBierPrijs(bier.getPrijs());
                    });
            bestelLijnForm.setAantalBakken(mandje.getBakken().get(i));
            bestelLijnFormList.add(bestelLijnForm);
        }
        return bestelLijnFormList;
    }
}
