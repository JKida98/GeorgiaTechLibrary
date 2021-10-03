package GTL_API.Services.MapService;

import GTL_API.Models.CreationModels.MapCreation;
import GTL_API.Models.Entities.MapEntity;
import GTL_API.Models.ReturnModels.MapReturn;
import GTL_API.Repositories.MapRepository.IMapRepositoryCustom;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapService implements IMapService{

    /**
     * Instance of map repository interface.
     */
    private IMapRepositoryCustom iMapRepository;

    /**
     * Instance of model mapper class.
     */
    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setIMapRepository(IMapRepositoryCustom iMapRepository){ this.iMapRepository = iMapRepository; }

    /**
     * Runs a finding method from map repository by passing a barcode.
     * @param barcode specific number that identifies a map.
     * @return Found MapReturn object with filled information.
     */
    @Override
    public MapReturn findByBarcode(String barcode) {
        return iMapRepository.findMapByBarcode(barcode);
    }

    /**
     * Invokes iMapRepository's creation method by passing mapped entity object.
     * @param mapCreation a map creation object that need to be passed in order to create a new map.
     * @return A MapReturn object with filled information.
     */
    @Override
    public MapReturn createMap(MapCreation mapCreation) {
        return iMapRepository.createMap(modelMapper.map(mapCreation, MapEntity.class));
    }

    /**
     * Invokes iMapRepository's findAll.
     * @return List of all maps stored in the database.
     */
    @Override
    public List<MapReturn> findAllMaps() {
        return iMapRepository.findAllMaps();
    }
}
