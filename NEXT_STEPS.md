# Übergabe – aktueller Stand

## Startpunkt dieses Repos

Dieses Repo setzt nach dem abgeschlossenen Ausbildungsblock aus:

`efz-entwicklung-j2a`

ein.

Vorausgesetzt werden:
- Java-Grundlagen
- OOP
- Maven
- JUnit
- Refactoring
- CSV-Persistenz
- JDBC/H2
- Repository
- Logging
- technische Konfiguration
- I18N
- REST-Einstieg mit Spring Boot
- HTTP-/REST-Grundlagen
- curl
- Bruno-Workflows

---

## Ziel dieses Repos

Dieses Repo vertieft:
- professionelle REST-Strukturierung
- Spring-basierte Architektur
- API-Design
- Validierung
- Fehlerbehandlung
- Persistenzintegration

Didaktischer Schwerpunkt:
Die bestehende Architektur wird weiterentwickelt und sauber strukturiert.

Neue Konzepte entstehen weiterhin aus konkreten Problemen:
- JSON-Strukturen unterscheiden sich von internen Fachobjekten
- REST benötigt kontrollierte Fehlerbehandlung
- Eingaben müssen validiert werden
- Spring integriert bestehende Services und Infrastruktur

---

## Aktueller Stand

Die Lerneinheit 46 ist erstellt:

- Arbeitsblatt: `Arbeitsblaetter/Arbeitsblatt_JSON_DTOs_REST.md`
- Übungen: `Uebungen/Uebungen_JSON_DTOs_REST.md`
- Musterlösungen: `Musterloesungen/Loesungen_JSON_DTOs_REST.md`
- Grafik: `graphics/json_dto_rest.svg`

Schwerpunkt:

- interne Fachobjekte von externer API-Struktur trennen
- DTOs problemgetrieben einführen
- JSON-Ausgabe und JSON-Eingabe kontrollieren
- manuelles Mapping bei GET und POST verwenden
- Bruno und `curl` zur Prüfung der JSON-Struktur nutzen

---

## Nächste geplante Einheiten

47. REST-Fehlerbehandlung und Statuscodes
48. Validation bei REST-Requests
49. Services mit Spring sauber integrieren
50. Vorbereitung auf JPA und Spring Data

---

## Geplanter technischer Fokus

- Spring Boot
- REST Controller
- DTOs
- JSON-Strukturen
- HTTP-Statuscodes
- Exception Handling
- Validation
- Dependency Injection
- Konfiguration mit Spring
- Vorbereitung auf JPA/Spring Data

---

## Noch bewusst NICHT behandeln

- Spring Security
- JWT/OAuth
- Microservices
- Kubernetes
- komplexe Security-Konzepte
- verteilte Systeme
- Event-Driven Architecture
- komplexe Cloud-Themen

---

## Aktuelle didaktische Leitidee

Architektur wird weiterhin evolutionär entwickelt.

Neue Konzepte entstehen aus:
- wachsender technischer Komplexität
- Strukturierungsproblemen
- REST-/API-Anforderungen
- Trennung von interner und externer Datenstruktur

Spring ersetzt die bestehende Architektur nicht.

Spring unterstützt:
- bestehende Fachlogik
- bestehende Services
- bestehende Verantwortlichkeiten
- bestehende Architekturprinzipien

---

## Wichtige Referenzen

- CONTENT.md
- docs/didaktik/entwicklungslogik.md
- efz-entwicklung-meta

---

## Offene didaktische Entscheidungen

- DTOs bewusst klein und kontrolliert einführen
- Validation erst nach DTO-Verständnis vertiefen
- JPA erst nach stabiler REST-Struktur behandeln
- Spring weiterhin kontrolliert und transparent einsetzen
- HTTP und REST weiterhin technisch nachvollziehbar halten
