package ch.allianz.youngoitv.shop.api.dto;

public record BestellpositionDto(
        Long produktId,
        String produktName,
        int menge,
        double einzelpreis
) {
}
