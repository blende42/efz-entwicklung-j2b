package ch.allianz.youngoitv.lager.api.dto;

import ch.allianz.youngoitv.lager.domain.ProduktStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record ProduktErstellenDto(
        @NotBlank
        String name,

        @PositiveOrZero
        double preis,

        @NotNull
        ProduktStatus status
) {
}
