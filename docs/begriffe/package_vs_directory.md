# Package vs. Verzeichnis

## Kurz erklärt

Ein Java-Package ist ein Name im Quellcode. Ein Verzeichnis ist ein Ordner im Dateisystem.

Beides muss zusammenpassen.

Beispiel:

```java
package ch.allianz.youngoitv.produktverwaltung.model;
```

Passender Pfad in einem Maven-Projekt:

```text
src/main/java/ch/allianz/youngoitv/produktverwaltung/model/Produkt.java
```

## Unterrichtsbezug

`src/main/java` ist der Quellwurzelordner. Danach beginnt die Package-Struktur.

## Typischer Stolperstein

Falsch:

```java
package src.main.java.ch.allianz.youngoitv;
```

`src/main/java` gehört nicht in die Package-Deklaration.
