package ch.allianz.youngoitv.shop.service;

import ch.allianz.youngoitv.shop.api.dto.BestellpositionDto;
import ch.allianz.youngoitv.shop.api.dto.BestellpositionErstellenDto;
import ch.allianz.youngoitv.shop.api.dto.BestellungDto;
import ch.allianz.youngoitv.shop.api.dto.BestellungErstellenDto;
import ch.allianz.youngoitv.shop.domain.Bestellposition;
import ch.allianz.youngoitv.shop.domain.Bestellung;
import ch.allianz.youngoitv.shop.domain.Kunde;
import ch.allianz.youngoitv.shop.domain.Produkt;
import ch.allianz.youngoitv.shop.repository.BestellpositionRepository;
import ch.allianz.youngoitv.shop.repository.BestellungRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BestellungService {

    private final BestellungRepository bestellungRepository;
    private final BestellpositionRepository bestellpositionRepository;
    private final KundeService kundeService;
    private final ProduktService produktService;

    public BestellungService(
            BestellungRepository bestellungRepository,
            BestellpositionRepository bestellpositionRepository,
            KundeService kundeService,
            ProduktService produktService
    ) {
        this.bestellungRepository = bestellungRepository;
        this.bestellpositionRepository = bestellpositionRepository;
        this.kundeService = kundeService;
        this.produktService = produktService;
    }

    @Transactional
    public BestellungDto erstellen(BestellungErstellenDto dto) {
        Kunde kunde = kundeService.ladeEntity(dto.kundeId());
        List<Produkt> produkte = ladeUndPruefeProdukte(dto.positionen());

        Bestellung bestellung = bestellungRepository.save(
                new Bestellung(kunde.getId(), kunde.getName(), LocalDateTime.now())
        );

        List<Bestellposition> positionen = new ArrayList<>();
        for (int i = 0; i < dto.positionen().size(); i++) {
            BestellpositionErstellenDto positionsDto = dto.positionen().get(i);
            Produkt produkt = produkte.get(i);
            produkt.reduziereBestand(positionsDto.menge());
            produktService.speichern(produkt);

            positionen.add(new Bestellposition(
                    bestellung.getId(),
                    produkt.getId(),
                    produkt.getName(),
                    positionsDto.menge(),
                    produkt.getPreis()
            ));
        }

        bestellpositionRepository.saveAll(positionen);
        return toDto(bestellung, positionen);
    }

    public BestellungDto nachId(Long id) {
        Bestellung bestellung = bestellungRepository.findById(id)
                .orElseThrow(() -> new RessourceNichtGefundenException("Bestellung nicht gefunden"));
        List<Bestellposition> positionen = bestellpositionRepository.findByBestellungId(id);
        return toDto(bestellung, positionen);
    }

    private List<Produkt> ladeUndPruefeProdukte(List<BestellpositionErstellenDto> positionen) {
        List<Produkt> produkte = new ArrayList<>();
        Map<Long, Integer> mengenProProdukt = new HashMap<>();
        for (BestellpositionErstellenDto position : positionen) {
            Produkt produkt = produktService.ladeEntity(position.produktId());
            int benoetigteMenge = mengenProProdukt.merge(produkt.getId(), position.menge(), Integer::sum);
            if (!produkt.hatGenugBestand(benoetigteMenge)) {
                throw new BestandNichtAusreichendException("Bestand reicht nicht aus");
            }
            produkte.add(produkt);
        }
        return produkte;
    }

    private BestellungDto toDto(Bestellung bestellung, List<Bestellposition> positionen) {
        return new BestellungDto(
                bestellung.getId(),
                bestellung.getKundeId(),
                bestellung.getKundenName(),
                bestellung.getBestelldatum(),
                positionen.stream().map(this::toDto).toList()
        );
    }

    private BestellpositionDto toDto(Bestellposition position) {
        return new BestellpositionDto(
                position.getProduktId(),
                position.getProduktName(),
                position.getMenge(),
                position.getEinzelpreis()
        );
    }
}
