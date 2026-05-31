# Übungen – Spring Container und Dependency Injection verstehen

## Ziel

Du erklärst an der REST-Lagerverwaltung, wie Controller, Service und Repository ohne Spring manuell verdrahtet würden und wie Spring diese Verdrahtung übernimmt.

Die zentrale Idee:

```text
Spring ersetzt die Architektur nicht.
Spring erzeugt und verbindet die Objekte.
```

---

## Vorwissen

Du solltest bereits können:

- Controller, Service und Repository unterscheiden
- `@RestController`, `@Service` und `@Repository` grob einordnen
- Constructor Injection im Controller und Service lesen
- Bruno oder `curl -i` für REST-Prüfungen verwenden

---

## Startpunkt

Verwende die bestehende REST-Lagerverwaltung.

Betrachte besonders:

- `ProduktController`
- `ProduktService`
- `ProduktRepository`

Falls deine Klassen anders heissen, verwende die entsprechenden Klassen aus deinem Projekt.

---

## Basis

### Aufgabe 1: Code mit manueller Objekterzeugung analysieren

Lies diesen Code:

```java
public class LagerApp {

    public static void main(String[] args) {
        ProduktRepository repository = new ProduktRepository();
        ProduktService service = new ProduktService(repository);
        ProduktController controller = new ProduktController(service);
    }
}
```

Beantworte:

- Welches Objekt wird zuerst erzeugt?
- Welches Objekt braucht welches andere Objekt?
- Welche Zeile verbindet Service und Repository?
- Welche Zeile verbindet Controller und Service?

---

### Aufgabe 2: Objektgraph ohne Spring zeichnen

Zeichne den Objektgraphen als Text oder Skizze.

Erwartete Richtung:

```text
ProduktController -> ProduktService -> ProduktRepository
```

Ergänze bei jedem Pfeil:

```text
braucht
```

---

### Aufgabe 3: Spring-Komponenten identifizieren

Suche in deiner Lagerverwaltung diese Annotationen:

- `@RestController`
- `@Service`
- `@Repository`

Notiere:

| Annotation | Klasse | Aufgabe |
|---|---|---|
| `@RestController` | | |
| `@Service` | | |
| `@Repository` | | |

---

### Aufgabe 4: Constructor Injection erklären

Lies den Konstruktor im Controller.

```java
public ProduktController(ProduktService produktService) {
    this.produktService = produktService;
}
```

Erkläre in zwei Sätzen:

- Was braucht der Controller?
- Wer übergibt dieses Objekt in der laufenden Spring-Anwendung?

---

### Aufgabe 5: Objektgraph mit Spring zeichnen

Zeichne den Objektgraphen mit Spring.

Nutze diese Begriffe:

- Spring Container
- `ProduktController`
- `ProduktService`
- `ProduktRepository`

Erwartete Aussage:

```text
Der Spring Container erzeugt die Objekte und verbindet sie über Konstruktoren.
```

---

### Aufgabe 6: Woher kommt der Service im Controller?

Beantworte schriftlich:

```text
Woher kommt der ProduktService im ProduktController, wenn im Controller kein new ProduktService(...) steht?
```

Nutze in deiner Antwort die Begriffe:

- Spring Container
- Konstruktor
- Abhängigkeit

---

### Aufgabe 7: Bruno-Requests nach DI-Struktur erneut prüfen

Führe bestehende Bruno-Requests erneut aus.

Prüfe mindestens:

| Request | Erwartung |
|---|---|
| `GET /produkte` | `200 OK` |
| unbekannte Produkt-ID | passende Fehlerantwort |
| gültiges `POST /produkte` | erfolgreiche Antwort |
| ungültiges `POST /produkte` | `400 Bad Request`, falls Validation vorhanden ist |

Erkläre danach:

```text
Warum sollten sich die REST-Antworten durch Dependency Injection nicht fachlich ändern?
```

---

