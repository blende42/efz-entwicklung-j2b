# Musterlösungen – Enums in Fachmodellen und REST-APIs

## Hinweis

Diese Musterlösung zeigt eine mögliche Standardlösung.

Paketnamen, Klassennamen und Methodennamen können in deinem Projekt abweichen. Wichtig ist die Idee:

```text
Statuswerte werden nicht als freie Strings modelliert.
Ein enum beschreibt den kontrollierten Wertebereich.
DTOs machen den Status in REST kontrolliert sichtbar.
```

---

## Basis

### Lösung 1: String-Status suchen

Typische Fundstellen:

```java
private String status;
```

```java
produkt.setStatus("AKTIV");
```

Problem:

```text
Java kann nicht verhindern, dass auch "aktiv", "ACTIVE" oder "FALSCH" gesetzt wird.
```

Der Status ist fachlich wichtig, aber technisch noch ein beliebiger Text.

---

### Lösung 2: `ProduktStatus`-Enum erstellen

```java
package ch.allianz.youngoitv.lager.domain;

public enum ProduktStatus {
    AKTIV,
    INAKTIV,
    NICHT_LIEFERBAR
}
```

Erklärung:

| Code | Bedeutung |
|---|---|
| `enum` | feste Auswahl erlaubter Werte |
| `ProduktStatus` | fachlicher Name des Wertebereichs |
| `AKTIV` | erlaubter Statuswert |
| `INAKTIV` | erlaubter Statuswert |
| `NICHT_LIEFERBAR` | erlaubter Statuswert |

---

### Lösung 3: String-Status ersetzen

```java
public class Produkt {

    private Long id;
    private String name;
    private double preis;
    private ProduktStatus status;

    public Produkt(Long id, String name, double preis, ProduktStatus status) {
        this.id = id;
        this.name = name;
        this.preis = preis;
        this.status = status;
    }

    public ProduktStatus getStatus() {
        return status;
    }

    public void setStatus(ProduktStatus status) {
        this.status = status;
    }
}
```

Beispieldaten:

```java
new Produkt(1L, "Tastatur", 49.9, ProduktStatus.AKTIV);
new Produkt(2L, "Maus", 24.9, ProduktStatus.INAKTIV);
```

Wichtig:

```text
Der Status ist nicht mehr ein beliebiger String.
```

---

### Lösung 4: DTO um Enum erweitern

```java
public record ProduktResponseDto(
        Long id,
        String name,
        double preis,
        ProduktStatus status
) {
}
```

Mapping:

```java
private ProduktResponseDto toResponseDto(Produkt produkt) {
    return new ProduktResponseDto(
            produkt.getId(),
            produkt.getName(),
            produkt.getPreis(),
            produkt.getStatus()
    );
}
```

Erklärung:

```text
Das Fachmodell enthält den kontrollierten Status.
Das Response-DTO macht diesen Status in der REST-Ausgabe sichtbar.
```

---

### Lösung 5: REST-JSON mit Enum testen

Befehl:

```bash
curl -i http://localhost:8080/produkte
```

Mögliche Antwort:

```json
[
  {
    "id": 1,
    "name": "Tastatur",
    "preis": 49.9,
    "status": "AKTIV"
  },
  {
    "id": 2,
    "name": "Maus",
    "preis": 24.9,
    "status": "INAKTIV"
  }
]
```

Antworten:

- Der Enum-Wert erscheint im JSON als String.
- Die Java-Seite verwendet trotzdem einen kontrollierten Enum-Wert.
- Das Response-DTO steuert, dass `status` Teil der API-Ausgabe ist.

---

### Lösung 6: `switch` mit Enum verwenden

```java
public String beschreibungFuer(ProduktStatus status) {
    return switch (status) {
        case AKTIV -> "Produkt ist sichtbar";
        case INAKTIV -> "Produkt ist ausgeblendet";
        case NICHT_LIEFERBAR -> "Produkt ist aktuell nicht lieferbar";
    };
}
```

Vorteil:

```text
Die Entscheidung basiert auf ProduktStatus und nicht auf frei geschriebenen Texten.
```

---

### Lösung 7: Ungültige Statuswerte analysieren

Request:

```json
{
  "name": "Monitor",
  "preis": 159.9,
  "status": "aktiv"
}
```

Beobachtung:

```text
"aktiv" passt nicht zum Enum-Wert AKTIV.
```

Je nach Projekt und Spring-Konfiguration entsteht eine Fehlermeldung, weil der JSON-Wert nicht in `ProduktStatus` umgewandelt werden kann.

Hinweis:

```text
Die genaue Fehlerantwort wird später bei REST-Fehlerbehandlung und Validation sauber gestaltet.
```

---

### Lösung 8: Bruno-Requests erneut testen

Prüfpunkte:

