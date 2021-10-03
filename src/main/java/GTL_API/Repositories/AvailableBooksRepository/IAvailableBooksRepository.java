package GTL_API.Repositories.AvailableBooksRepository;

import GTL_API.Models.Entities.AvailableBooksEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAvailableBooksRepository extends PagingAndSortingRepository<AvailableBooksEntity, Integer> {
    List<AvailableBooksEntity> findAllByIdIsNotNull(Pageable pageable);
    List<AvailableBooksEntity> findAllByIsbnIs(String isbn);
    Optional<AvailableBooksEntity> findByIdIs(int id);
}
