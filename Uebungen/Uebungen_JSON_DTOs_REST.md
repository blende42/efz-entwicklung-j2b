# Übungen – JSON-Strukturen und DTOs in REST-APIs

## Ziel

Du entwickelst die bestehende REST-Lagerverwaltung weiter.

Die REST-Endpunkte sollen nicht mehr unkontrolliert interne Fachobjekte als JSON veröffentlichen. Stattdessen verwendest du einfache DTOs und manuelles Mapping.

---

## Vorwissen

Du solltest bereits können:

- Spring Boot starten
- `GET` und `POST` in einem REST Controller einordnen
- JSON im Request Body und Response Body erkennen
- `curl` und Bruno für REST-Aufrufe verwenden
- den `LagerService` von Controller und Repository unterscheiden

---

## Ausgangslage

Die Architektur bleibt:

```text
Client
-> REST Controller
-> LagerService
-> ProduktRepository
-> H2
```

Neu kommt dazu:

```text
REST Controller
-> DTO
-> manuelles Mapping
-> Fachobjekt / Service
```

Du arbeitest an der bestehenden REST-Lagerverwaltung mit Produkten.

---

## Basis

### Aufgabe 1: Aktuelle JSON-Ausgabe analysieren

Starte die Spring-Boot-Anwendung.

Rufe die Produktliste mit `curl` auf:

```bash
curl -i http://localhost:8080/produkte
```

Führe denselben Request in Bruno aus.

Notiere:

- Welche Felder erscheinen im JSON?
- Welche Felder kommen direkt aus dem Fachobjekt?
- Gibt es Felder, die ein Client nicht kennen muss?
- Sind die Feldnamen für eine öffentliche API verständlich?

Erwartetes Resultat:

```text
Du kannst beschreiben, wie die aktuelle JSON-Struktur aussieht.
```

---

### Aufgabe 2: Unnötige Felder identifizieren

Vergleiche Fachobjekt und JSON-Ausgabe.

Erstelle eine kleine Tabelle:

| Feld | intern nötig? | extern nötig? | Begründung |
|---|---|---|---|
| `id` | ja | ja | identifiziert ein Produkt |
| `name` | ja | ja | für Clients sichtbar |
| `preis` | ja | ja | für Clients sichtbar |
| weiteres Feld | ja/nein | ja/nein | kurze Begründung |

Falls dein Fachobjekt aktuell nur wenige Felder hat, überlege trotzdem:

```text
Was würde passieren, wenn später interne technische Felder dazukommen?
```

---

### Aufgabe 3: Erstes Response-DTO erstellen

Erstelle im API-Bereich ein DTO für die Ausgabe.

Vorschlag:

```text
ch.allianz.youngoitv.lager.api.dto.ProduktResponseDto
```

Das DTO enthält nur Felder, die im JSON sichtbar sein sollen:

```java
private Long id;
private String name;
private double preis;
```

Erwartetes Resultat:

- Klasse ist klein.
- Klasse enthält keine Fachlogik.
- Klasse enthält keine Repository- oder Datenbanklogik.

---

### Aufgabe 4: Manuelles Mapping implementieren

Ergänze im Controller eine private Mapping-Methode:

```java
private ProduktResponseDto toResponseDto(Produkt produkt) {
    return new ProduktResponseDto(
            produkt.getId(),
            produkt.getName(),
            produkt.getPreis()
    );
}
```

Passe die Namen an dein bestehendes Fachobjekt an.

Erwartetes Resultat:

```text
Du kannst erklären, welches Feld aus dem Fachobjekt in welches DTO-Feld übertragen wird.
```

---

### Aufgabe 5: GET mit DTO-Ausgabe verwenden

Passe den GET-Endpunkt an.

Vorher ungefähr:

```java
@GetMapping
public List<Produkt> alleProdukte() {
    return lagerService.alleProdukte();
}
```

Nachher:

```java
@GetMapping
public List<ProduktResponseDto> alleProdukte() {
    List<Produkt> produkte = lagerService.alleProdukte();
    List<ProduktResponseDto> response = new ArrayList<>();

    for (Produkt produkt : produkte) {
        response.add(toResponseDto(produkt));
    }

    return response;
}
```

Falls `ArrayList` noch nicht importiert ist, ergänze:

```java
import java.util.ArrayList;
```

Prüfe danach:

```bash
curl -i http://localhost:8080/produkte
```

Führe auch den Bruno-Request erneut aus.

Erwartetes Resultat:

- Der Statuscode bleibt erfolgreich.
- Die JSON-Struktur enthält nur die DTO-Felder.
- Der Controller gibt keine `Produkt`-Liste mehr direkt zurück.

---

## Vertiefung

### Aufgabe 6: Request-DTO für POST erstellen

Erstelle ein DTO für die Eingabe.

Vorschlag:

```text
ProduktRequestDto
```

Dieses DTO enthält nur, was der Client beim Erstellen senden soll:

```java
private String name;
private double preis;
```

Wichtig:

```text
Die ID wird beim Erstellen nicht vom Client gesetzt.
```

---

### Aufgabe 7: POST mit DTO verarbeiten

Passe den POST-Endpunkt an.

Vorher ungefähr:

```java
@PostMapping
public Produkt produktErstellen(@RequestBody Produkt produkt) {
    return lagerService.produktErstellen(produkt);
}
```

Nachher ungefähr:

```java
@PostMapping
public ProduktResponseDto produktErstellen(@RequestBody ProduktRequestDto request) {
    Produkt produkt = new Produkt(request.getName(), request.getPreis());
    Produkt gespeichert = lagerService.produktErstellen(produkt);
    return toResponseDto(gespeichert);
}
```

Passe Konstruktoren und Methodennamen an deinen Code an.

