package ch.allianz.youngoitv.shop.repository;

import ch.allianz.youngoitv.shop.domain.Kunde;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KundeRepository extends JpaRepository<Kunde, Long> {
}
