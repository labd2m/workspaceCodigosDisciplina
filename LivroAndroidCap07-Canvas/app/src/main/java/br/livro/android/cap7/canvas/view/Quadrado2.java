package br.livro.android.cap7.canvas.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;

/**
 * Desenhar quadrados, linhas e círculos na tela
 * 
 * @author ricardo
 * 
 */
public class Quadrado2 extends View {
	// para definir a cor RGB
	private Paint pincelVermelho;
	private Paint pincelPreto;
	private Paint pincelAzul;
	private float y = 10;

	public Quadrado2(Context context) {
		this(context, null);
	}

	public Quadrado2(Context context, AttributeSet attrs) {
		super(context, attrs);
		setBackgroundColor(Color.LTGRAY);

		// Vermelho
		pincelVermelho = new Paint();
		pincelVermelho.setARGB(255, 255, 0, 0);

		// Preto
		pincelPreto = new Paint();
		pincelPreto.setARGB(255, 0, 0, 0);

		// Azul
		pincelAzul = new Paint();
		pincelAzul.setARGB(255, 0, 0, 255);

		// Configura a View para receber foco e tratar eventos de teclado
		setFocusable(true);
	}

	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		// Desenha um quadrado
		canvas.drawRect(10, y, 10 + 50, y + 50, pincelAzul);

		// desenha uma linha
		canvas.drawLine(50, 50, 100, 100, pincelPreto);

		// desenha um circulo
		canvas.drawCircle(100, 100, 20, pincelVermelho);
	}

	@Override
	public boolean onKeyDown(int codigo, KeyEvent evento) {
		if (codigo == KeyEvent.KEYCODE_DPAD_UP) {
			y -= 10;
			invalidate();
			return true;
		} else if (codigo == KeyEvent.KEYCODE_DPAD_DOWN) {
			y += 10;
			invalidate();
			return true;
		}
		return super.onKeyDown(codigo, evento);
	}
}
