package ch.allianz.youngoitv.shop.api;

import ch.allianz.youngoitv.shop.api.dto.ProduktDto;
import ch.allianz.youngoitv.shop.api.dto.ProduktErstellenDto;
import ch.allianz.youngoitv.shop.service.ProduktService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProduktDto erstellen(@Valid @RequestBody ProduktErstellenDto dto) {
        return produktService.erstellen(dto);
    }

    @GetMapping
    public List<ProduktDto> alle() {
        return produktService.alle();
    }

    @GetMapping("/{id}")
    public ProduktDto nachId(@PathVariable Long id) {
        return produktService.nachId(id);
    }
}
