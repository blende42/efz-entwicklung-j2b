package ch.allianz.youngoitv.shop.repository;

import ch.allianz.youngoitv.shop.domain.Bestellposition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BestellpositionRepository extends JpaRepository<Bestellposition, Long> {

    List<Bestellposition> findByBestellungId(Long bestellungId);
}
