package br.com.walmart.shopping.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.walmart.shopping.components.CarrinhoCompra;
import br.com.walmart.shopping.components.Totalizador;
import br.com.walmart.shopping.domain.Produto;

@RestController
@RequestMapping("/rest")
public class ShoppingRestController {
	
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
	
}
