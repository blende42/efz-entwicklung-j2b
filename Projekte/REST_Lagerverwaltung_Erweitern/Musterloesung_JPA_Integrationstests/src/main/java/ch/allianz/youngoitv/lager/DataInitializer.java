package ch.allianz.youngoitv.lager;

import ch.allianz.youngoitv.lager.domain.Produkt;
import ch.allianz.youngoitv.lager.domain.ProduktStatus;
import ch.allianz.youngoitv.lager.repository.ProduktRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ProduktRepository produktRepository;

    public DataInitializer(ProduktRepository produktRepository) {
        this.produktRepository = produktRepository;
    }

    @Override
    public void run(String... args) {
        if (produktRepository.count() > 0) {
            return;
        }

        produktRepository.save(new Produkt("Tastatur", 49.90, ProduktStatus.AKTIV));
        produktRepository.save(new Produkt("Maus", 24.90, ProduktStatus.RESERVIERT));
        produktRepository.save(new Produkt("Monitor", 179.00, ProduktStatus.AKTIV));
        produktRepository.save(new Produkt("Defektes Headset", 15.00, ProduktStatus.DEFEKT));
        produktRepository.save(new Produkt("Altes Notebook", 250.00, ProduktStatus.ARCHIVIERT));
    }
}
