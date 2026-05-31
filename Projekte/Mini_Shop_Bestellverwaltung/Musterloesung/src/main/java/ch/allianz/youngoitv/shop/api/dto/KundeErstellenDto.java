package ch.allianz.youngoitv.shop.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record KundeErstellenDto(
        @NotBlank String name,
        @NotBlank @Email String email
) {
}
