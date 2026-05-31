# Inhaltsübersicht – efz-entwicklung-j2b

## Ziel dieses Repositories

Dieses Repository vertieft:
- Spring Boot
- REST-Strukturierung
- professionelle API-Entwicklung
- DTOs
- Validation
- Fehlerbehandlung
- Integration von Spring in bestehende Architektur

Das Repository baut direkt auf:
`efz-entwicklung-j2a`
auf.

---

# Geplante Lerneinheiten

## REST- und API-Architektur

### 46. JSON-Strukturen und DTOs in REST-APIs

Material:
- [Arbeitsblatt](Arbeitsblaetter/Arbeitsblatt_JSON_DTOs_REST.md)
- [Übungen](Uebungen/Uebungen_JSON_DTOs_REST.md)
- [Musterlösungen](Musterloesungen/Loesungen_JSON_DTOs_REST.md)
- [Grafik](graphics/json_dto_rest.svg)

Fokus:
- interne Fachobjekte vs. externe API-Struktur
- DTO-Grundidee
- JSON-Strukturierung
- kontrollierte REST-Ausgabe
- manuelles Mapping
- GET und POST mit DTOs
- Bruno- und `curl`-Prüfung

---

### 47. Collections Framework in REST- und DTO-Anwendungen

Material:
- [Arbeitsblatt](Arbeitsblaetter/Arbeitsblatt_Collections_Framework_REST.md)
- [Übungen](Uebungen/Uebungen_Collections_Framework_REST.md)
- [Musterlösungen](Musterloesungen/Loesungen_Collections_Framework_REST.md)
- [Grafik](graphics/collections_framework_rest.svg)

Fokus:
- `List`, `ArrayList`, `Set`, `HashSet`, `Map` und `HashMap`
- DTO-Listen
- REST-Ausgabe mit mehreren Objekten
- klassische Iteration
- bewusste Collection-Auswahl
- Vorbereitung auf Streams ohne Streams

---

### 48. Streams und Transformationen in REST- und DTO-Anwendungen

Material:
- [Arbeitsblatt](Arbeitsblaetter/Arbeitsblatt_Streams_Transformationen_REST.md)
- [Übungen](Uebungen/Uebungen_Streams_Transformationen_REST.md)
- [Musterlösungen](Musterloesungen/Loesungen_Streams_Transformationen_REST.md)
- [Grafik](graphics/streams_transformationen_rest.svg)

Fokus:
- Stream API im REST-/DTO-Kontext
- `stream()`, `map()`, `filter()` und `toList()`
- DTO-Transformationen
- REST-Listen
- einfache Lambda-Ausdrücke
- Vergleich mit klassischer Iteration

---

### 49. Enums in Fachmodellen und REST-APIs

Material:
- [Arbeitsblatt](Arbeitsblaetter/Arbeitsblatt_Enums_REST.md)
- [Übungen](Uebungen/Uebungen_Enums_REST.md)
- [Musterlösungen](Musterloesungen/Loesungen_Enums_REST.md)
- [Grafik](graphics/enums_rest.svg)

Fokus:
- magische Strings vermeiden
- kontrollierte Wertebereiche mit `enum`
- `ProduktStatus` im Fachmodell
- Enums in DTOs und REST-JSON
- `switch` und Stream-Filter mit Enum-Werten
- stabile Statuswerte für REST-Clients

---

### 50. REST-Fehlerbehandlung und Statuscodes

Material:
- [Arbeitsblatt](Arbeitsblaetter/Arbeitsblatt_REST_Fehlerbehandlung_Statuscodes.md)
- [Übungen](Uebungen/Uebungen_REST_Fehlerbehandlung_Statuscodes.md)
- [Musterlösungen](Musterloesungen/Loesungen_REST_Fehlerbehandlung_Statuscodes.md)
- [Grafik](graphics/rest_fehlerbehandlung_statuscodes.svg)

Fokus:
- erfolgreiche Antworten und Fehlerantworten unterscheiden
- HTTP-Statuscodes `200`, `201`, `400`, `404` und `500`
- `ResponseEntity`
- einfache JSON-Fehlerantworten
- Fehler bei unbekannter ID und ungültigem `ProduktStatus`
- Bruno- und `curl`-Prüfung von Fehlerfällen

---

### 51. Optional und kontrollierte Null-Behandlung

