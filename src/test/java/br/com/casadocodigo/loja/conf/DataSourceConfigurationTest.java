package br.com.casadocodigo.loja.conf;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DataSourceConfigurationTest {
	
	@Bean
	@Profile(value = "test") //criando um dataSource apenas para teste
	public DataSource dataSource() {
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("root");
		dataSource.setPassword("guuuih25");
		dataSource.setUrl("jdbc:mysql://localhost/casadocodigo_teste?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8");
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		
		return dataSource;
	}
	
}
