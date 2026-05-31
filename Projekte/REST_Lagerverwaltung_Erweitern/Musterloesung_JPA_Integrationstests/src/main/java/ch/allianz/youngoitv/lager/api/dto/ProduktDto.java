package ch.allianz.youngoitv.lager.api.dto;

import ch.allianz.youngoitv.lager.domain.ProduktStatus;

public record ProduktDto(
        Long id,
        String name,
        double preis,
        ProduktStatus status
) {
}
