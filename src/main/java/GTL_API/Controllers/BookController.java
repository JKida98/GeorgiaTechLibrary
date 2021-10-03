package GTL_API.Controllers;

import GTL_API.Models.CreationModels.BookCreation;
import GTL_API.Models.UpdateModels.BookUpdate;
import GTL_API.Services.BookService.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("gtl/book")
public class BookController {

    private IBookService iBookService;

    @Autowired
    public void setIBookService(IBookService iBookService) {
        this.iBookService = iBookService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> getAllBooks() {
        return new ResponseEntity<>(iBookService.getAllBooks(), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/title/{title}", method = RequestMethod.GET)
    public ResponseEntity<?> getBookByTitle(@PathVariable String title) {
        return new ResponseEntity<>(iBookService.findBooksByTitle(title), new HttpHeaders(), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/author/{author}", method = RequestMethod.GET)
    public ResponseEntity<?> getBookByAuthor(@PathVariable String author) {
        return new ResponseEntity<>(iBookService.findBooksByAuthor(author), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/isbn/{isbn}", method = RequestMethod.GET)
    public ResponseEntity<?> getBookByIsbn(@PathVariable String isbn) {
        return new ResponseEntity<>(iBookService.findBook(isbn), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> addBook(@RequestBody BookCreation bookCreation) {
        return new ResponseEntity<>(iBookService.addBook(bookCreation), new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    public ResponseEntity<?> addBook(@RequestBody BookUpdate bookUpdate) {
        return new ResponseEntity<>(iBookService.updateBook(bookUpdate), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{cardNumber}", method = RequestMethod.GET)
    public ResponseEntity<?> findSpecificUsersBookToReturn(@PathVariable int cardNumber) {
        return new ResponseEntity<>(iBookService.findSpecificUsersBookToReturn(cardNumber), new HttpHeaders(), HttpStatus.OK);
    }

}
