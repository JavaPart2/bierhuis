package be.vdab.bierhuis.services;

import be.vdab.bierhuis.domain.Bestelbon;
import be.vdab.bierhuis.domain.BestelbonLijn;
import be.vdab.bierhuis.domain.Bier;
import be.vdab.bierhuis.domain.Brouwer;
import be.vdab.bierhuis.forms.BestelLijnForm;
import be.vdab.bierhuis.forms.MandjeForm;
import be.vdab.bierhuis.repositories.BierRepository;
import be.vdab.bierhuis.sessions.Mandje;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DefaultBierService implements BierService{
    private final BierRepository bierRepository;
    private final BestelBonService bestelBonService;
    private final BestelBonLijnService bestelBonLijnService;

    public DefaultBierService(BierRepository bierRepository,
                              BestelBonService bestelBonService,
                              BestelBonLijnService bestelBonLijnService) {
        this.bierRepository = bierRepository;
        this.bestelBonService = bestelBonService;
        this.bestelBonLijnService = bestelBonLijnService;
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
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void updateBier(Bier bier) {
        bierRepository.updateBier(bier);
    }

    @Override
    public MandjeForm composeBestelLijstForm(Mandje mandje) {
        MandjeForm mandjeForm = new MandjeForm();
        List<BestelLijnForm> bestelLijnFormList = new ArrayList<>();
        BigDecimal bestelBonPrijsTotaal = new BigDecimal(0);

        for (Map.Entry<Integer, Integer> i : mandje.getBestelLijnen().entrySet()){
            BestelLijnForm bestelLijnForm = new BestelLijnForm();
            bierRepository.findById(i.getKey()).ifPresent(bier -> {
                bestelLijnForm.setBierId(i.getKey());
                bestelLijnForm.setBierNaam(bier.getNaam());
                bestelLijnForm.setBierPrijs(bier.getPrijs());
            });
            bestelLijnForm.setAantalBakken(i.getValue());
            bestelLijnForm.setLijnTotaal(
                    bestelLijnForm.getBierPrijs(),
                    i.getValue()
            );
            bestelBonPrijsTotaal = bestelBonPrijsTotaal.add(bestelLijnForm.getLijnTotaal());
            bestelLijnFormList.add(bestelLijnForm);
        }
        mandjeForm.setBestelLijnen(bestelLijnFormList);
        mandjeForm.setTotaal(bestelBonPrijsTotaal);
        return mandjeForm;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public int insertBestelling(MandjeForm form, Mandje mandje) {
        Bestelbon bestelbon = new Bestelbon(
                0,
                form.getNaam(),
                form.getStraat(),
                form.getHuisNr(),
                form.getPostcode(),
                form.getGemeente());

        int bestelbonid = bestelBonService.insertBestelBon(bestelbon);

        for (Map.Entry<Integer, Integer> i : mandje.getBestelLijnen().entrySet()){
            BestelbonLijn bestelbonLijn = new BestelbonLijn(
                    bestelbonid,
                    i.getKey(),
                    i.getValue(),
                    new BigDecimal(0)
            );
            int aantalBakkenBier = i.getValue();
            bierRepository.findById(i.getKey()).ifPresent(bier ->
                bestelbonLijn.setPrijs(bier.getPrijs()
                        .multiply(BigDecimal.valueOf(aantalBakkenBier))));
            bestelBonLijnService.insertBestelBonLijn(bestelbonLijn);
            // besteld bier verhogen
            bierRepository.findById(i.getKey()).ifPresent(bier -> {
                        bier.setBesteld(bier.getBesteld() + aantalBakkenBier);
                        bierRepository.updateBier(bier);
                    });
        }
        return bestelbonid;
    }
}
