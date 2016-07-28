package br.com.walmart.shopping.domain;

import java.math.BigDecimal;

public class Total {
	
	private BigDecimal limiteMinimo;
	
	private int quantidade;
	private BigDecimal desconto;
	private BigDecimal totalOriginal;
	
	public Total(BigDecimal limiteMinimo) {
		this.limiteMinimo = limiteMinimo;
	}

	public void atualizar(int quantidade, BigDecimal desconto, BigDecimal totalOriginal) {
		this.quantidade = quantidade;
		this.desconto = desconto;
		this.totalOriginal = totalOriginal;
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
		return totalOriginal.subtract(desconto);
	}
	
	public boolean isPodeFinalizar() {
		return getTotalComDesconto().compareTo(limiteMinimo) >= 0;
	}
	
}
