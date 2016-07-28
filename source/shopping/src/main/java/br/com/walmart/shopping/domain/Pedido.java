package br.com.walmart.shopping.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Pedido {
	
	private Produto produto;
	private int quantidade;
	
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
	
	public Pedido set(int quantidade) {
		if (quantidade > 0) {
			this.quantidade = quantidade;
		} else {
			this.quantidade = 0;
		}
		return this;
	}
	
	public BigDecimal getTotal() {
		return produto.getValor().multiply(new BigDecimal(quantidade)).setScale(2, RoundingMode.HALF_UP);
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public int getQuantidade() {
		return quantidade;
	}

}
