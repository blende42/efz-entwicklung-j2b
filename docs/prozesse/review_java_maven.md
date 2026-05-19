# Prozess – Review Java und Maven

Diese Checkliste ergänzt [AGENTS.md](../../AGENTS.md). Die Repo-Regeln bleiben verbindlich.

## Checkliste

1. Java-Begriffe fachlich prüfen.
2. Package-Deklarationen und Ordnerstrukturen vergleichen.
3. `src`, `out`, `src/main/java`, `target` und `target/classes` sauber unterscheiden.
4. `javac`, `java` und `mvn` nicht vermischen.
5. Maven als Build-Tool prüfen, nicht als Ersatz für Java.
6. `pom.xml` auf Einfachheit und Passung prüfen.
7. Sicherstellen, dass keine ungewollten Dependencies eingeführt werden.
8. Prüfen, ob JUnit, Maven Central oder Multi-Module bewusst ausgeschlossen bleiben, wenn der Block das verlangt.
9. Java- oder Maven-Beispiele technisch verifizieren, soweit praktikabel.

## Ergebnisformat

- Fachliche Risiken zuerst nennen.
- Kleine Korrekturen bevorzugen.
- Nicht geprüfte technische Punkte offen benennen.
