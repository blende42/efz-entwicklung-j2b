# Projektreview – Mini-Shop / Bestellverwaltung

## Ziel des Reviews

Das Review macht sichtbar, ob die Lösung nicht nur läuft, sondern fachlich und technisch nachvollziehbar aufgebaut ist.

Im Zentrum steht der Ablauf:

```text
REST Request
-> Controller
-> DTO / Validation
-> Service
-> Repository
-> H2-Datenbank
```

---

## Vorbereitung

Die lernende Person bereitet vor:

- laufende Anwendung oder erfolgreiche Tests
- Bruno-Requests für Erfolgs- und Fehlerfälle
- mindestens einen Integrationstest
- kurze Erklärung der wichtigsten Klassen

---

## Review-Fragen

### Funktionalität

- Welche API-Anfrage erstellt eine Bestellung?
- Wie sieht die Antwort einer erfolgreichen Bestellung aus?
- Wie wird eine Bestellung wieder angezeigt?
- Wie wird sichtbar, dass der Bestand reduziert wurde?

### Verantwortlichkeiten

- Welche Aufgabe hat der Controller?
- Welche Aufgabe hat der Service?
- Welche Aufgabe hat das Repository?
- Wo findet DTO-Mapping statt?
- Wo liegt die Fachregel "Bestand muss ausreichen"?

### Verständlichkeit

- Warum gibt es Bestellung und Bestellposition?
- Welche Daten werden beim Erstellen einer Bestellung vom Client gesendet?
- Welche Daten werden von der Anwendung ergänzt?
- Welche Klassen würdest du einer anderen Person zuerst erklären?

### Technische Sauberkeit

- Wo wird Validation eingesetzt?
- Wie werden unbekannte Ressourcen behandelt?
- Welche Integrationstests prüfen den wichtigsten Ablauf?
- Welche Bruno-Requests prüfen Fehlerfälle?

### Lernzielerreichung

- Welche j2b-Themen kommen in deiner Lösung zusammen?
- Wo hilft Dependency Injection?
- Wo hilft Spring Data JPA?
- Warum ist Security noch nicht Teil dieser Lösung?

---

## Bewertungsfokus

| Bereich | Einschätzung | Beobachtung |
|---|---|---|
| Funktionalität | | |
| Verantwortlichkeiten | | |
| Verständlichkeit | | |
| Technische Sauberkeit | | |
| Lernzielerreichung | | |

Einschätzungen:

- gut erfüllt
- teilweise erfüllt
- noch unklar
- Verbesserungsbedarf

---

## Typische Review-Beobachtungen

- Der wichtigste Bestellablauf funktioniert, aber Fehlerfälle fehlen noch.
- Die technische Struktur ist vorhanden, aber Fachlogik liegt teilweise im Controller.
- DTOs sind vorhanden, werden aber nicht konsequent verwendet.
- Integrationstests prüfen den Statuscode, aber nicht den Datenbankzustand.
- Bruno zeigt Erfolgsfälle, aber keine Randfälle.
