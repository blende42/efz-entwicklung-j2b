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

# Geplante Themenblöcke

- REST-Fehlerbehandlung
- Validation bei REST-Requests
- Dependency Injection
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

---

## graphics/

SVG-Grafiken und didaktische Visualisierungen.

---

## docs/

Zusätzliche didaktische Dokumentation.

### docs/didaktik/

Didaktische Leitideen, Entwicklungslogik und qualitative Bewertungskriterien:
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
