package br.org.iupi.store.pedido.servico;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.org.iupi.store.pedido.dominio.Pedido;

@Path("/pedidoservice")
public class PedidoService {

	@POST
	@Path("/registra")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public RespostaPedido registra(Pedido pedido) {
		return new RespostaPedido(1809L, "Pedido de "
				+ pedido.getItens().get(0).getQuantidade() + " "
				+ pedido.getItens().get(0).getProduto()
				+ " realizado com sucesso");
	}
}