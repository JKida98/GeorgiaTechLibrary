package GTL_API.Repositories.BookReturnRespository;

import GTL_API.Models.Entities.PeopleWhoDidNotReturnedBooksYetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPeopleWhoDidNotReturnedBooksRepository extends JpaRepository<PeopleWhoDidNotReturnedBooksYetEntity, Integer> {


}
