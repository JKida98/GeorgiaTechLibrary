package GTL_API.Repositories.NotReturnedRepository;

import GTL_API.Models.Entities.PeopleWhoDidNotReturnedBooksYetEntity;
import GTL_API.Models.ReturnModels.AvailableBooksReturn;
import GTL_API.Models.ReturnModels.PeopleWhoDidNotReturnedBooksYetReturn;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

@Component
public class NotReturnedRepository implements INotReturnedRepositoryCustom {

    private INotReturnedRepository iNotReturnedRepository;

    private ModelMapper modelMapper;

    private Type listType = new TypeToken<List<PeopleWhoDidNotReturnedBooksYetReturn>>() {
    }.getType();

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setINotReturnedRepository(INotReturnedRepository iNotReturnedRepository) {
        this.iNotReturnedRepository = iNotReturnedRepository;
    }

    @Override
    public List<PeopleWhoDidNotReturnedBooksYetReturn> getListPeopleWhoDidNotReturnedBooks() {
        List<PeopleWhoDidNotReturnedBooksYetEntity> found = iNotReturnedRepository.findAllBySsnIsNotNull();
        return modelMapper.map(found, listType);
    }
}
