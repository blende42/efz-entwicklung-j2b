package ch.allianz.youngoitv.shop.repository;

import ch.allianz.youngoitv.shop.domain.Kategorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KategorieRepository extends JpaRepository<Kategorie, Long> {
}
