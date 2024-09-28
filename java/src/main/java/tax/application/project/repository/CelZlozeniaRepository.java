package tax.application.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tax.application.project.model.CelZlozenia;

import java.util.Optional;

public interface CelZlozeniaRepository extends JpaRepository<CelZlozenia, Long> {
    Optional<CelZlozenia> findById(Long celZlozeniaId);
}
