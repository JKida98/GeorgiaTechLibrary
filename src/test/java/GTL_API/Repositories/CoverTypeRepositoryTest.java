package GTL_API.Repositories;

import GTL_API.Exceptions.DuplicateException;
import GTL_API.Exceptions.NotFoundException;
import GTL_API.Exceptions.UnknownException;
import GTL_API.Exceptions.UpdateException;
import GTL_API.MainApplicationClass;
import GTL_API.Models.Entities.CoverTypeEntity;
import GTL_API.Models.ReturnModels.CoverTypeReturn;
import GTL_API.Repositories.CoverTypeRepository.CoverTypeRepository;
import GTL_API.Repositories.CoverTypeRepository.ICoverTypeRepository;
import GTL_API.TestDataSourceConfig;
import GTL_API.Repositories.CoverTypeRepository.ICoverTypeRepositoryCustom;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MainApplicationClass.class, TestDataSourceConfig.class})
public class CoverTypeRepositoryTest {

    @InjectMocks
    private CoverTypeRepository coverTypeRepositoryInjected;

    @Spy
    private ICoverTypeRepository iCoverTypeRepositorySpy;

    @Mock
    private ModelMapper modelMapperMock;

    @Autowired
    private CoverTypeRepository coverTypeRepository;

    /**
     * The test method checks finding a CoverTypeReturn class works correctly.
     */
    @Test
    public void findCoverTypeByName_thenReturnCoverTypeReturn() {
        CoverTypeReturn found = coverTypeRepository.findCoverTypeByName("Paperback");
        Assert.assertEquals("Paperback", found.getCoverType());
    }

    /**
     * The test method checks if NotFoundException is being thrown while searching for a record in a database, which
     * does not exist there.
     */
    @Test(expected = NotFoundException.class)
    public void findCoverTypeByName_shouldThrowNotFoundException() {
        coverTypeRepository.findCoverTypeByName("NotExisting");
    }

    /**
     * The test method checks if update operation successfully changes the state of a record in a database.
     */
    @Test
    public void updateCoverType_shouldChangeName() {
        CoverTypeEntity coverTypeUpdate = new CoverTypeEntity();
        coverTypeUpdate.setId(1);
        coverTypeUpdate.setCoverType("Updated");

        coverTypeRepository.updateCoverType(coverTypeUpdate);

        Assert.assertEquals("Updated", coverTypeRepository.findCoverTypeByName("Updated").getCoverType());

        CoverTypeEntity coverTypeCleanUp = new CoverTypeEntity();
        coverTypeCleanUp.setId(1);
        coverTypeCleanUp.setCoverType("Paperback");
        coverTypeRepository.updateCoverType(coverTypeCleanUp);
    }

    /**
     * The test method check is DuplicateException is being thrown when there is an attempt to add duplicate records
     * to a database.
     */
    @Test(expected = DuplicateException.class)
    public void updateCoverType_shouldThrowDuplicateException(){
        CoverTypeEntity coverTypeUpdate = new CoverTypeEntity();
        coverTypeUpdate.setId(1);
        coverTypeUpdate.setCoverType("Paperback");

        coverTypeRepository.updateCoverType(coverTypeUpdate);
    }

    /**
     * The test method checks if an UnknownException is being thrown when some unexpected error occurs while
     * finding a cover type.
     */
    @Test
    public void findCoverType_shouldThrowUnknownExceptionIfSomethingUnexpectedHappens(){
        try{
            MockitoAnnotations.initMocks(this);
            Mockito.when(modelMapperMock.map(Mockito.any(), Mockito.any())).thenThrow(new RuntimeException());
            Mockito.doReturn(Optional.of(new CoverTypeEntity())).when(iCoverTypeRepositorySpy).findByCoverTypeIs(Mockito.anyString());
            coverTypeRepositoryInjected.findCoverTypeByName("Not pass");
        }catch (UnknownException e){
            Assert.assertEquals("There was an unknown exception while finding a cover type with name: Not pass", e.getMessage());
        }
    }

    /**
     * The test method checks if an UpdateException is being thrown when some unexpected error happens while updating
     * a cover type.
     */
    @Test
    public void updateCoverType_shouldThrowUnknownExceptionIfSomethingUnexpectedHappens(){
        try{
            MockitoAnnotations.initMocks(this);
            Mockito.when(modelMapperMock.map(Mockito.any(), Mockito.any())).thenThrow(new RuntimeException());
            Mockito.doReturn(Optional.of(new CoverTypeEntity())).when(iCoverTypeRepositorySpy).findByCoverTypeIs(Mockito.anyString());
            coverTypeRepositoryInjected.updateCoverType(new CoverTypeEntity());
        }catch (UpdateException e){
            Assert.assertEquals("There was an unexpected error while updating the cover type.", e.getMessage());
        }
    }

}
