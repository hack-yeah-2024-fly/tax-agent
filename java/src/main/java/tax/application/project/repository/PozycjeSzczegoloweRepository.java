package tax.application.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tax.application.project.model.PozycjeSzczegolowe;

import java.util.Optional;

public interface PozycjeSzczegoloweRepository extends JpaRepository<PozycjeSzczegolowe, Long> {
    Optional<PozycjeSzczegolowe> findById(Long pozycjeSzczegoloweId);
}
