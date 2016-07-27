package br.com.walmart.shopping.components;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import br.com.walmart.shopping.domain.Pedido;
import br.com.walmart.shopping.domain.Produto;
import br.com.walmart.shopping.domain.Resumo;

@Component
@Scope(proxyMode=ScopedProxyMode.TARGET_CLASS, value="session")
public class CarrinhoCompra implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private CalculadoraDescontoFixo calculadoraDesconto;
	
	private List<Pedido> pedidos;
	
	public CarrinhoCompra() {
		pedidos = new ArrayList<>();
	}
	
	public Pedido adicionarProduto(Produto produto) {
		Pedido pedido = new Pedido(produto, 1);
		pedidos.add(pedido);
		return pedido;
	}
	
	public Pedido adicionarProduto(String nome, BigDecimal valor) {
		return adicionarProduto(new Produto(nome, valor));
	}
	
	public Pedido adicionarProduto(String nome, BigDecimal valor, int quantidade) {
		Pedido pedido = adicionarProduto(new Produto(nome, valor));
		pedido.set(quantidade);
		return pedido;
	}
	
	public Pedido obterPedidoPorNomeProduto(String nomeProduto) {
		for (Pedido pedido : pedidos) {
			if (pedido.getProduto().getNome().equals(nomeProduto)) {
				return pedido;
			}
		}
		
		return null;
	}
	
	public void adicionarQuantidade(String nomeProduto) {
		obterPedidoPorNomeProduto(nomeProduto).adicionar();
	}
	
	public void removerQuantidade(String nomeProduto) {
		obterPedidoPorNomeProduto(nomeProduto).remover();
	}
	
	public void alterarQuantidade(String nomeProduto, int quantidade) {
		obterPedidoPorNomeProduto(nomeProduto).set(quantidade);
	}
	
	public Resumo getResumo() {
		BigDecimal totalOriginal = obterTotal();
		BigDecimal desconto = calculadoraDesconto.calcularDesconto(totalOriginal);
		BigDecimal totalComDesconto = totalOriginal.subtract(desconto);
		
		return new Resumo(desconto, totalOriginal, totalComDesconto);
	}
	
	public BigDecimal obterTotal() {
		BigDecimal total = BigDecimal.ZERO;
		
		for (Pedido pedido : pedidos) {
			total = total.add(pedido.getTotal());
		}
		
		return total;
	}
	
	public List<Pedido> getPedidos() {
		return pedidos;
	}
	
}
