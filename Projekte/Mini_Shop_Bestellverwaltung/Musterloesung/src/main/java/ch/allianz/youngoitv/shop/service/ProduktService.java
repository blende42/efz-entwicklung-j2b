package ch.allianz.youngoitv.shop.service;

import ch.allianz.youngoitv.shop.api.dto.ProduktDto;
import ch.allianz.youngoitv.shop.api.dto.ProduktErstellenDto;
import ch.allianz.youngoitv.shop.domain.Produkt;
import ch.allianz.youngoitv.shop.repository.ProduktRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduktService {

    private final ProduktRepository produktRepository;
    private final KategorieService kategorieService;

    public ProduktService(ProduktRepository produktRepository, KategorieService kategorieService) {
        this.produktRepository = produktRepository;
        this.kategorieService = kategorieService;
    }

    public ProduktDto erstellen(ProduktErstellenDto dto) {
        kategorieService.ladeEntity(dto.kategorieId());
        Produkt produkt = produktRepository.save(
                new Produkt(dto.name(), dto.preis(), dto.bestand(), dto.kategorieId())
        );
        return toDto(produkt);
    }

    public List<ProduktDto> alle() {
        return produktRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public ProduktDto nachId(Long id) {
        return toDto(ladeEntity(id));
    }

    public Produkt ladeEntity(Long id) {
        return produktRepository.findById(id)
                .orElseThrow(() -> new RessourceNichtGefundenException("Produkt nicht gefunden"));
    }

    public Produkt speichern(Produkt produkt) {
        return produktRepository.save(produkt);
    }

    private ProduktDto toDto(Produkt produkt) {
        return new ProduktDto(
                produkt.getId(),
                produkt.getName(),
                produkt.getPreis(),
                produkt.getBestand(),
                produkt.getKategorieId()
        );
    }
}
