package GTL_API.Controllers;

import GTL_API.Models.UpdateModels.CoverTypeUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import GTL_API.Services.CoverTypeService.ICoverTypeService;

import javax.validation.Valid;

@RestController
@RequestMapping("gtl/coverType")
public class CoverTypeController {

    private ICoverTypeService iCoverTypeService;

    @Autowired
    public void setICoverTypeService(ICoverTypeService iCoverTypeService) {
        this.iCoverTypeService = iCoverTypeService;
    }

    @RequestMapping(value="/{coverType}", method = RequestMethod.GET)
    public ResponseEntity<?> findCoverTypeByName(@PathVariable String coverType){
        return new ResponseEntity<>(iCoverTypeService.findCoverTypeByName(coverType), new HttpHeaders(), HttpStatus.FOUND);
    }

    @RequestMapping(value="/", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateCoverType(@RequestBody @Valid CoverTypeUpdate coverType){
        return new ResponseEntity<>(iCoverTypeService.updateCoverType(coverType), new HttpHeaders(), HttpStatus.OK);
    }

}
