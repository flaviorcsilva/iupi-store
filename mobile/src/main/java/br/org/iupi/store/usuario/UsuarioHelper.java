package br.org.iupi.store.usuario;

import android.graphics.Bitmap;
import android.widget.EditText;
import android.widget.ImageView;
import br.org.iupi.store.R;
import br.org.iupi.store.util.ImagemHelper;

public class UsuarioHelper {

	private Usuario usuario;

	private EditText edtNome;
	private EditText edtEmail;
	private EditText edtSenha;
	private EditText edtSenhaConfirmacao;
	private ImageView imgFoto;

	public UsuarioHelper(UsuarioForm form) {
		usuario = new Usuario();

		edtNome = (EditText) form.findViewById(R.id.edtUsuarioNome);
		edtEmail = (EditText) form.findViewById(R.id.edtUsuarioEmail);
		edtSenha = (EditText) form.findViewById(R.id.edtUsuarioSenha);
		edtSenhaConfirmacao = (EditText) form
				.findViewById(R.id.edtUsuarioSenhaConfirmacao);
		imgFoto = (ImageView) form.findViewById(R.id.imgUsuarioFoto);
	}

	public Usuario criaUsuarioComDadosDoFormulario() {
		usuario.setNome(edtNome.getText().toString());
		usuario.setEmail(edtEmail.getText().toString());
		usuario.setSenha(edtSenha.getText().toString());

		return usuario;
	}

	public void populaFormulario(Usuario usuario) {
		this.usuario = usuario;

		edtNome.setText(usuario.getNome());
		edtEmail.setText(usuario.getEmail());
		edtSenha.setText(usuario.getSenha());
		edtSenhaConfirmacao.setText(usuario.getSenha());

		if (usuario.hasFoto()) {
			carregaFoto(usuario.getFoto(), 150, 150);
		}
	}

	public void carregaFoto(String arquivoDeFoto, int largura, int altura) {
		usuario.setFoto(arquivoDeFoto);

		Bitmap foto = ImagemHelper.getImagemRedimensionada(arquivoDeFoto,
				largura, altura);

		imgFoto.setImageBitmap(ImagemHelper.getImagemArredondada(foto, 100));
	}
}
