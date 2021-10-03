package GTL_API.Repositories.LibraryEmployeeTypeRepository;

import GTL_API.Models.Entities.LibraryEmployeeTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ILibraryEmployeeTypeRepository extends JpaRepository<LibraryEmployeeTypeEntity, Integer> {
    Optional<LibraryEmployeeTypeEntity> findByIdIs(int id);
    boolean existsByIdIs(int id);
}
