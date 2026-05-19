# Orchestrierung

## Kurz erklärt

Orchestrierung bedeutet: Mehrere Schritte oder Werkzeuge werden in einer sinnvollen Reihenfolge koordiniert.

Bei Maven heisst das zum Beispiel:

- Maven sucht den Quellcode am erwarteten Ort.
- Maven ruft den Java-Compiler `javac` passend auf.
- Maven legt erzeugte Dateien im Build-Ordner ab.

Maven ersetzt diese Werkzeuge nicht. Maven organisiert ihre Zusammenarbeit.

## Unterrichtsbezug

Die Dirigent-/Orchester-Analogie passt gut:

- Der Dirigent spielt nicht jedes Instrument selbst.
- Er koordiniert, wann welches Instrument einsetzt.
- Maven koordiniert, wann welches Build-Werkzeug verwendet wird.

## Typischer Stolperstein

Falsch:

```text
Maven macht alles magisch.
```

Besser:

```text
Maven führt bekannte Schritte geordnet aus.
```
