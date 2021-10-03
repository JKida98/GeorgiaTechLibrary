package GTL_API.Models.Entities;

import javax.persistence.*;

@Entity
@Table(name = "Student", schema = "Information_Basic")
public class StudentEntity {
    private Integer studentId;
    private Integer studentTypeId;
    private Integer deadlinesMissed;
    private Double gpa;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id", nullable = false)
    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @Basic
    @Column(name = "student_type_id", nullable = false)
    public Integer getStudentTypeId() {
        return studentTypeId;
    }

    public void setStudentTypeId(Integer studentTypeId) {
        this.studentTypeId = studentTypeId;
    }

    @Basic
    @Column(name = "deadlines_missed", nullable = false)
    public Integer getDeadlinesMissed() {
        return deadlinesMissed;
    }

    public void setDeadlinesMissed(Integer deadlinesMissed) {
        this.deadlinesMissed = deadlinesMissed;
    }

    @Basic
    @Column(name = "gpa", nullable = false, precision = 0)
    public Double getGpa() {
        return gpa;
    }

    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentEntity that = (StudentEntity) o;

        if (studentId != null ? !studentId.equals(that.studentId) : that.studentId != null) return false;
        if (studentTypeId != null ? !studentTypeId.equals(that.studentTypeId) : that.studentTypeId != null)
            return false;
        if (deadlinesMissed != null ? !deadlinesMissed.equals(that.deadlinesMissed) : that.deadlinesMissed != null)
            return false;
        return gpa != null ? gpa.equals(that.gpa) : that.gpa == null;
    }

    @Override
    public int hashCode() {
        int result = studentId != null ? studentId.hashCode() : 0;
        result = 31 * result + (studentTypeId != null ? studentTypeId.hashCode() : 0);
        result = 31 * result + (deadlinesMissed != null ? deadlinesMissed.hashCode() : 0);
        result = 31 * result + (gpa != null ? gpa.hashCode() : 0);
        return result;
    }
}
