package bo.edu.ucb.mabuserregistry.dto;

import bo.edu.ucb.mabuserregistry.dao.Hospital;
import bo.edu.ucb.mabuserregistry.dao.HospitalDoctor;

import java.util.ArrayList;
import java.util.List;

public class HospitalDto {

    private int idHospital;
    private String name;
    private String direction;
    private String contactNumber;
    private boolean status;
    private List<HospitalDoctorDto> hospitalDoctors;

    public HospitalDto() {
    }

    public HospitalDto(int idHospital, String name, String direction, String contactNumber, boolean status, List<HospitalDoctorDto> hospitalDoctors) {
        this.idHospital = idHospital;
        this.name = name;
        this.direction = direction;
        this.contactNumber = contactNumber;
        this.status = status;
        this.hospitalDoctors = hospitalDoctors;
    }

    public HospitalDto(Hospital hospital) {
        this.idHospital = hospital.getIdHospital();
        this.name = hospital.getName();
        this.direction = hospital.getDirection();
        this.contactNumber = hospital.getContactNumber();
        this.status = hospital.isStatus();
    }

    // Getters and Setters

    public int getIdHospital() {
        return idHospital;
    }

    public void setIdHospital(int idHospital) {
        this.idHospital = idHospital;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<HospitalDoctorDto> getHospitalDoctors() {
        return hospitalDoctors;
    }

    public void setHospitalDoctors(List<HospitalDoctorDto> hospitalDoctors) {
        this.hospitalDoctors = hospitalDoctors;
    }

}
