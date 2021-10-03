package GTL_API.Repositories.MapRepository;

import GTL_API.Models.Entities.MapEntity;
import GTL_API.Models.ReturnModels.MapReturn;

import java.util.List;

public interface IMapRepositoryCustom {
    MapReturn findMapByBarcode(String barcode);

    MapReturn createMap(MapEntity mapEntity);

    List<MapReturn> findAllMaps();
}
