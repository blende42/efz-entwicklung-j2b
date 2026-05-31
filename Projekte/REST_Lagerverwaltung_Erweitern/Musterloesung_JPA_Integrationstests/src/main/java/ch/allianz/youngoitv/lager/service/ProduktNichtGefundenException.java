package ch.allianz.youngoitv.lager.service;

public class ProduktNichtGefundenException extends RuntimeException {

    public ProduktNichtGefundenException(Long id) {
        super("Produkt nicht gefunden: " + id);
    }
}
