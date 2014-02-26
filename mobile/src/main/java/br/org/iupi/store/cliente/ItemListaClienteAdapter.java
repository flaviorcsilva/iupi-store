package br.org.iupi.store.cliente;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import br.org.iupi.store.R;

public class ItemListaClienteAdapter extends BaseAdapter {

	private List<Cliente> clientes;
	private Activity contexto;

	public ItemListaClienteAdapter(Activity contexto, List<Cliente> clientes) {
		this.contexto = contexto;
		this.clientes = clientes;
	}

	public int getCount() {
		return clientes.size();
	}

	public Object getItem(int position) {
		return clientes.get(position);
	}

	public long getItemId(int position) {
		return clientes.get(position).getId();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		final Cliente cliente = clientes.get(position);

		LayoutInflater inflater = contexto.getLayoutInflater();

		View linha = inflater.inflate(R.layout.item_lista_cliente, null);

		TextView lblNome = (TextView) linha
				.findViewById(R.id.txvItemListaClienteNome);
		lblNome.setText(cliente.getNome());

		ImageView imgExcluir = (ImageView) linha
				.findViewById(R.id.imgItemListaClienteExcluir);

		imgExcluir.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Toast.makeText(contexto, cliente.getNome(), Toast.LENGTH_SHORT)
						.show();
			}
		});

		return linha;
	}
}
