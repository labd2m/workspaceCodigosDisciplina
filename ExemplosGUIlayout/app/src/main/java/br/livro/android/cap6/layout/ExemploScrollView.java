package br.livro.android.cap6.layout;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import br.livro.android.cap6.R;

/**
 * Exemplo de ScrollView, adicionando varios TextView dinamicamente no loop para fazer scroll
 * 
 * @author ricardo
 *
 */
public class ExemploScrollView extends Activity {
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.exemplo_scrollview);

		LinearLayout layout = (LinearLayout) findViewById(R.id.layout1);

		for (int i = 0; i < 100; i++) {

			TextView text = new TextView(this);
			//eh obrigatorio o layout_width e layout_height
			text.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
			text.setText("Texto: " + i);

			layout.addView(text);
		}
	}
}
