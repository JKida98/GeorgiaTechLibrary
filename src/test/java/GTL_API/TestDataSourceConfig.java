package GTL_API;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories
@EnableJpaAuditing
@ComponentScan(excludeFilters = @ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE, value = CommandLineRunner.class))
@EnableAutoConfiguration
public class TestDataSourceConfig {
    @Bean
    @Primary
    public DataSource dataSourceTest() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUrl("jdbc:sqlserver://64.225.69.54;database=Giorgia_Tech_Library_Test");
        dataSource.setUsername("sa");
        dataSource.setPassword("Password123");
        return dataSource;
    }


    @Bean
    @Primary
    public EntityManager entityManagerFactory() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("GiorgiaTechLibrary_Persistence-Test");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager;
    }
}
