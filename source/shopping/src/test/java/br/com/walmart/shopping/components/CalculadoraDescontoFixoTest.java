package br.com.walmart.shopping.components;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.walmart.shopping.Shopping;
import br.com.walmart.shopping.components.CalculadoraDescontoFixo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Shopping.class)
public class CalculadoraDescontoFixoTest {
	
	@Autowired
	private CalculadoraDescontoFixo calculadora;
	
	@Test
	public void quando_compra_maior_que_500_reais_desconto_de_5_por_cento_no_valor_da_compra() {
		BigDecimal esperado = new BigDecimal("27.50");
		
		BigDecimal desconto = calculadora.calcularDesconto(new BigDecimal("550.00"));
		
		Assert.assertEquals(esperado, desconto);
	}
	
	@Test
	public void quando_compra_maior_que_600_reais_desconto_de_10_por_cento_no_valor_da_compra() {
		BigDecimal esperado = new BigDecimal("65.00");
		
		BigDecimal desconto = calculadora.calcularDesconto(new BigDecimal("650.00"));
		
		Assert.assertEquals(esperado, desconto);
	}
	
	@Test
	public void quando_compra_maior_que_700_reais_desconto_de_15_por_cento_no_valor_da_compra() {
		BigDecimal esperado = new BigDecimal("112.50");
		
		BigDecimal desconto = calculadora.calcularDesconto(new BigDecimal("750.00"));
		
		Assert.assertEquals(esperado, desconto);
	}
	
	@Test
	public void quando_compra_entre_200_e_400_nao_aplicar_desconto() {
		BigDecimal esperado = new BigDecimal("0");
		
		BigDecimal desconto = calculadora.calcularDesconto(new BigDecimal("250.00"));
		
		Assert.assertEquals(esperado, desconto);
	}

}
