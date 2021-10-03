package GTL_API.Handlers.DatabaseConnection;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


public class RoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DBContextHolder.getCurrentDb();
    }
}
