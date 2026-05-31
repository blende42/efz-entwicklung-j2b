# Übungen – Integrationstests

## Ziel

Du prüfst REST-Abläufe automatisiert mit Spring Boot und MockMvc.

Dabei geht es nicht nur um einzelne Methoden, sondern um das Zusammenspiel von:

- Controller
- Validation
- Service
- Repository
- Datenbank

---

## Vorwissen

Du solltest bereits:

- REST-Endpunkte lesen können
- DTOs und JSON-Strukturen verstehen
- HTTP-Statuscodes einordnen
- Validation und Fehlerantworten kennen
- `Optional` und `404 Not Found` verstehen
- Spring DI und den Spring Container grob erklären können
- JPA, Spring Data und H2 einordnen können

---

## Startpunkt

Für diese Übungen verwenden wir MockMvc.

Die Aufgaben gehen davon aus, dass die REST-Lagerverwaltung bereits eine JPA-/Spring-Data-Persistenz mit H2-Testdatenbank verwendet.

Die Testklasse hat ungefähr diese Form:

```java
@SpringBootTest
@AutoConfigureMockMvc
class ProduktIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
}
```

Für Tests mit H2 und Spring Data ist es sinnvoll, Testdaten vor jedem Test kontrolliert vorzubereiten.

Verwende in diesen Übungen kein Mockito. Die Tests sollen den echten Ablauf durch Controller, Validation, Service, Repository und H2 prüfen.

---

## Basis

### Aufgabe 1: Unit-Test oder Integrationstest?

Ordne die Aussagen zu.

| Aussage | Unit-Test oder Integrationstest? |
|---|---|
| Prüft nur eine Service-Methode mit direkt erzeugtem Repository | |
| Sendet einen `POST /produkte` Request mit JSON | |
| Prüft Controller, Service, Repository und H2 gemeinsam | |
| Prüft eine einzelne Berechnung ohne Spring-Kontext | |
| Prüft, ob ein ungültiger Request `400 Bad Request` ergibt | |

---

### Aufgabe 2: Testpfad markieren

Markiere, welche Stationen bei einem REST-Integrationstest beteiligt sind.

```text
MockMvc
  -> HTTP Request
  -> Controller
  -> Validation
  -> Service
  -> Repository
  -> H2
```

Beantworte:

- Welche Schicht verarbeitet HTTP?
- Wo wird Validation sichtbar?
- Wo wird gespeichert oder geladen?

---

### Aufgabe 3: Bestehenden REST-Endpunkt testen

Schreibe einen Test für `GET /produkte`.

Erwartung:

- Statuscode `200 OK`
- Antwort ist eine JSON-Liste

Startcode:

```java
@Test
void gibtProduktlisteZurueck() throws Exception {
    mockMvc.perform(get("/produkte"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray());
}
```

Erkläre danach kurz, warum das ein Integrationstest ist.

---

### Aufgabe 4: Gültigen POST testen

Schreibe einen Test für `POST /produkte` mit gültigem JSON.

Beispiel-JSON:

```json
{
  "name": "Tastatur",
  "preis": 49.90,
  "status": "AKTIV"
}
```

Prüfe:

- Statuscode `201 Created`
- Feld `name`
- Feld `status`

---

### Aufgabe 5: Ungültigen POST testen

Schreibe einen Test für `POST /produkte` mit ungültigem JSON.

Beispiel:

```json
{
  "name": "",
  "preis": 49.90,
  "status": "AKTIV"
}
```

Prüfe:

- Statuscode `400 Bad Request`
- falls vorhanden: Fehlerantwort enthält eine Meldung

---

### Aufgabe 6: GET mit gültiger ID testen

Bereite ein Produkt in der Datenbank vor.

Teste danach:

```text
GET /produkte/{id}
```

Prüfe:

- Statuscode `200 OK`
- Feld `id`
- Feld `name`

Notiere, warum der Test kontrollierte Testdaten braucht.

---

### Aufgabe 7: GET mit unbekannter ID testen

Teste:

```text
GET /produkte/999999
```

Prüfe:

- Statuscode `404 Not Found`
- falls vorhanden: Fehlerantwort enthält eine Meldung

---

## Vertiefung

### Aufgabe 8: Validation im Test prüfen

Erstelle mindestens zwei ungültige Requests:

- leerer Name
- negativer Preis

Prüfe jeweils:

- Statuscode `400`
- eine verständliche Fehlerantwort

Erkläre, warum dieser Test mehr prüft als eine einzelne Service-Methode.

---

### Aufgabe 9: Fehlerantworten prüfen

Eine Fehlerantwort könnte so aussehen:

```json
{
  "message": "Produkt nicht gefunden"
}
```

Erweitere einen Fehlerfall-Test so, dass nicht nur der Statuscode, sondern auch `message` geprüft wird.

---

### Aufgabe 10: Datenbankzustand prüfen

Schreibe einen Test, der nach einem gültigen `POST /produkte` zusätzlich im Repository prüft, ob das Produkt gespeichert wurde.

Beispiel:

```java
assertThat(produktRepository.findAll())
        .anyMatch(produkt -> produkt.getName().equals("Tastatur"));
```

Begründe, warum diese Prüfung bei Integrationstests sinnvoll sein kann.

---

### Aufgabe 11: Unit-Test und Integrationstest erklären

Fülle die Tabelle aus.

| Frage | Unit-Test | Integrationstest |
|---|---|---|
| Wie viele Schichten werden typischerweise geprüft? | | |
| Wird Spring gestartet? | | |
| Wird ein HTTP-Request simuliert? | | |
| Wird die Datenbank mitgeprüft? | | |
| Was ist der Hauptnutzen? | | |

---

## Transfer

### Aufgabe 12: Warum Integrationstests wichtig sind

Begründe in vier bis sechs Sätzen:

```text
Integrationstests sind wichtig, obwohl bereits Unit-Tests existieren.
```

Verwende mindestens drei Begriffe:

- Controller
- Validation
- Service
- Repository
- Datenbank
- Statuscode
- JSON

---

### Aufgabe 13: Gemeinsam getestete Schichten erklären

Erkläre den Ablauf:

```text
MockMvc -> Controller -> Validation -> Service -> Repository -> H2
```

Schreibe zu jeder Station einen kurzen Satz.

---

### Aufgabe 14: Fehler erkennen, die Unit-Tests nicht finden

Welche Fehler kann ein Integrationstest eher finden als ein reiner Unit-Test?

Kreuze an und begründe kurz.

| Fehler | Eher Integrationstest? |
|---|---|
| falsche URL im Controller | |
| falscher HTTP-Statuscode | |
| Service-Methode rechnet intern falsch | |
| JSON-Feld heisst anders als erwartet | |
| Repository wird nicht korrekt verdrahtet | |
| Validation wird im Controller nicht ausgelöst | |
| H2 speichert den Datensatz nicht wie erwartet | |

---

## Bewertungsfokus

Die Rückmeldung kann sich an diesen Standardbereichen orientieren:

| Bereich | Beobachtung |
|---|---|
| Funktionalität | Erfolgsfälle und Fehlerfälle werden automatisiert geprüft |
| Verantwortlichkeiten | Tests zeigen den Ablauf über Controller, Validation, Service, Repository und Datenbank |
| Verständlichkeit | Unterschied zwischen Unit-Test und Integrationstest wird nachvollziehbar erklärt |
| Technische Sauberkeit | MockMvc, Statuscode-Prüfungen, JSON-Prüfungen und Testdaten werden kontrolliert eingesetzt |
| Lernzielerreichung | Testbarkeit wird als Qualitätsmerkmal sauberer Architektur begründet |
