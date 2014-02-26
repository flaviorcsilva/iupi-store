package br.org.iupi.store.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import br.org.iupi.store.R;
import br.org.iupi.store.loja.LojaForm;

public class LoginForm extends Activity {

	private EditText edtEmail;
	private EditText edtSenha;
	private TextView lblMensagemLogin;
	private View viwLoginForm;
	private View viwLoginStatus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_form);

		edtEmail = (EditText) findViewById(R.id.edtEmail);
		edtSenha = (EditText) findViewById(R.id.edtSenha);
		lblMensagemLogin = (TextView) findViewById(R.id.lblMensagemLogin);

		viwLoginForm = findViewById(R.id.viwLoginForm);
		viwLoginStatus = findViewById(R.id.viwLoginStatus);

		criaButtonAcessar();
		criaButtonEsqueceuSuaSenha();
	}

	private Button criaButtonAcessar() {
		Button btnAcessar = (Button) findViewById(R.id.btnAcessar);

		btnAcessar.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				validaLogin();
			}
		});

		return btnAcessar;
	}

	private Button criaButtonEsqueceuSuaSenha() {
		Button btnEsqueceuSuaSenha = (Button) findViewById(R.id.btnEsqueceuSenha);

		btnEsqueceuSuaSenha.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				Intent intent = new Intent(LoginForm.this,
						EsqueceuSuaSenhaForm.class);
				startActivity(intent);
			}
		});

		return btnEsqueceuSuaSenha;
	}

	private void validaLogin() {
		edtEmail.setError(null);
		edtSenha.setError(null);

		String email = edtEmail.getText().toString();
		String senha = edtSenha.getText().toString();

		boolean cancela = false;
		View focusView = null;

		if (TextUtils.isEmpty(senha)) {
			edtSenha.setError(getString(R.string.erro_campo_obrigatorio));
			focusView = edtSenha;
			cancela = true;
		}

		if (TextUtils.isEmpty(email)) {
			edtEmail.setError(getString(R.string.erro_campo_obrigatorio));
			focusView = edtEmail;
			cancela = true;
		} else if (!email.contains("@")) {
			edtEmail.setError(getString(R.string.erro_email_invalido));
			focusView = edtEmail;
			cancela = true;
		}

		if (cancela) {
			focusView.requestFocus();
		} else {
			lblMensagemLogin.setText(R.string.info_logando);
			showProgress(true);

			Intent intent = new Intent(LoginForm.this, LojaForm.class);
			startActivity(intent);

			finish();
		}
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void showProgress(final boolean show) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			int shortAnimTime = getResources().getInteger(
					android.R.integer.config_shortAnimTime);

			viwLoginStatus.setVisibility(View.VISIBLE);
			viwLoginStatus.animate().setDuration(shortAnimTime)
					.alpha(show ? 1 : 0)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							viwLoginStatus.setVisibility(show ? View.VISIBLE
									: View.GONE);
						}
					});

			viwLoginForm.setVisibility(View.VISIBLE);
			viwLoginForm.animate().setDuration(shortAnimTime)
					.alpha(show ? 0 : 1)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							viwLoginForm.setVisibility(show ? View.GONE
									: View.VISIBLE);
						}
					});
		} else {
			viwLoginStatus.setVisibility(show ? View.VISIBLE : View.GONE);
			viwLoginForm.setVisibility(show ? View.GONE : View.VISIBLE);
		}
	}
}
