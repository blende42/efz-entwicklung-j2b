# AGENTS.md

## Zweck des Repos
Dieses Repository dient zur Erstellung und Pflege von Lehrinhalten für die Ausbildung zum Applikationsentwickler mit eidgenössischem Fähigkeitsausweis.

## Verzeichnisrollen
- `Arbeitsblaetter` enthält Lehr- und Arbeitsblätter.
- `Arbeitsblaetter/arbeitsblatt_grafiken` enthält SVG-Grafiken, auf die Arbeitsblätter referenzieren.
- `Uebungen` enthält Übungsblätter.
- `Projekte` enthält grössere, zusammenhängende Mini-Projekte mit getrennten Versionen für Lernende und Lehrpersonen.
- `Musterloesungen` enthält Musterlösungen.
- `graphics` enthält allgemeine Unterrichtsgrafiken im SVG-Format.
- `templates` enthält wiederverwendbare Templates, Prompts und begleitende Workflow-Dokumente.

## MUSS
- Verwende Deutsch mit Locale `de_CH`.
- Verwende Schweizer Hochdeutsch mit `ss` statt `ß`.
- Verwende in normalen Texten und SVG-Beschriftungen `ä`, `ö` und `ü` korrekt.
- Verwende für Build-, Test- und Run-Aufgaben bevorzugt `mvn`.
- Verwende `javac` nur zum Kompilieren bzw. Builden von Java-Code.
- Verwende `java` nur zur Ausführung von Artefakten, die mit `javac` oder Maven erstellt wurden.
- Orientiere dich an bestehenden Mustern im Repository, bevor du neue Formate oder Strukturen einführst.
- Halte Änderungen klein, gezielt und didaktisch nachvollziehbar.
- Aktualisiere `README.md` bei jeder strukturellen oder inhaltlichen Änderung des Repositories im gleichen Arbeitsschritt.
- Benenne offen, wenn Verifikation nicht praktikabel war oder Einschränkungen bestehen.
- Verwende in Dateinamen ausschliesslich ASCII-Zeichen.
- Halte Markdown-Inhalte klar gegliedert und zum Dateityp passend.
- Stelle sicher, dass Codeblöcke korrekt ausgezeichnet und lesbar sind.
- Halte Arbeitsblätter lernzielorientiert, Übungen auftragsklar und Musterlösungen kompakt.
- Halte Java-Beispiele klein, direkt nachvollziehbar und fachlich fokussiert.
- Zeige typische Fehler, Randfälle und Stolpersteine sichtbar, wenn sie zum Lernziel gehören.
- Verwende für SVG-Grafiken nach Möglichkeit die bestehenden robusten Templates.
- Escape in SVG-Texten XML-Sonderzeichen immer korrekt:
  - `<` als `&lt;`
  - `>` als `&gt;`
  - `&` als `&amp;`
- Prüfe jede erzeugte oder geänderte SVG auf Wohlgeformtheit, Platzhalterreste, Lesbarkeit, Textüberlauf und korrekte de-CH-Schreibweise.
- Verifiziere Änderungen in einer zum Dateityp passenden Tiefe.
- Schliesse die Arbeit mit einem kurzen Bericht über Änderungen, Verifikation und Einschränkungen ab.

## SOLL
- Bevorzuge einfache, gut nachvollziehbare Lösungen auf EFZ-Niveau.
- Bevorzuge kurze Abschnitte, kleine Schritte und konkrete Beispiele.
- Führe neue Konzepte möglichst einzeln ein.
- Trenne Theorie, Auftrag, Beispiel, Testfälle, Zusatzaufgaben und Reflexion klar sichtbar.
- Verwende bei Übungen nach Möglichkeit eine Staffelung wie Basis, Aufbau, Vertiefung und Transfer.
- Gib bei Bedarf konkrete Inputs, Testfälle oder erwartete Resultate an.
- Ergänze bei Musterlösungen kurze Hinweise zu Vorteil, Nachteil oder typischem Fehler, wenn das didaktisch hilft.
- Verwende Grafiken, wenn Vergleiche, Beziehungen, Abläufe oder Architekturen dadurch klarer verständlich werden.
- Behalte bestehende Strukturen bei, solange sie fachlich und didaktisch tragen.
- Verifiziere Java-bezogene Änderungen technisch, wenn kompilierbare oder ausführbare Artefakte betroffen sind.
- Verwende Shell-Kommandos nur, wenn sie technisch oder didaktisch sinnvoll sind.

## DARF NICHT
- Keine Git-Schreibaktionen ausführen, ausser dies wird explizit verlangt.
- Für Git-Abschlüsse den Skill `git-repo-updaten` verwenden; Git-Schreibaktionen bleiben nur bei ausdrücklicher Aufforderung erlaubt.
- Keine unnötig komplexen Enterprise-, Architektur- oder Framework-Muster einführen.
- Keine SVG-Layouts ohne klaren Nutzen umbauen.
- Keine Grafiken rein dekorativ hinzufügen.
- Keine Platzhalter, kaputten Referenzen, halbfertigen Abschnitte oder unvalidierten SVGs zurücklassen.
- Keine bestehenden Strukturen nur aus Geschmacksgründen umbauen.
- Keine Lösungen liefern, die zwar raffiniert, aber für Lernende unnötig schwer nachvollziehbar sind.

## Qualitätsgates

