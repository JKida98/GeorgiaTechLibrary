package GTL_API.Repositories.StudentsInformationRepository;

import GTL_API.Models.Entities.StudentsInformationEntity;
import GTL_API.Models.ReturnModels.StudentsInformationReturn;

import java.util.List;

public interface IStudentsInformationRepositoryCustom {
    List<StudentsInformationReturn> findAll(int page);
}
