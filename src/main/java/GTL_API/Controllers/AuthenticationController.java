package GTL_API.Controllers;

import GTL_API.Handlers.Security.JwtTokenProvider;
import GTL_API.Models.CreationModels.CredentialsCreation;
import GTL_API.Repositories.CredentialsRepository.ICredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("gtl/auth")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    private ICredentialsRepository credentialsRepository;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setJwtTokenProvider(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Autowired
    public void setCredentialsRepository(ICredentialsRepository credentialsRepository) {
        this.credentialsRepository = credentialsRepository;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody CredentialsCreation data){
        try{
            String username = data.getLogin();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            String role=this.credentialsRepository.findByLoginIs(username).get().getRole();
            String token = jwtTokenProvider.createToken(username, Stream.of(this.credentialsRepository.findByLoginIs(username).get().getRole()).collect(Collectors.toList()));
            if(token.isEmpty()){
                throw new AuthenticationException();
            }else{
                Object myobj = new Object() {
                    public final String authToken = token;
                    public final String authRole = role;
                };
                return new ResponseEntity<>(myobj, new HttpHeaders(), HttpStatus.OK);
            }
        }catch(AuthenticationException e){
            throw new BadCredentialsException("Invalid username/password");
        }
    }

}
