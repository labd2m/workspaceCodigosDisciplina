package br.livro.android.cap6.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ViewFlipper;
import br.livro.android.cap6.R;

/**
 * ViewFlipper pode animar as Views que foram adicionadas a ele
 * 
 * Exemplo que demonstra como carregar a View próxima/anterior, e animar Views.
 * 
 * @author ricardo
 * 
 */
public class ExemploViewFlipper extends Activity {
	//private boolean animando = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.exemplo_view_flipper);

		//final ViewFlipper flip = (ViewFlipper) findViewById(R.id.flip);

		//Utiliza algumas animações padrão do Android
		//Animation in = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
		//in.setDuration(3000);

		//Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
		//out.setDuration(2000);

		//flip.setInAnimation(in);
		//flip.setOutAnimation(out);

		//Carrega a próxima View
		//Button bProximo = (Button) findViewById(R.id.btProximo);
		/*bProximo.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				flip.showNext();
			}});*/

		//Inicia ou para a animação
		//Button bIniciar = (Button) findViewById(R.id.btIniciar);
		/*bIniciar.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				animando = !animando;
				if(animando){
					flip.startFlipping();
				}else{
					flip.stopFlipping();
				}
			}});*/

		//Carrega a view anterior
		//Button bAnterior = (Button) findViewById(R.id.btAnterior);
		// bAnterior.setOnClickListener(new OnClickListener(){
			//@Override
			//public void onClick(View v) {
			//	flip.showPrevious();
			//}});
	}
}