package bo.edu.ucb.mabuserregistry.bl;

import bo.edu.ucb.mabuserregistry.dao.Pacient;
import bo.edu.ucb.mabuserregistry.dao.Person;
import bo.edu.ucb.mabuserregistry.dto.DoctorDto;
import bo.edu.ucb.mabuserregistry.dto.PatientDto;
import bo.edu.ucb.mabuserregistry.repository.PacientRepository;
import bo.edu.ucb.mabuserregistry.repository.PersonRepository;
import bo.edu.ucb.mabuserregistry.service.FileUploaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.logging.Logger;

@Service
public class RegistryBl {

    @Autowired
    FileUploaderService fileUploaderService;

    @Autowired
    private Person person;
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private Pacient pacient;
    @Autowired
    private PacientRepository pacientRepository;




    @Value("${minio.bucket.name}")
    private String bucketName;

    Logger logger = Logger.getLogger(RegistryBl.class.getName());


    public PatientDto createPatient(PatientDto patientDto, MultipartFile image, MultipartFile clinicHistory, MultipartFile participationVideo, MultipartFile personalDocument) {
        logger.info("Saving patient on database");
        person.setCityId(1); //TODO need to seach the city id
        person.setName(patientDto.getName());
        person.setLastname(patientDto.getLastName());
        person.setUserMail(patientDto.getEmail());
        person.setPhoneNumber(patientDto.getPhone());
        person.setDocumentType(true);
        person.setDocumentNumber(patientDto.getDocumentNumber());
        person.setBirthDate(patientDto.getBirthDate());
        person.setAddress(patientDto.getAddress());
        person.setPersonalDocument("Document");
        person.setGender(true);
        person.setTxDate(new Date());
        person.setTxHost("localhost");
        person.setTxUser("mab");
        person.setStatus(true);
        logger.info(patientDto.toString());
        Person newPerson = personRepository.save(person);
        logger.info("Person saved on database");
        pacient.setPerson(newPerson);
        pacient.setEmergencyPhone(patientDto.getEmergencyPhone());
        pacient.setPacientStatus("Nuevo");
        pacient.setStatus(true);
        pacientRepository.save(pacient);
        logger.info("Uploading image to bucket: " + bucketName);
//        fileUploaderService.uploadFile(image, bucketName, false);
//        logger.info("Uploading clinic history to bucket: " + bucketName);
//        fileUploaderService.uploadFile(clinicHistory, bucketName, false);
//        logger.info("Uploading video to bucket: " + bucketName);
//        fileUploaderService.uploadFile(participationVideo, bucketName, false);
        return patientDto;
    }

    public DoctorDto createDoctor(DoctorDto doctorDto) {
        //TODO: Implementar la lógica de negocio para la creacion de nuevo doctor
        return doctorDto;
    }

}
