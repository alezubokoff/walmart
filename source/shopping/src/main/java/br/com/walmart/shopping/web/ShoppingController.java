package br.com.walmart.shopping.web;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.walmart.shopping.components.CarrinhoCompra;
import br.com.walmart.shopping.components.Totalizador;
import br.com.walmart.shopping.domain.Total;

@Controller
public class ShoppingController {

	@Autowired
	private CarrinhoCompra carrinho;
	
	@Autowired
	private Totalizador totalizador;
	
	@RequestMapping("/index")
	public String index() {
		return "novo-produto";
	}
	
	@RequestMapping(path = "/carrinho", method = RequestMethod.POST)
	public String novoProduto(String nome, @NumberFormat BigDecimal valor) {
		carrinho.adicionarProduto(nome, valor);
		return "carrinho";
	}
	
	@RequestMapping(path = "/carrinho", method = RequestMethod.GET)
	public String listar() {
		return "carrinho";
	}
	
	@RequestMapping(path = "/checkout", method = RequestMethod.GET)
	public String finalizar() {
		return "checkout";
	}
	
	@RequestMapping(path = "/pedido/{indice}/incrementar", method = RequestMethod.PUT)
	public @ResponseBody Map<String, Object> incrementar(@PathVariable int indice) {
		HashMap<String, Object> response = new HashMap<>();
		response.put("pedido", carrinho.adicionarQuantidade(indice));
		response.put("resumo", totalizador.getResumo());
		return response;
	}
	
	@RequestMapping(path = "/pedido/{indice}/decrementar", method = RequestMethod.PUT)
	public @ResponseBody Map<String, Object> decrementar(@PathVariable int indice) {
		HashMap<String, Object> response = new HashMap<>();
		response.put("pedido", carrinho.removerQuantidade(indice));
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
	
	@ModelAttribute("carrinho")
	public CarrinhoCompra getCarrinho() {
		return carrinho;
	}
	
	@ModelAttribute("total")
	public Totalizador getTotalizador() {
		return totalizador;
	}
	
}
