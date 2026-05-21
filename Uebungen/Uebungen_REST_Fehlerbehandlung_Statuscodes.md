# Übungen – REST-Fehlerbehandlung und Statuscodes

## Ziel

Du erweiterst die REST-Lagerverwaltung so, dass Fehlerfälle kontrolliert und verständlich zurückgegeben werden.

Die zentrale Idee:

```text
REST-APIs müssen nicht nur Erfolg, sondern auch Fehler klar kommunizieren.
```

---

## Vorwissen

Du solltest bereits können:

- REST-Endpunkte mit Spring Boot lesen
- DTOs von Fachobjekten unterscheiden
- `ProduktStatus` als Enum verwenden
- Controller, Service und Repository grob trennen
- Bruno oder `curl` für REST-Requests verwenden

---

## Startpunkt

Verwende deine REST-Lagerverwaltung oder die Musterlösung aus dem Integrationsprojekt.

Die Anwendung soll bereits Produkte verwalten und diese Statuswerte kennen:

- `AKTIV`
- `RESERVIERT`
- `DEFEKT`
- `ARCHIVIERT`

In dieser Übung ergänzt du kontrollierte Fehlerantworten. Du führst noch keine Bean Validation und keine globale Fehlerarchitektur ein.

---

## Basis

### Aufgabe 1: Erfolgsfall mit gültiger ID prüfen

Falls noch nicht vorhanden, ergänze einen Endpunkt für ein einzelnes Produkt.

```text
GET /produkte/{id}
```

Prüfe den Erfolgsfall:

```bash
curl -i http://localhost:8080/produkte/1
```

Erwartung:

- Statuscode `200`
- JSON mit `id`, `name`, `preis` und `status`
- keine direkte Ausgabe des Fachobjekts, sondern DTO-Ausgabe

---

### Aufgabe 2: Unbekannte ID prüfen

Rufe eine ID auf, die nicht existiert.

```bash
curl -i http://localhost:8080/produkte/999
```

Notiere:

- Welcher Statuscode kommt aktuell zurück?
- Ist die Antwort für einen Client verständlich?
- Siehst du technische Details, die nicht nach aussen gehören?

---

### Aufgabe 3: Einfache Fehlerantwort erstellen

Erstelle ein kleines DTO für Fehlerantworten.

```java
public record ErrorResponse(
        String code,
        String message,
        String details
) {
}
```

Lege es in einem passenden API- oder DTO-Package ab.

Erwartung:

```json
{
  "code": "PRODUCT_NOT_FOUND",
  "message": "Produkt wurde nicht gefunden.",
  "details": "id=999"
}
```

---

### Aufgabe 4: `404 Not Found` zurückgeben

Passe den Endpunkt `GET /produkte/{id}` so an, dass eine unbekannte ID kontrolliert beantwortet wird.

Erwartung:

- Produkt vorhanden: `200` mit Produkt-DTO
- Produkt fehlt: `404` mit `ErrorResponse`

Verwende dafür `ResponseEntity`.

---

### Aufgabe 5: Ungültigen Status testen

Sende einen Statuswert, der nicht im Enum vorkommt.

```bash
curl -i -X PUT http://localhost:8080/produkte/1/status/VERKAUFT
```

Prüfe:

- Welcher Statuscode kommt zurück?
- Ist die Antwort verständlich?
- Kann Bruno diesen Fehlerfall reproduzierbar ausführen?

---

### Aufgabe 6: `400 Bad Request` zurückgeben

Passe die Statusänderung so an, dass ein ungültiger Status kontrolliert beantwortet wird.

Erwartung:

- gültiger Status: `200` mit Produkt-DTO
- ungültiger Status: `400` mit `ErrorResponse`

Hinweis:

Du kannst den Status im Controller zuerst als `String` entgegennehmen und selbst in `ProduktStatus` umwandeln.

---

### Aufgabe 7: Bruno-Requests ergänzen

Ergänze gespeicherte Bruno-Requests für:

- `GET /produkte/1` als Erfolgsfall
- `GET /produkte/999` als `404`-Fehlerfall
- `PUT /produkte/1/status/ARCHIVIERT` als Erfolgsfall
- `PUT /produkte/1/status/VERKAUFT` als `400`-Fehlerfall

Benenne die Requests so, dass der erwartete Fall sichtbar ist.

