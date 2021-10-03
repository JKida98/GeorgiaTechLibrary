package GTL_API.Services.BookCatalogService;

import GTL_API.Models.CreationModels.BookCatalogCreation;
import GTL_API.Models.Entities.BookCatalogEntity;
import GTL_API.Models.ReturnModels.BookCatalogReturn;
import GTL_API.Models.ReturnModels.BookReturn;
import GTL_API.Models.UpdateModels.BookCatalogUpdate;
import GTL_API.Models.UpdateModels.BookUpdate;
import GTL_API.Repositories.BookCatalogRepository.IBookCatalogRepositoryCustom;
import GTL_API.Services.BookService.IBookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class BookCatalogService implements IBookCatalogService {

    private IBookCatalogRepositoryCustom iBookCatalogRepositoryCustom;

    private IBookService iBookService;

    private ModelMapper modelMapper;

    @Autowired
    public void setIBookService(IBookService iBookService) {
        this.iBookService = iBookService;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setIBookCatalogRepositoryCustom(IBookCatalogRepositoryCustom iBookCatalogRepositoryCustom) {
        this.iBookCatalogRepositoryCustom = iBookCatalogRepositoryCustom;
    }

    @Override
    public List<BookCatalogReturn> getBooksCatalog() {
        return iBookCatalogRepositoryCustom.getBooksCatalog();
    }

    @Override
    public BookCatalogReturn getBookCatalog(int id) {
        return iBookCatalogRepositoryCustom.getBookCatalog(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BookCatalogReturn addBookCatalog(BookCatalogCreation bookCatalogCreation) {
        addingIncrement(bookCatalogCreation.getIsbn(), bookCatalogCreation.getBookConditionId());
        return iBookCatalogRepositoryCustom.addBookCatalog(modelMapper.map(bookCatalogCreation, BookCatalogEntity.class));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BookCatalogReturn updateBookCatalog(BookCatalogUpdate bookCatalogUpdate) {
        availableCopiesUpdate(getBookCatalog(bookCatalogUpdate.getId()).getIsbn(), bookCatalogUpdate.getBookConditionId());
        return iBookCatalogRepositoryCustom.updateBookCatalog(modelMapper.map(bookCatalogUpdate, BookCatalogEntity.class));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeBookCatalog(int id) {
        deletionDecrement(id);
        return iBookCatalogRepositoryCustom.removeBookCatalog(id);
    }

    private void addingIncrement(String isbn, int condition) {
        BookReturn bookReturn = iBookService.findBook(isbn);
        int copies = bookReturn.getCopiesNumber();
        int availableCopies = bookReturn.getAvailableBooksNumber();
        BookUpdate bookUpdate = new BookUpdate();
        bookUpdate.setCopiesNumber(copies + 1);
        if (condition == 1 || condition == 3) {
            bookUpdate.setAvailableBooksNumber(availableCopies + 1);
        }
        bookUpdate.setIsbn(isbn);
        iBookService.updateBook(bookUpdate);
    }

    private void deletionDecrement(int id){
        String isbn = getBookCatalog(id).getIsbn();
        BookReturn bookReturn = iBookService.findBook(isbn);
        int copies = bookReturn.getCopiesNumber();
        int availableCopies = bookReturn.getAvailableBooksNumber();
        BookUpdate bookUpdate = new BookUpdate();
        bookUpdate.setCopiesNumber(copies - 1);
        bookUpdate.setAvailableBooksNumber(availableCopies-1);
        bookUpdate.setIsbn(isbn);
        iBookService.updateBook(bookUpdate);
    }

    private void availableCopiesUpdate(String isbn, int condition){
        int availableCopies = iBookService.findBook(isbn).getAvailableBooksNumber();
        BookUpdate bookUpdate = new BookUpdate();
        if (condition == 1 || condition == 3) {
            bookUpdate.setAvailableBooksNumber(availableCopies + 1);
        }else{
            bookUpdate.setAvailableBooksNumber(availableCopies - 1);
        }
        bookUpdate.setIsbn(isbn);
        iBookService.updateBook(bookUpdate);
    }
}
