# Bewertungskriterien

## Zweck

Bewertung macht sichtbar, worauf bei Lösungen geachtet wird.

Bewertet wird nicht nur, ob eine Lösung läuft. Wichtig sind auch Struktur, Verständlichkeit und Lernzielerreichung.

Die Rückmeldung soll technisch hilfreich sein. Sie zeigt, was bereits tragfähig ist und was als Nächstes verbessert werden soll.

Diese Kriterien eignen sich für:

- Übungen
- Projekte
- Code-Reviews
- Architekturgespräche
- Reflexionen nach praktischen Aufgaben

---

## Standardbereiche

### Funktionalität

Die Lösung erfüllt die geforderte Aufgabe.

Beobachtungen:

- Funktioniert der wichtigste Ablauf?
- Werden erwartete Eingaben verarbeitet?
- Sind offensichtliche Fehlerfälle sichtbar?

### Verantwortlichkeiten

Die Lösung trennt Aufgaben sinnvoll.

Beobachtungen:

- Bleibt Fachlogik in der passenden Klasse?
- Werden Controller, Service und Repository nicht vermischt?
- Gibt es keine unnötigen Abkürzungen durch mehrere Schichten?

### Verständlichkeit

Die Lösung ist nachvollziehbar.

Beobachtungen:

- Sind Namen verständlich?
- Ist der Ablauf lesbar?
- Kann eine andere Person die Lösung erklären?

### Technische Sauberkeit

Die Lösung ist technisch ordentlich umgesetzt.

Beobachtungen:

- Gibt es keine unnötige Komplexität?
- Sind Code und Dateien passend strukturiert?
- Sind typische Fehlerquellen vermieden?

### Lernzielerreichung

Die Lösung zeigt das angestrebte Konzept.

Beobachtungen:

- Wird das zentrale Lernziel sichtbar?
- Kann die lernende Person den Entscheid begründen?
- Passt die Lösung zum aktuellen Ausbildungsstand?

---

## Einschätzungsstufen

Verwende einfache qualitative Einschätzungen:

- gut erfüllt
- teilweise erfüllt
- noch unklar
- Verbesserungsbedarf

Die Einschätzung ersetzt keine technische Beobachtung. Sie soll immer mit einer kurzen Begründung verbunden sein.

---

## Standard-Raster

| Bereich | Einschätzung | Beobachtung |
|---|---|---|
| Funktionalität | | |
| Verantwortlichkeiten | | |
| Verständlichkeit | | |
| Technische Sauberkeit | | |
| Lernzielerreichung | | |

---

## Rückmeldung

### Stärken

Was ist bereits gut nachvollziehbar oder fachlich tragfähig?

Beispiel:

```text
Die Service-Schicht bleibt klar für die Fachlogik zuständig.
```

### Nächste Verbesserung

Was ist der wichtigste nächste Schritt?

Beispiel:

```text
Das Mapping sollte aus dem Repository herausgenommen werden.
```

### Offene Fragen

Welche Fragen müssen noch geklärt werden?

Beispiel:

```text
Welche JSON-Felder sollen wirklich Teil der öffentlichen API sein?
```

---

## Nicht-Ziele

Diese Kriterien sind bewusst keine:

- Punktevergabe
- Notenskala
- vollständige Rubric
- automatische Bewertung

Ziel ist eine klare technische Rückmeldung, die beim Weiterlernen hilft.
