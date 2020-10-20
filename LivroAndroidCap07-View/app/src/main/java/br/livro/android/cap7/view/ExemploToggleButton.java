package br.livro.android.cap7.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;
import br.livro.android.cap7.R;

/**
 * Exemplo de botão com Imagem
 * 
 * @author rlecheta
 * 
 */
public class ExemploToggleButton extends Activity {
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.exemplo_toogle_button);

	}
	
	public void Clicar(View v){
		ToggleButton toggle = (ToggleButton) findViewById(R.id.toggle);
		boolean selecionado = toggle.isChecked();
		Toast.makeText(ExemploToggleButton.this, "Selecionado: " + selecionado, Toast.LENGTH_SHORT).show();
	}
}