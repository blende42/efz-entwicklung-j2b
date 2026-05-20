# Musterlösungen – JSON-Strukturen und DTOs in REST-APIs

## Hinweis

Diese Lösung zeigt eine mögliche Standardlösung für die REST-Lagerverwaltung.

Paketnamen, Konstruktoren und Methodennamen können in deinem Projekt leicht anders heissen. Die Verantwortlichkeiten sollen gleich bleiben:

```text
Controller verwendet DTOs.
Service verwendet Fachobjekte.
Repository kennt keine DTOs.
```

---

## Basis

### Lösung 1: JSON-Ausgabe analysieren

Beispiel für eine direkte Ausgabe von Fachobjekten:

```json
[
  {
    "id": 1,
    "name": "Tastatur",
    "preis": 49.9,
    "internerStatus": "AKTIV"
  }
]
```

Analyse:

| Feld | Bewertung |
|---|---|
| `id` | für Clients sinnvoll |
| `name` | für Clients sinnvoll |
| `preis` | für Clients sinnvoll |
| `internerStatus` | internes Detail, nicht zwingend Teil der API |

Kernaussage:

```text
Die JSON-Ausgabe folgt aktuell direkt der internen Fachstruktur.
```

---

### Lösung 2: Unnötige Felder identifizieren

Beispiel:

| Feld | intern nötig? | extern nötig? | Begründung |
|---|---|---|---|
| `id` | ja | ja | Client kann Produkte unterscheiden |
| `name` | ja | ja | fachlich sichtbar |
| `preis` | ja | ja | fachlich sichtbar |
| `internerStatus` | ja | nein | technische oder interne Steuerinformation |

Hinweis:

```text
Nicht jedes Feld, das intern nützlich ist, gehört automatisch in die API.
```

---

### Lösung 3: Response-DTO

```java
package ch.allianz.youngoitv.lager.api.dto;

public class ProduktResponseDto {

    private Long id;
    private String name;
    private double preis;

    public ProduktResponseDto(Long id, String name, double preis) {
        this.id = id;
        this.name = name;
        this.preis = preis;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPreis() {
        return preis;
    }
}
```

Das DTO enthält keine Fachlogik und keinen Datenbankzugriff.

---

### Lösung 4: Manuelles Mapping

```java
private ProduktResponseDto toResponseDto(Produkt produkt) {
    return new ProduktResponseDto(
            produkt.getId(),
            produkt.getName(),
            produkt.getPreis()
    );
}
```

Wenn dein Fachobjekt andere Namen verwendet, wird hier bewusst übersetzt.

Beispiel:

```java
private ProduktResponseDto toResponseDto(Produkt produkt) {
    return new ProduktResponseDto(
            produkt.getId(),
            produkt.getBezeichnung(),
            produkt.getPreis()
    );
}
```

Dann heisst das interne Feld `bezeichnung`, die API zeigt aber `name`.

---

### Lösung 5: GET mit DTO-Ausgabe

```java
package ch.allianz.youngoitv.lager.api;

import ch.allianz.youngoitv.lager.LagerService;
import ch.allianz.youngoitv.lager.api.dto.ProduktResponseDto;
import ch.allianz.youngoitv.lager.model.Produkt;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produkte")
public class ProduktController {

    private final LagerService lagerService;

    public ProduktController(LagerService lagerService) {
        this.lagerService = lagerService;
    }

    @GetMapping
    public List<ProduktResponseDto> alleProdukte() {
        List<Produkt> produkte = lagerService.alleProdukte();
        List<ProduktResponseDto> response = new ArrayList<>();

        for (Produkt produkt : produkte) {
            response.add(toResponseDto(produkt));
        }

        return response;
    }

    private ProduktResponseDto toResponseDto(Produkt produkt) {
        return new ProduktResponseDto(
                produkt.getId(),
                produkt.getName(),
                produkt.getPreis()
        );
    }
}
```

Prüfung mit `curl`:

```bash
curl -i http://localhost:8080/produkte
```

Erwartete JSON-Struktur:

```json
[
  {
    "id": 1,
    "name": "Tastatur",
    "preis": 49.9
  }
]
```

---

## Vertiefung

### Lösung 6: Request-DTO

```java
package ch.allianz.youngoitv.lager.api.dto;

public class ProduktRequestDto {

    private String name;
    private double preis;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }
}
```

Dieses DTO enthält keine ID, weil die ID nicht vom Client gesetzt wird.

---

### Lösung 7: POST mit DTO

```java
package ch.allianz.youngoitv.lager.api;

import ch.allianz.youngoitv.lager.LagerService;
import ch.allianz.youngoitv.lager.api.dto.ProduktRequestDto;
import ch.allianz.youngoitv.lager.api.dto.ProduktResponseDto;
import ch.allianz.youngoitv.lager.model.Produkt;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produkte")
public class ProduktController {

    private final LagerService lagerService;

    public ProduktController(LagerService lagerService) {
        this.lagerService = lagerService;
    }

    @GetMapping
    public List<ProduktResponseDto> alleProdukte() {
        List<Produkt> produkte = lagerService.alleProdukte();
        List<ProduktResponseDto> response = new ArrayList<>();

        for (Produkt produkt : produkte) {
            response.add(toResponseDto(produkt));
        }

        return response;
    }

    @PostMapping
    public ProduktResponseDto produktErstellen(@RequestBody ProduktRequestDto request) {
        Produkt produkt = new Produkt(request.getName(), request.getPreis());
        Produkt gespeichert = lagerService.produktErstellen(produkt);
        return toResponseDto(gespeichert);
    }

    private ProduktResponseDto toResponseDto(Produkt produkt) {
        return new ProduktResponseDto(
                produkt.getId(),
                produkt.getName(),
                produkt.getPreis()
        );
    }
}
```

