package br.com.walmart.shopping.domain;

import java.math.BigDecimal;

public class Resumo {
	
	private int quantidade;
	private BigDecimal desconto;
	private BigDecimal totalOriginal;
	private BigDecimal totalComDesconto;
	
	public Resumo(int quantidade, BigDecimal desconto, BigDecimal totalOriginal, BigDecimal totalComDesconto) {
		this.quantidade = quantidade;
		this.desconto = desconto;
		this.totalOriginal = totalOriginal;
		this.totalComDesconto = totalComDesconto;
	}
	
	public int getQuantidade() {
		return quantidade;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public BigDecimal getTotalOriginal() {
		return totalOriginal;
	}

	public BigDecimal getTotalComDesconto() {
		return totalComDesconto;
	}
	
}
