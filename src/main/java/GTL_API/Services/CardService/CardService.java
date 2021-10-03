package GTL_API.Services.CardService;

import GTL_API.Models.CreationModels.CardCreation;
import GTL_API.Models.Entities.CardEntity;
import GTL_API.Models.ReturnModels.CardReturn;
import GTL_API.Repositories.CardRepository.ICardRepositoryCustom;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class CardService implements ICardService {

    /**
     * Instance of card repository interface.
     */
    private ICardRepositoryCustom iCardRepository;

    /**
     * Instance of model mapper class.
     */
    private ModelMapper modelMapper;

    /**
     * Minimal value from which a card number is going to be generated.
     */
    private static final int MINIMAL_VALUE = 1000000000;

    /**
     * Maximal value to which a card number is going to be generated.
     */
    private static final int MAXIMAL_VALUE = 2147483647;


    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setICardRepository(ICardRepositoryCustom iCardRepository) {
        this.iCardRepository = iCardRepository;
    }

    /**
     * Runs a finding method from card repository by passing a card number.
     * @param number specific number that identifies a card.
     * @return Found CartReturn object with filled information.
     */
    @Override
    public CardReturn findCardByNumber(int number) {
        return iCardRepository.findCardByNumber(number);
    }

    /**
     * Generates a random number which existence is checked in a database. If the number is unique
     * then it is assigned to a passed object. Otherwise it keeps generating the number. Invokes iCardRepository's
     * creation method by passing mapped entity object.
     * @param cardCreation a card creation object that need to be passed in order to create a new card.
     * @return A CartReturn object with filled information.
     */
    @Override
    public CardReturn createCard(CardCreation cardCreation) {
        boolean isNotValid = true;
        int generatedNumber = 0;
        while (isNotValid) {
            int generatedCardNumber = generateNumber();
            boolean exists = validateCardNumber(generatedCardNumber);
            if (!exists) {
                isNotValid = false;
                generatedNumber = generatedCardNumber;
            }
        }
        cardCreation.setNumber(generatedNumber);
        return iCardRepository.createCard(modelMapper.map(cardCreation, CardEntity.class));
    }

    /**
     * Invokes iCardRepository's deletion method by passing a card number.
     * @param number number of card that is stored in a database that is desired to be deleted
     * @return String informing whether or not was the card removed.
     */
    @Override
    public String deleteCard(int number) {
        boolean result = iCardRepository.deleteCard(number);
        if(result){
            return String.format("Card with number: %d was successfully deleted.", number);
        }else{
            return String.format("Card with number: %d was not deleted.", number);
        }
    }

    /**
     * Generates a random integer value in range from MINIMAL_VALUE to MAXIMUM_VALUE
     * @return A randomly generated number.
     */
    private int generateNumber() {
        return new Random().nextInt((MAXIMAL_VALUE - MINIMAL_VALUE) + 1) + MINIMAL_VALUE;
    }

    /**
     * Invokes a iCardRepository's method that checks if there exists a card with certain number.
     * @param number a number that has been generated
     * @return boolean value determining if a card with passed number exists or not
     */
    private boolean validateCardNumber(int number) {
        return iCardRepository.checkIfExistsByCardNumber(number);
    }
}
