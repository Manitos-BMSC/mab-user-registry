package bo.edu.ucb.mabuserregistry.dao;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "MAB_hospital")
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hospital")
    private int idHospital;

    @Column(name = "name")
    private String name;

    @Column(name = "direction")
    private String direction;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "status")
    private boolean status;

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
    private List<HospitalDoctor> hospitalDoctors;

    public Hospital() {
    }

    public Hospital(int idHospital, String name, String direction, String contactNumber, boolean status, List<HospitalDoctor> hospitalDoctors) {
        this.idHospital = idHospital;
        this.name = name;
        this.direction = direction;
        this.contactNumber = contactNumber;
        this.status = status;
        this.hospitalDoctors = hospitalDoctors;
    }

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

    public List<HospitalDoctor> getHospitalDoctors() {
        return hospitalDoctors;
    }

    public void setHospitalDoctors(List<HospitalDoctor> hospitalDoctors) {
        this.hospitalDoctors = hospitalDoctors;
    }
}
