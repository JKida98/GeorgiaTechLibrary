package GTL_API.Controllers;

import GTL_API.Models.CreationModels.BookCatalogCreation;
import GTL_API.Models.UpdateModels.BookCatalogUpdate;
import GTL_API.Services.AvailableBooksService.IAvailableBooksService;
import GTL_API.Services.BookCatalogService.IBookCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("gtl/bookCatalog")
public class BookCatalogController {

    private IBookCatalogService iBookCatalogService;

    private IAvailableBooksService iAvailableBooksService;

    @Autowired
    public void setIAvailableBooksService(IAvailableBooksService iAvailableBooksService) {
        this.iAvailableBooksService = iAvailableBooksService;
    }

    @Autowired
    public void setIBookCatalogService(IBookCatalogService iBookCatalogService) {
        this.iBookCatalogService = iBookCatalogService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> getBooksCatalog() {
        return new ResponseEntity<>(iBookCatalogService.getBooksCatalog(), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getBookCatalog(@PathVariable int id) {
        return new ResponseEntity<>(iBookCatalogService.getBookCatalog(id), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> addBookCatalog(@RequestBody @Validated BookCatalogCreation bookCatalogCreation) {
        return new ResponseEntity<>(iBookCatalogService.addBookCatalog(bookCatalogCreation), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateBookCatalog(@RequestBody @Validated BookCatalogUpdate bookCatalogUpdate) {
        return new ResponseEntity<>(iBookCatalogService.updateBookCatalog(bookCatalogUpdate), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteBookCatalog(@PathVariable int id) {
        return new ResponseEntity<>(iBookCatalogService.removeBookCatalog(id), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/available/{page}", method = RequestMethod.GET)
    public ResponseEntity<?> getAvailableBooks(@PathVariable int page) {
        return new ResponseEntity<>(iAvailableBooksService.getAvailableBooks(page), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value="/available/id/{catalogId}", method =RequestMethod.GET)
    public ResponseEntity<?> findAvailableByCatalogId(@PathVariable int catalogId) {
        return new ResponseEntity<>(iAvailableBooksService.findAvailableByCatalogId(catalogId), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value="/available/isbn/{isbn}", method =RequestMethod.GET)
    public ResponseEntity<?> getAvailableBooksByIsbn(@PathVariable String isbn) {
        return new ResponseEntity<>(iAvailableBooksService.getAvailableBooksByIsbn(isbn), new HttpHeaders(), HttpStatus.OK);
    }
}
