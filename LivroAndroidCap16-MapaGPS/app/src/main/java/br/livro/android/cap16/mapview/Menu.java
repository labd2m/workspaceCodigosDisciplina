package br.livro.android.cap16.mapview;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Exemplos de MapView (Google Maps)
 * 
 * @author ricardo
 * 
 */
public class Menu extends ListActivity {

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		String[] mStrings = new String[] {
				"Primeiro Exemplo",
				"Primeiro Exemplo em XML",
				"Centraliza Mapa",
				"Mapa Cristo", 
				"Bola Overlay", 
				"Imagem Overlay",
				"Mapa Overlay",
				"Tela Form Coordenadas",
				"Tela Form Endereço Geocoder",
				"Tela Form Endereço MyLocation",
				"Tela Form Endereço MyLocation Rota *",
				"Mapa ItemizedOverlay",
				"Satélite(S) / Rua(R)",
				"Zoom Teclas",
				"Zoom - Control",
				"Sair"};

		this.setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, mStrings));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		switch (position) {
			case 0:
				startActivity(new Intent(this, MapaSimples.class));
				break;
			case 1:
				startActivity(new Intent(this, MapaSimplesXML.class));
				break;
			case 2:
				startActivity(new Intent(this, ExemploCentralizaPonto.class));
				break;
			case 3:
				startActivity(new Intent(this, ExemploMapaCristo.class));
				break;
			case 4:
				startActivity(new Intent(this, ExemploBolaOverlay.class));
				break;
			case 5:
				startActivity(new Intent(this, ExemploImagemOverlay.class));
				break;
			case 6:
				startActivity(new Intent(this, ExemploOverlay.class));
				break;
			case 7:
				startActivity(new Intent(this, TelaFormCoordenadas.class));
				break;
			case 8:
				startActivity(new Intent(this, TelaFormEndereco.class));
				break;
			case 11:
				startActivity(new Intent(this, ExemploItemOverlay.class));
				break;
			case 12:
				startActivity(new Intent(this, ExemploSatelite.class));
				break;
			case 13:
				startActivity(new Intent(this, ExemploZoom.class));
				break;
			case 14:
				startActivity(new Intent(this, ExemploZoomControl.class));
				break;
			default:
				finish();
		}

	}
}