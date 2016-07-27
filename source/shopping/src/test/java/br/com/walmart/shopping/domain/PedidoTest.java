package br.com.walmart.shopping.domain;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class PedidoTest {
	
	@Test
	public void quando_adicionar_a_quantidade_deve_ser_incrementada_em_uma_unidade() {
		Pedido pedido = new Pedido(new Produto("TV", new BigDecimal("3000.00")));
		
		Assert.assertEquals(0, pedido.getQuantidade());
		
		pedido.adicionar();
		
		Assert.assertEquals(1, pedido.getQuantidade());
	}
	
	@Test
	public void quando_remover_a_quantidade_deve_ser_decrementada_em_uma_unidade() {
		Pedido pedido = new Pedido(new Produto("TV", new BigDecimal("3000.00")), 1);
		
		pedido.remover();
		
		Assert.assertEquals(0, pedido.getQuantidade());
	}
	
	@Test
	public void quando_remover_e_a_quantidade_for_zero_nao_fazer_nada() {
		Pedido pedido = new Pedido(new Produto("TV", new BigDecimal("3000.00")));
		
		Assert.assertEquals(0, pedido.getQuantidade());
		
		pedido.remover();
		
		Assert.assertEquals(0, pedido.getQuantidade());
	}
	
	@Test
	public void quando_setar_uma_quantidade_valida_quantidade_deve_assumir_o_novo_valor() {
		Pedido pedido = new Pedido(new Produto("TV", new BigDecimal("3000.00")));
		
		pedido.set(3);
		
		Assert.assertEquals(3, pedido.getQuantidade());
	}
	
	@Test
	public void quando_setar_uma_quantidade_invalida_quantidade_deve_ficar_como_zero() {
		Pedido pedido = new Pedido(new Produto("TV", new BigDecimal("3000.00")));
		
		pedido.set(-5);
		
		Assert.assertEquals(0, pedido.getQuantidade());
	}
	
	@Test
	public void valor_total_do_pedido_deve_ser_a_multiplicacao_do_valor_do_produto_pela_quantidade() {
		BigDecimal esperado = new BigDecimal("9837.42");
		
		Pedido pedido = new Pedido(new Produto("TV", new BigDecimal("3279.14")), 3);
		BigDecimal total = pedido.getTotal();
		
		Assert.assertEquals(esperado, total);
	}

}
