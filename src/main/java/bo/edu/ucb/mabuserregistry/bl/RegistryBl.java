package bo.edu.ucb.mabuserregistry.bl;

import bo.edu.ucb.mabuserregistry.dto.DoctorDto;
import bo.edu.ucb.mabuserregistry.dto.PatientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistryBl {

    @Autowired
    public RegistryBl() {

    }

    public PatientDto createPatient(PatientDto patientDto) {
        //TODO: Implementar la lógica de negocio para la creacion de nuevo paciente
        return patientDto;
    }

    public DoctorDto createDoctor(DoctorDto doctorDto) {
        //TODO: Implementar la lógica de negocio para la creacion de nuevo doctor
        return doctorDto;
    }

}
