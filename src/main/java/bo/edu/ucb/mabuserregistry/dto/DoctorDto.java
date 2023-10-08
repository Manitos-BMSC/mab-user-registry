package bo.edu.ucb.mabuserregistry.dto;

public class DoctorDto extends PersonDto{

    private String licenseCode;
    private String licenseDueDate;
    private String speciality;
    private int hospitalId;

    public DoctorDto() {
    }

    public DoctorDto(String name, String lastName, String email, String phone, String birthDate, Boolean isMale, String address, int cityId, Boolean isPassport, String documentNumber, String licenseCode, String licenseDueDate, String speciality, int hospitalId) {
        super(name, lastName, email, phone, birthDate, isMale, address, cityId, isPassport, documentNumber);
        this.licenseCode = licenseCode;
        this.licenseDueDate = licenseDueDate;
        this.speciality = speciality;
        this.hospitalId = hospitalId;
    }

    public String getLicenseCode() {
        return licenseCode;
    }

    public void setLicenseCode(String licenseCode) {
        this.licenseCode = licenseCode;
    }

    public String getLicenseDueDate() {
        return licenseDueDate;
    }

    public void setLicenseDueDate(String licenseDueDate) {
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
