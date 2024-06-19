package coding.toast.springweblegacy.config.jdbc;

import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.embedded.ConnectionProperties;
import org.springframework.jdbc.datasource.embedded.DataSourceFactory;

import javax.sql.DataSource;
import java.sql.Driver;
import java.util.Properties;

public class DetailH2DriverDataSourceFactory implements DataSourceFactory {
    private final SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

    @Override
    public ConnectionProperties getConnectionProperties() {
        return new ConnectionProperties() {
            @Override
            public void setDriverClass(Class<? extends Driver> driverClass) {
                dataSource.setDriverClass(driverClass);
            }

            @Override
            public void setUrl(String url) {
                dataSource.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=false"); // ;DATABASE_TO_UPPER=FALSE
            }

            @Override
            public void setUsername(String username) {
                dataSource.setUsername("sa");
            }

            @Override
            public void setPassword(String password) {
                dataSource.setPassword("");
            }
        };
    }

    @Override
    public DataSource getDataSource() {
        this.dataSource.setConnectionProperties(new Properties());
        return this.dataSource;
    }
}
