package GTL_API.Services.LibraryEmployeeTypeService;

import GTL_API.Models.Entities.LibraryEmployeeTypeEntity;
import GTL_API.Models.ReturnModels.LibraryEmployeeTypeReturn;
import GTL_API.Models.UpdateModels.LibraryEmployeeTypeUpdate;
import GTL_API.Models.CreationModels.LibraryEmployeeTypeCreation;
import GTL_API.Repositories.LibraryEmployeeTypeRepository.ILibraryEmployeeTypeRepositoryCustom;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryEmployeeTypeService implements ILibraryEmployeeTypeService {

    private ILibraryEmployeeTypeRepositoryCustom repo;

    @Autowired
    public void setRepo(ILibraryEmployeeTypeRepositoryCustom repo){
        this.repo=repo;
    }

    @Override
    public LibraryEmployeeTypeReturn findLibraryEmployeeTypeById(int id) {
        return repo.findLibraryEmployeeTypeById(id);
    }

    @Override
    public LibraryEmployeeTypeReturn createLibraryEmployeeType(LibraryEmployeeTypeCreation libraryEmployeeType) {
        return repo.createLibraryEmployeeType(libraryEmployeeType);
    }

    @Override
    public LibraryEmployeeTypeReturn updateLibraryEmployeeType(int id,LibraryEmployeeTypeUpdate libraryEmployeeType) {
        return repo.updateLibraryEmployeeType(id,libraryEmployeeType);
    }

    @Override
    public List<LibraryEmployeeTypeReturn> findAll() {
        return repo.getAllLibraryEmployeeTypes();
    }
}
