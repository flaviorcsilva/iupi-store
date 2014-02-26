package br.org.iupi.store.usuario;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import br.org.iupi.mobile.sqlitepersistence.dao.GenericSQLiteDAO;

public class UsuarioDAO extends GenericSQLiteDAO<Usuario> {

	public UsuarioDAO(Context context, String database, CursorFactory factory,
			int version, Class<Usuario> entityClass) {
		super(context, database, factory, version, entityClass);
	}

	public List<Usuario> consultaTodos() {
		String[] columns = { "id", "nome", "email", "senha", "foto" };

		Cursor cursor = getWritableDatabase().query(getTableName(), columns,
				null, null, null, null, null);

		List<Usuario> usuarios = criaListaDeUsuarios(cursor);

		return usuarios;
	}

	public List<Usuario> consultaUsuariosPorNome(String nome) {
		String sql = "SELECT id, nome, email, senha, foto FROM "
				+ getTableName() + " WHERE nome LIKE ?";
		String[] whereArgs = { "%" + nome + "%" };

		Cursor cursor = getWritableDatabase().rawQuery(sql, whereArgs);

		List<Usuario> usuarios = criaListaDeUsuarios(cursor);

		return usuarios;
	}

	public String consultaSenhaDoUsuario(String email) {
		String senha = null;

		String sql = "SELECT senha FROM " + getTableName() + " WHERE email = ?";
		String[] whereArgs = { email };

		Cursor cursor = getWritableDatabase().rawQuery(sql, whereArgs);

		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			senha = cursor.getString(cursor.getColumnIndex("senha"));
		}

		return senha;
	}

	private List<Usuario> criaListaDeUsuarios(Cursor cursor) {
		List<Usuario> usuarios = new ArrayList<Usuario>();

		while (cursor.moveToNext()) {
			Usuario usuario = new Usuario();

			usuario.setId(cursor.getLong(0));
			usuario.setNome(cursor.getString(1));
			usuario.setEmail(cursor.getString(2));
			usuario.setSenha(cursor.getString(3));
			usuario.setFoto(cursor.getString(4));

			usuarios.add(usuario);
		}

		return usuarios;
	}
}
