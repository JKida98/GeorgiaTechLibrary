package GTL_API.Models.Entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "LibraryEmployee", schema = "Information_Sensitive")
public class LibraryEmployeeEntity {
    private Integer employeeId;
    private Integer libraryEmployeeTypeId;
    private Double weeklyHours;
    private Date hireDate;

    @Id
    @Column(name = "employee_id", nullable = false)
    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    @Basic
    @Column(name = "library_employee_type_id", nullable = false)
    public Integer getLibraryEmployeeTypeId() {
        return libraryEmployeeTypeId;
    }

    public void setLibraryEmployeeTypeId(Integer libraryEmployeeTypeId) {
        this.libraryEmployeeTypeId = libraryEmployeeTypeId;
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
    @Column(name = "hire_date", nullable = false)
    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LibraryEmployeeEntity that = (LibraryEmployeeEntity) o;

        if (employeeId != null ? !employeeId.equals(that.employeeId) : that.employeeId != null) return false;
        if (libraryEmployeeTypeId != null ? !libraryEmployeeTypeId.equals(that.libraryEmployeeTypeId) : that.libraryEmployeeTypeId != null)
            return false;
        if (weeklyHours != null ? !weeklyHours.equals(that.weeklyHours) : that.weeklyHours != null) return false;
        return hireDate != null ? hireDate.equals(that.hireDate) : that.hireDate == null;
    }

    @Override
    public int hashCode() {
        int result = employeeId != null ? employeeId.hashCode() : 0;
        result = 31 * result + (libraryEmployeeTypeId != null ? libraryEmployeeTypeId.hashCode() : 0);
        result = 31 * result + (weeklyHours != null ? weeklyHours.hashCode() : 0);
        result = 31 * result + (hireDate != null ? hireDate.hashCode() : 0);
        return result;
    }
}
