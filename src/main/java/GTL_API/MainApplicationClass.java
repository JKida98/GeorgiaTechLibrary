package GTL_API;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan("GTL_API.Models.entities")
@EnableTransactionManagement
@EnableJpaRepositories("GTL_API.Repositories")
@SpringBootApplication
public class MainApplicationClass {
    public static void main(String[] args) {
        SpringApplication.run(MainApplicationClass.class, args);
    }
}