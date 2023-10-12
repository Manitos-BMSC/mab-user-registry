package bo.edu.ucb.mabuserregistry.dao;

import jakarta.persistence.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Entity
@Table(name = "MAB_pacient")
public class Pacient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "emergency_phone", length = 30, nullable = false)
    private String emergencyPhone;

    @Column(name = "pacient_status", length = 30, nullable = false)
    private String pacientStatus;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Person person;

    @OneToMany(mappedBy = "pacient") // One-to-many relationship with Files_pacient
    private List<FilesPacient> files;


    public Pacient(Long id, String emergencyPhone, String pacientStatus, Boolean status, Person person) {
        this.id = id;
        this.emergencyPhone = emergencyPhone;
        this.pacientStatus = pacientStatus;
        this.status = status;
        this.person = person;
    }

    public Pacient() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmergencyPhone() {
        return emergencyPhone;
    }

    public void setEmergencyPhone(String emergencyPhone) {
        this.emergencyPhone = emergencyPhone;
    }

    public String getPacientStatus() {
        return pacientStatus;
    }

    public void setPacientStatus(String pacientStatus) {
        this.pacientStatus = pacientStatus;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}