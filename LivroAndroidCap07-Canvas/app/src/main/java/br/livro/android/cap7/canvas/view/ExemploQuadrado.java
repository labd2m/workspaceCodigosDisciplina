package br.livro.android.cap7.canvas.view;

import android.app.Activity;
import android.os.Bundle;
import br.livro.android.cap7.canvas.R;

/**
 * Demonstra um XML que define uma View customizada para desenhar um Quadrado
 * 
 * "QuadradosView"
 * 
 * @author ricardo
 *
 */
public class ExemploQuadrado extends Activity {
    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.quadrados);
    }
}