package GTL_API.Services.PersonService;

import GTL_API.Models.CreationModels.PersonCreation;
import GTL_API.Models.Entities.PersonEntity;
import GTL_API.Models.ReturnModels.PersonReturn;
import GTL_API.Models.UpdateModels.PersonUpdate;
import GTL_API.Repositories.PersonRepository.IPersonRepositoryCustom;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements IPersonService {

    /**
     * Instance of card repository interface.
     */
    private IPersonRepositoryCustom personRepositoryCustom;

    /**
     * Instance of model mapper class.
     */
    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) { this.modelMapper = modelMapper; }

    @Autowired
    public void setIPersonCustomRepository(IPersonRepositoryCustom personRepositoryCustom){
        this.personRepositoryCustom = personRepositoryCustom;
    }

    /**
     * Runs a finding method from person repository by passing a ssn.
     * @param ssn specific number that identifies the person.
     * @return Found PersonReturn object with filled information.
     */
    @Override
    public PersonReturn findPersonBySsn(String ssn) {
        return personRepositoryCustom.findPersonBySsn(ssn);
    }

    /**
     * Runs a finding method from person repository by passing the first name and last name.
     * @param firstName and lastName specific names that identifies a person.
     * @return Found personReturn object with filled information.
     */
    @Override
    public PersonReturn findPersonByFirstNameAndLastName(String firstName, String lastName) {
        return personRepositoryCustom.findPersonByFirstNameAndLastName(firstName, lastName);
    }

    /**
     * Runs a finding method from person repository by passing a card number.
     * @param cardNumberId specific number that identifies the card which belongs to specific person.
     * @return Found PersonReturn object with filled information.
     */
    @Override
    public PersonReturn findPersonByCardNumberId(Integer cardNumberId) {
        return personRepositoryCustom.findPersonByCardSP(cardNumberId);
    }

    /**
     * Runs an updating method from person repository by passing a ssn and updated data.
     * @param person specific object that identifies the person.
     * @return Found PersonReturn object with filled updated information information.
     */
    @Override
    public PersonReturn updatePerson(PersonUpdate person) {
        return personRepositoryCustom.updatePerson(modelMapper.map(person, PersonEntity.class));
    }

    /**
     * Runs a creation method from person repository by passing a mapped person object to personEntity.
     * @param person specific object that identifies the new person that is to be added.
     * @return Found PersonReturn object with filled information about newly inserted person.
     */
    @Override
    public PersonReturn createPerson(PersonCreation person) {
        return personRepositoryCustom.createPerson(modelMapper.map(person, PersonEntity.class));
    }
}
