package GTL_API.Repositories.PersonRepository;

import GTL_API.Exceptions.NotFoundException;
import GTL_API.Exceptions.UnknownException;
import GTL_API.Exceptions.UpdateException;
import GTL_API.Handlers.Patcher.PatcherHandler;
import GTL_API.Models.Entities.PersonEntity;
import GTL_API.Models.ReturnModels.PersonReturn;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.Optional;

@Component
public class PersonRepository implements IPersonRepositoryCustom{
    /**
     * Instance of a IPersonRepository interface that extends JPARepository.
     */
    private IPersonRepository iPerson;

    /**
     * Instance of a ModelMapper class.
     */
    private ModelMapper modelMapper;

    /**
     * Instance of a PatchHandler class.
     */
    private PatcherHandler patcherHandler;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public void setPatcherHandler(PatcherHandler patcherHandler) { this.patcherHandler = patcherHandler; }

    /**
     * Instantiation of a IPersonRepository interface.
     *
     * @param iPerson An object that will be assigned to class's personRepository attribute.
     */
    @Autowired
    public void setIPerson(IPersonRepository iPerson) { this.iPerson = iPerson; }

    /**
     * Instantiation of a ModelMapper class.
     *
     * @param modelMapper An object that will be assigned to class's modelMapper attribute.
     */
    @Autowired
    public void setModelMapper(ModelMapper modelMapper) { this.modelMapper = modelMapper; }

    /**
     * Method finds a person by using a passed ssn parameter. First, method findBySsnIfExistsAndReturn is invoked.
     * Its returned value is assigned to foundPerson variable and next, it is mapped to PersonReturn class which is
     * returned back to service method call.
     * @param ssn a unique number of a person stored in a database.
     * @return Found person entity mapped to PersonReturned class.
     */
    @Override
    public PersonReturn findPersonBySsn(String ssn) {
        try{
            PersonEntity foundPerson = findBySsnIfExistsAndReturn(ssn);
            return modelMapper.map(foundPerson, PersonReturn.class);
        } catch (NotFoundException ex){
            throw new NotFoundException(ex.getMessage());
        } catch (Exception e){
            throw new UnknownException(String.format("There was an unknown exception while finding person with " +
                    "ssn: %s", ssn));
        }
    }

    /**
     * Method finds a person by using a passed first name and last name parameters.
     * First, method findByNameIfExistsAndReturn is invoked.
     * Its returned value is assigned to foundPerson variable and next, it is mapped to PersonReturn class which is
     * returned back to service method call.
     * @param firstName and lastName identify a person stored in a database.
     * @return Found person entity mapped to PersonReturned class.
     */
    @Override
    public PersonReturn findPersonByFirstNameAndLastName(String firstName, String lastName) {
        try{
            PersonEntity foundPerson = findByFirstNameAndLastNameIfExistsAndReturn(firstName, lastName);
            return modelMapper.map(foundPerson, PersonReturn.class);
        } catch (NotFoundException ex){
            throw new NotFoundException(ex.getMessage());
        } catch (Exception e){
            throw new UnknownException(String.format("There was an unknown exception while finding person with name" +
                    "%s %s", firstName, lastName));
        }
    }

    /**
     * Method finds a person by using a passed card number parameter. First, method findByCardIfExistsAndReturn is invoked.
     * Its returned value is assigned to foundPerson variable and next, it is mapped to PersonReturn class which is
     * returned back to service method call.
     * @param cardNumberId a unique number of card which belongs to a person stored in a database.
     * @return Found person entity mapped to PersonReturned class.
     */
    @Override
    public PersonReturn findPersonByCardNumberId(Integer cardNumberId) {
        try{
            PersonEntity foundPerson = findByCardNumberIdIfExistsAndReturn(cardNumberId);
            return modelMapper.map(foundPerson, PersonReturn.class);
        } catch (NotFoundException ex){
            throw new NotFoundException(ex.getMessage());
        } catch (Exception e){
            throw new UnknownException(String.format("There was an unknown exception while finding a person with " +
                    "card number: %d", cardNumberId));
        }
    }

