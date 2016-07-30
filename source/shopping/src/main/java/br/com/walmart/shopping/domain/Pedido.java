package br.com.walmart.shopping.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Classe que agrupa os pedidos relacionados 
 * a um mesmo produto em uma única instância
 * com a contagem da quantidade de produtos
 * que ela referencia.
 * 
 * @author Marcelo
 *
 */
public class Pedido {
	
	private Produto produto;
	private int quantidade;
	
	public Pedido() {}
	
	public Pedido(Produto produto) {
		this.produto = produto;
	}
	
	public Pedido(Produto produto, int quantidade) {
		this.produto = produto;
		this.quantidade = quantidade;
	}

	public Pedido adicionar() {
		quantidade++;
		return this;
	}
	
	public Pedido remover() {
		if (quantidade > 0) {
			quantidade--;
		}
		return this;
	}
	
	public BigDecimal getPrecoTotal() {
		return produto.getValor().multiply(new BigDecimal(quantidade)).setScale(2, RoundingMode.HALF_UP);
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	/**
	 * Não deve permitir valores negativos. Se for caso
	 * deve ser considerado 0.
	 * 
	 * @param quantidade
	 * @return
	 */
	public void setQuantidade(int quantidade) {
		if (quantidade > 0) {
			this.quantidade = quantidade;
		} else {
			this.quantidade = 0;
		}
	}
	
	public int getQuantidade() {
		return quantidade;
	}

}
