package br.com.casadocodigo.loja.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;

import br.com.casadocodigo.loja.dao.UsuarioDAO;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter { // deve estar configurada no ServletSpringMVC - root
																		 

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
	    filter.setEncoding("UTF-8");
	    filter.setForceEncoding(true);
	    http.addFilterBefore(filter,CsrfFilter.class);
		
		http.authorizeRequests()
				.antMatchers("/produtos/form").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/produtos").hasRole("ADMIN")
				.antMatchers(HttpMethod.GET, "/produtos").hasRole("ADMIN")
				.antMatchers("/editar").hasRole("ADMIN")
				
				.antMatchers("/resources/**").permitAll()
				.antMatchers("/produtos/**").permitAll() // permitindo tudo que nao foi bloqueado anteriormente
				.antMatchers("/carrinho/**").permitAll()
				.antMatchers("/pagamento/**").permitAll()
				.antMatchers("/**").permitAll()
				.anyRequest().authenticated()
				
				.and()
				.formLogin().loginPage("/login")
				.failureForwardUrl("/login-error")
				.defaultSuccessUrl("/produtos").permitAll()
				
				.and()
				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")) //logout através de GET
				.permitAll().logoutSuccessUrl("/");

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioDAO)
			.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	
}
