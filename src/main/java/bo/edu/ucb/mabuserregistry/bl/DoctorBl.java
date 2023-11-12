package bo.edu.ucb.mabuserregistry.bl;

import bo.edu.ucb.mabuserregistry.dao.Doctor;
import bo.edu.ucb.mabuserregistry.dao.HospitalDoctor;
import bo.edu.ucb.mabuserregistry.dto.DoctorDto;
import bo.edu.ucb.mabuserregistry.dto.HospitalDto;
import bo.edu.ucb.mabuserregistry.repository.HospitalDoctorRepository;
import bo.edu.ucb.mabuserregistry.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bo.edu.ucb.mabuserregistry.repository.DoctorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorBl {

    @Autowired
    private final DoctorRepository doctorRepository;

    @Autowired
    private final PersonRepository personRepository;

    @Autowired
    private final HospitalDoctorRepository hospitalDoctorRepository;

    public DoctorBl(DoctorRepository doctorRepository, PersonRepository personRepository, HospitalDoctorRepository hospitalDoctorRepository) {
        this.doctorRepository = doctorRepository;
        this.personRepository = personRepository;
        this.hospitalDoctorRepository = hospitalDoctorRepository;
    }
    public DoctorDto getDoctor (int doctorId) {
        Doctor doctorEntity = doctorRepository.findByIdEquals(doctorId);
        DoctorDto doctor = new DoctorDto();
        doctor.setDoctorId(doctorEntity.getId());
        doctor.setName(doctorEntity.getPerson().getName());
        doctor.setLastName(doctorEntity.getPerson().getLastname());
        doctor.setSpeciality(doctorEntity.getMedicalSpeciality());
        doctor.setPhone(doctorEntity.getPerson().getPhoneNumber());
        return doctor;
    }

    public List<DoctorDto> getDoctors (){
        List<Doctor> doctors = doctorRepository.findAll();
        //mapping doctors to doctorsDto
        return doctors.stream().map(doctor -> {
            DoctorDto doctorDto = new DoctorDto();
            doctorDto.setDoctorId(doctor.getId());
            doctorDto.setName(doctor.getPerson().getName());
            doctorDto.setLastName(doctor.getPerson().getLastname());
            doctorDto.setSpeciality(doctor.getMedicalSpeciality());
            doctorDto.setPhone(doctor.getPerson().getPhoneNumber());
            return doctorDto;
        }).collect(Collectors.toList());
    }

    public List<HospitalDto> getHospitalsByDoctor(Long doctorId){
        List<HospitalDoctor> hospitalDoctors = hospitalDoctorRepository.findAllByDoctorId(doctorId);
        List<HospitalDto> hospitals = new ArrayList<>();

        for (HospitalDoctor hospitalDoctor : hospitalDoctors) {
            HospitalDto hospitalDto = new HospitalDto();
            hospitalDto.setIdHospital(hospitalDoctor.getHospital().getIdHospital());
            hospitalDto.setName(hospitalDoctor.getHospital().getName());
            hospitalDto.setContactNumber(hospitalDoctor.getHospital().getContactNumber());
            hospitalDto.setDirection(hospitalDoctor.getHospital().getDirection());
            hospitals.add(hospitalDto);
        }

        return hospitals;
    }


}
