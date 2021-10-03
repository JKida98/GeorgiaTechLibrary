package GTL_API.Services.AvailableBooksService;

import GTL_API.Models.ReturnModels.AvailableBooksReturn;

import java.util.List;

public interface IAvailableBooksService {
    List<AvailableBooksReturn> getAvailableBooks(int page);
    List<AvailableBooksReturn> getAvailableBooksByIsbn(String isbn);
    AvailableBooksReturn findAvailableByCatalogId(int id);
}
