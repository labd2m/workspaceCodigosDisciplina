package br.livro.android.cap16.mapview;

import android.os.Bundle;
import android.view.KeyEvent;
import br.livro.android.cap16.R;

import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

/**
 * Simples Exemplo de como aumentar e diminuir o zoom baseado nos eventos do
 * teclado
 * 
 * @author ricardo
 * 
 */
public class ExemploZoom extends ExemploSatelite {
	private MapController controlador;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		MapView mapView = (MapView) findViewById(R.id.mapa);

		controlador = mapView.getController();

		controlador.setZoom(14);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_Z) {
			controlador.zoomIn();
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_X) {
			controlador.zoomOut();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
