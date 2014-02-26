package br.com.capboy.bestbuy.venda.dominio;

import lombok.Getter;
import lombok.Setter;
import br.com.capboy.bestbuy.pedido.dominio.Pedido;

public class Venda {
	
	@Getter
	@Setter
	private Pedido pedido;
	
	@Getter
	@Setter
	private FormaDePagamento formaDePagamento;
	
	@Getter
	@Setter
	private double frete;
	
	@Getter
	@Setter
	private double valor;
}
