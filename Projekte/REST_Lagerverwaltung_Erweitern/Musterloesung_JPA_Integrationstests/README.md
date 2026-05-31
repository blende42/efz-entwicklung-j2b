# Zusätzliche Musterlösung – JPA und Integrationstests

## Zweck

Diese zusätzliche Musterlösung ist ein weiterführender Referenzstand für das Integrationsprojekt.

Sie passt zur Zielstruktur nach den Lerneinheiten zu REST, DTOs, Validation, Fehlerbehandlung, Optional, Spring DI, JPA/Spring Data und Integrationstests.

Sie zeigt:

- REST-Endpunkte mit Spring Boot
- DTOs für JSON-Ausgabe und JSON-Eingabe
- `ProduktStatus` als Enum
- klare Trennung von Controller, Service und Repository
- JPA-Entity mit Spring Data Repository
- H2 als einfache Datenbank
- Bean Validation für REST-Eingaben
- einfache Fehlerantworten für Validation und unbekannte IDs
- Integrationstests mit MockMvc
- Streams für DTO-Mapping und Statusfilterung
- Bruno-Requests für die wichtigsten Workflows

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

## Build und Tests

```bash
mvn test
```

oder:

```bash
mvn package
```

Die Musterlösung enthält einen MockMvc-Integrationstest:

```text
src/test/java/ch/allianz/youngoitv/lager/api/ProduktIntegrationTest.java
```

Geprüft werden:

- `GET /produkte`
- gültiger `POST /produkte`
- ungültiger `POST /produkte`
- `GET /produkte/{id}` mit gültiger ID
- `GET /produkte/{id}` mit unbekannter ID
- Datenbankzustand nach dem Erstellen eines Produkts

---

## REST-Endpunkte

| Methode | Pfad | Zweck |
|---|---|---|
| `GET` | `/produkte` | alle Produkte laden |
| `GET` | `/produkte/{id}` | Produkt nach ID laden |
| `GET` | `/produkte/status/{status}` | Produkte nach Status filtern |
| `POST` | `/produkte` | Produkt erstellen |
| `PUT` | `/produkte/{id}/status/{status}` | Produktstatus ändern |

---

## Beispiel: Produkt erstellen

```bash
curl -i -X POST http://localhost:8080/produkte \
  -H "Content-Type: application/json" \
  -d '{"name":"Headset","preis":79.9,"status":"AKTIV"}'
```

Beispielantwort:

```json
{
  "id": 6,
  "name": "Headset",
  "preis": 79.9,
  "status": "AKTIV"
}
```

---

## Beispiel: Produkte nach Status filtern

```bash
curl -i http://localhost:8080/produkte/status/AKTIV
```

---

## Bruno

Die gespeicherten Bruno-Requests liegen unter:

```text
bruno/REST-Lagerverwaltung/
```

Empfohlene Reihenfolge:

1. `01 Alle Produkte laden`
2. `02 Aktive Produkte laden`
3. `03 Produkt erstellen`
4. `04 Status ändern`
5. `05 Archivierte Produkte laden`

---

## Bewusste Vereinfachungen

Diese Musterlösung verwendet:

- keine Security
- kein Lombok
- kein MapStruct
- keine komplexen Entity-Beziehungen
- keine Testcontainer
- keine Security-Tests
- keine Performance-Tests

Das Ziel ist ein kleiner, lesbarer Referenzstand für REST, DTOs, Validation, Fehlerbehandlung, Spring DI, JPA/Spring Data und Integrationstests.
