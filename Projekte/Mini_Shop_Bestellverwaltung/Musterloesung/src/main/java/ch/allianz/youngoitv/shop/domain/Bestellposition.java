package ch.allianz.youngoitv.shop.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Bestellposition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bestellungId;
    private Long produktId;
    private String produktName;
    private int menge;
    private double einzelpreis;

    protected Bestellposition() {
    }

    public Bestellposition(Long bestellungId, Long produktId, String produktName, int menge, double einzelpreis) {
        this.bestellungId = bestellungId;
        this.produktId = produktId;
        this.produktName = produktName;
        this.menge = menge;
        this.einzelpreis = einzelpreis;
    }

    public Long getId() {
        return id;
    }

    public Long getBestellungId() {
        return bestellungId;
    }

    public Long getProduktId() {
        return produktId;
    }

    public String getProduktName() {
        return produktName;
    }

    public int getMenge() {
        return menge;
    }

    public double getEinzelpreis() {
        return einzelpreis;
    }
}
