package br.com.casadocodigo.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.modelo.Produto;
import br.com.casadocodigo.loja.modelo.TipoPreco;

@Repository
@Transactional
public class ProdutoDAO {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void gravar(Produto produto) {
		manager.persist(produto);
	}

	public List<Produto> listar() {
		return manager.createQuery("select distinct(p) from Produto p", Produto.class)
				.getResultList();
	}
	
	public void altera(Produto produto, Integer id) {
		Produto produtoAlterar = manager.createQuery("select distinct(p) from Produto p join fetch p.precos where p.id = :id", Produto.class)
				.setParameter("id", id)
				.getSingleResult();
		produtoAlterar.setTitulo(produto.getTitulo());
		produtoAlterar.setDescricao(produto.getDescricao());
		produtoAlterar.setPaginas(produto.getPaginas());
		produtoAlterar.setDataLancamento(produto.getDataLancamento());
		produtoAlterar.setSumarioPath(produto.getSumarioPath());
		produtoAlterar.setPrecos(produto.getPrecos());
		produtoAlterar.setId(id);
		manager.merge(produtoAlterar);
	}

	public Produto find(Integer id) {
		// dá um LazyInitializationException
		//return manager.find(Produto.class, id);                                                     //alias
		Produto produto =  manager.createQuery("select distinct(p) from Produto p join fetch p.precos where p.id = :id", Produto.class)
				.setParameter("id", id)
				.getSingleResult();
		
		//O join fetch carrega a outra colecao no momento da busca. Quando fazemos um mapeamento 
		//objeto relacional, toda vez que tem uma lista de elementos - @ElementCollection, temos
		//uma dependencia que o hibernate carrega apenas quando precisar - Lazy. O lazy tem que 
		//ter sessao aberta, senão - LazyInitializationException. Sessão estava fechada - JPA 
		//inicia o entityManager no momento que entra no DAO, junto com a transação, termina a 
		//transação e fecha o entity manager, quando chega no JSP a transação já fechou. Então
		//o hibernate não consegue carregar. Para manter o entityManager aberto até o JSP - Filtro
		//que vai da primeira requisição do usuário até a jsp ser carregada - OpenEntityManagerInViewFilter
		//Sempre preferível fazer o join fetch para evitar consultas demais no banco. Quando não se
		//sabe ao certo quais coleções quer carregar na JSP pode usar OpenEntityManagerInViewFilter
		//para evitar um LazyInitializationException no JSP
		
//		System.out.println(produto.getPrecos().get(0).getTipo());
//		System.out.println(produto.getPrecos().get(0).getValor());
//		System.out.println(produto.getPrecos().get(1).getTipo());
//		System.out.println(produto.getPrecos().get(1).getValor());
//		System.out.println(produto.getPrecos().get(2).getTipo());
//		System.out.println(produto.getPrecos().get(2).getValor());
		
		return produto;
	}
	
	public BigDecimal somaPrecosPorTipo(TipoPreco tipoPreco) {
		TypedQuery<BigDecimal> query = manager
				.createQuery("SELECT sum(preco.valor) FROM Produto p JOIN p.precos preco "
				+ "WHERE preco.tipo = :tipoPreco ", BigDecimal.class);
		query.setParameter("tipoPreco", tipoPreco);
		
		return query.getSingleResult();
	}
	
	
	
}
