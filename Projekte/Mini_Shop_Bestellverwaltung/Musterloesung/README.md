# Musterlösung – Mini-Shop / Bestellverwaltung

## Zweck

Diese Musterlösung ist ein kompakter Referenzstand für das Abschlussprojekt j2b.

Sie zeigt:

- REST Controller
- DTOs für Eingabe und Ausgabe
- Bean Validation
- Services mit Constructor Injection
- JPA Entities
- Spring Data Repositorys
- H2-Datenbank
- einfache Fehlerantworten
- MockMvc-Integrationstests
- Bruno-Requests für zentrale Workflows

---

## Starten

```bash
mvn spring-boot:run
```

Die Anwendung läuft danach auf:

```text
http://localhost:8080
```

---

## Tests

```bash
mvn test
```

Die Integrationstests liegen unter:

```text
src/test/java/ch/allianz/youngoitv/shop/api/BestellungIntegrationTest.java
```

Geprüft werden:

- gültige Bestellung erstellen
- Bestand nach Bestellung reduzieren
- Bestellung wieder anzeigen
- unbekannter Kunde liefert `404`
- unbekanntes Produkt liefert `404`
- zu wenig Bestand liefert `400`
- dasselbe Produkt mehrfach in einer Bestellung wird gesamthaft gegen den Bestand geprüft
- ungültiger Request liefert `400`

---

## REST-Endpunkte

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

## Beispiel: Bestellung erstellen

```bash
curl -i -X POST http://localhost:8080/bestellungen \
  -H "Content-Type: application/json" \
  -d '{"kundeId":1,"positionen":[{"produktId":1,"menge":2}]}'
```

---

## Bruno

Die gespeicherten Bruno-Requests liegen unter:

```text
bruno/Mini-Shop/
```

Empfohlene Reihenfolge:

1. `01 Kategorie erstellen`
2. `02 Kategorien laden`
3. `03 Produkt erstellen`
4. `04 Produkte laden`
5. `05 Kunde erstellen`
6. `06 Kunden laden`
7. `07 Bestellung erstellen`
8. `08 Bestellung laden`
9. `09 Bestellung mit unbekanntem Produkt`
10. `10 Bestellung mit zu wenig Bestand`

---

## Bewusste Vereinfachungen

Diese Musterlösung verwendet:

- keine Security
- kein Login
- kein JWT/OAuth
- kein Frontend
- kein Payment
- keinen Versand
- keine Rabatte
- keine Rechnungen
- keine Microservices
- keine komplexen JPA-Beziehungen
- kein Lombok
- kein MapStruct

Bestellungen und Bestellpositionen speichern IDs und Snapshot-Daten. Dadurch bleibt die Persistenzstruktur auf EFZ-Niveau nachvollziehbar.
