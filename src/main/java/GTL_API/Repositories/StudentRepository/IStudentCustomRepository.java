package GTL_API.Repositories.StudentRepository;

import GTL_API.Models.Entities.StudentEntity;
import GTL_API.Models.ReturnModels.StudentReturn;


public interface IStudentCustomRepository {
    StudentReturn findByStudentId(int studentId);

    StudentReturn findByGpaBetween(int bottomRange, int upperRange);

    StudentReturn updateStudent(StudentEntity studentEntity);

    StudentReturn createStudent(StudentEntity studentEntity);

    boolean increaseMissedDeadlines(int studentId);
}
