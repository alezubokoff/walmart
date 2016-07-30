package br.com.walmart.shopping.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.walmart.shopping.components.CarrinhoComprasService;
import br.com.walmart.shopping.domain.CarrinhoCompras;
import br.com.walmart.shopping.domain.Pedido;
import br.com.walmart.shopping.domain.Produto;

@RestController
@RequestMapping("/rest")
public class ShoppingController {
	
	@Autowired
	private CarrinhoComprasService service;
	
	@RequestMapping(path = "/produto", method = RequestMethod.POST)
	public void cadastrarProduto(@RequestBody Produto produto) {
		service.adicionarProduto(produto);
	}
	
	@RequestMapping(path = "/carrinho", method = RequestMethod.GET)
	public CarrinhoCompras obterCarrinho() {
		return service.getCarrinho();
	}
	
	@RequestMapping(path = "/pedido/{indice}", method = RequestMethod.PUT)
	public CarrinhoCompras alterarQuantidade(@PathVariable("indice") int indice, @RequestBody Pedido pedido) {
		service.alterarQuantidade(indice, pedido.getQuantidade());
		return service.getCarrinho();
	}
	
	@RequestMapping(path = "/pedido/{indice}", method = RequestMethod.DELETE)
	public CarrinhoCompras removerPedido(@PathVariable("indice") int indice) {
		service.removerPedido(indice);
		return service.getCarrinho();
	}
	
}
