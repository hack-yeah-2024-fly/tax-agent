package tax.application.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tax.application.project.model.AdresPol;
import tax.application.project.model.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
}
