package GTL_API.Repositories.StudentsInformationRepository;

import GTL_API.Models.Entities.StudentsInformationEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStudentsInformationRepository extends PagingAndSortingRepository<StudentsInformationEntity, String> {
    List<StudentsInformationEntity> findAllBySsnIsNotNull(Pageable pageable);
}
