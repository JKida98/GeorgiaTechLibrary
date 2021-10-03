package GTL_API.Repositories;

import GTL_API.Exceptions.DuplicateException;
import GTL_API.Exceptions.NotFoundException;
import GTL_API.Exceptions.UnknownException;
import GTL_API.Exceptions.UpdateException;
import GTL_API.MainApplicationClass;
import GTL_API.Models.CreationModels.LibraryEmployeeTypeCreation;
import GTL_API.Models.Entities.CardEntity;
import GTL_API.Models.Entities.CoverTypeEntity;
import GTL_API.Models.Entities.LibraryEmployeeTypeEntity;
import GTL_API.Models.ReturnModels.CardReturn;
import GTL_API.Models.ReturnModels.CoverTypeReturn;
import GTL_API.Models.ReturnModels.LibraryEmployeeTypeReturn;
import GTL_API.Repositories.CardRepository.CardRepository;
import GTL_API.Repositories.CardRepository.ICardRepository;
import GTL_API.Repositories.CoverTypeRepository.CoverTypeRepository;
import GTL_API.Repositories.CoverTypeRepository.ICoverTypeRepository;
import GTL_API.Repositories.LibraryEmployeeTypeRepository.ILibraryEmployeeTypeRepository;
import GTL_API.Repositories.LibraryEmployeeTypeRepository.LibraryEmployeeTypeRepository;
import GTL_API.TestDataSourceConfig;
import GTL_API.Repositories.CoverTypeRepository.ICoverTypeRepositoryCustom;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MainApplicationClass.class, TestDataSourceConfig.class})

public class LibraryEmployeeTypeRepositoryTest {

    @Autowired
    private LibraryEmployeeTypeRepository letRepo;



    @InjectMocks
    private LibraryEmployeeTypeRepository letRepoInjected;

    @Mock
    private ILibraryEmployeeTypeRepository iLetRepoMock;

    @Mock
    private ModelMapper modelMapperMock;

    /**
     * The test method finds a library employee type number by its id by using letRepo instance and asserts.
     * The assertion expects the class of the returned object to be LibraryEmployeeTypeReturn
     */
    @Test
    public void findLibraryEmployeeTypeById_returnsLibraryEmployeeTypeReturn(){
        LibraryEmployeeTypeReturn found = letRepo.findLibraryEmployeeTypeById(1);
        Assert.assertEquals(found.getClass(),LibraryEmployeeTypeReturn.class);
    }

    /**
     * The test method tries to find a library employee type that does not exist in a database. It expects NotFoundException
     * to be thrown.
     */
    @Test(expected = NotFoundException.class)
    public void findLibraryEmployeeTypeById_throwsNotFoundException(){
        letRepo.findLibraryEmployeeTypeById(-5);
    }

    /**
     * The test method checks if during finding a library employee type an unexpected error occurs, then
     * it is caught and thrown. It asserts the exception's message to expected one.
     */
    @Test
    public void findById_throwsUnknownErrorWhenSomethingUnexpectedHappens(){
        try{
            MockitoAnnotations.initMocks(this);
            LibraryEmployeeTypeEntity entity=new LibraryEmployeeTypeEntity();
            entity.setId(6);
            Mockito.when(modelMapperMock.map(Mockito.any(), Mockito.any())).thenThrow(new RuntimeException());
            Mockito.when(iLetRepoMock.findByIdIs(Mockito.anyInt())).thenReturn(Optional.of(entity));
            letRepoInjected.findLibraryEmployeeTypeById(6);
        }catch(UnknownException unknownException){
            Assert.assertEquals("There was an unknown exception while finding a library employee type with id: 6", unknownException.getMessage());
        }
    }


    /**
     * The test method tries to create a library employee type as a null value. Unknown exception is expected to be thrown.
     */
    @Test
    public void createLETAsNull_throwsUnknownError(){
        try{
            letRepo.createLibraryEmployeeType(null);
        }catch (UnknownException unknownException){
            Assert.assertEquals("Unknown error while creating a new library employee type.", unknownException.getMessage());
        }
    }

    /**
     * The test method asserts that UnknownException would be returned if unexpected error would raise while creating a
     * libraryEmployeeType. Unknown exception is expected to be thrown in such case.
     */
    @Test
    public void createLibraryEmployeeType_throwsUnknownErrorWhenSomethingUnexpectedHappensAndDoesNotAddToADatabase(){
        try{
            MockitoAnnotations.initMocks(this);
            Mockito.when(iLetRepoMock.save(Mockito.any())).thenThrow(new RuntimeException());
            letRepoInjected.createLibraryEmployeeType(getLibraryEmployeeTypeEntity());
        }catch (UnknownException unknownException){
            Assert.assertEquals("Unknown error while creating a new library employee type.", unknownException.getMessage());
        }
    }

    /**
     * The test method checks if letRepo's checkIfExistsById method successfully determines
     * the existence of a LET that is present in a database.
     */
    @Test
    public void checkIfExistsById_returnsTrueIfExists(){
        Assert.assertTrue(letRepo.checkIfExistsById(1));
    }

    /**
     * The test method checks if letRepo's checkIfExistsById returns false if
     * the LET that is not present in a database.
     */
    @Test
    public void checkIfExistsById_returnsFalseIfDoesNotExist(){
        Assert.assertFalse(letRepo.checkIfExistsById(-5));
    }

    /**
     * The test method asserts that UnknownException would be returned
     * if unexpected error would raise while determining the existence of a LET in a database.
     */
    @Test
    public void checkIfExistsById_throwsUnknownErrorWhenSomethingUnexpectedHappens(){
        try{
            MockitoAnnotations.initMocks(this);
            Mockito.when(iLetRepoMock.existsByIdIs(Mockito.anyInt())).thenThrow(new RuntimeException());
            letRepoInjected.checkIfExistsById(1);
        }catch (UnknownException unknownException){
            Assert.assertEquals("Unknown error while determining an existence of a library employee type with id 1.",
                    unknownException.getMessage());
        }
    }

    /**
     * Creates object of LibraryEmployeeTypeEntity class.
     * @return created object with all information filled.
     */
    private LibraryEmployeeTypeCreation getLibraryEmployeeTypeEntity(){
        LibraryEmployeeTypeCreation entity = new LibraryEmployeeTypeCreation();
        entity.setType("Test");
        entity.setHourlyWage(1.0);
        entity.setId(9999);
        return entity;
    }
}