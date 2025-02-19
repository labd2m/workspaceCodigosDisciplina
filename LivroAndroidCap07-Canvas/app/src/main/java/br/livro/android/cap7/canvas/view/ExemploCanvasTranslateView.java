package br.livro.android.cap7.canvas.view;

import android.app.Activity;
import android.os.Bundle;

/**
 * Demonstra um XML que define uma View customizada para desenhar um Quadrado
 * 
 * "QuadradosView"
 * 
 * @author ricardo
 *
 */
public class ExemploCanvasTranslateView extends Activity {
    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(new CanvasTranslateView(this));
    }
}