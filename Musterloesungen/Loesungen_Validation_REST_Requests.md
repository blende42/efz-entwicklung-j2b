# Musterlösungen – Validation bei REST-Requests

## Hinweis

Diese Musterlösung zeigt eine einfache Standardlösung für die REST-Lagerverwaltung.

Wichtig ist die Idee:

```text
Das Request-DTO beschreibt einfache Eingaberegeln.
Der Controller übersetzt Validierungsfehler in 400 Bad Request.
Der Service bleibt fachlich.
```

---

## Basis

### Lösung 1: Ungültige Requests untersuchen

Ohne Validation kann ein ungültiger Request unterschiedliche Folgen haben:

- Das Produkt wird trotz leerem Namen erstellt.
- Ein technischer Fehler entsteht später.
- Die API gibt eine unklare Fehlermeldung zurück.
- Der Client erhält keinen verständlichen Hinweis, welches Feld ungültig ist.

Ein ungültiger Request soll kontrolliert beantwortet werden:

```text
400 Bad Request
```

---

### Lösung 2: Validation-Starter ergänzen

Im `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

Danach stehen die Bean-Validation-Annotationen aus `jakarta.validation.constraints` zur Verfügung.

---

### Lösung 3: Request-DTO erstellen

```java
package ch.allianz.youngoitv.lager.api;

import ch.allianz.youngoitv.lager.domain.ProduktStatus;

public record ProduktCreateRequest(
        String name,
        double preis,
        ProduktStatus status
) {
}
```

Das DTO beschreibt die JSON-Eingabe für `POST /produkte`. Es ist nicht das Fachmodell.

---

### Lösung 4: Einfache Regeln ergänzen

```java
package ch.allianz.youngoitv.lager.api;

import ch.allianz.youngoitv.lager.domain.ProduktStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record ProduktCreateRequest(
        @NotBlank(message = "Name darf nicht leer sein.")
        String name,

        @PositiveOrZero(message = "Preis darf nicht negativ sein.")
        double preis,

        @NotNull(message = "Status muss gesetzt sein.")
        ProduktStatus status
) {
}
```

Einordnung:

| Regel | Grund |
|---|---|
| `@NotBlank` | Name darf nicht leer oder nur Leerzeichen sein |
| `@PositiveOrZero` | Preis darf nicht negativ sein |
| `@NotNull` | Status muss im Request vorhanden sein |

---

### Lösung 5: `@Valid` im Controller verwenden

```java
package ch.allianz.youngoitv.lager.api;

import ch.allianz.youngoitv.lager.domain.Produkt;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class ProduktController {

    @PostMapping
    public ResponseEntity<Object> produktErstellen(
            @Valid @RequestBody ProduktCreateRequest request,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            ErrorResponse error = new ErrorResponse(
                    "VALIDATION_FAILED",
                    "Request ist ungültig.",
                    ersteFehlermeldung(bindingResult)
            );

            return ResponseEntity.badRequest().body(error);
        }

        Produkt produkt = produktService.erstellen(
                request.name(),
                request.preis(),
                request.status()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(produkt));
    }

    private String ersteFehlermeldung(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
                .findFirst()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .orElse("Unbekannter Validierungsfehler.");
    }
}
```

Hinweis:

```text
Der Ausschnitt zeigt nur die relevanten Teile des Controllers.
produktService und toDto(...) sind bereits aus früheren Einheiten bekannt.
```

---

### Lösung 6: Fehlerantwort für Validation bauen

```java
package ch.allianz.youngoitv.lager.api;

