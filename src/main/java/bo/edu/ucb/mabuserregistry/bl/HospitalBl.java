package bo.edu.ucb.mabuserregistry.bl;

import bo.edu.ucb.mabuserregistry.dao.Doctor;
import bo.edu.ucb.mabuserregistry.dao.Hospital;
import bo.edu.ucb.mabuserregistry.dao.HospitalDoctor;
import bo.edu.ucb.mabuserregistry.dto.DoctorDto;
import bo.edu.ucb.mabuserregistry.dto.HospitalDoctorDto;
import bo.edu.ucb.mabuserregistry.dto.HospitalDto;
import bo.edu.ucb.mabuserregistry.repository.DoctorRepository;
import bo.edu.ucb.mabuserregistry.repository.HospitalDoctorRepository;
import bo.edu.ucb.mabuserregistry.repository.HospitalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HospitalBl {

    private final Logger logger = LoggerFactory.getLogger(HospitalBl.class);

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private HospitalDoctorRepository hospitalDoctorRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public HospitalBl(HospitalRepository hospitalRepository, HospitalDoctorRepository hospitalDoctorRepository, DoctorRepository doctorRepository) {
        this.hospitalRepository = hospitalRepository;
        this.hospitalDoctorRepository = hospitalDoctorRepository;
        this.doctorRepository = doctorRepository;
    }

    public HospitalDto postNewHospital(HospitalDto hospitalDto) {
        Hospital hospital = new Hospital();
        hospital.setName(hospitalDto.getName());
        hospital.setContactNumber(hospitalDto.getContactNumber());
        hospital.setDirection(hospitalDto.getDirection());
        hospital.setStatus(true);
        Hospital savedHospital = hospitalRepository.save(hospital);
        return new HospitalDto(savedHospital);
    }

    public HospitalDto updateHospital(Long hospitalId, HospitalDto hospitalDto) {
        Hospital hospital = hospitalRepository.findById(hospitalId).get();
        hospital.setName(hospitalDto.getName());
        hospital.setContactNumber(hospitalDto.getContactNumber());
        hospital.setDirection(hospitalDto.getDirection());
        hospital.setStatus(true);
        Hospital savedHospital = hospitalRepository.save(hospital);
        return new HospitalDto(savedHospital);
    }

    public HospitalDto getHospital(Long hospitalId) {
        Hospital hospital = hospitalRepository.findById(hospitalId).get();
        return new HospitalDto(hospital);
    }

    public List<DoctorDto> getDoctorsForHospital(Long hospitalId){
        List<HospitalDoctor> hospitalDoctors = hospitalDoctorRepository.findAllByHospitalIdHospital(hospitalId);
        List<DoctorDto> doctors = new ArrayList<>();
        for (HospitalDoctor hospitalDoctor : hospitalDoctors) {
            DoctorDto doctorDto = new DoctorDto();
            doctorDto.setDoctorId(hospitalDoctor.getDoctor().getId());
            doctorDto.setName(hospitalDoctor.getDoctor().getPerson().getName());
            doctorDto.setLastName(hospitalDoctor.getDoctor().getPerson().getLastname());
            doctorDto.setSpeciality(hospitalDoctor.getDoctor().getMedicalSpeciality());
            doctorDto.setPhone(hospitalDoctor.getDoctor().getPerson().getPhoneNumber());
            doctors.add(doctorDto);
        }
        return doctors;
    }

    public HospitalDoctorDto addDoctorToHospital(Long hospitalId, Long doctorId) {
        Hospital hospital = hospitalRepository.findById(hospitalId).get();
        Doctor doctor = doctorRepository.findById(doctorId).get();
        if (hospital == null || doctor == null) {
            return null;
        }
        HospitalDoctor hospitalDoctor = new HospitalDoctor();
        hospitalDoctor.setHospital(hospital);
        hospitalDoctor.setDoctor(doctor);
        HospitalDoctor savedHospitalDoctor =  hospitalDoctorRepository.save(hospitalDoctor);
        return new HospitalDoctorDto(savedHospitalDoctor);
    }

}
