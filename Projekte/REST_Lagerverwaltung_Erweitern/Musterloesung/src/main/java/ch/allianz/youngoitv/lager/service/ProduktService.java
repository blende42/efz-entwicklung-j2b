package ch.allianz.youngoitv.lager.service;

import ch.allianz.youngoitv.lager.domain.Produkt;
import ch.allianz.youngoitv.lager.domain.ProduktStatus;
import ch.allianz.youngoitv.lager.repository.ProduktRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduktService {

    private final ProduktRepository produktRepository;

    public ProduktService(ProduktRepository produktRepository) {
        this.produktRepository = produktRepository;
    }

    public List<Produkt> alleProdukte() {
        return produktRepository.alle();
    }

    public List<Produkt> produkteMitStatus(ProduktStatus status) {
        return produktRepository.alle().stream()
                .filter(produkt -> produkt.getStatus() == status)
                .toList();
    }

    public Produkt produktErstellen(String name, double preis, ProduktStatus status) {
        Produkt produkt = new Produkt(null, name, preis, status);
        return produktRepository.speichern(produkt);
    }

    public Produkt statusAendern(Long id, ProduktStatus status) {
        Produkt produkt = produktRepository.findeNachId(id)
                .orElseThrow(() -> new IllegalArgumentException("Produkt nicht gefunden: " + id));
        produkt.setStatus(status);
        return produkt;
    }
}
