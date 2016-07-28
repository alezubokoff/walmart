package br.com.walmart.shopping.components;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationEventPublisher;

import br.com.walmart.shopping.components.CarrinhoCompra;

public class CarrinhoCompraTest {
	
	private CarrinhoCompra carrinho;
	
	@Before
	public void setup() {
		carrinho = new CarrinhoCompra(Mockito.mock(ApplicationEventPublisher.class));
	}
	
	@Test
	public void totalizar_preco() {
		carrinho.adicionarProduto("TV", new BigDecimal("1299.99"), 3);
		carrinho.adicionarProduto("Playstation 4", new BigDecimal("2782.89"), 1);
		carrinho.adicionarProduto("Notebook", new BigDecimal("4522.30"), 2);
		
		Assert.assertEquals(new BigDecimal("15727.46"), carrinho.obterPrecoTotal());
	}
	
	@Test
	public void totalizar_quantidade() {
		carrinho.adicionarProduto("TV", new BigDecimal("1299.99"), 3);
		carrinho.adicionarProduto("Playstation 4", new BigDecimal("2782.89"), 1);
		carrinho.adicionarProduto("Notebook", new BigDecimal("4522.30"), 2);
		
		Assert.assertEquals(6, carrinho.obterQuantidadeTotal());
	}

}
