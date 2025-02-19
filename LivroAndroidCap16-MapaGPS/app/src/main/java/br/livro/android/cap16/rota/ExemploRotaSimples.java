package br.livro.android.cap16.rota;

import android.os.Bundle;
import br.livro.android.cap16.R;

import com.google.android.maps.MapActivity;

/**
 * Exemplo que desenha a rota entre dois GeoPoint
 * 
 * @author ricardo
 *
 */
public class ExemploRotaSimples extends MapActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.mapview);
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}