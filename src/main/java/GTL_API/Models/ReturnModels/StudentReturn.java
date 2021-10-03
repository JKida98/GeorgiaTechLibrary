package GTL_API.Models.ReturnModels;

public class StudentReturn {
    private int studentId;
    private int studentTypeId;
    private int deadlinesMissed;
    private double gpa;

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

    public int getDeadlinesMissed() {
        return deadlinesMissed;
    }

    public void setDeadlinesMissed(int deadlinesMissed) {
        this.deadlinesMissed = deadlinesMissed;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
}
