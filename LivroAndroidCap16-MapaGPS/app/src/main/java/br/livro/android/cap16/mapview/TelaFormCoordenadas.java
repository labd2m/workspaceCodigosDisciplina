package br.livro.android.cap16.mapview;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import br.livro.android.cap16.Coordenada;
import br.livro.android.cap16.CoordenadaSete;
import br.livro.android.cap16.R;
import br.livro.android.cap16.overlay.BolaOverlay;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

/**
 * Permite digitar as coordenadas no formulário e brincar
 * 
 * @author ricardo
 *
 */
public class TelaFormCoordenadas extends MapActivity implements OnClickListener {
	private static final String CATEGORIA = "livro";
	private MapController controlador;

	private MapView mapa;
	private Button btAtualizarMapa;
	private Button btMoverPonto;
	private BolaOverlay bola;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.form_coordenadas);

		mapa = (MapView) findViewById(R.id.mapa);

		btAtualizarMapa = (Button) findViewById(R.id.btAtualizarMapa);
		btAtualizarMapa.setOnClickListener(this);

		btMoverPonto = (Button) findViewById(R.id.btMoverPonto);
		btMoverPonto.setOnClickListener(this);

		mapa.setStreetView(true);
		mapa.setClickable(true);

		bola = new BolaOverlay(new CoordenadaSete(),Color.BLUE);
		mapa.getOverlays().add(bola);

		controlador = mapa.getController();
		controlador.setZoom(15);

		mapa.setBuiltInZoomControls(true);

		controlador.animateTo(new CoordenadaSete());

		mapa.invalidate();
	}
	public void onClick(View view) {
		EditText latitude = (EditText) findViewById(R.id.latitude);
		EditText longitude = (EditText) findViewById(R.id.longitude);

		double lat = Double.parseDouble(latitude.getText().toString());
		double lng = Double.parseDouble(longitude.getText().toString());

		GeoPoint p = new Coordenada(lat, lng);

		Log.i(CATEGORIA, "Coordenada > " + p);

		if(view == btAtualizarMapa){
			controlador.animateTo(p);
			mapa.invalidate();
		}else if(view == btMoverPonto){
			bola.setGeoPoint(p);
			mapa.invalidate();
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		MapView mapView = (MapView) findViewById(R.id.mapa);

		if (keyCode == KeyEvent.KEYCODE_S) {
			// Satelite
			mapView.setSatellite(true);
			mapView.setStreetView(false);
			mapView.setTraffic(false);
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_R) {
			// Ruas
			mapView.setStreetView(true);
			mapView.setSatellite(false);
			mapView.setTraffic(false);
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
		MapView mapView = (MapView) findViewById(R.id.mapa);
		// Clicou no menu
		switch (item.getItemId()) {
		case 0:
			// Satelite
			mapView.setSatellite(true);
			mapView.setStreetView(false);
			mapView.setTraffic(false);
			break;
		case 1:
			// Rua
			mapView.setStreetView(true);
			mapView.setSatellite(false);
			mapView.setTraffic(false);
			break;
		}
		return true;
	}
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}
