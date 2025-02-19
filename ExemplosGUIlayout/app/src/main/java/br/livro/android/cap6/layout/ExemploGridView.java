package br.livro.android.cap6.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import br.livro.android.cap6.R;

/**
 * Exemplo do GridView para visualizar imagens
 * 
 * @author rlecheta
 * 
 */
public class ExemploGridView extends Activity {
	// array com os ids das imagens
	private int[] imagens = { R.drawable.smile1, R.drawable.smile2,
			R.drawable.smile1, R.drawable.smile2, R.drawable.smile1,
			R.drawable.smile2, R.drawable.smile1, R.drawable.smile2 };

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.exemplo_grid);

		GridView grid = (GridView) findViewById(R.id.grid1);
		grid.setAdapter(new AdaptadorImagem(this, imagens,new GridView.LayoutParams(30, 30)));

		grid.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView parent, View v, int posicao,long id) {
				Toast.makeText(ExemploGridView.this,"Imagem selecionada: " + posicao, Toast.LENGTH_SHORT).show();
			}
		});
	}
}