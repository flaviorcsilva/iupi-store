package br.org.iupi.store.loja;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import br.org.iupi.store.R;
import br.org.iupi.store.cliente.ConsultaClienteForm;
import br.org.iupi.store.usuario.ConsultaUsuarioForm;

public class LojaForm extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loja_form);

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.loja_form, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.mnuMaisOpcoes:
			break;

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	public void selecionaOpcao(View view) {
		TextView textView = (TextView) view;
		
		if (textView.getText().toString()
				.equals(getString(R.string.txt_clientes))) {
			Intent intent = new Intent(this, ConsultaClienteForm.class);
			startActivity(intent);
		}

		if (textView.getText().toString()
				.equals(getString(R.string.txt_usuarios))) {
			Intent intent = new Intent(this, ConsultaUsuarioForm.class);
			startActivity(intent);
		}
	}
}
