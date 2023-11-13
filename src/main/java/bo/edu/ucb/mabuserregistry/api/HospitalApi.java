package bo.edu.ucb.mabuserregistry.api;

import bo.edu.ucb.mabuserregistry.dto.DoctorDto;
import bo.edu.ucb.mabuserregistry.dto.HospitalDoctorDto;
import bo.edu.ucb.mabuserregistry.dto.HospitalDto;
import bo.edu.ucb.mabuserregistry.dto.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import bo.edu.ucb.mabuserregistry.bl.HospitalBl;

import java.util.List;

@RestController
@RequestMapping("api/v1/hospital")
public class HospitalApi {

    private final Logger logger = LoggerFactory.getLogger(HospitalApi.class);

    @Autowired
    private final HospitalBl hospitalBl;

    public HospitalApi(HospitalBl hospitalBl) {
        this.hospitalBl = hospitalBl;
    }

    @GetMapping("/hospitals")
    public ResponseDto<List<HospitalDto>> getHospitals(){
        logger.info("getHospitals");
        List<HospitalDto> hospitalsResponse = hospitalBl.getHospitals();
        int code = 200;
        String message = "OK";
        Boolean success = true;
        ResponseDto<List<HospitalDto>> response = new ResponseDto<>(success, message, code, hospitalsResponse);
        System.out.println("response: " + response);
        return response;
    }


    @PostMapping()
    public ResponseDto<HospitalDto> postNewHospital(
            @RequestBody HospitalDto hospitalDto
    ){
        logger.info("postNewHospital");
        HospitalDto hospitalResponse = hospitalBl.postNewHospital(hospitalDto);
        int code = 200;
        String message = "OK";
        Boolean success = true;
        ResponseDto<HospitalDto> response = new ResponseDto<>(success, message, code, hospitalResponse);
        System.out.println("response: " + response);
        return response;
    }

    @PutMapping("/{hospitalId}")
    public ResponseDto<HospitalDto> updateHospital(
            @PathVariable Long hospitalId,
            @RequestBody HospitalDto hospitalDto
    ){
        logger.info("updateHospital");
        HospitalDto hospitalResponse = hospitalBl.updateHospital(hospitalId, hospitalDto);
        int code = 200;
        String message = "OK";
        Boolean success = true;
        ResponseDto<HospitalDto> response = new ResponseDto<>(success, message, code, hospitalResponse);
        System.out.println("response: " + response);
        return response;
    }

    @GetMapping("/{hospitalId}")
    public ResponseDto<HospitalDto> getHospital(
            @PathVariable Long hospitalId
    ){
        logger.info("getHospital");
        HospitalDto hospitalResponse = hospitalBl.getHospital(hospitalId);
        int code = 200;
        String message = "OK";
        Boolean success = true;
        ResponseDto<HospitalDto> response = new ResponseDto<>(success, message, code, hospitalResponse);
        System.out.println("response: " + response);
        return response;
    }

    @GetMapping("/{hospitalId}/doctors")
    public ResponseDto<List<DoctorDto>> getDoctorsForHospital(
            @PathVariable Long hospitalId
    ){
        logger.info("getDoctorsForHospital");
        List<DoctorDto> doctorsResponse = hospitalBl.getDoctorsForHospital(hospitalId);
        int code = 200;
        String message = "OK";
        Boolean success = true;
        ResponseDto<List<DoctorDto>> response = new ResponseDto<>(success, message, code, doctorsResponse);
        System.out.println("response: " + response);
        return response;
    }

    @PostMapping("/{hospitalId}/doctor/{doctorId}")
    public ResponseDto<HospitalDoctorDto> addDoctorToHospital(
            @PathVariable Long hospitalId,
            @PathVariable Long doctorId
    ){
        logger.info("addDoctorToHospital");
        HospitalDoctorDto hospitalDoctorResponse = hospitalBl.addDoctorToHospital(hospitalId, doctorId);
        int code = 200;
        String message = "OK";
        Boolean success = true;
        ResponseDto<HospitalDoctorDto> response = new ResponseDto<>(success, message, code, hospitalDoctorResponse);
        System.out.println("response: " + response);
        return response;
    }

}
