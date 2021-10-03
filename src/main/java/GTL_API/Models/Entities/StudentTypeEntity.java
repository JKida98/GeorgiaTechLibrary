package GTL_API.Models.Entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "StudentType", schema = "Information_Basic")
public class StudentTypeEntity {
    private Integer id;
    private String courseName;
    private Date graduationDate;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "course_name", nullable = false, length = 50)
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Basic
    @Column(name = "graduation_date", nullable = false)
    public Date getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(Date graduationDate) {
        this.graduationDate = graduationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentTypeEntity that = (StudentTypeEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (courseName != null ? !courseName.equals(that.courseName) : that.courseName != null) return false;
        return graduationDate != null ? graduationDate.equals(that.graduationDate) : that.graduationDate == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (courseName != null ? courseName.hashCode() : 0);
        result = 31 * result + (graduationDate != null ? graduationDate.hashCode() : 0);
        return result;
    }
}
