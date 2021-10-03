package GTL_API.Models.ReturnModels;

public class PersonReturn {
    private String ssn;
    private String firstName;
    private String middleName;
    private String lastName;
    private int homeAddressId;
    private int campusAddressId;
    private int loanDuration;
    private int cardNumberId;
    private int personTypeId;

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

    public int getHomeAddressId() {
        return homeAddressId;
    }

    public void setHomeAddressId(int homeAddressId) {
        this.homeAddressId = homeAddressId;
    }

    public int getCampusAddressId() {
        return campusAddressId;
    }

    public void setCampusAddressId(int campusAddressId) {
        this.campusAddressId = campusAddressId;
    }

    public int getLoanDuration() {
        return loanDuration;
    }

    public void setLoanDuration(int loanDuration) {
        this.loanDuration = loanDuration;
    }

    public int getCardNumberId() {
        return cardNumberId;
    }

    public void setCardNumberId(int cardNumberId) {
        this.cardNumberId = cardNumberId;
    }

    public int getPersonTypeId() {
        return personTypeId;
    }

    public void setPersonTypeId(int personTypeId) {
        this.personTypeId = personTypeId;
    }


}
