package br.com.walmart.shopping.domain;

import java.math.BigDecimal;

public class Resumo {
	
	private BigDecimal desconto;
	private BigDecimal totalOriginal;
	private BigDecimal totalComDesconto;
	
	public Resumo(BigDecimal desconto, BigDecimal totalOriginal, BigDecimal totalComDesconto) {
		this.desconto = desconto;
		this.totalOriginal = totalOriginal;
		this.totalComDesconto = totalComDesconto;
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
