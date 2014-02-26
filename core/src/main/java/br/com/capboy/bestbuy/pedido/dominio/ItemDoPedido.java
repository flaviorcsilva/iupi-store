package br.com.capboy.bestbuy.pedido.dominio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import br.com.capboy.bestbuy.produto.dominio.Produto;

@AllArgsConstructor
public class ItemDoPedido {

	@Getter
	@Setter
	private Produto produto;

	@Getter
	@Setter
	private Integer quantidade;

	public double getSubTotal() {
		return produto.getPreco() * quantidade;
	}
}
