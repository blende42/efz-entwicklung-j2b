# Kompakt-Prompt – SVG-Erstellung (EFZ, de-CH)

Erstelle die Grafik mit dem passenden SVG-Template und halte dich strikt an das SVG-Playbook.

## WICHTIGE REGELN
- keine Layoutänderungen
- kurze Zeilen (max. 40 Zeichen)
- Text nie quetschen
- Inhalte klar und didaktisch formulieren

## 🇨🇭 Sprache (de-CH)
- ä, ö und ü korrekt verwenden (nicht ae/oe/ue)
- ss statt ß
- klare, einfache Fachsprache

## XML / SVG-Regeln (Pflicht)
- Sonderzeichen immer escapen:
  - `<` → `&lt;`
  - `>` → `&gt;`
  - `&` → `&amp;`
- keine kaputten Textknoten
- keine Platzhalterreste im Output

## PFLICHT: AUTOMATISCHE SVG-VALIDATION
Nach der Erstellung IMMER prüfen:
- ist die SVG wohlgeformtes XML?
- sind alle Sonderzeichen korrekt escaped?
- sind Umlaute korrekt dargestellt (ä, ö, ü)?
- gibt es Textüberlauf?
- ist die SVG im Viewer darstellbar?

Wenn Fehler vorhanden:
1. automatisch korrigieren
2. erneut validieren
3. erst dann ausgeben

## OUTPUT
- nur die finale, validierte SVG-Datei ausgeben
