package br.org.iupi.store.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import br.org.iupi.store.R;
import br.org.iupi.store.login.LoginForm;

public class SplashScreen extends Activity implements Runnable {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);
		
		Handler handler = new Handler();
		handler.postDelayed(this, 3000);
	}

	public void run() {
		startActivity(new Intent(this, LoginForm.class));
		finish();
	}
}
