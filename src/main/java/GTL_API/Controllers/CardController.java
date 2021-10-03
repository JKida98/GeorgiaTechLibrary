package GTL_API.Controllers;


import GTL_API.Models.CreationModels.CardCreation;
import GTL_API.Services.CardService.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("gtl/card")
public class CardController {

    private ICardService iCardService;

    @Autowired
    public void setICardService(ICardService iCardService) {
        this.iCardService = iCardService;
    }

    @RequestMapping(value = "/{cardNumber}", method = RequestMethod.GET)
    public ResponseEntity<?> findCardByNumber(@PathVariable int cardNumber) {
        return new ResponseEntity<>(iCardService.findCardByNumber(cardNumber), new HttpHeaders(), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createCard(@RequestBody @Valid CardCreation cardCreation) {
        return new ResponseEntity<>(iCardService.createCard(cardCreation), new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{cardNumber}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCard(@PathVariable int cardNumber) {
        return new ResponseEntity<>(iCardService.deleteCard(cardNumber), new HttpHeaders(), HttpStatus.OK);
    }


}
