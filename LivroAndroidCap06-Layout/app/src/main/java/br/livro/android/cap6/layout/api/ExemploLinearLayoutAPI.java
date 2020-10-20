package br.livro.android.cap6.layout.api;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

/**
 * Exemplo de criação de um LinearLayout apenas pela API, sem XML
 * 
 * @author ricardo
 *
 */
public class ExemploLinearLayoutAPI extends Activity {

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		// Cria o layout
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
		
		// The gesture threshold expressed in dip
		int dip = 10;

		Log.i("trevisan","dip: " + dip);
		
		// Convert the dips to pixels
		final float scale = getResources().getDisplayMetrics().density;
		Log.i("trevisan","scale: " + scale);
		int px = (int) (dip * scale + 0.5f);
		
		Log.i("trevisan","px: " + px);
		
		layout.setPadding(px,px,px,px);
		
		//pixels
//		layout.setBackgroundColor(Color.WHITE);

		TextView nome = new TextView(this);
		nome.setText("Nome:");
		nome.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		layout.addView(nome);

		EditText tnome = new EditText(this);
		tnome.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
		layout.addView(tnome);
		// focus
		tnome.requestFocus();

		TextView senha = new TextView(this);
		senha.setText("Senha:");
		senha.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		layout.addView(senha);

		EditText tsenha = new EditText(this);
		tsenha.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
		tsenha.setTransformationMethod(new PasswordTransformationMethod());
		layout.addView(tsenha);

		// Botão alinhado a direita
		Button ok = new Button(this);
		ok.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		ok.setGravity(Gravity.RIGHT);
		ok.setText("OK");
		layout.addView(ok);

		// Informa o layout
		setContentView(layout);
	}
}
