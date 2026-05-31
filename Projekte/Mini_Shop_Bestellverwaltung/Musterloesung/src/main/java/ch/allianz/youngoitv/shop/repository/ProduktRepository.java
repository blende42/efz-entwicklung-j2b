package ch.allianz.youngoitv.shop.repository;

import ch.allianz.youngoitv.shop.domain.Produkt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduktRepository extends JpaRepository<Produkt, Long> {
}
