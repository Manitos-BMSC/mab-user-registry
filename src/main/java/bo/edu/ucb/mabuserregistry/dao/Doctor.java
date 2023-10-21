package bo.edu.ucb.mabuserregistry.dao;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "MAB_doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "MAB_person_id_keycloack")
    private String mabPersonIdKeycloack;

    @Column(name = "s3_object_id")
    private int s3ObjectId;

    @Column(name = "license_code")
    private String licenseCode;

    @Column(name = "license_due_date")
    private Date licenseDueDate;

    @Column(name = "license_status")
    private String licenseStatus;

    @Column(name = "medical_speciality")
    private String medicalSpeciality;

    @Column(name = "status")
    private boolean status;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<HospitalDoctor> hospitalDoctors;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UnavailableSchedule> unavailableSchedules;



    public Doctor() {
    }

    public Doctor(int id, String mabPersonIdKeycloack, int s3ObjectId, String licenseCode, Date licenseDueDate, String licenseStatus, String medicalSpeciality, boolean status, List<HospitalDoctor> hospitalDoctors) {
        this.id = id;
        this.mabPersonIdKeycloack = mabPersonIdKeycloack;
        this.s3ObjectId = s3ObjectId;
        this.licenseCode = licenseCode;
        this.licenseDueDate = licenseDueDate;
        this.licenseStatus = licenseStatus;
        this.medicalSpeciality = medicalSpeciality;
        this.status = status;
        this.hospitalDoctors = hospitalDoctors;
    }
}
