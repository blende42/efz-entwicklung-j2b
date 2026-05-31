package ch.allianz.youngoitv.lager.repository;

import ch.allianz.youngoitv.lager.domain.Produkt;
import ch.allianz.youngoitv.lager.domain.ProduktStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProduktRepository extends JpaRepository<Produkt, Long> {

    List<Produkt> findByStatus(ProduktStatus status);
}
