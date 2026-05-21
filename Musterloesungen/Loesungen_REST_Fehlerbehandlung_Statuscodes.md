# Musterlösungen – REST-Fehlerbehandlung und Statuscodes

## Hinweis

Diese Musterlösung zeigt eine einfache Standardlösung zur bestehenden REST-Lagerverwaltung.

Paketnamen und Klassennamen können in deinem Projekt leicht abweichen. Wichtig ist die Idee:

```text
Der Service bleibt fachlich.
Der Controller baut die HTTP-Antwort.
Fehlerantworten haben eine einfache, stabile JSON-Struktur.
```

---

## Basis

### Lösung 1: Erfolgsfall mit gültiger ID prüfen

Ein passender Endpunkt:

```java
@GetMapping("/{id}")
public ResponseEntity<Object> produktNachId(@PathVariable Long id) {
    Optional<Produkt> produkt = produktService.findeProdukt(id);

    if (produkt.isPresent()) {
        return ResponseEntity.ok(toDto(produkt.get()));
    }

    ErrorResponse error = new ErrorResponse(
            "PRODUCT_NOT_FOUND",
            "Produkt wurde nicht gefunden.",
            "id=" + id
    );

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
}
```

`curl`:

```bash
curl -i http://localhost:8080/produkte/1
```

Erwartung:

```http
HTTP/1.1 200
Content-Type: application/json
```

```json
{
  "id": 1,
  "name": "Tastatur",
  "preis": 49.9,
  "status": "AKTIV"
}
```

---

### Lösung 2: Unbekannte ID prüfen

`curl`:

```bash
curl -i http://localhost:8080/produkte/999
```

Vor der Korrektur entsteht oft ein unklarer Fehler oder ein technischer `500`.

Ziel:

```http
HTTP/1.1 404
Content-Type: application/json
```

```json
{
  "code": "PRODUCT_NOT_FOUND",
  "message": "Produkt wurde nicht gefunden.",
  "details": "id=999"
}
```

---

### Lösung 3: Einfache Fehlerantwort

```java
package ch.allianz.youngoitv.lager.api.dto;

public record ErrorResponse(
        String code,
        String message,
        String details
) {
}
```

Vorteil:

```text
Alle Fehlerantworten folgen derselben Grundstruktur.
```

---

### Lösung 4: Service für Produkt nach ID

Der Service bleibt ohne HTTP-Klassen:

```java
public Optional<Produkt> findeProdukt(Long id) {
    return produktRepository.findeNachId(id);
}
```

Import:

```java
import java.util.Optional;
```

Der Service entscheidet fachlich:

```text
Produkt vorhanden oder nicht vorhanden.
```

Der Controller entscheidet über HTTP:

```text
200 OK oder 404 Not Found.
```

---

### Lösung 5: Ungültigen Status testen

Request:

```bash
curl -i -X PUT http://localhost:8080/produkte/1/status/VERKAUFT
```

`VERKAUFT` ist kein erlaubter Wert von:

```java
public enum ProduktStatus {
    AKTIV,
    RESERVIERT,
    DEFEKT,
    ARCHIVIERT
}
```

Zielantwort:

```http
HTTP/1.1 400
Content-Type: application/json
```

```json
{
  "code": "INVALID_PRODUCT_STATUS",
  "message": "Der Produktstatus ist ungültig.",
  "details": "status=VERKAUFT"
}
```

---

### Lösung 6: `400 Bad Request` bei ungültigem Status

Eine einfache Lösung ist, den Pfadwert zuerst als `String` entgegenzunehmen.

```java
@PutMapping("/{id}/status/{status}")
public ResponseEntity<Object> statusAendern(
        @PathVariable Long id,
        @PathVariable String status
) {
    Optional<ProduktStatus> produktStatus = parseStatus(status);

    if (produktStatus.isEmpty()) {
        return ResponseEntity.badRequest().body(new ErrorResponse(
                "INVALID_PRODUCT_STATUS",
                "Der Produktstatus ist ungültig.",
                "status=" + status
        ));
    }

    Optional<Produkt> produkt = produktService.findeProdukt(id);

    if (produkt.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(
                "PRODUCT_NOT_FOUND",
                "Produkt wurde nicht gefunden.",
                "id=" + id
        ));
    }

    Produkt geaendertesProdukt = produktService.statusAendern(
            id,
            produktStatus.get()
    );

    return ResponseEntity.ok(toDto(geaendertesProdukt));
}
```

Hilfsmethode:

```java
private Optional<ProduktStatus> parseStatus(String statusText) {
    try {
        return Optional.of(ProduktStatus.valueOf(statusText));
    } catch (IllegalArgumentException ex) {
        return Optional.empty();
    }
}
```

Hinweis:

```text
Das ist bewusst einfach. Vertieftes globales Exception Handling folgt später.
```

---

### Lösung 7: Bruno-Requests

Sinnvolle Bruno-Requests:

