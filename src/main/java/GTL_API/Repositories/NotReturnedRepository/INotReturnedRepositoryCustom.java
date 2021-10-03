package GTL_API.Repositories.NotReturnedRepository;

import GTL_API.Models.ReturnModels.PeopleWhoDidNotReturnedBooksYetReturn;

import java.util.List;

public interface INotReturnedRepositoryCustom {
    List<PeopleWhoDidNotReturnedBooksYetReturn> getListPeopleWhoDidNotReturnedBooks();
}
