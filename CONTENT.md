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
- JPA/Spring Data und Integrationstests als Abschluss von j2b

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

Material:
- [Arbeitsblatt](Arbeitsblaetter/Arbeitsblatt_Validation_REST_Requests.md)
- [Übungen](Uebungen/Uebungen_Validation_REST_Requests.md)
- [Musterlösungen](Musterloesungen/Loesungen_Validation_REST_Requests.md)
- [Grafik](graphics/validation_rest_requests.svg)

Fokus:
- Eingabevalidierung auf Request-DTOs
- fehlerhafte JSON-Requests als `400 Bad Request`
- einfache Bean Validation mit `@NotBlank`, `@PositiveOrZero` und `@NotNull`
- `@Valid` und `BindingResult` im REST Controller
- kontrollierte Fehlermeldungen mit `ErrorResponse`
- Trennung zwischen Validation und Fachlogik

---

### 53. Services mit Spring sauber integrieren

Material:
- [Arbeitsblatt](Arbeitsblaetter/Arbeitsblatt_Spring_Services_DI.md)
- [Übungen](Uebungen/Uebungen_Spring_Services_DI.md)
- [Musterlösungen](Musterloesungen/Loesungen_Spring_Services_DI.md)
- [Grafik](graphics/spring_services_di.svg)

Fokus:
- Dependency Injection
- `@RestController`, `@Service` und `@Repository`
- Constructor Injection
- Verantwortlichkeiten zwischen Controller, Service und Repository
- Spring als Infrastruktur für Objekterzeugung und Verdrahtung
- bestehende Services und Repositorys weiterverwenden
- manuelle `new`-Aufrufe im Controller und Service vermeiden

---

### 54. Spring Container und Dependency Injection verstehen

Material:
- [Arbeitsblatt](Arbeitsblaetter/Arbeitsblatt_Spring_Container_DI_Verstehen.md)
- [Übungen](Uebungen/Uebungen_Spring_Container_DI_Verstehen.md)
- [Musterlösungen](Musterloesungen/Loesungen_Spring_Container_DI_Verstehen.md)
- [Grafik](graphics/spring_container_di_verstehen.svg)

Fokus:
- Spring Container als einfache Infrastruktur
- manuelle Verdrahtung mit `new`
- Objektgraph ohne und mit Spring
- Constructor Injection als sichtbare Abhängigkeit
- Architektur bleibt Controller, Service, Repository
- Vorbereitung auf JPA/Spring Data

---

### 55. JPA und Spring Data Grundlagen

Fokus:
- JPA-Entity als persistentes Fachobjekt
- einfache Beziehungen vorbereiten
- Spring Data Repositorys einführen
- bestehende Service-Struktur beibehalten
- CRUD-Zugriffe mit Spring Data nachvollziehen

---

### 56. Integrationstests

Fokus:
- REST-Endpunkte mit Spring Boot testen
- Zusammenspiel von Controller, Service, Repository und Datenbank prüfen
- Testdaten kontrolliert vorbereiten
- erfolgreiche Abläufe und Fehlerfälle absichern
- Bruno-Workflows und automatisierte Tests unterscheiden

---

### 57. Abschlussprojekt j2b: Mini-Shop / Bestellverwaltung

Fachlicher Rahmen:
- Produkt
- Kategorie
- Kunde
- Bestellung
- Bestellposition
- Bestand prüfen
- Bestand reduzieren
- Bestellungen anzeigen

Technische Konsolidierung:
- REST
- DTOs
- Validation
- Dependency Injection
- JPA
- Spring Data
- Integrationstests

Bewusst nicht enthalten:
- Security
- Login
- Authentication
- Authorization
- JWT/OAuth
- Frontend
- Payment
- Rabatte
- Versand
- Rechnungen
- Retouren
- Microservices

Didaktischer Anschluss:

```text
Das Projekt schliesst j2b ab.
j3a startet danach mit Security: Der Mini-Shop funktioniert, aber wer darf was?
```

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

- [Entwicklungslogik](docs/didaktik/entwicklungslogik.md): roter Faden von Java-Grundlagen über Persistenz, REST/Spring, DTOs, Fehlerbehandlung, Validation, Dependency Injection, JPA/Spring Data und Integrationstests bis zum Abschlussprojekt j2b
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
- JPA/Spring Data

---

## API-Werkzeuge

- curl
- Bruno
- reproduzierbare REST-Workflows

---

# Noch bewusst NICHT enthalten

- Spring Security
- Login
- Authentication
- Authorization
- JWT/OAuth
- Frontend
- Payment
- Rabatte
- Versand
- Rechnungen
- Retouren
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
