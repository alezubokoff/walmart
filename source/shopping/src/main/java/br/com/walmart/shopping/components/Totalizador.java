package br.com.walmart.shopping.components;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.walmart.shopping.components.api.CalculadoraDesconto;
import br.com.walmart.shopping.components.events.AtualizacaoCarrinho;
import br.com.walmart.shopping.domain.Total;

@Component
@Scope(proxyMode=ScopedProxyMode.TARGET_CLASS, value="session")
public class Totalizador {
	
	@Autowired
	private CalculadoraDesconto calculadoraDesconto;
	
	@Value("${shopping.limite.minimo}")
	private BigDecimal limiteMinimo;
	
	private Total resumo;
	
	@PostConstruct
	public void inicializar() {
		resumo = new Total(limiteMinimo);
	}
	
	@EventListener
	public void tratarAtualizacaoCarrinho(AtualizacaoCarrinho atualizacao) {
		int quantidade = atualizacao.getQuantidadeProdutos();
		BigDecimal totalOriginal = atualizacao.getTotalProdutos();
		BigDecimal desconto = calculadoraDesconto.calcularDesconto(totalOriginal);
		
		resumo.atualizar(quantidade, desconto, totalOriginal);
	}
	
	public Total getResumo() {
		return resumo;
	}
	
}