Teste mit:

```bash
curl -i -X POST http://localhost:8080/produkte \
  -H "Content-Type: application/json" \
  -d '{"name":"Maus","preis":24.9}'
```

Erwartetes Resultat:

- Der Request Body passt zum Request-DTO.
- Die Response passt zum Response-DTO.
- Der Service arbeitet weiterhin mit internen Fachobjekten.

---

### Aufgabe 8: Fachobjekt und DTO vergleichen

Erstelle eine kurze Gegenüberstellung:

| Struktur | Aufgabe | Sichtbarkeit |
|---|---|---|
| `Produkt` | interne Fachstruktur | Server intern |
| `ProduktRequestDto` | JSON-Eingabe für POST | öffentlich über API |
| `ProduktResponseDto` | JSON-Ausgabe für GET/POST | öffentlich über API |

Ergänze zu jeder Struktur ein Beispiel mit den wichtigsten Feldern.

---

### Aufgabe 9: JSON-Struktur bewusst vereinfachen

Wähle eine kleine Vereinfachung für die API-Struktur.

Beispiele:

- Ein internes Feld wird nicht ausgegeben.
- Ein technischer Name wird im DTO verständlicher benannt.
- `POST` akzeptiert weniger Felder als `GET` ausgibt.

Dokumentiere:

- Was ist intern?
- Was ist extern?
- Warum ist die externe Struktur für Clients sinnvoller?

---

### Aufgabe 10: API-Struktur dokumentieren

Dokumentiere die neue API kurz in einer Markdown-Notiz oder direkt in deinem Übungsprotokoll.

Mindestinhalt:

```text
GET /produkte
Response JSON:
[
  {
    "id": 1,
    "name": "Tastatur",
    "preis": 49.9
  }
]

POST /produkte
Request JSON:
{
  "name": "Maus",
  "preis": 24.9
}
```

Ergänze, welche DTO-Klasse zu welcher JSON-Struktur gehört.

---

### Aufgabe 11: Logging beobachten

Falls in deiner Anwendung bereits Logging vorhanden ist, beobachte beim Aufruf:

- Wird der Controller erreicht?
- Wird der Service aufgerufen?
- Wird das Repository verwendet?
- Siehst du im Log Fachobjekte, DTOs oder beides?

Schreibe kurz auf:

```text
Wo tauchen DTOs auf?
Wo bleiben Fachobjekte sinnvoll?
```

---

## Transfer

### Aufgabe 12: DTOs begründen

Erkläre in eigenen Worten:

```text
Warum sind DTOs bei REST-APIs sinnvoll?
```

Verwende mindestens diese Begriffe:

- Fachobjekt
- JSON
- öffentliche Schnittstelle
- Mapping

---

### Aufgabe 13: REST-API als öffentliche Schnittstelle erklären

Begründe:

```text
Warum muss eine REST-API stabiler behandelt werden als eine interne Java-Klasse?
```

Denke an Clients, Bruno-Requests, Frontends und spätere Integrationen.

---

### Aufgabe 14: Direkte Objektserialisierung diskutieren

Diskutiere zu zweit oder schriftlich:

```text
Wann ist direkte Objektserialisierung für den Einstieg praktisch?
Wann wird sie problematisch?
```

Halte mindestens drei Risiken fest.

---

### Aufgabe 15: Mapping als eigene Verantwortung erklären

Erkläre:

- Warum gehört Mapping nicht ins Repository?
- Warum gehört Fachlogik nicht ins DTO?
- Warum darf der Controller Mapping enthalten, solange es klein bleibt?
- Wann könnte später eine eigene Mapper-Klasse sinnvoll werden?

Keine Mapping-Frameworks verwenden.

---

## Abgabe

Gib ab:

- kurze Analyse der alten JSON-Ausgabe
- DTO-Klassen
- angepasste GET- und POST-Endpunkte
- Mapping-Code
- Bruno- oder `curl`-Nachweis der neuen JSON-Struktur
- kurze Reflexion zu Fachobjekt, DTO und API-Struktur

---

## Checkliste

- `GET /produkte` gibt DTOs zurück.
- `POST /produkte` nimmt ein Request-DTO entgegen.
- Die Response verwendet ein Response-DTO.
- Das Repository kennt keine DTOs.
- DTOs enthalten keine Fachlogik.
- Bruno-Requests wurden erneut ausgeführt.
- Die JSON-Struktur wurde bewusst geprüft.

---

# Bewertungsfokus

Die Bewertung orientiert sich an den zentralen [Bewertungskriterien](../docs/didaktik/bewertungskriterien.md).

Es gibt keine Punkte und keine Notenskala. Die Rückmeldung soll sichtbar machen, wie gut die Lösung die DTO- und REST-Idee umsetzt.

| Bereich | Einschätzung | Beobachtung |
|---|---|---|
| Funktionalität | | REST-Endpunkte funktionieren mit DTOs. `GET` liefert DTO-Ausgaben, `POST` verarbeitet ein Request-DTO. |
| Verantwortlichkeiten | | DTOs sind von Fachobjekten getrennt. Controller, Service und Repository behalten ihre Aufgaben. |
| Verständlichkeit | | JSON-Struktur, DTO-Klassen und Mapping sind nachvollziehbar benannt und erklärbar. |
| Technische Sauberkeit | | Mapping ist sinnvoll platziert, Controller bleiben klein und DTOs werden nicht unnötig komplex. |
| Lernzielerreichung | | Die Lösung zeigt, dass interne Fachobjekte und öffentliche REST-/JSON-Strukturen unterschiedliche Verantwortlichkeiten haben. |

## Rückmeldung

- Stärken:
- Nächste Verbesserung:
- Offene Fragen:
