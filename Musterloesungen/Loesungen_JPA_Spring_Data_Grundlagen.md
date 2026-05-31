# Musterlösungen – JPA und Spring Data Grundlagen

## Lösung 1: JDBC-Aufwand markieren

```java
String sql = "select id, name, preis from produkte where id = ?";
// SQL

PreparedStatement statement = connection.prepareStatement(sql);
statement.setLong(1, id);
// Parameter setzen

ResultSet resultSet = statement.executeQuery();
// SQL ausführen und Ergebnis erhalten

if (resultSet.next()) {
    Produkt produkt = new Produkt(
            resultSet.getLong("id"),
            resultSet.getString("name"),
            resultSet.getBigDecimal("preis")
    );
    // Ergebnis lesen und Java-Objekt erzeugen
}
```

Der Code funktioniert, weil JDBC direkt mit der Datenbank spricht und das Ergebnis aus dem `ResultSet` gelesen werden kann.

Bei vielen Fachobjekten wird dieser Stil aufwendig, weil SQL, Parameter, Spaltennamen und Objektaufbau immer wieder manuell geschrieben werden müssen.

---

## Lösung 2: Objekt und Tabelle zuordnen

| Java-Feld | Tabellenspalte |
|---|---|
| `id` | `id` |
| `name` | `name` |
| `preis` | `preis` |

Zwischen Objekt und Tabelle braucht es eine Übersetzung, weil Java mit Objekten arbeitet und die Datenbank Zeilen und Spalten speichert.

---

## Lösung 3: Begriffe korrekt einordnen

| Aussage | Begriff |
|---|---|
| Java-Standard für Objekt-Persistenz | JPA |
| konkrete JPA-Implementierung | Hibernate |
| Spring-Integration mit Repository-Abstraktion | Spring Data JPA |
| direkte Datenbankarbeit mit SQL und manuellem Mapping | JDBC |
| kleine Datenbank für Lern- und Testprojekte | H2 |

---

## Lösung 4: Entity-Annotationen erklären

| Element | Erklärung |
|---|---|
| `@Entity` | Die Klasse ist ein persistentes Objekt und kann von JPA gespeichert werden |
| `@Id` | Das Feld ist der eindeutige Primärschlüssel |
| `@GeneratedValue` | Die ID wird automatisch erzeugt |
| leerer Konstruktor | JPA braucht ihn, um Objekte beim Laden erzeugen zu können |

Typischer Fehler: `@Entity` bedeutet nicht, dass die Klasse automatisch ein REST-DTO ist.

---

## Lösung 5: Repository mit `JpaRepository` lesen

```java
public interface ProduktRepository extends JpaRepository<Produkt, Long> {
}
```

- Entity-Typ: `Produkt`
- ID-Typ: `Long`
- einfache Methoden: `findAll()`, `findById(id)`, `save(produkt)`, `deleteById(id)`
- Es bleibt ein Repository, weil es den Datenzugriff kapselt.

Spring Data JPA stellt die technische Implementierung bereit. Die Rolle im Architekturmodell bleibt trotzdem Repository.

---

## Lösung 6: Architekturpfad einzeichnen

```text
HTTP Request
  -> Controller
  -> Service
  -> Repository
  -> JPA / Spring Data
  -> Datenbank
```

| Frage | Antwort |
|---|---|
| Wo wird HTTP verarbeitet? | Controller |
| Wo gehört Fachlogik hin? | Service |
| Wo wird Datenzugriff gekapselt? | Repository |

---

## Lösung 7: Falsche Aussagen korrigieren

| Falsche Aussage | Korrektur |
|---|---|
| JPA ist ein Spring-Feature. | JPA ist ein Java-Standard und kann ohne Spring verwendet werden. |
| Hibernate und JPA sind dasselbe. | Hibernate ist eine Implementierung von JPA. |
| Spring Data JPA ersetzt den Service. | Spring Data JPA vereinfacht Repositorys. Der Service bleibt für Fachlogik zuständig. |
| Mit `JpaRepository` darf der Controller direkt speichern. | Der Controller soll weiterhin über den Service arbeiten. |
| Entities sind automatisch REST-DTOs. | Entities beschreiben persistente Fachobjekte. DTOs beschreiben API-Strukturen. |

---

## Lösung 8: Service bleibt zuständig

