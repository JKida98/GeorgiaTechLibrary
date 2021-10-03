package GTL_API.Services.CredentialsService;


import GTL_API.Models.ReturnModels.CredentialsReturn;
import GTL_API.Repositories.CredentialsRepository.ICredentialsRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CredentialsService implements ICredentialsService, UserDetailsService {

    private ICredentialsRepositoryCustom credentialsRepository;

    @Autowired
    public void setCredentialsRepository(ICredentialsRepositoryCustom credentialsRepository) {
        this.credentialsRepository = credentialsRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        CredentialsReturn found = findCredentials(login);
        User toReturn = new User(found.getLogin(), found.getPassword(), getAuthority(found.getRole()));
        return toReturn;
    }

    @Override
    public CredentialsReturn findCredentials(String login) {
        return credentialsRepository.findCredentials(login);
    }

    private Set<SimpleGrantedAuthority> getAuthority(String authority) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(authority));
        return authorities;
    }
}
