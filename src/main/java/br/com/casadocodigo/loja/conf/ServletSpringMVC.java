package br.com.casadocodigo.loja.conf;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		//root para quando o sistema subir a config ja está pronta. 
		//SecurityConfiguration tenta injetar o usuarioDAO - JPAConfiguration aqui
		return new Class[] {AppWebConfiguration.class, JPAProductionConfiguration.class, 
				JPAConfiguration.class, SecurityConfiguration.class, }; 
		
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {};
	}

	@Override
	protected String[] getServletMappings() {
		String[] mapping = { "/" };
		return mapping;
	}
	
	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] {new OpenEntityManagerInViewFilter()};
		//OpenEntityManagerInViewFilter - Manter o EntityManager aberto em todo o processo da requisição
	}
	
	@Override
	//registro da cofiguração de um multipart
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement("")); 
		//para que o arquivo seja enviado do mesmo jeito que veio. Com mesmo nome
	}
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		servletContext.addListener(RequestContextListener.class);
		servletContext.setInitParameter("spring.profiles.active", "dev");
	}
	 
}
