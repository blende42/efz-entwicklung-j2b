# efz-entwicklung-j2b

Dieses Repository führt die interne Ausbildung nach dem REST-Einstieg aus
`efz-entwicklung-j2a`
weiter.

Der Schwerpunkt liegt auf:
- professioneller REST-Strukturierung
- Spring Boot
- DTOs
- Validation
- Fehlerbehandlung
- sauberer Backend-Architektur
- Vorbereitung auf JPA/Spring Data

Die Ausbildung bleibt:
- problemgetrieben
- architekturorientiert
- frameworkkontrolliert
- praxisnah

Neue Konzepte werden weiterhin aus konkreten Problemen und wachsender technischer Komplexität entwickelt.

---

# Didaktische Leitidee

Spring ersetzt bestehende Architektur nicht.

Spring unterstützt:
- bestehende Services
- bestehende Verantwortlichkeiten
- bestehende Fachlogik
- bestehende Repository-Strukturen

Die Architektur wird evolutionär weiterentwickelt.

---

# Ausbildungsstand beim Einstieg

Vorausgesetzt werden:
- Java/OOP
- Maven
- JUnit
- Refactoring
- JDBC/H2
- Repository
- Logging
- technische Konfiguration
- I18N
- REST-Einstieg
- HTTP-Grundlagen
- curl
- Bruno-Workflows

---

# Nächste Themenblöcke

- Spring-Konfiguration
- Vorbereitung auf JPA/Spring Data
- Integrationstests

---

# Lerneinheiten

## 46. JSON-Strukturen und DTOs in REST-APIs

Erstellt:
- [Arbeitsblatt](Arbeitsblaetter/Arbeitsblatt_JSON_DTOs_REST.md)
- [Übungen](Uebungen/Uebungen_JSON_DTOs_REST.md)
- [Musterlösungen](Musterloesungen/Loesungen_JSON_DTOs_REST.md)
- [Grafik](graphics/json_dto_rest.svg)

Fokus:
- interne Fachobjekte von externer API-Struktur trennen
- DTOs problemgetrieben einführen
- JSON-Ausgabe und JSON-Eingabe bewusst gestalten
- manuelles Mapping in einfachen Spring-Boot-REST-Endpunkten verwenden
- Bruno und `curl` zur Prüfung der JSON-Struktur einsetzen

---

## 47. Collections Framework in REST- und DTO-Anwendungen

Erstellt:
- [Arbeitsblatt](Arbeitsblaetter/Arbeitsblatt_Collections_Framework_REST.md)
- [Übungen](Uebungen/Uebungen_Collections_Framework_REST.md)
- [Musterlösungen](Musterloesungen/Loesungen_Collections_Framework_REST.md)
- [Grafik](graphics/collections_framework_rest.svg)

Fokus:
- `List`, `Set` und `Map` im REST-/DTO-Kontext unterscheiden
- DTO-Listen mit klassischer Iteration aufbauen
- `ArrayList`, `HashSet` und `HashMap` passend einsetzen
- REST-Ausgaben mit mehreren Objekten bewusst einordnen
- auf spätere Streams vorbereiten, ohne Streams einzuführen

---

## 48. Streams und Transformationen in REST- und DTO-Anwendungen

Erstellt:
- [Arbeitsblatt](Arbeitsblaetter/Arbeitsblatt_Streams_Transformationen_REST.md)
- [Übungen](Uebungen/Uebungen_Streams_Transformationen_REST.md)
- [Musterlösungen](Musterloesungen/Loesungen_Streams_Transformationen_REST.md)
- [Grafik](graphics/streams_transformationen_rest.svg)

Fokus:
- Streams als kontrollierte Verarbeitung von Collections verstehen
- DTO-Mapping mit `map()` ausdrücken
- einfache Filter mit `filter()` einsetzen
- Stream-Ergebnisse mit `toList()` wieder als REST-Liste zurückgeben
- klassische Schleifen und Stream-Transformationen vergleichen

---

## 49. Enums in Fachmodellen und REST-APIs

Erstellt:
- [Arbeitsblatt](Arbeitsblaetter/Arbeitsblatt_Enums_REST.md)
- [Übungen](Uebungen/Uebungen_Enums_REST.md)
- [Musterlösungen](Musterloesungen/Loesungen_Enums_REST.md)
- [Grafik](graphics/enums_rest.svg)

Fokus:
- kontrollierte Zustände statt magischer Strings verstehen
- `ProduktStatus` als einfachen Enum-Wertebereich einsetzen
- Enums im Fachmodell und in DTOs verwenden
- REST-/JSON-Darstellung von Enum-Werten einordnen
- `switch` und Streams mit Enum-Werten in kleinen Beispielen nutzen

---

## 50. REST-Fehlerbehandlung und Statuscodes

Erstellt:
- [Arbeitsblatt](Arbeitsblaetter/Arbeitsblatt_REST_Fehlerbehandlung_Statuscodes.md)
- [Übungen](Uebungen/Uebungen_REST_Fehlerbehandlung_Statuscodes.md)
- [Musterlösungen](Musterloesungen/Loesungen_REST_Fehlerbehandlung_Statuscodes.md)
- [Grafik](graphics/rest_fehlerbehandlung_statuscodes.svg)

Fokus:
- REST-Fehler kontrolliert zurückgeben
- HTTP-Statuscodes `200`, `201`, `400`, `404` und `500` sinnvoll einsetzen
- `ResponseEntity` für Statuscode und JSON-Body verwenden
- einfache Fehlerantwort als JSON-DTO strukturieren
- Fehlerfälle mit Bruno und `curl -i` prüfen

---

## 51. Optional und kontrollierte Null-Behandlung

