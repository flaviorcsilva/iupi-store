package br.org.iupi.store.usuario.dominio;

import lombok.Getter;
import lombok.Setter;

public class Usuario {
	@Getter
	@Setter
	private String nome;

	@Getter
	@Setter
	private String email;

	@Getter
	@Setter
	private String senha;

	@Getter
	@Setter
	private String foto;
}
