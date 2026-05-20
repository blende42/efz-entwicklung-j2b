package ch.allianz.youngoitv.lager.service;

import ch.allianz.youngoitv.lager.domain.Produkt;
import ch.allianz.youngoitv.lager.domain.ProduktStatus;
import ch.allianz.youngoitv.lager.repository.ProduktRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProduktServiceTest {

    @Test
    void filtertProdukteNachStatus() {
        ProduktRepository repository = new ProduktRepository();
        repository.speichern(new Produkt(null, "Tastatur", 49.90, ProduktStatus.AKTIV));
        repository.speichern(new Produkt(null, "Maus", 24.90, ProduktStatus.RESERVIERT));

        ProduktService service = new ProduktService(repository);

        List<Produkt> aktiveProdukte = service.produkteMitStatus(ProduktStatus.AKTIV);

        assertEquals(1, aktiveProdukte.size());
        assertEquals("Tastatur", aktiveProdukte.getFirst().getName());
    }

    @Test
    void aendertStatusEinesProdukts() {
        ProduktRepository repository = new ProduktRepository();
        Produkt gespeichertesProdukt = repository.speichern(
                new Produkt(null, "Monitor", 179.00, ProduktStatus.AKTIV)
        );
        ProduktService service = new ProduktService(repository);

        Produkt geaendertesProdukt = service.statusAendern(
                gespeichertesProdukt.getId(),
                ProduktStatus.ARCHIVIERT
        );

        assertEquals(ProduktStatus.ARCHIVIERT, geaendertesProdukt.getStatus());
    }
}
