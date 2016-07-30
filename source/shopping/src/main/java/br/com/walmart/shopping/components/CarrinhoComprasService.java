package br.com.walmart.shopping.components;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import br.com.walmart.shopping.domain.CarrinhoCompras;
import br.com.walmart.shopping.domain.Produto;

@Service
@Scope(proxyMode=ScopedProxyMode.TARGET_CLASS, value="session")
public class CarrinhoComprasService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private CarrinhoCompras carrinho;

	@Autowired
	private CalculoDescontoService calculadoraDesconto;

	@Value("${shopping.limite.minimo}")
	private BigDecimal limiteMinimo;

	@PostConstruct
	public void inicializar() {
		carrinho = new CarrinhoCompras(calculadoraDesconto, limiteMinimo);
	}
	
	public void adicionarProduto(Produto produto) {
		adicionarProduto(produto, 1);
	}
	
	public void adicionarProduto(Produto produto, int quantidade) {
		carrinho.adicionarProduto(produto, quantidade);
	}
	
	public void alterarQuantidade(int indicePedido, int quantidade) {
		carrinho.alterarQuantidade(indicePedido, quantidade);
	}
	
	public void removerPedido(int indicePedido) {
		carrinho.removerPedido(indicePedido);
	}
	
	public CarrinhoCompras getCarrinho() {
		return carrinho;
	}
	
}
