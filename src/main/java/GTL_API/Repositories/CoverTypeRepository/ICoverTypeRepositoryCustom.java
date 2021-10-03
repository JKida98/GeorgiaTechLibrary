package GTL_API.Repositories.CoverTypeRepository;

import GTL_API.Models.Entities.CoverTypeEntity;
import GTL_API.Models.ReturnModels.CoverTypeReturn;

import java.util.List;

public interface ICoverTypeRepositoryCustom {
    CoverTypeReturn findCoverTypeByName(String coverType);

    CoverTypeReturn updateCoverType(CoverTypeEntity coverTypeEntity);

    List<CoverTypeReturn> getAllCoverTypes();
}
