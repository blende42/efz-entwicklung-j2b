package ch.allianz.youngoitv.shop.api;

import ch.allianz.youngoitv.shop.domain.Kategorie;
import ch.allianz.youngoitv.shop.domain.Kunde;
import ch.allianz.youngoitv.shop.domain.Produkt;
import ch.allianz.youngoitv.shop.repository.BestellpositionRepository;
import ch.allianz.youngoitv.shop.repository.BestellungRepository;
import ch.allianz.youngoitv.shop.repository.KategorieRepository;
import ch.allianz.youngoitv.shop.repository.KundeRepository;
import ch.allianz.youngoitv.shop.repository.ProduktRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BestellungIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private KategorieRepository kategorieRepository;

    @Autowired
    private ProduktRepository produktRepository;

    @Autowired
    private KundeRepository kundeRepository;

    @Autowired
    private BestellungRepository bestellungRepository;

    @Autowired
    private BestellpositionRepository bestellpositionRepository;

    private Kunde kunde;
    private Produkt produkt;

    @BeforeEach
    void datenVorbereiten() {
        bestellpositionRepository.deleteAll();
        bestellungRepository.deleteAll();
        produktRepository.deleteAll();
        kundeRepository.deleteAll();
        kategorieRepository.deleteAll();

        Kategorie kategorie = kategorieRepository.save(new Kategorie("Hardware"));
        produkt = produktRepository.save(new Produkt("Tastatur", 49.90, 5, kategorie.getId()));
        kunde = kundeRepository.save(new Kunde("Ada Lovelace", "ada@example.com"));
    }

    @Test
    void erstelltBestellungUndReduziertBestand() throws Exception {
        String json = """
                {
                  "kundeId": %d,
                  "positionen": [
                    {
                      "produktId": %d,
                      "menge": 2
                    }
                  ]
                }
                """.formatted(kunde.getId(), produkt.getId());

        mockMvc.perform(post("/bestellungen")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.kundeId").value(kunde.getId()))
                .andExpect(jsonPath("$.kundenName").value("Ada Lovelace"))
                .andExpect(jsonPath("$.positionen[0].produktName").value("Tastatur"))
                .andExpect(jsonPath("$.positionen[0].menge").value(2));

        Produkt gespeichertesProdukt = produktRepository.findById(produkt.getId()).orElseThrow();
        assertThat(gespeichertesProdukt.getBestand()).isEqualTo(3);
        assertThat(bestellungRepository.findAll()).hasSize(1);
        assertThat(bestellpositionRepository.findAll()).hasSize(1);
    }

    @Test
    void kannBestellungNachDemErstellenLaden() throws Exception {
        String json = """
                {
                  "kundeId": %d,
                  "positionen": [
                    {
                      "produktId": %d,
                      "menge": 1
                    }
                  ]
                }
                """.formatted(kunde.getId(), produkt.getId());

        String response = mockMvc.perform(post("/bestellungen")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Long bestellungId = Long.valueOf(response.replaceAll(".*\"id\":(\\d+).*", "$1"));

        mockMvc.perform(get("/bestellungen/{id}", bestellungId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(bestellungId))
                .andExpect(jsonPath("$.positionen[0].produktId").value(produkt.getId()));
    }

    @Test
    void gibtNotFoundBeiUnbekanntemKundenZurueck() throws Exception {
        String json = """
                {
                  "kundeId": 999999,
                  "positionen": [
                    {
                      "produktId": %d,
                      "menge": 1
                    }
                  ]
                }
                """.formatted(produkt.getId());

        mockMvc.perform(post("/bestellungen")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void gibtNotFoundBeiUnbekanntemProduktZurueck() throws Exception {
        String json = """
                {
                  "kundeId": %d,
                  "positionen": [
                    {
                      "produktId": 999999,
                      "menge": 1
                    }
                  ]
                }
                """.formatted(kunde.getId());

        mockMvc.perform(post("/bestellungen")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void gibtBadRequestBeiZuWenigBestandZurueck() throws Exception {
        String json = """
                {
                  "kundeId": %d,
                  "positionen": [
                    {
                      "produktId": %d,
                      "menge": 99
                    }
                  ]
                }
                """.formatted(kunde.getId(), produkt.getId());

        mockMvc.perform(post("/bestellungen")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists());

        Produkt gespeichertesProdukt = produktRepository.findById(produkt.getId()).orElseThrow();
        assertThat(gespeichertesProdukt.getBestand()).isEqualTo(5);
    }

    @Test
    void gibtBadRequestWennGleichesProduktMehrfachZuVielBestandBraucht() throws Exception {
        String json = """
                {
                  "kundeId": %d,
                  "positionen": [
                    {
                      "produktId": %d,
                      "menge": 3
                    },
                    {
                      "produktId": %d,
                      "menge": 3
                    }
                  ]
                }
                """.formatted(kunde.getId(), produkt.getId(), produkt.getId());

        mockMvc.perform(post("/bestellungen")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists());

        Produkt gespeichertesProdukt = produktRepository.findById(produkt.getId()).orElseThrow();
        assertThat(gespeichertesProdukt.getBestand()).isEqualTo(5);
        assertThat(bestellungRepository.findAll()).isEmpty();
        assertThat(bestellpositionRepository.findAll()).isEmpty();
    }

    @Test
    void gibtBadRequestBeiUngueltigemRequestZurueck() throws Exception {
        String json = """
                {
                  "kundeId": %d,
                  "positionen": [
                    {
                      "produktId": %d,
                      "menge": 0
                    }
                  ]
                }
                """.formatted(kunde.getId(), produkt.getId());

        mockMvc.perform(post("/bestellungen")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists());
    }
}