Material:
- [Arbeitsblatt](Arbeitsblaetter/Arbeitsblatt_Optional_Nullbehandlung.md)
- [Übungen](Uebungen/Uebungen_Optional_Nullbehandlung.md)
- [Musterlösungen](Musterloesungen/Loesungen_Optional_Nullbehandlung.md)
- [Grafik](graphics/optional_nullbehandlung.svg)

Fokus:
- Probleme mit `null` und fehlenden Werten
- `Optional<T>` als sichtbarer Rückgabetyp
- `isPresent()`, `isEmpty()`, `orElse()` und `orElseThrow()`
- Repository- und Service-Methoden mit `Optional`
- REST-Fehlerbehandlung mit `404` und `ResponseEntity`

---

### 52. Validation bei REST-Requests

Fokus:
- Eingabevalidierung
- fehlerhafte Requests
- einfache Bean Validation
- kontrollierte Fehlermeldungen

---

### 53. Services mit Spring sauber integrieren

Fokus:
- Dependency Injection
- Verantwortlichkeiten
- Spring als Infrastruktur
- bestehende Services weiterverwenden

---

### 54. Vorbereitung auf JPA und Spring Data

Fokus:
- Persistenzabstraktion
- Repository-Idee vertiefen
- Motivation für ORM
- Grenzen von JDBC diskutieren

---

# Projekte

## REST-Lagerverwaltung professionell erweitern

Material:
- [Projektübersicht](Projekte/REST_Lagerverwaltung_Erweitern/README.md)
- [Projektauftrag für Lernende](Projekte/REST_Lagerverwaltung_Erweitern/Lernende/Projektauftrag_REST_Lagerverwaltung_Erweitern.md)
- [Projektauftrag für Lehrpersonen](Projekte/REST_Lagerverwaltung_Erweitern/Lehrperson/Projektauftrag_REST_Lagerverwaltung_Erweitern_LP.md)
- [Musterlösung](Projekte/REST_Lagerverwaltung_Erweitern/Musterloesung/README.md)
- [Projektreview](Projekte/REST_Lagerverwaltung_Erweitern/Review/Projektreview_REST_Lagerverwaltung_Erweitern.md)
- [Reflexion](Projekte/REST_Lagerverwaltung_Erweitern/Review/Reflexion_REST_Lagerverwaltung_Erweitern.md)

Fokus:
- DTOs
- Collections
- Streams
- Enums mit `ProduktStatus`
- strukturierte REST-API ohne JPA, Security und Validation
- Bruno-Workflows
- vollständige Spring-Boot-Musterlösung als Referenzstand

---

# Didaktische Entwicklungslinie

Die Architekturentwicklung bleibt bewusst:
- problemgetrieben
- evolutionär
- nachvollziehbar

Neue Konzepte entstehen aus:
- Strukturierungsproblemen
- wachsender REST-Komplexität
- Trennung von interner und externer Datenstruktur
- wiederkehrenden Fehlerbildern
- wachsender technischer Infrastruktur

## Didaktische Referenzen

- [Entwicklungslogik](docs/didaktik/entwicklungslogik.md): roter Faden von Java-Grundlagen über Persistenz, REST/Spring, DTOs, Fehlerbehandlung und Optional bis zur Vorbereitung auf Validation, Dependency Injection und JPA/Spring Data
- [Bewertungskriterien](docs/didaktik/bewertungskriterien.md): Standard für qualitative Rückmeldung und sichtbare Bewertungsschwerpunkte in grösseren Übungen, Projekten und Reviews ohne Punkte- oder Notenlogik

---

# Technischer Fokus

## Backend

- Java
- Spring Boot
- REST
- JSON
- Validation
- DTOs
- HTTP
- Logging
- Konfiguration

---

## Persistenz

- JDBC/H2
- Repository
- Vorbereitung auf JPA/Spring Data

---

## API-Werkzeuge

- curl
- Bruno
- reproduzierbare REST-Workflows

---

# Noch bewusst NICHT enthalten

- Spring Security
- JWT/OAuth
- Microservices
- verteilte Systeme
- komplexe Spring-Architektur
- automatische Mapping-Frameworks
- komplexe Validation-Szenarien
- Kubernetes
- Cloud-Architektur
- Event-Driven Architecture
- komplexe Deployment-Szenarien

Diese Themen folgen erst in späteren Ausbildungsabschnitten.
