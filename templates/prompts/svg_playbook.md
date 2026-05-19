# SVG Playbook – EFZ Applikationsentwicklung

## 🎯 Zweck
Standardisierter Workflow für die Erstellung von SVG-Unterrichtsgrafiken:
- Vergleich
- Prozess
- Konzept

---

## 🚀 Workflow

1. Template wählen
2. Master-Prompt kopieren
3. Inhalt einsetzen
4. SVG generieren
5. **SVG-Validation automatisch durchführen**
6. Optional: Auto-Refinement

---

## 🔀 Vergleich – Master-Prompt

Erstelle eine didaktisch hochwertige SVG-Grafik im Vergleichs-Template.

INHALT:
- Linke Seite: Code (1 Zeile), Struktur (1 Zeile), Erklärung (2 Zeilen), Kernaussagen (2 Zeilen)
- Rechte Seite: gleiche Struktur
- Detailblock: max. 3 Zeilen
- Zusammenfassung: 4 Punkte

REGELN:
- max. 40 Zeichen pro Zeile
- kurze Aussagen
- keine Layoutänderungen
- SVG-Texte nie quetschen
- Sonderzeichen in SVG-Texten immer korrekt escapen
- deutsche Umlaute in Darstellungen korrekt verwenden

---

## 🔄 Prozess – Master-Prompt

Erstelle eine didaktisch hochwertige SVG-Prozessgrafik.

INHALT:
- 5 Schritte, je max. 2 Zeilen
- Untere Boxen: max. 2 Zeilen
- Zusammenfassung: 4–6 Punkte

REGELN:
- kurze Verben
- keine langen Sätze
- keine 3. Zeile unten
- Sonderzeichen in SVG-Texten immer korrekt escapen
- deutsche Umlaute in Darstellungen korrekt verwenden

---

## 🧠 Konzept – Master-Prompt

Erstelle eine didaktisch hochwertige SVG-Konzeptgrafik.

INHALT:
- Zentrum: Begriff + 2 Zeilen
- 4 Boxen: je max. 3 Zeilen
- Merksatz
- Zusammenfassung

REGELN:
- einfache Sprache
- max. 40 Zeichen pro Zeile
- Sonderzeichen in SVG-Texten immer korrekt escapen
- deutsche Umlaute in Darstellungen korrekt verwenden

---

## 🇨🇭 Sprachregel für SVG-Texte (de-CH)

Texte in den Darstellungen sollen die de-CH-spezifischen Umlaute **ä, ö, ü** korrekt enthalten.

Pflichtregeln:
- Umlaute **nicht** durch ae, oe, ue ersetzen, wenn normaler Fliesstext oder didaktischer Text dargestellt wird
- ä, ö und ü direkt verwenden
- ss statt ß verwenden
- Fachbegriffe und Merksätze in natürlichem Deutsch (Schweiz) formulieren

Beispiele:
- „Zugriff über Index“
- „grössere Werte zählen“
- „gültiger Zugriff“
- „äusserer Bereich“
- „Lösung überprüfen“

Hinweis:
- In Code-Beispielen nur dann Umlaute verwenden, wenn sie didaktisch sinnvoll und im Unterrichtskontext gewünscht sind
- In normalen Beschriftungen, Erklärungen, Merksätzen und Zusammenfassungen sollen Umlaute korrekt dargestellt werden

---

## ✅ Verbindliche SVG-Regel: Sonderzeichen escapen

In SVG-Texten müssen Sonderzeichen immer XML-konform escaped werden.

Pflichtregeln:
- `<` wird zu `&lt;`
- `>` wird zu `&gt;`
- `&` wird zu `&amp;`

Beispiele:
- `i < zahlen.length` → `i &lt; zahlen.length`
- `a > b` → `a &gt; b`
- `A & B` → `A &amp; B`

Diese Regel gilt immer:
- in Text-Elementen
- in Code-Beispielen
- in Merksätzen
- in Zusammenfassungen

---

## 🔍 Automatische SVG-Validation (Pflicht)

Jede erzeugte SVG muss **automatisch validiert** werden, bevor sie ausgeliefert wird.

Zu prüfen:
- ist die SVG wohlgeformtes XML?
- gibt es unescaped Sonderzeichen?
- gibt es kaputte Textknoten?
- gibt es Platzhalterreste?
- sind Umlaute korrekt enthalten?
- ist die Datei im Viewer darstellbar?

Wenn Fehler gefunden werden:
1. Fehler korrigieren
2. SVG erneut validieren
3. erst dann ausliefern

Die Validation ist **kein optionaler Schritt**, sondern Teil des Standard-Workflows.

---

## ⚙️ Auto-Refinement

Aufgabe:
SVG prüfen und verbessern.

Korrigieren:
- Textüberlauf
- Abstände
- Lesbarkeit
- XML-/Escaping-Fehler
- fehlerhafte oder ersetzte Umlaute

Regeln:
- Layout nicht ändern
- Inhalte behalten
- nach jeder Korrektur erneut validieren

---

## 💡 Best Practice

- immer Zeilen umbrechen
- nie Text quetschen
- zuerst verschieben, dann skalieren
- SVG vor Auslieferung immer validieren
- keine SVG ohne erfolgreichen Validierungscheck ausgeben
- in normalen Darstellungen ä, ö und ü korrekt verwenden

---

## 🧾 Kompakt-Prompt für künftige SVG-Erstellung

Erstelle die Grafik mit dem passenden SVG-Template und halte dich strikt an das SVG-Playbook.

WICHTIGE REGELN:
- keine Layoutänderungen
- kurze Zeilen
- max. Zeichenzahl pro Bereich einhalten
- Text nie quetschen
- normale Darstellungstexte in Deutsch (Schweiz)
- ä, ö und ü korrekt verwenden
- ss statt ß verwenden
- alle Sonderzeichen in SVG-Texten XML-konform escapen:
  - `<` → `&lt;`
  - `>` → `&gt;`
  - `&` → `&amp;`

PFLICHT NACH DER ERSTELLUNG:
- führe automatisch eine SVG-Validation durch
- prüfe XML-Wohlgeformtheit
- prüfe unescaped Sonderzeichen
- prüfe Platzhalterreste
- prüfe korrekte Umlaute
- prüfe Renderbarkeit
- korrigiere Fehler automatisch
- liefere nur die validierte Endversion aus
