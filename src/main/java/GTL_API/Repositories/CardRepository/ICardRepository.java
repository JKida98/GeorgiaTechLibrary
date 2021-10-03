package GTL_API.Repositories.CardRepository;

import GTL_API.Models.Entities.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICardRepository extends JpaRepository<CardEntity, Integer> {
    Optional<CardEntity> findByNumberIs(int number);

    boolean existsByNumber(int number);

}
