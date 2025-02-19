package br.livro.android.cap16.mapview;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import br.livro.android.cap16.R;

import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

/**
 * Simples MapView para alternar entre o modo 'Street' e 'Satellite'
 * 
 * @author ricardo
 * 
 */
public class ExemploSatelite extends MapaSimplesXML {
	private MapView mapa;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		mapa = (MapView) findViewById(R.id.mapa);
		MapController controlador = mapa.getController();
		controlador.setZoom(4);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_S) {
			// Satelite
			mapa.setSatellite(true);
			mapa.setStreetView(false);
			mapa.setTraffic(false);
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_R) {
			// Rua
			mapa.setStreetView(true);
			mapa.setSatellite(false);
			mapa.setTraffic(false);
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_T) {
			// Traffic
			mapa.setTraffic(true);
			mapa.setStreetView(false);
			mapa.setSatellite(false);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, 0, 0, "Satelite");
		menu.add(0, 1, 1, "Rua");
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// Clicou no menu
		switch (item.getItemId()) {
		case 0:
			// Satelite
			mapa.setSatellite(true);
			mapa.setStreetView(false);
			mapa.setTraffic(false);
			break;
		case 1:
			// Rua
			mapa.setStreetView(true);
			mapa.setSatellite(false);
			mapa.setTraffic(false);
			break;
		}
		return true;
	}
}