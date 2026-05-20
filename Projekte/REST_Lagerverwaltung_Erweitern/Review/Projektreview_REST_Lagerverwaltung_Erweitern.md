# Projektreview – REST-Lagerverwaltung professionell erweitern

## Ziel des Reviews

Das Review unterstützt ein Architekturgespräch über die umgesetzte REST-Lagerverwaltung.

Im Zentrum stehen:

- REST-Struktur
- DTOs
- Verantwortlichkeiten
- Collections und Streams
- Enums
- Bruno-Workflows
- kleine Verbesserungen

---

## 1. Kurze Demo

- Welche Endpunkte werden gezeigt?
- Welche Bruno-Requests werden verwendet?
- Welche Produktdaten sind vorbereitet?
- Welche Statuswerte kommen vor?

---

## 2. Architekturüberblick

Beschreibe den Ablauf:

```text
Bruno
-> ProduktController
-> ProduktService
-> ProduktRepository
-> Collection
```

Fragen:

- Welche Klasse nimmt HTTP-Requests entgegen?
- Welche Klasse koordiniert fachliche Abläufe?
- Welche Klasse speichert Produkte?
- Wo entstehen DTOs?

---

## 3. Verantwortlichkeiten

| Klasse | Verantwortung | Beobachtung |
|---|---|---|
| `ProduktController` | REST-Endpunkte und DTO-Ausgabe | |
| `ProduktService` | fachliche Abläufe und Koordination | |
| `ProduktRepository` | Speichern und Laden von Produkten | |
| `Produkt` | internes Fachmodell | |
| `ProduktDto` | öffentliche REST-Ausgabe | |
| `ProduktErstellenDto` | REST-Eingabe | |
| `ProduktStatus` | kontrollierte Statuswerte | |

---

## 4. REST-Struktur und DTOs

Prüffragen:

- Werden Fachobjekte direkt zurückgegeben?
- Gibt es ein klares Ausgabe-DTO?
- Gibt es ein klares Eingabe-DTO?
- Ist die JSON-Struktur verständlich?
- Ist die `id` nur dort vorhanden, wo sie sinnvoll ist?

---

## 5. Enums und kontrollierte Zustände

Prüffragen:

- Wird `ProduktStatus` im Fachmodell verwendet?
- Werden Statuswerte im Code als Enum verarbeitet?
- Gibt es noch magische Strings?
- Sind die Statuswerte fachlich nachvollziehbar?
- Bleibt das Enum klein?

---

## 6. Collections und Streams

Prüffragen:

- Welche Collection wird für Produkte verwendet?
- Wo wird nach Status gefiltert?
- Wo werden Fachobjekte in DTOs umgewandelt?
- Sind Stream-Ausdrücke lesbar?
- Gibt es Fachlogik in langen Lambda-Ausdrücken?

---

## 7. Bruno-Workflow

Prüffragen:

- Kann die API mit gespeicherten Requests demonstriert werden?
- Gibt es Requests für alle Pflichtendpunkte?
- Sind Request-Namen verständlich?
- Stimmen Pfade, Methoden und JSON-Bodys?

---

## 8. Bewertungsfokus

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

## 9. Kleine Refactoring-Ideen

Notiere höchstens drei konkrete nächste Verbesserungen:

- Verbesserung 1:
- Verbesserung 2:
- Verbesserung 3:

---

## 10. Nächster Lernschritt

Welche eine Verbesserung soll als Nächstes umgesetzt werden?
