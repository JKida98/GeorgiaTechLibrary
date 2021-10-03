package GTL_API.Services;

import GTL_API.Exceptions.CreationException;
import GTL_API.Exceptions.NotFoundException;
import GTL_API.Exceptions.UnknownException;
import GTL_API.Models.CreationModels.BookBorrowCreation;
import GTL_API.Models.Entities.BookBorrowEntity;
import GTL_API.Models.ReturnModels.*;
import GTL_API.Repositories.BookBorrowRepository.IBookBorrowRepositoryCustom;
import GTL_API.Services.AvailableBooksService.IAvailableBooksService;
import GTL_API.Services.BookBorrowService.BookBorrowService;
import GTL_API.Services.BookCatalogService.IBookCatalogService;
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
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;


@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration
public class BookBorrowServiceTest {

    @Mock
    private IBookBorrowRepositoryCustom bookBorrowRepositoryCustom;

    @Mock
    private IBookReturnService bookReturnService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private IBookService iBookService;

    @Mock
    private IAvailableBooksService iAvailableBooksService;

    @Mock
    private IBookCatalogService iBookCatalogService;


    @Mock
    private IPersonService personService;

    @InjectMocks
    private BookBorrowService bookBorrowService;

    @Before
    public void initMocks() {

        MockitoAnnotations.initMocks(this);
    }

    private BookReturnReturn getBookReturnReturn(){
        BookReturnReturn brr = new BookReturnReturn();
        brr.setId(123);
        return brr;
    }


    private BookCatalogReturn getBookCatalogReturn() {
        BookCatalogReturn bcr = new BookCatalogReturn();
        bcr.setIsbn("123");
        return bcr;
    }

    private BookBorrowEntity getBookBorrowEntity(){
        BookBorrowEntity bbe = new BookBorrowEntity();
        bbe.setBookCatalogId(12);
        bbe.setSsn("123-3213-1223");
        bbe.setBookReturnId(123);
        return bbe;
    }


    private BookBorrowCreation getBookBorrowCreation() {
        BookBorrowCreation bbc = new BookBorrowCreation();
        bbc.setBookCatalogId(12);
        return bbc;
    }

    private BookReturn getBookReturnNotAvailable() {
        BookReturn br = new BookReturn();
        br.setAvailableBooksNumber(0);
        return br;
    }

    private BookReturn getBookReturnAvailable() {
        BookReturn br = new BookReturn();
        br.setAuthor("Author");
        br.setAvailableBooksNumber(3);
        return br;
    }

    private PersonReturn getPersonReturn(){
        PersonReturn pr = new PersonReturn();
        pr.setSsn("123-3213-1223");
        return pr;
    }

    private BookBorrowReturnView getBookBorrowReturnView(){
        BookBorrowReturnView bbrv = new BookBorrowReturnView();
        bbrv.setCatalogId(12);
        bbrv.setAuthor("Author");
        return bbrv;
    }

