package GTL_API.Models.ReturnModels;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;

public class StudentTypeReturn {
    private int id;
    private String courseName;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date graduationDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Date getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(Date graduationDate) {
        this.graduationDate = graduationDate;
    }
}
