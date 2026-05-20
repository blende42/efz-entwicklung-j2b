package ch.allianz.youngoitv.lager.repository;

import ch.allianz.youngoitv.lager.domain.Produkt;
import ch.allianz.youngoitv.lager.domain.ProduktStatus;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProduktRepository {

    private final List<Produkt> produkte = new ArrayList<>();
    private final AtomicLong naechsteId = new AtomicLong(1);

    @PostConstruct
    public void beispieldatenAnlegen() {
        speichern(new Produkt(null, "Tastatur", 49.90, ProduktStatus.AKTIV));
        speichern(new Produkt(null, "Maus", 24.90, ProduktStatus.RESERVIERT));
        speichern(new Produkt(null, "Monitor", 179.00, ProduktStatus.AKTIV));
        speichern(new Produkt(null, "Defektes Headset", 15.00, ProduktStatus.DEFEKT));
        speichern(new Produkt(null, "Altes Notebook", 250.00, ProduktStatus.ARCHIVIERT));
    }

    public List<Produkt> alle() {
        return new ArrayList<>(produkte);
    }

    public Optional<Produkt> findeNachId(Long id) {
        return produkte.stream()
                .filter(produkt -> produkt.getId().equals(id))
                .findFirst();
    }

    public Produkt speichern(Produkt produkt) {
        Produkt gespeichertesProdukt = new Produkt(
                naechsteId.getAndIncrement(),
                produkt.getName(),
                produkt.getPreis(),
                produkt.getStatus()
        );
        produkte.add(gespeichertesProdukt);
        return gespeichertesProdukt;
    }
}
