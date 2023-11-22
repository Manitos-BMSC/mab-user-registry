package bo.edu.ucb.mabuserregistry.bl;

import bo.edu.ucb.mabuserregistry.dao.*;
import bo.edu.ucb.mabuserregistry.dto.DoctorDto;
import bo.edu.ucb.mabuserregistry.dto.FileDto;
import bo.edu.ucb.mabuserregistry.dto.PatientDto;
import bo.edu.ucb.mabuserregistry.repository.*;
import bo.edu.ucb.mabuserregistry.service.FileUploaderService;
import bo.edu.ucb.mabuserregistry.service.KeycloakTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class RegistryBl {

    @Autowired
    FileUploaderService fileUploaderService;

    @Autowired
    private KeycloakTokenService keycloakTokenService;

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

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired HospitalDoctorRepository hospitalDoctorRepository;




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
        logger.info("Getting token");
        Map<String, ?> response = keycloakTokenService.getToken(
                "client_credentials",
                "mab_backend",
                "mzhqeGKq8LiwBb9tQ6q1z4HONF6to3tr"
        );

        String token = "Bearer " + response.get("access_token");


        logger.info("Saving patient on database");
        person.setIdKeycloack(patientDto.getKeycloakId());
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
        Pacient pacientRegistred = pacientRepository.save(pacient);
        patientDto.setPatientId(pacientRegistred.getId());
        logger.info("Pacient saved on database");
        logger.info("Uploading image to bucket: " + bucketImage);
        FileDto imageRes = fileUploaderService.uploadFile(image, bucketImage, false, token);
        logger.info("Saving image on database");

        FilesPacient filesPacient = new FilesPacient();
        filesPacient.setFileDate(new Date());
        filesPacient.setStatus(true);
        S3Object imageS3 = s3ObjectRepository.findById(imageRes.getS3ObjectId()).get();
        filesPacient.setS3Object(imageS3);
        filesPacient.setPacient(pacient);
        filesPacientRepository.save(filesPacient);



        logger.info("Uploading clinic history to bucket: " + bucketDocuments);
        FileDto clinicHistoryRes = fileUploaderService.uploadFile(clinicHistory, bucketDocuments, false, token);

        logger.info("Saving clinic history on database");
        FilesPacient filesPacient2 = new FilesPacient();
        filesPacient2.setFileDate(new Date());
        filesPacient2.setStatus(true);
        S3Object clinicHistoryS3 = s3ObjectRepository.findById(clinicHistoryRes.getS3ObjectId()).get();
        filesPacient2.setS3Object(clinicHistoryS3);
        filesPacient2.setPacient(pacient);
        filesPacientRepository.save(filesPacient2);

        logger.info("Uploading video to bucket: " + bucketVideo);
        FileDto videoRes = fileUploaderService.uploadFile(participationVideo, bucketVideo, false, token);
        logger.info("Saving video on database");
        FilesPacient filesPacient3 = new FilesPacient();
        filesPacient3.setFileDate(new Date());
        filesPacient3.setStatus(true);
        S3Object videoS3 = s3ObjectRepository.findById(videoRes.getS3ObjectId()).get();
        filesPacient3.setS3Object(videoS3);
        filesPacient3.setPacient(pacient);
        filesPacientRepository.save(filesPacient3);

        logger.info("Uploading personal document to bucket: " + personalInfo);
        FileDto personalDocumentFile = fileUploaderService.uploadFile(personalDocument, personalInfo, false, token);
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

    public DoctorDto createDoctor(DoctorDto doctorDto, MultipartFile image) {

        logger.info("Getting token");
        Map<String, ?> response = keycloakTokenService.getToken(
                "client_credentials",
                "mab_backend",
                "mzhqeGKq8LiwBb9tQ6q1z4HONF6to3tr"
        );

        String token = "Bearer " + response.get("access_token");

        logger.info("Saving doctor on database");
        person.setIdKeycloack("2"); //TODO put the keycloack id
        Optional<City> doctorCity = cityRepository.findById(doctorDto.getCityId());
        person.setCity(doctorCity.get());
        person.setName(doctorDto.getName());
        person.setLastname(doctorDto.getLastName());
        person.setUserMail(doctorDto.getEmail());
        person.setUsername(doctorDto.getUsername());
        person.setPhoneNumber(doctorDto.getPhone());
        person.setDocumentType(doctorDto.getPassport());
        person.setDocumentNumber(doctorDto.getDocumentNumber());
        person.setBirthDate(doctorDto.getBirthDate());
        person.setAddress(doctorDto.getAddress());
        person.setPersonalDocument(image.getOriginalFilename());
        person.setGender(doctorDto.getMale());
        person.setTxUser("1");
        person.setTxHost("localhost");
        person.setTxDate(new Date());
        person.setStatus(true);
        logger.info(doctorDto.toString());

        logger.info("Person saved on database");
        logger.info("Uploading image to bucket: " + bucketImage);
        FileDto imageRes = fileUploaderService.uploadFile(image, bucketImage, false, token);

        logger.info("Saving doctor on database");
        Doctor doctor = new Doctor();
        doctor.setPerson(person);
        doctor.setS3ObjectId(imageRes.getS3ObjectId());
        doctor.setLicenseCode(doctorDto.getLicenseCode());
        doctor.setLicenseDueDate(doctorDto.getLicenseDueDate());
        doctor.setLicenseStatus("Activo");
        doctor.setMedicalSpeciality(doctorDto.getSpeciality());
        doctor.setStatus(true);

        personRepository.save(person);
        Doctor savedDoctor = doctorRepository.save(doctor);
        logger.info("Doctor saved on database");
        //TODO: Implementar la lógica de negocio para la creacion de nuevo doctor

        DoctorDto savedDoctorDto = new DoctorDto();
        savedDoctorDto.setDoctorId(savedDoctor.getId());
        savedDoctorDto.setLicenseCode(savedDoctor.getLicenseCode());
        savedDoctorDto.setLicenseDueDate(savedDoctor.getLicenseDueDate());
        savedDoctorDto.setSpeciality(savedDoctor.getMedicalSpeciality());
        savedDoctorDto.setPhone(savedDoctor.getPerson().getPhoneNumber());
        savedDoctorDto.setName(savedDoctor.getPerson().getName());
        savedDoctorDto.setLastName(savedDoctor.getPerson().getLastname());
        savedDoctorDto.setEmail(savedDoctor.getPerson().getUserMail());
        savedDoctorDto.setBirthDate(savedDoctor.getPerson().getBirthDate());
        savedDoctorDto.setMale(savedDoctor.getPerson().isGender());
        savedDoctorDto.setAddress(savedDoctor.getPerson().getAddress());
        savedDoctorDto.setDocumentNumber(savedDoctor.getPerson().getDocumentNumber());
        savedDoctorDto.setPassport(savedDoctor.getPerson().isDocumentType());
        savedDoctorDto.setCityId(savedDoctor.getPerson().getCity().getId());
        savedDoctorDto.setUsername(savedDoctor.getPerson().getUsername());

        HospitalDoctor hospitalDoctor = new HospitalDoctor();
        Hospital hospital = new Hospital();
        hospital.setIdHospital(doctorDto.getHospitalId());
        hospitalDoctor.setDoctor(savedDoctor);
        hospitalDoctor.setHospital(hospital);

        hospitalDoctorRepository.save(hospitalDoctor);

        return savedDoctorDto;
    }

    public Boolean userExist(String userId){
        logger.info("Checking if user exist");
        logger.info("userId: " + userId);
        List<Person> person = personRepository.findAll();

        for (Person actualPerson : person) {
            logger.info("person1.getIdKeycloack(): " + actualPerson.getIdKeycloack());
            if(actualPerson.getIdKeycloack().equals(userId)){
                logger.info("User found");
                logger.info(userId + " is equal to " + actualPerson.getIdKeycloack());
                return true;
            }
        }

        logger.info("User not found");
        return false;
    }

}
