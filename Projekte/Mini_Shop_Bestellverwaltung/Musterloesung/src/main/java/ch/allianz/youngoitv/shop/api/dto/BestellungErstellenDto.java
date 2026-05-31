package ch.allianz.youngoitv.shop.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record BestellungErstellenDto(
        @NotNull Long kundeId,
        @NotEmpty List<@Valid BestellpositionErstellenDto> positionen
) {
}
