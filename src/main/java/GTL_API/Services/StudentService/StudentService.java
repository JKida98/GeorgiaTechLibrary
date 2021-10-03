package GTL_API.Services.StudentService;

import GTL_API.Models.CreationModels.StudentCreation;
import GTL_API.Models.Entities.StudentEntity;
import GTL_API.Models.ReturnModels.StudentReturn;
import GTL_API.Models.UpdateModels.StudentUpdate;
import GTL_API.Repositories.StudentRepository.IStudentCustomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService implements IStudentService {

    private IStudentCustomRepository studentRepositoryCustom;
    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setIStudentRepository(IStudentCustomRepository iStudentRepository) {
        this.studentRepositoryCustom = iStudentRepository;
    }

    @Override
    public StudentReturn findByStudentId(int studentId) {
        return studentRepositoryCustom.findByStudentId(studentId);
    }

    @Override
    public StudentReturn findByGpaBetween(int bottomRange, int upperRange) {
        return studentRepositoryCustom.findByGpaBetween(bottomRange, upperRange);
    }

    @Override
    public StudentReturn updateStudent(StudentUpdate student) {
        return studentRepositoryCustom.updateStudent(modelMapper.map(student, StudentEntity.class));
    }

    @Override
    public StudentReturn createStudent(StudentCreation student) {
        return studentRepositoryCustom.createStudent(modelMapper.map(student, StudentEntity.class));
    }

    @Override
    public boolean increaseMissedDeadlines(int studentId) {
        return studentRepositoryCustom.increaseMissedDeadlines(studentId);
    }
}
