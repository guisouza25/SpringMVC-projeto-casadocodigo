package br.com.casadocodigo.loja.conf;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Profile(value = "prod")
public class JPAProductionConfiguration {
	
//	@Autowired
//	private org.springframework.core.env.Environment environment;
	
	
//	@Bean
//	public Properties additionalProperties() {
//		Properties props = new Properties();
//		props.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
//		props.setProperty("hibernate.show_sql", "true");
//		props.setProperty("hibernate.hbm2ddl.auto", "update");
//		return props;
//	}
//	
//	@Bean
//	public DataSource dataSource() throws URISyntaxException {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName("org.postgresql.Driver");
//		// usuario:senha@host:port/path
//		URI dbURI = new URI(environment.getProperty("DATABASE_URL")); //variavel de ambiente do heroku
//		
//		dataSource.setUrl("jdbc:postgresql://" + dbURI.getHost() + ":" + dbURI.getPort() + dbURI.getPath());
//		dataSource.setUsername(dbURI.getUserInfo().split(":")[0]);
//		dataSource.setPassword(dbURI.getUserInfo().split(":")[1]);
//		return dataSource;
//	}
	
	@Bean
	public Properties additionalProperties() {
		Properties props = new Properties();
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		props.setProperty("hibernate.show_sql", "true");
		props.setProperty("hibernate.hbm2ddl.auto", "drop-and-create");
		return props;
	}
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("root");
		dataSource.setPassword("guuuih25");
		dataSource.setUrl("jdbc:mysql://db-casadocodigo.cz2aqisaaguf.sa-east-1.rds.amazonaws.com:3306/casadocodigo?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8");
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		return dataSource;
	}
		
}
