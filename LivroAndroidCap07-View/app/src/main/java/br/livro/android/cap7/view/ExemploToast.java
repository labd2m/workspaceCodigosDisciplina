package br.livro.android.cap7.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import br.livro.android.cap7.R;

/**
 * Demo Toast abrindo View de um XML
 * 
 * @author ricardo
 *
 */
public class ExemploToast extends Activity {
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.exemplo_toast);

//		Toast toast = Toast.makeText(this, "Teste de Mensagem", Toast.LENGTH_SHORT);
//		toast.show();

		View tv = getView();
		Toast toast = new Toast(this);
		toast.setView(tv);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.show();
	}

	private View getView() {
		//View view = new TextView(this);
		//view.setText("Podemos mostrar qualquer coisa no Toast");

		//Abrir uma View a partir de um xml
		LayoutInflater inflate = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflate.inflate(R.layout.exemplo_toast_view_inflate, null);

		//ImageView view = new ImageView(this);
		//view.setImageResource(R.drawable.smile1);

		return view;
	}
}