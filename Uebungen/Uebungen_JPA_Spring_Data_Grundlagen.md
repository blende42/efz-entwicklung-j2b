# Übungen – JPA und Spring Data Grundlagen

## Ziel

Du erklärst, warum die REST-Lagerverwaltung von manuellem JDBC-Code zu JPA und Spring Data JPA wechseln kann, ohne die Architektur zu verlieren.

Die Architektur bleibt:

```text
Controller -> Service -> Repository
```

---

## Vorwissen

Du solltest bereits:

- Controller, Service und Repository unterscheiden
- Constructor Injection lesen können
- einfache REST-Endpunkte verstehen
- JDBC/H2 grob einordnen können
- `Optional<T>` im Repository und Service kennen

---

## Startpunkt

In dieser Einheit geht es um die Persistenzschicht. Du sollst noch keine komplexen Beziehungen, kein Lazy Loading, keinen Persistence Context und keine Transaktionen im Detail bearbeiten.

Verwendete Begriffe:

- Spring Boot
- JPA
- Hibernate
- Spring Data JPA
- H2
- `@Entity`
- `@Id`
- `@GeneratedValue`
- `JpaRepository`

---

## Basis

### Aufgabe 1: JDBC-Aufwand markieren

Lies den Codeausschnitt.

```java
String sql = "select id, name, preis from produkte where id = ?";

PreparedStatement statement = connection.prepareStatement(sql);
statement.setLong(1, id);

ResultSet resultSet = statement.executeQuery();

if (resultSet.next()) {
    Produkt produkt = new Produkt(
            resultSet.getLong("id"),
            resultSet.getString("name"),
            resultSet.getBigDecimal("preis")
    );
}
```

Markiere im Code:

- SQL
- Parameter setzen
- Ergebnis lesen
- Java-Objekt erzeugen
- technischen Boilerplate-Code

Schreibe danach zwei Sätze:

- Warum funktioniert dieser Code grundsätzlich?
- Warum wird dieser Stil bei vielen Objekten aufwendig?

---

### Aufgabe 2: Objekt und Tabelle zuordnen

Ordne die Java-Felder den Tabellenspalten zu.

```java
public class Produkt {
    private Long id;
    private String name;
    private BigDecimal preis;
}
```

```text
produkte
-----------------------
id | name | preis
```

| Java-Feld | Tabellenspalte |
|---|---|
| `id` | |
| `name` | |
| `preis` | |

Erkläre kurz, warum zwischen Objekt und Tabelle eine Übersetzung nötig ist.

---

### Aufgabe 3: Begriffe korrekt einordnen

Ordne jede Aussage einem Begriff zu: JDBC, JPA, Hibernate, Spring Data JPA oder H2.

| Aussage | Begriff |
|---|---|
| Java-Standard für Objekt-Persistenz | |
| konkrete JPA-Implementierung | |
| Spring-Integration mit Repository-Abstraktion | |
| direkte Datenbankarbeit mit SQL und manuellem Mapping | |
| kleine Datenbank für Lern- und Testprojekte | |

---

### Aufgabe 4: Entity-Annotationen erklären

Lies das Beispiel.

```java
@Entity
public class Produkt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigDecimal preis;

    protected Produkt() {
    }
}
```

Erkläre:

- Was bedeutet `@Entity`?
- Was bedeutet `@Id`?
- Was bedeutet `@GeneratedValue`?
- Warum braucht JPA einen leeren Konstruktor?

---

## Aufbau

### Aufgabe 5: Repository mit `JpaRepository` lesen

Lies das Repository.

```java
public interface ProduktRepository extends JpaRepository<Produkt, Long> {
}
```

Beantworte:

- Welcher Entity-Typ wird gespeichert?
- Welchen ID-Typ verwendet das Entity?
- Welche einfachen Methoden stellt `JpaRepository` bereit?
- Warum ist das Repository trotzdem weiterhin ein Repository?

---

### Aufgabe 6: Architekturpfad einzeichnen

Zeichne den Ablauf für einen neuen Produkt-Request.

```text
HTTP Request
  -> __________
  -> __________
  -> __________
  -> JPA / Spring Data
  -> Datenbank
```

Trage Controller, Service und Repository passend ein.

Markiere danach:

- Wo wird HTTP verarbeitet?
- Wo gehört Fachlogik hin?
- Wo wird Datenzugriff gekapselt?

