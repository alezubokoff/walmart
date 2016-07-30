package br.com.walmart.shopping.domain;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import br.com.walmart.shopping.components.CalculoDescontoService;

public class CarrinhoComprasTest {
	
	private CarrinhoCompras carrinho;
	private CalculoDescontoService calculadora;
	
	@Before
	public void setup() {
		calculadora = Mockito.mock(CalculoDescontoService.class);
		carrinho = new CarrinhoCompras(calculadora, new BigDecimal("200.00"));
	}
	
	@Test
	public void quando_alterar_para_zero_remover_pedido_do_carrinho() {
		carrinho.adicionarProduto(new Produto("TV", new BigDecimal("1299.99")));
		carrinho.adicionarProduto(new Produto("Fone de Ouvido", new BigDecimal("299.99")));
		
		carrinho.alterarQuantidade(0, 0);
		
		Assert.assertEquals(1, carrinho.getPedidos().size());
	}
	
	@Test
	public void totalizar_preco_original() {
		carrinho.adicionarProduto(new Produto("TV", new BigDecimal("1299.99")), 3);
		carrinho.adicionarProduto(new Produto("Playstation 4", new BigDecimal("2782.89")), 1);
		carrinho.adicionarProduto(new Produto("Notebook", new BigDecimal("4522.30")), 2);
		
		Assert.assertEquals(new BigDecimal("15727.46"), carrinho.getPrecoTotal());
	}
	
	@Test
	public void totalizar_quantidade() {
		carrinho.adicionarProduto(new Produto("TV", new BigDecimal("1299.99")), 3);
		carrinho.adicionarProduto(new Produto("Playstation 4", new BigDecimal("2782.89")), 1);
		carrinho.adicionarProduto(new Produto("Notebook", new BigDecimal("4522.30")), 2);
		
		Assert.assertEquals(6, carrinho.getPedidos().getQuantidadeTotal());
	}
	
	@Test
	public void totalizar_preco_com_desconto() {
		BigDecimal desconto = new BigDecimal("100.00");
		
		Mockito.when(calculadora.calcularDesconto(Mockito.any())).thenReturn(desconto);
		
		carrinho.adicionarProduto(new Produto("TV", new BigDecimal("1299.99")), 3);
		carrinho.adicionarProduto(new Produto("Playstation 4", new BigDecimal("2782.89")), 1);
		carrinho.adicionarProduto(new Produto("Notebook", new BigDecimal("4522.30")), 2);
		
		Assert.assertEquals(new BigDecimal("15627.46"), carrinho.getPrecoTotalComDesconto());
	}
	
	@Test
	public void total_abaixo_do_limite_minimo() {
		BigDecimal desconto = new BigDecimal("100.00");
		
		Mockito.when(calculadora.calcularDesconto(Mockito.any())).thenReturn(desconto);
		
		carrinho.adicionarProduto(new Produto("TV", new BigDecimal("200.00")));
		
		Assert.assertFalse(carrinho.isPodeFinalizar());
	}
	
	@Test
	public void total_acima_do_limite_minimo() {
		BigDecimal desconto = new BigDecimal("100.00");
		
		Mockito.when(calculadora.calcularDesconto(Mockito.any())).thenReturn(desconto);
		
		carrinho.adicionarProduto(new Produto("TV", new BigDecimal("500.00")));
		
		Assert.assertTrue(carrinho.isPodeFinalizar());
	}

}
