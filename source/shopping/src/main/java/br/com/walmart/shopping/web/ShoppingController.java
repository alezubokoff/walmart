package br.com.walmart.shopping.web;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.walmart.shopping.components.CalculadoraDesconto;
import br.com.walmart.shopping.components.CarrinhoCompra;

@Controller
public class ShoppingController {

	@Autowired
	private CarrinhoCompra carrinho;
	
	@Autowired
	private CalculadoraDesconto calculadoraDesconto;
	
	@RequestMapping("/index")
	public String index() {
		return "novo-produto";
	}
	
	@RequestMapping(path = "/carrinho", method = RequestMethod.POST)
	public String novoProduto(String nome, @NumberFormat BigDecimal valor) {
		// adicionar produto repetido!
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
	
	@ModelAttribute("carrinho")
	public CarrinhoCompra getCarrinho() {
		return carrinho;
	}
	
	@RequestMapping(path = "/test", method = RequestMethod.GET)
	public String test(Model model) {
		carrinho.adicionarProduto("Smart TV LED 43\" Ultra HD 4k LG 43UF6400 com Conversor Digital 2 HDMI 1 USB WebOS 2.0 Wi-Fi Integrado - LG", new BigDecimal("1299.99"), 3);
		carrinho.adicionarProduto("Playstation 4", new BigDecimal("2782.89"), 1);
		carrinho.adicionarProduto("Notebook Asus Intel Quad Core 4GB 500GB Z550MA XX004T 15.6' Windows 10 Marrom Escuro", new BigDecimal("4522.30"), 2);
		carrinho.adicionarProduto("Bicicleta", new BigDecimal("576.37"), 2);
		
		// Isso tem que ficar numa fachada de serviço
		BigDecimal desconto = calculadoraDesconto.calcularDesconto(carrinho.obterTotal());
		model.addAttribute("desconto", desconto);
		model.addAttribute("total", carrinho.obterTotal().subtract(desconto));
		
		return "carrinho";
	}
	
}