- `GET /produkte` liefert weiterhin eine JSON-Liste.
- Jedes Produkt enthält den erwarteten `status`.
- Requests mit Status verwenden exakt Enum-Werte wie `AKTIV`.
- Die DTO-Struktur ist weiterhin die öffentliche API-Struktur.

---

## Vertiefung

### Lösung 9: Mehrere Statuswerte ergänzen

```java
List<Produkt> produkte = List.of(
        new Produkt(1L, "Tastatur", 49.9, ProduktStatus.AKTIV),
        new Produkt(2L, "Maus", 24.9, ProduktStatus.INAKTIV),
        new Produkt(3L, "Monitor", 159.9, ProduktStatus.NICHT_LIEFERBAR)
);
```

REST-Ausgabe:

```json
[
  {
    "id": 1,
    "name": "Tastatur",
    "preis": 49.9,
    "status": "AKTIV"
  },
  {
    "id": 2,
    "name": "Maus",
    "preis": 24.9,
    "status": "INAKTIV"
  },
  {
    "id": 3,
    "name": "Monitor",
    "preis": 159.9,
    "status": "NICHT_LIEFERBAR"
  }
]
```

---

### Lösung 10: Produkte nach Status filtern

```java
return produkte.stream()
        .filter(produkt -> produkt.getStatus() == ProduktStatus.AKTIV)
        .map(produkt -> toResponseDto(produkt))
        .toList();
```

Antworten:

- `ProduktStatus.AKTIV` verweist auf einen definierten Enum-Wert.
- Im Stream bleiben nur aktive Produkte.
- `filter()` steht vor `map()`, weil auf dem Fachobjekt nach fachlichem Status entschieden wird.

---

### Lösung 11: Logging mit Statuswerten beobachten

```java
log.info("Produkt {} hat Status {}", produkt.getName(), produkt.getStatus());
```

Mögliche Log-Ausgabe:

```text
Produkt Tastatur hat Status AKTIV
```

Der Enum-Wert ist direkt lesbar und entspricht dem kontrollierten Wertebereich.

---

### Lösung 12: Enum-Verwendung mit Strings vergleichen

Alte String-Variante:

```java
"AKTIV".equals(statusText)
```

Diese Variante hängt von einem frei geschriebenen Text ab. Tippfehler oder unterschiedliche Schreibweisen können unbemerkt bleiben.

Neue Enum-Variante:

```java
produkt.getStatus() == ProduktStatus.AKTIV
```

Diese Variante ist klarer, weil `AKTIV` aus dem Enum `ProduktStatus` stammt. Der erlaubte Wertebereich ist dadurch im Code sichtbar.

---

## Transfer

### Lösung 13: Sinn von Enums erklären

Eine mögliche Erklärung:

```text
Ein Enum ist sinnvoll, wenn ein Wert nur wenige erlaubte Zustände haben darf. Bei Produkten darf der Status zum Beispiel AKTIV, INAKTIV oder NICHT_LIEFERBAR sein. Ein String könnte dagegen jeden beliebigen Text enthalten.
```

---

### Lösung 14: Unterschied zwischen String und Enum erklären

| String | Enum |
|---|---|
| `"aktiv"` ist beliebiger Text | `ProduktStatus.AKTIV` ist ein definierter Wert |
| Tippfehler können lange unbemerkt bleiben | falsche Enum-Namen fallen im Code früher auf |
| erlaubte Werte stehen oft verstreut | erlaubte Werte stehen im Enum |

---

### Lösung 15: Kontrollierte Zustände begründen

Mögliche Antwort:

```text
Kontrollierte Zustände sind wichtig, weil Fachmodelle dadurch klarer ausdrücken, welche Situationen erlaubt sind. REST profitiert davon, weil Clients stabile Werte wie AKTIV oder INAKTIV erhalten. Das Fachmodell wird lesbarer, weil der Typ ProduktStatus sofort zeigt, dass es um einen begrenzten fachlichen Wertebereich geht.
```

---

## Bewertungsfokus

| Bereich | Einschätzung | Beobachtung |
|---|---|---|
| Funktionalität | gut erfüllt | REST-Ausgabe enthält den Enum-Status korrekt |
| Verantwortlichkeiten | gut erfüllt | Fachmodell speichert `ProduktStatus`, DTO macht ihn sichtbar |
| Verständlichkeit | gut erfüllt | Enum-Name und Werte sind fachlich lesbar |
| Technische Sauberkeit | gut erfüllt | keine freien String-Vergleiche für Statuslogik |
| Lernzielerreichung | gut erfüllt | kontrollierte Zustände werden sichtbar und begründet |

---

## Typischer Fehlerhinweis

Nicht ideal:

```java
if ("AKTIV".equals(produkt.getStatus().name())) {
    ...
}
```

Besser:

```java
if (produkt.getStatus() == ProduktStatus.AKTIV) {
    ...
}
```

Begründung:

```text
Der Code soll mit dem Enum arbeiten, nicht wieder auf String-Vergleiche zurückfallen.
```
