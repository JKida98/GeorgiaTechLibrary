package GTL_API.Handlers.Encrpytion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EncryptionConfig {
    @Bean
    public EncryptionHandler encryptionHandler() {
        return new EncryptionHandler();
    }
}