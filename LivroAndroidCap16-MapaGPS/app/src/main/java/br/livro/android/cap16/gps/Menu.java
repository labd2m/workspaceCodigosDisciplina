package br.livro.android.cap16.gps;

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
				"Andando pela Rua Handler",
				"Apenas loga as coordenadas",
				"provider names",
				"Andando pela Rua GPS",
				"Andando pela Rua MyLocation",
				"Sair"};

		this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, mStrings));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		switch (position) {
			case 0:
				startActivity(new Intent(this,ExemploAndandoRuaFake.class));
				break;
			case 1:
				startActivity(new Intent(this,LogaCoordenadas.class));
				break;
			case 2:
				startActivity(new Intent(this,LocationProviderList.class));
				break;
			case 3:
				startActivity(new Intent(this,ExemploAndandoRua.class));
				break;
			case 4:
				startActivity(new Intent(this,ExemploAndandoRuaMyLocation.class));
				break;
			default:
				finish();
				break;
		}

		
	}
}