package coding.toast.springweblegacy.config.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

// https://docs.spring.io/spring-data/jdbc/docs/3.1.7/reference/html/#jdbc.java-config
@Configuration
@EnableJdbcRepositories(basePackages = "coding.toast.springweblegacy")
@EnableJdbcAuditing
@EnableTransactionManagement
public class DataJdbcConfig extends AbstractJdbcConfiguration {
	
	@Bean
	DataSource dataSource(
		@Value("${my.jdbc.driver}") String myJdbcDriver,
		@Value("${my.jdbc.url}") String myJdbcUrl,
		@Value("${my.jdbc.user}") String myJdbcUsername,
		@Value("${my.jdbc.password}") String myJdbcPassword,
		@Value("${my.jdbc.schema:public}") String myJdbcSchema
	) {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(myJdbcDriver);
		hikariConfig.setJdbcUrl(myJdbcUrl);
		hikariConfig.setUsername(myJdbcUsername);
		hikariConfig.setPassword(myJdbcPassword);
		hikariConfig.setSchema(myJdbcSchema);
		Properties properties = new Properties();
		properties.setProperty("cachePrepStmts", "true");
		properties.setProperty("prepStmtCacheSize", "250");
		properties.setProperty("prepStmtCacheSqlLimit", "2048");
		hikariConfig.setDataSourceProperties(properties);
		return new HikariDataSource(hikariConfig);
	}
	
	@Bean
	NamedParameterJdbcOperations namedParameterJdbcOperations(DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}
	
	
	@Bean
	TransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
}
