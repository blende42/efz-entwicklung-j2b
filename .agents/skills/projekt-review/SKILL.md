---
name: projekt-review
description: Unterstützt strukturierte Projekt-Reviews mit Architekturgespräch, Reflexionsfragen, Verantwortlichkeitsanalyse, Testreview und Refactoring-Ideen.
---

# Skill - Projekt-Review

## Zweck

Unterstützt strukturierte Reviews von Mini-Projekten. Der Fokus liegt auf Coaching, Architekturreflexion, Verantwortlichkeiten, fachlichen Tests, Refactoring-Ideen und nächsten Lernschritten.

## Verwenden wenn

Ein Mini-Projekt besprochen, reflektiert oder didaktisch ausgewertet werden soll, ohne daraus eine klassische Prüfung oder eine Musterlösung zu machen.

## Vorgehen

1. Lies zuerst [AGENTS.md](../../../AGENTS.md).
2. Nutze [Prozess - Projekt-Review](../../../docs/prozesse/projekt_review.md).
3. Nutze bei Bedarf [Projekt-Review Vorlage](../../../templates/projekt_review_template.md).
4. Berücksichtige den Projektcharakter: mehrere Konzepte, Eigenständigkeit, Transfer und unterschiedliche vertretbare Lösungswege.
5. Verwende keine klassische Prüfungslogik und kein formales Bewertungsraster.
6. Unterstütze Coaching und Mentoring durch offene, konkrete Fragen.
7. Fördere Architekturreflexion: Packages, Klassen, Datenfluss, Fachlogik und Infrastruktur.
8. Analysiere Verantwortlichkeiten: Was macht `main`, welche Klasse kennt welche Daten, wo liegt Fachlogik?
9. Diskutiere fachliche Tests: besprochene Regeln, Randfälle, fehlende Tests und Abhängigkeit von Datei-I/O.
10. Erzeuge gezielte Reflexionsfragen zu Entscheidungen, Schwierigkeiten, Randfällen und Erweiterbarkeit.
11. Fördere kleine Refactorings, keine komplette Neuschreibung.
12. Halte nächste Lernschritte kurz, konkret und umsetzbar.

## Typische Architekturprobleme

- alles liegt in `main`
- fehlende Trennung von Verantwortlichkeiten
- unnötige Interfaces ohne erklärbaren Nutzen
- doppelte Logik
- fehlende oder triviale Tests
- zu starke Kopplung zwischen Fachlogik und Datei-I/O
- schlechte oder unklare Methodennamen
- fehlende Trennung von Fachlogik, Programmstart und Infrastruktur
- unnötige Komplexität vor funktionierender Grundlogik

## Ergebnis

Ein Projekt-Review mit klaren Beobachtungspunkten, passenden Reflexionsfragen, Hinweisen zu Verantwortlichkeiten, Testreview, kleinen Refactoring-Ideen und konkreten nächsten Lernschritten.
