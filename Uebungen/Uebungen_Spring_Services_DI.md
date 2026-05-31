# Übungen – Services mit Spring sauber integrieren

## Ziel

Du stellst die REST-Lagerverwaltung so um, dass Spring Controller, Service und Repository erzeugt und über Konstruktoren verbindet.

Die zentrale Idee:

```text
Die Architektur bleibt gleich.
Spring erzeugt und verbindet die Objekte.
```

---

## Vorwissen

Du solltest bereits können:

- REST Controller, Service und Repository unterscheiden
- DTOs und Validation im REST Controller einordnen
- Fehlerantworten mit `ResponseEntity` verstehen
- `Optional` bei fehlenden Produkten verwenden
- Bruno oder `curl -i` für REST-Prüfungen einsetzen

---

## Startpunkt

Verwende deine REST-Lagerverwaltung oder die Musterlösung aus dem Integrationsprojekt.

Prüfe besonders diese Klassen:

- `ProduktController`
- `ProduktService`
- `ProduktRepository`

Falls deine Klassen leicht anders heissen, verwende die entsprechenden Namen aus deinem Projekt.

---

## Basis

### Aufgabe 1: Manuelle `new`-Aufrufe suchen

Suche im Controller und im Service nach manueller Objekterzeugung.

Typische Beispiele:

```java
new ProduktService(...)
```

```java
new ProduktRepository()
```

Notiere:

- In welcher Klasse wird `new` verwendet?
- Wird damit ein Service oder Repository erzeugt?
- Welche Klasse kennt dadurch zu viel?

Hinweis:

```text
Konzentriere dich auf Anwendungscode im Controller und Service.
`new` in einfachen Unit-Tests ist weiterhin erlaubt, weil dort gezielt ohne Spring-Kontext getestet wird.
```

---

### Aufgabe 2: `ProduktService` mit `@Service` markieren

Markiere den Service als Spring-Service.

```java
@Service
public class ProduktService {
}
```

Ergänze den passenden Import:

```java
import org.springframework.stereotype.Service;
```

Prüfe:

- Fachlogik bleibt im Service.
- Der Service baut keine `ResponseEntity`.
- Der Service bleibt frei von HTTP-Details.

---

### Aufgabe 3: Repository mit `@Repository` markieren

Markiere das Repository als Spring-Repository.

```java
@Repository
public class ProduktRepository {
}
```

Ergänze den passenden Import:

```java
import org.springframework.stereotype.Repository;
```

Prüfe:

- Datenzugriff bleibt im Repository.
- Das Repository baut keine HTTP-Antworten.
- Das Repository ist noch kein Spring Data Repository.

---

### Aufgabe 4: Constructor Injection im Controller verwenden

Passe den Controller so an, dass er den Service über den Konstruktor erhält.

```java
@RestController
@RequestMapping("/produkte")
public class ProduktController {

    private final ProduktService produktService;

    public ProduktController(ProduktService produktService) {
        this.produktService = produktService;
    }
}
```

Entferne manuelle Service-Erzeugung aus dem Controller.

---

### Aufgabe 5: Constructor Injection im Service verwenden

Passe den Service so an, dass er das Repository über den Konstruktor erhält.

```java
@Service
public class ProduktService {

    private final ProduktRepository produktRepository;

    public ProduktService(ProduktRepository produktRepository) {
        this.produktRepository = produktRepository;
    }
}
```

Entferne manuelle Repository-Erzeugung aus dem Service.

---

### Aufgabe 6: Anwendung starten und REST-Endpunkte prüfen

Starte die Anwendung mit Maven.

```bash
mvn spring-boot:run
```

Prüfe danach mit Bruno oder `curl -i`:

| Request | Erwartung |
|---|---|
| `GET /produkte` | `200 OK` |
| `GET /produkte/999` | `404 Not Found` |
| gültiges `POST /produkte` | `201 Created` |
| ungültiges `POST /produkte` | `400 Bad Request` |

---

### Aufgabe 7: Bruno-Requests erneut ausführen

Führe die Bruno-Requests aus den letzten Einheiten erneut aus:

- DTO-Requests
- Enum-/Status-Requests
- Fehlerbehandlungs-Requests
- Optional-Requests
- Validation-Requests

Erwartung:

```text
Die REST-Antworten bleiben gleich.
Nur die Verdrahtung im Code hat sich geändert.
```

---

## Vertiefung

### Aufgabe 8: Verantwortlichkeiten kontrollieren

Prüfe deinen Code:

