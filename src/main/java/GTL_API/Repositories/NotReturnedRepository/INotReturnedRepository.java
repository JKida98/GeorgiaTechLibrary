package GTL_API.Repositories.NotReturnedRepository;

import GTL_API.Models.Entities.PeopleWhoDidNotReturnedBooksYetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INotReturnedRepository extends JpaRepository<PeopleWhoDidNotReturnedBooksYetEntity, Integer> {
    List<PeopleWhoDidNotReturnedBooksYetEntity> findAllBySsnIsNotNull();
    PeopleWhoDidNotReturnedBooksYetEntity findBySsnIs(String ssn);

}
