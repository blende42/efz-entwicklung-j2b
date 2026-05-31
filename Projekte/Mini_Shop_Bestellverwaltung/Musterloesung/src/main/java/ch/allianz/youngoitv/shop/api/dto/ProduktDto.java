package ch.allianz.youngoitv.shop.api.dto;

public record ProduktDto(
        Long id,
        String name,
        double preis,
        int bestand,
        Long kategorieId
) {
}
