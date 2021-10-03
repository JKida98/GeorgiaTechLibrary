package GTL_API.Services.NotReturnedService;

import GTL_API.Models.ReturnModels.PeopleWhoDidNotReturnedBooksYetReturn;
import GTL_API.Repositories.NotReturnedRepository.INotReturnedRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotReturnedService implements INotReturnedService {

    private INotReturnedRepositoryCustom iNotReturnedRepositoryCustom;


    @Autowired
    public void setINotReturnedRepositoryCustom(INotReturnedRepositoryCustom iNotReturnedRepositoryCustom) {
        this.iNotReturnedRepositoryCustom = iNotReturnedRepositoryCustom;
    }

    @Override
    public List<PeopleWhoDidNotReturnedBooksYetReturn> getListPeopleWhoDidNotReturnedBooks() {
        List<PeopleWhoDidNotReturnedBooksYetReturn> found = iNotReturnedRepositoryCustom.getListPeopleWhoDidNotReturnedBooks();
        return found;
    }
}
