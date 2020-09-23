package br.com.casadocodigo.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.modelo.Produto;

@Controller
@RequestMapping("/")
public class HomeController {
	
	
	@Autowired
	private ProdutoDAO produtoDAO;
	
	
	@RequestMapping("/")
	//nome do cache(nome da coleção guardada no map). Este é o valor do par chave-valor
	//lista fica salva no cache e pode ficar defasada caso haja atualização - @CacheEvict
	@Cacheable(value = "produtosHome") 
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView("home");
		List<Produto> listaProdutos = produtoDAO.listar();
		modelAndView.addObject("produtos", listaProdutos);
		
		return modelAndView;
	} 
}
