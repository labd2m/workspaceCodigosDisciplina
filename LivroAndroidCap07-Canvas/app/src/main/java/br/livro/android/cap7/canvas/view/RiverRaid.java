package br.livro.android.cap7.canvas.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import br.livro.android.cap7.canvas.R;

/**
 * <br.livro.android.cap7.canvas.view.RiverRaid android:id="@+id/canvas"
 * android:layout_width="fill_parent" android:layout_height="fill_parent" />
 * 
 * @author ricardo
 * 
 */
public class RiverRaid extends View {
	private static final String CATEGORIA = "livro";
	private Drawable imgAviao;
	int x, y;
	int pulo = 10;
	int largura;
	int altura;

	public RiverRaid(Context context, AttributeSet attrs) {
		super(context, attrs);

		// Imagem de Fundo
		setBackgroundResource(R.drawable.fundo_river);

		// Recupera a Imagem
		imgAviao = context.getResources().getDrawable(R.drawable.aviao);

		// Recupera a largura e altura da imagem
		largura = imgAviao.getIntrinsicWidth();
		altura = imgAviao.getIntrinsicHeight();

		x = 145;
		y = 290;

		// Configura a View para receber foco e tratar eventos de teclado
		setFocusable(true);
	}

	@Override
	// Chamado quando a tela é redimensioada, ou iniciada...
	protected void onSizeChanged(int width, int height, int oldw, int oldh) {
		super.onSizeChanged(width, height, oldw, oldh);

		Log.i(CATEGORIA, "X: " + x);
		Log.i(CATEGORIA, "Y: " + y);
		
		x = width / 2 - (largura / 2);
		y = height / 2 - (altura / 2);
	}

	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		Log.i(CATEGORIA, "X: " + x);
		Log.i(CATEGORIA, "Y: " + y);
		
		// Define os limites/áreaa para desenhar
		imgAviao.setBounds(x, y, x + largura, y + altura);

		// Desenha a imagem
		imgAviao.draw(canvas);
	}

	@Override
	public boolean onKeyDown(int codigoTecla, KeyEvent evento) {
		boolean redesenhar = true;
		switch (codigoTecla) {
		case KeyEvent.KEYCODE_DPAD_UP:
			y -= pulo;
			break;
		case KeyEvent.KEYCODE_DPAD_DOWN:
			y += pulo;
			break;
		case KeyEvent.KEYCODE_DPAD_LEFT:
			x -= pulo;
			break;
		case KeyEvent.KEYCODE_DPAD_RIGHT:
			x += pulo;
			break;
		default:
			redesenhar = false;
		}
		if (redesenhar) {
			invalidate();
			return true;
		}
		return super.onKeyDown(codigoTecla, evento);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
	}
}
