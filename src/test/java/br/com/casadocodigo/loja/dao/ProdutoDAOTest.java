package br.com.casadocodigo.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.casadocodigo.loja.builders.ProdutoBuilder;
import br.com.casadocodigo.loja.conf.DataSourceConfigurationTest;
import br.com.casadocodigo.loja.conf.JPAConfiguration;
import br.com.casadocodigo.loja.modelo.Produto;
import br.com.casadocodigo.loja.modelo.TipoPreco;

@RunWith(SpringJUnit4ClassRunner.class) //rodar os testes dentro do spring
@ContextConfiguration(classes = {JPAConfiguration.class, ProdutoDAO.class, DataSourceConfigurationTest.class})
@ActiveProfiles(profiles = "test")
public class ProdutoDAOTest {
	
	@Autowired
	private ProdutoDAO produtoDAO;
	
	@Test
	@Transactional
	public void TesteSomaPrecosPorTipo() {
		
		
		List<Produto> livrosImpressos = ProdutoBuilder
				.newProduto(TipoPreco.IMPRESSO, BigDecimal.TEN)
				.more(3).buildAll();
		
		List<Produto> livrosEbook = ProdutoBuilder
				.newProduto(TipoPreco.EBOOK, BigDecimal.TEN)
				.more(3).buildAll();
		
		livrosImpressos.stream().forEach(produtoDAO::gravar);
	    livrosEbook.stream().forEach(produtoDAO::gravar);
	    
	    BigDecimal valor = produtoDAO.somaPrecosPorTipo(TipoPreco.EBOOK);
	    Assert.assertEquals(new BigDecimal(40).setScale(2), valor);
	}
	
}
