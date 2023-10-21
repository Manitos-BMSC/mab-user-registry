package bo.edu.ucb.mabuserregistry.bl;

import bo.edu.ucb.mabuserregistry.dao.*;
import bo.edu.ucb.mabuserregistry.dto.DoctorDto;
import bo.edu.ucb.mabuserregistry.dto.FileDto;
import bo.edu.ucb.mabuserregistry.dto.PatientDto;
import bo.edu.ucb.mabuserregistry.repository.*;
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
    private FilesPacientRepository filesPacientRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private S3ObjectRepository s3ObjectRepository;




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
        FileDto imageRes = fileUploaderService.uploadFile(image, bucketImage, false);
        logger.info("Saving image on database");

        FilesPacient filesPacient = new FilesPacient();
        filesPacient.setFileDate(new Date());
        filesPacient.setStatus(true);
        S3Object imageS3 = s3ObjectRepository.findById(imageRes.getS3ObjectId()).get();
        filesPacient.setS3Object(imageS3);
        filesPacient.setPacient(pacient);
        filesPacientRepository.save(filesPacient);



        logger.info("Uploading clinic history to bucket: " + bucketDocuments);
        FileDto clinicHistoryRes = fileUploaderService.uploadFile(clinicHistory, bucketDocuments, false);

        logger.info("Saving clinic history on database");
        FilesPacient filesPacient2 = new FilesPacient();
        filesPacient2.setFileDate(new Date());
        filesPacient2.setStatus(true);
        S3Object clinicHistoryS3 = s3ObjectRepository.findById(clinicHistoryRes.getS3ObjectId()).get();
        filesPacient2.setS3Object(clinicHistoryS3);
        filesPacient2.setPacient(pacient);
        filesPacientRepository.save(filesPacient2);

        logger.info("Uploading video to bucket: " + bucketVideo);
        FileDto videoRes = fileUploaderService.uploadFile(participationVideo, bucketVideo, false);
        logger.info("Saving video on database");
        FilesPacient filesPacient3 = new FilesPacient();
        filesPacient3.setFileDate(new Date());
        filesPacient3.setStatus(true);
        S3Object videoS3 = s3ObjectRepository.findById(videoRes.getS3ObjectId()).get();
        filesPacient3.setS3Object(videoS3);
        filesPacient3.setPacient(pacient);
        filesPacientRepository.save(filesPacient3);

        logger.info("Uploading personal document to bucket: " + personalInfo);
        FileDto personalDocumentFile = fileUploaderService.uploadFile(personalDocument, personalInfo, false);
        logger.info("Saving personal document on database");
        FilesPacient filesPacient4 = new FilesPacient();
        filesPacient4.setFileDate(new Date());
        filesPacient4.setStatus(true);
        S3Object personalDocumentS3 = s3ObjectRepository.findById(personalDocumentFile.getS3ObjectId()).get();
        filesPacient4.setS3Object(personalDocumentS3);
        filesPacient4.setPacient(pacient);
        filesPacientRepository.save(filesPacient4);

        return patientDto;
    }

    public DoctorDto createDoctor(DoctorDto doctorDto) {
        //TODO: Implementar la lógica de negocio para la creacion de nuevo doctor
        return doctorDto;
    }

}
