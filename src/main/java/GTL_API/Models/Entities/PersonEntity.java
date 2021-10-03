package GTL_API.Models.Entities;

import javax.persistence.*;

@Entity
@Table(name = "Person", schema = "Information_Basic", catalog = "Giorgia_Tech_Library")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name="findPersonByCardNumber",
                procedureName = "StudentPermission_Procedures.FindPersonByCardNumber",
                resultClasses = PersonEntity.class,
                parameters =
                @StoredProcedureParameter(
                        mode = ParameterMode.IN,
                        type=Integer.class,
                        name="@theCardNumber"
                )
        ),
}
)
public class PersonEntity {
    private String ssn;
    private String firstName;
    private String middleName;
    private String lastName;
    private Integer homeAddressId;
    private Integer campusAddressId;
    private Integer loanDuration;
    private Integer cardNumberId;
    private Integer personTypeId;
    private Boolean isDeleted;
    private Integer credentialsId;

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
    @Column(name = "home_address_id", nullable = false)
    public Integer getHomeAddressId() {
        return homeAddressId;
    }

    public void setHomeAddressId(Integer homeAddressId) {
        this.homeAddressId = homeAddressId;
    }

    @Basic
    @Column(name = "campus_address_id", nullable = false)
    public Integer getCampusAddressId() {
        return campusAddressId;
    }

    public void setCampusAddressId(Integer campusAddressId) {
        this.campusAddressId = campusAddressId;
    }

    @Basic
    @Column(name = "loan_duration", nullable = false)
    public Integer getLoanDuration() {
        return loanDuration;
    }

    public void setLoanDuration(Integer loanDuration) {
        this.loanDuration = loanDuration;
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
    @Column(name = "person_type_id", nullable = false)
    public Integer getPersonTypeId() {
        return personTypeId;
    }

    public void setPersonTypeId(Integer personTypeId) {
        this.personTypeId = personTypeId;
    }

    @Basic
    @Column(name = "is_deleted", nullable = false)
    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Basic
    @Column(name = "credentials_id", nullable = false)
    public Integer getCredentialsId() {
        return credentialsId;
    }

    public void setCredentialsId(Integer credentialsId) {
        this.credentialsId = credentialsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonEntity that = (PersonEntity) o;

        if (ssn != null ? !ssn.equals(that.ssn) : that.ssn != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (middleName != null ? !middleName.equals(that.middleName) : that.middleName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (homeAddressId != null ? !homeAddressId.equals(that.homeAddressId) : that.homeAddressId != null)
            return false;
        if (campusAddressId != null ? !campusAddressId.equals(that.campusAddressId) : that.campusAddressId != null)
            return false;
        if (loanDuration != null ? !loanDuration.equals(that.loanDuration) : that.loanDuration != null) return false;
        if (cardNumberId != null ? !cardNumberId.equals(that.cardNumberId) : that.cardNumberId != null) return false;
        if (personTypeId != null ? !personTypeId.equals(that.personTypeId) : that.personTypeId != null) return false;
        if (isDeleted != null ? !isDeleted.equals(that.isDeleted) : that.isDeleted != null) return false;
        return credentialsId != null ? credentialsId.equals(that.credentialsId) : that.credentialsId == null;
    }

    @Override
    public int hashCode() {
        int result = ssn != null ? ssn.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (homeAddressId != null ? homeAddressId.hashCode() : 0);
        result = 31 * result + (campusAddressId != null ? campusAddressId.hashCode() : 0);
        result = 31 * result + (loanDuration != null ? loanDuration.hashCode() : 0);
        result = 31 * result + (cardNumberId != null ? cardNumberId.hashCode() : 0);
        result = 31 * result + (personTypeId != null ? personTypeId.hashCode() : 0);
        result = 31 * result + (isDeleted != null ? isDeleted.hashCode() : 0);
        result = 31 * result + (credentialsId != null ? credentialsId.hashCode() : 0);
        return result;
    }
}
