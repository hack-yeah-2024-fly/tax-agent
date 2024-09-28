package tax.application.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tax.application.project.model.Podmiot1;

import java.util.Optional;

public interface Podmiot1Repository extends JpaRepository<Podmiot1, Long> {
    Optional<Podmiot1> findById(Long podmiot1Id);
}
