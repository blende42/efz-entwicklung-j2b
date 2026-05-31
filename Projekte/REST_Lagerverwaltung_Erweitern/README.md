# Projekt – REST-Lagerverwaltung professionell erweitern

## Ziel

Dieses Integrationsprojekt festigt die bisherigen Konzepte aus REST, DTOs, Collections, Streams und Enums in einer kleinen Spring-Boot-Anwendung.

Die Lernenden erweitern eine REST-Lagerverwaltung so, dass Produkte über eine kontrollierte JSON-Struktur gelesen, angelegt, nach Status gefiltert und im Status geändert werden können.

Zusätzlich gibt es eine separate Musterlösung als Referenz für die spätere Zielstruktur mit JPA, Spring Data, H2, Validation und Integrationstests.

---

## Fachlicher Kontext

Produkte besitzen einen kontrollierten Zustand:

- `AKTIV`
- `RESERVIERT`
- `DEFEKT`
- `ARCHIVIERT`

Diese Zustände werden nicht als freie Strings modelliert, sondern als `ProduktStatus`-Enum.

---

## Technischer Rahmen

- Java 21
- Spring Boot
- Maven
- REST Controller
- Service-Schicht
- einfache In-Memory-Repository-Implementierung
- DTOs für REST-Eingabe und REST-Ausgabe
- Collections für Produktverwaltung
- Streams für Filterung und DTO-Mapping
- Bruno-Requests für API-Workflows

Nicht Teil dieses Projekts:

- Security
- JPA/Spring Data
- Validation
- Lombok
- MapStruct
- komplexe Fehlerbehandlung
- Datenbankintegration

Zusätzlich zeigt die separate Musterlösung als weiterführender Referenzstand:

- JPA-Entity
- Spring Data Repository
- H2-Datenbank
- Bean Validation
- einfache Fehlerantworten
- MockMvc-Integrationstests

---

## Projektmaterial

- [Projektauftrag für Lernende](Lernende/Projektauftrag_REST_Lagerverwaltung_Erweitern.md)
- [Projektauftrag für Lehrpersonen](Lehrperson/Projektauftrag_REST_Lagerverwaltung_Erweitern_LP.md)
- [Musterlösung](Musterloesung/README.md)
- [Zusätzliche Musterlösung JPA und Integrationstests](Musterloesung_JPA_Integrationstests/README.md)
- [Projektreview](Review/Projektreview_REST_Lagerverwaltung_Erweitern.md)
- [Reflexion](Review/Reflexion_REST_Lagerverwaltung_Erweitern.md)

---

## Erwartete Architektur

```text
Client / Bruno
-> REST Controller
-> ProduktService
-> ProduktRepository
-> In-Memory-Collection
```

Die REST-API gibt DTOs zurück. Das Fachmodell bleibt intern.

Die zusätzliche Musterlösung verwendet anstelle der In-Memory-Collection ein Spring Data Repository mit H2.
