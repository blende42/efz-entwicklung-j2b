# Musterlösungen – Optional und kontrollierte Null-Behandlung

## Hinweis

Diese Musterlösung zeigt eine einfache Standardlösung zur REST-Lagerverwaltung.

Wichtig ist die Idee:

```text
Das Repository macht fehlende Produkte mit Optional sichtbar.
Der Service bleibt fachlich.
Der Controller übersetzt fehlende Produkte in REST-Antworten.
```

---

## Basis

### Lösung 1: Aktuelle Null-Behandlung suchen

Typische alte Variante:

```java
public Produkt findeNachId(Long id) {
    for (Produkt produkt : produkte) {
        if (produkt.getId().equals(id)) {
            return produkt;
        }
    }

    return null;
}
```

Problem:

```text
Der Rückgabetyp Produkt zeigt nicht, dass kein Produkt gefunden werden kann.
```

Wenn der Aufrufer danach direkt Methoden aufruft, kann eine `NullPointerException` entstehen.

---

### Lösung 2: Repository auf `Optional` umstellen

```java
package ch.allianz.youngoitv.lager.repository;

import ch.allianz.youngoitv.lager.domain.Produkt;

import java.util.Optional;

public class ProduktRepository {

    public Optional<Produkt> findeNachId(Long id) {
        return produkte.stream()
                .filter(produkt -> produkt.getId().equals(id))
                .findFirst();
    }
}
```

Erklärung:

```text
findFirst() liefert Optional<Produkt>.
Ist ein Produkt vorhanden, enthält Optional dieses Produkt.
Wird nichts gefunden, entsteht Optional.empty().
```

Das Repository gibt kein `null` mehr zurück.

---

### Lösung 3: Service-Methode für Produkt nach ID

```java
package ch.allianz.youngoitv.lager.service;

import ch.allianz.youngoitv.lager.domain.Produkt;
import ch.allianz.youngoitv.lager.repository.ProduktRepository;

import java.util.Optional;

public class ProduktService {

    private final ProduktRepository produktRepository;

    public ProduktService(ProduktRepository produktRepository) {
        this.produktRepository = produktRepository;
    }

    public Optional<Produkt> findeProdukt(Long id) {
        return produktRepository.findeNachId(id);
    }
}
```

Der Service kennt hier weiterhin keine HTTP-Klassen.

---

### Lösung 4: Optional im Controller prüfen

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

Imports:

```java
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
```

Ergebnis:

| Fall | Antwort |
|---|---|
| Produkt vorhanden | `200 OK` mit `ProduktDto` |
| Produkt fehlt | `404 Not Found` mit `ErrorResponse` |

---

### Lösung 5: Bruno und `curl`

Gültige ID:

```bash
curl -i http://localhost:8080/produkte/1
```

Mögliche Antwort:

```http
HTTP/1.1 200
Content-Type: application/json
```

Unbekannte ID:

```bash
curl -i http://localhost:8080/produkte/999
```

Mögliche Antwort:

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

Bruno-Requests:

| Name | Methode und Pfad | Erwartung |
|---|---|---|
| `GET Produkt 1 - Optional vorhanden` | `GET /produkte/1` | `200` |
| `GET Produkt 999 - Optional leer` | `GET /produkte/999` | `404` |

---

## Vertiefung

### Lösung 6: Service mit `orElseThrow()`

```java
public Produkt statusAendern(Long id, ProduktStatus status) {
    Produkt produkt = produktRepository.findeNachId(id)
            .orElseThrow(() -> new IllegalArgumentException("Produkt nicht gefunden: " + id));

    produkt.setStatus(status);
    return produkt;
}
```

Einordnung:

- `orElseThrow()` ist passend, wenn der Service ohne Produkt nicht weiterarbeiten kann.
- Der Service bleibt fachlich und baut keine `ResponseEntity`.
- Der Controller oder eine spätere Fehlerbehandlung kann daraus eine REST-Antwort machen.

---

### Lösung 7: Optional mit Streams vergleichen

Suche nach genau einem Produkt:

```java
public Optional<Produkt> findeNachId(Long id) {
    return produkte.stream()
            .filter(produkt -> produkt.getId().equals(id))
            .findFirst();
}
```

Alle Produkte:

```java
public List<Produkt> alle() {
    return new ArrayList<>(produkte);
}
```

Begründung:

