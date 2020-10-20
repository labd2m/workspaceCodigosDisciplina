package br.livro.android.cap6.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import br.livro.android.cap6.R;

/**
 * Exemplo da View Gallery para visualizar imagens
 * 
 * @author rlecheta
 * 
 */
public class ExemploGallery extends Activity {
	// Planetas
	private int[] imagens = { R.drawable.mercurio, R.drawable.venus,
			R.drawable.terra, R.drawable.marte, R.drawable.jupiter,
			R.drawable.saturno, R.drawable.urano, R.drawable.netuno,
			R.drawable.plutao };

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.exemplo_gallery);

		Gallery g = (Gallery) findViewById(R.id.gallery);
		g.setAdapter(new AdaptadorImagem(this, imagens,new Gallery.LayoutParams(150, 100)));

		g.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView parent, View v, int posicao,long id) {
				ImageView imgView = new ImageView(ExemploGallery.this);
				imgView.setImageResource(imagens[posicao]);
				Toast t = new Toast(ExemploGallery.this);
				t.setView(imgView);
				t.show();
			}
		});
	}
}