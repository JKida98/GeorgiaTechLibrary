package GTL_API.Repositories.BookRepository;

import GTL_API.Models.Entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IBookRepository extends JpaRepository<BookEntity, Integer> {
    Optional<List<BookEntity>> findAllByIsbnIsNotNull();
    Optional<BookEntity> findByIsbnIs(String isbn);
    int countAllByIsbnIs(String isbn);
    Optional<List<BookEntity>> findAllByTitleIs(String title);
    Optional<List<BookEntity>> findAllByAuthorIs(String author);

}
