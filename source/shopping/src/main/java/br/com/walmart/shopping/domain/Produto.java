package br.com.walmart.shopping.domain;

import java.math.BigDecimal;

import org.springframework.format.annotation.NumberFormat;

public class Produto {
	
	private String nome;
	
	private String imagem;
	
	@NumberFormat
	private BigDecimal valor;
	
	public Produto() {}
	
	public Produto(String nome, BigDecimal valor) {
		this.nome = nome;
		this.valor = valor;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getValor() {
		return valor;
	}
	
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

}
