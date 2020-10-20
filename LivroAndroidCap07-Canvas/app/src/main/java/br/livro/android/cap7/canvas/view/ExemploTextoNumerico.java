package br.livro.android.cap7.canvas.view;

import br.livro.android.cap7.canvas.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * Exemplo de View customizada
 * 
 * <pre>
 * &lt;br.livro.android.canvas.view.NumericText
 * 		android:layout_width=&quot;fill_parent&quot;
 *     	android:layout_height=&quot;fill_parent&quot;
 *     	android:numeric=&quot;true&quot; 
 * /&gt;
 * </pre>
 * 
 * @author ricardo
 * 
 */
public class ExemploTextoNumerico extends Activity implements OnClickListener {
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.texto_numerico);
		Button ok = (Button) findViewById(R.id.ok);
		ok.setOnClickListener(this);
	}
	public void onClick(View view) {
		TextoNumerico text = (TextoNumerico) findViewById(R.id.n1);
		Integer numero = text.getNumero();
		Toast.makeText(this, "Numero: " + numero, Toast.LENGTH_SHORT).show();
	}
}
