---
name: git-repo-updaten
description: Verwenden, wenn der Benutzer Git-Schreibaktionen wie Commit oder Push ausdrücklich verlangt.
---

# name: git-repo-updaten

description: Aktualisiert ein Git-Repository kontrolliert mit git status, git diff, git add, git commit und git push. Nur verwenden, wenn der Benutzer Git-Schreibaktionen ausdrücklich verlangt.

## Verwenden wenn

Der Benutzer ausdrücklich verlangt, dass Änderungen mit Git abgeschlossen werden, zum Beispiel durch Commit und Push.

Nicht verwenden für normale Dateiänderungen, Reviews oder Verifikation ohne ausdrücklichen Git-Auftrag.

## Grundsatz

Git-Schreibaktionen sind nur erlaubt, wenn der Benutzer sie ausdrücklich verlangt. Vorher nur lesend prüfen und berichten.

## Ablauf

1. Repository-Zustand prüfen:

   ```bash
   git status
   git status --short
   git branch --show-current
   ```

2. Änderungen prüfen:

   ```bash
   git diff --stat
   git diff
   ```

3. Stoppen und Rückfrage stellen, wenn verdächtige Dateien auftauchen:

   - Secrets oder Zugangsdaten
   - Build-Artefakte
   - `target/`
   - `out/`
   - `.class`-Dateien
   - temporäre Dateien
   - grosse oder thematisch fremde Änderungen

   Untracked-Dateien aus `git status` und `git status --short` besonders prüfen, weil sie in `git diff` nicht vollständig sichtbar sind.

4. Änderungen stagen:

   ```bash
   git add .
   ```

5. Staging prüfen:

   ```bash
   git diff --cached --stat
   git diff --cached
   ```

   Wenn dabei verdächtige oder nicht zum Auftrag passende Dateien sichtbar werden, abbrechen und Rückfrage stellen.

6. Commit-Message formulieren:

   - kurz
   - konkret
   - im Imperativ
   - passend zu den tatsächlich gestagten Änderungen

   Beispiel:

   ```text
   Ergänze Maven-Einstieg
   ```

7. Commit ausführen, wenn die gestagten Änderungen vollständig zum Benutzerauftrag passen:

   ```bash
   git commit -m "Kurze konkrete Nachricht"
   ```

8. Push vorbereiten:

   ```bash
   git status
   git branch -vv
   ```

9. Push ausführen, wenn der Commit erfolgreich war, `git status` keine unerwarteten offenen Änderungen zeigt und ein Upstream gesetzt ist:

   ```bash
   git push
   ```

10. Wenn kein Upstream gesetzt ist:

    - nicht blind raten
    - keinen Remote ändern
    - Vorschlag melden, zum Beispiel:

      ```text
      Kein Upstream gesetzt. Vorschlag: git push -u origin <branch>
      ```

## Strikte Verbote

- kein `git push --force`
- kein Rebase
- keine Branches löschen
- keine Tags erstellen
- keine Remotes ändern
- keine Merge-Konflikte automatisch auflösen
- keine verdächtigen Dateien committen

## Ergebnisbericht

Nach Abschluss kurz melden:

- Branch
- Commit-Hash
- Push-Ergebnis
- nicht ausgeführte Schritte oder Einschränkungen
