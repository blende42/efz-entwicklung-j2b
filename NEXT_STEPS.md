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

Die Lerneinheit 47 ist erstellt:

- Arbeitsblatt: `Arbeitsblaetter/Arbeitsblatt_Collections_Framework_REST.md`
- Übungen: `Uebungen/Uebungen_Collections_Framework_REST.md`
- Musterlösungen: `Musterloesungen/Loesungen_Collections_Framework_REST.md`
- Grafik: `graphics/collections_framework_rest.svg`

Schwerpunkt:

- Collections in REST- und DTO-Anwendungen verstehen
- `List`, `Set` und `Map` unterscheiden
- DTO-Listen mit klassischer Iteration aufbauen
- `ArrayList`, `HashSet` und `HashMap` passend einsetzen
- bewusst noch keine Streams verwenden

Die Lerneinheit 48 ist erstellt:

- Arbeitsblatt: `Arbeitsblaetter/Arbeitsblatt_Streams_Transformationen_REST.md`
- Übungen: `Uebungen/Uebungen_Streams_Transformationen_REST.md`
- Musterlösungen: `Musterloesungen/Loesungen_Streams_Transformationen_REST.md`
- Grafik: `graphics/streams_transformationen_rest.svg`

Schwerpunkt:

- Streams als kontrollierte Verarbeitung von Collections verstehen
- DTO-Mapping mit `map()` ausdrücken
- einfache Filter mit `filter()` einsetzen
- Stream-Ergebnisse mit `toList()` wieder als REST-Liste zurückgeben
- klassische Schleifen und Stream-Transformationen vergleichen

Die Lerneinheit 49 ist erstellt:

- Arbeitsblatt: `Arbeitsblaetter/Arbeitsblatt_Enums_REST.md`
- Übungen: `Uebungen/Uebungen_Enums_REST.md`
- Musterlösungen: `Musterloesungen/Loesungen_Enums_REST.md`
- Grafik: `graphics/enums_rest.svg`

Schwerpunkt:

- kontrollierte Zustände statt magischer Strings verstehen
- `ProduktStatus` als Enum-Wertebereich einführen
- Enums im Fachmodell und in DTOs verwenden
- REST-/JSON-Darstellung von Enums prüfen
- `switch` und Streams mit Enum-Werten in kleinen Beispielen nutzen

Die Lerneinheit 50 ist erstellt:

- Arbeitsblatt: `Arbeitsblaetter/Arbeitsblatt_REST_Fehlerbehandlung_Statuscodes.md`
- Übungen: `Uebungen/Uebungen_REST_Fehlerbehandlung_Statuscodes.md`
- Musterlösungen: `Musterloesungen/Loesungen_REST_Fehlerbehandlung_Statuscodes.md`
- Grafik: `graphics/rest_fehlerbehandlung_statuscodes.svg`

Schwerpunkt:

- REST-Fehler kontrolliert zurückgeben
- HTTP-Statuscodes `200`, `201`, `400`, `404` und `500` sinnvoll einsetzen
- `ResponseEntity` für Statuscode und JSON-Body verwenden
- einfache Fehlerantwort als JSON-DTO strukturieren
- Fehler bei unbekannter Produkt-ID und ungültigem `ProduktStatus` behandeln
- Fehlerfälle mit Bruno und `curl -i` prüfen

Die Lerneinheit 51 ist erstellt:

- Arbeitsblatt: `Arbeitsblaetter/Arbeitsblatt_Optional_Nullbehandlung.md`
- Übungen: `Uebungen/Uebungen_Optional_Nullbehandlung.md`
- Musterlösungen: `Musterloesungen/Loesungen_Optional_Nullbehandlung.md`
- Grafik: `graphics/optional_nullbehandlung.svg`

Schwerpunkt:

- fehlende Werte bewusst und sichtbar modellieren
- typische `null`-Probleme und `NullPointerException` vermeiden
- `Optional<T>` in Repository und Service einfach einsetzen
- `isPresent()`, `isEmpty()`, `orElse()` und `orElseThrow()` verwenden
- REST-Fehlerbehandlung mit `Optional.empty()` und `404` verbinden
- Verantwortlichkeiten zwischen Repository, Service und Controller sauber halten

Die Lerneinheit 52 ist erstellt:

- Arbeitsblatt: `Arbeitsblaetter/Arbeitsblatt_Validation_REST_Requests.md`
- Übungen: `Uebungen/Uebungen_Validation_REST_Requests.md`
- Musterlösungen: `Musterloesungen/Loesungen_Validation_REST_Requests.md`
- Grafik: `graphics/validation_rest_requests.svg`

Schwerpunkt:

- ungültige JSON-Requests früh und kontrolliert abweisen
- Bean Validation auf Request-DTOs einsetzen
- `@Valid` und `BindingResult` im REST Controller verwenden
- einfache `400 Bad Request`-Antworten mit `ErrorResponse` zurückgeben
- Validation klar von Fachlogik im Service trennen
- Bruno und `curl -i` zur Prüfung von Validierungsfehlern einsetzen

Die Lerneinheit 53 ist erstellt:

- Arbeitsblatt: `Arbeitsblaetter/Arbeitsblatt_Spring_Services_DI.md`
- Übungen: `Uebungen/Uebungen_Spring_Services_DI.md`
- Musterlösungen: `Musterloesungen/Loesungen_Spring_Services_DI.md`
- Grafik: `graphics/spring_services_di.svg`

