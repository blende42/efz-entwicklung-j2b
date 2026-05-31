package ch.allianz.youngoitv.shop.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record ProduktErstellenDto(
        @NotBlank String name,
        @Positive double preis,
        @PositiveOrZero int bestand,
        @NotNull Long kategorieId
) {
}
