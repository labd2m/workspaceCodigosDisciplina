package lucas.vegi.demogooglemapsv2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity {
	private final LatLng VICOSA = new LatLng(-20.752946, -42.879097);
	private final LatLng MURIAE = new LatLng(-21.12881, -42.374247);
	private final LatLng FAMINAS = new LatLng(-21.109725, -42.381738);
	
	private GoogleMap map;

	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		map  = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

		//seta 3 marcadores
		//map.addMarker(new MarkerOptions().position(VICOSA).title("Meu apt Viçosa")
				//.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
				//.snippet("Lugar onde eu moro em Viçosa!"));
				//.icon(BitmapDescriptorFactory.fromResource(R.drawable.bus));
		
		//acrescentar atributo icon com imagem do projeto
		map.addMarker(new MarkerOptions().position(VICOSA).title("Meu apt Viçosa"));
		map.addMarker(new MarkerOptions().position(MURIAE).title("Minha casa Muriae"));
		map.addMarker(new MarkerOptions().position(FAMINAS).title("Faculdade de Minas"));

		/*Intent it = getIntent();
		String local = it.getStringExtra("local");

		if(local.equals("Muriae")){
			CameraUpdate update = CameraUpdateFactory.newLatLngZoom(MURIAE, 16);
			map.animateCamera(update);
		}else if(local.equals("Viçosa")) {
			CameraUpdate update = CameraUpdateFactory.newLatLngZoom(VICOSA, 16);
			map.animateCamera(update);

		}else if(local.equals("Faminas")) {
			CameraUpdate update = CameraUpdateFactory.newLatLngZoom(FAMINAS, 21);
			map.animateCamera(update);
		}*/

	}
	
	public void onClick_Muriae(View v) {
		map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
		CameraUpdate update = CameraUpdateFactory.newLatLngZoom(MURIAE, 16);
		map.animateCamera(update);
	}
	
	public void onClick_Vicosa(View v) {
		map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		CameraUpdate update = CameraUpdateFactory.newLatLngZoom(VICOSA, 14);
		map.animateCamera(update);
		
	}
	
	public void onClick_Faminas(View v) {
		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		CameraUpdate update = CameraUpdateFactory.newLatLngZoom(FAMINAS, 12);
		map.animateCamera(update);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

