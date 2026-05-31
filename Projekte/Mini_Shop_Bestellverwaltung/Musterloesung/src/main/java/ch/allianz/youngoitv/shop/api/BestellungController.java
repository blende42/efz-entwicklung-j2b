package ch.allianz.youngoitv.shop.api;

import ch.allianz.youngoitv.shop.api.dto.BestellungDto;
import ch.allianz.youngoitv.shop.api.dto.BestellungErstellenDto;
import ch.allianz.youngoitv.shop.service.BestellungService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bestellungen")
public class BestellungController {

    private final BestellungService bestellungService;

    public BestellungController(BestellungService bestellungService) {
        this.bestellungService = bestellungService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BestellungDto erstellen(@Valid @RequestBody BestellungErstellenDto dto) {
        return bestellungService.erstellen(dto);
    }

    @GetMapping("/{id}")
    public BestellungDto nachId(@PathVariable Long id) {
        return bestellungService.nachId(id);
    }
}
