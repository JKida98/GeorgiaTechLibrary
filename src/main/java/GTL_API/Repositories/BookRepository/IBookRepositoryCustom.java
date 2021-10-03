package GTL_API.Repositories.BookRepository;

import GTL_API.Models.Entities.BookEntity;
import GTL_API.Models.ReturnModels.BookReturn;

import java.util.List;

public interface IBookRepositoryCustom  {
    List<BookReturn> getAllBooks();
    BookReturn addBook(BookEntity bookEntity);
    BookReturn updateBook(BookEntity bookEntity);
    BookReturn findBook(String isbn);
    List<BookReturn> findBooksByTitle(String title);
    List<BookReturn> findBooksByAuthor(String author);
    boolean borrowingBookDecrease(String isbn);
    boolean returningBookIncrease(String isbn);
    List<BookReturn> findSpecificUsersBookToReturn(Integer cardNumber);
}
