# Reflexion – REST-Lagerverwaltung professionell erweitern

## Architektur

- Warum hast du Controller, Service und Repository getrennt?
- Welche Klasse kennt HTTP?
- Welche Klasse kennt die Speicherstruktur?
- Welche Klasse enthält fachliche Abläufe?

---

## DTOs und JSON

- Warum gibst du nicht direkt `Produkt` als REST-Response zurück?
- Welche Felder enthält dein Ausgabe-DTO?
- Welche Felder enthält dein Eingabe-DTO?
- Wo findet das Mapping zwischen Fachobjekt und DTO statt?

---

## Enums

- Warum ist `ProduktStatus` besser als ein String?
- Welche Statuswerte sind in deinem Projekt erlaubt?
- Wo wird der Status im REST-JSON sichtbar?
- Wo würdest du keinen neuen Status ergänzen, obwohl es technisch möglich wäre?

---

## Collections und Streams

- Welche Collection verwendest du für Produkte?
- Wo setzt du Streams ein?
- Warum ist der Stream-Code dort lesbar?
- Wo wäre eine klassische Schleife klarer?

---

## Bruno

- Welche Bruno-Requests zeigen die Pflichtfunktionen?
- Welcher Request war beim Debuggen am hilfreichsten?
- Welche Antwortstruktur erwartest du bei `GET /produkte`?
- Wie prüfst du die Statusänderung?

---

## Lernen und nächster Schritt

- Welche Stelle war am schwierigsten?
- Welche Entscheidung war im Nachhinein richtig?
- Welche Stelle würdest du beim nächsten Projekt früher strukturieren?
- Welche kleine Verbesserung würdest du als Nächstes umsetzen?
