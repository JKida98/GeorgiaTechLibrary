package GTL_API.Models.Entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "StudentsGpaHigher3WorkingMoreThan1Year", schema = "dbo")
public class StudentsGpaHigher3WorkingMoreThan1YearEntity {
    private Integer studentId;
    private Double gpa;
    private Integer employeeId;
    private Double weeklyHours;
    private Date hireDate;

    @Id
    @Column(name = "student_id", nullable = false)
    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @Basic
    @Column(name = "gpa", nullable = false, precision = 0)
    public Double getGpa() {
        return gpa;
    }

    public void setGpa(Double gpa) {
        this.gpa = gpa;
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

        StudentsGpaHigher3WorkingMoreThan1YearEntity that = (StudentsGpaHigher3WorkingMoreThan1YearEntity) o;

        if (studentId != null ? !studentId.equals(that.studentId) : that.studentId != null) return false;
        if (gpa != null ? !gpa.equals(that.gpa) : that.gpa != null) return false;
        if (employeeId != null ? !employeeId.equals(that.employeeId) : that.employeeId != null) return false;
        if (weeklyHours != null ? !weeklyHours.equals(that.weeklyHours) : that.weeklyHours != null) return false;
        return hireDate != null ? hireDate.equals(that.hireDate) : that.hireDate == null;
    }

    @Override
    public int hashCode() {
        int result = studentId != null ? studentId.hashCode() : 0;
        result = 31 * result + (gpa != null ? gpa.hashCode() : 0);
        result = 31 * result + (employeeId != null ? employeeId.hashCode() : 0);
        result = 31 * result + (weeklyHours != null ? weeklyHours.hashCode() : 0);
        result = 31 * result + (hireDate != null ? hireDate.hashCode() : 0);
        return result;
    }
}
