package br.org.iupi.store.cliente;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import br.org.iupi.mobile.sqlitepersistence.annotation.Column;
import br.org.iupi.mobile.sqlitepersistence.annotation.Entity;
import br.org.iupi.mobile.sqlitepersistence.annotation.Id;
import br.org.iupi.mobile.sqlitepersistence.annotation.Table;

@Entity
@Table
public class Cliente implements Serializable {

	private static final long serialVersionUID = -4599875064277461619L;

	@Getter
	@Setter
	@Id(autoincrement = true)
	private Long id;

	@Getter
	@Setter
	@Column(unique = true, nullable = false)
	private String cpf;

	@Getter
	@Setter
	@Column(nullable = false)
	private String nome;

	@Getter
	@Setter
	@Column
	private String endereco;

	@Getter
	@Setter
	@Column
	private String cidade;

	@Getter
	@Setter
	@Column
	private String estado;

	@Getter
	@Setter
	@Column
	private String cep;

	public Cliente() {
	}

	public Cliente(Long id, String cpf, String nome) {
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
	}

}
