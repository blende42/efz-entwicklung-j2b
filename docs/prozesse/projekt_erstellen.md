# Prozess – Projekt erstellen

Diese Checkliste ergänzt [AGENTS.md](../../AGENTS.md) und [Prozess – Übungen erstellen](./uebungen_erstellen.md). Die Repo-Regeln bleiben verbindlich.

Mini-Projekte sind grössere, zusammenhängende Aufträge. Sie verbinden mehrere bekannte Konzepte, verlangen mehr Eigenständigkeit und führen Lernende näher an eine realistische Umsetzung heran als einzelne Übungen.

Projekte werden standardmässig getrennt von normalen Übungen unter `Projekte` abgelegt und in zwei Versionen geführt:

```text
Projekte/
  <Projektname>/
    README.md
    Lernende/
      Projektauftrag_<Projektname>.md
    Lehrperson/
      Projektauftrag_<Projektname>_LP.md
```

`<Projektname>` soll fachlich zum Inhalt passen und als Dateiname ASCII verwenden. Die Lernenden-Version ist direkt abgabefähig und enthält keine internen didaktischen Hinweise. Die Lehrpersonen-Version enthält Beobachtungspunkte, typische Schwierigkeiten, Bewertungsideen und mögliche Lösungsrichtungen. Eine vollständige Musterlösung ist nicht zwingend nötig.

## 1. Entscheid: Projekt oder Übung

Ein Projekt ist sinnvoll, wenn mehrere dieser Punkte zutreffen:

1. Mehrere bisherige Konzepte sollen in einem zusammenhängenden Auftrag kombiniert werden.
2. Lernende sollen selbst kleinere Strukturentscheidungen treffen.
3. Der Auftrag braucht mehrere Arbeitsschritte oder Arbeitstage.
4. Es gibt einen fachlichen Kontext, zum Beispiel Lagerverwaltung, Produktverwaltung oder einfache Simulation.
5. Transfer, Planung, Tests, Reflexion oder Abgabeform sind Teil des Lernziels.

Eine normale Übung ist passender, wenn ein einzelnes Konzept gezielt trainiert wird, der Lösungsweg stark geführt sein soll oder die Aufgabe in kurzer Zeit klar abgeschlossen werden kann.

## 2. Projektstruktur vorbereiten

1. Projektordner unter `Projekte/<Projektname>/` anlegen.
2. Projekt-README mit Ziel, fachlichem Kontext, technischem Rahmen und Links auf beide Versionen erstellen.
3. Lernenden-Version unter `Lernende/` ablegen.
4. Lehrpersonen-Version unter `Lehrperson/` ablegen.
5. Das Projekt-README verlinkt immer Lernenden- und Lehrpersonen-Version.
6. Lernenden- und Lehrpersonen-Version verlinken mindestens zurück auf das Projekt-README; gegenseitige Direktlinks sind optional.
7. `README.md` und `CONTENT.md` aktualisieren.

## 3. Lernenden-Version strukturieren

Die Lernenden-Version soll klar führen, aber nicht jeden Schritt vorwegnehmen.

Empfohlene Grundstruktur:

```text
# Projektauftrag <Projektname>

## Ausgangslage
## Ziel des Projekts
## Technischer Rahmen
## Pflichtanforderungen
## Optionale Erweiterungen
## Abgabe
## Reflexion
```

## 4. Lehrpersonen-Version strukturieren

Die Lehrpersonen-Version ergänzt den Projektauftrag um didaktische Orientierung und Auswertungshilfen.

Empfohlene Grundstruktur:

```text
# Projektauftrag <Projektname> LP

## Didaktischer Zweck
## Beobachtungspunkte
## Typische Schwierigkeiten
## Bewertungsideen
## Mögliche Lösungsrichtungen
## Bezug zur Lernenden-Version
```

## 5. Pflichtanforderungen formulieren

1. Pflichtanforderungen als beobachtbare Ergebnisse formulieren.
2. Jede Pflichtanforderung muss für Lernende eindeutig prüfbar sein.
3. Anforderungen auf bekannte Konzepte beschränken.
4. Fachliche Muss-Funktionen von technischen Muss-Kriterien trennen, wenn das Klarheit schafft.
5. Keine versteckten Zusatzanforderungen einbauen.
6. Randfälle und erwartetes Verhalten nur dort konkretisieren, wo sie zum Lernziel gehören.

Gute Pflichtanforderungen beschreiben, was am Ende vorhanden sein oder funktionieren muss. Sie schreiben nicht jeden Implementierungsschritt vor.

