package br.livro.android.cap7.canvas;

import android.app.Activity;
import android.os.Bundle;
import br.livro.android.cap7.canvas.R;
/**
 * Demonstra um XML que define um Canvas customizado
 * 
 * br.livro.android.canvas.view.CanvasSimplesView
 * 
 * @author ricardo
 *
 */
public class CanvasSimples extends Activity {

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.canvas_simples);
    }
}