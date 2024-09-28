package tax.application.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tax.application.project.model.Naglowek;

import java.util.Optional;

public interface NaglowekRepository extends JpaRepository<Naglowek, Long> {
    Optional<Naglowek> findById(Long naglowekId);
}
