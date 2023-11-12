package bo.edu.ucb.mabuserregistry.repository;

import bo.edu.ucb.mabuserregistry.dao.HospitalDoctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface HospitalDoctorRepository extends JpaRepository<HospitalDoctor, Long> {

    List<HospitalDoctor> findAllByHospitalIdHospital(Long hospitalId);

    List<HospitalDoctor> findAllByDoctorId(Long doctorId);

}
