package br.com.casadocodigo.loja.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
public class JPAConfiguration {
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			DataSource dataSource, Properties additionalProperties) {
		LocalContainerEntityManagerFactoryBean factoryBean = 
				new LocalContainerEntityManagerFactoryBean();
		
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		factoryBean.setJpaVendorAdapter(vendorAdapter);
		
		factoryBean.setPackagesToScan("br.com.casadocodigo.loja.modelo");
		
		factoryBean.setDataSource(dataSource);
		factoryBean.setJpaProperties(additionalProperties);
				
		return factoryBean;
	}
	
//	@Bean
//	@Profile(value = "dev")
//	public Properties additionalProperties() {
//		Properties props = new Properties();
//		props.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
//		props.setProperty("hibernate.show_sql", "true");
//		props.setProperty("hibernate.hbm2ddl.auto", "update");
//		return props;
//	}
//	
//	@Bean
//	@Profile(value = "dev")
//	public DataSource dataSource() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setUsername("postgres");
//		dataSource.setPassword("guuuih25");
//		dataSource.setUrl("jdbc:postgresql://localhost:5432/casadocodigo?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8");
//		dataSource.setDriverClassName("org.postgresql.Driver");
//		return dataSource;
//	}
	
	@Bean
	@Profile(value = "dev")
	public Properties additionalProperties() {
		Properties props = new Properties();
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		props.setProperty("hibernate.show_sql", "true");
		props.setProperty("hibernate.hbm2ddl.auto", "update");
		return props;
	}
	
	@Bean
	@Profile(value = "dev")
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("root");
		dataSource.setPassword("guuuih25");
		dataSource.setUrl("jdbc:mysql://localhost/casadocodigo?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8");
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		return dataSource;
	}
	
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}
	
}
