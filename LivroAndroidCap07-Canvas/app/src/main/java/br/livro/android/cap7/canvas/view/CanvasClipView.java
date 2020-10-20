package br.livro.android.cap7.canvas.view;

import android.R.color;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Demonstra como utilizar o clip no Canvas do Android
 * 
 * Primeiro é desenhado um quadrado "azul" no centro.
 * 
 * Então é feito um clip dentro do quadrado.
 * 
 * E depois é desenhado outro quadrado no mesmo local do primeiro.
 * 
 * Mas como foi feito clip, somente uma parte do segundo quadrado "verde" é desenhado
 * 
 * @author ricardo
 * 
 */
public class CanvasClipView extends View {

	//Objetos utilizado para pintar, contém a cor RGB
	private Paint paintVerde;
	private Paint paintAzul;
	
	public CanvasClipView(Context context) {
        this(context,null);
	}

	public CanvasClipView(Context context, AttributeSet attrs) {
        super(context, attrs);
        
        // Imagem de Fundo
		//setBackground(R.drawable.fundo);
		setBackgroundColor(color.darker_gray);

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
        int x = 10;
        int y = 10;
		int size = 50;

		canvas.drawRect(x, y, x + size, y + size, paintAzul);

		//salva o clip como era
		canvas.save();

		//faz clip com +10 dentro do quadrado, para só pintar um pedaço
        canvas.clipRect(x+10, y+10, x + size, y + size);

        canvas.drawRect(x, x, x + size, x + size, paintVerde);

        //restaura o clip para o tamanho original
        canvas.restore();

		invalidate();
	}
}
