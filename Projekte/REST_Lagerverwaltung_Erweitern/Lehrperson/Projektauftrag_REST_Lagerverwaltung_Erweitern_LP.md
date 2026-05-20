# Lehrpersonen-Version – REST-Lagerverwaltung professionell erweitern

## Didaktische Einordnung

Das Projekt festigt die Lerneinheiten zu DTOs, Collections, Streams und Enums im REST-Kontext.

Es soll keine neuen grossen Konzepte einführen. Der Fokus liegt auf Kombination, Struktur und Erklärung:

```text
REST Controller
-> DTOs
-> Service
-> Repository
-> Collection
```

---

## Erwartete Kernlösung

Eine tragfähige Lösung enthält:

- `Produkt` als Fachmodell
- `ProduktStatus` als Enum
- `ProduktDto` für REST-Ausgabe
- `ProduktErstellenDto` für REST-Eingabe
- `ProduktController`
- `ProduktService`
- `ProduktRepository`
- In-Memory-Speicherung mit Collection
- DTO-Mapping über eine kleine Mapping-Methode
- Stream-Nutzung für Listen-Mapping und Statusfilter
- Bruno-Requests für die Pflichtabläufe

---

## Beobachtungspunkte

### REST-Struktur

- Sind die Endpunkte verständlich und stabil?
- Gibt der Controller DTOs zurück?
- Bleibt die JSON-Struktur kontrolliert?
- Werden Statuswerte im JSON korrekt als Enum-Namen verwendet?

### Verantwortlichkeiten

- Kennt der Controller keine Speicherstruktur?
- Liegt fachliche Koordination im Service?
- Verwaltet das Repository nur Produkte?
- Ist DTO-Mapping sichtbar und nicht im Repository versteckt?

### DTOs

- Gibt es getrennte DTOs für Eingabe und Ausgabe?
- Enthält das Eingabe-DTO keine `id`?
- Enthält das Ausgabe-DTO die kontrollierte öffentliche Struktur?
- Werden Fachobjekte nicht direkt als REST-Response zurückgegeben?

### Enums

- Wird `ProduktStatus` statt String verwendet?
- Sind die Werte fachlich nachvollziehbar?
- Gibt es keine unnötigen Enum-Methoden, Labels oder Serializer?
- Werden Enum-Werte nicht wieder über String-Vergleiche verarbeitet?

### Collections und Streams

- Wird eine Collection als einfacher Speicher verwendet?
- Werden Streams für Filterung oder DTO-Mapping sinnvoll eingesetzt?
- Bleiben Stream-Ausdrücke kurz und lesbar?
- Wird Fachlogik nicht in langen Lambda-Ausdrücken versteckt?

### Bruno

- Sind die wichtigsten Workflows gespeichert?
- Können Lernende die API reproduzierbar demonstrieren?
- Sind Request-Bodys und Pfade nachvollziehbar benannt?

---

## Hilfestellungen

Mögliche Impulse bei Schwierigkeiten:

- "Welche Klasse sollte wissen, wie Produkte gespeichert werden?"
- "Welche Klasse sollte entscheiden, welche JSON-Felder sichtbar sind?"
- "Wo würdest du den String `AKTIV` durch `ProduktStatus.AKTIV` ersetzen?"
- "Kannst du zuerst `GET /produkte` stabil machen, bevor du Filter ergänzt?"
- "Welche Bruno-Anfrage beweist diese Funktion?"

---

## Bewertungsfokus

| Bereich | Beobachtung |
|---|---|
| Funktionalität | Die Pflichtendpunkte funktionieren und sind mit Bruno prüfbar. |
| Verantwortlichkeiten | Controller, Service und Repository sind klar getrennt. |
| Verständlichkeit | Namen, Klassen und Datenfluss sind nachvollziehbar. |
| Technische Sauberkeit | DTOs, Enums, Collections und Streams werden passend eingesetzt. |
| Lernzielerreichung | Die Lernenden können ihre Architektur- und Modellierungsentscheide erklären. |

Qualitative Einschätzungen:

- gut erfüllt
- teilweise erfüllt
- noch unklar
- Verbesserungsbedarf

---

## Typische Fehlerbilder

- Controller greift direkt auf eine Collection zu.
- Repository erzeugt DTOs.
- Fachmodell wird direkt als REST-Response zurückgegeben.
- Status bleibt ein `String`.
- `ProduktStatus` wird mit `.name()` und Strings verglichen.
- Stream-Ausdrücke werden unnötig lang.
- Optionale Erweiterungen werden vor stabilen Pflichtfunktionen umgesetzt.
- Bruno enthält nur einzelne manuelle Tests statt nachvollziehbarer Workflows.

---

## Abgrenzung

Nicht bewerten oder erwarten:

- Security
- JPA oder Spring Data
- Bean Validation
- komplexe Fehlerbehandlung
- OAuth/JWT
- MapStruct
- Lombok
- produktionsreife API-Gestaltung