### Markdown
- Sprache, Stil und Niveau passen zum Repository.
- Struktur ist klar erkennbar und passt zum Dateityp.
- Überschriften, Aufgaben, Beispiele sowie Reflexions- oder Lösungsteile sind sauber getrennt.
- Referenzen auf lokale Dateien oder Grafiken sind korrekt.
- Keine offensichtlichen Platzhalter, Brüche oder halbfertigen Abschnitte bleiben zurück.

### Arbeitsblätter
- Lernziele sind klar formuliert.
- Theorie bleibt knapp und verständlich.
- Wenn fachlich nötig, ist mindestens ein konkretes Beispiel oder eine Veranschaulichung enthalten.
- Ein didaktisch sinnvoller Abschluss wie Reflexion ist vorhanden.

### Übungen
- Aufträge sind eindeutig lösbar formuliert.
- Schwierigkeit ist nachvollziehbar gestaffelt oder bewusst einheitlich.
- Inputs, Testfälle oder erwartete Resultate sind vorhanden, wenn sie zum Lernziel beitragen.
- Kein unvorbereitetes Vorwissen wird stillschweigend vorausgesetzt.

### Musterlösungen
- Lösung ist fachlich korrekt.
- Lösung ist mit vertretbarem Aufwand nachvollziehbar.
- Keine unnötige Abstraktion oder Zusatzkomplexität ist eingebaut.
- Bei komplexeren Aufgaben bleibt die Struktur der Lösung erkennbar.

### SVG
- Datei ist wohlgeformtes SVG bzw. XML.
- Keine Platzhalterreste sind vorhanden.
- Texte sind lesbar und wirken nicht überlaufen oder gequetscht.
- Template-Typ bleibt passend oder eine Abweichung ist klar begründet.
- Verknüpfung mit referenzierenden Markdown-Dateien ist konsistent.

### Java und Maven
- Beispiel oder Code ist fachlich korrekt und didaktisch angemessen.
- `javac` wird nur zum Kompilieren und `java` nur zum Ausführen erzeugter Artefakte verwendet.
- Soweit praktikabel wurde mit `mvn`, `javac` oder `java` verifiziert.
- Nicht durchgeführte Verifikation oder bekannte Einschränkungen sind klar benannt.

## Entscheidungsregeln
- Erweitere bestehende Dateien, wenn neues Material thematisch, didaktisch und strukturell klar dazu gehört.
- Lege neue Dateien an, wenn ein neues Thema, ein neuer Unterrichtsblock oder ein deutlich anderer Schwierigkeitsgrad entsteht.
- Bevorzuge Text, wenn kurze Erklärungen und kleine Beispiele genügen.
- Verwende Grafiken, wenn Vergleiche, Abläufe, Beziehungen oder Architekturen dadurch klarer werden.
- Liefere genau eine Standardlösung, wenn das Lernziel eine eindeutige Grundlösung erwartet.
- Liefere mehrere Lösungsniveaus nur, wenn dies didaktischen Mehrwert bringt.
- Behalte bestehende Struktur, wenn sie den Inhalt weiterhin klar trägt.
- Baue Struktur nur um, wenn Orientierung, Wiederverwendbarkeit oder didaktische Klarheit klar verbessert werden.

## Prioritäten bei Zielkonflikten
- Fachliche Korrektheit vor stilistischer Eleganz.
- Didaktische Verständlichkeit vor technischer Raffinesse.
- Konsistenz mit der bestehenden Struktur vor kosmetischer Umstrukturierung.
- Verifizierte Ergebnisse vor ungetesteten Annahmen.
- Klare, kleine und nachvollziehbare Lösungen vor überladener Vollständigkeit.
- Bewährte Muster im Repository vor neuen Formaten, solange sie das Lernziel gut tragen.
- Offene Benennung von Einschränkungen vor stillschweigender schwacher Kompromisslösung.

## Verbindlicher Arbeitsablauf
1. Bestehende Struktur, betroffene Dateien und vorhandene Muster analysieren.
2. Relevante Unklarheiten frühzeitig klären.
3. Auf Basis der Entscheidungsregeln Erweiterung oder neue Datei wählen.
4. Änderungen klein, klar und konsistent umsetzen.
5. In passender Tiefe verifizieren.
6. `README.md` bei Struktur- oder Inhaltsänderungen im gleichen Arbeitsschritt aktualisieren.
7. Kurz über Änderungen, Verifikation und Einschränkungen berichten.

## SVG-Konventionen
- Verwende nach Möglichkeit die bestehenden robusten Templates in `templates`.
- Behalte die Template-Struktur grundsätzlich bei und ersetze primär Inhalte statt Layout.
- Halte Texte kurz, klar und didaktisch verständlich.
- Vermeide Textüberlauf, gequetschte Beschriftungen und Überladungen.
- Escape in SVG-Texten immer XML-Sonderzeichen:
  - `<` als `&lt;`
  - `>` als `&gt;`
  - `&` als `&amp;`
- Entferne alle Platzhalter vollständig.
- Prüfe jede erzeugte oder geänderte SVG vor Abschluss auf Wohlgeformtheit, Renderbarkeit, Textüberlauf und korrekte de-CH-Schreibweise.

## Dateinamen
- Verwende in Dateinamen ausschliesslich ASCII-Zeichen.

## README.md
- `README.md` beschreibt den aktuellen Stand des Repository-Inhaltsverzeichnisses.
- Wenn Struktur oder Inhalt des Repositories geändert werden, ist `README.md` immer im gleichen Arbeitsschritt zu aktualisieren.
