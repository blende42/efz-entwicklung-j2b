# Projektauftrag – REST-Lagerverwaltung professionell erweitern

## Ausgangslage

Du kennst bereits:

- REST und HTTP-Grundlagen
- Spring Boot
- DTOs
- Collections
- Streams
- Enums
- Bruno-Requests

In diesem Projekt kombinierst du diese Konzepte in einer kleinen REST-Lagerverwaltung.

---

## Projektziel

Erstelle oder erweitere eine Spring-Boot-Anwendung, die Produkte verwaltet.

Produkte sollen über REST:

- gelesen werden
- angelegt werden
- nach Status gefiltert werden
- im Status geändert werden

Der Status ist kein freier String. Er wird mit einem Enum `ProduktStatus` modelliert.

---

## Fachliche Anforderungen

Ein Produkt enthält mindestens:

- `id`
- `name`
- `preis`
- `status`

Erlaubte Statuswerte:

- `AKTIV`
- `RESERVIERT`
- `DEFEKT`
- `ARCHIVIERT`

---

## Pflichtanforderungen

Deine Lösung erfüllt folgende Punkte:

- Es gibt eine Spring-Boot-Anwendung.
- Es gibt ein Fachmodell `Produkt`.
- Es gibt ein Enum `ProduktStatus`.
- Es gibt ein Ausgabe-DTO, zum Beispiel `ProduktDto`.
- Es gibt ein Eingabe-DTO, zum Beispiel `ProduktErstellenDto`.
- Es gibt einen `ProduktController`.
- Es gibt einen `ProduktService`.
- Es gibt ein `ProduktRepository` oder eine einfache In-Memory-Repository-Klasse.
- Produkte werden intern in einer Collection verwaltet.
- REST-Ausgaben werden über DTOs erzeugt.
- Fachobjekte werden nicht direkt als REST-Response zurückgegeben.
- Mapping zwischen Fachobjekt und DTO ist sichtbar und nachvollziehbar.
- Streams werden für DTO-Mapping oder Statusfilterung verwendet.
- Bruno-Requests dokumentieren die wichtigsten API-Workflows.

---

## Pflichtendpunkte

Setze diese Endpunkte um:

| Methode | Pfad | Zweck |
|---|---|---|
| `GET` | `/produkte` | alle Produkte lesen |
| `GET` | `/produkte/status/{status}` | Produkte nach Status filtern |
| `POST` | `/produkte` | Produkt anlegen |
| `PUT` | `/produkte/{id}/status/{status}` | Status eines Produkts ändern |

---

## Beispiel-JSON

Produkt erstellen:

```json
{
  "name": "Tastatur",
  "preis": 49.9,
  "status": "AKTIV"
}
```

Produkt in der Ausgabe:

```json
{
  "id": 1,
  "name": "Tastatur",
  "preis": 49.9,
  "status": "AKTIV"
}
```

---

## Bruno-Workflows

Erstelle gespeicherte Bruno-Requests für:

- alle Produkte laden
- Produkte mit Status `AKTIV` laden
- Produkt erstellen
- Produktstatus ändern
- geänderte Produktliste erneut laden

---

## Optionale Erweiterungen

Diese Punkte sind freiwillig:

- Produkte nach Name suchen
- Produkte nach Mindestpreis filtern
- kurze Produktansicht als zusätzliches DTO erstellen
- Produkte nach Preis oder Name sortieren
- einfache Service-Tests ergänzen

Setze optionale Erweiterungen erst um, wenn die Pflichtanforderungen stabil funktionieren.

---

## Abgabe

Dein Projekt soll:

- mit Maven buildbar sein
- startbar sein
- mit Bruno prüfbar sein
- eine kurze README mit Start- und Testhinweisen enthalten
- klar getrennte Klassen für Controller, Service, Repository, DTOs und Fachmodell verwenden

---

## Reflexion

Bereite dich auf folgende Fragen vor:

- Warum ist `ProduktStatus` besser als ein String?
- Wo findet das DTO-Mapping statt?
- Welche Klasse ist für REST zuständig?
- Welche Klasse enthält fachliche Abläufe?
- Welche Bruno-Requests zeigen, dass deine API funktioniert?
- Wo hast du Streams eingesetzt und warum?
