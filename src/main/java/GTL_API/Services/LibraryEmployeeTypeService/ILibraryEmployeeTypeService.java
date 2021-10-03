package GTL_API.Services.LibraryEmployeeTypeService;

import GTL_API.Models.ReturnModels.LibraryEmployeeTypeReturn;
import GTL_API.Models.CreationModels.LibraryEmployeeTypeCreation;
import GTL_API.Models.UpdateModels.LibraryEmployeeTypeUpdate;

import java.util.List;

public interface ILibraryEmployeeTypeService {
    LibraryEmployeeTypeReturn findLibraryEmployeeTypeById(int id);
    LibraryEmployeeTypeReturn createLibraryEmployeeType(LibraryEmployeeTypeCreation libraryEmployeeType);
    LibraryEmployeeTypeReturn updateLibraryEmployeeType(int id,LibraryEmployeeTypeUpdate libraryEmployeeType);
    List<LibraryEmployeeTypeReturn> findAll();
}
