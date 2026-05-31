package ch.allianz.youngoitv.shop.api;

import ch.allianz.youngoitv.shop.api.dto.KundeDto;
import ch.allianz.youngoitv.shop.api.dto.KundeErstellenDto;
import ch.allianz.youngoitv.shop.service.KundeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/kunden")
public class KundeController {

    private final KundeService kundeService;

    public KundeController(KundeService kundeService) {
        this.kundeService = kundeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public KundeDto erstellen(@Valid @RequestBody KundeErstellenDto dto) {
        return kundeService.erstellen(dto);
    }

    @GetMapping
    public List<KundeDto> alle() {
        return kundeService.alle();
    }
}
