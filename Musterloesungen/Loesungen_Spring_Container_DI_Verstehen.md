# Musterlösungen – Spring Container und Dependency Injection verstehen

## Hinweis

Diese Musterlösung zeigt mögliche Standardantworten. Entscheidend ist nicht eine bestimmte Formulierung, sondern das saubere mentale Modell:

```text
Controller, Service und Repository bleiben fachlich getrennt.
Spring erzeugt und verbindet die Objekte.
```

---

## Basis

### Lösung 1: Code mit manueller Objekterzeugung analysieren

```java
ProduktRepository repository = new ProduktRepository();
ProduktService service = new ProduktService(repository);
ProduktController controller = new ProduktController(service);
```

Antwort:

- Zuerst wird das Repository erzeugt.
- Der Service braucht das Repository.
- Der Controller braucht den Service.
- `new ProduktService(repository)` verbindet Service und Repository.
- `new ProduktController(service)` verbindet Controller und Service.

---

### Lösung 2: Objektgraph ohne Spring zeichnen

Mögliche Darstellung:

```text
ProduktController
  braucht ProduktService
    braucht ProduktRepository
```

Oder kurz:

```text
ProduktController -> ProduktService -> ProduktRepository
```

Die Architektur ist bereits sichtbar. Ohne Spring müsste die Anwendung diese Objekte selbst erzeugen und weitergeben.

---

### Lösung 3: Spring-Komponenten identifizieren

| Annotation | Klasse | Aufgabe |
|---|---|---|
| `@RestController` | `ProduktController` | HTTP-Requests annehmen und Antworten zurückgeben |
| `@Service` | `ProduktService` | Fachlogik und Abläufe koordinieren |
| `@Repository` | `ProduktRepository` | Datenzugriff kapseln |

Hinweis:

```text
Die Annotation macht die Rolle für Spring sichtbar.
Sie ersetzt die fachliche Verantwortung nicht.
```

---

### Lösung 4: Constructor Injection erklären

Mögliche Antwort:

```text
Der ProduktController braucht einen ProduktService.
In der laufenden Spring-Anwendung erzeugt Spring den ProduktService und übergibt ihn über den Konstruktor.
```

---

### Lösung 5: Objektgraph mit Spring zeichnen

Mögliche Darstellung:

```text
Spring Container
  erzeugt ProduktRepository
  erzeugt ProduktService und übergibt ProduktRepository
  erzeugt ProduktController und übergibt ProduktService
```

Der Objektgraph bleibt:

```text
ProduktController -> ProduktService -> ProduktRepository
```

Nur die Verdrahtung wird von Spring übernommen.

---

### Lösung 6: Woher kommt der Service im Controller?

Mögliche Antwort:

```text
Der ProduktService kommt aus dem Spring Container.
Der Controller beschreibt im Konstruktor, dass er einen ProduktService braucht.
Spring erkennt diese Abhängigkeit und übergibt ein passendes Objekt.
```

---

### Lösung 7: Bruno-Requests nach DI-Struktur erneut prüfen

Erwartung:

| Request | Erwartung |
|---|---|
| `GET /produkte` | `200 OK` |
| unbekannte Produkt-ID | gleiche Fehlerantwort wie vorher |
| gültiges `POST /produkte` | erfolgreiche Antwort |
| ungültiges `POST /produkte` | `400 Bad Request`, falls Validation vorhanden ist |

Begründung:

```text
Dependency Injection ändert die Objekterzeugung und Verdrahtung.
Sie ändert nicht die fachlichen REST-Regeln.
```

---

## Vertiefung

### Lösung 8: Falsche Field Injection erkennen

Ungünstig:

```java
@Autowired
private ProduktService produktService;
```

Problem:

- Die Pflichtabhängigkeit ist nicht im Konstruktor sichtbar.
- Das Feld ist meist nicht `final`.
- Die Klasse wirkt, als könnte sie ohne Service erzeugt werden.

