package GTL_API.Repositories.BookBorrowRepository;

import GTL_API.Models.Entities.BookBorrowEntity;
import GTL_API.Models.ReturnModels.BookBorrowReturn;

import java.util.List;

public interface IBookBorrowRepositoryCustom {
    BookBorrowReturn createBookBorrow(BookBorrowEntity bookBorrowEntity, int bookReturnId);

    List<Integer> findBorrowedBook(int bookCatalogId, String ssn);
}
