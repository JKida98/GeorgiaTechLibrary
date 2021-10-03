package GTL_API.Handlers.DatabaseConnection;

import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    public DataSource dataSourceMain() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUrl("jdbc:sqlserver://64.225.69.54;database=Giorgia_Tech_Library");
        dataSource.setUsername("sa");
        dataSource.setPassword("Password123");
        return dataSource;
    }


    public static DataSource dataSourceLibrarian() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUrl("jdbc:sqlserver://64.225.69.54;database=Giorgia_Tech_Library");
        dataSource.setUsername("librarian");
        dataSource.setPassword("userOnePassword");
        return dataSource;
    }


    public DataSource dataSourceStudent() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUrl("jdbc:sqlserver://64.225.69.54;database=Giorgia_Tech_Library");
        dataSource.setUsername("student");
        dataSource.setPassword("studentPassword");
        return dataSource;
    }


    public DataSource dataSourceChefLibrarian() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUrl("jdbc:sqlserver://64.225.69.54;database=Giorgia_Tech_Library");
        dataSource.setUsername("chefLibrarian");
        dataSource.setPassword("chefLibrarianPassword");
        return dataSource;
    }

    @Bean
    public DataSource multiRoutingDataSource() {
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put(DBTypeEnum.MAIN, dataSourceMain());
        targetDataSource.put(DBTypeEnum.LIBRARIAN, dataSourceLibrarian());
        targetDataSource.put(DBTypeEnum.STUDENT, dataSourceStudent());
        targetDataSource.put(DBTypeEnum.CHEF_LIBRARIAN, dataSourceChefLibrarian());
        RoutingDataSource routingDataSource = new RoutingDataSource();
        routingDataSource.setDefaultTargetDataSource(dataSourceMain());
        routingDataSource.setTargetDataSources(targetDataSource);
        return routingDataSource;
    }

    @Bean
    public EntityManager entityManagerFactory() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("GiorgiaTechLibrary_Persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager;
    }
}
