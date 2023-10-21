package bo.edu.ucb.mabuserregistry.bl;

import bo.edu.ucb.mabuserregistry.dao.City;
import bo.edu.ucb.mabuserregistry.dao.Pacient;
import bo.edu.ucb.mabuserregistry.dao.Person;
import bo.edu.ucb.mabuserregistry.dto.DoctorDto;
import bo.edu.ucb.mabuserregistry.dto.PatientDto;
import bo.edu.ucb.mabuserregistry.repository.CityRepository;
import bo.edu.ucb.mabuserregistry.repository.PacientRepository;
import bo.edu.ucb.mabuserregistry.repository.PersonRepository;
import bo.edu.ucb.mabuserregistry.service.FileUploaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
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

    @Autowired
    private CityRepository cityRepository;




    @Value("${minio.bucket.image}")
    private String bucketImage;

    @Value("${minio.bucket.video}")
    private String bucketVideo;

    @Value("${minio.bucket.documents}")
    private String bucketDocuments;

    @Value("${minio.bucket.name}")
    private String personalInfo;

    Logger logger = Logger.getLogger(RegistryBl.class.getName());


    public PatientDto createPatient(PatientDto patientDto, MultipartFile image, MultipartFile clinicHistory, MultipartFile participationVideo, MultipartFile personalDocument) {
        logger.info("Saving patient on database");
        person.setIdKeycloack("1"); //TODO put the keycloack id
        Optional<City> pacientCity = cityRepository.findById(patientDto.getCityId());
        person.setCity(pacientCity.get());
        person.setName(patientDto.getName());
        person.setLastname(patientDto.getLastName());
        person.setUserMail(patientDto.getEmail());
        person.setUsername(patientDto.getUsername());
        person.setPhoneNumber(patientDto.getPhone());
        person.setDocumentType(patientDto.getPassport());
        person.setDocumentNumber(patientDto.getDocumentNumber());
        person.setBirthDate(patientDto.getBirthDate());
        person.setAddress(patientDto.getAddress());
        person.setPersonalDocument(personalDocument.getOriginalFilename());
        person.setGender(patientDto.getMale());
        person.setTxUser("1");
        person.setTxHost("localhost");
        person.setTxDate(new Date());
        person.setStatus(true);
        logger.info(patientDto.toString());
        personRepository.save(person);
        logger.info("Person saved on database");
        pacient.setPerson(person);
        logger.info("Saving pacient on database");
        logger.info(person.toString());
        pacient.setEmergencyPhone(patientDto.getEmergencyPhone());
        pacient.setPacientStatus("Nuevo");
        pacient.setStatus(true);
        pacientRepository.save(pacient);
        logger.info("Pacient saved on database");
        logger.info("Uploading image to bucket: " + bucketImage);
        fileUploaderService.uploadFile(image, bucketImage, false);
        logger.info("Uploading clinic history to bucket: " + bucketDocuments);
        fileUploaderService.uploadFile(clinicHistory, bucketDocuments, false);
        logger.info("Uploading video to bucket: " + bucketVideo);
        fileUploaderService.uploadFile(participationVideo, bucketVideo, false);
        logger.info("Uploading personal document to bucket: " + personalInfo);
        fileUploaderService.uploadFile(personalDocument, personalInfo, false);
        return patientDto;
    }

    public DoctorDto createDoctor(DoctorDto doctorDto) {
        //TODO: Implementar la lógica de negocio para la creacion de nuevo doctor
        return doctorDto;
    }

}
