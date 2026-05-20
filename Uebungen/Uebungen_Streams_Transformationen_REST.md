# Übungen – Streams und Transformationen in REST- und DTO-Anwendungen

## Ziel

Du erweiterst die REST-Lagerverwaltung so, dass Listen von Produkten mit Streams verarbeitet und in DTO-Listen transformiert werden.

Die bekannte Architektur bleibt:

```text
Client
-> REST Controller
-> LagerService
-> ProduktRepository
-> H2
```

Neu ist die Verarbeitung im Java-Code:

```text
List<Produkt>
-> stream()
-> filter()
-> map()
-> toList()
-> List<ProduktResponseDto>
-> JSON-Liste
```

---

## Vorwissen

Du solltest bereits können:

- REST-Endpunkte mit `GET` einordnen
- DTOs von Fachobjekten unterscheiden
- `List`, `Set` und `Map` grob unterscheiden
- DTO-Listen mit einer klassischen Schleife aufbauen
- Bruno oder `curl` zur Prüfung einer JSON-Antwort verwenden

---

## Basis

### Aufgabe 1: Schleifen-Mapping lesen

Suche im Controller oder in einer Mapping-Methode nach einem Ablauf wie diesem:

```java
List<ProduktResponseDto> response = new ArrayList<>();

for (Produkt produkt : produkte) {
    response.add(toResponseDto(produkt));
}

return response;
```

Markiere:

- Wo beginnt die Produktliste?
- Wo wird ein Produkt in ein DTO umgewandelt?
- Wo entsteht die Response-Liste?

Erwartetes Resultat:

```text
Du kannst erklären, welcher Teil der Schleife das DTO-Mapping ist.
```

---

### Aufgabe 2: `stream()` als Startpunkt verwenden

Erzeuge aus der Produktliste einen Stream:

```java
produkte.stream()
```

Erkläre kurz:

- Ist `produkte` weiterhin eine Liste?
- Was beschreibt der Stream?
- Warum beginnt die Verarbeitung mit `stream()`?

---

### Aufgabe 3: DTO-Mapping mit `map()` umsetzen

Ersetze die Schleife durch eine Stream-Transformation:

```java
List<ProduktResponseDto> response = produkte.stream()
        .map(produkt -> toResponseDto(produkt))
        .toList();
```

Auftrag:

- Baue den Code im Controller oder in einer passenden Mapping-Methode ein.
- Verwende die bestehende Methode `toResponseDto`.
- Schreibe keine Fachlogik direkt in den Lambda-Ausdruck.

Erwartetes Resultat:

```text
Aus einer List<Produkt> entsteht eine List<ProduktResponseDto>.
```

---

### Aufgabe 4: Stream-Ergebnis zurückgeben

Vereinfache den Rückgabecode:

```java
@GetMapping
public List<ProduktResponseDto> alleProdukte() {
    List<Produkt> produkte = lagerService.alleProdukte();

    return produkte.stream()
            .map(produkt -> toResponseDto(produkt))
            .toList();
}
```

Prüfe:

- Der Rückgabetyp bleibt `List<ProduktResponseDto>`.
- Es werden keine Fachobjekte direkt zurückgegeben.
- Die Methode bleibt gut lesbar.

---

### Aufgabe 5: Produkte filtern

Ergänze eine einfache Auswahl:

```java
return produkte.stream()
        .filter(produkt -> produkt.getPreis() > 0)
        .map(produkt -> toResponseDto(produkt))
        .toList();
```

Beantworte:

- Welche Produkte bleiben im Stream?
- Warum steht `filter()` vor `map()`?
- Was passiert mit Produkten, die den Filter nicht erfüllen?

---

### Aufgabe 6: REST-Ausgabe erneut testen

Starte die Anwendung und rufe die Produktliste auf:

```bash
curl -i http://localhost:8080/produkte
```

Führe denselben Request in Bruno aus.

Prüfe:

- Die Antwort ist weiterhin eine JSON-Liste.
- Jedes Element entspricht dem Response-DTO.
- Interne Felder erscheinen nicht unnötig.
- Die Stream-Umstellung hat die öffentliche JSON-Struktur nicht zufällig verändert.

---

### Aufgabe 7: Schleife und Stream vergleichen

Vergleiche beide Varianten:

```java
List<ProduktResponseDto> response = new ArrayList<>();

for (Produkt produkt : produkte) {
    response.add(toResponseDto(produkt));
}

return response;
```

```java
return produkte.stream()
        .map(produkt -> toResponseDto(produkt))
        .toList();
```

Notiere:

- Was ist in beiden Varianten gleich?
- Welche Variante zeigt die Einzelschritte stärker?
- Welche Variante zeigt die Transformation direkter?

---

## Vertiefung

### Aufgabe 8: `filter()` gezielt einsetzen

Erstelle als Übungsvariante eine Ausgabe, die nur Produkte mit Preis grösser oder gleich `10.0` zurückgibt:

```java
return produkte.stream()
        .filter(produkt -> produkt.getPreis() >= 10.0)
        .map(produkt -> toResponseDto(produkt))
        .toList();
```

Prüfe mit Bruno oder `curl`, ob die Antwort weiterhin eine JSON-Liste ist.

Erkläre:

```text
Der Filter entscheidet, welche Produkte in der REST-Ausgabe erscheinen.
```

Hinweis:

```text
Wenn die Filterregel fachlich wichtig wird, gehört sie eher in den Service.
```

---

### Aufgabe 9: Mehrere Transformationen bewusst klein halten

Angenommen, es gibt zusätzlich ein einfaches DTO für eine Kurzansicht:

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

Baue eine Mapping-Methode:

