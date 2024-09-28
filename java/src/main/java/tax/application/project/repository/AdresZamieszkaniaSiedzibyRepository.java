package tax.application.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tax.application.project.model.AdresZamieszkaniaSiedziby;

import java.util.Optional;

public interface AdresZamieszkaniaSiedzibyRepository extends JpaRepository<AdresZamieszkaniaSiedziby, Long> {
    Optional<AdresZamieszkaniaSiedziby> findById(Long adresZamieszkaniaSiedzibyId);
}
