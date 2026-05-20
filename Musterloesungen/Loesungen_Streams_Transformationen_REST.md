# Musterlösungen – Streams und Transformationen in REST- und DTO-Anwendungen

## Hinweis

Diese Musterlösung zeigt eine mögliche Standardlösung.

Paketnamen, Klassennamen und Methodennamen können in deinem Projekt abweichen. Wichtig ist die Idee:

```text
Streams transformieren Collections kontrolliert.
DTO-Mapping wird mit map() lesbar ausgedrückt.
Das Ergebnis für REST bleibt eine List<ProduktResponseDto>.
```

---

## Basis

### Lösung 1: Schleifen-Mapping lesen

In der Schleifenvariante beginnt die Produktliste hier:

```java
for (Produkt produkt : produkte) {
```

Die Umwandlung in ein DTO passiert hier:

```java
toResponseDto(produkt)
```

Die Response-Liste entsteht hier:

```java
List<ProduktResponseDto> response = new ArrayList<>();
response.add(toResponseDto(produkt));
```

Erklärung:

```text
Für jedes Produkt wird ein ProduktResponseDto erzeugt und in die Response-Liste gelegt.
```

---

### Lösung 2: `stream()` als Startpunkt

```java
produkte.stream()
```

Antworten:

- `produkte` bleibt eine `List<Produkt>`.
- Der Stream beschreibt die Verarbeitung dieser Liste.
- `stream()` ist der Startpunkt für Verarbeitungsschritte wie `filter()` und `map()`.

Wichtig:

```text
Der Stream speichert die Produkte nicht dauerhaft.
```

---

### Lösung 3: DTO-Mapping mit `map()`

```java
List<ProduktResponseDto> response = produkte.stream()
        .map(produkt -> toResponseDto(produkt))
        .toList();
```

Erklärung:

| Code | Bedeutung |
|---|---|
| `produkte.stream()` | Verarbeitung der Produktliste starten |
| `map(...)` | jedes Produkt in ein DTO transformieren |
| `toResponseDto(produkt)` | bestehende Mapping-Methode verwenden |
| `toList()` | Ergebnis wieder als Liste sammeln |

---

### Lösung 4: Stream-Ergebnis zurückgeben

```java
@GetMapping
public List<ProduktResponseDto> alleProdukte() {
    List<Produkt> produkte = lagerService.alleProdukte();

    return produkte.stream()
            .map(produkt -> toResponseDto(produkt))
            .toList();
}
```

Die REST-Methode gibt weiterhin eine `List<ProduktResponseDto>` zurück.

Spring Boot erzeugt daraus weiterhin eine JSON-Liste.

---

### Lösung 5: Produkte filtern

```java
return produkte.stream()
        .filter(produkt -> produkt.getPreis() > 0)
        .map(produkt -> toResponseDto(produkt))
        .toList();
```

Antworten:

- Im Stream bleiben nur Produkte mit Preis grösser `0`.
- `filter()` steht vor `map()`, weil die Bedingung auf dem Fachobjekt `Produkt` einfacher lesbar ist.
- Produkte, die den Filter nicht erfüllen, werden nicht in ein DTO transformiert.

---

### Lösung 6: REST-Ausgabe erneut testen

Prüfung:

```bash
curl -i http://localhost:8080/produkte
```

Erwartung:

- Statuscode ist erfolgreich.
- Body ist weiterhin eine JSON-Liste.
- Jedes Element enthält die Felder des Response-DTOs.
- Interne Felder erscheinen nicht unnötig.

Beispiel:

```json
[
  {
    "id": 1,
    "name": "Tastatur",
    "preis": 49.9
  },
  {
    "id": 2,
    "name": "Maus",
    "preis": 24.9
  }
]
```

---

### Lösung 7: Schleife und Stream vergleichen

Gleich bleibt:

- Die Ausgangsdaten sind `List<Produkt>`.
- Jedes Produkt wird in ein `ProduktResponseDto` umgewandelt.
- Das Ergebnis ist `List<ProduktResponseDto>`.
- Die REST-Ausgabe bleibt eine JSON-Liste.

Unterschied:

| Variante | Stärke |
|---|---|
| Schleife | zeigt jeden Einzelschritt sichtbar |
| Stream | zeigt die Transformation direkter |

---

## Vertiefung

### Lösung 8: `filter()` gezielt einsetzen

```java
return produkte.stream()
        .filter(produkt -> produkt.getPreis() >= 10.0)
        .map(produkt -> toResponseDto(produkt))
        .toList();
```

Erklärung:

```text
Nur Produkte mit Preis ab 10.0 werden in die REST-Ausgabe übernommen.
```

Die Antwort bleibt technisch eine JSON-Liste. Sie kann aber weniger Elemente enthalten.

Hinweis:

```text
Für diese Übung ist der Filter im Stream in Ordnung. Eine fachlich wichtige Filterregel sollte im Service geprüft werden.
```

---

### Lösung 9: Mehrere Transformationen bewusst klein halten

DTO:

```java
public class ProduktKurzDto {

    private Long id;
    private String name;

    public ProduktKurzDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
```

