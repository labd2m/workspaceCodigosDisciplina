package br.livro.android.cap6.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;
import android.widget.Gallery.LayoutParams;
import br.livro.android.cap6.R;

/**
 * Exemplo de ImageSwitcher
 * 
 * @author ricardo
 * 
 */
public class ExemploImageSwitcher extends Activity implements ViewSwitcher.ViewFactory {
	// Planetas
	private int[] imagens = { R.drawable.mercurio, R.drawable.venus,
			R.drawable.terra, R.drawable.marte, R.drawable.jupiter,
			R.drawable.saturno, R.drawable.urano, R.drawable.netuno,
			R.drawable.plutao };
	private ImageSwitcher imageSwitcher;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(br.livro.android.cap6.R.layout.exemplo_image_switcher);

		// Configura o ImageSwitcher e os efeitos
		imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
		imageSwitcher.setFactory(this);
		imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,android.R.anim.fade_in));
		imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,android.R.anim.fade_out));

		// Configura o adaptador da galeria
		Gallery g = (Gallery) findViewById(R.id.gallery);
		g.setAdapter(new AdaptadorImagem(this, imagens,new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT)));
		g.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView parent, View v, int posicao,long id) {
				// Avisa o ImageSwitcher que a imagem foi alterada
				imageSwitcher.setImageResource(imagens[posicao]);
			}
			public void onNothingSelected(AdapterView parent) {
			}
		});
	}
	// Implementa "ViewSwitcher.ViewFactory"
	public View makeView() {
		ImageView img = new ImageView(this);
		img.setBackgroundColor(0xFF000000);
		img.setScaleType(ImageView.ScaleType.FIT_CENTER);
		img.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		return img;
	}
}