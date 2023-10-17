package bo.edu.ucb.mabuserregistry.repository;

import bo.edu.ucb.mabuserregistry.dao.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PersonRepository extends JpaRepository<Person, Long> {
}
