package GTL_API.Services;

import GTL_API.Exceptions.CreationException;
import GTL_API.Exceptions.NotFoundException;
import GTL_API.Exceptions.UpdateException;
import GTL_API.Models.CreationModels.BookReturnCreation;
import GTL_API.Models.Entities.BookReturnEntity;
import GTL_API.Models.ReturnModels.BookCatalogReturn;
import GTL_API.Models.ReturnModels.BookReturnReturn;
import GTL_API.Models.ReturnModels.PersonReturn;
import GTL_API.Repositories.BookReturnRespository.IBookReturnRepositoryCustom;
import GTL_API.Services.BookBorrowService.IBookBorrowService;
import GTL_API.Services.BookCatalogService.IBookCatalogService;
import GTL_API.Services.BookReturnService.BookReturnService;
import GTL_API.Services.BookReturnService.IBookReturnService;
import GTL_API.Services.BookService.IBookService;
import GTL_API.Services.PersonService.IPersonService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration
public class BookReturnServiceTest {

    private BookReturnCreation bookReturnCreation;
    private PersonReturn personReturn;
    private BookCatalogReturn bookCatalogReturn;
    private List<Integer> bookReturnIds;
    private BookReturnReturn bookReturnReturn;
    private BookReturnEntity bookReturnEntity;

    @Before
    public void setup(){
        bookReturnCreation = new BookReturnCreation();
        bookReturnCreation.setBookCatalogId(1);

        personReturn = new PersonReturn();
        personReturn.setSsn("TestSsn");
        personReturn.setLoanDuration(1);

        bookCatalogReturn = new BookCatalogReturn();
        bookCatalogReturn.setIsbn("TestISBN");

        bookReturnIds = new ArrayList<>();
        bookReturnIds.add(1);

        bookReturnEntity = new BookReturnEntity();

        bookReturnReturn = new BookReturnReturn();
    }

    @Mock
    private IPersonService personService;

    @Mock
    private IBookReturnRepositoryCustom iBookReturnRepository;

    @Mock
    private IBookBorrowService bookBorrowService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private IBookService iBookService;

    @Mock
    private IBookCatalogService iBookCatalogService;

    @InjectMocks
    private BookReturnService bookReturnService;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void returnBookWithValidData_ShouldReturnTrue(){
        UserDetails user = Mockito.mock(UserDetails.class);
        Mockito.when(user.getUsername()).thenReturn("123");
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        Mockito.when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(user);

        Mockito.when(personService.findPersonByCardNumberId(Mockito.anyInt())).thenReturn(personReturn);
        Mockito.when(iBookService.returningBookIncrease(Mockito.anyString())).thenReturn(true);
        Mockito.when(iBookCatalogService.getBookCatalog(Mockito.anyInt())).thenReturn(bookCatalogReturn);
        Mockito.when(bookBorrowService.findBookBorrows(Mockito.anyInt(),Mockito.anyString())).thenReturn(bookReturnIds);
        Mockito.when(iBookReturnRepository.findReturningBook(Mockito.anyInt())).thenReturn(bookReturnReturn);
        Mockito.when(iBookReturnRepository.returnBookAndChangeStatus(Mockito.anyInt())).thenReturn(true);

        boolean result = bookReturnService.returnBook(bookReturnCreation);

        Assert.assertTrue(result);

    }

    @Test
    public void returnBookWithInvalidBookCatalogId_ShouldThrowNotFoundException(){
        UserDetails user = Mockito.mock(UserDetails.class);
        Mockito.when(user.getUsername()).thenReturn("123");
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        Mockito.when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(user);

        Mockito.when(personService.findPersonByCardNumberId(Mockito.anyInt())).thenReturn(personReturn);
        Mockito.when(iBookCatalogService.getBookCatalog(Mockito.anyInt())).thenThrow(new NotFoundException("Book Catalog not found"));
        try{
            boolean result = bookReturnService.returnBook(bookReturnCreation);
            Assert.fail();
        }catch (NotFoundException ex){
            Assert.assertEquals(ex.getMessage(),"Book Catalog not found");
        }

    }

    @Test
    public void returnBookAndSomethingIsWrongDuringReturning_ShouldThrowUpdateException(){
        UserDetails user = Mockito.mock(UserDetails.class);
        Mockito.when(user.getUsername()).thenReturn("123");
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        Mockito.when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(user);

        Mockito.when(personService.findPersonByCardNumberId(Mockito.anyInt())).thenReturn(personReturn);
        Mockito.when(iBookService.returningBookIncrease(Mockito.anyString())).thenReturn(true);
        Mockito.when(iBookCatalogService.getBookCatalog(Mockito.anyInt())).thenReturn(bookCatalogReturn);
        Mockito.when(bookBorrowService.findBookBorrows(Mockito.anyInt(),Mockito.anyString())).thenReturn(bookReturnIds);
        Mockito.when(iBookReturnRepository.findReturningBook(Mockito.anyInt())).thenReturn(bookReturnReturn);
        Mockito.when(iBookReturnRepository.returnBookAndChangeStatus(Mockito.anyInt())).thenReturn(false);

        try{
            boolean result = bookReturnService.returnBook(bookReturnCreation);
            Assert.fail();
        }catch (UpdateException ex){
            Assert.assertEquals(ex.getMessage(),"There was a problem while returning a book.");
        }

    }

    @Test
    public void createBookReturnWithValidData_ShouldReturnBookReturnReturn(){
        Mockito.when(personService.findPersonBySsn(Mockito.anyString())).thenReturn(personReturn);
        Mockito.when(modelMapper.map(bookReturnCreation,BookReturnEntity.class)).thenReturn(bookReturnEntity);
        Mockito.when(iBookReturnRepository.createBookReturn(bookReturnEntity)).thenThrow(new CreationException("Error"));

        try {
            bookReturnService.createBookReturn(bookReturnCreation,"1");
            Assert.fail();
        }catch (CreationException ex){
            Assert.assertEquals(ex.getMessage(), "Error");
        }
    }
}
