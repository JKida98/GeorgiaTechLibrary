package GTL_API.Repositories.BookCatalogRepository;


import GTL_API.Models.Entities.BookCatalogEntity;
import GTL_API.Models.ReturnModels.BookCatalogReturn;

import java.util.List;

public interface IBookCatalogRepositoryCustom {
    List<BookCatalogReturn> getBooksCatalog();
    BookCatalogReturn getBookCatalog(int id);
    BookCatalogReturn addBookCatalog(BookCatalogEntity bookCatalogEntity);
    BookCatalogReturn updateBookCatalog(BookCatalogEntity bookCatalogEntity);
    boolean removeBookCatalog(int id);
}