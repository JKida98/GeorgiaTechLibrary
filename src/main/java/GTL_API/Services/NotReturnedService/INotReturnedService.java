package GTL_API.Services.NotReturnedService;

import GTL_API.Models.ReturnModels.PeopleWhoDidNotReturnedBooksYetReturn;

import java.util.List;

public interface INotReturnedService {
    List<PeopleWhoDidNotReturnedBooksYetReturn> getListPeopleWhoDidNotReturnedBooks();
}
