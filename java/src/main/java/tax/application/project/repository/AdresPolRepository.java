package tax.application.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tax.application.project.model.AdresPol;

import java.util.Optional;

@Repository
public interface AdresPolRepository extends JpaRepository<AdresPol, Long> {
    Optional<AdresPol> findById(Long adresPolId);
}
