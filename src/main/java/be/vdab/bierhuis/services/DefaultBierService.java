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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
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
    public void updateBier(Bier bier) {
        bierRepository.updateBier(bier);
    }

    @Override
    public MandjeForm composeBestelLijstForm(Mandje mandje) {
        MandjeForm mandjeForm = new MandjeForm();
        List<BestelLijnForm> bestelLijnFormList = new ArrayList<>();
        BigDecimal bestelBonPrijsTotaal = new BigDecimal(0);

        for (int i = 0; i < mandje.getBierIds().size(); i++) {
            BestelLijnForm bestelLijnForm = new BestelLijnForm();

            bierRepository.findById(mandje.getBierIds().get(i)).ifPresent(bier -> {
                bestelLijnForm.setBierId(bier.getId());
                bestelLijnForm.setBierNaam(bier.getNaam());
                bestelLijnForm.setBierPrijs(bier.getPrijs());

            });
            bestelLijnForm.setAantalBakken(mandje.getBakken().get(i));
            bestelLijnForm.setLijnTotaal(
                    bestelLijnForm.getBierPrijs(),
                    mandje.getBakken().get(i)
            );
            bestelBonPrijsTotaal = bestelBonPrijsTotaal.add(bestelLijnForm.getLijnTotaal());
            bestelLijnFormList.add(bestelLijnForm);
        }
        mandjeForm.setBestelLijnen(bestelLijnFormList);
        mandjeForm.setTotaal(bestelBonPrijsTotaal);
        return mandjeForm;
    }

    @Override
    public int insertBestelling(MandjeForm form) {
        Bestelbon bestelbon = new Bestelbon(
                0,
                form.getNaam(),
                form.getStraat(),
                form.getHuisNr(),
                form.getPostcode(),
                form.getGemeente());

        int bestelbonid = bestelBonService.insertBestelBon(bestelbon);

        for (int i = 0; i < form.getBestelLijnen().size(); i++) {
            BestelbonLijn bestelbonLijn = new BestelbonLijn(
                    bestelbonid,
                    form.getBestelLijnen().get(i).getBierId(),
                    form.getBestelLijnen().get(i).getAantalBakken(),
                    form.getBestelLijnen().get(i).getBierPrijs()
            );
            bestelBonLijnService.insertBestelBonLijn(bestelbonLijn);
            // besteld bier verhogen
            int aantalBakkenBier = form.getBestelLijnen().get(i).getAantalBakken();
            bierRepository.findById(form.getBestelLijnen().get(i).getBierId())
                .ifPresent(bier -> {
                    bier.setBesteld(bier.getBesteld() + aantalBakkenBier);
                    bierRepository.updateBier(bier);
                });
        }

        return bestelbonid;
    }
}
