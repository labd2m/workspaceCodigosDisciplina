package br.livro.android.cap16.rota;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Menu dos exemplos de Rota
 * 
 * @author ricardo
 *
 */
public class Menu extends ListActivity {

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		String[] mStrings = new String[] { 
				"Rota Simples",
				"Rota com busca no Google",
				"Sair"
		};

		this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, mStrings));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		switch (position) {
			case 0:
				startActivity(new Intent(this,ExemploRotaSimples.class));
				break;
			case 1:
				startActivity(new Intent(this,ExemploRotaBuscaGoogle.class));
				break;
			default:
				finish();
		}
	}
}