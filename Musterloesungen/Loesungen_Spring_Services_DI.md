# Musterlösungen – Services mit Spring sauber integrieren

## Hinweis

Diese Musterlösung zeigt eine einfache Standardlösung.

Wichtig ist die Idee:

```text
Controller, Service und Repository bleiben fachlich getrennt.
Spring erzeugt und verbindet diese Objekte.
Constructor Injection macht Abhängigkeiten sichtbar.
```

---

## Basis

### Lösung 1: Manuelle `new`-Aufrufe suchen

Typische problematische Stelle im Controller:

```java
private final ProduktService produktService = new ProduktService(new ProduktRepository());
```

Problem:

```text
Der Controller weiss dadurch zu viel über die technische Erzeugung der Anwendung.
```

Nach der Umstellung soll der Controller den Service nur noch als Abhängigkeit verlangen.

---

### Lösung 2: `ProduktService` mit `@Service` markieren

```java
package ch.allianz.youngoitv.lager.service;

import org.springframework.stereotype.Service;

@Service
public class ProduktService {
}
```

Einordnung:

- `@Service` macht die Service-Klasse für Spring sichtbar.
- Die Klasse bleibt fachlich für Abläufe und Regeln zuständig.
- `@Service` bedeutet nicht, dass Spring die Fachlogik übernimmt.

---

### Lösung 3: Repository mit `@Repository` markieren

```java
package ch.allianz.youngoitv.lager.repository;

import org.springframework.stereotype.Repository;

@Repository
public class ProduktRepository {
}
```

Einordnung:

- `@Repository` macht die Repository-Klasse für Spring sichtbar.
- Das Repository bleibt für Datenzugriff zuständig.
- In dieser Einheit ist es noch kein Spring Data Repository.

---

### Lösung 4: Constructor Injection im Controller

```java
package ch.allianz.youngoitv.lager.api;

import ch.allianz.youngoitv.lager.service.ProduktService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produkte")
public class ProduktController {

    private final ProduktService produktService;

    public ProduktController(ProduktService produktService) {
        this.produktService = produktService;
    }
}
```

Erklärung:

```text
Der Controller erzeugt den Service nicht.
Spring übergibt den passenden ProduktService über den Konstruktor.
```

---

### Lösung 5: Constructor Injection im Service

```java
package ch.allianz.youngoitv.lager.service;

import ch.allianz.youngoitv.lager.repository.ProduktRepository;
import org.springframework.stereotype.Service;

@Service
public class ProduktService {

    private final ProduktRepository produktRepository;

    public ProduktService(ProduktRepository produktRepository) {
        this.produktRepository = produktRepository;
    }
}
```

Erklärung:

```text
Der Service erzeugt das Repository nicht.
Spring übergibt das passende ProduktRepository über den Konstruktor.
```

---

### Lösung 6: Anwendung starten und REST-Endpunkte prüfen

Start:

```bash
mvn spring-boot:run
```

Prüfung:

| Request | Erwartung |
|---|---|
| `GET /produkte` | `200 OK` |
| `GET /produkte/999` | `404 Not Found` |
| gültiges `POST /produkte` | `201 Created` |
| ungültiges `POST /produkte` | `400 Bad Request` |

Wenn die Anwendung nicht startet, ist häufig eine Bean nicht gefunden worden. Prüfe dann:

- Fehlt `@Service`?
- Fehlt `@Repository`?
- Liegt die Klasse ausserhalb des Spring-Boot-Scan-Bereichs?
- Hat der Konstruktor eine Abhängigkeit, die Spring nicht erzeugen kann?

---

### Lösung 7: Bruno-Requests erneut ausführen

Die bisherigen REST-Antworten sollen gleich bleiben.

Begründung:

```text
Dependency Injection ändert die technische Verdrahtung.
Sie ändert nicht die fachlichen Regeln der API.
```

---

## Vertiefung

### Lösung 8: Verantwortlichkeiten kontrollieren

| Frage | Erwartung |
|---|---|
| Bleibt HTTP im Controller? | ja |
| Bleibt Fachlogik im Service? | ja |
| Bleibt Datenzugriff im Repository? | ja |
| Gibt es keine Abkürzung vom Controller direkt zum Repository? | ja |
| Gibt es keine `ResponseEntity` im Service? | ja |

Typische Korrektur:

```text
Wenn der Controller direkt das Repository nutzt, wird wieder der Service eingeführt.
```

---

### Lösung 9: Field Injection erkennen

Ungünstig:

```java
@Autowired
private ProduktService produktService;
```

Besser:

```java
private final ProduktService produktService;

public ProduktController(ProduktService produktService) {
    this.produktService = produktService;
}
```

Begründung:

