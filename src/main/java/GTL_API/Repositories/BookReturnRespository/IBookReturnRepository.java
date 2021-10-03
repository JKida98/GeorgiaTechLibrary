package GTL_API.Repositories.BookReturnRespository;

import GTL_API.Models.Entities.BookReturnEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBookReturnRepository extends JpaRepository<BookReturnEntity, Integer> {
    Optional<BookReturnEntity> findByIdIsAndStatusIsFalse(int id);
    BookReturnEntity findByIdIs(int id);
}