| Name | Methode und Pfad | Erwartung |
|---|---|---|
| `GET Produkt 1 - 200` | `GET /produkte/1` | Produkt-JSON |
| `GET Produkt 999 - 404` | `GET /produkte/999` | `ErrorResponse` |
| `PUT Status ARCHIVIERT - 200` | `PUT /produkte/1/status/ARCHIVIERT` | geändertes Produkt |
| `PUT Status VERKAUFT - 400` | `PUT /produkte/1/status/VERKAUFT` | `ErrorResponse` |

In Bruno sollte sichtbar sein:

- Request-Methode
- URL
- erwarteter Statuscode
- erwartete JSON-Struktur

---

## Vertiefung

### Lösung 8: Einheitliche Fehlerantwort

Gute Fehlercodes:

```text
PRODUCT_NOT_FOUND
INVALID_PRODUCT_STATUS
```

Gute Meldungen:

```text
Produkt wurde nicht gefunden.
Der Produktstatus ist ungültig.
```

Nicht gut:

```text
java.lang.IllegalArgumentException: No enum constant ...
```

Begründung:

```text
Clients sollen fachliche Fehler verstehen, nicht interne Java-Details lesen.
```

---

### Lösung 9: Controller klein halten

Der Controller darf HTTP-Antworten bauen:

```java
return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
```

Der Controller soll nicht selbst Produkte in der Repository-Collection suchen.

Gute Trennung:

| Klasse | Aufgabe |
|---|---|
| `ProduktController` | Request, Response, Statuscode |
| `ProduktService` | fachlicher Ablauf |
| `ProduktRepository` | Speichern und Laden |
| `ErrorResponse` | Fehlerstruktur nach aussen |

---

### Lösung 10: Service-Fehler klar weitergeben

Eine einfache Service-Methode:

```java
public Optional<Produkt> findeProdukt(Long id) {
    return produktRepository.findeNachId(id);
}
```

Auch möglich:

```java
public Produkt statusAendern(Long id, ProduktStatus status) {
    Produkt produkt = produktRepository.findeNachId(id)
            .orElseThrow(() -> new IllegalArgumentException("Produkt nicht gefunden: " + id));
    produkt.setStatus(status);
    return produkt;
}
```

Für diese Einheit ist `Optional` beim Lesen besonders gut nachvollziehbar.

Nicht empfohlen:

```java
public ResponseEntity<Object> findeProdukt(Long id) {
    // Service kennt dadurch HTTP
}
```

---

### Lösung 11: Statuscodes begründen

| Situation | Statuscode | Begründung |
|---|---|---|
| Produktliste laden | `200` | Request war erfolgreich, Liste wird geliefert |
| Produkt erstellen | `201` | Ein neues Produkt wurde erstellt |
| Produkt-ID unbekannt | `404` | Die angefragte Ressource existiert nicht |
| Statuswert ungültig | `400` | Der Request enthält einen ungültigen Wert |
| unerwarteter Serverfehler | `500` | Fehler liegt nicht in einem normalen Fachfall |

---

### Lösung 12: Logging beobachten

Beim kontrollierten Fehlerfall soll der Client eine klare Antwort erhalten.

Beispiel:

```text
HTTP 404 + ErrorResponse
```

Das ist nicht dasselbe wie ein technischer Absturz.

Ein technischer Absturz zeigt sich oft durch:

- Stacktrace
- `500`
- unklare oder sehr technische Antwort

---

## Transfer

### Lösung 13: Statuscodes erklären

`200` bei einem Fehler ist problematisch, weil Clients dann nicht zuverlässig erkennen, ob die Anfrage erfolgreich war.

`404` bedeutet:

```text
Die angefragte Ressource wurde nicht gefunden.
```

`500` bedeutet:

```text
Der Server hatte ein unerwartetes technisches Problem.
```

Ein ungültiger Status wie `VERKAUFT` ist ein fehlerhafter Request. Deshalb passt `400` besser als `404`.

---

### Lösung 14: Fehlerantworten begründen

Strukturierte Fehlerantworten helfen, weil sie vorhersehbar sind.

Bruno kann zum Beispiel prüfen:

- Statuscode
- `code`
- `message`
- `details`

Ein Stacktrace ist ungeeignet, weil er:

- interne technische Details zeigt
- für Clients schwer auswertbar ist
- je nach Codeänderung zufällig anders aussieht

---

### Lösung 15: Fehlerbehandlung und Fachlogik trennen

Beispielhafte Trennung:

```text
ProduktService:
- Produkt suchen
- Status ändern
- fachlichen Ablauf ausführen

ProduktController:
- HTTP-Request entgegennehmen
- Statuscode wählen
- ProduktDto oder ErrorResponse zurückgeben
```

Fehlerbehandlung ersetzt keine Fachlogik. Sie macht nur sichtbar, wie ein fachlicher oder technischer Fehler über REST kommuniziert wird.

---

## Typische Fehlerhinweise

- Wenn ein Fehler mit `200` zurückkommt, ist der API-Vertrag unklar.
- Wenn ein Stacktrace im JSON erscheint, ist die Antwort zu technisch.
- Wenn der Service `ResponseEntity` kennt, sind Fachlogik und HTTP zu stark vermischt.
- Wenn Fehlertexte überall einzeln gebaut werden, werden Antworten schnell uneinheitlich.
