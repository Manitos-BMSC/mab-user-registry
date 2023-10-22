package bo.edu.ucb.mabuserregistry.bl;

import bo.edu.ucb.mabuserregistry.dao.Doctor;
import bo.edu.ucb.mabuserregistry.dto.DoctorDto;
import bo.edu.ucb.mabuserregistry.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bo.edu.ucb.mabuserregistry.repository.DoctorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorBl {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PersonRepository personRepository;

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


}
