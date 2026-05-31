package ch.allianz.youngoitv.shop.service;

import ch.allianz.youngoitv.shop.api.dto.KategorieDto;
import ch.allianz.youngoitv.shop.api.dto.KategorieErstellenDto;
import ch.allianz.youngoitv.shop.domain.Kategorie;
import ch.allianz.youngoitv.shop.repository.KategorieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KategorieService {

    private final KategorieRepository kategorieRepository;

    public KategorieService(KategorieRepository kategorieRepository) {
        this.kategorieRepository = kategorieRepository;
    }

    public KategorieDto erstellen(KategorieErstellenDto dto) {
        Kategorie kategorie = kategorieRepository.save(new Kategorie(dto.name()));
        return toDto(kategorie);
    }

    public List<KategorieDto> alle() {
        return kategorieRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public Kategorie ladeEntity(Long id) {
        return kategorieRepository.findById(id)
                .orElseThrow(() -> new RessourceNichtGefundenException("Kategorie nicht gefunden"));
    }

    private KategorieDto toDto(Kategorie kategorie) {
        return new KategorieDto(kategorie.getId(), kategorie.getName());
    }
}
