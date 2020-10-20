package br.livro.android.cap16.mapview;

import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import br.livro.android.cap16.Endereco;
import br.livro.android.cap16.R;
import br.livro.android.cap16.overlay.BolaOverlay;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

/**
 * Permite digitar um endereço.
 * 
 * Com o endereço faz a busca no Google, e o usuário escolhe o local desejado
 * 
 * Utilizando o endereço, o convertemos para coordenadas e a rota é desenhada
 * entre o MyLocation e o endereço digitado
 * 
 * @author ricardo
 *
 */
public class TelaFormEndereco extends MapActivity implements OnClickListener {
	private static final String CATEGORIA = "livro";
	private MapController controlador;

	private MapView mapa;
	private Button btBuscarEndereco;
	private BolaOverlay bola;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.form_endereco);

		mapa = (MapView) findViewById(R.id.mapa);

		btBuscarEndereco = (Button) findViewById(R.id.btBuscarEndereco);
		btBuscarEndereco.setOnClickListener(this);

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

	/**
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	public void onClick(View view) {
		EditText textEndereco = (EditText) findViewById(R.id.endereco);

		String endereco = textEndereco.getText().toString();

		final List<Endereco> enderecos = Endereco.buscar(this, endereco);
		final String[] s = new String[enderecos.size()];
		int i = 0;
		for (Endereco e : enderecos) {
			s[i++] = e.getDesc();
			Log.i(CATEGORIA, e.toString());
		}

		final Context contexto = this;
		AlertDialog create = new AlertDialog.Builder(contexto)
        .setTitle("Endreço")
        .setItems(s, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
				Endereco endereco = enderecos.get(id);
				Log.i(CATEGORIA,"Endereço lat/long: " + endereco.getLatitude() + "," + endereco.getLongitude());
				Coordenada c = new Coordenada(endereco);
				controlador.animateTo(c);
				bola.setGeoPoint(c);
				mapa.invalidate();
            }
        })
        .create();
		create.show();
	}
	
	@Override
	protected boolean isRouteDisplayed() {
		return false;
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
