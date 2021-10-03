package GTL_API.Repositories.MapRepository;

import GTL_API.Exceptions.NotFoundException;
import GTL_API.Exceptions.UnknownException;
import GTL_API.Models.Entities.MapEntity;
import GTL_API.Models.ReturnModels.MapReturn;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Component
public class MapRepository implements IMapRepositoryCustom{

    /**
     * Instance of a IMapRepository interface that extends JPARepository.
     */
    private IMapRepository mapRepository;

    /**
     * Instance of a ModelMapper class.
     */
    private ModelMapper modelMapper;

    /**
     * Instantiation of a IMapRepository interface.
     *
     * @param mapRepository An object that will be assigned to class's mapRepository attribute.
     */
    @Autowired
    public void setMapRepository(IMapRepository mapRepository) {
        this.mapRepository = mapRepository;
    }

    /**
     * Instantiation of a ModelMapper class.
     *
     * @param modelMapper An object that will be assigned to class's modelMapper attribute.
     */
    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * Method finds a barcode by using a passed string parameter. First, method findIfExistsAndReturn is invoked.
     * Its returned value is assigned to foundMap variable and next, it is mapped to MapReturn class which is
     * returned back to service method call.
     * @param barcode a identification barcode of a map stored in a database.
     * @return Found map entity mapped to MapReturned class.
     */
    @Override
    public MapReturn findMapByBarcode(String barcode) {
        try{
            MapEntity foundMap = findIfExistsAndReturn(barcode);
            return modelMapper.map(foundMap,MapReturn.class);
        }catch (NotFoundException notFoundException){
            throw notFoundException;
        }catch (Exception e){
            throw new UnknownException(String.format("Unknown error occurred while finding map with barcode:" + barcode));
        }
    }

    /**
     * Method finds all maps in the database.
     * @return Found list of map entities mapped to MapReturned class.
     */
    @Override
    public List<MapReturn> findAllMaps() {
        try{
            Type listType = new TypeToken<List<MapRepository>>() {
            }.getType();
            List<MapEntity> foundMaps = mapRepository.findAll();
            return modelMapper.map(foundMaps, listType);
        }catch (Exception e){
            throw new UnknownException(String.format("Unknown exception occurred with message: " + e.getMessage()));
        }
    }

    /**
     * Creates a map record in a database, by invoking mapRepository's method save. Having successfully created
     * the record it is being mapped to MapReturn class and returned back to service method call.
     * @param mapEntity class object with filled information which will be added to a database.
     * @return Created object of MapReturn class, containing all added information.
     */
    @Override
    public MapReturn createMap(MapEntity mapEntity) {
        try{
            mapEntity.setDeleted(false);
            MapEntity added = mapRepository.save(mapEntity);
            return modelMapper.map(added, MapReturn.class);
        }catch (Exception e){
            throw new UnknownException("Unknown error occurred while creating a new map message: " + e.getMessage());
        }

    }

    /**
     * The method uses mapRepository's method findByNumberIs to find and optionally return an object of a MapEntity
     * class.
     * @param barcode a barcode of map that is wanted to be found.
     * @return Found map object of MapEntity class.
     */
    private MapEntity findIfExistsAndReturn(String barcode){
        Optional<MapEntity> foundMap = mapRepository.findByBarcodeIs(barcode);
        if(foundMap.isPresent()){
            return foundMap.get();
        }else{
            throw new NotFoundException(String.format("Map with number %s was not found", barcode));
        }
    }
}
