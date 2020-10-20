package br.livro.android.cap8.filtro;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Tela2 extends Activity {

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		//chamada pela category "CATEGORIA_TESTE" do android com Action "ACAO_TESTE"

		Bundle extras = getIntent().getExtras();
		if(extras != null){
			String msg = extras.getString("mensagem");

			TextView text = new TextView(this);
			text.setText("Esta é a tela 2\nMensagem: " + msg);
			setContentView(text);
		} else {

			TextView text = new TextView(this);
			text.setText("Esta é a tela 2");
			setContentView(text);
		}
	}
}
