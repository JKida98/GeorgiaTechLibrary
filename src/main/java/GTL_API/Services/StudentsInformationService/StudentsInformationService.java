package GTL_API.Services.StudentsInformationService;

import GTL_API.Models.ReturnModels.StudentsInformationReturn;
import GTL_API.Repositories.StudentsInformationRepository.IStudentsInformationRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentsInformationService implements IStudentsInformationSerice {

    private IStudentsInformationRepositoryCustom iStudentsInformationRepositoryCustom;

    @Autowired
    public void setiStudentsInformationRepositoryCustom(IStudentsInformationRepositoryCustom iStudentsInformationRepositoryCustom){
        this.iStudentsInformationRepositoryCustom = iStudentsInformationRepositoryCustom;
    }

    @Override
    public List<StudentsInformationReturn> getStudentsInformation(int page) {
        return iStudentsInformationRepositoryCustom.findAll(page);
    }
}
