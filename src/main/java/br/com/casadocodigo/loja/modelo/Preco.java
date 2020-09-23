package br.com.casadocodigo.loja.modelo;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable // vai ser colocado dentro do produto
public class Preco {

	private BigDecimal valor;
	private BigDecimal valorTabela;
	private TipoPreco tipo;

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	

	public BigDecimal getValorTabela() {
		return valorTabela;
	}

	public void setValorTabela(BigDecimal valorTabela) {
		this.valorTabela = valorTabela;
	}

	public TipoPreco getTipo() {
		return tipo;
	}

	public void setTipo(TipoPreco tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return  this.tipo.name() + " - R$" + this.valor;
	}

}
