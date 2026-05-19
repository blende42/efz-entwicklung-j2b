# Build-Artefakt

## Kurz erklärt

Ein Build-Artefakt ist eine Datei, die beim Build erzeugt wird.

Beispiele:

- `.class`-Dateien
- später auch eine `.jar`-Datei

## Unterrichtsbezug

Beim Maven-Einstieg reicht:

```text
src/main/java  ->  target/classes
```

Links steht eigener Quellcode. Rechts stehen erzeugte Dateien.

Der Ordner `target` ist der Build-Ordner. Er enthält Build-Artefakte.

## Typischer Stolperstein

Eigener Quellcode gehört nicht in `target`.

`target` darf gelöscht und neu erzeugt werden. Quellcode darf nicht verloren gehen, wenn `target` gelöscht wird.
