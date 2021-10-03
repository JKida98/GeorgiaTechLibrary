package GTL_API.Services.BookReturnService;

import GTL_API.Models.CreationModels.BookReturnCreation;
import GTL_API.Models.ReturnModels.BookReturnReturn;

public interface IBookReturnService {

    BookReturnReturn createBookReturn(BookReturnCreation bookReturnCreation, String ssn);

    boolean returnBook(BookReturnCreation bookReturnCreation);



}
