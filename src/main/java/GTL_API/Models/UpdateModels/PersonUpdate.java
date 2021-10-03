package GTL_API.Models.UpdateModels;

import javax.validation.constraints.NotNull;

public class PersonUpdate {
    @NotNull(message = "Social security number must be provided.")
    private String ssn;
    @NotNull(message = "First name is required.")
    private String firstName;
    private String middleName;
    @NotNull(message = "Last name must be provided.")
    private String lastName;
    @NotNull(message = "Identification number of home address must be given.")
    private Integer homeAddressId;
    @NotNull(message = "Identification number of campus address must be given.")
    private Integer campusAddressId;
    @NotNull(message = "Loan duration must be given.")
    private Integer loanDuration;
    @NotNull(message = "A card number must be provided.")
    private Integer cardNumberId;
    @NotNull(message = "Identification of person type must be passed.")
    private Integer personTypeId;

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getHomeAddressId() {
        return homeAddressId;
    }

    public void setHomeAddressId(Integer homeAddressId) {
        this.homeAddressId = homeAddressId;
    }

    public Integer getCampusAddressId() {
        return campusAddressId;
    }

    public void setCampusAddressId(Integer campusAddressId) {
        this.campusAddressId = campusAddressId;
    }

    public Integer getLoanDuration() {
        return loanDuration;
    }

    public void setLoanDuration(Integer loanDuration) {
        this.loanDuration = loanDuration;
    }

    public Integer getCardNumberId() {
        return cardNumberId;
    }

    public void setCardNumberId(Integer cardNumberId) {
        this.cardNumberId = cardNumberId;
    }

    public Integer getPersonTypeId() {
        return personTypeId;
    }

    public void setPersonTypeId(Integer personTypeId) {
        this.personTypeId = personTypeId;
    }

}
