package br.livro.android.cap7.canvas.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.SurfaceHolder.Callback;
import br.livro.android.cap7.canvas.R;

/**
 * Demonstra um XML que define uma View customizada para desenhar um Quadrado
 * 
 * "QuadradosView"
 * 
 * @author ricardo
 * 
 */
public class ExemploSurfaceView extends Activity implements Callback {

	private static final String CATEGORIA = "livro";
	private int x;
	private int y;
	private int largura;
	private int altura;

	@Override
	public void onCreate(Bundle b) {
		super.onCreate(b);
		SurfaceView surface = new TesteSurfaceView(this);
		SurfaceHolder holder = surface.getHolder();
		holder.addCallback(this);
		setContentView(surface);
	}

	private class TesteSurfaceView extends SurfaceView {
		private static final int VELOCIDADE = 2;
		private Bitmap bitmap;
		private Paint paint;

		public TesteSurfaceView(Context context) {
			super(context);
			setFocusable(true);

			bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.aviao);

			paint = new Paint();
		}

		@Override
		public boolean onKeyDown(int codigoTecla, KeyEvent evento) {
			boolean redesenhar = true;
			switch (codigoTecla) {
				case KeyEvent.KEYCODE_DPAD_UP:
					y -= VELOCIDADE;
					break;
				case KeyEvent.KEYCODE_DPAD_DOWN:
					y += VELOCIDADE;
					break;
				case KeyEvent.KEYCODE_DPAD_LEFT:
					x -= VELOCIDADE;
					break;
				case KeyEvent.KEYCODE_DPAD_RIGHT:
					x += VELOCIDADE;
					break;
				default:
					redesenhar = false;
			}
//			if (redesenhar) {
//				invalidate();
//				return true;
//			}
			
			desenhar();
			
			return super.onKeyDown(codigoTecla, evento);
		}

		private void desenhar() {
			SurfaceHolder holder = getHolder();
			Canvas canvas = holder.lockCanvas(null);
			try{
				desenhar(canvas);
			}finally{
				if (canvas != null) {
					holder.unlockCanvasAndPost(canvas);
				}
			}
		}

		private void desenhar(Canvas canvas) {

			Log.i(CATEGORIA, "onDraw: x/y: "+x+"/y"+y);

			//Fundo branco
			paint.setColor(0xffffff);
			canvas.drawRect(0,0,largura,altura, paint);

			canvas.drawBitmap(bitmap, x,y, paint);
		}
	}

	
	public void surfaceChanged(SurfaceHolder holder, int format, int largura, int altura) {
		Log.d(CATEGORIA, "surfaceChanged: " + largura + "/" + altura);

		this.largura = largura;
		this.altura = altura;
		
		x = largura / 2;
		y = altura / 2;
	}

	
	public void surfaceCreated(SurfaceHolder holder) {
		Log.d(CATEGORIA, "surfaceCreated: " + holder);
	}

	
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.d(CATEGORIA, "surfaceDestroyed: " + holder);
	}
}