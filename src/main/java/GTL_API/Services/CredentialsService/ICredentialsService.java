package GTL_API.Services.CredentialsService;

import GTL_API.Models.ReturnModels.CredentialsReturn;

public interface ICredentialsService {
    CredentialsReturn findCredentials(String login);
}