Mapping-Methode:

```java
private ProduktKurzDto toKurzDto(Produkt produkt) {
    return new ProduktKurzDto(
            produkt.getId(),
            produkt.getName()
    );
}
```

Stream:

```java
return produkte.stream()
        .map(produkt -> toKurzDto(produkt))
        .toList();
```

Hinweis:

```text
Die DTO-Erzeugung bleibt in einer eigenen Methode. Der Stream bleibt kurz.
```

---

### Lösung 10: Stream-Code mit Schleifen vergleichen

Stream:

```java
return produkte.stream()
        .filter(produkt -> produkt.getPreis() > 0)
        .map(produkt -> toResponseDto(produkt))
        .toList();
```

Passende Schleife:

```java
List<ProduktResponseDto> response = new ArrayList<>();

for (Produkt produkt : produkte) {
    if (produkt.getPreis() > 0) {
        response.add(toResponseDto(produkt));
    }
}

return response;
```

Zuordnung:

| Stream | Schleife |
|---|---|
| `filter(...)` | `if (produkt.getPreis() > 0)` |
| `map(...)` | `toResponseDto(produkt)` |
| `toList()` | `response.add(...)` und `return response` |

---

### Lösung 11: Logging bei Stream-Verarbeitung beobachten

Mögliche Beobachtung:

```text
Service lädt mehrere Produkt-Fachobjekte.
Controller startet eine Stream-Verarbeitung.
map() erzeugt Response-DTOs.
Spring Boot serialisiert die DTO-Liste als JSON-Liste.
```

Wichtig:

```text
Streams ändern nicht die Schichten der Anwendung.
```

---

### Lösung 12: Stream-Code lesbar halten

Der gezeigte Code ist noch verständlich, solange das DTO klein bleibt:

```java
return produkte.stream()
        .filter(produkt -> produkt.getPreis() > 0)
        .map(produkt -> new ProduktResponseDto(
                produkt.getId(),
                produkt.getName(),
                produkt.getPreis()
        ))
        .toList();
```

Bei mehr Feldern oder zusätzlicher Logik ist diese Variante besser:

```java
return produkte.stream()
        .filter(produkt -> produkt.getPreis() > 0)
        .map(produkt -> toResponseDto(produkt))
        .toList();
```

Fachlogik wie Preisberechnung, Lagerregeln oder Berechtigungen gehört nicht in den Lambda-Ausdruck.

---

## Transfer

### Lösung 13: Warum Streams sinnvoll sind

Streams passen zu REST-Listen und DTO-Mapping, weil sie mehrere Elemente einer Collection kontrolliert verarbeiten. Bei `GET /produkte` wird aus einer `List<Produkt>` eine `List<ProduktResponseDto>`. Diese Transformation kann mit `map()` direkt ausgedrückt werden.

---

### Lösung 14: Schleife oder Stream?

| Situation | Eher Schleife oder Stream? | Begründung |
|---|---|---|
| einfache DTO-Transformation | Stream | `map()` beschreibt die Transformation direkt |
| mehrere verschachtelte Bedingungen | Schleife | Einzelschritte bleiben oft lesbarer |
| lernende Person soll den Ablauf zuerst verstehen | Schleife | der Ablauf ist Schritt für Schritt sichtbar |
| bestehende Liste soll gefiltert und gemappt werden | Stream | `filter()` und `map()` passen gut zusammen |

---

### Lösung 15: `map()` und DTOs erklären

`map()` transformiert jedes Element eines Streams in ein neues Element.

Bei DTOs passt das gut:

```text
Produkt -> ProduktResponseDto
```

`map()` soll nicht für Seiteneffekte verwendet werden, weil sonst unklar wird, ob der Stream transformiert oder bestehende Objekte verändert.

---

### Lösung 16: `filter()` für REST-Ausgaben

`filter()` kann sinnvoll sein, wenn nur bestimmte Elemente in der REST-Ausgabe erscheinen sollen.

Beispiel:

```java
.filter(produkt -> produkt.getPreis() > 0)
```

Wenn die Filterbedingung fachlich wichtig oder komplex wird, gehört sie eher in den Service. Sonst wächst im Controller versteckte Fachlogik.

---

### Lösung 17: Streams ersetzen Collections nicht

```text
List<Produkt>
```

speichert die Produkte.

```text
Stream<Produkt>
```

verarbeitet die Produkte.

```text
List<ProduktResponseDto>
```

speichert das Ergebnis für die REST-Ausgabe.

Kurz:

```text
speichern -> verarbeiten -> Ergebnis speichern
```

---

## Typische Fehlerhinweise

- Wenn `toList()` fehlt, entsteht keine Rückgabeliste.
- Wenn `map()` und `filter()` verwechselt werden, passt der Ablauf nicht mehr.
- Wenn Lambda-Ausdrücke zu lang werden, sollte eine Methode ausgelagert werden.
- Wenn Fachlogik in Streams wandert, werden Verantwortlichkeiten unklar.
- Wenn Fachobjekte direkt zurückgegeben werden, ist die DTO-Trennung nicht mehr umgesetzt.
