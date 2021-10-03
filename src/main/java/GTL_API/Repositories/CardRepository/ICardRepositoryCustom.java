package GTL_API.Repositories.CardRepository;


import GTL_API.Models.Entities.CardEntity;
import GTL_API.Models.ReturnModels.CardReturn;


public interface ICardRepositoryCustom {
    CardReturn findCardByNumber(int number);

    CardReturn createCard(CardEntity cardEntity);

    boolean checkIfExistsByCardNumber(int number);

    boolean deleteCard(int number);
}
