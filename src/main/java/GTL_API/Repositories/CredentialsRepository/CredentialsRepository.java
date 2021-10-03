package GTL_API.Repositories.CredentialsRepository;

import GTL_API.Exceptions.NotFoundException;
import GTL_API.Models.Entities.CredentialsEntity;
import GTL_API.Models.ReturnModels.CredentialsReturn;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CredentialsRepository implements ICredentialsRepositoryCustom {

    private ICredentialsRepository credentialsRepository;

    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setCredentialsRepository(ICredentialsRepository credentialsRepository) {
        this.credentialsRepository = credentialsRepository;
    }

    @Override
    public CredentialsReturn findCredentials(String login) {
        Optional<CredentialsEntity> found = credentialsRepository.findByLoginIs(login);
        if(found.isPresent()){
            return modelMapper.map(found.get(), CredentialsReturn.class);
        }else{
            throw new NotFoundException(String.format("The user with login: %s was not found.", login));
        }
    }
}
