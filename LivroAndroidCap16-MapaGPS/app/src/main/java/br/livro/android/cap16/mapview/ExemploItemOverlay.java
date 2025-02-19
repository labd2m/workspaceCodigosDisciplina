package br.livro.android.cap16.mapview;

import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import br.livro.android.cap16.CoordenadaSete;
import br.livro.android.cap16.R;
import br.livro.android.cap16.overlay.ImagensOverlay;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

/**
 * Simples demo de como inseirir um Overlay no mapa
 * 
 * @author ricardo
 *
 */
public class ExemploItemOverlay extends MapaSimplesXML {
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		MapView mapa = (MapView) findViewById(R.id.mapa);
		mapa.setClickable(true);
		mapa.getController().setZoom(18);
		mapa.getController().animateTo(new CoordenadaSete());

		// Define a imagem de cada overlay
		Drawable imagem = getResources().getDrawable(R.drawable.ponto);

		List<OverlayItem> pontos = new ArrayList<OverlayItem>();
		pontos.add(new OverlayItem(new GeoPoint(-25443195, -49280977),"Ponto 1", "Que legal"));
		pontos.add(new OverlayItem(new GeoPoint(-25442770, -49279830),"Ponto 2", "Termina aqui"));

		// Adiciona as imagens no mapa
		ImagensOverlay pontosOverlay = new ImagensOverlay(this,pontos,imagem);
		mapa.getOverlays().add(pontosOverlay);
	}
}