---

### Aufgabe 7: Falsche Aussagen korrigieren

Korrigiere die Aussagen fachlich präzise.

| Falsche Aussage | Korrektur |
|---|---|
| JPA ist ein Spring-Feature. | |
| Hibernate und JPA sind dasselbe. | |
| Spring Data JPA ersetzt den Service. | |
| Mit `JpaRepository` darf der Controller direkt speichern. | |
| Entities sind automatisch REST-DTOs. | |

---

### Aufgabe 8: Service bleibt zuständig

Lies den Service-Ausschnitt.

```java
@Service
public class ProduktService {

    private final ProduktRepository produktRepository;

    public ProduktService(ProduktRepository produktRepository) {
        this.produktRepository = produktRepository;
    }

    public Produkt erfassen(String name, BigDecimal preis) {
        Produkt produkt = new Produkt(name, preis);
        return produktRepository.save(produkt);
    }
}
```

Erkläre:

- Welche Aufgabe hat der Service?
- Welche Aufgabe hat das Repository?
- Warum erzeugt der Controller das Produkt nicht direkt im Repository?

---

## Vertiefung

### Aufgabe 9: JDBC und Spring Data JPA vergleichen

Fülle die Tabelle aus.

| Frage | JDBC | Spring Data JPA |
|---|---|---|
| Wer schreibt das SQL sichtbar im Repository? | | |
| Wo entsteht manuelles Mapping? | | |
| Welche Rolle hat das Repository? | | |
| Bleibt die Datenbank real vorhanden? | | |

---

### Aufgabe 10: Verantwortlichkeiten prüfen

Ordne die Aufgaben zu.

| Aufgabe | Controller, Service oder Repository? |
|---|---|
| HTTP-Statuscode auswählen | |
| Fachregel prüfen, ob ein Preis negativ ist | |
| Produkt speichern | |
| `findById(id)` aufrufen | |
| Request-DTO entgegennehmen | |
| Datenzugriff kapseln | |

---

### Aufgabe 11: Technikschichten erklären

Erkläre den Ablauf in zwei bis vier Sätzen.

```text
Produkt -> Spring Data Repository -> JPA -> Hibernate -> Datenbank
```

Verwende dabei die Begriffe Standard, Implementierung und Spring-Integration.

---

## Transfer

### Aufgabe 12: JPA ohne Spring

Beantworte:

- Kann JPA ohne Spring verwendet werden?
- Was ist dann der Unterschied zu Spring Data JPA?
- Warum lernen wir trotzdem Spring Data JPA in einer Spring-Boot-Anwendung?

---

### Aufgabe 13: Architekturentscheidung begründen

Schreibe eine kurze Begründung für diese Aussage:

```text
Spring Data JPA vereinfacht die Persistenz, aber es ersetzt nicht die Architektur.
```

Nenne mindestens:

- Controller
- Service
- Repository
- JPA oder Spring Data JPA

---

### Aufgabe 14: Nicht-Ziele erkennen

Welche Themen gehören noch nicht in diese Einheit?

Kreuze an.

| Thema | Gehört in diese Einheit? |
|---|---|
| `@Entity` | |
| `@Id` | |
| `JpaRepository` | |
| Lazy Loading | |
| `FetchType` | |
| Cascade | |
| komplexe Beziehungen | |
| `EntityManager` | |
| Persistence Context | |
| JPQL | |
| Criteria API | |
| JPA als Standard | |

---

## Bewertungsfokus

Die Rückmeldung kann sich an diesen Standardbereichen orientieren:

| Bereich | Beobachtung |
|---|---|
| Funktionalität | Die Grundidee von Entity und Repository kann an einfachen Beispielen nachvollzogen werden |
| Verantwortlichkeiten | Controller, Service und Repository bleiben trotz Spring Data JPA sauber getrennt |
| Verständlichkeit | JPA, Hibernate und Spring Data JPA werden mit eigenen Rollen erklärt |
| Technische Sauberkeit | `@Entity`, `@Id`, `@GeneratedValue` und `JpaRepository` werden korrekt und ohne Zusatzkomplexität eingeordnet |
| Lernzielerreichung | Der Wechsel von JDBC-Aufwand zu Persistenz-Abstraktion wird problemgetrieben begründet |
