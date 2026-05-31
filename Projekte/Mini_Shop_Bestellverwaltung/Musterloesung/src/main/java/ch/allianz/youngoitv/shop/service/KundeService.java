package ch.allianz.youngoitv.shop.service;

import ch.allianz.youngoitv.shop.api.dto.KundeDto;
import ch.allianz.youngoitv.shop.api.dto.KundeErstellenDto;
import ch.allianz.youngoitv.shop.domain.Kunde;
import ch.allianz.youngoitv.shop.repository.KundeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KundeService {

    private final KundeRepository kundeRepository;

    public KundeService(KundeRepository kundeRepository) {
        this.kundeRepository = kundeRepository;
    }

    public KundeDto erstellen(KundeErstellenDto dto) {
        Kunde kunde = kundeRepository.save(new Kunde(dto.name(), dto.email()));
        return toDto(kunde);
    }

    public List<KundeDto> alle() {
        return kundeRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public Kunde ladeEntity(Long id) {
        return kundeRepository.findById(id)
                .orElseThrow(() -> new RessourceNichtGefundenException("Kunde nicht gefunden"));
    }

    private KundeDto toDto(Kunde kunde) {
        return new KundeDto(kunde.getId(), kunde.getName(), kunde.getEmail());
    }
}
