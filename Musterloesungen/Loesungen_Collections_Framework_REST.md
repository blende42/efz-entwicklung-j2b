# Musterlösungen – Collections Framework in REST- und DTO-Anwendungen

## Hinweis

Diese Musterlösung zeigt eine mögliche Standardlösung.

Paketnamen, Klassennamen und Methodennamen können in deinem Projekt abweichen. Wichtig ist die Idee:

```text
Collections werden passend zur Aufgabe gewählt.
DTO-Listen werden mit einer klassischen Schleife aufgebaut.
Streams werden noch nicht verwendet.
```

---

## Basis

### Lösung 1: Produktliste erkennen

Eine typische Antwort von `GET /produkte` ist eine JSON-Liste:

```json
[
  {
    "id": 1,
    "name": "Tastatur",
    "preis": 49.9
  },
  {
    "id": 2,
    "name": "Maus",
    "preis": 24.9
  }
]
```

Erklärung:

```text
Der Endpoint liefert mehrere Produkte.
Darum braucht der Java-Code eine Collection.
```

Passende Java-Struktur:

```java
List<ProduktResponseDto>
```

---

### Lösung 2: DTOs in einer List speichern

```java
import java.util.ArrayList;
import java.util.List;

List<ProduktResponseDto> response = new ArrayList<>();
```

Erklärung:

- `List` beschreibt die allgemeine Aufgabe: geordnete Sammlung.
- `ArrayList` ist eine konkrete Umsetzung.
- Eine Liste passt gut zur REST-Ausgabe, weil mehrere DTOs als JSON-Liste zurückgegeben werden.

---

### Lösung 3: Produkte iterieren

```java
for (Produkt produkt : produkte) {
    ProduktResponseDto dto = toResponseDto(produkt);
    response.add(dto);
}
```

Zuordnung:

| Code | Bedeutung |
|---|---|
| `Produkt produkt` | internes Fachobjekt |
| `toResponseDto(produkt)` | Mapping |
| `ProduktResponseDto dto` | API-DTO |
| `response.add(dto)` | DTO wird in die Liste gelegt |

---

### Lösung 4: REST-Ausgabe mit mehreren DTOs testen

Prüfung:

```bash
curl -i http://localhost:8080/produkte
```

Erwartung:

- Statuscode ist erfolgreich.
- Body ist eine JSON-Liste.
- Jedes Element enthält nur die Felder des Response-DTOs.
- Interne Felder erscheinen nicht unnötig.

---

### Lösung 5: Set für eindeutige Kategorien

```java
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

List<String> produktKategorien = new ArrayList<>();
produktKategorien.add("Hardware");
produktKategorien.add("Zubehör");
produktKategorien.add("Hardware");

Set<String> kategorien = new HashSet<>();

for (String kategorie : produktKategorien) {
    kategorien.add(kategorie);
}
```

Wenn dieselbe Kategorie zweimal vorkommt, enthält das `Set` diese Kategorie nur einmal.

Beispiel:

```text
Hardware
Zubehör
Hardware
```

Ergebnis im `Set`:

```text
Hardware
Zubehör
```

Hinweis:

```text
Bei HashSet soll keine feste sichtbare Reihenfolge erwartet werden.
```

---

### Lösung 6: Map für Produktsuche

```java
import java.util.HashMap;
import java.util.Map;

Map<Long, ProduktResponseDto> produkteNachId = new HashMap<>();

for (ProduktResponseDto dto : response) {
    produkteNachId.put(dto.getId(), dto);
}
```

Zugriff:

```java
ProduktResponseDto dto = produkteNachId.get(1L);
```

Erklärung:

| Begriff | Beispiel |
|---|---|
| Schlüssel | `1L` |
| Wert | `ProduktResponseDto` |
| Zuordnung | `1L -> TastaturDto` |

Wenn dieselbe ID zweimal eingefügt wird, ersetzt der neue Wert den alten Wert für diesen Schlüssel.

---

## Vertiefung

### Lösung 7: DTO-Liste erweitern

Diese Lösung passt nur, wenn dein Produktmodell fachlich bereits eine Kategorie hat oder du sie bewusst ergänzt hast.

Falls dein aktuelles Modell keine Kategorie kennt, ist eine korrekte Lösung auch:

```text
Ich ergänze kein DTO-Feld, weil die bestehende Fachstruktur diese Information noch nicht enthält.
```

Beispiel für ein erweitertes DTO:

```java
public class ProduktResponseDto {

    private Long id;
    private String name;
    private double preis;
    private String kategorie;

    public ProduktResponseDto(Long id, String name, double preis, String kategorie) {
        this.id = id;
        this.name = name;
        this.preis = preis;
        this.kategorie = kategorie;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPreis() {
        return preis;
    }

    public String getKategorie() {
        return kategorie;
    }
}
```

Mapping:

```java
private ProduktResponseDto toResponseDto(Produkt produkt) {
    return new ProduktResponseDto(
            produkt.getId(),
            produkt.getName(),
            produkt.getPreis(),
            produkt.getKategorie()
    );
}
```

Hinweis:

```text
Das Feld gehört nur ins DTO, wenn es Teil der öffentlichen API sein soll.
```

---

### Lösung 8: Doppelte Werte analysieren

Eingaben:

```text
Hardware
Zubehör
Hardware
```

`List`:

```text
Hardware
Zubehör
Hardware
```