## Vertiefung

### Aufgabe 8: Falsche Field Injection erkennen

Markiere, was an diesem Code ungünstig ist:

```java
@Autowired
private ProduktService produktService;
```

Erkläre:

- Warum sieht man die Pflichtabhängigkeit weniger gut?
- Wie sieht die Constructor-Injection-Variante aus?

---

### Aufgabe 9: `new`-Aufrufe im Controller finden

Suche im Controller nach:

```java
new ProduktService(...)
```

oder:

```java
new ProduktRepository()
```

Falls du solche Stellen findest, erkläre:

- Welche Verantwortung übernimmt der Controller dadurch zusätzlich?
- Warum passt das nicht zur Spring-Verdrahtung?

Hinweis:

```text
`new` in einfachen Unit-Tests ist nicht dasselbe wie `new` im Anwendungscode.
```

---

### Aufgabe 10: Verantwortlichkeiten markieren

Ordne die Aufgaben zu:

| Aufgabe | Controller, Service, Repository oder Spring Container? |
|---|---|
| HTTP-Request entgegennehmen | |
| Fachregel prüfen | |
| Produkt speichern | |
| `ProduktService` erzeugen | |
| `ProduktRepository` an den Service übergeben | |
| JSON-Antwort zurückgeben | |

---

### Aufgabe 11: Was macht `@Service` nicht?

Erkläre, warum diese Aussage falsch oder ungenau ist:

```text
@Service sorgt dafür, dass die Fachlogik automatisch ausgeführt wird.
```

Formuliere eine bessere Aussage.

---

### Aufgabe 12: Warum ersetzt Spring keine Fachlogik?

Erkläre an einem Beispiel aus der Lagerverwaltung:

- Welche Entscheidung trifft der Service?
- Was macht Spring stattdessen?
- Warum sind das zwei verschiedene Dinge?

---

## Transfer

### Aufgabe 13: Warum DI vor JPA wichtig ist

Erkläre:

```text
Warum ist es hilfreich, Dependency Injection zu verstehen, bevor JPA und Spring Data eingeführt werden?
```

---

### Aufgabe 14: Wie Spring später Repositorys bereitstellen kann

Spring Data wird noch nicht umgesetzt. Beschreibe nur die Idee:

- Der Service braucht weiterhin ein Repository.
- Spring kann später eine Repository-Implementierung bereitstellen.
- Der Service muss nicht wissen, wie diese Implementierung gebaut wird.

Formuliere daraus eine kurze Erklärung in eigenen Worten.

---

### Aufgabe 15: Architektur und Verdrahtung unterscheiden

Fülle aus:

| Aussage | Architektur oder Verdrahtung? |
|---|---|
| Controller ruft Service auf | |
| Service enthält Fachlogik | |
| Spring erzeugt `ProduktService` | |
| Spring übergibt Repository an Service | |
| Repository kapselt Datenzugriff | |

---

### Aufgabe 16: Constructor Injection begründen

Begründe in drei Sätzen:

- Warum ist der Konstruktor ein guter Ort für Pflichtabhängigkeiten?
- Warum ist `private final` hilfreich?
- Warum passt Constructor Injection besser zu dieser Ausbildungsreihe als Field Injection?

---

## Bewertungsfokus

Die Rückmeldung kann sich an diesen Standardbereichen orientieren:

| Bereich | Beobachtung |
|---|---|
| Funktionalität | Die REST-Lagerverwaltung funktioniert nach der Prüfung weiterhin |
| Verantwortlichkeiten | Controller, Service, Repository und Spring Container werden sauber unterschieden |
| Verständlichkeit | Der Objektgraph kann nachvollziehbar erklärt oder gezeichnet werden |
| Technische Sauberkeit | Constructor Injection wird erkannt und Field Injection vermieden |
| Lernzielerreichung | Spring wird als Infrastruktur verstanden, nicht als Ersatz für Architektur |
