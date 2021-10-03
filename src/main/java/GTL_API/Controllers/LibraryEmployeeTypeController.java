package GTL_API.Controllers;

import GTL_API.Models.CreationModels.LibraryEmployeeTypeCreation;
import GTL_API.Models.UpdateModels.LibraryEmployeeTypeUpdate;
import GTL_API.Services.LibraryEmployeeTypeService.ILibraryEmployeeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("gtl/let")
public class LibraryEmployeeTypeController {
    private ILibraryEmployeeTypeService letService;

    @Autowired
    public void setletService(ILibraryEmployeeTypeService _letService) {
        this.letService = _letService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllLETs() {
        return new ResponseEntity<>(letService.findAll(), new HttpHeaders(), HttpStatus.FOUND);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllLETs(@PathVariable int id) {
        return new ResponseEntity<>(letService.findLibraryEmployeeTypeById(id), new HttpHeaders(), HttpStatus.FOUND);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createLET(@RequestBody @Valid LibraryEmployeeTypeCreation letCreate) {
        return new ResponseEntity<>(letService.createLibraryEmployeeType(letCreate), new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> createLET(@PathVariable int id,@RequestBody @Valid LibraryEmployeeTypeUpdate letUpdate) {
        return new ResponseEntity<>(letService.updateLibraryEmployeeType(id,letUpdate), new HttpHeaders(), HttpStatus.OK);
    }
}
