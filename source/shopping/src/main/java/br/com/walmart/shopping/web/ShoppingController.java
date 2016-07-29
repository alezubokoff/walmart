package br.com.walmart.shopping.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.walmart.shopping.components.CarrinhoCompra;
import br.com.walmart.shopping.components.Totalizador;
import br.com.walmart.shopping.domain.Produto;
import br.com.walmart.shopping.domain.Total;

@RestController
@RequestMapping("/rest")
public class ShoppingController {
	
	@Autowired
	private CarrinhoCompra carrinho;
	
	@Autowired
	private Totalizador totalizador;
	
	@RequestMapping(path = "/produto", method = RequestMethod.POST)
	public void cadastrarProduto(@RequestBody Produto produto) {
		carrinho.adicionarProduto(produto);
	}
	
	@RequestMapping(path = "/carrinho", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> listarCarrinho() {
		HashMap<String, Object> response = new HashMap<>();
		response.put("pedidos", carrinho.getPedidos());
		response.put("resumo", totalizador.getResumo());
		return response;
	}
	
	@RequestMapping(path = "/pedido/{indice}/incrementar", method = RequestMethod.PUT)
	public @ResponseBody Map<String, Object> incrementar(@PathVariable int indice) {
		HashMap<String, Object> response = new HashMap<>();
		response.put("pedido", carrinho.incrementarQuantidade(indice));
		response.put("resumo", totalizador.getResumo());
		return response;
	}
	
	@RequestMapping(path = "/pedido/{indice}/decrementar", method = RequestMethod.PUT)
	public @ResponseBody Map<String, Object> decrementar(@PathVariable int indice) {
		HashMap<String, Object> response = new HashMap<>();
		response.put("pedido", carrinho.decrementarQuantidade(indice));
		response.put("resumo", totalizador.getResumo());
		return response;
	}
	
	@RequestMapping(path = "/pedido/{indice}/{quantidade}", method = RequestMethod.PUT)
	public @ResponseBody Map<String, Object> alterarQuantidade(@PathVariable("indice") int indice, @PathVariable("quantidade") int quantidade) {
		HashMap<String, Object> response = new HashMap<>();
		response.put("pedido", carrinho.alterarQuantidade(indice, quantidade));
		response.put("resumo", totalizador.getResumo());
		return response;
	}
	
	@RequestMapping(path = "/pedido/{indice}", method = RequestMethod.DELETE)
	public @ResponseBody Total removerPedido(@PathVariable("indice") int indice) {
		carrinho.removerPedido(indice);
		return totalizador.getResumo();
	}
	
}
