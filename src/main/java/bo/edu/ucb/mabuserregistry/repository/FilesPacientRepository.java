package bo.edu.ucb.mabuserregistry.repository;

import bo.edu.ucb.mabuserregistry.dao.FilesPacient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilesPacientRepository extends JpaRepository<FilesPacient, Long> {
}
