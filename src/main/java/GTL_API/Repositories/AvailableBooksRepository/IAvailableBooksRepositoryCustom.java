package GTL_API.Repositories.AvailableBooksRepository;

import GTL_API.Models.Entities.AvailableBooksEntity;
import GTL_API.Models.ReturnModels.AvailableBooksReturn;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAvailableBooksRepositoryCustom {
    List<AvailableBooksReturn> findAllAvailableBooks(int page);

    AvailableBooksReturn findAvailableByCatalogId(int catalogId);

    List<AvailableBooksReturn> findByIsbn(String isbn);
}
