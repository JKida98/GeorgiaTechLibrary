package GTL_API.Services.BookCatalogService;

import GTL_API.Models.CreationModels.BookCatalogCreation;
import GTL_API.Models.ReturnModels.BookCatalogReturn;
import GTL_API.Models.UpdateModels.BookCatalogUpdate;

import java.util.List;

public interface IBookCatalogService {
    List<BookCatalogReturn> getBooksCatalog();
    BookCatalogReturn getBookCatalog(int id);
    BookCatalogReturn addBookCatalog(BookCatalogCreation bookCatalogEntity);
    BookCatalogReturn updateBookCatalog(BookCatalogUpdate bookCatalogEntity);
    boolean removeBookCatalog(int id);
}
