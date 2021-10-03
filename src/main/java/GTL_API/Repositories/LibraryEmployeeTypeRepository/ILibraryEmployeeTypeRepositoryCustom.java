package GTL_API.Repositories.LibraryEmployeeTypeRepository;

import GTL_API.Models.CreationModels.LibraryEmployeeTypeCreation;
import GTL_API.Models.Entities.LibraryEmployeeTypeEntity;
import GTL_API.Models.ReturnModels.LibraryEmployeeTypeReturn;
import GTL_API.Models.UpdateModels.LibraryEmployeeTypeUpdate;

import java.util.List;

public interface ILibraryEmployeeTypeRepositoryCustom {
    LibraryEmployeeTypeReturn findLibraryEmployeeTypeById(int id);
    List<LibraryEmployeeTypeReturn> getAllLibraryEmployeeTypes();
    LibraryEmployeeTypeReturn createLibraryEmployeeType(LibraryEmployeeTypeCreation letCreate);
    boolean checkIfExistsById(int number);
    LibraryEmployeeTypeReturn updateLibraryEmployeeType(int id,LibraryEmployeeTypeUpdate letUpdate);
}
