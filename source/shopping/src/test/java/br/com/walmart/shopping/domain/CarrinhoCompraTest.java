package br.com.walmart.shopping.domain;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.walmart.shopping.components.CarrinhoCompra;

public class CarrinhoCompraTest {
	
	private CarrinhoCompra carrinho;
	
	@Before
	public void setup() {
		carrinho = new CarrinhoCompra();
	}
	
	@Test
	public void quando_obter_por_nome_produto_retornar_pedido_do_produto() {
		Pedido peditoTv = carrinho.adicionarProduto(new Produto("TV", BigDecimal.ZERO));
		Pedido pedidoPlaystation = carrinho.adicionarProduto(new Produto("Playstation 4", BigDecimal.ZERO));
		Pedido pedidoNotebook = carrinho.adicionarProduto(new Produto("Notebook", BigDecimal.ONE));
		
		Assert.assertEquals(peditoTv, carrinho.obterPedidoPorNomeProduto("TV"));
		Assert.assertEquals(pedidoPlaystation, carrinho.obterPedidoPorNomeProduto("Playstation 4"));
		Assert.assertEquals(pedidoNotebook, carrinho.obterPedidoPorNomeProduto("Notebook"));
	}
	
	@Test
	public void quando_obter_por_nome_produto_inexistente_retornar_null() {
		Assert.assertEquals(null, carrinho.obterPedidoPorNomeProduto("TV"));
	}
	
	@Test
	public void totalizar_pedidos() {
		carrinho.adicionarProduto("TV", new BigDecimal("1299.99"), 3);
		carrinho.adicionarProduto("Playstation 4", new BigDecimal("2782.89"), 1);
		carrinho.adicionarProduto("Notebook", new BigDecimal("4522.30"), 2);
		
		Assert.assertEquals(new BigDecimal("15727.46"), carrinho.obterTotal());
	}

}
