package br.livro.android.cap7.canvas.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * Exibe o tamanho da tela
 * 
 * Demonstra como desenhar textos em determinada coordenada.
 * 
 * @author ricardo
 *
 */
public class ExemploTamanhoTela extends Activity {
	private static final String CATEGORIA = "livro";

	@Override
	public void onCreate(Bundle b) {
		super.onCreate(b);

		setContentView(new CanvasTamanho(this));
	}

	/**
	 * Exibe o tamanho da tela
	 * 
	 * @author ricardo
	 *
	 */
	public class CanvasTamanho extends View {

		private Paint paint;

		public CanvasTamanho(Context context) {
			super(context);
			
			paint = new  Paint();
			paint.setColor(Color.BLUE);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			int w = canvas.getWidth();
			int h = canvas.getHeight();

			// Desenha o fundo quadrado branco
			paint.setColor(Color.WHITE);
			canvas.drawRect(0, 0, w, h, paint);

			// android:theme="@android:style/Theme.Light.NoTitleBar.Fullscreen"
			Log.i(CATEGORIA,w+"x"+h); //  320x480

			paint.setColor(Color.BLUE);
			canvas.drawText("0,0",2,10, paint);

			canvas.drawText("w,y",w-20,h-50, paint);
		}
	}
}