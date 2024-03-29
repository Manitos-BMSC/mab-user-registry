package bo.edu.ucb.mabuserregistry.dto;

import bo.edu.ucb.mabuserregistry.dao.Doctor;
import bo.edu.ucb.mabuserregistry.dao.Person;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DoctorDto{

    private int doctorId;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private Date birthDate;
    private Boolean isMale;
    private String address;

    private String username;

    private String documentNumber;
    private Boolean isPassport;
    private int cityId;
    private String licenseCode;
    private Date licenseDueDate;
    private String speciality;
    private int hospitalId;

    public DoctorDto() {
    }

    public DoctorDto(int doctorId, String name, String lastName, String email, String phone, Date birthDate, Boolean isMale, String address, String username, String documentNumber, Boolean isPassport, int cityId, String licenseCode, Date licenseDueDate, String speciality, int hospitalId) {
        this.doctorId = doctorId;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.isMale = isMale;
        this.address = address;
        this.username = username;
        this.documentNumber = documentNumber;
        this.isPassport = isPassport;
        this.cityId = cityId;
        this.licenseCode = licenseCode;
        this.licenseDueDate = licenseDueDate;
        this.speciality = speciality;
        this.hospitalId = hospitalId;
    }

    public DoctorDto(String name, String lastName, String email, String phone, Date birthDate, Boolean isMale, String address, String username, String documentNumber, Boolean isPassport, int cityId, String licenseCode, Date licenseDueDate, String speciality, int hospitalId) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.isMale = isMale;
        this.address = address;
        this.username = username;
        this.documentNumber = documentNumber;
        this.isPassport = isPassport;
        this.cityId = cityId;
        this.licenseCode = licenseCode;
        this.licenseDueDate = licenseDueDate;
        this.speciality = speciality;
        this.hospitalId = hospitalId;
    }

    public  DoctorDto(String name, String lastName, String speciality, String phone){
        this.name = name;
        this.lastName = lastName;
        this.speciality = speciality;
        this.phone = phone;
    }

    public DoctorDto(int doctorId, String name, String lastName, String speciality, String phone){
        this.doctorId = doctorId;
        this.name = name;
        this.lastName = lastName;
        this.speciality = speciality;
        this.phone = phone;
    }

    public DoctorDto(Doctor doctor){
        this.doctorId = doctor.getId();
        this.licenseCode = doctor.getLicenseCode();
        this.licenseDueDate = doctor.getLicenseDueDate();
        this.speciality = doctor.getMedicalSpeciality();
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getMale() {
        return isMale;
    }

    public void setMale(Boolean male) {
        isMale = male;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Boolean getPassport() {
        return isPassport;
    }

    public void setPassport(Boolean passport) {
        isPassport = passport;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getLicenseCode() {
        return licenseCode;
    }

    public void setLicenseCode(String licenseCode) {
        this.licenseCode = licenseCode;
    }

    public Date getLicenseDueDate() {
        return licenseDueDate;
    }

    public void setLicenseDueDate(Date licenseDueDate) {
        this.licenseDueDate = licenseDueDate;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }
}
