package GTL_API.Services.BookBorrowService;

import GTL_API.Models.CreationModels.BookBorrowCreation;
import GTL_API.Models.ReturnModels.BookBorrowReturn;
import GTL_API.Models.ReturnModels.BookBorrowReturnView;

import java.util.List;

public interface IBookBorrowService {
    BookBorrowReturnView borrowBook(BookBorrowCreation bookBorrowCreation);

    List<Integer> findBookBorrows(int bookCatalogId, String ssn);
}
