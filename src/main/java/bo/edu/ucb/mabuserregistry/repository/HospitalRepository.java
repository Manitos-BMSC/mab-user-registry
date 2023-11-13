package bo.edu.ucb.mabuserregistry.repository;

import bo.edu.ucb.mabuserregistry.dao.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    List<Hospital> findAllByStatusIsTrue();

}
