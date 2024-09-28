package tax.application.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tax.application.project.model.KodFormularza;

import java.util.Optional;

public interface KodFormularzaRepository extends JpaRepository<KodFormularza, Long> {
    Optional<KodFormularza> findById(Long kodFormularzaId);
}
