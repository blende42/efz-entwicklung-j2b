package ch.allianz.youngoitv.lager.api;

import ch.allianz.youngoitv.lager.domain.Produkt;
import ch.allianz.youngoitv.lager.domain.ProduktStatus;
import ch.allianz.youngoitv.lager.repository.ProduktRepository;
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
class ProduktIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProduktRepository produktRepository;

    @BeforeEach
    void datenVorbereiten() {
        produktRepository.deleteAll();
    }

    @Test
    void gibtProduktlisteZurueck() throws Exception {
        produktRepository.save(new Produkt("Tastatur", 49.90, ProduktStatus.AKTIV));

        mockMvc.perform(get("/produkte"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value("Tastatur"));
    }

    @Test
    void erstelltProduktMitGueltigemRequest() throws Exception {
        String json = """
                {
                  "name": "Maus",
                  "preis": 24.90,
                  "status": "AKTIV"
                }
                """;

        mockMvc.perform(post("/produkte")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Maus"))
                .andExpect(jsonPath("$.status").value("AKTIV"));

        assertThat(produktRepository.findAll())
                .anyMatch(produkt -> produkt.getName().equals("Maus"));
    }

    @Test
    void lehntUngueltigenPostAb() throws Exception {
        String json = """
                {
                  "name": "",
                  "preis": 24.90,
                  "status": "AKTIV"
                }
                """;

        mockMvc.perform(post("/produkte")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void findetProduktMitGueltigerId() throws Exception {
        Produkt produkt = produktRepository.save(new Produkt("Monitor", 179.00, ProduktStatus.AKTIV));

        mockMvc.perform(get("/produkte/{id}", produkt.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(produkt.getId()))
                .andExpect(jsonPath("$.name").value("Monitor"));
    }

    @Test
    void gibtNotFoundBeiUnbekannterIdZurueck() throws Exception {
        mockMvc.perform(get("/produkte/{id}", 999999L))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").exists());
    }
}
