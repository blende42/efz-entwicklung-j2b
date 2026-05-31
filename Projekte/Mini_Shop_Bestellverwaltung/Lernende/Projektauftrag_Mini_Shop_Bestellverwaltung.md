# Projektauftrag – Mini-Shop / Bestellverwaltung

## Ausgangslage

Du hast in `efz-entwicklung-j2b` Schritt für Schritt gelernt, wie eine Spring-Boot-REST-Anwendung strukturiert wird.

Du kennst bereits:

- REST Controller
- DTOs und JSON-Strukturen
- Validation
- Fehlerbehandlung mit Statuscodes
- Services
- Dependency Injection
- JPA und Spring Data
- H2
- Integrationstests
- Bruno-Workflows

In diesem Abschlussprojekt kombinierst du diese Konzepte in einem Mini-Shop.

---

## Projektziel

Erstelle eine Spring-Boot-Anwendung für eine einfache Bestellverwaltung.

Der Mini-Shop soll:

- Kategorien verwalten
- Produkte verwalten
- Kunden erfassen
- Bestellungen erfassen
- den Bestand prüfen
- den Bestand bei erfolgreicher Bestellung reduzieren
- Bestellungen wieder anzeigen

---

## Fachliche Anforderungen

### Kategorie

Eine Kategorie enthält mindestens:

- `id`
- `name`

### Produkt

Ein Produkt enthält mindestens:

- `id`
- `name`
- `preis`
- `bestand`
- `kategorieId`

### Kunde

Ein Kunde enthält mindestens:

- `id`
- `name`
- `email`

### Bestellung

Eine Bestellung enthält mindestens:

- `id`
- `kundeId`
- `kundenName`
- `bestelldatum`
- Bestellpositionen

### Bestellposition

Eine Bestellposition enthält mindestens:

- `produktId`
- `produktName`
- `menge`
- `einzelpreis`

---

## Pflichtanforderungen

Deine Lösung erfüllt folgende Punkte:

- Es gibt eine lauffähige Spring-Boot-Anwendung.
- Es gibt JPA Entities für Kategorie, Produkt, Kunde, Bestellung und Bestellposition.
- Es gibt Spring Data Repositorys für den Datenzugriff.
- Es gibt REST Controller für Kategorien, Produkte, Kunden und Bestellungen.
- Eingaben werden über Request-DTOs entgegengenommen.
- Ausgaben werden über Response-DTOs zurückgegeben.
- Request-DTOs werden mit Bean Validation geprüft.
- Fachregeln liegen im Service.
- Abhängigkeiten werden über Constructor Injection übergeben.
- Die Anwendung verwendet H2.
- Integrationstests prüfen zentrale REST-Abläufe.
- Bruno-Requests dokumentieren die wichtigsten API-Workflows.

---

## Pflichtendpunkte

Setze mindestens diese Endpunkte um:

| Methode | Pfad | Zweck |
|---|---|---|
| `POST` | `/kategorien` | Kategorie erstellen |
| `GET` | `/kategorien` | Kategorien anzeigen |
| `POST` | `/produkte` | Produkt erstellen |
| `GET` | `/produkte` | Produkte anzeigen |
| `GET` | `/produkte/{id}` | Produkt anzeigen |
| `POST` | `/kunden` | Kunde erstellen |
| `GET` | `/kunden` | Kunden anzeigen |
| `POST` | `/bestellungen` | Bestellung erstellen |
| `GET` | `/bestellungen/{id}` | Bestellung anzeigen |

---

## Geschäftsregeln

Beim Erstellen einer Bestellung gelten diese Regeln:

- Der Kunde muss existieren.
- Jedes Produkt muss existieren.
- Jede Menge muss positiv sein.
- Der Bestand muss für jede Position ausreichen.
- Nur bei erfolgreicher Bestellung wird Bestand reduziert.
- Ungültige Requests liefern `400 Bad Request`.
- Nicht gefundene Ressourcen liefern `404 Not Found`.

---

## Beispiel-JSON

Bestellung erstellen:

```json
{
  "kundeId": 1,
  "positionen": [
    {
      "produktId": 1,
      "menge": 2
    }
  ]
}
```

---

## Integrationstests

Erstelle Integrationstests für mindestens diese Fälle:

- gültige Bestellung erstellen
- Bestellung mit unbekanntem Kunden ablehnen
- Bestellung mit unbekanntem Produkt ablehnen
- Bestellung mit zu grosser Menge ablehnen
- Bestand wird nach erfolgreicher Bestellung reduziert
- Bestellung kann nach dem Erstellen wieder gelesen werden

---

## Bruno-Workflows

Erstelle gespeicherte Bruno-Requests für:

- Kategorie erstellen
- Produkt erstellen
- Kunde erstellen
- Produkte anzeigen
- Bestellung erstellen
- Bestellung anzeigen
- Bestellung mit unbekanntem Produkt
- Bestellung mit zu wenig Bestand

---

## Optionale Erweiterungen

Diese Punkte sind freiwillig:

- Produkte nach Kategorie filtern
- Kunden per ID anzeigen
- Bestellungen eines Kunden anzeigen
- Bestellung stornieren
- Bestellstatus ergänzen
- zusätzliche Fehlerantworten mit Feldnamen
- zusätzliche Integrationstests

Setze optionale Erweiterungen erst um, wenn die Pflichtanforderungen stabil funktionieren.

---

## Abgabe

Dein Projekt soll:

- mit `mvn test` prüfbar sein
- mit `mvn spring-boot:run` startbar sein
- eine kurze README enthalten
- Bruno-Requests enthalten
- klare Packages für `api`, `api.dto`, `domain`, `repository` und `service` verwenden

---

## Reflexion

Bereite dich auf folgende Fragen vor:

- Wo wird Validation sichtbar?
- Welche Fachregel liegt im Bestellservice?
- Warum sollte der Controller den Bestand nicht selbst reduzieren?
- Welche DTOs schützen deine REST-API vor zu viel interner Struktur?
- Welche Fehlerfälle prüfst du mit Integrationstests?
- Was zeigt dein Bruno-Workflow?
- Was wäre ein sinnvoller Anschluss für j3a mit Security?
