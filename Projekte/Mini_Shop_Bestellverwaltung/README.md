# Projekt – Mini-Shop / Bestellverwaltung

## Ziel

Dieses Abschlussprojekt schliesst `efz-entwicklung-j2b` ab.

Die Lernenden erstellen einen kleinen Mini-Shop mit Bestellverwaltung. Dabei werden die bisherigen Themen in einem zusammenhängenden Spring-Boot-Projekt kombiniert:

- REST
- DTOs
- Validation
- Services und Dependency Injection
- JPA Entities
- Spring Data Repositorys
- H2
- Integrationstests
- Bruno-Workflows

Das Projekt führt keine neuen grossen Konzepte ein. Es zeigt, ob die bekannten Bausteine in einer fachlich zusammenhängenden Anwendung verstanden und sauber getrennt werden können.

---

## Fachlicher Kontext

Der Mini-Shop verwaltet:

- Kategorien
- Produkte
- Kunden
- Bestellungen
- Bestellpositionen

Beim Erstellen einer Bestellung muss geprüft werden:

- Der Kunde existiert.
- Jedes Produkt existiert.
- Der Bestand reicht für jede Position aus.
- Bei erfolgreicher Bestellung wird der Bestand reduziert.
- Die Bestellung kann danach angezeigt werden.

---

## Technischer Rahmen

- Java 21
- Spring Boot
- Maven
- REST Controller
- DTOs für Eingabe und Ausgabe
- Bean Validation
- Services mit Constructor Injection
- JPA Entities
- Spring Data Repositorys
- H2-Datenbank
- MockMvc-Integrationstests
- Bruno-Collection für API-Workflows

Bewusst nicht Teil dieses Projekts:

- Security
- Login
- JWT/OAuth
- Frontend
- Payment
- Versand
- Rabatte
- Rechnungen
- Microservices

---

## Projektmaterial

- [Projektauftrag für Lernende](Lernende/Projektauftrag_Mini_Shop_Bestellverwaltung.md)
- [Projektauftrag für Lehrpersonen](Lehrperson/Projektauftrag_Mini_Shop_Bestellverwaltung_LP.md)
- [Musterlösung](Musterloesung/README.md)
- [Projektreview](Review/Projektreview_Mini_Shop_Bestellverwaltung.md)
- [Reflexion](Review/Reflexion_Mini_Shop_Bestellverwaltung.md)

---

## Erwartete Architektur

```text
Client / Bruno
-> REST Controller
-> DTO / Validation
-> Service
-> Spring Data Repository
-> H2-Datenbank
```

Die Fachregeln liegen im Service. Controller übersetzen HTTP-Anfragen und HTTP-Antworten. Repositorys kapseln den Datenzugriff.
