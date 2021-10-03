package GTL_API.Repositories.AvailableBooksRepository;

import GTL_API.Exceptions.NotFoundException;
import GTL_API.Models.Entities.AvailableBooksEntity;
import GTL_API.Models.ReturnModels.AvailableBooksReturn;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Component
public class AvailableBooksRepository implements IAvailableBooksRepositoryCustom {

    private IAvailableBooksRepository iAvailableBooksRepository;

    private ModelMapper modelMapper;

    private Type listType = new TypeToken<List<AvailableBooksReturn>>() {
    }.getType();

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setIAvailableBooksRepository(IAvailableBooksRepository iAvailableBooksRepository) {
        this.iAvailableBooksRepository = iAvailableBooksRepository;
    }

    @Override
    public List<AvailableBooksReturn> findAllAvailableBooks(int page) {

        return modelMapper.map(iAvailableBooksRepository.findAllByIdIsNotNull(PageRequest.of(page, 15)), listType);
    }

    @Override
    public AvailableBooksReturn findAvailableByCatalogId(int catalogId) {
        Optional<AvailableBooksEntity> foundBook = iAvailableBooksRepository.findByIdIs(catalogId);
        if (foundBook.isPresent()) {
            return modelMapper.map(foundBook.get(), AvailableBooksReturn.class);
        } else {
            throw new NotFoundException("The book was not found or is not available.");
        }
    }

    @Override
    public List<AvailableBooksReturn> findByIsbn(String isbn) {
        List<AvailableBooksEntity> foundBooks = iAvailableBooksRepository.findAllByIsbnIs(isbn);
        if(foundBooks.size() == 0){
            throw new NotFoundException("Books were not found.");
        }else{
            return modelMapper.map(iAvailableBooksRepository.findAllByIsbnIs(isbn), listType);
        }
    }
}
