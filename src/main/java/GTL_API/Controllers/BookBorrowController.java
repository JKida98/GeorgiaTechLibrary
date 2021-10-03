package GTL_API.Controllers;

import GTL_API.Models.CreationModels.BookBorrowCreation;
import GTL_API.Services.BookBorrowService.IBookBorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("gtl/borrow")
public class BookBorrowController {

    private IBookBorrowService bookBorrowService;

    @Autowired
    public void setBookBorrowService(IBookBorrowService bookBorrowService) {
        this.bookBorrowService = bookBorrowService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> borrowBook(@RequestBody @Validated BookBorrowCreation bookBorrow) {
        return new ResponseEntity<>(bookBorrowService.borrowBook(bookBorrow), new HttpHeaders(), HttpStatus.OK);
    }
}
