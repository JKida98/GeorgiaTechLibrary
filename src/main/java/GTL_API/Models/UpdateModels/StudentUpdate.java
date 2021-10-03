package GTL_API.Models.UpdateModels;

import javax.validation.constraints.NotNull;

public class StudentUpdate {
    @NotNull(message = "Student ID must be set.")
    private int studentId;
    @NotNull(message = "Student type must be set.")
    private int studentTypeId;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getStudentTypeId() {
        return studentTypeId;
    }

    public void setStudentTypeId(int studentTypeId) {
        this.studentTypeId = studentTypeId;
    }

}
