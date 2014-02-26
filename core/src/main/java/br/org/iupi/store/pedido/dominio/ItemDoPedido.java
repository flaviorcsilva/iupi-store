package br.org.iupi.store.pedido.dominio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import br.org.iupi.store.produto.dominio.Produto;

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
