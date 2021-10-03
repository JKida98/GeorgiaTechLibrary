package GTL_API.Repositories.LibraryEmployeeTypeRepository;

import GTL_API.Models.CreationModels.LibraryEmployeeTypeCreation;
import GTL_API.Models.Entities.CardEntity;
import GTL_API.Models.Entities.CoverTypeEntity;
import GTL_API.Models.Entities.LibraryEmployeeTypeEntity;
import GTL_API.Models.ReturnModels.CardReturn;
import GTL_API.Models.ReturnModels.CoverTypeReturn;
import GTL_API.Models.ReturnModels.LibraryEmployeeTypeReturn;
import GTL_API.Models.UpdateModels.LibraryEmployeeTypeUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;
import GTL_API.Exceptions.UnknownException;
import GTL_API.Exceptions.UpdateException;
import GTL_API.Handlers.Patcher.PatcherHandler;
import GTL_API.Exceptions.DuplicateException;
import GTL_API.Exceptions.NotFoundException;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.util.Optional;
import java.util.List;

@Component
public class LibraryEmployeeTypeRepository implements ILibraryEmployeeTypeRepositoryCustom {
    /**
     * Instance of a ILibraryEmployeeTypeRepository interface that extends JPARepository.
     */
    private ILibraryEmployeeTypeRepository libraryEmployeeTypeRepository;

    /**
     * Instance of a ModelMapper class.
     */
    private ModelMapper modelMapper;

    /**
     * Instance of a PatcherHandler class.
     */
    private PatcherHandler patcherHandler;

    /**
     * Instantiation of a PatcherHandler class.
     *
     * @param patcherHandler An object that will be assigned to class's patcherHandler attribute.
     */
    @Autowired
    public void setPatcherHandler(PatcherHandler patcherHandler) {
        this.patcherHandler = patcherHandler;
    }

    /**
     * Instantiation of a LibraryEmployeeTypeRepository class
     *
     * @param libraryEmployeeTypeRepository An object that will be assigned to class's libraryEmployeeTypeRepository attribute.
     */
    @Autowired
    public void setlibraryEmployeeTypeRepository(ILibraryEmployeeTypeRepository libraryEmployeeTypeRepository){
        this.libraryEmployeeTypeRepository=libraryEmployeeTypeRepository;
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

    @Override
    public LibraryEmployeeTypeReturn findLibraryEmployeeTypeById(int id) {
        try {
            Optional<LibraryEmployeeTypeEntity> foundLET = libraryEmployeeTypeRepository.findByIdIs(id);
            if(foundLET.isPresent()){
                LibraryEmployeeTypeReturn returnType=modelMapper.map(foundLET.get(), LibraryEmployeeTypeReturn.class);
                System.out.println(returnType);
                return returnType;
            }
            else throw new NotFoundException("Library employee type not found");
        } catch (NotFoundException notFoundException) {
            throw notFoundException;
        } catch (Exception e) {
            throw new UnknownException(String.format("There was an unknown exception while finding a library employee" +
                    " type with id: %d", id));
        }
    }

    @Override
    public List<LibraryEmployeeTypeReturn> getAllLibraryEmployeeTypes() {
        try {
            List<LibraryEmployeeTypeEntity> foundLETs = libraryEmployeeTypeRepository.findAll();
            if (foundLETs.isEmpty()) {
                throw new NotFoundException("There are no employee types");
            } else {
                Type listType = new TypeToken<List<LibraryEmployeeTypeReturn>>() {
                }.getType();
                return modelMapper.map(foundLETs, listType);
            }
        }catch (NotFoundException notFoundException) {
            throw notFoundException;
        } catch (Exception e) {
            throw new UnknownException(String.format("There was an unknown exception while querying all library employee types"));
        }
    }

    @Override
    public LibraryEmployeeTypeReturn createLibraryEmployeeType(LibraryEmployeeTypeCreation letCreate) {
        try {
            LibraryEmployeeTypeEntity newLet = modelMapper.map(letCreate,LibraryEmployeeTypeEntity.class);
            LibraryEmployeeTypeEntity added = libraryEmployeeTypeRepository.save(newLet);
            return modelMapper.map(added, LibraryEmployeeTypeReturn.class);
        }catch (Exception e) {
            throw new UnknownException("Unknown error while creating a new library employee type.");
        }
    }

    @Override
    public boolean checkIfExistsById(int id) {
        try {
            return libraryEmployeeTypeRepository.existsByIdIs(id);
        } catch (Exception e) {
            throw new UnknownException(String.format("Unknown error while " +
                    "determining an existence of a library employee type with id %d.", id));
        }
    }

    @Override
    public LibraryEmployeeTypeReturn updateLibraryEmployeeType(int id,LibraryEmployeeTypeUpdate letUpdate) {
        try {
            Optional<LibraryEmployeeTypeEntity> found = libraryEmployeeTypeRepository.findByIdIs(id);
            if(found.isPresent()){
                LibraryEmployeeTypeEntity foundObj=found.get();
                patcherHandler.fillNotNullFields(foundObj, modelMapper.map(letUpdate,LibraryEmployeeTypeEntity.class));
                LibraryEmployeeTypeEntity updated = libraryEmployeeTypeRepository.save(foundObj);
                return modelMapper.map(updated, LibraryEmployeeTypeReturn.class);
            }
            else throw new NotFoundException("Library employee type not found");
        } catch (NotFoundException notFoundException){
            throw notFoundException;
        } catch (DuplicateException duplicateException) {
            throw duplicateException;
        } catch (Exception e) {
            throw new UpdateException("There was an unexpected error while updating the cover type.");
        }
    }

}
