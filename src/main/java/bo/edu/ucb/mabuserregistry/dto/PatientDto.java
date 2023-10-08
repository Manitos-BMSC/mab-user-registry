package bo.edu.ucb.mabuserregistry.dto;

public class PatientDto extends PersonDto{
    private String emergencyPhone;
    private int countryId;

    public PatientDto() {
    }

    public PatientDto(String name, String lastName, String email, String phone, String birthDate, Boolean isMale, String address, int cityId, Boolean isPassport, String documentNumber, String emergencyPhone, int countryId) {
        super(name, lastName, email, phone, birthDate, isMale, address, cityId, isPassport, documentNumber);
        this.emergencyPhone = emergencyPhone;
        this.countryId = countryId;
    }

    public String getEmergencyPhone() {
        return emergencyPhone;
    }

    public void setEmergencyPhone(String emergencyPhone) {
        this.emergencyPhone = emergencyPhone;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
}