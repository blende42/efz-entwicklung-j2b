package ch.allianz.youngoitv.shop;

import ch.allianz.youngoitv.shop.domain.Kategorie;
import ch.allianz.youngoitv.shop.domain.Kunde;
import ch.allianz.youngoitv.shop.domain.Produkt;
import ch.allianz.youngoitv.shop.repository.KategorieRepository;
import ch.allianz.youngoitv.shop.repository.KundeRepository;
import ch.allianz.youngoitv.shop.repository.ProduktRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final KategorieRepository kategorieRepository;
    private final ProduktRepository produktRepository;
    private final KundeRepository kundeRepository;

    public DataInitializer(
            KategorieRepository kategorieRepository,
            ProduktRepository produktRepository,
            KundeRepository kundeRepository
    ) {
        this.kategorieRepository = kategorieRepository;
        this.produktRepository = produktRepository;
        this.kundeRepository = kundeRepository;
    }

    @Override
    public void run(String... args) {
        Kategorie hardware = kategorieRepository.save(new Kategorie("Hardware"));
        Kategorie zubehoer = kategorieRepository.save(new Kategorie("Zubehoer"));

        produktRepository.save(new Produkt("Tastatur", 49.90, 10, hardware.getId()));
        produktRepository.save(new Produkt("Maus", 24.90, 15, zubehoer.getId()));
        produktRepository.save(new Produkt("Monitor", 179.00, 5, hardware.getId()));

        kundeRepository.save(new Kunde("Ada Lovelace", "ada@example.com"));
        kundeRepository.save(new Kunde("Grace Hopper", "grace@example.com"));
    }
}
