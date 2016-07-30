package br.com.walmart.shopping.domain;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Classe responsável por servir de wrapper
 * para um ArrayList de pedidos com a funcionalidade
 * extra de controla a quantidade total de produtos
 * e o somatório do valor.
 * 
 * @author Marcelo
 *
 */
public class ListaPedidos extends ArrayList<Pedido> {
	private static final long serialVersionUID = 1L;

	/**
	 * Cada pedido pode representar uma quantiade N
	 * do mesmo produto. Este campo deve armazenar
	 * o quatidade total de produtos considerando o
	 * valor N de cada pedido.
	 */
	private int quantidadeTotal;
	
	/**
	 * Este campo deve armazenar o somatório do preço
	 * de cada pedido levando em consideração a quantidade
	 * de itens por pedido.
	 */
	private BigDecimal precoTotal = BigDecimal.ZERO;
	
	@Override
	public boolean add(Pedido e) {
		boolean adicionado = super.add(e);
		
		if (adicionado) {
			acrescentarPedido(e);
		}
		
		return adicionado;
	}
	
	public void add(Produto produto) {
		add(new Pedido(produto, 1));
	}
	
	@Override
	public Pedido remove(int index) {
		Pedido removido = super.remove(index);
		
		if (removido != null) {
			subtrairPedido(removido);
		}
		
		return removido;
	}
	
	public void setQuantidadePedido(int index, int quantidade) {
		Pedido pedido = get(index);
		pedido.setQuantidade(quantidade);
		
		if (pedido.getQuantidade() == 0) {
			remove(index);
		}
		
		atualizar();
	}

	private void acrescentarPedido(Pedido e) {
		quantidadeTotal += e.getQuantidade();
		precoTotal = precoTotal.add(e.getPrecoTotal());
	}
	
	private void subtrairPedido(Pedido e) {
		quantidadeTotal -= e.getQuantidade();
		precoTotal = precoTotal.subtract(e.getPrecoTotal());
	}
	
	private void atualizar() {
		reiniciar();
		
		for (Pedido pedido : this) {
			acrescentarPedido(pedido);
		}
	}

	private void reiniciar() {
		quantidadeTotal = 0;
		precoTotal = BigDecimal.ZERO;
	}
	
	public int getQuantidadeTotal() {
		return quantidadeTotal;
	}
	
	public BigDecimal getPrecoTotal() {
		return precoTotal;
	}
}
