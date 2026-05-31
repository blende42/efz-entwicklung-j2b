package ch.allianz.youngoitv.shop.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record BestellpositionErstellenDto(
        @NotNull Long produktId,
        @Positive int menge
) {
}