Der Service steuert den fachlichen Ablauf. Er entscheidet zum Beispiel, wie ein Produkt erzeugt wird und welche Fachregeln vor dem Speichern gelten.

Das Repository kapselt den Datenzugriff. Mit Spring Data JPA kann es `save(produkt)` verwenden, ohne selbst JDBC-Code zu schreiben.

Der Controller erzeugt das Produkt nicht direkt im Repository, weil er für HTTP zuständig ist. Sonst würden HTTP-Schicht, Fachlogik und Datenzugriff vermischt.

---

## Lösung 9: JDBC und Spring Data JPA vergleichen

| Frage | JDBC | Spring Data JPA |
|---|---|---|
| Wer schreibt das SQL sichtbar im Repository? | Entwicklerin oder Entwickler | für einfache CRUD-Fälle Spring Data JPA/JPA/Hibernate |
| Wo entsteht manuelles Mapping? | im eigenen Repository-Code | wird weitgehend durch JPA/Hibernate übernommen |
| Welche Rolle hat das Repository? | Datenzugriff kapseln | Datenzugriff kapseln |
| Bleibt die Datenbank real vorhanden? | ja | ja |

Wichtig: Spring Data JPA entfernt nicht die Datenbank. Es reduziert den selbst geschriebenen technischen Code.

---

## Lösung 10: Verantwortlichkeiten prüfen

| Aufgabe | Controller, Service oder Repository? |
|---|---|
| HTTP-Statuscode auswählen | Controller |
| Fachregel prüfen, ob ein Preis negativ ist | Service |
| Produkt speichern | Repository |
| `findById(id)` aufrufen | Service ruft Repository auf |
| Request-DTO entgegennehmen | Controller |
| Datenzugriff kapseln | Repository |

---

## Lösung 11: Technikschichten erklären

Spring Data JPA ist die Spring-Integration für Repositorys. Es nutzt JPA als Java-Standard für Objekt-Persistenz. Hibernate ist eine konkrete Implementierung dieses Standards und führt den Datenbankzugriff aus.

Kurz:

```text
Spring Data JPA baut auf JPA auf.
Hibernate implementiert JPA.
```

---

## Lösung 12: JPA ohne Spring

Ja, JPA kann ohne Spring verwendet werden. JPA ist ein Java-Standard und nicht an Spring gebunden.

Spring Data JPA ist eine zusätzliche Spring-Integration. Sie passt gut zu Spring Boot, weil Repositorys als Beans bereitgestellt und per Constructor Injection in Services verwendet werden können.

In dieser Ausbildungsreihe wird Spring Data JPA verwendet, weil die bestehende Anwendung bereits mit Spring Boot, Services und Repositorys arbeitet.

---

## Lösung 13: Architekturentscheidung begründen

Spring Data JPA vereinfacht die Persistenz, weil weniger JDBC-, SQL- und Mapping-Code selbst geschrieben werden muss. Der Controller bleibt aber für HTTP zuständig, der Service für Fachlogik und das Repository für Datenzugriff. JPA und Spring Data JPA verändern also die technische Umsetzung hinter dem Repository, nicht die grundlegende Architektur.

---

## Lösung 14: Nicht-Ziele erkennen

| Thema | Gehört in diese Einheit? |
|---|---|
| `@Entity` | ja |
| `@Id` | ja |
| `JpaRepository` | ja |
| Lazy Loading | nein |
| `FetchType` | nein |
| Cascade | nein |
| komplexe Beziehungen | nein |
| `EntityManager` | nein |
| Persistence Context | nein |
| JPQL | nein |
| Criteria API | nein |
| JPA als Standard | ja |

---

## Bewertungsfokus

| Bereich | Beobachtung |
|---|---|
| Funktionalität | Die Beispiele zu Entity und Repository werden korrekt gelesen |
| Verantwortlichkeiten | Controller, Service und Repository werden nicht vermischt |
| Verständlichkeit | Die Unterschiede zwischen JPA, Hibernate und Spring Data JPA sind erklärbar |
| Technische Sauberkeit | Die Annotationen und `JpaRepository` werden ohne unnötige Zusatzthemen eingeordnet |
| Lernzielerreichung | Der Nutzen von Persistenz-Abstraktion wird aus dem JDBC-Aufwand abgeleitet |
