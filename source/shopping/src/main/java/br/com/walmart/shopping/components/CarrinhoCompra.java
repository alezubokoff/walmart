package br.com.walmart.shopping.components;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import br.com.walmart.shopping.components.events.AtualizacaoCarrinho;
import br.com.walmart.shopping.domain.Pedido;
import br.com.walmart.shopping.domain.Produto;

@Component
@Scope(proxyMode=ScopedProxyMode.TARGET_CLASS, value="session")
public class CarrinhoCompra implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private final ApplicationEventPublisher publisher;
	
	private List<Pedido> pedidos;
	
	@Autowired
	public CarrinhoCompra(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
		this.pedidos = new ArrayList<>();
	}
	
	public Pedido adicionarProduto(Produto produto) {
		return adicionarProduto(produto, 1);
	}
	
	public Pedido adicionarProduto(Produto produto, int quantidade) {
		Pedido pedido = new Pedido(produto, quantidade);
		
		pedidos.add(pedido);
		notificarAtualizacao();
		
		return pedido;
	}
	
	public Pedido adicionarProduto(String nome, BigDecimal valor) {
		return adicionarProduto(new Produto(nome, valor));
	}
	
	public Pedido adicionarProduto(String nome, BigDecimal valor, int quantidade) {
		Pedido pedido = adicionarProduto(new Produto(nome, valor), quantidade);
		return pedido;
	}
	
	public Pedido incrementarQuantidade(int indicePedido) {
		Pedido pedido = pedidos.get(indicePedido).adicionar();
		notificarAtualizacao();
		return pedido;
	}
	
	public Pedido decrementarQuantidade(int indicePedido) {
		Pedido pedido = pedidos.get(indicePedido).remover();
		
		if (pedido.getQuantidade() == 0) {
			pedidos.remove(indicePedido);
		}
		
		notificarAtualizacao();
		return pedido;
	}
	
	public Pedido alterarQuantidade(int indicePedido, int quantidade) {
		Pedido pedido = pedidos.get(indicePedido).set(quantidade);
		
		if (pedido.getQuantidade() == 0) {
			pedidos.remove(indicePedido);
		}
		
		notificarAtualizacao();
		return pedido;
	}
	
	public void removerPedido(int indicePedido) {
		pedidos.remove(indicePedido);
		notificarAtualizacao();
	}
	
	public BigDecimal obterPrecoTotal() {
		BigDecimal total = BigDecimal.ZERO;
		
		for (Pedido pedido : pedidos) {
			total = total.add(pedido.getTotal());
		}
		
		return total;
	}
	
	public int obterQuantidadeTotal() {
		int total = 0;
		
		for (Pedido pedido : pedidos) {
			total += pedido.getQuantidade();
		}
		
		return total;
	}
	
	public List<Pedido> getPedidos() {
		return pedidos;
	}
	
	public void notificarAtualizacao() {
		AtualizacaoCarrinho atualizacao = new AtualizacaoCarrinho(obterPrecoTotal(), obterQuantidadeTotal());
		publisher.publishEvent(atualizacao);
	}
	
}