    @Test
    public void testBookBorrowServiceThrowsExceptionWhenIdIsInvalid() {
        try {
            Mockito.when(iBookCatalogService.getBookCatalog(Mockito.anyInt())).thenThrow(NotFoundException.class);
            bookBorrowService.borrowBook(getBookBorrowCreation());
            Assert.fail();
        } catch (NotFoundException notFoundException) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testBookBorrowServiceThrowsNotFoundExceptionIfBookIsNotAvailable() {
        try {
            Mockito.when(iBookCatalogService.getBookCatalog(Mockito.anyInt())).thenReturn(getBookCatalogReturn());
            Mockito.when(iAvailableBooksService.findAvailableByCatalogId(Mockito.anyInt())).thenReturn(null);
            bookBorrowService.borrowBook(getBookBorrowCreation());
            Assert.fail();
        } catch (NotFoundException notFoundException) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testBookBorrowServiceThrowsNotFoundExceptionIfThereIsNoCopiesAvailable() {
        try {
            Mockito.when(iBookCatalogService.getBookCatalog(Mockito.anyInt())).thenReturn(getBookCatalogReturn());
            Mockito.when(iAvailableBooksService.findAvailableByCatalogId(Mockito.anyInt())).thenReturn(new AvailableBooksReturn());
            Mockito.when(iBookService.findBook(Mockito.anyString())).thenReturn(getBookReturnNotAvailable());
            bookBorrowService.borrowBook(getBookBorrowCreation());
            Assert.fail();
        } catch (NotFoundException notFoundException) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testBookBorrowServiceThrowsExceptionIfUserIsNotLoggedIn() {
        try {
            Authentication authentication = Mockito.mock(Authentication.class);
            SecurityContext securityContext = Mockito.mock(SecurityContext.class);
            Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
            SecurityContextHolder.setContext(securityContext);
            Mockito.when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(null);
            Mockito.when(iBookCatalogService.getBookCatalog(Mockito.anyInt())).thenReturn(getBookCatalogReturn());
            Mockito.when(iAvailableBooksService.findAvailableByCatalogId(Mockito.anyInt())).thenReturn(new AvailableBooksReturn());
            Mockito.when(iBookService.findBook(Mockito.anyString())).thenReturn(getBookReturnAvailable());
            bookBorrowService.borrowBook(getBookBorrowCreation());
            Assert.fail();
        } catch (NotFoundException notFoundException) {
            Assert.assertEquals("User is not logged in.", notFoundException.getMessage());
        }

    }

    @Test
    public void testBookBorrowFailsIfUserWithUserNameWasNotFound(){
        try {
            UserDetails userDetails = Mockito.mock(UserDetails.class);
            Mockito.when(userDetails.getUsername()).thenReturn("121565423");
            Authentication authentication = Mockito.mock(Authentication.class);
            SecurityContext securityContext = Mockito.mock(SecurityContext.class);
            Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
            SecurityContextHolder.setContext(securityContext);
            Mockito.when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(userDetails);
            Mockito.when(iBookCatalogService.getBookCatalog(Mockito.anyInt())).thenReturn(getBookCatalogReturn());
            Mockito.when(iAvailableBooksService.findAvailableByCatalogId(Mockito.anyInt())).thenReturn(new AvailableBooksReturn());
            Mockito.when(iBookService.findBook(Mockito.anyString())).thenReturn(getBookReturnAvailable());
            Mockito.when(personService.findPersonByCardNumberId(Mockito.anyInt())).thenThrow(new NotFoundException("User was not found"));
            bookBorrowService.borrowBook(getBookBorrowCreation());
            Assert.fail();
        }catch (NotFoundException notFoundException){
            Assert.assertEquals(notFoundException.getMessage(), "User was not found");
        }
    }

    @Test
    public void testBookBorrowFailsAtCreatingBookReturnRecordCreationExceptionThrown(){
        try {
            UserDetails userDetails = Mockito.mock(UserDetails.class);
            Mockito.when(userDetails.getUsername()).thenReturn("121565423");
            Authentication authentication = Mockito.mock(Authentication.class);
            SecurityContext securityContext = Mockito.mock(SecurityContext.class);
            Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
            SecurityContextHolder.setContext(securityContext);
            Mockito.when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(userDetails);
            Mockito.when(iBookCatalogService.getBookCatalog(Mockito.anyInt())).thenReturn(getBookCatalogReturn());
            Mockito.when(iAvailableBooksService.findAvailableByCatalogId(Mockito.anyInt())).thenReturn(new AvailableBooksReturn());
            Mockito.when(iBookService.findBook(Mockito.anyString())).thenReturn(getBookReturnAvailable());
            Mockito.when(personService.findPersonByCardNumberId(Mockito.anyInt())).thenReturn(getPersonReturn());
            Mockito.when(bookReturnService.createBookReturn(Mockito.any(), Mockito.anyString())).thenThrow(new CreationException("Problem with creating book return record."));
            bookBorrowService.borrowBook(getBookBorrowCreation());
            Assert.fail();
        }catch (CreationException creationException){
            Assert.assertEquals(creationException.getMessage(), "Problem with creating book return record.");
        }
    }

    @Test
    public void testBookBorrowFailsAtDecreasingNumberOfCopies(){
        try {
            UserDetails userDetails = Mockito.mock(UserDetails.class);
            Mockito.when(userDetails.getUsername()).thenReturn("121565423");
            Authentication authentication = Mockito.mock(Authentication.class);
            SecurityContext securityContext = Mockito.mock(SecurityContext.class);
            Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
            SecurityContextHolder.setContext(securityContext);
            Mockito.when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(userDetails);
            Mockito.when(iBookCatalogService.getBookCatalog(Mockito.anyInt())).thenReturn(getBookCatalogReturn());
            Mockito.when(iAvailableBooksService.findAvailableByCatalogId(Mockito.anyInt())).thenReturn(new AvailableBooksReturn());
            Mockito.when(iBookService.findBook(Mockito.anyString())).thenReturn(getBookReturnAvailable());
            Mockito.when(personService.findPersonByCardNumberId(Mockito.anyInt())).thenReturn(getPersonReturn());
            Mockito.when(bookReturnService.createBookReturn(Mockito.any(), Mockito.anyString())).thenReturn(getBookReturnReturn());
            Mockito.when(iBookService.borrowingBookDecrease(Mockito.anyString())).thenThrow(new UnknownException("Unexpected error"));
            bookBorrowService.borrowBook(getBookBorrowCreation());
            Assert.fail();
        }catch (UnknownException unknownException){
            Assert.assertEquals(unknownException.getMessage(), "Unexpected error");
        }
    }

    @Test
    public void testBookBorrowFailsAtCreatingBookBorrow(){
        try {
            UserDetails userDetails = Mockito.mock(UserDetails.class);
            Mockito.when(userDetails.getUsername()).thenReturn("121565423");
            Authentication authentication = Mockito.mock(Authentication.class);
            SecurityContext securityContext = Mockito.mock(SecurityContext.class);
            Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
            SecurityContextHolder.setContext(securityContext);
            Mockito.when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(userDetails);
            Mockito.when(iBookCatalogService.getBookCatalog(Mockito.anyInt())).thenReturn(getBookCatalogReturn());
            Mockito.when(iAvailableBooksService.findAvailableByCatalogId(Mockito.anyInt())).thenReturn(new AvailableBooksReturn());
            Mockito.when(iBookService.findBook(Mockito.anyString())).thenReturn(getBookReturnAvailable());
            Mockito.when(personService.findPersonByCardNumberId(Mockito.anyInt())).thenReturn(getPersonReturn());
            Mockito.when(bookReturnService.createBookReturn(Mockito.any(), Mockito.anyString())).thenReturn(getBookReturnReturn());
            Mockito.when(iBookService.borrowingBookDecrease(Mockito.anyString())).thenReturn(true);
            Mockito.when(bookBorrowRepositoryCustom.createBookBorrow(Mockito.any(), Mockito.anyInt())).thenThrow(new UnknownException("Unknown exception while creating book borrow."));
            bookBorrowService.borrowBook(getBookBorrowCreation());
            Assert.fail();
        }catch (UnknownException unknownException){
            Assert.assertEquals(unknownException.getMessage(), "Unknown exception while creating book borrow.");
        }
    }

    @Test
    public void testBookBorrowPasses(){
        try {
            UserDetails userDetails = Mockito.mock(UserDetails.class);
            Mockito.when(userDetails.getUsername()).thenReturn("121565423");
            Authentication authentication = Mockito.mock(Authentication.class);
            SecurityContext securityContext = Mockito.mock(SecurityContext.class);
            Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
            SecurityContextHolder.setContext(securityContext);
            Mockito.when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(userDetails);
            Mockito.when(iBookCatalogService.getBookCatalog(Mockito.anyInt())).thenReturn(getBookCatalogReturn());
            Mockito.when(iAvailableBooksService.findAvailableByCatalogId(Mockito.anyInt())).thenReturn(new AvailableBooksReturn());
            Mockito.when(iBookService.findBook(Mockito.anyString())).thenReturn(getBookReturnAvailable());
            Mockito.when(personService.findPersonByCardNumberId(Mockito.anyInt())).thenReturn(getPersonReturn());
            Mockito.when(bookReturnService.createBookReturn(Mockito.any(), Mockito.anyString())).thenReturn(getBookReturnReturn());
            Mockito.when(iBookService.borrowingBookDecrease(Mockito.anyString())).thenReturn(true);
            Mockito.when(iBookService.findBook(Mockito.anyString())).thenReturn(getBookReturnAvailable());
            Mockito.when(bookBorrowRepositoryCustom.createBookBorrow(Mockito.any(), Mockito.anyInt())).thenReturn(new BookBorrowReturn());
            Mockito.when(modelMapper.map(Mockito.any(BookBorrowCreation.class), Mockito.any())).thenReturn(getBookBorrowEntity());
            Mockito.when(modelMapper.map(Mockito.any(BookReturn.class), Mockito.any())).thenReturn(getBookBorrowReturnView());
            BookBorrowReturnView result = bookBorrowService.borrowBook(getBookBorrowCreation());
            Assert.assertEquals("Author", result.getAuthor());
        }catch (Exception unknownException){
            Assert.fail();
        }
    }


}