| Frage | Ja/Nein |
|---|---|
| Bleibt HTTP im Controller? | |
| Bleibt Fachlogik im Service? | |
| Bleibt Datenzugriff im Repository? | |
| Gibt es keine Abkürzung vom Controller direkt zum Repository? | |
| Gibt es keine `ResponseEntity` im Service? | |

Korrigiere mindestens eine Stelle, falls eine Verantwortung vermischt wurde.

---

### Aufgabe 9: Field Injection erkennen

Suche nach Field Injection.

```java
@Autowired
private ProduktService produktService;
```

Ersetze sie durch Constructor Injection.

Begründe kurz:

- Warum sind Konstruktorabhängigkeiten sichtbarer?
- Warum ist ein `final`-Feld hilfreich?

---

### Aufgabe 10: Controller klein halten

Prüfe den Controller nach der Umstellung.

Der Controller darf:

- Request entgegennehmen
- DTOs mappen
- Validation-Ergebnis behandeln
- Service aufrufen
- HTTP-Antwort bauen

Der Controller soll nicht:

- Produktlisten selbst verwalten
- direkt im Repository suchen
- Statuswechsel fachlich entscheiden
- technische Speicherlogik enthalten

Notiere zwei Stellen, die gut getrennt sind.

---

### Aufgabe 11: Service- und Repository-Aufgaben unterscheiden

Ordne zu:

| Aufgabe | Controller, Service oder Repository? |
|---|---|
| `GET /produkte/999` in `404` übersetzen | |
| prüfen, ob ein Statuswechsel fachlich erlaubt ist | |
| Produkt nach ID suchen | |
| Produkt speichern | |
| JSON-Request validieren | |
| `ProduktDto` zurückgeben | |

---

### Aufgabe 12: Logging beobachten

Falls deine Anwendung Logging nutzt, beobachte den Start.

Prüfe:

- Startet Spring ohne Bean-Fehler?
- Werden Controller-Endpunkte registriert?
- Gibt es Fehlermeldungen zu fehlenden Beans?

Notiere eine Logzeile oder Fehlermeldung, die dir beim Verstehen der Verdrahtung geholfen hat.

---

### Aufgabe 13: Fehlerfälle nach DI-Umstellung erneut testen

Prüfe gezielt:

| Fall | Erwartung |
|---|---|
| unbekannte ID | `404` |
| ungültiger Status | `400` |
| ungültiger Request | `400` |

Erkläre:

```text
Warum sollen diese Antworten durch Dependency Injection nicht fachlich anders werden?
```

---

## Transfer

### Aufgabe 14: Was verdrahtet Spring?

Erkläre in drei Sätzen:

- Welche Objekte erzeugt Spring?
- Welche Abhängigkeiten werden über Konstruktoren übergeben?
- Welche Aufgaben bleiben trotzdem im Code sichtbar?

---

### Aufgabe 15: Warum ersetzt Spring die Architektur nicht?

Erkläre anhand deiner Lagerverwaltung:

- Was macht der Controller?
- Was macht der Service?
- Was macht das Repository?
- Was macht Spring?

---

### Aufgabe 16: Objekterzeugung und Fachlogik unterscheiden

Ordne die Aussagen zu:

| Aussage | Objekterzeugung oder Fachlogik? |
|---|---|
| Spring erstellt `ProduktService`. | |
| Service entscheidet, ob ein Statuswechsel erlaubt ist. | |
| Spring übergibt `ProduktRepository` an den Service. | |
| Repository sucht ein Produkt nach ID. | |
| Controller baut eine `404`-Antwort. | |

---

### Aufgabe 17: Constructor Injection begründen

Beantworte:

- Warum sieht man Abhängigkeiten im Konstruktor klar?
- Warum ist `private final` sinnvoll?
- Warum ist Field Injection in dieser Reihe kein Ziel?

---

### Aufgabe 18: Vorbereitung auf JPA/Spring Data erklären

Erkläre:

```text
Warum ist es hilfreich, zuerst Controller, Service und Repository sauber mit Spring zu verdrahten, bevor JPA oder Spring Data eingeführt wird?
```

---

## Bewertungsfokus

Die Rückmeldung kann sich an diesen Standardbereichen orientieren:

| Bereich | Beobachtung |
|---|---|
| Funktionalität | REST-Endpunkte funktionieren nach der DI-Umstellung weiterhin |
| Verantwortlichkeiten | Controller, Service und Repository bleiben getrennt |
| Verständlichkeit | Abhängigkeiten sind über Konstruktoren sichtbar |
| Technische Sauberkeit | `@RestController`, `@Service`, `@Repository` und Constructor Injection werden passend verwendet |
| Lernzielerreichung | Spring wird als Verdrahtung verstanden, nicht als Ersatz für Architektur |
