package GTL_API.Repositories.StudentsInformationRepository;

import GTL_API.Models.Entities.StudentsInformationEntity;
import GTL_API.Models.ReturnModels.AvailableBooksReturn;
import GTL_API.Models.ReturnModels.StudentsInformationReturn;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import java.lang.reflect.Type;
import java.util.List;

@Component
public class StudentsInformationRepository implements IStudentsInformationRepositoryCustom{
    private IStudentsInformationRepository iStudentsInformationRepository;
    private ModelMapper modelMapper;

    private Type listType = new TypeToken<List<AvailableBooksReturn>>() {
    }.getType();

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) { this.modelMapper = modelMapper; }

    @Autowired
    public void setiStudentsInformationRepository(IStudentsInformationRepository iStudentsInformationRepository){
        this.iStudentsInformationRepository = iStudentsInformationRepository;
    }


    @Override
    public List<StudentsInformationReturn> findAll(int page) {
        return modelMapper.map(iStudentsInformationRepository.findAllBySsnIsNotNull(PageRequest.of(page, 30)), listType);
    }
}
