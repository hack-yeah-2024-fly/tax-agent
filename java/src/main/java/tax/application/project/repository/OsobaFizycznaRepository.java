package tax.application.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tax.application.project.model.OsobaFizyczna;

import java.util.Optional;

@Repository
public interface OsobaFizycznaRepository extends JpaRepository<OsobaFizyczna, Long> {
    Optional<OsobaFizyczna> findById(Long osobaFizycznaId);
}