Erstellt:
- [Arbeitsblatt](Arbeitsblaetter/Arbeitsblatt_Optional_Nullbehandlung.md)
- [Übungen](Uebungen/Uebungen_Optional_Nullbehandlung.md)
- [Musterlösungen](Musterloesungen/Loesungen_Optional_Nullbehandlung.md)
- [Grafik](graphics/optional_nullbehandlung.svg)

Fokus:
- fehlende Werte sichtbar statt still mit `null` modellieren
- `Optional<T>` im Repository und Service einsetzen
- `isPresent()`, `isEmpty()`, `orElse()` und `orElseThrow()` einfach verwenden
- REST-Fehlerbehandlung mit `Optional.empty()` und `404` verbinden
- Verantwortlichkeiten zwischen Repository, Service und Controller sauber halten

---

## 52. Validation bei REST-Requests

Erstellt:
- [Arbeitsblatt](Arbeitsblaetter/Arbeitsblatt_Validation_REST_Requests.md)
- [Übungen](Uebungen/Uebungen_Validation_REST_Requests.md)
- [Musterlösungen](Musterloesungen/Loesungen_Validation_REST_Requests.md)
- [Grafik](graphics/validation_rest_requests.svg)

Fokus:
- ungültige JSON-Requests früh erkennen
- Bean Validation auf Request-DTOs einsetzen
- `@Valid` und `BindingResult` im REST Controller verwenden
- einfache `400 Bad Request`-Antworten mit `ErrorResponse` zurückgeben
- Validation von Fachlogik im Service trennen
- Bruno und `curl -i` zur Prüfung von Validierungsfehlern einsetzen

---

## 53. Services mit Spring sauber integrieren

Erstellt:
- [Arbeitsblatt](Arbeitsblaetter/Arbeitsblatt_Spring_Services_DI.md)
- [Übungen](Uebungen/Uebungen_Spring_Services_DI.md)
- [Musterlösungen](Musterloesungen/Loesungen_Spring_Services_DI.md)
- [Grafik](graphics/spring_services_di.svg)

Fokus:
- Spring als Infrastruktur für Objekterzeugung und Verdrahtung verstehen
- `@RestController`, `@Service` und `@Repository` ihren Rollen zuordnen
- Constructor Injection im Controller und Service verwenden
- manuelle `new`-Aufrufe für Services und Repositorys vermeiden
- Fachlogik im Service und Datenzugriff im Repository belassen
- bestehende Bruno- und `curl`-Prüfungen nach der DI-Umstellung erneut ausführen

---

# Repository-Struktur

## Arbeitsblaetter/

Didaktische Theorieblätter und Einführungseinheiten.

---

## Uebungen/

Praktische Übungen:
- Basis
- Vertiefung
- Transfer

---

## Musterloesungen/

Kompakte Musterlösungen und Lösungsansätze.

---

## Projekte/

Grössere zusammenhängende Projektarbeiten.

### REST-Lagerverwaltung professionell erweitern

Erstellt:
- [Projektübersicht](Projekte/REST_Lagerverwaltung_Erweitern/README.md)
- [Projektauftrag für Lernende](Projekte/REST_Lagerverwaltung_Erweitern/Lernende/Projektauftrag_REST_Lagerverwaltung_Erweitern.md)
- [Projektauftrag für Lehrpersonen](Projekte/REST_Lagerverwaltung_Erweitern/Lehrperson/Projektauftrag_REST_Lagerverwaltung_Erweitern_LP.md)
- [Musterlösung](Projekte/REST_Lagerverwaltung_Erweitern/Musterloesung/README.md)
- [Projektreview](Projekte/REST_Lagerverwaltung_Erweitern/Review/Projektreview_REST_Lagerverwaltung_Erweitern.md)
- [Reflexion](Projekte/REST_Lagerverwaltung_Erweitern/Review/Reflexion_REST_Lagerverwaltung_Erweitern.md)

Fokus:
- DTOs, Collections, Streams und Enums in einer kleinen REST-Lagerverwaltung kombinieren
- `ProduktStatus` als kontrollierten Zustand einsetzen
- Controller, Service und Repository sauber trennen
- Bruno-Workflows für API-Prüfungen verwenden
- vollständige lauffähige Spring-Boot-Musterlösung als Referenzstand bereitstellen

---

## graphics/

SVG-Grafiken und didaktische Visualisierungen.

---

## docs/

Zusätzliche didaktische Dokumentation.

### docs/didaktik/

Didaktische Leitideen, Entwicklungslogik und qualitative Bewertungskriterien:
- [Entwicklungslogik](docs/didaktik/entwicklungslogik.md)
- [Bewertungskriterien](docs/didaktik/bewertungskriterien.md)

### docs/prozesse/

Projekt-, Review- und Entwicklungsprozesse.

### docs/begriffe/

Technische Begriffserklärungen.

---

## .agents/skills/

Codex-/KI-Skills für:
- Arbeitsblätter
- Übungen
- Reviews
- SVG
- Java/Maven-Validierung

---

# Arbeitsweise mit KI/Codex

Das Repository ist für strukturierte KI-gestützte Entwicklungsarbeit vorbereitet.

Wichtige Dateien:
- AGENTS.md
- NEXT_STEPS.md
- PROMPT_STRATEGIE.md

Sessions sollen:
- klein
- fokussiert
- tokeneffizient

bleiben.

---

# Qualitätsziele

- kleine nachvollziehbare Schritte
- EFZ-gerechte Komplexität
- technische Transparenz
- saubere Verantwortlichkeiten
- verständnisorientierte Architekturentwicklung
- reproduzierbare Entwicklungsworkflows
- qualitative Bewertungsschwerpunkte für grössere Übungen, Projekte und Reviews
