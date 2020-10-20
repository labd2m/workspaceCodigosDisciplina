package br.livro.android.cap16.rota;

import android.os.Bundle;
import br.livro.android.cap16.R;

import com.google.android.maps.MapActivity;

/**
 * Exemplo que busca no Gooogle uma determinada rota e desenha no mapa
 * 
 * @author ricardo
 *
 */
public class ExemploRotaBuscaGoogle extends MapActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.mapview);
	}

	@Override
	protected boolean isRouteDisplayed() {
		return true;
	}
}