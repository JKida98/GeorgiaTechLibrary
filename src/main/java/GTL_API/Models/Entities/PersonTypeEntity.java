package GTL_API.Models.Entities;

import javax.persistence.*;

@Entity
@Table(name = "PersonType", schema = "Information_Basic")
public class PersonTypeEntity {
    private Integer id;
    private Integer studentId;
    private Integer libraryEmployeeId;
    private Integer facultyMemberId;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "student_id", nullable = true)
    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @Basic
    @Column(name = "library_employee_id", nullable = true)
    public Integer getLibraryEmployeeId() {
        return libraryEmployeeId;
    }

    public void setLibraryEmployeeId(Integer libraryEmployeeId) {
        this.libraryEmployeeId = libraryEmployeeId;
    }

    @Basic
    @Column(name = "faculty_member_id", nullable = true)
    public Integer getFacultyMemberId() {
        return facultyMemberId;
    }

    public void setFacultyMemberId(Integer facultyMemberId) {
        this.facultyMemberId = facultyMemberId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonTypeEntity that = (PersonTypeEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (studentId != null ? !studentId.equals(that.studentId) : that.studentId != null) return false;
        if (libraryEmployeeId != null ? !libraryEmployeeId.equals(that.libraryEmployeeId) : that.libraryEmployeeId != null)
            return false;
        return facultyMemberId != null ? facultyMemberId.equals(that.facultyMemberId) : that.facultyMemberId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (studentId != null ? studentId.hashCode() : 0);
        result = 31 * result + (libraryEmployeeId != null ? libraryEmployeeId.hashCode() : 0);
        result = 31 * result + (facultyMemberId != null ? facultyMemberId.hashCode() : 0);
        return result;
    }
}
