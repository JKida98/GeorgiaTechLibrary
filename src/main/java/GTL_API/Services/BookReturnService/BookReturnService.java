package GTL_API.Services.BookReturnService;

import GTL_API.Exceptions.NotFoundException;
import GTL_API.Exceptions.UpdateException;
import GTL_API.Models.CreationModels.BookReturnCreation;
import GTL_API.Models.Entities.BookReturnEntity;
import GTL_API.Models.ReturnModels.BookReturnReturn;
import GTL_API.Models.ReturnModels.PersonReturn;
import GTL_API.Repositories.BookReturnRespository.IBookReturnRepositoryCustom;
import GTL_API.Services.BookBorrowService.IBookBorrowService;
import GTL_API.Services.BookCatalogService.IBookCatalogService;
import GTL_API.Services.BookService.IBookService;
import GTL_API.Services.PersonService.IPersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Service
public class BookReturnService implements IBookReturnService {

    private IPersonService personService;

    private IBookReturnRepositoryCustom iBookReturnRepository;

    private IBookBorrowService bookBorrowService;

    private ModelMapper modelMapper;

    private IBookService iBookService;

    private IBookCatalogService iBookCatalogService;

    @Autowired
    public void setiBookService(IBookService iBookService) {
        this.iBookService = iBookService;
    }

    @Autowired
    public void setiBookCatalogService(IBookCatalogService iBookCatalogService) {
        this.iBookCatalogService = iBookCatalogService;
    }

    @Autowired
    public void setBookBorrowService(IBookBorrowService bookBorrowService) {
        this.bookBorrowService = bookBorrowService;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setPersonService(IPersonService personService) {
        this.personService = personService;
    }

    @Autowired
    public void setIBookReturnRepository(IBookReturnRepositoryCustom iBookReturnRepository) {
        this.iBookReturnRepository = iBookReturnRepository;
    }

    @Override
    public BookReturnReturn createBookReturn(BookReturnCreation bookReturnCreation, String ssn) {
        bookReturnCreation.setPayment(0D);
        bookReturnCreation.setStatus(false);
        int loanDuration = personService.findPersonBySsn(ssn).getLoanDuration();
        Calendar c = Calendar.getInstance();
        c.setTime(new java.util.Date(new java.util.Date().getTime()));
        c.add(Calendar.DATE, loanDuration);
        bookReturnCreation.setEstimatedReturnDate(new Date(c.getTime().getTime()));
        BookReturnReturn result = iBookReturnRepository.createBookReturn(
                modelMapper.map(bookReturnCreation, BookReturnEntity.class)
        );
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean returnBook(BookReturnCreation bookReturnCreation) {
        UserDetails user =  (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int username = Integer.parseInt(user.getUsername());
        PersonReturn foundPerson = personService.findPersonByCardNumberId(username);
        iBookService.returningBookIncrease(iBookCatalogService.getBookCatalog(bookReturnCreation.getBookCatalogId()).getIsbn());
        String ssn = foundPerson.getSsn();
        List<Integer> bookReturnIds = bookBorrowService.findBookBorrows(bookReturnCreation.getBookCatalogId(), ssn);
        for (Integer id : bookReturnIds) {
            BookReturnReturn result = iBookReturnRepository.findReturningBook(id);
            if (result != null) {
                boolean returningResult = iBookReturnRepository.returnBookAndChangeStatus(id);
                if(returningResult){
                    return true;
                }else{
                    throw new UpdateException("There was a problem while returning a book.");
                }
            }
        }
        throw new NotFoundException("Book is not borrowed");
    }
}
