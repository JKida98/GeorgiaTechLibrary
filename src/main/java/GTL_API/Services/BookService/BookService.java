package GTL_API.Services.BookService;

import GTL_API.Models.CreationModels.BookCreation;
import GTL_API.Models.Entities.BookEntity;
import GTL_API.Models.ReturnModels.BookBorrowReturnView;
import GTL_API.Models.ReturnModels.BookReturn;
import GTL_API.Models.ReturnModels.PersonReturn;
import GTL_API.Models.UpdateModels.BookUpdate;
import GTL_API.Repositories.BookRepository.IBookRepositoryCustom;
import GTL_API.Services.PersonService.IPersonService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class BookService implements IBookService {

    private IBookRepositoryCustom iBookRepositoryCustom;

    private ModelMapper modelMapper;

    private IPersonService personService;


    @Autowired
    public void setIBookRepositoryCustom(IBookRepositoryCustom iBookRepositoryCustom) {
        this.iBookRepositoryCustom = iBookRepositoryCustom;
    }

    @Autowired
    public void setPersonService(IPersonService personService) {
        this.personService = personService;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BookReturn> getAllBooks() {
        return iBookRepositoryCustom.getAllBooks();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BookReturn addBook(BookCreation bookEntity) {
        return iBookRepositoryCustom.addBook(modelMapper.map(bookEntity, BookEntity.class));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BookReturn updateBook(BookUpdate bookEntity) {
        return iBookRepositoryCustom.updateBook(modelMapper.map(bookEntity, BookEntity.class));
    }

    @Override
    public BookReturn findBook(String isbn) {
        return iBookRepositoryCustom.findBook(isbn);
    }

    @Override
    public List<BookReturn> findBooksByTitle(String title) {
        return iBookRepositoryCustom.findBooksByTitle(title);
    }

    @Override
    public List<BookReturn> findBooksByAuthor(String author) {
        return iBookRepositoryCustom.findBooksByAuthor(author);
    }

    @Override
    public boolean borrowingBookDecrease(String isbn) {
        return iBookRepositoryCustom.borrowingBookDecrease(isbn);
    }

    @Override
    public boolean returningBookIncrease(String isbn) {
        return iBookRepositoryCustom.returningBookIncrease(isbn);
    }

    @Override
    public List<BookBorrowReturnView> findSpecificUsersBookToReturn(int cardNumber) {
        if(cardNumber==0){
            UserDetails user =  (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            cardNumber=Integer.parseInt(user.getUsername());
        }
        List<BookReturn> result = iBookRepositoryCustom.findSpecificUsersBookToReturn(cardNumber);
        Type listType = new TypeToken<List<BookBorrowReturnView>>() {
        }.getType();
        return modelMapper.map(result,listType);
    }

}
