package bo.edu.ucb.mabuserregistry.api;


import bo.edu.ucb.mabuserregistry.bl.DoctorBl;
import bo.edu.ucb.mabuserregistry.dto.DoctorDto;
import bo.edu.ucb.mabuserregistry.dto.ResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/doctor")
public class DoctorApi {

    @Autowired
    private DoctorBl doctorBl;

    private final Logger logger = LoggerFactory.getLogger(RegitryApi.class);

    @GetMapping("/{doctorId}")
    public ResponseEntity<ResponseDto<DoctorDto>> getDoctor(
            @PathVariable("doctorId") int doctorId
    ) throws JsonProcessingException {
        logger.info("doctorId: " + doctorId);
        DoctorDto doctorResponse = doctorBl.getDoctor(doctorId);
        int code = 200;
        String message = "OK";
        Boolean success = true;
        ResponseDto<DoctorDto> response = new ResponseDto<>(success, message, code, doctorResponse);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/doctors")
    public ResponseEntity<ResponseDto<List<DoctorDto>>> getDoctors(){
        List<DoctorDto> doctors = doctorBl.getDoctors();
        int code = 200;
        String message = "OK";
        Boolean success = true;
        ResponseDto<List<DoctorDto>> response = new ResponseDto<>(success, message, code, doctors);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
