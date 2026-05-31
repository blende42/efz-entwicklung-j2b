package ch.allianz.youngoitv.shop.api.dto;

import java.time.LocalDateTime;
import java.util.List;

public record BestellungDto(
        Long id,
        Long kundeId,
        String kundenName,
        LocalDateTime bestelldatum,
        List<BestellpositionDto> positionen
) {
}
