package br.livro.android.cap16.mapview.intent;

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
				"Intent Exibir Direções",
				"Intent Coordenada no Google Maps",
				"Intent McDonald´s no Google Maps",
				"Sair"};

		this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, mStrings));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		switch (position) {
			case 0:
				startActivity(new Intent(this, ExemploDirecoes.class));
				break;
			case 1:
				startActivity(new Intent(this, ExemploDirecoesGoogleMaps.class));
				break;
			case 2:
				startActivity(new Intent(this, ExemploMcDonaldsGoogleMaps.class));
				break;
			default:
				finish();
				break;
		}

		
	}
}