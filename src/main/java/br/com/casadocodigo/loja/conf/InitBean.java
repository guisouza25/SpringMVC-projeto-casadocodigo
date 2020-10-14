
package br.com.casadocodigo.loja.conf;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.casadocodigo.loja.dao.PopulaBanco;

@Component
public class InitBean implements InitializingBean {
	
	@Autowired
	PopulaBanco populaBanco;
	
	public void afterPropertiesSet() throws Exception {
		
		populaBanco.init();
		
	}

}
