package coding.toast.springweblegacy.config.jdbc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

// https://docs.spring.io/spring-data/jdbc/docs/3.1.7/reference/html/#jdbc.java-config
@Configuration
@EnableJdbcRepositories(basePackages = "coding.toast.springweblegacy")
@EnableJdbcAuditing
@EnableTransactionManagement
public class DataJdbcConfig extends AbstractJdbcConfiguration {

	@Bean
	public DataSource embeddedDataSource() {
		return new EmbeddedDatabaseBuilder()
				// using H2 Database
				.setType(EmbeddedDatabaseType.H2)

				// JDBC URL will be fixed to [jdbc:h2:mem:testdb] !!!
				// .generateUniqueName(false)
				// .setName("testdb")

				// replacing two config above. this can config more detailed.
				.setDataSourceFactory(new DetailH2DriverDataSourceFactory())

				// add some startup script
				.setScriptEncoding("UTF-8")
				.ignoreFailedDrops(true)
				.addScripts(
					"classpath:h2-startup-sql/schema.sql",
					"classpath:h2-startup-sql/data.sql"
				)

				// create memory db datasource
				.build();
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
