package bo.edu.ucb.mabuserregistry.dao;

import jakarta.persistence.*;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Entity
@Table(name = "MAB_person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city_id", nullable = false)
    private Integer cityId;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "lastname", length = 50, nullable = false)
    private String lastname;

    @Column(name = "user_mail", length = 50, nullable = false)
    private String userMail;

    @Column(name = "phone_number", length = 30, nullable = false)
    private String phoneNumber;

    @Column(name = "document_type", nullable = false)
    private Boolean documentType;

    @Column(name = "document_number", length = 50, nullable = false)
    private String documentNumber;

    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    @Column(name = "address", length = 50, nullable = false)
    private String address;

    @Column(name = "personal_document", length = 100, nullable = false)
    private String personalDocument;

    @Column(name = "gender", nullable = false)
    private Boolean gender;

    @Column(name = "tx_user", length = 30, nullable = false)
    private String txUser;

    @Column(name = "tx_host", length = 30, nullable = false)
    private String txHost;

    @Column(name = "tx_date", nullable = false)
    private Date txDate;

    @Column(name = "status", nullable = false)
    private Boolean status;

    public Person() {
    }

    public Person(Long id, Integer cityId, String name, String lastname, String userMail, String phoneNumber, Boolean documentType, String documentNumber, Date birthDate, String address, String personalDocument, Boolean gender, String txUser, String txHost, Date txDate, Boolean status) {
        this.id = id;
        this.cityId = cityId;
        this.name = name;
        this.lastname = lastname;
        this.userMail = userMail;
        this.phoneNumber = phoneNumber;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.birthDate = birthDate;
        this.address = address;
        this.personalDocument = personalDocument;
        this.gender = gender;
        this.txUser = txUser;
        this.txHost = txHost;
        this.txDate = txDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getDocumentType() {
        return documentType;
    }

    public void setDocumentType(Boolean documentType) {
        this.documentType = documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPersonalDocument() {
        return personalDocument;
    }

    public void setPersonalDocument(String personalDocument) {
        this.personalDocument = personalDocument;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getTxUser() {
        return txUser;
    }

    public void setTxUser(String txUser) {
        this.txUser = txUser;
    }

    public String getTxHost() {
        return txHost;
    }

    public void setTxHost(String txHost) {
        this.txHost = txHost;
    }

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}

