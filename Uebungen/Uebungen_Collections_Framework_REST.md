# Übungen – Collections Framework in REST- und DTO-Anwendungen

## Ziel

Du erweiterst dein Verständnis der REST-Lagerverwaltung.

In der DTO-Einheit hast du bereits Listen von DTOs verwendet. Jetzt klärst du bewusst, warum diese Collections gebraucht werden und wann `List`, `Set` oder `Map` passen.

---

## Vorwissen

Du solltest bereits können:

- REST-Endpunkte mit `GET` einordnen
- DTOs von Fachobjekten unterscheiden
- einfache DTOs manuell mappen
- mit `curl` und Bruno eine JSON-Antwort prüfen
- eine einfache `for`- oder `for-each`-Schleife lesen

Streams werden in dieser Übung nicht verwendet.

---

## Ausgangslage

Die bekannte Architektur bleibt:

```text
Client
-> REST Controller
-> LagerService
-> ProduktRepository
-> H2
```

Beim GET-Endpunkt entstehen mehrere DTOs:

```text
List<Produkt>
-> Mapping mit Schleife
-> List<ProduktResponseDto>
-> JSON-Liste
```

---

## Basis

### Aufgabe 1: Produktliste erkennen

Rufe die Produktliste mit `curl` oder Bruno auf:

```bash
curl -i http://localhost:8080/produkte
```

Notiere:

- Kommt ein einzelnes JSON-Objekt zurück oder eine JSON-Liste?
- Welche Felder enthält ein Produkt-DTO?
- In welcher Reihenfolge erscheinen die Produkte?

Erwartetes Resultat:

```text
Du kannst erklären, warum der GET-Endpunkt mehrere DTOs zurückgibt.
```

---

### Aufgabe 2: DTOs in einer List speichern

Erstelle oder prüfe im Controller eine Liste für Response-DTOs:

```java
List<ProduktResponseDto> response = new ArrayList<>();
```

Ergänze die nötigen Imports, falls sie fehlen:

```java
import java.util.ArrayList;
import java.util.List;
```

Erkläre kurz:

- Warum steht links `List`?
- Warum steht rechts `ArrayList`?
- Warum passt eine Liste zur REST-Ausgabe?

---

### Aufgabe 3: Produkte iterieren

Baue die DTO-Liste mit einer klassischen Schleife auf:

```java
for (Produkt produkt : produkte) {
    ProduktResponseDto dto = toResponseDto(produkt);
    response.add(dto);
}
```

Auftrag:

- Markiere, wo das Fachobjekt verwendet wird.
- Markiere, wo das DTO entsteht.
- Markiere, wo das DTO in die Liste gelegt wird.

Keine Streams verwenden.

---

### Aufgabe 4: REST-Ausgabe mit mehreren DTOs testen

Starte die Anwendung und rufe die Produktliste erneut auf:

```bash
curl -i http://localhost:8080/produkte
```

Führe denselben Request in Bruno aus.

Prüfe:

- Die Antwort ist eine JSON-Liste.
- Jedes Element entspricht dem Response-DTO.
- Interne Felder werden nicht unnötig veröffentlicht.

---

### Aufgabe 5: Set für eindeutige Kategorien verwenden

Für diese Aufgabe brauchst du kein zusätzliches Feld im Produktmodell.

Arbeite mit einer kleinen Liste von Beispielkategorien:

```java
List<String> produktKategorien = new ArrayList<>();
produktKategorien.add("Hardware");
produktKategorien.add("Zubehör");
produktKategorien.add("Hardware");

Set<String> kategorien = new HashSet<>();

for (String kategorie : produktKategorien) {
    kategorien.add(kategorie);
}
```

Ergänze die nötigen Imports:

```java
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
```

Beantworte:

- Was passiert, wenn dieselbe Kategorie zweimal vorkommt?
- Warum ist ein `Set` hier sinnvoll?
- Warum sollst du keine feste Reihenfolge erwarten?

---

### Aufgabe 6: Map für Produktsuche verwenden

Baue eine einfache Zuordnung von ID zu DTO:

```java
Map<Long, ProduktResponseDto> produkteNachId = new HashMap<>();

for (ProduktResponseDto dto : response) {
    produkteNachId.put(dto.getId(), dto);
}
```

Ergänze die nötigen Imports:

```java
import java.util.HashMap;
import java.util.Map;
```

Teste im Code gedanklich:

```java
ProduktResponseDto dto = produkteNachId.get(1L);
```

Beantworte:

- Was ist der Schlüssel?
- Was ist der Wert?
- Was passiert, wenn dieselbe ID zweimal eingefügt wird?

---

## Vertiefung

### Aufgabe 7: DTO-Liste erweitern

Ergänze im Response-DTO ein einfaches Feld, wenn es fachlich passt, zum Beispiel:

```java
private String kategorie;
```

Passe das Mapping mit der Schleife an.

Prüfe danach mit Bruno oder `curl`, ob die JSON-Liste weiterhin verständlich bleibt.

Wichtig:

```text
Nur Felder ergänzen, die zur öffentlichen API passen.
```

---

### Aufgabe 8: Doppelte Werte analysieren

Vergleiche:

```java
List<String> kategorienListe = new ArrayList<>();
Set<String> kategorienSet = new HashSet<>();
```

Füge gedanklich diese Werte ein:

```text
Hardware
Zubehör
Hardware
```

