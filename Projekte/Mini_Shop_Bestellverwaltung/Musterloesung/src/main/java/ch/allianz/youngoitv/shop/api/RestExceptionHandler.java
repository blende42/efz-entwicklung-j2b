package ch.allianz.youngoitv.shop.api;

import ch.allianz.youngoitv.shop.service.BestandNichtAusreichendException;
import ch.allianz.youngoitv.shop.service.RessourceNichtGefundenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ErrorResponse> handleValidation() {
        return ResponseEntity.badRequest().body(new ErrorResponse("Request ist ungültig"));
    }

    @ExceptionHandler(BestandNichtAusreichendException.class)
    ResponseEntity<ErrorResponse> handleBestand(BestandNichtAusreichendException exception) {
        return ResponseEntity.badRequest().body(new ErrorResponse(exception.getMessage()));
    }

    @ExceptionHandler(RessourceNichtGefundenException.class)
    ResponseEntity<ErrorResponse> handleNichtGefunden(RessourceNichtGefundenException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(exception.getMessage()));
    }
}
