package br.com.walmart.shopping.components;

import java.io.IOException;
import java.math.BigDecimal;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.walmart.shopping.domain.Desconto;

@Component
public class CalculadoraDescontoFixo implements CalculadoraDesconto {
	
	@Value("classpath:/tabeladesconto.json")
	private Resource tabelaDesconto;
	
	private Desconto[] descontos;
	
	@PostConstruct
	public void carregarDescontos() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		descontos = mapper.readValue(tabelaDesconto.getInputStream(), Desconto[].class);
	}
	
	public BigDecimal calcularDesconto(BigDecimal totalCompra) {
		for (Desconto desconto : descontos) {
			if (totalCompra.compareTo(desconto.getValorMinimo()) > 0) {
				return desconto.aplicar(totalCompra);
			}
		}
		
		return BigDecimal.ZERO;
	}

}
