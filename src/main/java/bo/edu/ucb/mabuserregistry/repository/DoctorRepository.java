package bo.edu.ucb.mabuserregistry.repository;

import bo.edu.ucb.mabuserregistry.dao.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Doctor findByIdEquals(int doctorId);

}
