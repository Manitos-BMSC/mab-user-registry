package bo.edu.ucb.mabuserregistry.api;


import bo.edu.ucb.mabuserregistry.bl.DoctorBl;
import bo.edu.ucb.mabuserregistry.dto.DoctorDto;
import bo.edu.ucb.mabuserregistry.dto.HospitalDto;
import bo.edu.ucb.mabuserregistry.dto.ResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/doctor")
public class DoctorApi {

    @Autowired
    private DoctorBl doctorBl;

    private final Logger logger = LoggerFactory.getLogger(RegitryApi.class);

    @GetMapping("/{doctorId}")
    public ResponseDto<DoctorDto> getDoctor(
            @PathVariable("doctorId") int doctorId
    ) throws JsonProcessingException {
        logger.info("doctorId: " + doctorId);
        DoctorDto doctorResponse = doctorBl.getDoctor(doctorId);
        int code = 200;
        String message = "OK";
        Boolean success = true;
        ResponseDto<DoctorDto> response = new ResponseDto<>(success, message, code, doctorResponse);
        return response;
    }

    @GetMapping("/doctors")
    public ResponseDto<List<DoctorDto>> getDoctors(){
        List<DoctorDto> doctors = doctorBl.getDoctors();
        int code = 200;
        String message = "OK";
        Boolean success = true;
        ResponseDto<List<DoctorDto>> response = new ResponseDto<>(success, message, code, doctors);
        return response;
    }

    @GetMapping("/{doctorId}/hospitals")
    public ResponseDto<List<HospitalDto>> getHospitalsByDoctor(
            @PathVariable Long doctorId
    ){
        logger.info("getHospitalsByDoctor");
        List<HospitalDto> hospitals = doctorBl.getHospitalsByDoctor(doctorId);
        int code = 200;
        String message = "OK";
        Boolean success = true;
        ResponseDto<List<HospitalDto>> response = new ResponseDto<>(success, message, code, hospitals);
        System.out.println("response: " + response);
        return response;
    }
}
