# Übungen – Enums in Fachmodellen und REST-APIs

## Ziel

Du erweiterst die REST-Lagerverwaltung um kontrollierte Produktzustände.

Die bekannte Architektur bleibt:

```text
Client
-> REST Controller
-> LagerService
-> ProduktRepository
-> H2
```

Neu ist der kontrollierte Wertebereich:

```text
REST-JSON
-> DTO mit ProduktStatus
-> Fachmodell mit ProduktStatus
-> enum ProduktStatus
```

---

## Vorwissen

Du solltest bereits können:

- einfache REST-Endpunkte mit `GET` und `POST` einordnen
- Fachobjekte und DTOs unterscheiden
- DTO-Mapping lesen
- Listen mit Collections und Streams verarbeiten
- Bruno oder `curl` zur Prüfung von JSON verwenden

---

## Basis

### Aufgabe 1: String-Status suchen

Suche in deiner Lagerverwaltung nach Statuswerten als String.

Beispiele:

```java
private String status;
```

```java
produkt.setStatus("AKTIV");
```

Notiere:

- In welcher Klasse kommt der Status vor?
- Welche Werte werden verwendet?
- Gibt es unterschiedliche Schreibweisen?

Erwartetes Resultat:

```text
Du erkennst, wo Statuswerte bisher als unkontrollierte Strings vorkommen.
```

---

### Aufgabe 2: `ProduktStatus`-Enum erstellen

Erstelle ein Enum für den Produktstatus.

```java
public enum ProduktStatus {
    AKTIV,
    INAKTIV,
    NICHT_LIEFERBAR
}
```

Auftrag:

- Lege das Enum in einem fachlich passenden Package ab.
- Verwende sprechende Enum-Werte.
- Ergänze keine Methoden im Enum.

Erwartetes Resultat:

```text
Die erlaubten Produktstatuswerte sind an einer Stelle sichtbar.
```

---

### Aufgabe 3: String-Status ersetzen

Ersetze im Fachmodell den String-Status durch `ProduktStatus`.

Vorher:

```java
private String status;
```

Nachher:

```java
private ProduktStatus status;
```

Passe Konstruktoren, Getter und Setter an, falls dein Projekt solche Methoden verwendet.

Prüfe:

- Der Status ist kein `String` mehr.
- Bestehende Beispielprodukte verwenden `ProduktStatus.AKTIV` oder einen anderen Enum-Wert.
- Der Code bleibt klein und nachvollziehbar.

---

### Aufgabe 4: DTO um Enum erweitern

Erweitere das Response-DTO um den Status.

Beispiel mit Record:

```java
public record ProduktResponseDto(
        Long id,
        String name,
        double preis,
        ProduktStatus status
) {
}
```

Oder mit Klasse:

```java
public class ProduktResponseDto {

    private Long id;
    private String name;
    private double preis;
    private ProduktStatus status;
}
```

Passe das Mapping an:

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

---

### Aufgabe 5: REST-JSON mit Enum testen

Starte die Anwendung und rufe die Produktliste auf.

```bash
curl -i http://localhost:8080/produkte
```

Prüfe dieselbe Anfrage in Bruno.

Erwartete JSON-Idee:

```json
{
  "id": 1,
  "name": "Tastatur",
  "preis": 49.9,
  "status": "AKTIV"
}
```

Beantworte:

- Wie erscheint der Enum-Wert im JSON?
- Ist die JSON-Struktur weiterhin kontrolliert?
- Welche Felder gehören zum Response-DTO?

---

### Aufgabe 6: `switch` mit Enum verwenden

Erstelle eine kleine Methode, die einen Statustext erzeugt.

```java
public String beschreibungFuer(ProduktStatus status) {
    return switch (status) {
        case AKTIV -> "Produkt ist sichtbar";
        case INAKTIV -> "Produkt ist ausgeblendet";
        case NICHT_LIEFERBAR -> "Produkt ist aktuell nicht lieferbar";
    };
}
```

Auftrag:

- Verwende `ProduktStatus` als Parameter.
- Verwende keinen String-Vergleich.
- Halte die Methode klein.

---

### Aufgabe 7: Ungültige Statuswerte analysieren

Sende mit Bruno oder `curl` testweise einen ungültigen Statuswert, falls dein Request-DTO bereits einen Status enthält.

Beispiel:

```json
{
  "name": "Monitor",
  "preis": 159.9,
  "status": "aktiv"
}
```

Beobachte:

- Wird der Request verarbeitet?
- Welche Fehlermeldung oder welcher Statuscode erscheint?
- Warum passt `"aktiv"` nicht zu `AKTIV`?

Hinweis:

```text
Eine saubere REST-Fehlerbehandlung folgt später.
Hier geht es nur um die Beobachtung.
```

