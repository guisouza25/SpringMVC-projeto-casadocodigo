package br.com.casadocodigo.loja.modelo;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

//Para o spring reconhecer, pq ele nao e nem um controller nem dao ou outro especifico. Por padrão, 
//Beans do Spring são criados uma única vez, ou seja, tem uma única instância do carrinho para 
//a aplicação inteira - Escopo application
//Já está mapeado. O getQuantidade vai parecer no jsp - config InternalResourceViewResolver
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, //Padrão é application
		proxyMode = ScopedProxyMode.TARGET_CLASS) //nao precisar mudar o escopo dos controller que usam o CarrinhoCompras
public class CarrinhoCompras {
	
	
	private Map<CarrinhoItem, Integer> itens = new LinkedHashMap<CarrinhoItem, Integer>();
	
	
	public Collection<CarrinhoItem> getItens() {
		return itens.keySet();
	}
	
	public void add(CarrinhoItem carrinhoItem) {
		itens.put(carrinhoItem, getQuantidade(carrinhoItem) + 1);
	}
	
	public Integer getQuantidade(CarrinhoItem carrinhoItem) {
		if(!itens.containsKey(carrinhoItem)) {
			itens.put(carrinhoItem, 0);
		}
		return itens.get(carrinhoItem);
	}
	
	public int getQuantidade() {
		return itens.values().stream().reduce(0, (proximo, acumulador) -> proximo + acumulador);
	}
	
	public BigDecimal getTotal(CarrinhoItem carrinhoItem) {
		return carrinhoItem.getTotal(getQuantidade(carrinhoItem));
	}
	
	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for (CarrinhoItem item : itens.keySet()) {
			total = total.add(getTotal(item));  
		}
		return total;
	}

	public void remover(Integer produtoId, TipoPreco tipoPreco) {
		Produto produto = new Produto();
		produto.setId(produtoId);
		itens.remove(new CarrinhoItem(produto, tipoPreco));
	}
	
}	
