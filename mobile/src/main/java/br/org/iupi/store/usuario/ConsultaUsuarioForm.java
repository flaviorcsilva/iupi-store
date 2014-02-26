package br.org.iupi.store.usuario;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.SearchView;
import br.org.iupi.mobile.service.email.GMailService;
import br.org.iupi.store.R;

public class ConsultaUsuarioForm extends Activity {

	private ListView lstUsuarios;
	private Usuario usuario;
	private UsuarioDAO dao;
	private String nomeParaPesquisa;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.consulta_usuario_form);

		configuraActionBar();

		dao = new UsuarioDAO(this, "loja.db", null, 1, Usuario.class);

		lstUsuarios = criaListViewUsuarios();

		/* Registra o menu do clique longo a lista de usuários */
		registerForContextMenu(lstUsuarios);

		handleIntent(getIntent());
	}

	@Override
	protected void onNewIntent(Intent intent) {
		setIntent(intent);
		handleIntent(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.consulta_usuario_form, menu);

		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(
				R.id.mnuPesquisaUsuario).getActionView();
		searchView.setSearchableInfo(searchManager
				.getSearchableInfo(getComponentName()));

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.mnuConsultaUsuario:
			Intent intent = new Intent(this, UsuarioForm.class);
			startActivity(intent);
			break;

		case android.R.id.home:
			finish();

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View view,
			ContextMenuInfo menuInfo) {
		menu.setHeaderTitle("Usuário: " + usuario.getNome());

		criaMenuItemExcluir(menu);
		criaMenuItemEnviarEmail(menu);

		super.onCreateContextMenu(menu, view, menuInfo);
	}

	@Override
	protected void onResume() {
		super.onResume();

		populaListaDeUsuarios();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		dao.close();
	}

	private void handleIntent(Intent intent) {
		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			nomeParaPesquisa = intent.getStringExtra(SearchManager.QUERY);
		}
	}

	private ListView criaListViewUsuarios() {
		ListView lstUsuarios = (ListView) findViewById(R.id.lstUsuarios);

		lstUsuarios.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long id) {
				usuario = (Usuario) adapter.getItemAtPosition(position);

				Intent intent = new Intent(ConsultaUsuarioForm.this,
						UsuarioForm.class);
				intent.putExtra("usuarioSelecionado", usuario);

				startActivity(intent);
			}
		});

		lstUsuarios.setOnItemLongClickListener(new OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> adapter, View view,
					int position, long id) {
				usuario = (Usuario) adapter.getItemAtPosition(position);

				return false;
			}
		});

		return lstUsuarios;
	}

	private MenuItem criaMenuItemExcluir(ContextMenu menu) {
		MenuItem mnuExcluir = menu.add("Excluir");
		mnuExcluir.setIcon(R.drawable.icon_excluir);
		mnuExcluir.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			public boolean onMenuItemClick(MenuItem item) {
				dao.remove(usuario);

				populaListaDeUsuarios();

				return false;
			}
		});

		return mnuExcluir;
	}

	private MenuItem criaMenuItemEnviarEmail(ContextMenu menu) {
		MenuItem mnuEnviarEmail = menu.add("Enviar E-mail");
		mnuEnviarEmail.setIcon(R.drawable.icon_email);
		mnuEnviarEmail
				.setOnMenuItemClickListener(new OnMenuItemClickListener() {

					public boolean onMenuItemClick(MenuItem item) {
						String emailTo = usuario.getEmail();
						String subject = "Olá " + usuario.getNome();
						String body = "Enviado pelo aplicativo iUPi! Store";
						String sender = "flavio.roberto.cruz@gmail.com";

						GMailService service = new GMailService(sender,
								"Pit@1702");
						try {
							service.sendMail(subject, body, sender, emailTo);
						} catch (Exception e) {
							e.printStackTrace();
						}

						return false;
					}
				});

		return mnuEnviarEmail;
	}

	private void populaListaDeUsuarios() {
		List<Usuario> usuarios = new ArrayList<Usuario>();

		if (TextUtils.isEmpty(nomeParaPesquisa)) {
			usuarios = dao.consultaTodos();
		} else {
			usuarios = dao.consultaUsuariosPorNome(nomeParaPesquisa);
		}

		ItemListaUsuarioAdapter adapter = new ItemListaUsuarioAdapter(
				ConsultaUsuarioForm.this, usuarios);

		lstUsuarios.setAdapter(adapter);
	}

	private void configuraActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
	}
}
