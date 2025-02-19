package br.livro.android.cap16;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * Exemplos de MapView (Google Maps) e GPS
 * 
 * @author rlecheta
 * 
 */
public class Menu extends ListActivity {

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		String[] mStrings = new String[] { 
				"MapView",
				"GoogleMaps Intents",
				"GPS",
				"Rotas",
				"Sair"
		};

		this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, mStrings));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		switch (position) {
			case 0:
				startActivity(new Intent(this,br.livro.android.cap16.mapview.Menu.class));
				break;
			case 1:
				startActivity(new Intent(this,br.livro.android.cap16.mapview.intent.Menu.class));
				break;
			case 2:
				startActivity(new Intent(this,br.livro.android.cap16.gps.Menu.class));
				break;
			case 3:
				startActivity(new Intent(this,br.livro.android.cap16.rota.Menu.class));
				break;
			default:
				finish();
		}
	}
}