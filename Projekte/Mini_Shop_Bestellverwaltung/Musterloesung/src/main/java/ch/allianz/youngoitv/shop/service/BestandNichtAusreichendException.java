package ch.allianz.youngoitv.shop.service;

public class BestandNichtAusreichendException extends RuntimeException {

    public BestandNichtAusreichendException(String message) {
        super(message);
    }
}
