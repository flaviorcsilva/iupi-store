package br.org.iupi.store.pedido.servico;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
class RespostaPedido {

	@Getter
	private Long numeroDoPedido;

	@Getter
	private String mensagem;
}
