package GTL_API.Repositories;

import GTL_API.MainApplicationClass;
import GTL_API.Models.Entities.PersonEntity;
import GTL_API.Models.ReturnModels.PersonReturn;
import GTL_API.Repositories.PersonRepository.IPersonRepositoryCustom;
import GTL_API.TestDataSourceConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MainApplicationClass.class, TestDataSourceConfig.class})
public class PersonRepositoryTest {


    @Autowired
    private IPersonRepositoryCustom personRepository;

    private final PersonEntity personEntity = new PersonEntity();


    @Test
    public void whenFindBySsn_thenReturnPersonReturn(){

        PersonReturn found = personRepository.findPersonBySsn("000-71-3764");

        Assert.assertEquals("000-71-3764", found.getSsn());
    }

    @Test
    public void whenFindByName_thenReturnPersonReturn(){

        PersonReturn found = personRepository.findPersonByFirstNameAndLastName("Chastity", "Crane");

        Assert.assertEquals("003-02-7546", found.getSsn());
    }

    @Test
    public void whenFindByCard_thenReturnPersonReturn(){

        PersonReturn found = personRepository.findPersonByCardNumberId(1209995103);

        Assert.assertEquals(1209995103, found.getCardNumberId());
    }

    @Test
    public void whenUpdatePerson_thenReturnPersonReturn(){
        personEntity.setFirstName("Test");
        personEntity.setLastName("TestLast");
        personEntity.setSsn("000-71-3764");

        PersonReturn found = personRepository.updatePerson(personEntity);

        Assert.assertEquals("Test", found.getFirstName());
        Assert.assertEquals("TestLast", found.getLastName());

        cleanup();
    }

    public void cleanup(){
        PersonEntity person = new PersonEntity();
        person.setSsn("000-71-3764");
        person.setFirstName("Bart");
        person.setMiddleName("Aaron Prince");
        person.setLastName("Bennet");
        person.setHomeAddressId(3052);
        person.setCampusAddressId(3052);
        person.setLoanDuration(21);
        person.setPersonTypeId(102084);

        personRepository.updatePerson(person);
    }

}
