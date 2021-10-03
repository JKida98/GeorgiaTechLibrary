package GTL_API.Repositories.CredentialsRepository;

import GTL_API.Models.Entities.CredentialsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICredentialsRepository extends JpaRepository<CredentialsEntity, Integer> {
    Optional<CredentialsEntity> findByLoginIs(String login);
}
