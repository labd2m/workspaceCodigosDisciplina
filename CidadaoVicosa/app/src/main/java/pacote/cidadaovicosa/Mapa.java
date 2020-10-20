package pacote.cidadaovicosa;

import java.util.Hashtable;

import org.json.JSONArray;
import org.json.JSONObject;

import pacote.ferramentas.UserFunctions;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;

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
	
	private Marker marker;
	private Hashtable<String, String[]> markersMultimidia;
	public String nomeFoto;
	public String nomeVideo;
	public Marker marcadorTemp;
	public String tipoVideo;
	
	public String notaMedia;
	public String dtOcorrencia;
	public String hrOcorrencia;
	public String categoria;
	public String subcategoria;
	public String dtHrCriacao;
	
	
	public String CAMINHO_FOTO = "http://200.235.131.170:8008/cidadaovicosa/ImagensEnviadas/";
	public String CAMINHO_VIDEO = "http://200.235.131.170:8008/cidadaovicosa/arquivos/";
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try{
			
			setContentView(R.layout.layout_mapa);
			
			markersMultimidia = new Hashtable<String, String[]>();
			
			Intent it = getIntent();
			
			double latitude = it.getDoubleExtra("latitude", 0.0);
			double longitude = it.getDoubleExtra("longitude", 0.0);
			
			pos = new LatLng(latitude, longitude);
			
			//mapa = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
			
			mapa = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
			
			mapa.setInfoWindowAdapter(new CustomInfoWindowAdapter()); //novidade
			
			//EVENTO DE CLIQUE DA INFO WINDOWS
			mapa.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
		        @Override
		        public void onInfoWindowClick(Marker marker) {
		        	
		        	String[] url = null;

					if (marker.getId() != null && markersMultimidia != null && markersMultimidia.size() > 0) {
						if ( markersMultimidia.get(marker.getId()) != null ) {
							url = markersMultimidia.get(marker.getId());
						}
					}
					
					if(url != null){
						Intent itMultimidia;
						String criacao[] = null;
						
						//separa data e hora de criação (não faz isso quando é no ponto de marcação atual)
						if(url.length > 2 ){
							criacao = url[4].split(" - "); 
						}
						
						if(!url[0].equals("") && !url[1].equals("") && ( tipoVideo.equals("mp4") || tipoVideo.equals("3gp") ) ){
							itMultimidia = new Intent(Mapa.this,VerDetalhesMarcador.class);
							itMultimidia.putExtra("foto", CAMINHO_FOTO + url[0]);
							itMultimidia.putExtra("video", CAMINHO_VIDEO + url[1]);	
							itMultimidia.putExtra("titulo", marker.getTitle());
							
							itMultimidia.putExtra("dtOcorrencia", url[2]);
							itMultimidia.putExtra("hrOcorrencia", url[3]);
							itMultimidia.putExtra("dtCriacao", criacao[0]);
							itMultimidia.putExtra("hrCriacao", criacao[1]);
							itMultimidia.putExtra("categoria", url[5]);
							itMultimidia.putExtra("subcategoria", url[6]);
							itMultimidia.putExtra("notaMedia", url[7]);
							
							startActivity(itMultimidia);
							
							//0 = foto
							//1 = video
							//2 = data de ocorrencia
							//3 = hora de ocorrencia
							//4 = data e hora de criação
							//5 = categoria
							//6 = subcategoria
							//7 = nota media
							
							//Toast.makeText(Mapa.this, "Cliquei na InfoWindow com Video e Foto", Toast.LENGTH_SHORT).show();
						}else if(!url[0].equals("")){
							//itMultimidia = new Intent(Mapa.this,VerImagem.class);
							itMultimidia = new Intent(Mapa.this,VerDetalhesMarcador.class);
							itMultimidia.putExtra("foto", CAMINHO_FOTO + url[0]);
							itMultimidia.putExtra("video", "");	
							itMultimidia.putExtra("titulo", marker.getTitle());
							
							itMultimidia.putExtra("dtOcorrencia", url[2]);
							itMultimidia.putExtra("hrOcorrencia", url[3]);
							itMultimidia.putExtra("dtCriacao", criacao[0]);
							itMultimidia.putExtra("hrCriacao", criacao[1]);
							itMultimidia.putExtra("categoria", url[5]);
							itMultimidia.putExtra("subcategoria", url[6]);
							itMultimidia.putExtra("notaMedia", url[7]);
							
							startActivity(itMultimidia);
							
							//Toast.makeText(Mapa.this, "Cliquei na InfoWindow com Foto", Toast.LENGTH_SHORT).show();
						}else if(!url[1].equals("") && ( tipoVideo.equals("mp4") || tipoVideo.equals("3gp") )){
							//itMultimidia = new Intent(Mapa.this,VerVideo.class);
							itMultimidia = new Intent(Mapa.this,VerDetalhesMarcador.class);
							itMultimidia.putExtra("foto", "");
							itMultimidia.putExtra("video", CAMINHO_VIDEO + url[1]);	
							itMultimidia.putExtra("titulo", marker.getTitle());
							
							itMultimidia.putExtra("dtOcorrencia", url[2]);
							itMultimidia.putExtra("hrOcorrencia", url[3]);
							itMultimidia.putExtra("dtCriacao", criacao[0]);
							itMultimidia.putExtra("hrCriacao", criacao[1]);
							itMultimidia.putExtra("categoria", url[5]);
							itMultimidia.putExtra("subcategoria", url[6]);
							itMultimidia.putExtra("notaMedia", url[7]);
							
							startActivity(itMultimidia);
							
							//Toast.makeText(Mapa.this, "Cliquei na InfoWindow com Video", Toast.LENGTH_SHORT).show();
						}else if(url.length > 2){							
							itMultimidia = new Intent(Mapa.this,VerDetalhesMarcador.class);
							itMultimidia.putExtra("foto", "");
							itMultimidia.putExtra("video", "");	
							itMultimidia.putExtra("titulo", marker.getTitle());
							
							itMultimidia.putExtra("dtOcorrencia", url[2]);
							itMultimidia.putExtra("hrOcorrencia", url[3]);
							itMultimidia.putExtra("dtCriacao", criacao[0]);
							itMultimidia.putExtra("hrCriacao", criacao[1]);
							itMultimidia.putExtra("categoria", url[5]);
							itMultimidia.putExtra("subcategoria", url[6]);
							itMultimidia.putExtra("notaMedia", url[7]);
							
							startActivity(itMultimidia);
							
							//Toast.makeText(Mapa.this, "Cliquei na InfoWindow sem foto e video", Toast.LENGTH_SHORT).show();
						}
						criacao = null; //limpa vetor
					}				
		        	
		        }//
		    });
			
			atual = CameraUpdateFactory.newLatLngZoom(pos,16);
			moveCamera();
			
			marcadorTemp = mapa.addMarker(new MarkerOptions().position(pos).title("Minha localização atual").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
			markersMultimidia.put(marcadorTemp.getId(), new String [] {"",""}); //novidade
			
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
						
						//novidade
						nomeFoto = z.getString("caminhoFoto");
						nomeVideo = z.getString("caminhoVideo");
						
						//novidade 2
						dtOcorrencia = z.getString("dataOcorrencia");
						hrOcorrencia = z.getString("horaOcorrencia");
						dtHrCriacao = z.getString("datahoraCriacao");
						categoria = z.getString("codCategoriaEvento");
						subcategoria = z.getString("codSubcategoriaEvento");
						notaMedia = z.getString("notaMedia");
						 
						Log.d("ID", i+"");
						Log.d("TITULO", a);
						Log.d("DESCRIÇÃO", b);		
						Log.d("COORDENADA X", c);		
						Log.d("COORDENADA Y", d);
						Log.d("CAMINHO FOTO", nomeFoto); //novidade
						Log.d("CAMINHO VIDEO", nomeVideo); //novidade
						 
						double m = Double.parseDouble(c);
						double n = Double.parseDouble(d);
						pos = new LatLng(m, n);
						
						titulo = a.trim();	//remove espaços em branco
						try {
							titulo = titulo.substring(0,1).toUpperCase().concat(titulo.substring(1)); //Coloca primeira letra em maiusculo
						} catch (Exception e) {
							Log.e("Caixa Alta", "Não foi possível atualizar o título " + titulo);
						}
						
						descricao = b.trim(); //remove espaços em branco
						try {
							descricao = descricao.substring(0,1).toUpperCase().concat(descricao.substring(1)); //Coloca primeira letra em maiusculo
						} catch (Exception e) {
							Log.e("Caixa Alta", "Não foi possível atualizar a descrição " + descricao);
							Log.e("Caixa Alta", "Não foi possível atualizar a descrição com título " + titulo);
						}
					
						//modifica o formato da data de publicação
						String dtHr[] = dtHrCriacao.split(" ");
						String dt[] = dtHr[0].split("-");
						dtHrCriacao = dt[2]+"/"+dt[1]+"/"+dt[0] + " - " + dtHr[1];
						dt = null;
						dtHr = null;
						System.gc();
						
						//adiciona data e hora de criação ao final do snippet
						if(!dtOcorrencia.equals("") && !hrOcorrencia.equals("")){
							//modifica o formato da data
							dt = dtOcorrencia.split("-");
							dtOcorrencia = dt[2]+"/"+dt[1]+"/"+dt[0];
							dt = null;
							System.gc();
							
							descricao += "\n\nOCORRÊNCIA: " + dtOcorrencia + " - " + hrOcorrencia;
						}else{							
							descricao += "\n\nPUBLICAÇÃO: " + dtHrCriacao;	
						}
						
						handler.post(new Runnable() {
							public void run() {
								marcadorTemp = mapa.addMarker (new MarkerOptions().position(pos).title(titulo).snippet(descricao));						
								markersMultimidia.put(marcadorTemp.getId(), new String [] {nomeFoto,nomeVideo,dtOcorrencia,hrOcorrencia,dtHrCriacao,categoria,subcategoria,notaMedia}); //MUDEI HOJE 15/12/2014
								//0 = foto
								//1 = video
								//2 = data de ocorrencia
								//3 = hora de ocorrencia
								//4 = data e hora de criação
								//5 = categoria
								//6 = subcategoria
								//7 = nota media
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
	
	private class CustomInfoWindowAdapter implements InfoWindowAdapter {

		private View view;

		public CustomInfoWindowAdapter() {
			view = getLayoutInflater().inflate(R.layout.custom_info_window, null);
		}

		@Override
		public View getInfoContents(Marker marker) {

			if (Mapa.this.marker != null && Mapa.this.marker.isInfoWindowShown()) {
				Mapa.this.marker.hideInfoWindow();
				Mapa.this.marker.showInfoWindow();
			}
			return null;
		}

		@Override
		public View getInfoWindow(final Marker marker) {
			Mapa.this.marker = marker;

			String[] url = null;

			if (marker.getId() != null && markersMultimidia != null && markersMultimidia.size() > 0) {
				if ( markersMultimidia.get(marker.getId()) != null ) {
					url = markersMultimidia.get(marker.getId());
				}
			}
			
			final ImageButton foto = ((ImageButton) view.findViewById(R.id.icon_i));
			final ImageButton video = ((ImageButton) view.findViewById(R.id.icon_v));
			final TextView labelInfo = ((TextView) view.findViewById(R.id.labelInfoWindow));


            // TODO - ENTRA AQUI PARA HABILITAR OU DESABILITAR ICONE DE FOTO E VIDEO - MELHORAR ESTA PARTE
			if(url != null){
				//se existir foto
				if(!url[0].equals("")){
					foto.setVisibility(View.VISIBLE);
					foto.setClickable(true);
					labelInfo.setVisibility(View.VISIBLE);
				}else{
					foto.setVisibility(View.GONE);
					foto.setClickable(false);
					foto.setTag("nulo");
				}
			}else{
				foto.setVisibility(View.GONE);
				foto.setClickable(false);
				foto.setTag("nulo");
			}

			if(url != null){
				//se existir video
				if(!url[1].equals("")){
					tipoVideo = url[1].substring(url[1].length()-3);
					Log.i("TIPO_VIDEO", tipoVideo);
					
					if(tipoVideo.equals("mp4") || tipoVideo.equals("3gp")){
						video.setVisibility(View.VISIBLE);
						video.setClickable(true);
						labelInfo.setVisibility(View.VISIBLE);
					}else{
						video.setVisibility(View.GONE);
						video.setClickable(false);
						video.setTag("nulo");
					}
				}else{
					video.setVisibility(View.GONE);
					video.setClickable(false);
					video.setTag("nulo");
				}
			}else{
				video.setVisibility(View.GONE);
				video.setClickable(false);
				video.setTag("nulo");
			}
			
						
			if(url != null){	
				//se for a minha localização entra! NOVIDADE
				if(url[0].equals("") && url[1].equals("") && url.length == 2)
					labelInfo.setVisibility(View.GONE);
				else if(url[0].equals("") && !url[1].equals("") && (!tipoVideo.equals("mp4") && !tipoVideo.equals("3gp")) )
					labelInfo.setVisibility(View.GONE);  //se tiver um video incompatível entra NOVIDADE
			}else{
				labelInfo.setVisibility(View.GONE);
			}
            // TODO - FIM - ENTRA AQUI PARA HABILITAR OU DESABILITAR ICONE DE FOTO E VIDEO - MELHORAR ESTA PARTE

			final String title = marker.getTitle();
			final TextView titleUi = ((TextView) view.findViewById(R.id.titleMarcador));
			if (title != null) {
				titleUi.setText(title);
			} else {
				titleUi.setText("");
			}

			final String snippet = marker.getSnippet();
			final TextView snippetUi = ((TextView) view.findViewById(R.id.snippet));
			
			if (snippet != null) {
				snippetUi.setText(snippet);
			} else {
				snippetUi.setText("");
			}

			return view;
		}
	}

}