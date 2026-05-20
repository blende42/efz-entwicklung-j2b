package ch.allianz.youngoitv.lager.api;

import ch.allianz.youngoitv.lager.api.dto.ProduktDto;
import ch.allianz.youngoitv.lager.api.dto.ProduktErstellenDto;
import ch.allianz.youngoitv.lager.domain.Produkt;
import ch.allianz.youngoitv.lager.domain.ProduktStatus;
import ch.allianz.youngoitv.lager.service.ProduktService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/produkte")
public class ProduktController {

    private final ProduktService produktService;

    public ProduktController(ProduktService produktService) {
        this.produktService = produktService;
    }

    @GetMapping
    public List<ProduktDto> alleProdukte() {
        return produktService.alleProdukte().stream()
                .map(this::toDto)
                .toList();
    }

    @GetMapping("/status/{status}")
    public List<ProduktDto> produkteMitStatus(@PathVariable ProduktStatus status) {
        return produktService.produkteMitStatus(status).stream()
                .map(this::toDto)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProduktDto produktErstellen(@RequestBody ProduktErstellenDto dto) {
        Produkt produkt = produktService.produktErstellen(
                dto.name(),
                dto.preis(),
                dto.status()
        );
        return toDto(produkt);
    }

    @PutMapping("/{id}/status/{status}")
    public ProduktDto statusAendern(@PathVariable Long id, @PathVariable ProduktStatus status) {
        Produkt produkt = produktService.statusAendern(id, status);
        return toDto(produkt);
    }

    private ProduktDto toDto(Produkt produkt) {
        return new ProduktDto(
                produkt.getId(),
                produkt.getName(),
                produkt.getPreis(),
                produkt.getStatus()
        );
    }
}