---

### Aufgabe 8: Bruno-Requests erneut testen

Teste deine bisherigen Bruno-Requests erneut:

- Produktliste laden
- einzelnes Produkt laden, falls vorhanden
- Produkt erfassen, falls vorhanden
- Produktstatus prüfen

Prüfe:

- Die Response enthält den erwarteten Status.
- Alte Requests sind angepasst, wenn sie einen Status senden.
- Die API-Struktur ist weiterhin verständlich.

---

## Vertiefung

### Aufgabe 9: Mehrere Statuswerte ergänzen

Ergänze bewusst mehrere Statuswerte im Projekt.

Beispiel:

```java
new Produkt(1L, "Tastatur", 49.9, ProduktStatus.AKTIV);
new Produkt(2L, "Maus", 24.9, ProduktStatus.INAKTIV);
new Produkt(3L, "Monitor", 159.9, ProduktStatus.NICHT_LIEFERBAR);
```

Rufe danach `GET /produkte` auf und analysiere die REST-Ausgabe.

---

### Aufgabe 10: Produkte nach Status filtern

Erstelle eine Variante, die nur aktive Produkte zurückgibt.

```java
return produkte.stream()
        .filter(produkt -> produkt.getStatus() == ProduktStatus.AKTIV)
        .map(produkt -> toResponseDto(produkt))
        .toList();
```

Beantworte:

- Warum ist `ProduktStatus.AKTIV` klarer als `"AKTIV"`?
- Was bleibt im Stream?
- Warum steht `filter()` vor `map()`?

---

### Aufgabe 11: Logging mit Statuswerten beobachten

Ergänze an einer passenden Stelle ein einfaches Log oder eine Konsolenausgabe, falls dein Projekt Logging bereits verwendet.

Beispiel:

```java
log.info("Produkt {} hat Status {}", produkt.getName(), produkt.getStatus());
```

Prüfe:

- Welcher Enum-Wert erscheint im Log?
- Ist der Status gut lesbar?
- Hilft der Status beim Nachvollziehen des Ablaufs?

---

### Aufgabe 12: Enum-Verwendung mit Strings vergleichen

Vergleiche die alte String-Variante mit der neuen Enum-Variante.

Alte String-Variante:

```java
"AKTIV".equals(statusText)
```

Neue Enum-Variante:

```java
produkt.getStatus() == ProduktStatus.AKTIV
```

Notiere:

- Welche Variante schützt besser vor Tippfehlern?
- Welche Variante zeigt den erlaubten Wertebereich klarer?
- Welche Variante passt besser zum Fachmodell?

---

## Transfer

### Aufgabe 13: Sinn von Enums erklären

Erkläre in eigenen Worten:

```text
Enums sind sinnvoll, wenn ein Wert nur aus einer kleinen festen Auswahl stammen darf.
```

Verwende ein Beispiel aus der Lagerverwaltung.

---

### Aufgabe 14: Unterschied zwischen String und Enum erklären

Vergleiche:

| String | Enum |
|---|---|
| beliebiger Text möglich | nur definierte Werte möglich |
| Tippfehler oft spät sichtbar | Tippfehler im Code früher sichtbar |
| erlaubte Werte verstreut | erlaubte Werte an einer Stelle |

Ergänze zu jeder Zeile ein eigenes Beispiel.

---

### Aufgabe 15: Kontrollierte Zustände begründen

Begründe:

- Warum sind kontrollierte Zustände in Fachmodellen wichtig?
- Warum profitiert REST von stabilen Statuswerten?
- Warum wird ein Fachmodell mit Enums lesbarer?

---

## Bewertungsfokus

Nutze für eine kurze Selbst- oder Partnerbeurteilung die Standardbereiche.

| Bereich | Einschätzung | Beobachtung |
|---|---|---|
| Funktionalität | | Funktioniert die REST-Ausgabe mit Enum-Status? |
| Verantwortlichkeiten | | Bleiben Status und Mapping in passenden Klassen? |
| Verständlichkeit | | Sind Enum-Name, Werte und DTO-Felder klar benannt? |
| Technische Sauberkeit | | Werden keine unnötigen String-Vergleiche verwendet? |
| Lernzielerreichung | | Kannst du erklären, warum `ProduktStatus` den Wertebereich kontrolliert? |

Mögliche Einschätzungen:

- gut erfüllt
- teilweise erfüllt
- noch unklar
- Verbesserungsbedarf

---

## Reflexion

Beantworte zum Abschluss:

- Wo hat das Enum den Code klarer gemacht?
- Welche Stelle wäre mit Strings fehleranfälliger?
- Welche REST-JSON-Werte müssen für Clients stabil bleiben?
- Welche Enum-Erweiterung wäre sinnvoll und welche wäre übertrieben?