`Set`:

```text
Hardware
Zubehör
```

Bewertung:

- `List` passt, wenn alle Einträge und ihre Reihenfolge wichtig sind.
- `Set` passt, wenn jeder Wert nur einmal vorkommen soll.
- Für eine öffentliche REST-Ausgabe ist eine `List` oft verständlicher.
- Für interne eindeutige Werte ist ein `Set` oft passend.

---

### Lösung 9: Map-Zugriffe ergänzen

```java
if (produkteNachId.containsKey(1L)) {
    ProduktResponseDto dto = produkteNachId.get(1L);
    System.out.println(dto.getName());
}
```

Erklärung:

- `containsKey` prüft, ob der Schlüssel vorhanden ist.
- Die ID ist ein sinnvoller Schlüssel, weil sie ein Produkt eindeutig identifiziert.
- Der Produktname kann ein schlechter Schlüssel sein, weil mehrere Produkte gleich heissen können.

---

### Lösung 10: Collection-Typen vergleichen

| Aufgabe | List | Set | Map | Begründung |
|---|---|---|---|---|
| Produktliste als JSON zurückgeben | ja | teilweise | nein | Reihenfolge und mehrere DTOs sind wichtig |
| eindeutige Kategorien sammeln | teilweise | ja | nein | doppelte Kategorien sollen wegfallen |
| Produkt über ID finden | nein | nein | ja | ID ist ein Schlüssel |
| doppelte Produktnamen anzeigen | ja | nein | nein | doppelte Werte sollen sichtbar bleiben |

---

### Lösung 11: Typische REST-Listen analysieren

Beispiel:

```text
GET /produkte
```

Antwort:

```text
List<ProduktResponseDto>
```

Ein Element wird durch `ProduktResponseDto` beschrieben.

Eine Liste ist für Clients verständlich, weil mehrere Produkte als mehrere JSON-Objekte in einer JSON-Liste erscheinen.

Eine sinnvolle Reihenfolge kann zum Beispiel sein:

- nach ID
- nach Name
- nach Erfassungsreihenfolge

Sortierung wird in dieser Einheit noch nicht vertieft.

---

### Lösung 12: Logging mit Collections beobachten

Mögliche Beobachtung:

```text
Repository lädt mehrere Produkt-Fachobjekte.
Service gibt eine List<Produkt> zurück.
Controller erzeugt eine List<ProduktResponseDto>.
Spring Boot serialisiert die DTO-Liste als JSON-Liste.
```

Ein `Set` kann intern auftauchen, wenn eindeutige Kategorien gesammelt werden.

Eine `Map` kann intern auftauchen, wenn Produkte über eine ID nachgeschlagen werden.

---

## Transfer

### Lösung 13: Warum Collections nötig sind

REST-/DTO-Anwendungen brauchen Collections, weil sie häufig mit mehreren Objekten arbeiten. Ein GET-Endpunkt wie `/produkte` liefert mehrere Produkte. Im Java-Code wird daraus eine DTO-Liste. Mit einer Schleife wird jedes Fachobjekt in ein DTO übertragen.

---

### Lösung 14: List, Set und Map unterscheiden

`List`: Eine geordnete Sammlung, die doppelte Einträge enthalten kann.

Beispiel:

```text
List<ProduktResponseDto> für GET /produkte
```

`Set`: Eine Sammlung ohne doppelte Werte.

Beispiel:

```text
Set<String> für eindeutige Kategorien
```

`Map`: Eine Zuordnung von Schlüssel zu Wert.

Beispiel:

```text
Map<Long, ProduktResponseDto> für ID -> ProduktResponseDto
```

---

### Lösung 15: REST gibt häufig Listen zurück

`GET /produkte` liefert typischerweise eine Liste, weil Clients mehrere Produkte anzeigen oder weiterverarbeiten wollen. Jedes Produkt wird als DTO dargestellt. Die gesamte Antwort ist eine JSON-Liste.

---

### Lösung 16: Sets verhindern doppelte Werte

Ein `Set` speichert denselben Wert nur einmal. Das ist für Kategorien sinnvoll, wenn man wissen will, welche Kategorien es gibt, aber nicht wie oft sie vorkommen.

Ein `Set` ist nicht automatisch die beste REST-Ausgabe, weil eine REST-Ausgabe oft eine stabile und verständliche Reihenfolge braucht.

---

### Lösung 17: Maps für Zuordnungen

Eine `Map` ist für ID-zu-Produkt sinnvoll, weil die ID ein eindeutiger Schlüssel ist. Über diesen Schlüssel kann der passende Wert gefunden werden.

Der Schlüssel muss eindeutig sein, weil pro Schlüssel nur ein Wert gespeichert wird.

Eine DTO-Liste kann für die öffentliche API trotzdem verständlicher sein, weil Clients mehrere Objekte direkt als JSON-Liste sehen.

---

## Typische Fehlerhinweise

- Wenn eine feste Reihenfolge wichtig ist, ist `HashSet` meist nicht passend.
- Wenn doppelte Werte sichtbar bleiben müssen, ist `Set` falsch.
- Wenn ein Schlüssel nicht eindeutig ist, ist eine `Map` riskant.
- Wenn Collection-Logik den Controller stark vergrössert, gehört sie eher in Service oder Mapping.
- Wenn Streams verwendet werden, wird ein noch nicht behandeltes Konzept vorweggenommen.
