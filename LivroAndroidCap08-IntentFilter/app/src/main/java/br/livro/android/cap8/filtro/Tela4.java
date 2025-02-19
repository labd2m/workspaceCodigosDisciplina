package br.livro.android.cap8.filtro;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Tela4 extends Activity {

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		//chamada pela CATEGORIA_DUPLICADA com a action "ABRIR_ACTIVITY"
		
		TextView text = new TextView(this);
		text.setText("Esta é a tela 4");
		setContentView(text);
	}
}
