package ch.allianz.youngoitv.lager.domain;

public class Produkt {

    private final Long id;
    private String name;
    private double preis;
    private ProduktStatus status;

    public Produkt(Long id, String name, double preis, ProduktStatus status) {
        this.id = id;
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
