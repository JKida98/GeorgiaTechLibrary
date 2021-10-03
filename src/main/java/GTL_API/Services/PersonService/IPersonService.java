package GTL_API.Services.PersonService;

import GTL_API.Models.CreationModels.PersonCreation;
import GTL_API.Models.ReturnModels.PersonReturn;
import GTL_API.Models.UpdateModels.PersonUpdate;

public interface IPersonService {
    PersonReturn findPersonBySsn(String ssn);

    PersonReturn findPersonByFirstNameAndLastName(String firstName, String lastName);

    PersonReturn findPersonByCardNumberId(Integer cardNumberId);

    PersonReturn updatePerson(PersonUpdate person);

    PersonReturn createPerson(PersonCreation person);
}