Besser:

```java
private final ProduktService produktService;

public ProduktController(ProduktService produktService) {
    this.produktService = produktService;
}
```

---

### Lösung 9: `new`-Aufrufe im Controller finden

Problematisches Beispiel:

```java
private final ProduktService produktService = new ProduktService(new ProduktRepository());
```

Begründung:

```text
Der Controller übernimmt dadurch technische Verdrahtung.
Er kennt sogar das Repository, obwohl er fachlich nur den Service kennen sollte.
```

In einfachen Unit-Tests ist `new` weiterhin erlaubt, weil dort bewusst ohne Spring-Kontext getestet wird.

---

### Lösung 10: Verantwortlichkeiten markieren

| Aufgabe | Zuständigkeit |
|---|---|
| HTTP-Request entgegennehmen | Controller |
| Fachregel prüfen | Service |
| Produkt speichern | Repository |
| `ProduktService` erzeugen | Spring Container |
| `ProduktRepository` an den Service übergeben | Spring Container |
| JSON-Antwort zurückgeben | Controller |

---

### Lösung 11: Was macht `@Service` nicht?

Die Aussage ist ungenau:

```text
@Service sorgt dafür, dass die Fachlogik automatisch ausgeführt wird.
```

Besser:

```text
@Service macht die Klasse für Spring als Service sichtbar.
Die Fachlogik steht weiterhin in den Methoden und wird nur ausgeführt, wenn diese Methoden aufgerufen werden.
```

---

### Lösung 12: Warum ersetzt Spring keine Fachlogik?

Mögliches Beispiel:

```text
Der Service entscheidet, ob ein Produktstatus geändert werden darf oder wie Produkte gefiltert werden.
Spring erzeugt den Service und übergibt ihm das Repository.
Das eine ist Fachlogik, das andere ist technische Verdrahtung.
```

---

## Transfer

### Lösung 13: Warum DI vor JPA wichtig ist

Mögliche Antwort:

```text
Vor JPA muss klar sein, dass der Service nur ein Repository braucht und dieses nicht selbst erzeugt.
Spring kann später ein Repository bereitstellen.
Wenn Dependency Injection nicht verstanden ist, wirkt Spring Data wie Magie.
```

---

### Lösung 14: Wie Spring später Repositorys bereitstellen kann

Mögliche Antwort:

```text
Der Service bleibt von einer Repository-Abhängigkeit abhängig.
Später kann Spring Data eine passende Repository-Implementierung bereitstellen.
Der Service arbeitet weiterhin gegen seine Repository-Rolle und muss die konkrete Erzeugung nicht kennen.
```

---

### Lösung 15: Architektur und Verdrahtung unterscheiden

| Aussage | Einordnung |
|---|---|
| Controller ruft Service auf | Architektur |
| Service enthält Fachlogik | Architektur |
| Spring erzeugt `ProduktService` | Verdrahtung |
| Spring übergibt Repository an Service | Verdrahtung |
| Repository kapselt Datenzugriff | Architektur |

---

### Lösung 16: Constructor Injection begründen

Mögliche Antwort:

```text
Der Konstruktor zeigt sofort, welche Pflichtabhängigkeiten eine Klasse braucht.
`private final` macht sichtbar, dass diese Abhängigkeit nach dem Erzeugen stabil bleibt.
Constructor Injection passt besser als Field Injection, weil sie für Lernende sichtbar und gut erklärbar ist.
```

---

## Kurzer Prüfplan

| Prüfung | Erwartung |
|---|---|
| Objektgraph ohne Spring | Controller -> Service -> Repository |
| Objektgraph mit Spring | gleiche Struktur, Spring verdrahtet |
| Verantwortlichkeiten | HTTP im Controller, Fachlogik im Service, Datenzugriff im Repository |
| Field Injection | wird als Fehlerbild erkannt |
| JPA/Spring Data | wird nur vorbereitet, nicht umgesetzt |