    /**
     * A method updates a person that is being stored in a database. First it invokes findBySsnIfExistsAndReturn
     * method that optionally returns a stored record from a database. Next, it takes advantage from patcher handler
     * to fill not null fields of passed method parameter. Having done that, it uses checkIfExistsBySsn method to
     * check if such record exists in a database. At the end, the record is being updated by using
     * iPerson object's save() method, which returns updated object which is later mapped to PersonReturn class
     * and returned.
     * @param personEntity CoverTypeEntity object with new information.
     * @return PersonReturn object with updated information.
     */
    @Override
    public PersonReturn updatePerson(PersonEntity personEntity) {
        try {
            PersonEntity found = findBySsnIfExistsAndReturn(personEntity.getSsn());
            patcherHandler.fillNotNullFields(found, personEntity);
            checkIfExistsBySsn(found);
            PersonEntity updated = iPerson.save(found);
            return modelMapper.map(updated, PersonReturn.class);
        }catch(NotFoundException notFoundException) {
            throw notFoundException;
        } catch (Exception e){
            throw new UpdateException("There was an unexpected error while updating the person");
        }
    }

    /**
     * Creates a person record in a database, by invoking personRepository's method save. Having successfully created
     * the record it is being mapped to PersonReturn class and returned back to service method call.
     * @param personEntity class object with filled information which will be added to a database.
     * @return Created object of PersonReturn class, containing all added information.
     */
    @Override
    public PersonReturn createPerson(PersonEntity personEntity) {
        return null;
        /*try{

        } catch (Exception e) {
            throw new CreationException("There was an unexpected error while updating the person");
        }*/
    }

    @Override
    public PersonReturn findPersonByCardSP(Integer cardNumber) {
        StoredProcedureQuery findPersonByCardNumberProcedure =
                entityManager.createNamedStoredProcedureQuery("findPersonByCardNumber").setParameter("@theCardNumber", cardNumber);
        return modelMapper.map(findPersonByCardNumberProcedure.getSingleResult(), PersonReturn.class);
    }



    /**
     * The methods checks if a record of person is present in a database by performing search by ssn.
     * @param ssn String class's object.
     * @return Found person of PersonEntity class.
     */


    private PersonEntity findBySsnIfExistsAndReturn(String ssn) {
        Optional<PersonEntity> foundPerson;
        String message;
        foundPerson = iPerson.findBySsnIs(ssn);
        message = String.format("Person with ssn: %s was not found", ssn);

        if (foundPerson.isPresent()) {
            return foundPerson.get();
        } else {
            throw new NotFoundException(message);
        }
    }

    /**
     * The methods checks if a record of person is present in a database by performing search by first name and last name.
     * @param firstName and lastName String class's object.
     * @return Found person of PersonEntity class.
     */
    private PersonEntity findByFirstNameAndLastNameIfExistsAndReturn(String firstName, String lastName) {


        Optional<PersonEntity> foundPerson = iPerson.findByFirstNameIsAndLastNameIs(firstName, lastName);
        String message = String.format("Person with name: %s %s was not found", firstName, lastName);

        if (foundPerson.isPresent()) {
            return foundPerson.get();
        } else {
            throw new NotFoundException(message);
        }
    }

    /**
     * The methods checks if a record of person is present in a database by performing search by card number id.
     * @param cardNumberId String class's object.
     * @return Found person of PersonEntity class.
     */
    private PersonEntity findByCardNumberIdIfExistsAndReturn(Integer cardNumberId) {
        Optional<PersonEntity> foundPerson;
        String message;
        foundPerson = iPerson.findByCardNumberIdIs(cardNumberId);
        message = String.format("Person with card number: %s was not found", cardNumberId);

        if (foundPerson.isPresent()) {
            return foundPerson.get();
        } else {
            throw new NotFoundException(message);
        }
    }

    /**
     * The method checks if there exists a record in a database with the same ssn as one that
     * passed parameter has. If there is no such record, the method throws NotFoundException.
     * @param personEntity An object of CoverTypeEntity class.
     */
    private void checkIfExistsBySsn(PersonEntity personEntity){
        if (iPerson.countAllBySsn(personEntity.getSsn())==0){
            throw new NotFoundException("You tried to update a person who does not exist in the database. Choose differetn ssn");
        }
    }

}

