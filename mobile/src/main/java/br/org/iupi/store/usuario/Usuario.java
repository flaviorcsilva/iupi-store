package br.org.iupi.store.usuario;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import br.org.iupi.mobile.sqlitepersistence.annotation.Column;
import br.org.iupi.mobile.sqlitepersistence.annotation.Entity;
import br.org.iupi.mobile.sqlitepersistence.annotation.Id;
import br.org.iupi.mobile.sqlitepersistence.annotation.Table;

@Entity
@Table
public class Usuario implements Serializable {

	private static final long serialVersionUID = -1548586978338400298L;

	@Getter
	@Setter
	@Id(autoincrement = true)
	private Long id;

	@Getter
	@Setter
	@Column(unique = true, nullable = false)
	private String nome;

	@Getter
	@Setter
	@Column(unique = true, nullable = false)
	private String email;

	@Getter
	@Setter
	@Column(nullable = false)
	private String senha;
	
	@Getter
	@Setter
	@Column
	private String foto;
	
	public boolean hasFoto() {
		if (foto != null && !foto.equals("")) {
			return true;
		}

		return false;
	}

}
