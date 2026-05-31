package ch.allianz.youngoitv.shop.repository;

import ch.allianz.youngoitv.shop.domain.Bestellung;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BestellungRepository extends JpaRepository<Bestellung, Long> {
}