## 6. Optionale Erweiterungen formulieren

1. Optionale Erweiterungen klar als `Optional` oder `Erweiterung` kennzeichnen.
2. Erweiterungen dürfen den Pflichtteil nicht voraussetzen oder verdeckt vergrössern.
3. Erweiterungen sollen bekannte Konzepte in einen leicht anspruchsvolleren Kontext übertragen.
4. Für stärkere Lernende dürfen Entscheidungsräume geöffnet werden.
5. Erwartete Wirkung oder Prüfpunkte knapp benennen.

Optionale Erweiterungen sind kein zweiter Pflichtteil. Sie dienen Transfer, Differenzierung und zusätzlicher Vertiefung.

## 7. Technischen Rahmen setzen

1. Vorgegebene Sprache, Build-Werkzeug und Projektstruktur nennen.
2. Bei Java-Projekten bevorzugt Maven verwenden, wenn der Unterrichtsstand dazu passt.
3. Erlaubte und nicht erlaubte Hilfsmittel oder Bibliotheken klar benennen.
4. Startstruktur, Package-Namen, Testbefehle oder Abgabeformat nur so weit vorgeben, wie es für das Lernziel nötig ist.
5. Keine Frameworks oder Architekturbegriffe einführen, die fachlich noch nicht vorbereitet sind.
6. Technische Vorgaben so formulieren, dass sie Orientierung geben, aber sinnvolle Eigenständigkeit zulassen.

## 8. Reflexion ergänzen

Reflexionsfragen sollen kurz, konkret und auf beobachtbare Entscheidungen bezogen sein.

Geeignete Fragen:

- Welche Strukturentscheidung hat dir geholfen?
- Wo war dein Code zuerst unübersichtlich?
- Welche Anforderung war am schwierigsten zu prüfen?
- Was würdest du beim nächsten Projekt früher testen?
- Welche optionale Erweiterung wäre fachlich sinnvoll, auch wenn du sie nicht umgesetzt hast?

## 9. Bewertungsideen ergänzen

Bewertungsideen gehören in die Lehrpersonen-Version. Sie müssen nicht zwingend ein detailliertes Punkteraster sein.

Sinnvolle Bewertungsperspektiven:

- Erfüllung der Pflichtanforderungen
- Nachvollziehbare Struktur
- Umgang mit bekannten Konzepten
- Testbarkeit oder manuelle Prüfbarkeit
- Lesbarkeit und Benennung
- Umgang mit Randfällen
- Reflexion über Entscheidungen und Schwierigkeiten

Bewertungsideen sollen helfen, Beobachtungen einzuordnen. Sie sollen den Projektauftrag nicht in eine reine Checklistenprüfung verwandeln.

## 10. Musterlösung bewusst entscheiden

Eine vollständige Musterlösung ist bei Projekten nicht zwingend nötig, weil Projekte unterschiedliche gültige Lösungswege zulassen können. Oft reichen mögliche Lösungsrichtungen, Beobachtungspunkte und Prüfkriterien.

Eine vollständige Musterlösung ist sinnvoll, wenn:

1. Lehrpersonen eine Referenzimplementierung für technische Prüfung brauchen.
2. Der Projektauftrag sehr klar eine Standardarchitektur erwartet.
3. spätere Aufgaben direkt auf einer gemeinsamen Lösung aufbauen.

Keine vollständige Musterlösung ist nötig, wenn:

1. verschiedene einfache Strukturen fachlich vertretbar sind.
2. Eigenständigkeit und Begründung wichtiger sind als eine einzige Ziellösung.
3. die Auswertung über Pflichtanforderungen, Tests, Demo und Reflexion zuverlässig möglich ist.

## Qualitätscheck

- Ist klar, warum der Auftrag ein Projekt und keine normale Übung ist?
- Kombiniert das Projekt mehrere bereits behandelte Konzepte?
- Sind Lernenden- und Lehrpersonen-Version sauber getrennt?
- Ist die Lernenden-Version direkt abgabefähig?
- Sind Pflichtanforderungen eindeutig und prüfbar formuliert?
- Sind optionale Erweiterungen wirklich optional?
- Sind technische Rahmenbedingungen klar, aber nicht unnötig eng?
- Gibt es Reflexionsfragen mit Bezug zur Umsetzung?
- Unterstützt die Lehrpersonen-Version Beobachtung, Bewertung und Nachbesprechung?
- Ist bewusst entschieden, ob eine vollständige Musterlösung nötig ist?