public record ErrorResponse(
        String code,
        String message,
        String details
) {
}
```

Mögliche Antwort:

```json
{
  "code": "VALIDATION_FAILED",
  "message": "Request ist ungültig.",
  "details": "name: Name darf nicht leer sein."
}
```

Das passt zu `400 Bad Request`, weil der Client ungültige Eingaben gesendet hat.

---

## Aufbau

### Lösung 7: Bruno-Requests

| Name | Methode und Pfad | Body | Erwartung |
|---|---|---|---|
| `POST Produkt gültig` | `POST /produkte` | gültiger Name, Preis, Status | `201` |
| `POST Produkt leerer Name` | `POST /produkte` | `name` leer | `400` |
| `POST Produkt negativer Preis` | `POST /produkte` | `preis` negativ | `400` |
| `POST Produkt ohne Status` | `POST /produkte` | kein `status` | `400` |

Beispiel mit `curl`:

```bash
curl -i -X POST http://localhost:8080/produkte \
  -H "Content-Type: application/json" \
  -d '{"name":"","preis":-5,"status":"AKTIV"}'
```

Erwartung:

```http
HTTP/1.1 400
Content-Type: application/json
```

```json
{
  "code": "VALIDATION_FAILED",
  "message": "Request ist ungültig.",
  "details": "name: Name darf nicht leer sein."
}
```

---

### Lösung 8: Validation und Fachlogik trennen

| Regel | Gehört wohin? | Begründung |
|---|---|---|
| Name darf nicht leer sein | Request-DTO | einfache Eingaberegel |
| Preis darf nicht negativ sein | Request-DTO | einfache Eingaberegel |
| Status muss vorhanden sein | Request-DTO | einfache Eingaberegel |
| Produkt-ID darf nicht doppelt entstehen | Service oder Repository | braucht bestehenden Datenbestand |
| Statuswechsel von `ARCHIVIERT` zu `AKTIV` ist verboten | Service | fachliche Regel |

Validation ersetzt keine Fachlogik. Sie sorgt dafür, dass einfache fehlerhafte Eingaben nicht unnötig weit in die Anwendung gelangen.

---

### Lösung 9: Typische Fehler

| Fehler | Korrektur |
|---|---|
| `@Valid` fehlt | Request-Parameter im Controller mit `@Valid` markieren |
| `BindingResult` wird ignoriert | vor dem Service-Aufruf `bindingResult.hasErrors()` prüfen |
| `ResponseEntity` im Service | HTTP-Antwort im Controller bauen |
| Validation nur auf Fachmodell | Regeln für diese Einheit auf Request-DTO setzen |
| Stacktrace in Antwort | kontrollierte `ErrorResponse` zurückgeben |

---

## Vertiefung

### Lösung 10: Mehrere Fehlermeldungen sammeln

Eine einfache Standardlösung mit `Collectors.joining`:

```java
private String alleFehlermeldungen(BindingResult bindingResult) {
    return bindingResult.getFieldErrors().stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .collect(Collectors.joining("; "));
}
```

Benötigter Import:

```java
import java.util.stream.Collectors;
```

Für diese Einheit ist auch die erste Fehlermeldung akzeptabel, wenn die Aufgabe nicht die Sammlung mehrerer Fehler verlangt.

---

### Lösung 11: Architekturgespräch

Mögliche Antworten:

- Validation liegt auf dem Request-DTO, weil dort beschrieben wird, welche Eingaben von aussen erlaubt sind.
- Der Service bleibt frei von `ResponseEntity`, weil HTTP eine Controller-Verantwortung ist.
- `400 Bad Request` passt, weil der Client ungültige Daten gesendet hat.
- Automatische Mapping-Frameworks oder komplexe Validation-Gruppen wären hier zu viel, weil zuerst die Grundidee sichtbar werden soll.

---

## Kurzer Prüfplan

| Fall | Erwartung |
|---|---|
| gültiger Request | `201 Created` mit Produkt-JSON |
| leerer Name | `400 Bad Request` mit `VALIDATION_FAILED` |
| negativer Preis | `400 Bad Request` mit `VALIDATION_FAILED` |
| fehlender Status | `400 Bad Request` mit `VALIDATION_FAILED` |

Der wichtigste Strukturpunkt:

```text
Controller prüft Request.
Service verarbeitet gültige Eingaben.
Repository bleibt für Datenzugriff zuständig.
```
