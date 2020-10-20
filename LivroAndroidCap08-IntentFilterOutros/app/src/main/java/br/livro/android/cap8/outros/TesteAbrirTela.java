package br.livro.android.cap8.outros;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Exemplo de como criar uma Intent para chamar uma Activity em outro projeto "LivroAndroidCap8-IntentFilter"
 * 
 * @author ricardo
 *
 */
public class TesteAbrirTela extends Activity {

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.main);

		Button b = (Button) findViewById(R.id.btOk);
		b.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// Chama a Tela3 criada no outro projeto
				Intent it = new Intent("ACAO_TESTE");
				//it.addCategory("CATEGORIA_3");
				startActivity(it);
			}
		});
	}
}