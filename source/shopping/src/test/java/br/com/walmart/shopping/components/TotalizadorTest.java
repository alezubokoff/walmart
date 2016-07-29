package br.com.walmart.shopping.components;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import br.com.walmart.shopping.components.api.CalculadoraDesconto;
import br.com.walmart.shopping.components.events.AtualizacaoCarrinho;

public class TotalizadorTest {
	
	private Totalizador totalizador;
	private CalculadoraDesconto calculadora;
	
	@Before
	public void setup() {
		calculadora = Mockito.mock(CalculadoraDesconto.class);
		totalizador = new Totalizador(calculadora);
		totalizador.inicializar();
	}
	
	@Test
	public void atualizacao_de_valores() {
		BigDecimal precoTotal = new BigDecimal("500.00");
		BigDecimal desconto = new BigDecimal("100.00");
		int quantidade = 5;
		
		Mockito.when(calculadora.calcularDesconto(Mockito.any())).thenReturn(desconto);
		
		AtualizacaoCarrinho atualizacao = new AtualizacaoCarrinho(precoTotal, quantidade);
		totalizador.tratarAtualizacaoCarrinho(atualizacao);
		
		Assert.assertEquals(new BigDecimal("400.00"), totalizador.getResumo().getTotalComDesconto());
	}
	
	@Test
	public void total_abaixo_do_limite_minimo() {
		BigDecimal precoTotal = new BigDecimal("500.00");
		BigDecimal desconto = new BigDecimal("100.00");
		BigDecimal limiteMinimo = new BigDecimal("700.00");
		int quantidade = 5;
		
		Mockito.when(calculadora.calcularDesconto(Mockito.any())).thenReturn(desconto);
		
		AtualizacaoCarrinho atualizacao = new AtualizacaoCarrinho(precoTotal, quantidade);
		
		totalizador.inicializar(limiteMinimo);
		totalizador.tratarAtualizacaoCarrinho(atualizacao);
		
		Assert.assertFalse(totalizador.getResumo().isPodeFinalizar());
	}
	
	@Test
	public void total_acima_do_limite_minimo() {
		BigDecimal precoTotal = new BigDecimal("500.00");
		BigDecimal desconto = new BigDecimal("100.00");
		BigDecimal limiteMinimo = new BigDecimal("100.00");
		int quantidade = 5;
		
		Mockito.when(calculadora.calcularDesconto(Mockito.any())).thenReturn(desconto);
		
		AtualizacaoCarrinho atualizacao = new AtualizacaoCarrinho(precoTotal, quantidade);
		
		totalizador.inicializar(limiteMinimo);
		totalizador.tratarAtualizacaoCarrinho(atualizacao);
		
		Assert.assertTrue(totalizador.getResumo().isPodeFinalizar());
	}

}
