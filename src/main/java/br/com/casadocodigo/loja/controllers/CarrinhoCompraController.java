package br.com.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.modelo.CarrinhoCompras;
import br.com.casadocodigo.loja.modelo.CarrinhoItem;
import br.com.casadocodigo.loja.modelo.Produto;
import br.com.casadocodigo.loja.modelo.TipoPreco;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoCompraController {
	
	
	@Autowired
	private ProdutoDAO produtoDAO;
	@Autowired //spring verifica o contexto do carrinho e do controller
	private CarrinhoCompras carrinho;
	
	
	@RequestMapping("/add")
	public ModelAndView add(Integer produtoId, TipoPreco tipoPreco) {
		ModelAndView modelAndView = new ModelAndView("redirect:/carrinho");
		//modelAndView.addObject("id", produtoId);
		CarrinhoItem carrinhoItem = criaItem(produtoId, tipoPreco);
		carrinho.add(carrinhoItem);
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView itens() { //carrinhoCompras ja esta exposto no jsp
		ModelAndView modelAndView = new ModelAndView("carrinho/itens");
		return modelAndView;
	}
	
	@RequestMapping("/remover/{id}/{tipoPreco}")
	public ModelAndView remover(@PathVariable("id") Integer produtoId, @PathVariable TipoPreco tipoPreco) {
		
		carrinho.remover(produtoId, tipoPreco);
		
		return new ModelAndView("redirect:/carrinho");
	}
	
	
	private CarrinhoItem criaItem(Integer produtoId, TipoPreco tipoPreco) {
		Produto produto = produtoDAO.find(produtoId);
		CarrinhoItem carrinhoItem = new CarrinhoItem(produto, tipoPreco);
		return carrinhoItem;
	}
}
