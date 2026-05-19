# Classpath

## Kurz erklärt

Der Classpath sagt Java, wo es kompilierte Klassen suchen soll.

Beim manuellen Starten eines Programms kann das so aussehen:

```bash
java -cp out ch.allianz.youngoitv.produktverwaltung.Main
```

`-cp out` bedeutet: Suche die `.class`-Dateien im Ordner `out`.

## Unterrichtsbezug

Der Classpath ist wichtig, bevor Maven als Build-Tool eingeführt wird:

- Ohne Maven wird oft nach `out` kompiliert.
- Mit Maven liegen Klassen standardmässig unter `target/classes`.
- Maven ersetzt das Classpath-Verständnis nicht.
- `mvn compile` startet das Programm nicht; Starten bleibt ein eigener Schritt.

## Typischer Stolperstein

Am Schluss des `java`-Befehls steht der vollständige Klassenname, nicht ein Dateipfad:

```text
ch.allianz.youngoitv.produktverwaltung.Main
```