```java
private ProduktKurzDto toKurzDto(Produkt produkt) {
    return new ProduktKurzDto(
            produkt.getId(),
            produkt.getName()
    );
}
```

Nutze sie im Stream:

```java
return produkte.stream()
        .map(produkt -> toKurzDto(produkt))
        .toList();
```

Wichtig:

```text
Der Lambda-Ausdruck bleibt kurz. Die DTO-Erzeugung liegt in einer Methode.
```

---

### Aufgabe 10: Stream-Code mit Schleifen vergleichen

Schreibe zu diesem Stream die passende Schleifenvariante:

```java
return produkte.stream()
        .filter(produkt -> produkt.getPreis() > 0)
        .map(produkt -> toResponseDto(produkt))
        .toList();
```

Erwartete Struktur:

```java
List<ProduktResponseDto> response = new ArrayList<>();

for (Produkt produkt : produkte) {
    if (produkt.getPreis() > 0) {
        response.add(toResponseDto(produkt));
    }
}

return response;
```

Markiere:

- Welche Zeile entspricht `filter()`?
- Welche Zeile entspricht `map()`?
- Welche Zeile entspricht `toList()`?

---

### Aufgabe 11: Logging bei Stream-Verarbeitung beobachten

Falls Logging vorhanden ist, ergänze kein kompliziertes Logging im Stream.

Beobachte stattdessen an den bestehenden Stellen:

- Service lädt Produkte.
- Controller erzeugt DTOs.
- REST-Endpunkt liefert JSON zurück.

Notiere:

```text
Streams ändern nicht die Architektur. Sie ändern nur die Verarbeitung der Liste.
```

---

### Aufgabe 12: Stream-Code lesbar halten

Bewerte diesen Code:

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

Beantworte:

- Ist der Code noch lesbar?
- Wann wäre eine Methode `toResponseDto` besser?
- Welche Fachlogik sollte nicht in den Stream wandern?

---

## Transfer

### Aufgabe 13: Warum Streams sinnvoll sind

Erkläre in eigenen Worten:

```text
Warum passen Streams zu REST-Listen und DTO-Mapping?
```

Verwende mindestens diese Begriffe:

- Collection
- Transformation
- DTO
- REST-Ausgabe

---

### Aufgabe 14: Schleife oder Stream?

Entscheide für jede Situation, ob du eher eine Schleife oder einen Stream verwenden würdest:

| Situation | Eher Schleife oder Stream? | Begründung |
|---|---|---|
| einfache DTO-Transformation | | |
| mehrere verschachtelte Bedingungen | | |
| Lernende Person soll den Ablauf zuerst verstehen | | |
| bestehende Liste soll gefiltert und gemappt werden | | |

---

### Aufgabe 15: `map()` und DTOs erklären

Erkläre:

- Was macht `map()`?
- Warum passt `map()` zu `Produkt -> ProduktResponseDto`?
- Warum soll `map()` nicht für Seiteneffekte verwendet werden?

---

### Aufgabe 16: `filter()` für REST-Ausgaben

Erkläre:

- Warum kann `filter()` für REST-Ausgaben sinnvoll sein?
- Warum muss fachliche Filterlogik trotzdem sauber platziert werden?
- Was könnte passieren, wenn Filterbedingungen im Controller wachsen?

---

### Aufgabe 17: Streams ersetzen Collections nicht

Erkläre den Unterschied:

```text
List<Produkt>
Stream<Produkt>
List<ProduktResponseDto>
```

Nutze dazu den Ablauf:

```text
speichern -> verarbeiten -> Ergebnis speichern
```

---

## Abgabe

Gib ab:

- Codeausschnitt für DTO-Mapping mit `stream()`, `map()` und `toList()`
- Codeausschnitt mit einem einfachen `filter()`
- kurze Erklärung zu `map()`
- kurze Erklärung zu `filter()`
- Vergleich Schleife vs. Stream
- Bruno- oder `curl`-Nachweis einer JSON-Liste
- kurze Reflexion zur Lesbarkeit

---

## Checkliste

- Der Rückgabetyp bleibt `List<ProduktResponseDto>`.
- `stream()` startet von einer bestehenden Collection.
- `map()` wird für DTO-Transformation verwendet.
- `filter()` wird nur für einfache, nachvollziehbare Auswahl verwendet.
- `toList()` erzeugt das Ergebnis.
- Lambda-Ausdrücke bleiben kurz.
- Fachlogik wird nicht in lange Lambdas verschoben.
- Die REST-JSON-Struktur bleibt kontrolliert.

---

# Bewertungsfokus

Die Bewertung orientiert sich an den zentralen [Bewertungskriterien](../docs/didaktik/bewertungskriterien.md).

Es gibt keine Punkte und keine Notenskala. Die Rückmeldung soll sichtbar machen, ob Streams verständlich und passend für REST-/DTO-Transformationen eingesetzt werden.

| Bereich | Einschätzung | Beobachtung |
|---|---|---|
| Funktionalität | | Stream-Code erzeugt korrekte DTO-Listen und REST-Ausgaben bleiben prüfbar. |
| Verantwortlichkeiten | | Controller, Service und Mapping bleiben nachvollziehbar getrennt; Fachlogik wandert nicht unkontrolliert in Lambdas. |
| Verständlichkeit | | `stream()`, `map()`, `filter()` und `toList()` können erklärt und mit der Schleifenvariante verglichen werden. |
| Technische Sauberkeit | | Stream-Pipelines bleiben kurz, lesbar und ohne unnötige Seiteneffekte. |
| Lernzielerreichung | | Die Lösung zeigt Streams als kontrollierte Transformation von Collections in REST-/DTO-Anwendungen. |

## Rückmeldung

- Stärken:
- Nächste Verbesserung:
- Offene Fragen:
