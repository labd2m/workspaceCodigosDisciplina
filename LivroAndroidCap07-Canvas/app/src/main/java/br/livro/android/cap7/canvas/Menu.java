package br.livro.android.cap7.canvas;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.livro.android.cap7.canvas.touch.ExemploTouchScreen;
import br.livro.android.cap7.canvas.view.ExemploCanvasClipView;
import br.livro.android.cap7.canvas.view.ExemploCanvasTranslateView;
import br.livro.android.cap7.canvas.view.ExemploQuadrado;
import br.livro.android.cap7.canvas.view.ExemploRiverRaid;
import br.livro.android.cap7.canvas.view.ExemploSurfaceView;
import br.livro.android.cap7.canvas.view.ExemploTamanhoTela;
import br.livro.android.cap7.canvas.view.ExemploTextoNumerico;
import br.livro.android.cap7.canvas.view.TeclasActivity;

/**
 * Exemplos Views customizadas
 * 
 * @author rlecheta
 * 
 */
public class Menu extends ListActivity {
	private static final String[] ops = new String[] {
			"Canvas Tamanho Tela",
			"View Customizada",
			"Desenhar Quadrados",
			"Mover nave pela tela",
			"Pressionar teclas",
			"Canvas Clip",
			"Canvas Translate",
			"SurfaceView",
			"Touch Screen",
			"Sair"
			
	};

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, ops));
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		switch (position) {
			case 0:
					startActivity(new Intent(this,ExemploTamanhoTela.class));
			break;
			case 1:
				startActivity(new Intent(this,ExemploTextoNumerico.class));
				break;
			case 2:
				startActivity(new Intent(this,ExemploQuadrado.class));
				break;
			case 3:
				startActivity(new Intent(this,ExemploRiverRaid.class));
				break;
			case 4:
				startActivity(new Intent(this,TeclasActivity.class));
				break;
			case 5:
				startActivity(new Intent(this,ExemploCanvasClipView.class));
				break;
			case 6:
				startActivity(new Intent(this,ExemploCanvasTranslateView.class));
				break;
			case 7:
				startActivity(new Intent(this,ExemploSurfaceView.class));
				break;
			case 8:
				startActivity(new Intent(this,ExemploTouchScreen.class));
				break;
			default:
				finish();
		}
	}
}