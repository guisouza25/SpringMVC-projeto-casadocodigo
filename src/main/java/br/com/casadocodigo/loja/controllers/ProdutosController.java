package br.com.casadocodigo.loja.controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.NoResultException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.modelo.Produto;
import br.com.casadocodigo.loja.modelo.TipoPreco;
import br.com.casadocodigo.loja.validation.ProdutoValidation;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {
	
	
	@Autowired //Pede ao spring uma instância. Injeção de dependência. Inversão de controle
	private ProdutoDAO produtoDAO;
	@Autowired
	private FileSaver fileSaver;
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new ProdutoValidation());
	}
	
	
	@RequestMapping("/form")
	public ModelAndView form(Produto produto) { // que vai para o request
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("tipos", TipoPreco.values());
		return modelAndView;
	}
	
	
	@RequestMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Integer id) {
		
		Produto produto = produtoDAO.find(id);
		
		ModelAndView modelAndView = new ModelAndView("produtos/formEdita");
		
		modelAndView.addObject("tipos", TipoPreco.values());
		modelAndView.addObject("produto", produto);
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/alterar/{id}", method = RequestMethod.POST)
	@CacheEvict(value = "produtosHome", allEntries = true)
	public ModelAndView alterar(
			MultipartFile sumario, 
			@Valid Produto produto, BindingResult result, 
			@PathVariable("id") Integer id) throws AmazonServiceException, AmazonClientException, IOException {
		
		if(result.hasErrors()) {
			return editar(produto.getId());
		}
		
		//para ter a opção de não fazer upload de imagem
		try {
			String path = fileSaver.saveS3(sumario);
			produto.setSumarioPath(path);
			produtoDAO.altera(produto, id);
			
		} catch (com.amazonaws.services.s3.model.AmazonS3Exception e) {
			produtoDAO.altera(produto, id);
		} 
		return new ModelAndView("redirect:/produtos");
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	@CacheEvict(value = "produtosHome", allEntries = true)// libera o cache toda vez que este metodo for chamado
	public ModelAndView gravar(
			MultipartFile sumario, 
			@Valid Produto produto, BindingResult result, //deve vir depois do produto
			RedirectAttributes redirectAttributes) throws AmazonServiceException, AmazonClientException, IOException {
		
		if(result.hasErrors()) {
			return form(produto);
		}
		
		String path = fileSaver.saveS3(sumario);
		produto.setSumarioPath(path);
		
		produtoDAO.gravar(produto);
		
		redirectAttributes.addFlashAttribute("msg", "Produto cadastrado com sucesso!");
		return new ModelAndView("redirect:/produtos");
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {
		List<Produto> listaProdutos = produtoDAO.listar();
		ModelAndView modelAndView = new ModelAndView("produtos/lista");
		modelAndView.addObject("produtos", listaProdutos);
		
		if(listaProdutos.isEmpty()) {
			String msg = "Nenhum produto cadastrado.";
			modelAndView.addObject("msg", msg);
			return modelAndView;
		}
		
		return modelAndView;
	}
	
	
	@RequestMapping("/detalhe/{id}")
	public ModelAndView detalhe(@PathVariable("id") Integer id) { //@PathVariable("id") - a variavel que esta na url
		ModelAndView modelAndView = new ModelAndView("produtos/detalhe");
		Produto produto = produtoDAO.find(id);
		modelAndView.addObject("produto", produto);
		return modelAndView;
	}
	
	
	@ExceptionHandler(NoResultException.class)
	public ModelAndView trataDetalheNaoEncontrado(NoResultException exception) {
		exception.printStackTrace();
		String msg = "Produto não encontrado.";
		ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.addObject("exception", exception);
		modelAndView.addObject("msg", msg);
		return modelAndView;
	}
		
}