| Fall | Passender Rückgabetyp | Warum |
|---|---|---|
| ein Produkt nach ID | `Optional<Produkt>` | genau ein Produkt oder keines |
| alle Produkte | `List<Produkt>` | auch eine leere Liste ist eine gültige Antwort |

`Optional<List<Produkt>>` wäre hier unnötig kompliziert.

---

### Lösung 8: `orElse()` sinnvoll einsetzen

Einfache Variante mit `isPresent()`:

```java
public String produktNameOderUnbekannt(Long id) {
    Optional<Produkt> produkt = produktRepository.findeNachId(id);

    if (produkt.isPresent()) {
        return produkt.get().getName();
    }

    return "Unbekanntes Produkt";
}
```

Variante mit `orElse()` über einen Ersatzwert:

```java
public Produkt produktOderErsatz(Long id) {
    Produkt ersatzProdukt = new Produkt(0L, "Unbekanntes Produkt", 0.0, ProduktStatus.ARCHIVIERT);

    return produktRepository.findeNachId(id)
            .orElse(ersatzProdukt);
}
```

Hinweis:

```text
Ein Ersatzprodukt ist nur sinnvoll, wenn es fachlich wirklich passt.
Für GET /produkte/999 ist 404 meistens besser.
```

---

### Lösung 9: Fehlerfälle sauber behandeln

| Fall | Ablauf |
|---|---|
| `GET /produkte/1` | Repository liefert `Optional` mit Produkt, Controller gibt `200` zurück |
| `GET /produkte/999` | Repository liefert `Optional.empty()`, Controller gibt `404` zurück |
| `PUT /produkte/999/status/AKTIV` | Service kann kein Produkt ändern, Fehler wird kontrolliert behandelt |

Klare Verantwortlichkeiten:

```text
Repository: sucht Produkt.
Service: koordiniert Fachlogik.
Controller: baut HTTP-Antwort.
```

---

### Lösung 10: Logging bei fehlenden Produkten

Beispiel im Controller:

```java
private static final Logger LOG = LoggerFactory.getLogger(ProduktController.class);
```

```java
if (produkt.isEmpty()) {
    LOG.info("Produkt wurde nicht gefunden: {}", id);

    ErrorResponse error = new ErrorResponse(
            "PRODUCT_NOT_FOUND",
            "Produkt wurde nicht gefunden.",
            "id=" + id
    );

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
}
```

Imports:

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
```

Wichtig:

```text
Logging hilft beim Beobachten.
Logging ersetzt keine REST-Fehlerantwort.
```

---

## Transfer

### Lösung 11: Optional erklären

`Optional<Produkt>` ist klarer als `Produkt`, weil der Rückgabetyp zeigt:

```text
Es kann ein Produkt fehlen.
```

Dadurch muss der Aufrufer eine Entscheidung treffen:

- Was passiert, wenn ein Produkt vorhanden ist?
- Was passiert, wenn kein Produkt vorhanden ist?

Im REST Controller wird daraus:

```text
Produkt vorhanden -> 200 OK
Produkt fehlt -> 404 Not Found
```

---

### Lösung 12: `null` und `Optional` unterscheiden

| Situation | `null` | `Optional` |
|---|---|---|
| Produkt nicht gefunden | Wert fehlt unsichtbar | `Optional.empty()` |
| Rückgabetyp zeigt fehlenden Wert | nein | ja |
| Prüfung wird leicht vergessen | ja | weniger leicht, weil Rückgabetyp auffällt |
| REST-Fehler kann bewusst entstehen | nur mit zusätzlicher Disziplin | ja, über sichtbare Prüfung |

---

### Lösung 13: Optional und REST-Fehlerbehandlung verbinden

Ablauf:

```text
ProduktRepository.findeNachId(999)
-> Optional.empty()
-> ProduktService.findeProdukt(999)
-> ProduktController erkennt fehlenden Wert
-> 404 Not Found + ErrorResponse
```

Antworten:

- HTTP kennt der Controller.
- Die Speicherstruktur kennt das Repository.
- Die fachliche Suche koordiniert der Service.

---

## Typische Fehlerhinweise

- `return null;` in einer Methode mit `Optional<Produkt>` ist falsch.
- `optional.get()` ohne vorherige Prüfung verschiebt das Problem nur.
- `Optional` in DTOs macht die REST-Struktur unnötig schwer.
- `Optional` soll fehlende Werte sichtbar machen, nicht Code komplizierter aussehen lassen.
