package GTL_API.Repositories.PersonRepository;

import GTL_API.Models.Entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPersonRepository extends JpaRepository<PersonEntity, String>{
    Optional<PersonEntity> findBySsnIs(String ssn);

    Optional<PersonEntity> findByFirstNameIsAndLastNameIs(String firstName, String lastName);
    Optional<PersonEntity> findByCardNumberIdIs(Integer cardNumberId);
    int countAllBySsn(String ssn);
}
