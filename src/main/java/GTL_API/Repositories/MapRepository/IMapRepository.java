package GTL_API.Repositories.MapRepository;

import GTL_API.Models.Entities.MapEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IMapRepository extends JpaRepository<MapEntity, String> {
    Optional<MapEntity> findByBarcodeIs(String barcode);
    List<MapEntity> findAll();
}
