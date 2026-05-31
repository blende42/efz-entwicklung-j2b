package ch.allianz.youngoitv.lager.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @Enumerated(EnumType.STRING)
    private ProduktStatus status;

    protected Produkt() {
    }

    public Produkt(String name, double preis, ProduktStatus status) {
        this.name = name;
        this.preis = preis;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public ProduktStatus getStatus() {
        return status;
    }

    public void setStatus(ProduktStatus status) {
        this.status = status;
    }
}
