package br.com.walmart.shopping.components.events;

import java.math.BigDecimal;

public class AtualizacaoCarrinho {
	
	private BigDecimal totalProdutos;
	private int quantidadeProdutos;
	
	public AtualizacaoCarrinho(BigDecimal totalProdutos, int quantidadeProdutos) {
		this.totalProdutos = totalProdutos;
		this.quantidadeProdutos = quantidadeProdutos;
	}

	public BigDecimal getTotalProdutos() {
		return totalProdutos;
	}

	public int getQuantidadeProdutos() {
		return quantidadeProdutos;
	}

}
