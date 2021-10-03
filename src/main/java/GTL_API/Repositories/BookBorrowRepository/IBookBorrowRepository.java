package GTL_API.Repositories.BookBorrowRepository;

import GTL_API.Models.Entities.BookBorrowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IBookBorrowRepository extends JpaRepository<BookBorrowEntity, Integer> {

    List<BookBorrowEntity> findAllByBookCatalogIdIsAndSsnIs(int bookCatalogId, String ssn);

}
