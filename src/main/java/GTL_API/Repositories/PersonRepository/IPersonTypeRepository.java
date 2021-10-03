package GTL_API.Repositories.PersonRepository;

import GTL_API.Models.Entities.PersonTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPersonTypeRepository extends JpaRepository<PersonTypeEntity, Integer> {
    List<PersonTypeEntity> findAllByStudentIdIsNotNullAndFacultyMemberIdIsNullAndLibraryEmployeeIdIsNull();
    List<PersonTypeEntity> findAllByStudentIdIsNullAndFacultyMemberIdIsNullAndLibraryEmployeeIdIsNotNull();


}
