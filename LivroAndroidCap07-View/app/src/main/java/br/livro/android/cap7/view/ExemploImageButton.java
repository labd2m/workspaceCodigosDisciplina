package br.livro.android.cap7.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import br.livro.android.cap7.R;

/**
 * Exemplo de botão com Imagem
 * 
 * @author rlecheta
 * 
 */
public class ExemploImageButton extends Activity {
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.exemplo_image_button);

		ImageButton botaoImagem2 = (ImageButton) findViewById(R.id.img2);
		botaoImagem2.setImageResource(R.drawable.smile2);
	}
	
	public void ClicaImagem1(View v){
		Toast.makeText(ExemploImageButton.this, "Imagem 1 OK", Toast.LENGTH_SHORT).show();
	}
	public void ClicaImagem2(View v){
		Toast.makeText(ExemploImageButton.this, "Imagem 2 OK", Toast.LENGTH_SHORT).show();
	}
}