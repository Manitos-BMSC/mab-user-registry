package bo.edu.ucb.mabuserregistry.repository;

import bo.edu.ucb.mabuserregistry.dao.Pacient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PacientRepository extends JpaRepository<Pacient, Long> {
}
