package ch.allianz.youngoitv.shop.api;

import ch.allianz.youngoitv.shop.api.dto.KategorieDto;
import ch.allianz.youngoitv.shop.api.dto.KategorieErstellenDto;
import ch.allianz.youngoitv.shop.service.KategorieService;
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
@RequestMapping("/kategorien")
public class KategorieController {

    private final KategorieService kategorieService;

    public KategorieController(KategorieService kategorieService) {
        this.kategorieService = kategorieService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public KategorieDto erstellen(@Valid @RequestBody KategorieErstellenDto dto) {
        return kategorieService.erstellen(dto);
    }

    @GetMapping
    public List<KategorieDto> alle() {
        return kategorieService.alle();
    }
}
