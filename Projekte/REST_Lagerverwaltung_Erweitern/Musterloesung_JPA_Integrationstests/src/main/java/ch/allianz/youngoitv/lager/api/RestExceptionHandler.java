package ch.allianz.youngoitv.lager.api;

import ch.allianz.youngoitv.lager.service.ProduktNichtGefundenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> validationFehler() {
        return ResponseEntity.badRequest()
                .body(new ErrorResponse("Request ist ungültig"));
    }

    @ExceptionHandler(ProduktNichtGefundenException.class)
    public ResponseEntity<ErrorResponse> produktNichtGefunden(ProduktNichtGefundenException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(exception.getMessage()));
    }
}
