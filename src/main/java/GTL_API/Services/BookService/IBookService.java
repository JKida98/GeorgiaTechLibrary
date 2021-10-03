package GTL_API.Services.BookService;

import GTL_API.Models.CreationModels.BookCreation;
import GTL_API.Models.ReturnModels.BookBorrowReturnView;
import GTL_API.Models.ReturnModels.BookReturn;
import GTL_API.Models.UpdateModels.BookUpdate;

import java.util.List;

public interface IBookService {
    List<BookReturn> getAllBooks();
    BookReturn addBook(BookCreation bookEntity);
    BookReturn updateBook(BookUpdate bookEntity);
    BookReturn findBook(String isbn);
    List<BookReturn> findBooksByTitle(String title);
    List<BookReturn> findBooksByAuthor(String author);
    boolean borrowingBookDecrease(String isbn);
    boolean returningBookIncrease(String isbn);
    List<BookBorrowReturnView> findSpecificUsersBookToReturn(int cardNumber);
}
