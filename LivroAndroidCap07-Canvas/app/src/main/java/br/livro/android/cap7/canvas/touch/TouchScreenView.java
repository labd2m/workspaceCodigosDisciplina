package br.livro.android.cap7.canvas.touch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import br.livro.android.cap7.canvas.R;

/**
 * View que trata o TouchScreen
 * 
 * @author ricardo
 * 
 */
public class TouchScreenView extends View {
	private static final String CATEGORIA = "livro";
	private Drawable img;
	int x, y;
	private boolean selecionou;
	private int larguraTela;
	private int alturaTela;
	private int larguraImg;
	private int alturaImg;

	public TouchScreenView(Context context) {
		super(context, null);

		// Recupera a Imagem
		img = context.getResources().getDrawable(R.drawable.android);

		// Recupera a largura e altura da imagem
		larguraImg = img.getIntrinsicWidth();
		alturaImg = img.getIntrinsicHeight();

		// Configura a View para receber foco e tratar eventos de teclado
		setFocusable(true);
	}

	@Override
	// Chamado quando a tela é redimensioada, ou iniciada...
	protected void onSizeChanged(int width, int height, int oldw, int oldh) {
		super.onSizeChanged(width, height, oldw, oldh);

		this.larguraTela = width;
		this.alturaTela = height;

		x = width / 2 - (larguraImg / 2);
		y = height / 2 - (alturaImg / 2);

		Log.i(CATEGORIA, "onSizeChanged x/y: " + x + "/" + y);
	}

	@Override
	// Desenha a tela
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		// Fundo branco
		Paint pincel = new Paint();
		pincel.setColor(Color.WHITE);
		canvas.drawRect(0, 0, larguraTela, alturaTela, pincel);

		// Define os limites/áreaa para desenhar
		img.setBounds(x, y, x + larguraImg, y + alturaImg);

		// Desenha a imagem
		img.draw(canvas);
	}

	@Override
	// Move a imagem
	public boolean onTouchEvent(MotionEvent event) {

		float x = event.getX();
		float y = event.getY();

		Log.i(CATEGORIA, "onTouchEvent: x/y > " + x + "/" + y);

		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				// Inicia o movimento se pressionou a imagem
				//Olha se a imagem foi selecionada e retorna true ou false
				selecionou = img.copyBounds().contains((int) x, (int) y);
				Log.i(CATEGORIA, "CLIQUEI");
				break;
			case MotionEvent.ACTION_MOVE:
				// Arrasta o boneco
				if (selecionou) {
					this.x = (int) x - (larguraImg / 2);
					this.y = (int) y - (alturaImg / 2);
				}
				Log.i(CATEGORIA, "MOVI");
				break;
			case MotionEvent.ACTION_UP:
				// Finaliza o movimento
				selecionou = false;
				Log.i(CATEGORIA, "SOLTEI");
				break;
		}
		invalidate();
		return true;
	}
}
