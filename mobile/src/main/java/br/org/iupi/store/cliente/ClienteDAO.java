package br.org.iupi.store.cliente;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import br.org.iupi.mobile.sqlitepersistence.dao.GenericSQLiteDAO;

public class ClienteDAO extends GenericSQLiteDAO<Cliente> {

	public ClienteDAO(Context context, String database, CursorFactory factory,
			int version, Class<Cliente> entityClass) {
		super(context, database, factory, version, entityClass);
	}

	public List<Cliente> consultaTodos() {
		String[] columns = { "id", "cpf", "nome", "endereco", "cidade",
				"estado", "cep", };

		Cursor cursor = getWritableDatabase().query(getTableName(), columns,
				null, null, null, null, null);

		List<Cliente> clientes = criaListaDeClientes(cursor);

		return clientes;
	}

	private List<Cliente> criaListaDeClientes(Cursor cursor) {
		List<Cliente> clientes = new ArrayList<Cliente>();

		while (cursor.moveToNext()) {
			Cliente cliente = new Cliente();

			cliente.setId(cursor.getLong(0));
			cliente.setCpf(cursor.getString(1));
			cliente.setNome(cursor.getString(2));
			cliente.setEndereco(cursor.getString(3));
			cliente.setCidade(cursor.getString(4));
			cliente.setEstado(cursor.getString(5));
			cliente.setCep(cursor.getString(6));

			clientes.add(cliente);
		}

		return clientes;
	}
}
