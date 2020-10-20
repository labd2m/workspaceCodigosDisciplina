package br.livro.android.cap7.canvas.view;

import android.R.color;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Desenhar quadrados, linhas e círculos na tela
 * 
 * @author ricardo
 * 
 */
public class CanvasSimplesView extends View {

	//Objetos utilizado para pintar, contém a cor RGB
	private Paint paintVermelho;
	private Paint paintVerde;
	private Paint paintAzul;
	
	public CanvasSimplesView(Context context) {
        this(context,null);
	}

	public CanvasSimplesView(Context context, AttributeSet attrs) {
        super(context, attrs);

		// Imagem de Fundo
		//setBackground(R.drawable.fundo);
		setBackgroundColor(color.darker_gray);

		// Vermelho
		paintVermelho = new Paint();
		paintVermelho.setAntiAlias(true);
		paintVermelho.setARGB(255, 255, 0, 0);//cores em RGB

		// Verde
		paintVerde = new Paint();
		paintVerde.setAntiAlias(true);
		paintVerde.setARGB(255, 0, 255, 0);//cores em RGB

		// Azul
		paintAzul = new Paint();
		paintAzul.setAntiAlias(true);
		paintAzul.setARGB(255, 0, 0, 255);//cores em RGB
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		//Desenha um quadrado
        canvas.drawRect(10, 10, 10 + 50, 10 + 50, paintAzul);

        //desenha uma linha
        canvas.drawLine(50, 50, 100, 100, paintVermelho);

        //desenha um circulo
        canvas.drawCircle(100, 100, 20,paintVerde);

		invalidate();
	}
}
