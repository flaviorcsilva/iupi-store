package br.com.capboy.bestbuy.produto.dominio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Produto {

	@Getter
	@Setter
	private int codigo;

	@Getter
	@Setter
	private String descricao;

	@Getter
	@Setter
	private double preco;

	@Getter
	@Setter
	private int quantidadeEmEstoque;

	public void baixaEstoque(int quantidade) throws Exception {
		if (!temEstoqueDisponivel(quantidade)) {
			throw new Exception(
					"Não há quantidade disponível do produto em estoque");
		}
	}

	public void supreEstoque(int quantidade) {
		quantidadeEmEstoque = quantidadeEmEstoque + quantidade;
	}

	public boolean temEstoqueDisponivel(int quantidade) {
		if (quantidade <= this.quantidadeEmEstoque) {
			return true;
		}

		return false;
	}
}