Prüfung mit `curl`:

```bash
curl -i -X POST http://localhost:8080/produkte \
  -H "Content-Type: application/json" \
  -d '{"name":"Maus","preis":24.9}'
```

Mögliche Response:

```json
{
  "id": 2,
  "name": "Maus",
  "preis": 24.9
}
```

Hinweis:

```text
Der genaue Statuscode hängt vom bestehenden Controller ab.
Die DTO-Idee hängt nicht am Statuscode.
```

---

### Lösung 8: Fachobjekt und DTO vergleichen

| Struktur | Aufgabe | Sichtbarkeit |
|---|---|---|
| `Produkt` | interne Fachstruktur für Service und Fachlogik | Server intern |
| `ProduktRequestDto` | Eingabe von `POST /produkte` | API öffentlich |
| `ProduktResponseDto` | Ausgabe von `GET /produkte` und `POST /produkte` | API öffentlich |

Beispiel:

```text
Produkt:
- id
- name
- preis
- internerStatus

ProduktRequestDto:
- name
- preis

ProduktResponseDto:
- id
- name
- preis
```

---

### Lösung 9: JSON-Struktur bewusst vereinfachen

Beispiel:

Intern:

```text
Produkt
- id
- bezeichnung
- preis
- internerStatus
```

Extern:

```json
{
  "id": 1,
  "name": "Tastatur",
  "preis": 49.9
}
```

Begründung:

- `name` ist für Clients verständlicher als `bezeichnung`, wenn die API bereits englische Feldnamen nutzt.
- `internerStatus` bleibt eine interne Information.
- Die API zeigt nur die Daten, die ein Client für Produkte braucht.

---

### Lösung 10: API-Struktur dokumentieren

Beispiel:

```text
GET /produkte
Response DTO: ProduktResponseDto
Response JSON:
[
  {
    "id": 1,
    "name": "Tastatur",
    "preis": 49.9
  }
]

POST /produkte
Request DTO: ProduktRequestDto
Request JSON:
{
  "name": "Maus",
  "preis": 24.9
}

Response DTO: ProduktResponseDto
Response JSON:
{
  "id": 2,
  "name": "Maus",
  "preis": 24.9
}
```

---

### Lösung 11: Logging beobachten

Mögliche Beobachtung:

```text
Controller:
- nimmt ProduktRequestDto entgegen
- gibt ProduktResponseDto zurück

Service:
- arbeitet mit Produkt

Repository:
- speichert und lädt Produkt
```

Bewertung:

```text
DTOs gehören an die API-Grenze.
Fachobjekte bleiben in der internen Anwendung.
```

---

## Transfer

### Lösung 12: DTOs begründen

DTOs sind sinnvoll, weil eine REST-API eine öffentliche Schnittstelle ist. Clients sehen JSON und sollen nicht direkt von internen Fachobjekten abhängig sein. Das DTO beschreibt die API-Struktur. Das Fachobjekt bleibt für die interne Fachlogik zuständig. Das Mapping übersetzt zwischen beiden Verantwortlichkeiten.

---

### Lösung 13: REST-API als öffentliche Schnittstelle

Eine interne Java-Klasse kann innerhalb der Anwendung refaktoriert werden. Eine REST-API wird aber von Clients verwendet. Wenn sich Feldnamen oder JSON-Strukturen ändern, müssen Clients angepasst werden. Darum soll die API bewusst und stabil gestaltet werden.

---

### Lösung 14: Direkte Objektserialisierung diskutieren

Direkte Objektserialisierung ist am Anfang praktisch, weil wenig Code nötig ist und Spring Boot schnell sichtbares JSON liefert.

Sie wird problematisch, wenn:

- interne Felder öffentlich sichtbar werden
- interne Feldnamen zu API-Feldnamen werden
- Refactorings die API ungewollt verändern
- Fachmodell, REST-Struktur und Datenbankstruktur gekoppelt werden
- Clients auf zufällig entstandene JSON-Strukturen angewiesen sind

---

### Lösung 15: Mapping als eigene Verantwortung

Mapping gehört nicht ins Repository, weil das Repository für Datenzugriff zuständig ist.

Fachlogik gehört nicht ins DTO, weil ein DTO nur Daten für den Transport beschreibt.

Kleines Mapping darf im Controller stehen, wenn es übersichtlich bleibt und direkt die API-Struktur betrifft.

Eine eigene Mapper-Klasse kann später sinnvoll werden, wenn:

- mehrere Controller dasselbe Mapping brauchen
- das Mapping länger und unübersichtlich wird
- mehrere DTOs aus demselben Fachobjekt entstehen

In dieser Einheit bleibt das Mapping bewusst manuell und einfach.

---

## Typische Fehlerhinweise

- Wenn ein Controller weiterhin `List<Produkt>` zurückgibt, ist die Trennung noch nicht umgesetzt.
- Wenn das DTO Preisregeln prüft, enthält es Fachlogik.
- Wenn das Repository `ProduktResponseDto` zurückgibt, ist API-Struktur in die Persistenz gerutscht.
- Wenn Bruno nur den Statuscode prüft, bleibt die wichtigste Frage offen: Stimmt die JSON-Struktur?