- Abhängigkeiten sind im Konstruktor sichtbar.
- `final` zeigt, dass die Abhängigkeit nach dem Erzeugen gesetzt bleibt.
- Die Klasse kann nicht sinnvoll ohne ihre Pflichtabhängigkeit erzeugt werden.

---

### Lösung 10: Controller klein halten

Gut getrennte Controller-Aufgaben:

- Request entgegennehmen
- DTOs mappen
- Validation-Ergebnis behandeln
- Service aufrufen
- HTTP-Antwort bauen

Nicht in den Controller gehören:

- Produktliste intern verwalten
- direktes Suchen im Repository
- fachliche Statuswechsel-Regeln
- Speicherlogik

---

### Lösung 11: Service- und Repository-Aufgaben unterscheiden

| Aufgabe | Zuständigkeit |
|---|---|
| `GET /produkte/999` in `404` übersetzen | Controller |
| prüfen, ob ein Statuswechsel fachlich erlaubt ist | Service |
| Produkt nach ID suchen | Repository |
| Produkt speichern | Repository |
| JSON-Request validieren | Controller mit Request-DTO |
| `ProduktDto` zurückgeben | Controller |

Hinweis:

```text
Je nach Projekt kann der Service das Repository für eine Suche aufrufen.
Der eigentliche Datenzugriff bleibt trotzdem im Repository.
```

---

### Lösung 12: Logging beobachten

Hilfreiche Beobachtungen:

- Die Anwendung startet ohne Bean-Fehler.
- Spring registriert die REST-Endpunkte.
- Es gibt keine Meldung wie `No qualifying bean`.

Typischer Fehler:

```text
No qualifying bean of type 'ProduktRepository' available
```

Mögliche Ursache:

```text
ProduktRepository ist nicht mit @Repository markiert oder liegt ausserhalb des Scan-Bereichs.
```

---

### Lösung 13: Fehlerfälle nach DI-Umstellung erneut testen

| Fall | Erwartung |
|---|---|
| unbekannte ID | `404` |
| ungültiger Status | `400` |
| ungültiger Request | `400` |

Begründung:

```text
Die Verdrahtung ändert sich, nicht die REST-Regeln.
```

---

## Transfer

### Lösung 14: Was verdrahtet Spring?

Mögliche Antwort:

```text
Spring erzeugt ProduktController, ProduktService und ProduktRepository.
Spring übergibt den ProduktService an den Controller.
Spring übergibt das ProduktRepository an den Service.
Die fachlichen Methoden bleiben weiterhin im eigenen Code sichtbar.
```

---

### Lösung 15: Warum ersetzt Spring die Architektur nicht?

| Baustein | Aufgabe |
|---|---|
| Controller | HTTP verarbeiten und Antwort bauen |
| Service | Fachlogik und Abläufe koordinieren |
| Repository | Daten speichern und laden |
| Spring | Objekte erzeugen und verbinden |

Spring ist Infrastruktur. Die Architekturentscheidungen bleiben im Code sichtbar.

---

### Lösung 16: Objekterzeugung und Fachlogik unterscheiden

| Aussage | Einordnung |
|---|---|
| Spring erstellt `ProduktService`. | Objekterzeugung |
| Service entscheidet, ob ein Statuswechsel erlaubt ist. | Fachlogik |
| Spring übergibt `ProduktRepository` an den Service. | Objekterzeugung/Verdrahtung |
| Repository sucht ein Produkt nach ID. | Datenzugriff |
| Controller baut eine `404`-Antwort. | HTTP-Antwort |

---

### Lösung 17: Constructor Injection begründen

Mögliche Antwort:

- Im Konstruktor sieht man sofort, welche Abhängigkeiten eine Klasse braucht.
- `private final` macht die Abhängigkeit stabil und nicht zufällig austauschbar.
- Field Injection versteckt Abhängigkeiten und ist für diese Ausbildungsstufe unnötig.

---

### Lösung 18: Vorbereitung auf JPA/Spring Data erklären

Mögliche Antwort:

```text
Vor JPA und Spring Data müssen Controller, Service und Repository sauber getrennt sein.
Sonst wirkt Spring Data später wie Magie und verdeckt, welche Verantwortung wohin gehört.
Dependency Injection zeigt zuerst, wie Spring bestehende Bausteine verbindet.
```

---

## Kurzer Prüfplan

| Prüfung | Erwartung |
|---|---|
| Anwendung startet | keine fehlenden Beans |
| `GET /produkte` | `200 OK` |
| Fehlerfälle | gleiche Statuscodes wie vor der Umstellung |
| Code-Review | keine Field Injection und keine falschen `new`-Aufrufe |

Der wichtigste Strukturpunkt:

```text
Spring verdrahtet.
Der Service bleibt für Fachlogik zuständig.
Das Repository bleibt für Datenzugriff zuständig.
```
