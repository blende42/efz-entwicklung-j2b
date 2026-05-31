# Lehrpersonen-Version – Mini-Shop / Bestellverwaltung

## Didaktische Einordnung

Dieses Projekt schliesst `efz-entwicklung-j2b` ab. Es konsolidiert die Themen REST, DTOs, Validation, Dependency Injection, JPA/Spring Data und Integrationstests in einer fachlich zusammenhängenden Anwendung.

Das Projekt soll keine neuen grossen Konzepte einführen. Lernende sollen die bekannte Architektur sicher anwenden:

```text
Controller -> DTO / Validation -> Service -> Repository -> Datenbank
```

Der Anschluss an `j3a` bleibt sichtbar, aber Security ist noch nicht Teil des Projekts.

---

## Erwartete Kernlösung

Eine tragfähige Lösung enthält:

- JPA Entities für Kategorie, Produkt, Kunde, Bestellung und Bestellposition
- Spring Data Repositorys
- REST Controller für Kategorien, Produkte, Kunden und Bestellungen
- Request- und Response-DTOs
- Bean Validation auf Request-DTOs
- Service-Schicht für Fachlogik
- kontrollierte Fehlerbehandlung mit `400` und `404`
- Bestandprüfung und Bestandreduktion im Bestellservice
- H2 als Datenbank
- Integrationstests mit MockMvc
- Bruno-Requests für zentrale Abläufe

---

## Beobachtungspunkte

### Funktionalität

- Können Kategorien, Produkte und Kunden erstellt werden?
- Kann eine gültige Bestellung erstellt werden?
- Wird der Bestand nach erfolgreicher Bestellung reduziert?
- Kann eine Bestellung mit Positionen angezeigt werden?
- Werden ungültige Requests mit `400` beantwortet?
- Werden unbekannte Ressourcen mit `404` beantwortet?

### Verantwortlichkeiten

- Bleibt HTTP-Verarbeitung im Controller?
- Liegt die Bestandprüfung im Service?
- Werden Repositorys nicht direkt als Fachlogik-Ersatz verwendet?
- Werden DTOs nicht als Entities verwendet?
- Bleiben Entities frei von REST-spezifischer Logik?

### Verständlichkeit

- Sind Klassen und DTOs klar benannt?
- Ist der Ablauf einer Bestellung nachvollziehbar?
- Können Lernende erklären, warum Bestellung und Bestellposition getrennt sind?
- Können Lernende den Weg vom Request bis zur Datenbank erklären?

### Technische Sauberkeit

- Wird Constructor Injection verwendet?
- Werden `JpaRepository`-Interfaces passend eingesetzt?
- Werden Validierungsannotationen sinnvoll verwendet?
- Sind Integrationstests kontrolliert und reproduzierbar?
- Bleiben Bruno-Requests nachvollziehbar benannt?

### Lernzielerreichung

- Werden die j2b-Konzepte zusammenhängend angewendet?
- Können Lernende Fachlogik, REST-Struktur und Persistenz unterscheiden?
- Wird Testbarkeit als Qualitätsmerkmal sichtbar?
- Wird Security als nächster Schritt begründbar, aber noch nicht umgesetzt?

---

## Bewertungsfokus

| Bereich | Beobachtung |
|---|---|
| Funktionalität | Bestellung, Anzeige, Bestandprüfung und Fehlerfälle funktionieren nachvollziehbar. |
| Verantwortlichkeiten | Controller, DTOs, Service, Repository und Entities sind klar getrennt. |
| Verständlichkeit | Namen, Datenfluss und fachlicher Ablauf sind erklärbar. |
| Technische Sauberkeit | Validation, JPA, Spring Data, H2, Integrationstests und Bruno werden passend eingesetzt. |
| Lernzielerreichung | Die Lösung zeigt, dass die zentralen j2b-Konzepte gemeinsam angewendet werden können. |

Qualitative Einschätzungen:

- gut erfüllt
- teilweise erfüllt
- noch unklar
- Verbesserungsbedarf

---

## Typische Schwierigkeiten

- Lernende schreiben Bestandlogik direkt in den Controller.
- Request-DTOs werden als Entities gespeichert.
- Entities werden direkt als REST-Response zurückgegeben.
- Fehlerfälle liefern unkontrolliert `500`.
- Bestand wird reduziert, obwohl später eine Position fehlschlägt.
- Integrationstests prüfen nur Statuscodes und nicht Datenbankzustand.
- Bruno-Requests fehlen für Fehlerfälle.
- Optionale Erweiterungen werden begonnen, bevor der Pflichtablauf stabil ist.

---

## Mögliche Hilfsimpulse

- "Welche Klasse kennt HTTP?"
- "Welche Klasse sollte wissen, ob der Bestand reicht?"
- "Welche Daten kommen vom Client, welche Daten erzeugt die Anwendung?"
- "Welche Repository-Abfrage brauchst du für diese Fachregel?"
- "Welcher Test zeigt, dass der Bestand wirklich reduziert wurde?"
- "Welche Bruno-Anfrage beweist den Fehlerfall?"

---

## Review-Empfehlung

Das Review kann als Architekturgespräch geführt werden:

1. Lernende demonstrieren eine gültige Bestellung mit Bruno.
2. Lernende zeigen den passenden Integrationstest.
3. Lernende erklären den Weg vom Request über Controller und Service bis zur Datenbank.
4. Lehrperson wählt einen Fehlerfall, zum Beispiel zu wenig Bestand.
5. Lernende zeigen, wo `400` oder `404` erzeugt wird.

---

## Abgrenzung

Nicht erwarten oder bewerten:

- Security
- Login
- Rollen oder Berechtigungen
- JWT/OAuth
- Frontend
- Payment
- Versand
- Rabatte
- Rechnungen
- Microservices
- produktionsreife Shop-Funktionalität
