package GTL_API.Exceptions;

public class InvalidJwtAuthenticationException extends RuntimeException {
    public InvalidJwtAuthenticationException(String message){
        super(message);
    }
}
