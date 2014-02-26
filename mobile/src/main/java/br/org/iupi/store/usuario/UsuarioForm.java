package br.org.iupi.store.usuario;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import br.org.iupi.mobile.service.camera.CameraService;
import br.org.iupi.mobile.service.common.ActionResult;
import br.org.iupi.store.R;

public class UsuarioForm extends Activity {

	private static final String TAG_FOTO = "foto";

	private EditText edtNome;
	private EditText edtEmail;
	private EditText edtSenha;
	private EditText edtSenhaConfirmacao;
	private Button btnSalvar;
	private View focusView;

	private Usuario usuario;
	private String arquivoDeFoto;
	private UsuarioHelper helper;
	private UsuarioDAO dao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.usuario_form);

		dao = new UsuarioDAO(this, "loja.db", null, 1, Usuario.class);
		helper = new UsuarioHelper(this);

		if (savedInstanceState != null) {
			arquivoDeFoto = savedInstanceState.getString(TAG_FOTO);
		}

		edtNome = criaCampoNome();
		edtEmail = criaCampoEmail();
		edtSenha = criaCampoSenha();
		edtSenhaConfirmacao = criaCampoSenhaConfirmacao();

		criaImagemFoto();
		criaBotaoCancelar();
		btnSalvar = criaBotaoSalvar();

		Intent intent = getIntent();
		usuario = (Usuario) intent.getSerializableExtra("usuarioSelecionado");

		if (usuario != null) {
			btnSalvar.setText("Alterar");
			helper.populaFormulario(usuario);
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		outState.putString(TAG_FOTO, arquivoDeFoto);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == ActionResult.TAKE_A_PHOTO.ordinal()) {
			if (resultCode == Activity.RESULT_OK) {
				helper.carregaFoto(arquivoDeFoto, 150, 150);
			} else {
				arquivoDeFoto = null;
			}
		}
	}

	private EditText criaCampoNome() {
		EditText edtNome = (EditText) findViewById(R.id.edtUsuarioNome);

		return edtNome;
	}

	private EditText criaCampoEmail() {
		EditText edtEmail = (EditText) findViewById(R.id.edtUsuarioEmail);

		return edtEmail;
	}

	private EditText criaCampoSenha() {
		EditText edtSenha = (EditText) findViewById(R.id.edtUsuarioSenha);

		return edtSenha;
	}

	private EditText criaCampoSenhaConfirmacao() {
		EditText edtSenhaConfirmacao = (EditText) findViewById(R.id.edtUsuarioSenhaConfirmacao);

		return edtSenhaConfirmacao;
	}

	private ImageView criaImagemFoto() {
		ImageView imgFoto = (ImageView) findViewById(R.id.imgUsuarioFoto);

		imgFoto.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				arquivoDeFoto = Environment.getExternalStorageDirectory()
						.toString()
						+ "/foto-"
						+ System.currentTimeMillis()
						+ ".png";

				CameraService.takeAPhoto(UsuarioForm.this, arquivoDeFoto);
			}
		});

		return imgFoto;
	}

	private Button criaBotaoCancelar() {
		Button btnCancelar = (Button) findViewById(R.id.btnUsuarioCancelar);

		btnCancelar.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				finish();
			}
		});

		return btnCancelar;
	}

	private Button criaBotaoSalvar() {
		Button btnSalvar = (Button) findViewById(R.id.btnUsuarioSalvar);

		btnSalvar.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				if (isCamposValidados()) {
					Usuario usuarioNovo = helper
							.criaUsuarioComDadosDoFormulario();

					if (usuario == null) {
						dao.save(usuarioNovo);
					} else {
						usuarioNovo.setId(usuario.getId());
						dao.update(usuarioNovo);
					}

					dao.close();

					finish();
				} else {
					focusView.requestFocus();
				}
			}
		});

		return btnSalvar;
	}

	private boolean isCamposValidados() {
		boolean camposValidados = true;

		focusView = null;
		edtNome.setError(null);
		edtEmail.setError(null);
		edtSenha.setError(null);

		String nome = edtNome.getText().toString();
		String email = edtEmail.getText().toString();
		String senha = edtSenha.getText().toString();
		String senhaConfirmacao = edtSenhaConfirmacao.getText().toString();

		if (TextUtils.isEmpty(senha)) {
			edtSenha.setError(getString(R.string.erro_campo_obrigatorio));
			focusView = edtSenha;
			camposValidados = false;
		}

		if (!senha.equals(senhaConfirmacao)) {
			edtSenha.setError(getString(R.string.erro_confirmacao_senha));
			focusView = edtSenha;
			camposValidados = false;
		}

		if (TextUtils.isEmpty(email)) {
			edtEmail.setError(getString(R.string.erro_campo_obrigatorio));
			focusView = edtEmail;
			camposValidados = false;
		}

		if (TextUtils.isEmpty(nome)) {
			edtNome.setError(getString(R.string.erro_campo_obrigatorio));
			focusView = edtNome;
			camposValidados = false;
		}

		return camposValidados;
	}

	public String getArquivoDeFoto() {
		return arquivoDeFoto;
	}
}
