# Musterlösung – REST-Lagerverwaltung professionell erweitern

## Zweck

Diese Musterlösung ist ein kleiner Referenzstand für das Integrationsprojekt.

Sie zeigt:

- REST-Endpunkte mit Spring Boot
- DTOs für JSON-Ausgabe und JSON-Eingabe
- `ProduktStatus` als Enum
- klare Trennung von Controller, Service und Repository
- In-Memory-Speicherung mit einer Collection
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

---

## REST-Endpunkte

| Methode | Pfad | Zweck |
|---|---|---|
| `GET` | `/produkte` | alle Produkte laden |
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

- keine Datenbank
- kein JPA
- keine Security
- keine Bean Validation
- keine komplexe Fehlerbehandlung
- kein Lombok
- kein MapStruct

Das Ziel ist ein kleiner, lesbarer Referenzstand für die bisher behandelten Konzepte.
