# Prozess – Repetition und Vertiefung erstellen

Diese Checkliste ergänzt [AGENTS.md](../../AGENTS.md) und [Prozess – Übungen erstellen](./uebungen_erstellen.md). Die Repo-Regeln bleiben verbindlich.

Repetitions- und Vertiefungsübungen prüfen, ob Lernende bisherige Konzepte wirklich verinnerlicht haben. Sie führen keine neuen Konzepte ein, sondern kombinieren bekannte Bausteine in kleinen, klar lösbaren Aufgaben.

Repetitionsserien werden standardmässig getrennt von normalen Übungen unter `Repetitionen` abgelegt und immer in zwei Versionen erstellt:

```text
Repetitionen/
  <Name_der_Repetition>/
    Lernende/
      <Name_der_Repetition>.md
    Lehrperson/
      <Name_der_Repetition>_LP.md
```

`<Name_der_Repetition>` soll fachlich zum Inhalt passen und als Dateiname ASCII verwenden. Die beiden Dateien müssen sich gegenseitig verlinken. Die Lernenden-Version ist direkt abgabefähig und enthält keine didaktischen Hinweise für Lehrpersonen. Die Lehrpersonen-Version enthält denselben Aufgabenkern plus didaktische Hinweise, Diagnosehinweise, Beobachtungspunkte, mögliche Hilfestellungen und grobe Zeitangaben. Musterlösungen können später ergänzt werden, werden aber nicht automatisch erzeugt.

## 1. Ziel der Repetition

1. Festlegen, welche bereits behandelten Konzepte sichtbar geprüft werden sollen.
2. Pro Konzept eine beobachtbare Fähigkeit formulieren.
3. Aufgaben so planen, dass typische Unsicherheiten erkennbar werden.
4. Repetition nicht als Prüfung tarnen, sondern als Lernstandsklärung nutzen.

## 2. Auswahl mehrerer bisheriger Lerneinheiten

1. Zwei bis vier passende Lerneinheiten aus [CONTENT.md](../../CONTENT.md) auswählen.
2. Nur Inhalte verwenden, die bereits eingeführt und geübt wurden.
3. Die ausgewählten Konzepte bewusst mischen, zum Beispiel Methoden, Klassen, `ArrayList`, Schleifen, Tests und Maven.
4. Keine neuen Bibliotheken, Sprachfeatures, Frameworks oder Architekturmuster ergänzen.

## 3. Pflichtteil mit vielen kleinen Aufgaben

1. Viele kurze Aufgaben statt wenige grosse Aufgaben formulieren.
2. Jede Aufgabe auf eine klar beobachtbare Handlung ausrichten.
3. Aufgaben so klein halten, dass Fehler lokal erkennbar bleiben.
4. Aufgaben mit konkreten Inputs, erwarteten Resultaten oder Testfällen ergänzen, wenn das hilft.
5. Pflichtaufgaben auf EFZ-Niveau halten und ohne unnötige Zusatzkomplexität lösen lassen.

## 4. Vertiefungsteil mit etwas stärkerer Kombination

1. Bekannte Konzepte in etwas längeren, aber weiterhin überschaubaren Aufgaben kombinieren.
2. Zum Beispiel Fachlogik aus `main` auslagern, Methoden mit Rückgabewerten schreiben und mit Tests prüfen.
3. Aufgaben so formulieren, dass Strukturentscheidungen sichtbar werden.
4. Keine neue Theorie einführen; Vertiefung heisst Anwendung bekannter Konzepte in Kombination.

## 5. Optionale Transferaufgaben

1. Zusatzaufgaben klar als `Optional` oder `Transfer` kennzeichnen.
2. Zusatzaufgaben nicht als Pflicht und nicht als schwierige Aufgaben formulieren.
3. Transferaufgaben für stärkere Lernende öffnen, ohne den Pflichtteil abzuwerten oder zum Pflichtniveau zu zählen.
4. Transfer soll bekannte Konzepte in einen leicht veränderten Kontext übertragen.
5. Erwartete Resultate oder Prüfpunkte auch bei Transferaufgaben knapp angeben.

## 6. Diagnostische Hinweise für die Lehrperson

1. Nur in der Lehrpersonen-Version ergänzen.
2. Vor dem Einsatz festlegen, welche Aufgaben welche Konzepte sichtbar machen.
3. Beobachtungen während der Bearbeitung notieren, nicht erst bei der Schlusslösung.
4. Fehler als Hinweis auf fehlende Verinnerlichung lesen, nicht nur als falsches Resultat.
5. Häufige Muster sammeln und für eine kurze Nachbesprechung nutzen.
6. Bei Bedarf Aufgaben einzeln auswerten, damit klar wird, ob das Problem bei Syntax, Struktur, Testverständnis oder Werkzeugnutzung liegt.
7. Für die Auswertung ein einfaches Raster nutzen: Aufgabe, geprüftes Konzept, Beobachtung, möglicher Anschluss.

## 7. Typische Beobachtungspunkte

- Wird weiterhin alles in `main` geschrieben?
- Werden Methoden mit Rückgabewerten sinnvoll verwendet?
- Werden `ArrayList`, Schleifen und Collections korrekt eingesetzt?
- Werden Klassen, Getter/Setter und Objektlogik verstanden?
- Können Lernende sinnvolle Tests formulieren?
- Werden Edge Cases erkannt?
- Können Testfehler gelesen werden?
- Wird Refactoring als Strukturverbesserung verstanden?
- Werden Maven/JUnit-Befehle korrekt genutzt?

## 8. Qualitätscheck

- Prüft die Repetition mehrere bisherige Lerneinheiten statt nur ein isoliertes Thema?
- Enthält der Pflichtteil viele kleine, eindeutig lösbare Aufgaben?
- Bleiben alle Konzepte bekannt und bereits eingeführt?
- Ist der Vertiefungsteil eine Kombination bekannter Konzepte und kein neues Thema?
- Sind optionale Aufgaben klar als `Optional` oder `Transfer` gekennzeichnet?
- Sind die Aufgaben diagnostisch nutzbar, weil Beobachtungspunkte sichtbar werden?
- Sind Inputs, erwartete Resultate oder Testfälle dort vorhanden, wo sie Klarheit schaffen?
- Passt Sprache, Umfang und Schwierigkeit zum EFZ-Niveau?
- Bleiben Auftrag, Vertiefung, Transfer und Beobachtungshinweise klar getrennt?
- Liegt die Repetition unter `Repetitionen` und nicht unter `Uebungen`?

## Zielformat

1. Lernenden-Version mit Pflichtteil, Vertiefung und `Optional` oder `Transfer`.
2. Lehrpersonen-Version mit demselben Aufgabenkern.
3. Zusätzliche didaktische Hinweise, Diagnosehinweise, Beobachtungspunkte, Hilfestellungen und Zeitangaben nur in der Lehrpersonen-Version.
4. Gegenseitige Links zwischen Lernenden- und Lehrpersonen-Version.
