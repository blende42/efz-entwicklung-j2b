---
name: svg-pruefen
description: Verwenden, wenn eine SVG-Grafik neu erstellt, geändert oder in ein Arbeitsblatt eingebunden wurde.
---

# Skill – SVG prüfen

## Zweck

Unterstützt beim Prüfen von SVG-Grafiken für Unterrichtsmaterial.

## Verwenden wenn

Eine SVG-Grafik neu erstellt, geändert oder in ein Arbeitsblatt eingebunden wurde.

## Vorgehen

1. Lies zuerst [AGENTS.md](../../../AGENTS.md), besonders die SVG-Regeln.
2. Identifiziere Template oder Grafiktyp.
3. Prüfe den Lernnutzen der Grafik.
4. Prüfe XML-Wohlgeformtheit, zum Beispiel mit `xmllint --noout`.
5. Rendere die Grafik, wenn ein Renderer verfügbar ist.
6. Prüfe Lesbarkeit, Textüberlauf und Platzhalterreste.
7. Prüfe verknüpfte Markdown-Dateien.

## Ergebnis

Eine kurze Prüfrückmeldung mit Befunden, Verifikation und bekannten Einschränkungen.
