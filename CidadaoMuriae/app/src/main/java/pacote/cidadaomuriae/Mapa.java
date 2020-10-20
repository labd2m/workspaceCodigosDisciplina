package pacote.cidadaomuriae;

import org.json.JSONArray;
import org.json.JSONObject;

import pacote.ferramentas.UserFunctions;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

public class Mapa extends FragmentActivity {
	
	public int TIMEOUT_CONNECTION;
	public int TIMEOUT_SOCKET;
	
	LatLng pos ; 

	String titulo;
	
	String descricao;

	CameraUpdate atual;
	
	private GoogleMap mapa;
	
	JSONArray pontos = null;
	
	private ProgressDialog pDialog;
	
	public Handler handler = new Handler();
	
	private static String KEY_SUCCESS = "success";
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try{
			
			setContentView(R.layout.layout_mapa);
			
			Intent it = getIntent();
			
			double latitude = it.getDoubleExtra("latitude", 0.0);
			double longitude = it.getDoubleExtra("longitude", 0.0);
			
			pos = new LatLng(latitude, longitude);
			
			//mapa = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
			
			mapa = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
			
			atual = CameraUpdateFactory.newLatLngZoom(pos,16);
			moveCamera();
			mapa.addMarker(new MarkerOptions().position(pos).title("Minha localização atual").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
			
			new FuncaoMapa().execute();
			
		}catch(Exception e){
			e.printStackTrace();
			Toast.makeText(Mapa.this, "Serviço indisponível no momento...", Toast.LENGTH_LONG).show();
			finish();
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		/*Obtem confirmação se wifi está conectado ou conectando*/
	    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    if( cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected() ) {
	        Log.i("Conexão", "USANDO CONEXÃO WI-FI"); 
	        TIMEOUT_CONNECTION = 20000;
	        TIMEOUT_SOCKET = 20000;
	    }else{
	        Log.i("Conexão", "USANDO CONEXÃO DE DADOS"); 
	        TIMEOUT_CONNECTION = 70000;
	        TIMEOUT_SOCKET = 70000;
	    }
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		//Google Analytics
		EasyTracker.getInstance(this).activityStart(this);
	}
	
	@Override
	public void onStop() {
	    super.onStop();
	   
	    //Google Analytics
	    EasyTracker.getInstance(this).activityStop(this);  // Add this method.
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.mapa, menu);
		
		return true;
	}
	
	public boolean onMenuItemSelected(int featureId, MenuItem item) { 
		if (item.getOrder() == 100) {
			mapaNormal();
			return true;
		}else if (item.getOrder() == 200){
			mapaSatelite();
			return true;
		}
		else if (item.getOrder() == 300){
			moveCamera();
			return true;
		}
		else if (item.getOrder() == 400){
			Intent it = new Intent(this,Sobre.class);
			startActivity(it);
			return true;
		}
		return false;
	}
	
	public void moveCamera(){
		mapa.animateCamera(atual);
	}
	
	public void mapaSatelite(){
		mapa.setMapType(GoogleMap.MAP_TYPE_HYBRID);
	}

	public void mapaNormal(){
		mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.gc();
	}
	
	class FuncaoMapa extends AsyncTask<String, String, String> {

		/////// Before starting background thread Show Progress Dialog ///////
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Mapa.this);
			pDialog.setMessage("Carregando...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		protected String doInBackground(String... args) {

			UserFunctions userFunction = new UserFunctions();

			JSONObject json = userFunction.Pontos(TIMEOUT_CONNECTION, TIMEOUT_SOCKET);
			
			try {
				String res = json.getString(KEY_SUCCESS); 
				if(Integer.parseInt(res) == 1){

					pontos = json.getJSONArray("pontos");

					for (int i = 0; i < pontos.length(); i++) {
						JSONObject z = pontos.getJSONObject(i);
						String a = z.getString("desTituloAssunto");
						String b = z.getString("desColaboracao");
						String c = z.getString("numLatitude");
						String d = z.getString("numLongitude");
						 
						Log.d("ID", i+"");
						Log.d("TITULO", a);
						Log.d("DESCRIÇÃO", b);		
						Log.d("COORDENADA X", c);		
						Log.d("COORDENADA Y", d);
						 
						double m = Double.parseDouble(c);
						double n = Double.parseDouble(d);
						pos = new LatLng(m, n);
						titulo = a;
						descricao = b; 
						
						handler.post(new Runnable() {
							public void run() {
								mapa.addMarker (new MarkerOptions().position(pos).title(titulo).snippet(descricao));
							}
						});	 
						System.gc();
					}
					//////////////////////////
					 /*
					String frase = json.getString("frase");
					String partes[] = frase.split("£");
					
					int tamanho = partes.length;

					for(int i = 0; i < tamanho; i++){
						
						pedaco = partes[i].split("¤");
						Log.d("ID", i+"");
						Log.d("TITULO", pedaco[0]);
						Log.d("DESCRI‚ÌO", pedaco[1]);		
						Log.d("COORDENADA X", pedaco[2]);		
						Log.d("COORDENADA Y", pedaco[3]);
						double m = Double.parseDouble(pedaco[2]);
						double n = Double.parseDouble(pedaco[3]);

						pos = new LatLng(m, n);
						titulo = pedaco[0];
						
						handler.post(new Runnable() {
							public void run() {
								mapa.addMarker (new MarkerOptions().position(pos).title(titulo).snippet(pedaco[1]));
							}
						});
					}
					*/		
					 ////////////////////////
				}
			}catch (Exception e) {
				e.printStackTrace();
				handler.post(new Runnable() {
					public void run() {
						Toast.makeText(Mapa.this, "Serviço indisponível no momento...", Toast.LENGTH_LONG).show();
					}
				});
			}
			
			return null;
		}

		protected void onPostExecute(String file_url) {
			pDialog.dismiss();
		}
	
	}
}