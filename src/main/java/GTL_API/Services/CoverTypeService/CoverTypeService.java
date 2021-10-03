package GTL_API.Services.CoverTypeService;

import GTL_API.Models.Entities.CoverTypeEntity;
import GTL_API.Models.ReturnModels.CoverTypeReturn;
import GTL_API.Models.UpdateModels.CoverTypeUpdate;
import GTL_API.Repositories.CoverTypeRepository.ICoverTypeRepositoryCustom;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoverTypeService implements ICoverTypeService {

    /**
     * Instance of ICoverTypeRepositoryCustom interface.
     */
    private ICoverTypeRepositoryCustom iCoverTypeRepository;

    /**
     * Instance of model mapper class.
     */
    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setICoverTypeRepository(ICoverTypeRepositoryCustom iCoverTypeRepository) {
        this.iCoverTypeRepository = iCoverTypeRepository;
    }

    /**
     * Invokes iCoverTypeRepository instance's method findCoverTypeByName by passing desired string
     * @param coverType A name of a cover type.
     * @return A found object with filled information.
     */
    @Override
    public CoverTypeReturn findCoverTypeByName(String coverType) {
        return iCoverTypeRepository.findCoverTypeByName(coverType);
    }

    /**
     * Invokes iCoverTypeRepository instance's method updateCoverType by passing a mapped CoverTypeEntity with new
     * information.
     * @param coverType An object containing information that will overwrite old.
     * @return An updated object with new information.
     */
    @Override
    public CoverTypeReturn updateCoverType(CoverTypeUpdate coverType) {
        return iCoverTypeRepository.updateCoverType(modelMapper.map(coverType, CoverTypeEntity.class));
    }
}