package GTL_API.Models.Entities;

import javax.persistence.*;

@Entity
@Table(name = "StudentsInformation", schema = "dbo")
public class StudentsInformationEntity {
    private String ssn;
    private String firstName;
    private String middleName;
    private String lastName;
    private Integer cardNumberId;
    private Integer studentId;

    @Id
    @Column(name = "ssn", nullable = false, length = 11)
    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    @Basic
    @Column(name = "first_name", nullable = false, length = 30)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "middle_name", nullable = true, length = 30)
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Basic
    @Column(name = "last_name", nullable = false, length = 30)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "card_number_id", nullable = false)
    public Integer getCardNumberId() {
        return cardNumberId;
    }

    public void setCardNumberId(Integer cardNumberId) {
        this.cardNumberId = cardNumberId;
    }

    @Basic
    @Column(name = "student_id", nullable = true)
    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentsInformationEntity that = (StudentsInformationEntity) o;

        if (ssn != null ? !ssn.equals(that.ssn) : that.ssn != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (middleName != null ? !middleName.equals(that.middleName) : that.middleName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (cardNumberId != null ? !cardNumberId.equals(that.cardNumberId) : that.cardNumberId != null) return false;
        return studentId != null ? studentId.equals(that.studentId) : that.studentId == null;
    }

    @Override
    public int hashCode() {
        int result = ssn != null ? ssn.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (cardNumberId != null ? cardNumberId.hashCode() : 0);
        result = 31 * result + (studentId != null ? studentId.hashCode() : 0);
        return result;
    }
}
