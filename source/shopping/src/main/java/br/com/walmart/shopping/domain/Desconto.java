package br.com.walmart.shopping.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Desconto {
	
	private BigDecimal valorMinimo;
	private BigDecimal valorDesconto;
	
	public BigDecimal getValorMinimo() {
		return valorMinimo;
	}

	public void setValorMinimo(BigDecimal valorMinimo) {
		this.valorMinimo = valorMinimo;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public BigDecimal aplicar(BigDecimal totalCompra) {
		return totalCompra.multiply(valorDesconto).setScale(2, RoundingMode.HALF_UP);
	}

}
