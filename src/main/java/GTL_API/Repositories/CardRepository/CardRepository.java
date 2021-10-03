package GTL_API.Repositories.CardRepository;

import GTL_API.Exceptions.NotFoundException;
import GTL_API.Exceptions.UnknownException;
import GTL_API.Models.Entities.CardEntity;
import GTL_API.Models.ReturnModels.CardReturn;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class CardRepository implements ICardRepositoryCustom {

    /**
     * Instance of a ICardRepository interface that extends JPARepository.
     */
    private ICardRepository cardRepository;

    /**
     * Instance of a ModelMapper class.
     */
    private ModelMapper modelMapper;

    /**
     * Instantiation of a ICardRepository interface.
     *
     * @param cardRepository An object that will be assigned to class's cardRepository attribute.
     */
    @Autowired
    public void setCardRepository(ICardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    /**
     * Instantiation of a ModelMapper class.
     *
     * @param modelMapper An object that will be assigned to class's modelMapper attribute.
     */
    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * Method finds a card by using a passed number parameter. First, method findIfExistsAndReturn is invoked.
     * Its returned value is assigned to foundCard variable and next, it is mapped to CardReturn class which is
     * returned back to service method call.
     * @param number a identification number of a card stored in a database.
     * @return Found card entity mapped to CardReturned class.
     */
    @Override
    public CardReturn findCardByNumber(int number) {
        try {
            CardEntity foundCard = findIfExistsAndReturn(number);
            return modelMapper.map(foundCard, CardReturn.class);
        }catch(NotFoundException notFoundException){
            throw notFoundException;
        }catch (Exception e) {
            throw new UnknownException(String.format("Unknown error while finding card with number: %d", number));
        }
    }

    /**
     * Creates a card record in a database, by invoking cardRepository's method save. Having successfully created
     * the record it is being mapped to CardReturn class and returned back to service method call.
     * @param cardEntity CardEntity class object with filled information which will be added to a database.
     * @return Created object of CardReturn class, containing all added information.
     */
    @Override
    public CardReturn createCard(CardEntity cardEntity) {
        try {
            cardEntity.setDeleted(false);
            CardEntity added = cardRepository.save(cardEntity);
            return modelMapper.map(added, CardReturn.class);
        }catch (Exception e) {
            throw new UnknownException("Unknown error while creating a new card.");
        }
    }

    /**
     * The method invokes cardRepository's method existsByNumber by passing card number as a parameter. The method's
     * return value determines if there exists a card with passed number in a database.
     * @param number A number that is being checked against its existence in a database.
     * @return Appropriate boolean value.
     */
    @Override
    public boolean checkIfExistsByCardNumber(int number) {
        try {
            return cardRepository.existsByNumber(number);
        } catch (Exception e) {
            throw new UnknownException(String.format("Unknown error while " +
                    "determining an existence of a card with number %d.", number));
        }
    }

    /**
     * The method sets database's records isDeleted column value to false. It returns record's updated isDeleted
     * attribute value to state if it was deleted or not.
     * @param number a number of card that is desired to be deleted.
     * @return Boolean value determining the deletion operation result.
     */
    @Override
    public boolean deleteCard(int number) {
        try {
            CardEntity found = findIfExistsAndReturn(number);
            found.setDeleted(true);
            CardEntity result = cardRepository.save(found);
            return result.getDeleted();
        } catch (NotFoundException notFoundException){
            throw notFoundException;
        } catch (Exception e) {
            throw new UnknownException(String.format("Unknown error while deleting a card with number: %d.", number));
        }
    }

    /**
     * The method uses cardRepository's method findByNumberIs to find and optionally return an object of a CardEntity
     * class.
     * @param number a number of card that is wanted to be found.
     * @return Found card object of CardEntity class.
     */
    private CardEntity findIfExistsAndReturn(int number) {
        Optional<CardEntity> foundCard = cardRepository.findByNumberIs(number);
        if (foundCard.isPresent()) {
            return foundCard.get();
        } else {
            throw new NotFoundException(String.format("Card with number %s was not found.", number));
        }
    }
}
