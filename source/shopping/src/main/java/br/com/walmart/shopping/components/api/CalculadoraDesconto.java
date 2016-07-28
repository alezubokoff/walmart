package br.com.walmart.shopping.components.api;

import java.math.BigDecimal;

public interface CalculadoraDesconto {
	
	public BigDecimal calcularDesconto(BigDecimal totalCompra);

}
