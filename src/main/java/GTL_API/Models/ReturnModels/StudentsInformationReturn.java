package GTL_API.Models.ReturnModels;

public class StudentsInformationReturn {

    private String ssn;
    private String firstName;
    private String middleName;
    private String lastName;
    private Integer cardNumberId;
    private Integer studentId;

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

    public Integer getCardNumberId() {
        return cardNumberId;
    }

    public void setCardNumberId(Integer cardNumberId) {
        this.cardNumberId = cardNumberId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }


}
