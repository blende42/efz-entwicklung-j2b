# Musterlösungen – Integrationstests

Die Codebeispiele beziehen sich auf eine REST-Lagerverwaltung mit Spring Data Repository und H2-Testdatenbank.

Die Lösungen verwenden MockMvc, aber kein Mockito. Die Spring-Komponenten werden nicht durch Mocks ersetzt, weil gerade ihr Zusammenspiel geprüft werden soll.

Eine typische Vorbereitung für kontrollierte Testdaten sieht so aus:

```java
@Autowired
private ProduktRepository produktRepository;

@BeforeEach
void datenVorbereiten() {
    produktRepository.deleteAll();
}
```

## Lösung 1: Unit-Test oder Integrationstest?

| Aussage | Unit-Test oder Integrationstest? |
|---|---|
| Prüft nur eine Service-Methode mit direkt erzeugtem Repository | Unit-Test |
| Sendet einen `POST /produkte` Request mit JSON | Integrationstest |
| Prüft Controller, Service, Repository und H2 gemeinsam | Integrationstest |
| Prüft eine einzelne Berechnung ohne Spring-Kontext | Unit-Test |
| Prüft, ob ein ungültiger Request `400 Bad Request` ergibt | Integrationstest |

---

## Lösung 2: Testpfad markieren

```text
MockMvc
  -> HTTP Request
  -> Controller
  -> Validation
  -> Service
  -> Repository
  -> H2
```

| Frage | Antwort |
|---|---|
| Welche Schicht verarbeitet HTTP? | Controller |
| Wo wird Validation sichtbar? | am Request-DTO im Controller-Ablauf |
| Wo wird gespeichert oder geladen? | Repository und H2 |

---

## Lösung 3: Bestehenden REST-Endpunkt testen

```java
@Test
void gibtProduktlisteZurueck() throws Exception {
    mockMvc.perform(get("/produkte"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray());
}
```

Das ist ein Integrationstest, weil der Request über Spring MVC in den Controller läuft und danach Service, Repository und Datenhaltung beteiligt sein können.

---

## Lösung 4: Gültigen POST testen

```java
@Test
void erstelltProduktMitGueltigemRequest() throws Exception {
    String json = """
            {
              "name": "Tastatur",
              "preis": 49.90,
              "status": "AKTIV"
            }
            """;

    mockMvc.perform(post("/produkte")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.name").value("Tastatur"))
            .andExpect(jsonPath("$.status").value("AKTIV"));
}
```

Typischer Fehler: Nur `201 Created` zu prüfen reicht oft nicht. Die JSON-Antwort sollte mindestens zentrale Felder enthalten.

---

## Lösung 5: Ungültigen POST testen

```java
@Test
void lehntProduktOhneNameAb() throws Exception {
    String json = """
            {
              "name": "",
              "preis": 49.90,
              "status": "AKTIV"
            }
            """;

    mockMvc.perform(post("/produkte")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.message").exists());
}
```

Falls die Anwendung noch keine strukturierte Fehlerantwort liefert, wird nur `status().isBadRequest()` geprüft. Dann ist die fehlende Fehlerstruktur eine sinnvolle nächste Verbesserung.

---

## Lösung 6: GET mit gültiger ID testen

```java
@Test
void findetProduktMitGueltigerId() throws Exception {
    Produkt gespeichertesProdukt = produktRepository.save(
            new Produkt("Tastatur", 49.90, ProduktStatus.AKTIV)
    );

    mockMvc.perform(get("/produkte/{id}", gespeichertesProdukt.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(gespeichertesProdukt.getId()))
            .andExpect(jsonPath("$.name").value("Tastatur"));
}
```

Der Test braucht kontrollierte Testdaten, damit er nicht von zufälligen lokalen Daten abhängt.

---

## Lösung 7: GET mit unbekannter ID testen

```java
@Test
void gibtNotFoundBeiUnbekannterIdZurueck() throws Exception {
    mockMvc.perform(get("/produkte/{id}", 999999L))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.message").exists());
}
```

Wenn die Fehlerantwort anders strukturiert ist, wird der JSON-Pfad an die echte Anwendung angepasst.

---

## Lösung 8: Validation im Test prüfen

