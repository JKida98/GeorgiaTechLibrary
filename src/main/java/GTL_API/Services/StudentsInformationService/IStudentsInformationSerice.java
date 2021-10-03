package GTL_API.Services.StudentsInformationService;

import GTL_API.Models.ReturnModels.StudentsInformationReturn;

import java.util.List;

public interface IStudentsInformationSerice {
    List<StudentsInformationReturn> getStudentsInformation(int page);
}
