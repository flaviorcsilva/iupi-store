package br.org.iupi.store.usuario;

import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.org.iupi.store.R;
import br.org.iupi.store.util.ImagemHelper;

public class ItemListaUsuarioAdapter extends BaseAdapter {

	private List<Usuario> usuarios;
	private Activity contexto;

	public ItemListaUsuarioAdapter(Activity contexto, List<Usuario> usuarios) {
		this.contexto = contexto;
		this.usuarios = usuarios;
	}

	public int getCount() {
		return usuarios.size();
	}

	public Object getItem(int position) {
		return usuarios.get(position);
	}

	public long getItemId(int position) {
		return usuarios.get(position).getId();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		Usuario usuario = usuarios.get(position);

		LayoutInflater inflater = contexto.getLayoutInflater();

		View linha = inflater.inflate(R.layout.item_lista_usuario, null);

		ImageView imgFoto = (ImageView) linha
				.findViewById(R.id.imgItemListaUsuarioFoto);
		if (usuario.hasFoto()) {
			Bitmap foto = ImagemHelper.getImagemRedimensionada(
					usuario.getFoto(), 90, 90);

			imgFoto.setImageBitmap(ImagemHelper.getImagemArredondada(foto, 100));
		} else {
			imgFoto.setImageDrawable(contexto.getResources().getDrawable(
					R.drawable.icon_foto));
		}

		TextView lblNome = (TextView) linha
				.findViewById(R.id.txvItemListaUsuarioNome);
		lblNome.setText(usuario.getNome());
		
		return linha;
	}
}
