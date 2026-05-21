# Übungen – Optional und kontrollierte Null-Behandlung

## Ziel

Du erweiterst die REST-Lagerverwaltung so, dass fehlende Produkte nicht still mit `null`, sondern sichtbar mit `Optional` behandelt werden.

Die zentrale Idee:

```text
Ein Repository findet nicht immer ein Objekt.
Optional macht diesen Fall im Code sichtbar.
```

---

## Vorwissen

Du solltest bereits können:

- Repository, Service und REST Controller unterscheiden
- einfache REST-Fehlerantworten mit `ResponseEntity` einordnen
- `404 Not Found` bei fehlenden Produkten erklären
- Bruno oder `curl -i` für REST-Tests verwenden
- einfache Streams lesen

---

## Startpunkt

Verwende deine REST-Lagerverwaltung oder die Musterlösung aus dem Integrationsprojekt.

Gesucht wird vor allem die Methode, die ein Produkt über die ID findet.

Typische Namen:

- `findeNachId`
- `findeProdukt`
- `getProdukt`

---

## Basis

### Aufgabe 1: Aktuelle Null-Behandlung suchen

Suche im Repository und Service nach Stellen, an denen ein Produkt fehlen kann.

Prüfe besonders:

```java
return null;
```

```java
Produkt produkt = ...
```

Notiere:

- Welche Methode sucht ein Produkt nach ID?
- Was passiert aktuell, wenn kein Produkt gefunden wird?
- Entsteht eine klare REST-Antwort oder ein unklarer Fehler?

---

### Aufgabe 2: Repository auf `Optional` umstellen

Passe die Repository-Methode an.

Vorher, falls vorhanden:

```java
public Produkt findeNachId(Long id) {
    // Produkt oder null
}
```

Nachher:

```java
public Optional<Produkt> findeNachId(Long id) {
    return produkte.stream()
            .filter(produkt -> produkt.getId().equals(id))
            .findFirst();
}
```

Erwartung:

```text
Der Rückgabetyp zeigt sichtbar, dass kein Produkt gefunden werden kann.
```

---

### Aufgabe 3: Produkt per ID suchen

Ergänze im Service eine einfache Methode.

```java
public Optional<Produkt> findeProdukt(Long id) {
    return produktRepository.findeNachId(id);
}
```

Prüfe:

- Der Service gibt kein `null` zurück.
- Der Service baut keine `ResponseEntity`.
- Der Service bleibt fachlich.

---

### Aufgabe 4: Optional im Controller prüfen

Ergänze oder passe den Endpunkt an:

```text
GET /produkte/{id}
```

Verwende `isPresent()` oder `isEmpty()`.

Erwartung:

- Produkt vorhanden: `200` mit `ProduktDto`
- Produkt fehlt: `404` mit `ErrorResponse`

---

### Aufgabe 5: Bruno-Requests für fehlende IDs testen

Ergänze oder prüfe Bruno-Requests:

- `GET /produkte/1`
- `GET /produkte/999`

Prüfe mit `curl -i`:

```bash
curl -i http://localhost:8080/produkte/999
```

Erwartung:

- Statuscode `404`
- JSON-Fehlerantwort
- keine technische Stacktrace-Ausgabe

---

## Vertiefung

### Aufgabe 6: Service mit `orElseThrow()` erweitern

Prüfe die Methode für Statusänderungen.

Eine mögliche Variante:

```java
public Produkt statusAendern(Long id, ProduktStatus status) {
    Produkt produkt = produktRepository.findeNachId(id)
            .orElseThrow(() -> new IllegalArgumentException("Produkt nicht gefunden: " + id));

    produkt.setStatus(status);
    return produkt;
}
```

Beantworte:

- Warum ist hier `orElseThrow()` nachvollziehbar?
- Wer wandelt diesen Fehler später in eine REST-Antwort um?
- Wäre `Optional<Produkt>` als Rückgabe hier ebenfalls möglich?

---

### Aufgabe 7: Optional mit Streams vergleichen

Vergleiche:

```java
public Optional<Produkt> findeNachId(Long id) {
    return produkte.stream()
            .filter(produkt -> produkt.getId().equals(id))
            .findFirst();
}
```

und:

```java
public List<Produkt> alle() {
    return new ArrayList<>(produkte);
}
```

Erkläre:

- Warum passt `Optional<Produkt>` bei einer Suche nach ID?
- Warum passt bei `alle()` eine Liste besser?
- Warum wäre `Optional<List<Produkt>>` unnötig kompliziert?

---

### Aufgabe 8: `orElse()` sinnvoll einsetzen

Erstelle eine kleine Hilfsmethode für eine Anzeige.

Beispiel:

```java
public String produktNameOderUnbekannt(Long id) {
    Optional<Produkt> produkt = produktRepository.findeNachId(id);

    if (produkt.isPresent()) {
        return produkt.get().getName();
    }

    return "Unbekanntes Produkt";
}
```

Schreibe danach eine zweite Variante mit `orElse()`, indem du ein einfaches Ersatzprodukt verwendest.

Beispielidee:

```java
Produkt ersatzProdukt = new Produkt(0L, "Unbekanntes Produkt", 0.0, ProduktStatus.ARCHIVIERT);
```

Wichtig:

```text
Nutze orElse() nur dort, wo ein Ersatzwert fachlich sinnvoll ist.
```

---

### Aufgabe 9: Fehlerfälle sauber behandeln

Prüfe deine REST-Antworten:

| Fall | Erwartung |
|---|---|
| `GET /produkte/1` | `200` mit Produkt |
| `GET /produkte/999` | `404` mit Fehlerantwort |
| `PUT /produkte/999/status/AKTIV` | kontrollierter Fehlerfall |

Notiere:

- Wo entsteht `Optional.empty()`?
- Wo wird daraus `404`?
- Wo bleibt die Fachlogik?

---

### Aufgabe 10: Logging bei fehlenden Produkten ergänzen

Ergänze an einer passenden Stelle eine einfache Log-Ausgabe, wenn ein Produkt fehlt.

Beispiel:

```java
private static final Logger LOG = LoggerFactory.getLogger(ProduktController.class);
```

```java
LOG.info("Produkt wurde nicht gefunden: {}", id);
```

Prüfe:

- Die REST-Antwort bleibt gleich.
- Das Logging ersetzt keine Fehlerbehandlung.
- Die Meldung ist kurz und verständlich.

---

## Transfer

### Aufgabe 11: Optional erklären

Erkläre in eigenen Worten:

- Warum ist `Optional<Produkt>` aussagekräftiger als `Produkt`, wenn `null` möglich wäre?
- Warum zwingt `Optional` zu einer bewussten Entscheidung?
- Welche Entscheidung triffst du bei `Optional.empty()` im REST Controller?

---

### Aufgabe 12: `null` und `Optional` unterscheiden

Fülle die Tabelle aus:

| Situation | `null` | `Optional` |
|---|---|---|
| Produkt nicht gefunden | | |
| Rückgabetyp zeigt fehlenden Wert | | |
| Prüfung wird leicht vergessen | | |
| REST-Fehler kann bewusst entstehen | | |

---

### Aufgabe 13: Optional und REST-Fehlerbehandlung verbinden

Erkläre den Ablauf:

```text
Repository findet kein Produkt
-> Optional.empty()
-> Service gibt Ergebnis weiter oder wirft fachlichen Fehler
-> Controller baut 404 mit ErrorResponse
```

Beantworte:

- Welche Klasse kennt HTTP?
- Welche Klasse kennt die Speicherstruktur?
- Welche Klasse entscheidet über die fachliche Suche?

---

## Bewertungsfokus

| Bereich | Beobachtung |
|---|---|
| Funktionalität | Fehlende Produkte werden ohne `null` und mit kontrollierter REST-Antwort behandelt. |
| Verantwortlichkeiten | Repository, Service und Controller behalten klare Aufgaben. |
| Verständlichkeit | `Optional` wird einfach und lesbar verwendet. |
| Technische Sauberkeit | `isPresent()`, `isEmpty()`, `orElse()` und `orElseThrow()` werden passend eingesetzt. |
| Lernzielerreichung | Die lernende Person kann erklären, warum fehlende Werte sichtbar modelliert werden. |

Qualitative Einschätzungen:

- gut erfüllt
- teilweise erfüllt
- noch unklar
- Verbesserungsbedarf

---

## Abschluss

Halte fest:

- Welche Methode liefert jetzt `Optional<Produkt>`?
- Wo wird `Optional.empty()` zu einer `404`-Antwort?
- Wo wäre `null` besonders gefährlich gewesen?