```java
@Test
void lehntNegativenPreisAb() throws Exception {
    String json = """
            {
              "name": "Tastatur",
              "preis": -1.00,
              "status": "AKTIV"
            }
            """;

    mockMvc.perform(post("/produkte")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.message").exists());
}
```

Der Test prüft mehr als eine Service-Methode, weil JSON gelesen, Validation ausgelöst und eine HTTP-Fehlerantwort erzeugt wird.

---

## Lösung 9: Fehlerantworten prüfen

```java
mockMvc.perform(get("/produkte/{id}", 999999L))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message").value("Produkt nicht gefunden"));
```

Wenn die konkrete Meldung variieren darf, ist diese Variante robuster:

```java
mockMvc.perform(get("/produkte/{id}", 999999L))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message").exists());
```

---

## Lösung 10: Datenbankzustand prüfen

```java
@Test
void speichertProduktInDerDatenbank() throws Exception {
    String json = """
            {
              "name": "Tastatur",
              "preis": 49.90,
              "status": "AKTIV"
            }
            """;

    mockMvc.perform(post("/produkte")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
            .andExpect(status().isCreated());

    assertThat(produktRepository.findAll())
            .anyMatch(produkt -> produkt.getName().equals("Tastatur"));
}
```

Diese Prüfung ist sinnvoll, weil sie zeigt, dass der Request nicht nur eine Antwort erzeugt, sondern auch die Persistenz verändert hat.

---

## Lösung 11: Unit-Test und Integrationstest erklären

| Frage | Unit-Test | Integrationstest |
|---|---|---|
| Wie viele Schichten werden typischerweise geprüft? | eine Klasse oder kleine Einheit | mehrere Schichten |
| Wird Spring gestartet? | meistens nein | ja, bei `@SpringBootTest` |
| Wird ein HTTP-Request simuliert? | nein | ja, mit MockMvc |
| Wird die Datenbank mitgeprüft? | meistens nein | ja, wenn Repository/H2 beteiligt sind |
| Was ist der Hauptnutzen? | einzelne Logik schnell prüfen | Zusammenspiel prüfen |

---

## Lösung 12: Warum Integrationstests wichtig sind

Integrationstests sind wichtig, weil sie prüfen, ob mehrere Schichten zusammen funktionieren. Ein Unit-Test kann zeigen, dass eine Service-Methode korrekt arbeitet, aber er findet keine falsche URL im Controller. Ein Integrationstest kann zusätzlich prüfen, ob Validation ausgelöst wird, ob der passende Statuscode zurückkommt und ob die JSON-Antwort stimmt. Wenn Repository und Datenbank beteiligt sind, wird auch sichtbar, ob Daten wirklich gespeichert oder geladen werden.

---

## Lösung 13: Gemeinsam getestete Schichten erklären

| Station | Erklärung |
|---|---|
| MockMvc | simuliert den HTTP-Request im Test |
| Controller | nimmt den Request entgegen und gibt eine HTTP-Antwort zurück |
| Validation | prüft ungültige Request-Daten |
| Service | führt Fachlogik oder Ablaufsteuerung aus |
| Repository | kapselt den Datenzugriff |
| H2 | speichert Testdaten in einer kontrollierten Testdatenbank |

---

## Lösung 14: Fehler erkennen, die Unit-Tests nicht finden

| Fehler | Eher Integrationstest? |
|---|---|
| falsche URL im Controller | ja |
| falscher HTTP-Statuscode | ja |
| Service-Methode rechnet intern falsch | eher Unit-Test oder beide |
| JSON-Feld heisst anders als erwartet | ja |
| Repository wird nicht korrekt verdrahtet | ja |
| Validation wird im Controller nicht ausgelöst | ja |
| H2 speichert den Datensatz nicht wie erwartet | ja |

---

## Bewertungsfokus

| Bereich | Beobachtung |
|---|---|
| Funktionalität | Tests prüfen erfolgreiche und fehlerhafte REST-Abläufe |
| Verantwortlichkeiten | Die getesteten Schichten werden korrekt benannt und nicht vermischt |
| Verständlichkeit | Unit-Test und Integrationstest werden klar unterschieden |
| Technische Sauberkeit | MockMvc, JSON-Prüfungen und Testdaten werden nachvollziehbar eingesetzt |
| Lernzielerreichung | Die Lernenden begründen Integrationstests als Qualitätsmerkmal der Architektur |
