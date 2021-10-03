package GTL_API.Models.CreationModels;

import javax.validation.constraints.NotNull;

public class StudentCreation {
    @NotNull(message = "Student type must be provided.")
    private Integer studentTypeId;

    public Integer getStudentTypeId() {
        return studentTypeId;
    }

    public void setStudentTypeId(Integer studentTypeId) {
        this.studentTypeId = studentTypeId;
    }

}
