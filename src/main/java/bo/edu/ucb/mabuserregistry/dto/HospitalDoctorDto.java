package bo.edu.ucb.mabuserregistry.dto;

import bo.edu.ucb.mabuserregistry.dao.HospitalDoctor;

public class HospitalDoctorDto {

    private int id;
    private HospitalDto hospital;
    private DoctorDto doctor;

    public HospitalDoctorDto() {
    }

    public HospitalDoctorDto(int id, HospitalDto hospital, DoctorDto doctor) {
        this.id = id;
        this.hospital = hospital;
        this.doctor = doctor;
    }

    public HospitalDoctorDto(HospitalDoctor hospitalDoctor) {
        this.id = hospitalDoctor.getId();
        this.hospital = new HospitalDto(hospitalDoctor.getHospital());
        this.doctor = new DoctorDto(hospitalDoctor.getDoctor());
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HospitalDto getHospital() {
        return hospital;
    }

    public void setHospital(HospitalDto hospital) {
        this.hospital = hospital;
    }

    public DoctorDto getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorDto doctor) {
        this.doctor = doctor;
    }
}