---

## Vertiefung

### Aufgabe 8: Fehlerantwort einheitlich halten

Prüfe deine Fehlerantworten.

Alle Fehlerantworten sollen die gleiche Grundstruktur verwenden:

```json
{
  "code": "...",
  "message": "...",
  "details": "..."
}
```

Notiere:

- Welche Fehlercodes verwendest du?
- Sind die Meldungen für Menschen verständlich?
- Sind technische Exception-Namen aus der Antwort entfernt?

---

### Aufgabe 9: Controller klein halten

Prüfe deinen Controller.

Der Controller soll:

- HTTP-Requests entgegennehmen
- Service-Methoden aufrufen
- DTOs und Fehlerantworten zurückgeben

Der Controller soll nicht:

- Produkte selbst in Collections suchen
- Fachregeln im Detail umsetzen
- Repository-Methoden direkt aufrufen

Markiere eine Stelle, an der dein Controller klar bleibt, und eine Stelle, die du verbessern würdest.

---

### Aufgabe 10: Service-Fehler klar weitergeben

Prüfe, wie der Service einen fehlenden Datensatz meldet.

Geeignete einfache Varianten:

- `Optional<Produkt>` zurückgeben
- eine einfache fachliche Exception werfen

Nicht passend für diese Einheit:

- Service gibt `ResponseEntity` zurück
- Service baut `ErrorResponse`
- Repository entscheidet über HTTP-Statuscodes

---

### Aufgabe 11: Statuscodes begründen

Begründe schriftlich:

| Situation | Statuscode | Begründung |
|---|---|---|
| Produktliste laden | | |
| Produkt erstellen | | |
| Produkt-ID unbekannt | | |
| Statuswert ungültig | | |
| unerwarteter Serverfehler | | |

---

### Aufgabe 12: Logging beobachten

Starte die Anwendung und löse bewusst Fehlerfälle aus.

Beobachte die Konsole:

- Welche Meldungen erscheinen?
- Wird der Fehler im Client kontrolliert beantwortet?
- Wirkt der Fehler wie ein normaler fachlicher Fall oder wie ein Absturz?

Du musst in dieser Einheit kein Logging-Konzept bauen. Es reicht, die Wirkung zu beobachten.

---

## Transfer

### Aufgabe 13: Statuscodes erklären

Erkläre in eigenen Worten:

- Warum ist `200` bei einem Fehler problematisch?
- Warum ist `404` nicht dasselbe wie `500`?
- Warum ist `400` bei einem ungültigen Status passender als `404`?

---

### Aufgabe 14: Fehlerantworten begründen

Erkläre:

- Warum sollte eine Fehlerantwort strukturiert sein?
- Warum ist ein Stacktrace keine gute Antwort für REST-Clients?
- Welche Felder deiner Fehlerantwort helfen Bruno-Tests?

---

### Aufgabe 15: Fehlerbehandlung und Fachlogik trennen

Erkläre an deinem Code:

- Wo liegt die Fachlogik?
- Wo wird entschieden, welcher HTTP-Statuscode zurückkommt?
- Warum ersetzt Fehlerbehandlung keine Fachlogik?

---

## Bewertungsfokus

| Bereich | Beobachtung |
|---|---|
| Funktionalität | Erfolgs- und Fehlerfälle liefern die erwarteten Statuscodes und JSON-Strukturen. |
| Verantwortlichkeiten | Controller, Service und Repository bleiben sinnvoll getrennt. |
| Verständlichkeit | Fehlercodes, Meldungen und Methodennamen sind nachvollziehbar. |
| Technische Sauberkeit | `ResponseEntity`, DTOs und Enum-Verarbeitung werden einfach und passend eingesetzt. |
| Lernzielerreichung | Der Unterschied zwischen Erfolgsantwort, Fehlerantwort und Serverfehler kann erklärt werden. |

Qualitative Einschätzungen:

- gut erfüllt
- teilweise erfüllt
- noch unklar
- Verbesserungsbedarf

---

## Abschluss

Halte fest:

- Welche Fehlerfälle hast du mit Bruno geprüft?
- Welche `curl -i`-Befehle zeigen die Statuscodes?
- Welche Stelle im Code trennt Service-Entscheidung und HTTP-Antwort am klarsten?
