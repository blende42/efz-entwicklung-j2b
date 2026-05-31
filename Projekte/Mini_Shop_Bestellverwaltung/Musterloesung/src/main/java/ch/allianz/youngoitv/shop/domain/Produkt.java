package ch.allianz.youngoitv.shop.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Produkt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double preis;
    private int bestand;
    private Long kategorieId;

    protected Produkt() {
    }

    public Produkt(String name, double preis, int bestand, Long kategorieId) {
        this.name = name;
        this.preis = preis;
        this.bestand = bestand;
        this.kategorieId = kategorieId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPreis() {
        return preis;
    }

    public int getBestand() {
        return bestand;
    }

    public Long getKategorieId() {
        return kategorieId;
    }

    public boolean hatGenugBestand(int menge) {
        return bestand >= menge;
    }

    public void reduziereBestand(int menge) {
        bestand -= menge;
    }
}
