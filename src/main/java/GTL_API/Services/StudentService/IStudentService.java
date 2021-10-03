package GTL_API.Services.StudentService;

import GTL_API.Models.CreationModels.StudentCreation;
import GTL_API.Models.ReturnModels.StudentReturn;
import GTL_API.Models.UpdateModels.StudentUpdate;


public interface IStudentService {
    StudentReturn findByStudentId(int studentId);

    StudentReturn findByGpaBetween(int bottomRange, int upperRange);

    StudentReturn updateStudent(StudentUpdate student);

    StudentReturn createStudent(StudentCreation student);

    boolean increaseMissedDeadlines(int studentId);
}
