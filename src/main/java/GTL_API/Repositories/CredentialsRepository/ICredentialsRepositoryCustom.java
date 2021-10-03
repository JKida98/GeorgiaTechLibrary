package GTL_API.Repositories.CredentialsRepository;

import GTL_API.Models.ReturnModels.CredentialsReturn;

public interface ICredentialsRepositoryCustom {
    CredentialsReturn findCredentials(String login);
}
