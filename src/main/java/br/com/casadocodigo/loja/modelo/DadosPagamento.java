package br.com.casadocodigo.loja.modelo;

import java.math.BigDecimal;

/**
 * Classe responsável por preparar o envio do JSON.
 */
public class DadosPagamento {

	/**
	 * O nome do atributo é a chave no par chave-valor do JSON
	 */
	private BigDecimal value; // o spring usa este atributo(chave) para enviar o JSON

	/**
	 * O parâmetro recebido é o valor do respectivo par chave-valor do JSON
	 * 
	 * @param value o valor no par chave-valor
	 */
	public DadosPagamento(BigDecimal value) {
		this.value = value;
	}

	public DadosPagamento() {
	}

	public BigDecimal getValue() {
		return value;
	}
}
