package GTL_API.Services.AvailableBooksService;

import GTL_API.Models.ReturnModels.AvailableBooksReturn;
import GTL_API.Repositories.AvailableBooksRepository.IAvailableBooksRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvailableBooksService implements IAvailableBooksService {

    private IAvailableBooksRepositoryCustom iAvailableBooksRepositoryCustom;

    @Autowired
    public void setIAvailableBooksRepositoryCustom(IAvailableBooksRepositoryCustom iAvailableBooksRepositoryCustom) {
        this.iAvailableBooksRepositoryCustom = iAvailableBooksRepositoryCustom;
    }

    @Override
    public List<AvailableBooksReturn> getAvailableBooks(int page) {
        return iAvailableBooksRepositoryCustom.findAllAvailableBooks(page);
    }

    @Override
    public List<AvailableBooksReturn> getAvailableBooksByIsbn(String isbn) {
        return iAvailableBooksRepositoryCustom.findByIsbn(isbn);
    }

    @Override
    public AvailableBooksReturn findAvailableByCatalogId(int id) {
        return iAvailableBooksRepositoryCustom.findAvailableByCatalogId(id);
    }
}
