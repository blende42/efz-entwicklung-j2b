# Convention over Configuration

## Kurz erklärt

`Convention over Configuration` bedeutet: Ein Werkzeug verwendet sinnvolle Standardregeln, damit man weniger einstellen muss.

Bei Maven ist zum Beispiel diese Struktur eine Konvention:

```text
src/main/java
```

Maven erwartet dort Java-Quellcode. Deshalb muss dieser Ordner in einem einfachen Projekt nicht extra in der `pom.xml` konfiguriert werden.

## Unterrichtsbezug

Der Begriff passt gut zum Maven-Einstieg:

- Lernende kennen bereits `src` und `out` aus dem manuellen Build.
- Maven führt neue Standardorte ein: `src/main/java` und `target`.
- Die Struktur wirkt zuerst länger, spart aber Erklärungen und Spezialfälle.

## Typischer Stolperstein

Konvention heisst nicht, dass man nichts verstehen muss. Man muss wissen, welche Struktur erwartet wird.
