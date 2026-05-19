# Prozess - Projekt-Review

Diese Checkliste ergänzt [AGENTS.md](../../AGENTS.md) und [Prozess - Projekt erstellen](./projekt_erstellen.md). Die Repo-Regeln bleiben verbindlich.

Ein Projekt-Review ist ein strukturiertes Einzelgespräch nach einem Mini-Projekt. Es ist kein klassischer Prüfungsanlass und kein Peer-Review. Im Mittelpunkt stehen Präsentation, Architekturverständnis, Reflexion, Softwareengineering-Denken und nächste Lernschritte.

## 1. Zweck des Projekt-Reviews

Mini-Projekte unterscheiden sich von normalen Übungen: Sie kombinieren mehrere bisherige Konzepte, lassen mehr Entscheidungsraum und führen zu unterschiedlichen, fachlich vertretbaren Lösungen.

Der Review soll deshalb nicht nur zeigen, ob eine Lösung funktioniert. Er soll sichtbar machen, wie der Lernende über Struktur, Verantwortlichkeiten, Fachlogik, Tests und Erweiterbarkeit nachdenkt.

Wichtige Schwerpunkte:

- Projektlösung nachvollziehbar präsentieren
- Architekturentscheidungen erklären und reflektieren
- Verantwortlichkeiten im Code besprechen
- Fachlogik und Randfälle analysieren
- Tests als Absicherung und Denkwerkzeug nutzen
- gezielte Refactoring-Ideen ableiten
- individuelle Diagnose und Coaching ermöglichen

## 2. Ablauf des Projekt-Reviews

Der Ablauf kann je nach Projektumfang kurz oder ausführlicher sein. Für ein Mini-Projekt reicht oft ein konzentriertes Gespräch mit konkretem Blick in Code, Tests und Demo.

1. **Projektpräsentation**
   - Der Lernende zeigt kurz, was die Anwendung kann.
   - Eine kleine Demo mit Beispieldaten reicht.
   - Wichtig ist, dass der Ablauf der Lösung verständlich wird.

2. **Architekturgespräch**
   - Der Lernende erklärt Packages, zentrale Klassen und den groben Ablauf.
   - Die Lehrperson fragt nach Gründen für die gewählte Struktur.
   - Fachbegriffe sind hilfreich, aber nicht wichtiger als nachvollziehbare Begründungen.

3. **Diskussion von Verantwortlichkeiten**
   - Gemeinsam wird angeschaut, welche Klasse welche Aufgabe hat.
   - Besonders wichtig ist die Trennung von Programmstart, Fachlogik und Infrastruktur.
   - `main` soll koordiniert, aber nicht die ganze Lösung tragen.

4. **Review der Tests**
   - Der Lernende zeigt zentrale Tests und erklärt, welche Fachlogik sie absichern.
   - Randfälle und fehlende Tests werden gemeinsam benannt.
   - Tests sollen nicht nur Getter und Setter prüfen.

5. **Review der Fachlogik**
   - Fachliche Regeln werden anhand konkreter Beispiele gemeinsam angeschaut.
   - Typische Fragen betreffen Validierung, Zustandsänderungen, Fehlerfälle und gespeicherte Daten.
   - Wichtig ist, ob der Code die Fachregel klar ausdrückt.

6. **Refactoring-Ideen**
   - Es werden 1 bis 3 kleine Verbesserungen gesammelt.
   - Die Vorschläge sollen konkret, machbar und begründbar sein.
   - Ziel ist bessere Wartbarkeit, nicht eine neue Komplettlösung.

7. **Reflexion**
   - Der Lernende beschreibt schwierige Stellen, Entscheidungen und Lernmomente.
   - Die Lehrperson fragt nach Alternativen und Begründungen.

8. **Nächste Lernschritte**
   - Am Schluss werden wenige nächste Schritte festgehalten.
   - Geeignet sind zum Beispiel ein zusätzlicher Test, ein kleines Refactoring oder eine präzisere Trennung von Verantwortlichkeiten.

## 3. Beobachtungspunkte

Mögliche Beobachtungspunkte für das Gespräch:

- Sind Verantwortlichkeiten sinnvoll getrennt?
- Ist `main` übersichtlich oder überladen?
- Sind fachliche Regeln korrekt modelliert?
- Sind Zustandsänderungen nachvollziehbar?
- Werden ungültige Werte und Randfälle bewusst behandelt?
- Sind Tests sinnvoll gewählt?
- Zeigen Tests Fachlogik statt nur technische Nebensachen?
- Ist Wiederverwendung vorhanden, ohne unnötige Abstraktion?
- Gibt es unnötige Komplexität?
- Sind Namen von Klassen, Methoden und Variablen verständlich?
- Ist Erweiterbarkeit sichtbar, ohne dass eine grosse Architektur vorgegeben wurde?
- Kann der Lernende die wichtigsten Entscheidungen begründen?

## 4. Reflexionsfragen

Geeignete Fragen für das Einzelgespräch:

- Warum hast du diese Struktur gewählt?
- Welche Klasse ist für dich am wichtigsten, und warum?
- Wo liegt die zentrale Fachlogik?
- Welche Stelle war schwierig umzusetzen?
- Welche Fachregel war komplexer als erwartet?
- Welche Tests geben dir am meisten Sicherheit?
- Welche Randfälle hast du bewusst geprüft?
- Wo wäre eine Erweiterung schwierig?
- Welche Refactorings wären sinnvoll?
- Was würdest du beim nächsten Mini-Projekt früher testen?
- Welche Entscheidung würdest du heute anders treffen?

## 5. Refactoring-Teil

Der Refactoring-Teil soll klein und gezielt bleiben. Es geht nicht darum, die Lösung neu zu schreiben oder eine Idealarchitektur zu erzwingen.

Sinnvolle Refactoring-Ideen:

- eine überladene Methode aufteilen
- sprechendere Namen wählen
- Logik aus `main` in eine passende Klasse verschieben
- doppelte Codeabschnitte zusammenführen
- eine fachliche Regel klarer kapseln
- Datei-I/O von Fachlogik trennen
- einen fehlenden Randfall mit einem Test absichern

Jede Refactoring-Idee soll kurz begründet werden:

- Was wird dadurch verständlicher?
- Was wird leichter testbar?
- Welche spätere Änderung wird einfacher?

## 6. Hinweise für Einzelbetreuung

Bei aktuell einem Lernenden ist der Projekt-Review ein Coaching-Gespräch. Es braucht keine künstliche Gruppenarbeit, keine simulierten Peer-Reviews und kein formales Bewertungsraster.

Die Lehrperson kann das Gespräch lenken durch:

- offene Fragen
- konkrete Codeausschnitte
- kurze Gegenbeispiele
- gemeinsame Suche nach einem kleinen nächsten Schritt
- klare Rückmeldung zu Stärken und Unsicherheiten

Der Review soll individuelle Diagnose ermöglichen. Entscheidend ist, welche Konzepte der Lernende wirklich verstanden hat und welche nächsten Lernschritte fachlich sinnvoll sind.

## Qualitätscheck

- Unterstützt der Review die Präsentation der Lösung?
- Werden Architekturentscheidungen sichtbar?
- Werden Verantwortlichkeiten, Tests und Fachlogik besprochen?
- Bleibt der Gesprächscharakter erhalten?
- Werden kleine Refactoring-Ideen statt kompletter Neuschreibung gefördert?
- Entsteht eine individuelle Diagnose?
- Sind nächste Lernschritte konkret genug?