Schwerpunkt:

- Spring als Infrastruktur für Objekterzeugung und Verdrahtung verstehen
- `@RestController`, `@Service` und `@Repository` ihren Rollen zuordnen
- Constructor Injection im Controller und Service verwenden
- manuelle `new`-Aufrufe für Services und Repositorys vermeiden
- Fachlogik im Service und Datenzugriff im Repository belassen
- bestehende Bruno- und `curl`-Prüfungen nach der DI-Umstellung erneut ausführen

Die Lerneinheit 54 ist erstellt:

- Arbeitsblatt: `Arbeitsblaetter/Arbeitsblatt_Spring_Container_DI_Verstehen.md`
- Übungen: `Uebungen/Uebungen_Spring_Container_DI_Verstehen.md`
- Musterlösungen: `Musterloesungen/Loesungen_Spring_Container_DI_Verstehen.md`
- Grafik: `graphics/spring_container_di_verstehen.svg`

Schwerpunkt:

- Spring Container als einfache Objektverwaltung verstehen
- manuelle Verdrahtung mit Spring-Verdrahtung vergleichen
- Objektgraphen ohne und mit Spring lesen
- Constructor Injection als sichtbare Übergabe von Abhängigkeiten erklären
- Architektur und Verdrahtung unterscheiden
- JPA/Spring Data vorbereiten, ohne JPA bereits einzuführen

Die Lerneinheit 55 ist erstellt:

- Arbeitsblatt: `Arbeitsblaetter/Arbeitsblatt_JPA_Spring_Data_Grundlagen.md`
- Übungen: `Uebungen/Uebungen_JPA_Spring_Data_Grundlagen.md`
- Musterlösungen: `Musterloesungen/Loesungen_JPA_Spring_Data_Grundlagen.md`
- Grafiken:
  - `graphics/jpa_spring_data_architektur.svg`
  - `graphics/jdbc_jpa_spring_data_vergleich.svg`

Schwerpunkt:

- JDBC-Mapping-Aufwand mit SQL, `ResultSet` und Objektaufbau sichtbar machen
- JPA als Java-Standard für Objekt-Persistenz erklären
- Hibernate als konkrete JPA-Implementierung einordnen
- Spring Data JPA als Spring-Integration auf Basis von JPA erklären
- `@Entity`, `@Id`, `@GeneratedValue` und `JpaRepository` auf EFZ-Niveau einführen
- Architektur `Controller -> Service -> Repository` trotz Persistenz-Abstraktion beibehalten
- bewusst noch keine komplexen JPA-Themen wie `EntityManager`, Persistence Context, Lazy Loading, Cascade oder Beziehungen behandeln

Das Integrationsprojekt ist erstellt:

- Projektübersicht: `Projekte/REST_Lagerverwaltung_Erweitern/README.md`
- Lernendenauftrag: `Projekte/REST_Lagerverwaltung_Erweitern/Lernende/Projektauftrag_REST_Lagerverwaltung_Erweitern.md`
- Lehrpersonenauftrag: `Projekte/REST_Lagerverwaltung_Erweitern/Lehrperson/Projektauftrag_REST_Lagerverwaltung_Erweitern_LP.md`
- Musterlösung: `Projekte/REST_Lagerverwaltung_Erweitern/Musterloesung/`
- Bruno-Requests: `Projekte/REST_Lagerverwaltung_Erweitern/Musterloesung/bruno/`
- Review: `Projekte/REST_Lagerverwaltung_Erweitern/Review/Projektreview_REST_Lagerverwaltung_Erweitern.md`
- Reflexion: `Projekte/REST_Lagerverwaltung_Erweitern/Review/Reflexion_REST_Lagerverwaltung_Erweitern.md`

Schwerpunkt:

- DTOs, Collections, Streams und Enums in einem kleinen REST-Projekt festigen
- `ProduktStatus` als kontrollierten Zustand verwenden
- REST-Struktur mit Controller, Service und Repository stabil halten
- Bruno-Workflows für API-Prüfungen einsetzen
- vollständige Spring-Boot-Musterlösung als Referenzstand bereitstellen

---

## Nächste geplante Einheiten

56. Integrationstests
57. Abschlussprojekt j2b: Mini-Shop / Bestellverwaltung

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
- JPA/Spring Data

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
- Validation bleibt zunächst request-nah und einfach
- Dependency Injection bleibt zunächst auf Constructor Injection beschränkt
- JPA erst nach stabiler REST- und DI-Struktur behandeln
- Spring weiterhin kontrolliert und transparent einsetzen
- HTTP und REST weiterhin technisch nachvollziehbar halten

---

## Geplanter Abschluss j2b

Das Abschlussprojekt j2b ist ein Mini-Shop mit Bestellverwaltung:

- fachlich: Produkt, Kategorie, Kunde, Bestellung, Bestellposition, Bestand prüfen und reduzieren, Bestellungen anzeigen
- technisch: REST, DTOs, Validation, Dependency Injection, JPA, Spring Data, Integrationstests
- nicht enthalten: Security, Login, Authentication, Authorization, JWT/OAuth, Frontend, Payment, Rabatte, Versand, Rechnungen, Retouren, Microservices

j3a startet danach mit Security:

```text
Der Mini-Shop funktioniert, aber wer darf was?
```
