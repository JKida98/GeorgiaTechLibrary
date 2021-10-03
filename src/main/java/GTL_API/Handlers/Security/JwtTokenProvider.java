package GTL_API.Handlers.Security;

import GTL_API.Exceptions.InvalidJwtAuthenticationException;
import GTL_API.Handlers.DatabaseConnection.DBContextHolder;
import GTL_API.Handlers.DatabaseConnection.DBTypeEnum;
import GTL_API.Handlers.DatabaseConnection.RoutingDataSource;
import GTL_API.Services.CredentialsService.CredentialsService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
public class JwtTokenProvider {

    @Value("${security.jwt.token.secret-key:secret}")
    private final String secretKey = "secret";

    @Value("${security.jwt.token.expire-length:600000}")
    private final long validityInMilliseconds = 600000;


    private CredentialsService credentialsService;


    @Autowired
    public void setCredentialsService(CredentialsService credentialsService) {
        this.credentialsService = credentialsService;
    }


    @PostConstruct
    protected void init() {
        Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String username, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", roles);
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.credentialsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            List<?> roles = (List<?>) claims.getBody().get("roles");
            String specificRole = (String) roles.get(0);
            switch (specificRole) {
                case "student":
                    DBContextHolder.setCurrentDb(DBTypeEnum.STUDENT);
                    break;
                case "chefLibrarian":
                    DBContextHolder.setCurrentDb(DBTypeEnum.CHEF_LIBRARIAN);
                    break;
                case "librarian":
                    DBContextHolder.setCurrentDb(DBTypeEnum.LIBRARIAN);
                    break;
            }
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidJwtAuthenticationException("Expired or invalid JWT token");
        }
    }
}
