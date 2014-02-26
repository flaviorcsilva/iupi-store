package br.org.iupi.store.cliente;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;
import br.org.iupi.store.R;

public class ConsultaClienteForm extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.consulta_cliente_form);

		configuraActionBar();

		criaListViewClientes();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.consulta_cliente_form, menu);

		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(
				R.id.mnuPesquisaCliente).getActionView();
		searchView.setSearchableInfo(searchManager
				.getSearchableInfo(getComponentName()));

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onResume() {
		super.onResume();

		// populaListaDeClientes();
	}

	private ListView criaListViewClientes() {
		ListView lstClientes = (ListView) findViewById(R.id.lstClientes);

		/*
		 * String[] usuarios = { "Flávio Roberto", "Erica Jordana",
		 * "Rosendo Manoel" };
		 * 
		 * ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
		 * android.R.layout.simple_list_item_1, usuarios);
		 */

		List<Cliente> clientes = new ArrayList<Cliente>();
		clientes.add(new Cliente(1L, "77276469115", "Flávio Roberto"));
		clientes.add(new Cliente(2L, "02728594430", "Érica Jordana"));

		ItemListaClienteAdapter adapter = new ItemListaClienteAdapter(
				ConsultaClienteForm.this, clientes);

		lstClientes.setAdapter(adapter);

		return lstClientes;
	}

	private void configuraActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
	}
}
