package GTL_API.Services.MapService;

import GTL_API.Models.CreationModels.MapCreation;
import GTL_API.Models.ReturnModels.MapReturn;

import java.util.List;

public interface IMapService {
    MapReturn findByBarcode(String barcode);

    MapReturn createMap(MapCreation mapCreation);

    List<MapReturn> findAllMaps();
}
