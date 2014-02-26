package br.org.iupi.store.pedido.dominio;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import br.org.iupi.store.produto.dominio.Produto;

@AllArgsConstructor
public class Pedido {

	@Getter
	private Date data;

	@Getter
	private List<ItemDoPedido> itens;

	public Pedido() {
		data = new Date();
	}

	public void adicionaItemAoPedido(Produto produto, int quantidade) {
		ItemDoPedido item = new ItemDoPedido(produto, quantidade);

		itens.add(item);
	}

	public double getValorTotal() {
		double totalDoPedido = 0.0;

		for (ItemDoPedido item : itens) {
			totalDoPedido = totalDoPedido + item.getSubTotal();
		}

		return totalDoPedido;
	}
}
