package br.com.casadocodigo.loja.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.modelo.Preco;
import br.com.casadocodigo.loja.modelo.Produto;
import br.com.casadocodigo.loja.modelo.Role;
import br.com.casadocodigo.loja.modelo.TipoPreco;
import br.com.casadocodigo.loja.modelo.Usuario;

@Transactional
@Component	
public class PopulaBanco {
	
	@Autowired
	UsuarioDAO usuarioDao;

	@Autowired
	ProdutoDAO produtoDao;

	@Autowired
	RoleDAO roleDao;
	
	public void init() {
		
		String urlBase = "https://casadocodigo-imagens.s3-sa-east-1.amazonaws.com/";
		
		Produto produto1 = criarProduto(502, LocalDate.of(2016, 6, 15), "Java SE 8 Programmer I",
				"O guia para sua certificação Oracle Certified Associate", new String[] { "29.9", "39.9", "79.9" }, urlBase + "certificacao-java.png");
		Produto produto2 = criarProduto(172, LocalDate.of(2017, 11, 21), "Orientação a Objetos",
				"Aprenda seus conceitos e suas aplicabilidades de forma efetiva",
				new String[] { "29.9", "49.9", "59.9" }, urlBase + "eBook-Orientacao-a-Objetos-atualizado_large.jpg");
		Produto produto3 = criarProduto(260, LocalDate.of(2018, 2, 2), "Spring MVC",
				"Domine o principal framework web Java", new String[] { "19.9", "29.9", "39.9" }, urlBase + "spring-framework-featured_large.png");
		Produto produto4 = criarProduto(234, LocalDate.of(2017, 11, 11), "Amazon AWS",
				"Descomplicando a computação na nuvem", new String[] { "29.9", "69.9", "79.9" }, urlBase + "DJLjy9t0n3p93BKLq_8gCNDcOwXGBnPAe_d1aYzCAxU_large.jpg");
		Produto produto5 = criarProduto(355, LocalDate.of(2018, 7, 2), "Introdução à Arquitetura e Design de Software",
				"Uma visão sobre a plataforma Java", new String[] { "29.9", "69.9", "79.9" }, urlBase + "arquitetura-java-featured_large.png");
		Produto produto6 = criarProduto(151, LocalDate.of(2017, 3, 4), "Java 8 Prático",
				"Lambdas, Streams e os novos recursos da linguagem", new String[] { "19.9", "49.9", "69.9" }, urlBase + "java8-featured_large.png");
		produtoDao.gravar(produto1);
		produtoDao.gravar(produto2);
		produtoDao.gravar(produto3);
		produtoDao.gravar(produto4);
		produtoDao.gravar(produto5);
		produtoDao.gravar(produto6);
		
		Role roleAdmin = new Role("ROLE_ADMIN");
		roleDao.gravar(roleAdmin);
		
		Usuario usuario = new Usuario();
		usuario.setNome("Admin");
		usuario.setEmail("admin@casadocodigo.com.br");
		usuario.setSenha("$2a$10$lt7pS7Kxxe5JfP.vjLNSyOXP11eHgh7RoPxo5fvvbMCZkCUss2DGu");
		usuario.setRoles(Arrays.asList(roleAdmin));
		usuarioDao.gravar(usuario);
		
	}
	
	public Produto criarProduto(int paginas, LocalDate data, String titulo, String descricao, String[] precos, String path) {
		Produto produto = new Produto();
		produto.setPaginas(paginas);
		produto.setTitulo(titulo);
		produto.setDataLancamento(data);
		produto.setDescricao(descricao);
		Preco ebook = new Preco(new BigDecimal(precos[0]), TipoPreco.EBOOK);
		Preco impresso = new Preco(new BigDecimal(precos[1]), TipoPreco.IMPRESSO);
		Preco combo = new Preco(new BigDecimal(precos[2]), TipoPreco.COMBO);
		produto.setPrecos(Arrays.asList(ebook, impresso, combo));
		produto.setSumarioPath(path);
		return produto;
	}
	
	
}
