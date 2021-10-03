package GTL_API.Services.CardService;

import GTL_API.Models.CreationModels.CardCreation;
import GTL_API.Models.ReturnModels.CardReturn;

public interface ICardService {

    CardReturn findCardByNumber(int number);

    CardReturn createCard(CardCreation cardCreation);

    String deleteCard(int number);
}
