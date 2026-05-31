package ch.allianz.youngoitv.shop.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Bestellung {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long kundeId;
    private String kundenName;
    private LocalDateTime bestelldatum;

    protected Bestellung() {
    }

    public Bestellung(Long kundeId, String kundenName, LocalDateTime bestelldatum) {
        this.kundeId = kundeId;
        this.kundenName = kundenName;
        this.bestelldatum = bestelldatum;
    }

    public Long getId() {
        return id;
    }

    public Long getKundeId() {
        return kundeId;
    }

    public String getKundenName() {
        return kundenName;
    }

    public LocalDateTime getBestelldatum() {
        return bestelldatum;
    }
}
