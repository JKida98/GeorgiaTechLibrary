package GTL_API.Models.Entities;

import javax.persistence.*;

@Entity
@Table(name = "EmployeesInformation", schema = "dbo")
public class EmployeesInformationEntity {
    private String ssn;
    private Integer employeeId;
    private String firstName;
    private String lastName;
    private Double hourlyWage;
    private Double weeklyHours;
    private String type;

    @Id
    @Column(name = "ssn", nullable = false, length = 11)
    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    @Basic
    @Column(name = "employee_id", nullable = false)
    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
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
    @Column(name = "last_name", nullable = false, length = 30)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "hourly_wage", nullable = false, precision = 0)
    public Double getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(Double hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    @Basic
    @Column(name = "weekly_hours", nullable = false, precision = 0)
    public Double getWeeklyHours() {
        return weeklyHours;
    }

    public void setWeeklyHours(Double weeklyHours) {
        this.weeklyHours = weeklyHours;
    }

    @Basic
    @Column(name = "type", nullable = false, length = 50)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeesInformationEntity that = (EmployeesInformationEntity) o;

        if (ssn != null ? !ssn.equals(that.ssn) : that.ssn != null) return false;
        if (employeeId != null ? !employeeId.equals(that.employeeId) : that.employeeId != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (hourlyWage != null ? !hourlyWage.equals(that.hourlyWage) : that.hourlyWage != null) return false;
        if (weeklyHours != null ? !weeklyHours.equals(that.weeklyHours) : that.weeklyHours != null) return false;
        return type != null ? type.equals(that.type) : that.type == null;
    }

    @Override
    public int hashCode() {
        int result = ssn != null ? ssn.hashCode() : 0;
        result = 31 * result + (employeeId != null ? employeeId.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (hourlyWage != null ? hourlyWage.hashCode() : 0);
        result = 31 * result + (weeklyHours != null ? weeklyHours.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
