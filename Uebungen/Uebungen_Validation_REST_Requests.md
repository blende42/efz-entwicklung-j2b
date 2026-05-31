# Übungen – Validation bei REST-Requests

## Ziel

Du erweiterst die REST-Lagerverwaltung so, dass ungültige JSON-Requests früh erkannt und kontrolliert mit `400 Bad Request` beantwortet werden.

Die zentrale Idee:

```text
DTOs beschreiben die API-Eingabe.
Validation prüft einfache Eingaberegeln.
Der Service bleibt für Fachlogik zuständig.
```

---

## Vorwissen

Du solltest bereits können:

- REST Controller, Service und Repository unterscheiden
- DTOs für REST-Requests und REST-Antworten verwenden
- Fehlerantworten mit `ResponseEntity` einordnen
- `400 Bad Request` und `404 Not Found` unterscheiden
- `Optional` bei fehlenden Produkten erklären
- Bruno oder `curl -i` für REST-Tests verwenden

---

## Startpunkt

Verwende deine REST-Lagerverwaltung oder die Musterlösung aus dem Integrationsprojekt.

Gesucht wird der Endpunkt zum Erstellen eines Produkts:

```text
POST /produkte
```

Falls deine Anwendung noch kein eigenes Request-DTO verwendet, erstelle eines.

---

## Basis

### Aufgabe 1: Ungültige Requests untersuchen

Sende einen ungültigen Request an deine API.

Beispiel:

```bash
curl -i -X POST http://localhost:8080/produkte \
  -H "Content-Type: application/json" \
  -d '{"name":"","preis":-5,"status":"AKTIV"}'
```

Notiere:

- Welcher Statuscode kommt aktuell zurück?
- Wird das Produkt trotzdem erstellt?
- Ist die Fehlermeldung für einen Client verständlich?
- Entsteht ein technischer Fehler?

---

### Aufgabe 2: Validation-Starter ergänzen

Prüfe dein `pom.xml`.

Ergänze bei Bedarf:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

Starte danach die Anwendung neu.

---

### Aufgabe 3: Request-DTO erstellen

Erstelle ein Request-DTO für das Erstellen eines Produkts.

Beispiel:

```java
public record ProduktCreateRequest(
        String name,
        double preis,
        ProduktStatus status
) {
}
```

Prüfe:

- Das DTO liegt im API-/Controller-Bereich.
- Das DTO ist nicht das Fachmodell `Produkt`.
- Das DTO enthält nur Felder, die ein Client senden darf.

---

### Aufgabe 4: Einfache Regeln ergänzen

Ergänze Bean-Validation-Annotationen.

Regeln:

| Feld | Regel |
|---|---|
| `name` | darf nicht leer sein |
| `preis` | darf nicht negativ sein |
| `status` | muss vorhanden sein |

Nutze:

```java
@NotBlank
@PositiveOrZero
@NotNull
```

Ergänze verständliche Messages.

---

### Aufgabe 5: `@Valid` im Controller verwenden

Passe den `POST`-Endpunkt an.

Erwartung:

```java
public ResponseEntity<Object> produktErstellen(
        @Valid @RequestBody ProduktCreateRequest request,
        BindingResult bindingResult
) {
    // Validation prüfen
}
```

Wenn `bindingResult.hasErrors()` wahr ist, soll die API `400 Bad Request` zurückgeben.

Wichtig:

```text
Der Service soll keine ResponseEntity bauen.
```

---

### Aufgabe 6: Fehlerantwort für Validation bauen

Verwende die bestehende Fehlerstruktur oder erstelle eine einfache `ErrorResponse`.

Erwartung bei ungültigem Request:

```json
{
  "code": "VALIDATION_FAILED",
  "message": "Request ist ungültig.",
  "details": "name: Name darf nicht leer sein."
}
```

Es genügt, die erste Fehlermeldung zurückzugeben.

---

## Aufbau

### Aufgabe 7: Bruno-Requests erstellen

Erstelle oder ergänze Bruno-Requests:

| Name | Erwartung |
|---|---|
| `POST Produkt gültig` | `201 Created` |
| `POST Produkt leerer Name` | `400 Bad Request` |
| `POST Produkt negativer Preis` | `400 Bad Request` |
| `POST Produkt ohne Status` | `400 Bad Request` |

Prüfe jeweils:

- Statuscode
- JSON-Feld `code`
- JSON-Feld `message`
- JSON-Feld `details`

---

### Aufgabe 8: Validation und Fachlogik trennen

Ordne die folgenden Regeln zu:

| Regel | Gehört wohin? |
|---|---|
| Name darf nicht leer sein | |
| Preis darf nicht negativ sein | |
| Status muss vorhanden sein | |
| Produkt-ID darf nicht doppelt entstehen | |
| Statuswechsel von `ARCHIVIERT` zu `AKTIV` ist verboten | |

Begründe deine Zuordnung kurz.

---

### Aufgabe 9: Typischen Fehler finden

Prüfe deinen Code gezielt auf diese Fehler:

- `@Valid` fehlt im Controller.
- `BindingResult` wird nicht geprüft.
- `ResponseEntity` wird im Service gebaut.
- Validation steht nur auf dem Fachmodell, aber nicht auf dem Request-DTO.
- Fehlermeldungen enthalten technische Stacktraces.

Notiere mindestens eine Stelle, die du korrigiert hast oder bewusst geprüft hast.

---

## Vertiefung

### Aufgabe 10: Mehrere Fehlermeldungen sammeln

Passe deine Fehlerantwort so an, dass mehrere Validierungsfehler im Feld `details` sichtbar werden.

Eine einfache Variante genügt:

```text
name: Name darf nicht leer sein.; preis: Preis darf nicht negativ sein.
```

Prüfe danach diesen Request:

```json
{
  "name": "",
  "preis": -5,
  "status": null
}
```

Erwartung:

```text
400 Bad Request
```

---

### Aufgabe 11: Architekturgespräch vorbereiten

Bereite kurze Antworten vor:

- Warum liegt Validation auf dem Request-DTO?
- Warum bleibt der Service frei von `ResponseEntity`?
- Warum ist `400 Bad Request` bei ungültigen Eingaben korrekt?
- Was wäre an automatischen Mapping- oder Validation-Frameworks für diese Einheit zu viel?

---

## Bewertungsfokus

Die Rückmeldung kann sich an diesen Punkten orientieren:

| Bereich | Beobachtung |
|---|---|
| Funktionalität | Ungültige Requests werden mit `400` abgewiesen |
| Verantwortlichkeiten | DTO, Controller und Service bleiben getrennt |
| Verständlichkeit | Regeln und Fehlermeldungen sind nachvollziehbar |
| Technische Sauberkeit | `@Valid`, `BindingResult` und `ResponseEntity` werden passend verwendet |
| Lernzielerreichung | Validation wird als Eingabeprüfung verstanden, nicht als Fachlogik-Ersatz |
