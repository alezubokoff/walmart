package br.com.walmart.shopping.domain;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ListaPedidoTest {
	
	private ListaPedidos lista;
	
	@Before
	public void setup() {
		lista = new ListaPedidos();
	}
	
	@Test
	public void quando_adicionar_produto_criar_pedido_com_quantidade_igual_a_um() {
		Produto tv = new Produto("TV", new BigDecimal("100.00"));
		
		lista.add(tv);
		
		Assert.assertEquals(1, lista.get(0).getQuantidade());
	}
	
	@Test
	public void quando_adicionar_pedido_contabilizar_quantidade_e_preco_total() {
		Produto tv = new Produto("TV", new BigDecimal("100.00"));
		Produto playstation = new Produto("TV", new BigDecimal("50.00"));
		
		lista.add(tv);
		lista.add(new Pedido(playstation, 3));
		
		Assert.assertEquals(4, lista.getQuantidadeTotal());
		Assert.assertEquals(new BigDecimal("250.00"), lista.getPrecoTotal());
	}
	
	@Test
	public void quando_remover_pedido_contabilizar_quantidade_e_preco_total() {
		Produto tv = new Produto("TV", new BigDecimal("100.00"));
		Produto playstation = new Produto("TV", new BigDecimal("50.00"));
		
		lista.add(tv);
		lista.add(new Pedido(playstation, 3));
		
		Assert.assertEquals(4, lista.getQuantidadeTotal());
		Assert.assertEquals(new BigDecimal("250.00"), lista.getPrecoTotal());
		
		lista.remove(0);
		
		Assert.assertEquals(3, lista.getQuantidadeTotal());
		Assert.assertEquals(new BigDecimal("150.00"), lista.getPrecoTotal());
	}
	
	@Test
	public void quando_alterar_quantidade_contabilizar_quantidade_e_preco_total() {
		Produto tv = new Produto("TV", new BigDecimal("100.00"));
		Produto playstation = new Produto("TV", new BigDecimal("50.00"));
		
		lista.add(tv);
		lista.add(new Pedido(playstation, 3));
		
		Assert.assertEquals(4, lista.getQuantidadeTotal());
		Assert.assertEquals(new BigDecimal("250.00"), lista.getPrecoTotal());
		
		lista.setQuantidadePedido(1, 1);
		
		Assert.assertEquals(2, lista.getQuantidadeTotal());
		Assert.assertEquals(new BigDecimal("150.00"), lista.getPrecoTotal());
	}
	
	@Test
	public void quando_alterar_quantidade_para_zero_remover_pedido_e_contabilizar_quantidade_e_preco_total() {
		Produto tv = new Produto("TV", new BigDecimal("100.00"));
		Produto playstation = new Produto("TV", new BigDecimal("50.00"));
		
		lista.add(tv);
		lista.add(new Pedido(playstation, 3));
		
		Assert.assertEquals(4, lista.getQuantidadeTotal());
		Assert.assertEquals(new BigDecimal("250.00"), lista.getPrecoTotal());
		
		lista.setQuantidadePedido(1, 0);
		
		Assert.assertEquals(1, lista.size());
		Assert.assertEquals(1, lista.getQuantidadeTotal());
		Assert.assertEquals(new BigDecimal("100.00"), lista.getPrecoTotal());
	}

}
