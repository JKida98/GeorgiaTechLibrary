package GTL_API.Repositories.BookCatalogRepository;

import GTL_API.Models.Entities.BookCatalogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IBookCatalogRepository extends JpaRepository<BookCatalogEntity, Integer> {
    int countAllByIsbnIsAndBookConditionIdIs(int isbn, int bookConditionId);
    Optional<List<BookCatalogEntity>> findAllByDeletedIsFalse();
    Optional<BookCatalogEntity> findByIdIs(int id);

}


