package GTL_API.Controllers;

import GTL_API.Models.CreationModels.BookReturnCreation;
import GTL_API.Services.BookReturnService.IBookReturnService;
import GTL_API.Services.NotReturnedService.INotReturnedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("gtl/return")
public class BookReturnController {

    private IBookReturnService iBookReturnService;

    private INotReturnedService iNotReturnedService;

    @Autowired
    public void setINotReturnedService(INotReturnedService iNotReturnedService) {
        this.iNotReturnedService = iNotReturnedService;
    }

    @Autowired
    public void setBookReturnService(IBookReturnService iBookReturnService) {
        this.iBookReturnService = iBookReturnService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> returnBook(@RequestBody @Validated BookReturnCreation bookReturnCreation) {
        return new ResponseEntity<>(iBookReturnService.returnBook(bookReturnCreation), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> getListPeopleWhoDidNotReturnedBooks() {
        return new ResponseEntity<>(iNotReturnedService.getListPeopleWhoDidNotReturnedBooks(), new HttpHeaders(), HttpStatus.OK);
    }


}
