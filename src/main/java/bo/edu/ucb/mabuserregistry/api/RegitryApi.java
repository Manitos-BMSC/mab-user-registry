package bo.edu.ucb.mabuserregistry.api;

import bo.edu.ucb.mabuserregistry.bl.RegistryBl;
import bo.edu.ucb.mabuserregistry.dto.DoctorDto;
import bo.edu.ucb.mabuserregistry.dto.PatientDto;
import bo.edu.ucb.mabuserregistry.dto.ResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "api/v1/registry")
public class RegitryApi {

    private final RegistryBl registryBl;
    private final Logger logger = LoggerFactory.getLogger(RegitryApi.class);

    @Autowired
    public RegitryApi(RegistryBl registryBl) {
        this.registryBl = registryBl;
    }

    @PostMapping("/patient")
    public ResponseEntity<ResponseDto<PatientDto>> createPatient(
            @RequestParam("data") String patientDtoJson,
            @RequestParam("image") MultipartFile image,
            @RequestParam("clinicHistory") MultipartFile clinicHistory,
            @RequestParam("participationVideo") MultipartFile participationVideo,
            @RequestParam("personalDocument") MultipartFile personalDocument
    ) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        PatientDto patientDto = objectMapper.readValue(patientDtoJson, PatientDto.class);
        PatientDto patientResponse = registryBl.createPatient(patientDto);
        int code = 200;
        String message = "OK";
        Boolean success = true;
        ResponseDto<PatientDto> response = new ResponseDto<>(success, message, code, patientResponse);
        System.out.println("response: " + response);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/doctor")
    public ResponseEntity<ResponseDto<DoctorDto>> createDoctor(
        @RequestParam("data") DoctorDto doctorDto,
        @RequestParam("doctorPicture") MultipartFile doctorPicture,
        @RequestParam("personalDocument") MultipartFile personalDocument
    ) {
        DoctorDto doctorResponse = registryBl.createDoctor(doctorDto);
        int code = 200;
        String message = "OK";
        Boolean success = true;
        ResponseDto<DoctorDto> response = new ResponseDto<>(success, message, code, doctorResponse);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
