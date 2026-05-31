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
        return produktRepository.findAll();
    }

    public List<Produkt> produkteMitStatus(ProduktStatus status) {
        return produktRepository.findByStatus(status);
    }

    public Produkt produktNachId(Long id) {
        return produktRepository.findById(id)
                .orElseThrow(() -> new ProduktNichtGefundenException(id));
    }

    public Produkt produktErstellen(String name, double preis, ProduktStatus status) {
        Produkt produkt = new Produkt(name, preis, status);
        return produktRepository.save(produkt);
    }

    public Produkt statusAendern(Long id, ProduktStatus status) {
        Produkt produkt = produktNachId(id);
        produkt.setStatus(status);
        return produktRepository.save(produkt);
    }
}