Notiere:

- Was enthält die Liste?
- Was enthält das Set?
- Welche Struktur passt für eine REST-Ausgabe?
- Welche Struktur passt für eindeutige interne Werte?

---

### Aufgabe 9: Map-Zugriffe ergänzen

Erweitere die `Map`-Übung:

```java
if (produkteNachId.containsKey(1L)) {
    ProduktResponseDto dto = produkteNachId.get(1L);
    System.out.println(dto.getName());
}
```

Beantworte:

- Warum wird zuerst `containsKey` geprüft?
- Warum ist die ID ein sinnvoller Schlüssel?
- Wann wäre der Produktname ein schlechter Schlüssel?

---

### Aufgabe 10: Collection-Typen vergleichen

Fülle die Tabelle aus:

| Aufgabe | List | Set | Map | Begründung |
|---|---|---|---|---|
| Produktliste als JSON zurückgeben | | | | |
| eindeutige Kategorien sammeln | | | | |
| Produkt über ID finden | | | | |
| doppelte Produktnamen anzeigen | | | | |

---

### Aufgabe 11: Typische REST-Listen analysieren

Suche in deiner API oder in den bisherigen Beispielen nach REST-Ausgaben, die Listen sind.

Beantworte:

- Welche URL liefert mehrere Objekte?
- Welche DTO-Klasse beschreibt ein Element?
- Warum ist eine Liste für Clients verständlich?
- Welche Reihenfolge wäre für Clients sinnvoll?

---

### Aufgabe 12: Logging mit Collections beobachten

Falls Logging vorhanden ist, beobachte beim GET-Aufruf:

- Wie viele Produkte werden geladen?
- Wie viele DTOs werden erzeugt?
- Wird eine Liste, ein Set oder eine Map sichtbar?

Schreibe kurz auf:

```text
Welche Collection gehört zu welcher Aufgabe?
```

---

## Transfer

### Aufgabe 13: Warum Collections nötig sind

Erkläre in eigenen Worten:

```text
Warum braucht eine REST-/DTO-Anwendung Collections?
```

Verwende mindestens diese Begriffe:

- mehrere Objekte
- DTO-Liste
- REST-Ausgabe
- Schleife

---

### Aufgabe 14: List, Set und Map unterscheiden

Erkläre je in einem Satz:

- `List`
- `Set`
- `Map`

Gib zu jedem Typ ein Beispiel aus der Lagerverwaltung.

---

### Aufgabe 15: REST gibt häufig Listen zurück

Begründe:

```text
Warum liefert GET /produkte typischerweise eine Liste?
```

Denke an Clients, JSON und DTOs.

---

### Aufgabe 16: Sets verhindern doppelte Werte

Erkläre:

- Warum verhindert ein `Set` doppelte Werte?
- Warum ist das für Kategorien sinnvoll?
- Warum ist ein `Set` nicht automatisch die beste REST-Ausgabe?

---

### Aufgabe 17: Maps für Zuordnungen

Erkläre:

- Warum ist eine `Map` für ID-zu-Produkt sinnvoll?
- Warum muss der Schlüssel eindeutig sein?
- Warum kann eine DTO-Liste für die öffentliche API trotzdem verständlicher sein?

---

## Abgabe

Gib ab:

- kurze Erklärung zu `List`, `Set` und `Map`
- Codeausschnitt für eine DTO-Liste mit `ArrayList`
- Codeausschnitt für eindeutige Kategorien mit `HashSet`
- Codeausschnitt für ID-zu-DTO mit `HashMap`
- Bruno- oder `curl`-Nachweis einer JSON-Liste
- kurze Reflexion zur passenden Collection-Auswahl

---

## Checkliste

- DTO-Liste wird mit `List` und `ArrayList` aufgebaut.
- Produkte werden mit einer klassischen Schleife iteriert.
- Es werden keine Streams verwendet.
- `Set` wird für eindeutige Werte eingesetzt.
- `Map` wird für Schlüssel/Wert-Zuordnungen eingesetzt.
- REST-Ausgabe bleibt als JSON-Struktur verständlich.
- Controller wird nicht mit unnötiger Collection-Logik überladen.

---

# Bewertungsfokus

Die Bewertung orientiert sich an den zentralen [Bewertungskriterien](../docs/didaktik/bewertungskriterien.md).

Es gibt keine Punkte und keine Notenskala. Die Rückmeldung soll sichtbar machen, ob die Collection-Auswahl zur REST-/DTO-Aufgabe passt.

| Bereich | Einschätzung | Beobachtung |
|---|---|---|
| Funktionalität | | DTO-Listen werden aufgebaut und REST-Ausgaben mit mehreren Objekten können geprüft werden. |
| Verantwortlichkeiten | | Controller, Service und Repository behalten ihre Aufgaben; Collection-Logik wird nicht unnötig vermischt. |
| Verständlichkeit | | `List`, `Set` und `Map` werden mit einfachen Beispielen aus der Lagerverwaltung erklärt. |
| Technische Sauberkeit | | `ArrayList`, `HashSet` und `HashMap` werden passend und ohne Streams eingesetzt. |
| Lernzielerreichung | | Die Lösung zeigt, dass Collections mehrere Objekte in REST- und DTO-Anwendungen strukturieren. |

## Rückmeldung

- Stärken:
- Nächste Verbesserung:
- Offene Fragen:
