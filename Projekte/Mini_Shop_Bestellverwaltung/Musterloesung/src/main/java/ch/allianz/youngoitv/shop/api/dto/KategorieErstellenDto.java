package ch.allianz.youngoitv.shop.api.dto;

import jakarta.validation.constraints.NotBlank;

public record KategorieErstellenDto(
        @NotBlank String name
) {
}
