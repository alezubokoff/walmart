package br.com.walmart.shopping.domain;

import java.math.BigDecimal;

import br.com.walmart.shopping.components.CalculoDescontoService;

public class CarrinhoCompras {
	
	private CalculoDescontoService calculadoraDesconto;
	private BigDecimal limiteMinimo;
	
	private ListaPedidos pedidos;
	
	public CarrinhoCompras(CalculoDescontoService calculadoraDesconto, BigDecimal limiteMinimo) {
		this.calculadoraDesconto = calculadoraDesconto;
		this.limiteMinimo = limiteMinimo;
		this.pedidos = new ListaPedidos();
	}
	
	public ListaPedidos getPedidos() {
		return pedidos;
	}
	
	public void adicionarProduto(Produto produto) {
		pedidos.add(produto);
	}
	
	public void adicionarProduto(Produto produto, int quantidade) {
		pedidos.add(new Pedido(produto, quantidade));
	}

	public void alterarQuantidade(int indicePedido, int quantidade) {
		pedidos.setQuantidadePedido(indicePedido, quantidade);
	}

	public void removerPedido(int indicePedido) {
		pedidos.remove(indicePedido);
	}
	
	public BigDecimal getPrecoTotal() {
		return pedidos.getPrecoTotal();
	}
	
	public int getQuantidadeTotal() {
		return pedidos.getQuantidadeTotal();
	}
	
	public BigDecimal getDesconto() {
		return calculadoraDesconto.calcularDesconto(getPrecoTotal());
	}
	
	public BigDecimal getPrecoTotalComDesconto() {
		return getPrecoTotal().subtract(getDesconto());
	}
	
	public boolean isPodeFinalizar() {
		return getPrecoTotalComDesconto().compareTo(limiteMinimo) >= 0;
	}

}
