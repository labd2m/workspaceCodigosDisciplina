package pacote.cidadaovicosa;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.json.JSONObject;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
//import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationRequest;
//import com.google.android.gms.location.LocationListener;

import pacote.ferramentas.BancoDados;
import pacote.ferramentas.JSONParser;
import pacote.ferramentas.UploadFile;
import pacote.ferramentas.UserFunctions;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.support.v4.net.ConnectivityManagerCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


@SuppressLint("ServiceCast")
//implements GooglePlayServicesClient.ConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener, LocationListener 

public class Colaboracao extends Activity implements LocationListener {

	private String[] categorias = new String[] { "Desaparecidos", "Entretenimento", "Infraestrutura", "Meio Ambiente", "Saúde", 
			"Segurança", "Serviço ou Produto","Outras Categorias"};
	
	FuncaoFormulario ATform;
	FuncaoFoto ATfoto;
	FuncaoVideo ATvideo;
	
	public boolean colaboracaoEnviada = true;
		
	public static final  int SOBRE  = 0;
	public static final  int SAIR  = 1;
	public static final  int SYNC = 2;
	
	public static final  int WIFI = 1;
	public static final  int DADOS = 2;
	
	public int conexao;
	
	public int contaColaboracao;
	
	public int TIMEOUT_CONNECTION;
	public int TIMEOUT_SOCKET;
	
	public int TIMEOUT_CONNECTION_UPLOAD;
	public int TIMEOUT_SOCKET_UPLOAD;
	
	public int TIMEOUT_HANDLER_UPLOAD;
	
	Uri myFoto;
	Uri myVideo;
	
	static String ID;
	
	String Email;
	
	String mytitulo;
	String myresumo;
	
	EditText titulo;
	EditText resumo;
	
	ImageButton foto;
	ImageButton audio;
	ImageButton video;
	ImageButton btnsync;
	
	String categoria;
	String subcategoria;
	
	String Caminhofoto;
	String Caminhovideo;
	
	Spinner Categoria;
	Spinner Subcategoria;
	
	TextView usuario;
	TextView valLatitude;
	TextView valLongitude;
	TextView labelMultimidia;

	UploadFile uploadfoto;
	UploadFile uploadvideo;
	
	String latitude = null;
	String longitude = null;
	
	Button btmapa;
	Button btlogout;
	Button btsalvar;
	
	//atributos usados na sincronização de dados
	int idColaboracaoSync;
	String tituloSync;
	String resumoSync;
	String categoriaSync;
	String subcategoriaSync;
	String nomearquivofotoSync;
	String nomearquivovideoSync;
	String latitudeSync;
	String longitudeSync;
	String idSync;
	String CaminhofotoSync;
	String CaminhovideoSync;
	String dtOcorrenciaSync;
	String hrOcorrenciaSync;
	//--------------------------------
	
	public String provider;
	public LocationManager lm;
	
	UserFunctions userFunctions;
	
	private ProgressDialog pDialog;
	
	static String nomearquivofoto = "";
	static String nomearquivovideo = "";
	
	public Handler handler = new Handler();
			
	JSONParser jsonParser = new JSONParser();
	
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    
	private static String KEY_SUCCESS = "success";
	
	//MyLocationListener mlocListener;
	
	boolean isConnected;
	
	//FusedLocationChanged
	//private LocationClient locationclient;
	private LocationRequest locationrequest;
	
	//Parâmetros do Location Manager
	public int TEMPO_REQUISICAO_LATLONG = 5000;
	public int DISTANCIA_MIN_METROS = 0;
	public Criteria criteria;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
				
		userFunctions = new UserFunctions();

    	if(userFunctions.isUserLoggedIn(getApplicationContext())){
    		setContentView(R.layout.layout_colaboracao);
    		    		
    		///////RECUPERA ID e EMAIL DO USUARIO
    		ID = userFunctions.recuperaID(getApplicationContext());
    		Email = userFunctions.RecuperaEmail(getApplicationContext());
		    		
    		/////// LOCATION MANAGER ///////
			lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
			criteria = new Criteria();
		
			//Testa se o aparelho tem GPS
			PackageManager packageManager = getPackageManager();
			boolean hasGPS = packageManager.hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS);
			
			//Estabelece critério de precisão
			if(hasGPS){
				criteria.setAccuracy( Criteria.ACCURACY_FINE );
				Log.i("LOCATION", "Dispositivo possui GPS");
			}else{
				criteria.setAccuracy( Criteria.ACCURACY_COARSE );
				Log.i("LOCATION", "Dispositivo não possui GPS. Usando WI-FI ou dados");
			}
		    /////// LOCATION MANAGER ///////  		
    		
	    	/////// VIEWS ///////
			//audio = (ImageButton) findViewById(R.id.ib_audio);
			titulo = (EditText) findViewById(R.id.edt_titulo);
			resumo = (EditText) findViewById(R.id.edt_resumo);
			foto = 	(ImageButton) findViewById(R.id.ib_foto);
			usuario = (TextView) findViewById(R.id.tv_usuario);
			video = (ImageButton) findViewById(R.id.ib_video);
			Categoria = (Spinner) findViewById(R.id.spin_categoria);
			Subcategoria = (Spinner) findViewById(R.id.spin_subcategoria);
			labelMultimidia = (TextView) findViewById(R.id.multimidiaColaboracao);
			
			usuario.setText("Usuário: "+ Email);
		
    		Categoria.setOnItemSelectedListener(new mudarSpinner());
    		
    		//configura adaptador
    		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, R.layout.meu_spinner_item, categorias);
    		adaptador.setDropDownViewResource(R.layout.meu_spinner_item);
    		//informa qual é o adaptador
    		Categoria.setAdapter(adaptador);
    		
    		/////// CRIA ARQUIVOS FOTO E VIDEO ///////
			uploadfoto 	= new UploadFile();
			uploadvideo = new UploadFile();
			
			int numCameras = Camera.getNumberOfCameras();
			
			//ativa opção de enviar video em dispositivos android superiores ao 4.0 que tenham camera
			if(android.os.Build.VERSION.SDK_INT >= 14 && numCameras > 0 ){
				video.setVisibility(View.VISIBLE);
				video.setClickable(true);
			}
			
			//ativa opção de enviar foto em dispositivos android que tenham camera
			if(numCameras > 0){
				foto.setVisibility(View.VISIBLE);
				foto.setClickable(true);
				labelMultimidia.setVisibility(View.VISIBLE);
			}
			
			//ativa botão de sincronizar se existirem contribuições não sincronizadas
			contaColaboracao = userFunctions.contaColaboracoesNaoSincronizadas(Colaboracao.this);
			
			if(contaColaboracao > 0 ){
				btnsync = (ImageButton) findViewById(R.id.btnSync);
				
				btnsync.setVisibility(View.VISIBLE);
				btnsync.setClickable(true);
			}
						
    	}
    	else{
    		
    		Intent login = new Intent(getApplicationContext(), Login.class);
    		login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		startActivity(login);
    		ID = null;
    		finish();
    	}
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		/*try{
			//TESTA SE SERVIÇO DO GOOGLE ESTÁ DISPONÍVEL
			int resp = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
    		if(resp == ConnectionResult.SUCCESS){
    			Log.d("Google Play Service", "Serviço do Google está disponível");
    			
        		//LOCATION -- FusedLocationProvider supplied with Google Play Services (NÃO DA PROBLEMA COM BATERIA BAIXA)
        		locationclient = new LocationClient(this,this,this);
        		locationclient.connect();
    		}
    		else{
    			Log.d("Google Play Service", "Serviço do Google está indisponível. Erro: " + resp);
    			Toast.makeText(this, "Serviço indisponível no momento...", Toast.LENGTH_LONG).show();
    			finish();
    		}
		}catch(Exception e){
			e.printStackTrace();
			Toast.makeText(this, "Desculpe-nos pelo transtorno. Esta versão do app é incompatível com o seu aparelho.", Toast.LENGTH_LONG).show();
			Log.d("Google Play Service", "Desculpe-nos pelo transtorno. Esta versão do app é incompatível com o seu aparelho.");    			
			finish();
		}*/
		
		//Obtem melhor provedor habilitado com o critério estabelecido
		provider = lm.getBestProvider(criteria, true );
		
    	if ( provider == null ){
    		Log.e( "LOCATION", "Nenhum provedor encontrado!" );
    	}else{
			Log.i( "LOCATION", "Esta sendo utilizado o provedor: " + provider );
			
			//Obtem atualizações de posição
			lm.requestLocationUpdates(provider, TEMPO_REQUISICAO_LATLONG, DISTANCIA_MIN_METROS, this);
    	}
		
		//Google Analytics
		EasyTracker.getInstance(this).activityStart(this);
	}
	  
	@Override
	public void onStop() {
	    super.onStop();
	   
	    //Google Analytics
	    EasyTracker.getInstance(this).activityStop(this);  // Add this method.
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		//VOLTA COR DOS BOTÕES PARA O PADRÃO
		btlogout = (Button) findViewById(R.id.bt_logout);
		btlogout.setBackgroundResource(R.drawable.fundobotaopadrao);
		
		btmapa = (Button) findViewById(R.id.bt_mapa);
		btmapa.setBackgroundResource(R.drawable.fundobotaopadrao);
		
		btsalvar = (Button) findViewById(R.id.bt_salvar);
		btsalvar.setBackgroundResource(R.drawable.fundobotaopadrao);
		
		/*Obtem confirmação se wifi está conectado ou conectando*/
	    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    
	    //Obtem confirmação se existe conexão com a internet
	    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
	    isConnected = activeNetwork != null && activeNetwork.isConnected();
	    
	    if( cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()) {
	        Log.i("Conexão", "USANDO CONEXÃO WI-FI"); 
	        conexao = WIFI;
	        
	        TIMEOUT_CONNECTION = 20000;
	        TIMEOUT_SOCKET = 20000;
	        
	        TIMEOUT_CONNECTION_UPLOAD = 30000;
	        TIMEOUT_SOCKET_UPLOAD = 30000;
	        
	        TIMEOUT_HANDLER_UPLOAD = 30000;
	    }else if(cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting()){
	        Log.i("Conexão", "USANDO CONEXÃO DE DADOS"); 
	        conexao = DADOS;
	        
	        TIMEOUT_CONNECTION = 70000;
	        TIMEOUT_SOCKET = 70000;
	        
	        TIMEOUT_CONNECTION_UPLOAD = 70000;
	        TIMEOUT_SOCKET_UPLOAD = 70000*2;
	        
	        TIMEOUT_HANDLER_UPLOAD = 150000;
	    }else{
	        Log.i("Conexão", "SEM CONEXÃO COM INTERNET"); 
	        conexao = DADOS;
	        
	        TIMEOUT_CONNECTION = 70000;
	        TIMEOUT_SOCKET = 70000;
	        
	        TIMEOUT_CONNECTION_UPLOAD = 70000;
	        TIMEOUT_SOCKET_UPLOAD = 70000*2;
	        
	        TIMEOUT_HANDLER_UPLOAD = 150000;
	    }
		
	}
	
	@Override
	protected void onDestroy() {		
		/*if(locationclient != null){
			locationclient.disconnect();
			Log.w("PROVEDOR","Fused Location  Provider Parado...");
		}*/
		
		if(lm != null){
			lm.removeUpdates(this);
			Log.w("LOCATION","Provedor " + provider + " parado!");
		}
		super.onDestroy();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super .onCreateOptionsMenu(menu);
			
		menu.add(0, SOBRE, 1, "Sobre").setIcon(R.drawable.get_info);
		menu.add(0, SAIR, 3, "Sair").setIcon(R.drawable.sair);
		
		if(contaColaboracao > 0)
			menu.add(0, SYNC, 2, "Sincronizar").setIcon(R.drawable.sync_icon_menu);

		return true;
	}
	
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId())  {
			case SOBRE:
				Intent it = new Intent(this,Sobre.class);
				startActivity(it);
				return  true;
				
			case SAIR:
				
				/** AlertDialog com Sim e Não **/
				AlertDialog.Builder alerta = new AlertDialog.Builder(this);
				alerta.setMessage("Deseja realmente sair?");
				
				// Configura Método executado se escolher Sim
				alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,  int id) {
						finish();
					}
				});
				
				// Configura Método executado se escolher Não
				alerta.setNegativeButton("Não", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,  int id) {
						dialog.cancel();
					}
				});
				
				// Exibe o alerta de confirmação
				alerta.show();
				return true;
				
			case SYNC:
				eventosync(new View(Colaboracao.this));
				return true;
						
		}
		
		return  false;
	}
	
	public void reducaoDeFoto(){
		//reduz foto
		ArrayList<String> fotoCaminho = uploadfoto.reduzirImagem(Caminhofoto, ID, 0.5, 40);
		
		if(fotoCaminho != null){
			nomearquivofoto = fotoCaminho.get(0);
			Caminhofoto = fotoCaminho.get(1);
		}else{
			nomearquivofoto = "";
			Caminhofoto = null;
		}
	}
	
	/*public class MyLocationListener implements LocationListener {
		public void onLocationChanged(Location loc) {
			Log.i("LOCATION", "Obteve localização...");
			
			latitude = Double.toString(loc.getLatitude());
			longitude = Double.toString(loc.getLongitude());
			TextView tvlat = (TextView) findViewById(R.id.tv_lat);
			TextView tvLong = (TextView) findViewById(R.id.tv_long);
			tvlat.setText("Latitude: " + latitude);
			tvLong.setText("Longitude: " + longitude);
		}

		public void onProviderDisabled(String provider) {
			Log.d("LOCATION", "Desabilitou o provedor");
		}

		public void onProviderEnabled(String provider) {
			Log.d("LOCATION", "Habilitou o provedor");
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
			Log.d("LOCATION", "Provedor mudou de estado");
		}
	}*/
	
	public class mudarSpinner implements OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> parent, View arg1, int pos, long arg3) {
			funcaoSpinner(pos);
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
		}
	}
	
	public void funcaoSpinner(int pos){
		String[] lista = null;
		if(pos == 0){
			lista = new String [] { "Animal desaparecido","Pessoa desaparecida","Outro"};} //3
		else if(pos == 1){
			lista = new String [] { "Balada","Calourada","Casamento","Cinema","Clube","Empresarial",
									"Exposição","Formatura","Religiosa","Show","Temática","Teatro","Outro"};} //13
		else if(pos == 2){
			lista = new String [] { "Calçada","Coleta de Lixo","Distribuição de água","Faixa de pedestre","Iluminação urbana","Internet",
									"Logradouro","Rua/Avenida","Saneamento básico","Semáforo","Terreno Baldio","Outro"};} //12
		else if(pos == 3){
			lista = new String [] { "Desmatamento","Fauna","Flora","Incêndio ou Queimadas","Poluição","Tráfico de animal",
									"Tráfico de vegetal","Outro"};} //8
		else if(pos == 4){
			lista = new String [] { "Epidemia","Foco de doença","Foco de dengue","Hospital","Plano de saúde","Pronto socorro",
									"Outro"};} //7
		else if(pos == 5){
			lista = new String [] { "Assalto","Denúncia","Furto","Policiamento","Ponto de uso de drogas","Roubo",
									"Tráfico", "Violência doméstica","Outro"};} //9
		else if(pos == 6){
			lista = new String [] { "Academia","Açougue","Banco","Bar","Correio","Empresa",
									"Farmácia","Lojas","Mercado","Papelaria","Pizzaria","Restaurante","Salão de beleza","Supermercado","outro"};} //15
		else if(pos == 7){
			lista = new String [] { "Outro tipo"};} //1
		
		//SUBSTITUIDO PARA COMPATIBILIZAR O APP COM API LEVEL 8
		//ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista);
		//adaptador.setDropDownViewResource(android.R.layout.simple_list_item_1);
		
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, R.layout.meu_spinner_item, lista);
		adaptador.setDropDownViewResource(R.layout.meu_spinner_item);
		
		Subcategoria.setAdapter(adaptador);
	}
	
	/////// Create a file Uri for saving an image or video ///////
    public static Uri getOutputMediaFileUri(int type){
          return Uri.fromFile(getOutputMediaFile(type));
    }

    /////// Create a File for saving an image or video ///////
    private static File getOutputMediaFile(int type){

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                  Environment.DIRECTORY_PICTURES), "CidadaoVicosa");

        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("PASTA", "FALHA AO CRIAR PASTA");
                return null;
            }
        }
        
        String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.US).format(new Date());

        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
        	nomearquivofoto = "IMG"+ ID + "_" + timeStamp + ".jpg";
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + nomearquivofoto);
        } else if(type == MEDIA_TYPE_VIDEO) {
        	nomearquivovideo = "VID"+ ID + "_" + timeStamp + ".mp4";
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + nomearquivovideo);
        } else {
            return null;
        }

        return mediaFile;
    }
	
	public void eventoenviar(View view) {
		
    	int categoriaPos = Categoria.getSelectedItemPosition() + 1;
    	int subcategoriaPos = Subcategoria.getSelectedItemPosition();
    	
    	if (categoriaPos == 1){
    		subcategoriaPos += 1;
    	}else if(categoriaPos == 2){
    		subcategoriaPos += 4;
    	}else if(categoriaPos == 3 && subcategoriaPos <= 8){
    		subcategoriaPos += 17;
    	}else if(categoriaPos == 3 && subcategoriaPos > 8){ //novidade
    		subcategoriaPos += 18;
    	}else if(categoriaPos == 4){
    		subcategoriaPos += 30;
    	}else if(categoriaPos == 5){
    		subcategoriaPos += 38;
    	}else if(categoriaPos == 6){
    		subcategoriaPos += 45;
    	}else if(categoriaPos == 7){
    		subcategoriaPos += 54;
    	}else if(categoriaPos == 8){
    		subcategoriaPos += 69;
    	}
    	
    	categoria = Integer.toString(categoriaPos);
    	subcategoria = Integer.toString(subcategoriaPos);
    	
    	mytitulo = titulo.getText().toString();
    	myresumo = resumo.getText().toString();
		
		if(longitude == null || latitude == null){
			
			Toast.makeText(this, "Aguarde a localização", Toast.LENGTH_SHORT).show();
			
		}
		else if(mytitulo.length() == 0 || myresumo.length() == 0){

			Toast.makeText(this, "Preencha o título e a descrição", Toast.LENGTH_SHORT).show();

		}
		else{
			
			reducaoDeFoto();
			
			ATform = new FuncaoFormulario();
			ATform.execute();
			
			if(colaboracaoEnviada){
				
				ATfoto = new FuncaoFoto();
				ATfoto.execute();
				
	            handler.postDelayed(new Runnable() {	
		       			@Override
		       			public void run() {
		       				Log.i("FECHANDO CONEXÃO", "TENTANDO FECHAR CONEXÃO..");
		       				if(ATfoto.getStatus() == AsyncTask.Status.RUNNING){
		       					Log.i("FECHANDO CONEXÃO", "CANCELEI ENVIO DE FOTO...");
		       					ATfoto.cancel(true);
		       				}else{
		       					Log.i("FECHANDO CONEXÃO", "PROCESSO DE ENVIO DE FOTO JÁ HAVIA TERMINADO...");
		       				}
		       				
		       			}
	     		}, TIMEOUT_HANDLER_UPLOAD);
				
				ATvideo = new FuncaoVideo();
				ATvideo.execute();
				
	            handler.postDelayed(new Runnable() {	
		       			@Override
		       			public void run() {
		       				Log.i("FECHANDO CONEXÃO", "TENTANDO FECHAR CONEXÃO..");
		       				if(ATvideo.getStatus() == AsyncTask.Status.RUNNING){
		       					Log.i("FECHANDO CONEXÃO", "CANCELEI ENVIO DE VIDEO...");
		       					ATvideo.cancel(true);
		       				}else{
		       					Log.i("FECHANDO CONEXÃO", "PROCESSO DE ENVIO DE VIDEO JÁ HAVIA TERMINADO...");
		       				}
		       				
		       			}
	     		}, TIMEOUT_HANDLER_UPLOAD * 2);
	            
			}
			
			/*if (Caminhofoto != null) {
				new Thread(new Runnable() {
					public void run() {
						runOnUiThread(new Runnable() {
							public void run() {
							}
						});
						uploadfoto.uploadFile(Caminhofoto);
						nomearquivofoto = "";
					}
				}).start();
			}

			if (Caminhovideo != null) {
				new Thread(new Runnable() {
					public void run() {
						runOnUiThread(new Runnable() {
							public void run() {
							}
						});
						uploadvideo.uploadFile(Caminhovideo);
						nomearquivovideo = "";
					}
				}).start();
			}*/
		}
    }
	
	class FuncaoFoto extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

		}

		protected String doInBackground(String... args) {
			
			if (Caminhofoto != null) {		
				Log.i("UPLOAD", "Enviando foto...");						
				
				System.gc();
				int resp = uploadfoto.uploadFile(Caminhofoto, nomearquivofoto, "foto", TIMEOUT_CONNECTION_UPLOAD, TIMEOUT_SOCKET_UPLOAD);
				
				if(resp != 200){
					//nomearquivofoto = "";
					Log.i("UPLOAD", "Foto não enviada!");
				}else{
					Log.i("UPLOAD", "Terminei de enviar foto!");
				}	
			}else{
				Log.i("UPLOAD", "Foto não existe...");
			}
			
			return null;    	
		}
		
		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
			Log.i("FECHANDO CONEXÃO", "onCancelled() foto!");
			
		    //Inicia Thread para enviar requisição de exclusão da foto
			DeletaFoto deleta = new DeletaFoto();   
		    Thread threadDeletaFoto = new Thread(deleta);
		    threadDeletaFoto.start();	
		}

		protected void onPostExecute(String file_url) {
			Log.i("FECHANDO CONEXÃO", "onPostExecute() foto!");
		}
	}
	
	class FuncaoVideo extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

		}

		protected String doInBackground(String... args) {
			
			if (Caminhovideo != null) {
				Log.i("UPLOAD", "Enviando video...");
				
				System.gc();
				int resp = uploadvideo.uploadFile(Caminhovideo, nomearquivovideo, "video", TIMEOUT_CONNECTION_UPLOAD, TIMEOUT_SOCKET_UPLOAD);
				
				if(resp != 200){
					//nomearquivovideo = "";
					Log.i("UPLOAD", "Vídeo não enviado!");
				}else{
					Log.i("UPLOAD", "Terminei de enviar video!");
				}
			}
			return null;

	    	
		}
		
		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();		
			Log.i("FECHANDO CONEXÃO", "onCancelled() video!");
			
		    //Inicia Thread para enviar requisição de exclusão do video
			DeletaVideo deleta = new DeletaVideo();    
		    Thread threadDeletaVideo = new Thread(deleta);
		    threadDeletaVideo.start();		
		}

		protected void onPostExecute(String file_url) {
			Log.i("FECHANDO CONEXÃO", "onPostExecute() video!");
		}
	}
	
	class FuncaoFormulario extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Colaboracao.this);
			pDialog.setMessage("ENVIANDO...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		protected String doInBackground(String... args) {

	    	UserFunctions userFunction = new UserFunctions();    	
	    		    	
	    	JSONObject json = userFunction.Colaborar(mytitulo, myresumo, categoria, subcategoria,
	    							nomearquivofoto, nomearquivovideo, latitude, longitude, ID, TIMEOUT_CONNECTION, TIMEOUT_SOCKET);
	    		    	
			try {
				String res = json.getString(KEY_SUCCESS); 
				if(Integer.parseInt(res) == 1){
						
					handler.post(new Runnable() {
						public void run() {
							Toast.makeText(Colaboracao.this, "Enviado com sucesso", Toast.LENGTH_LONG).show();
							Intent it4 = new Intent(Colaboracao.this,Colaboracao.class);
							startActivity(it4);
						}
					});
					finish();
				}else{
					colaboracaoEnviada = false;  //configura valor para impedir a tentativa de upload;
				}
			}catch (Exception e) {
				e.printStackTrace();
				
				colaboracaoEnviada = false;  //configura valor para impedir a tentativa de upload;
				
				handler.post(new Runnable() {
					public void run() {
						Toast.makeText(Colaboracao.this, "Serviço indisponível no momento...", Toast.LENGTH_SHORT).show();
					}
				});
			}
			return null;
		}
		
		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
			pDialog.dismiss();
			Log.i("CANCELOU", "CANCELOU ASYNC TASK");
		}

		protected void onPostExecute(String file_url) {
			pDialog.dismiss();
		}
	}
	
	class FuncaoSalvar extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		protected String doInBackground(String... args) {
			Log.i("SALVAR COLABORAÇÃO","Iniciando em segundo plano...");
			
            handler.postDelayed(new Runnable() {	
	       			@Override
	       			public void run() {
	       				reducaoDeFoto();
	       				
	       				UserFunctions userFunction = new UserFunctions();    	
	       		    	
	       		    	try{
	       					long idCadastrado = userFunction.Salvar(mytitulo, myresumo, categoria, subcategoria, nomearquivofoto, nomearquivovideo, Caminhofoto, Caminhovideo, latitude, longitude, ID, Colaboracao.this);
	       			    		    	
	       					if(idCadastrado > 0){
	       						Log.i("SALVAR COLABORAÇÃO", "COLABORAÇÃO FOI SALVA");		
	       						Toast.makeText(Colaboracao.this, "Colaboração foi salva", Toast.LENGTH_LONG).show();
	       						
	       						//OBTENDO VALORES SALVOS
	       						userFunction.ExibeColaboracaoSalva(idCadastrado, Colaboracao.this);				
	       						
	       						Intent it4 = new Intent(Colaboracao.this,Colaboracao.class);
	       						startActivity(it4);
	       		
	       						finish();
	       					}else{
	       						Toast.makeText(Colaboracao.this, "Problema ao salvar a colaboração", Toast.LENGTH_LONG).show();
	       						Log.e("SALVAR COLABORAÇÃO", "Problema ao tentar salvar a colaboração...");
	       						btsalvar.setBackgroundResource(R.drawable.fundobotaopadrao);
	       					}
	       		    	}catch(Exception e){
	       		    		e.printStackTrace();
	       		    		Log.e("SALVAR COLABORAÇÃO", "Problema ao tentar salvar a colaboração... (exeption)");
	       		    		btsalvar.setBackgroundResource(R.drawable.fundobotaopadrao);
	       		    	}
	       				
	       			}
     		}, 200); //uso esse delay somente para o menu ficar mais interativo
			
			return null;
		}
		
		protected void onPostExecute(String file_url) {

		}
	}
	
	
	class FuncaoSincronizar extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Colaboracao.this);
			pDialog.setMessage("SINCRONIZANDO...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		protected String doInBackground(String... args) {
			
			UserFunctions userFunction = new UserFunctions(); 
			
			Cursor c = userFunction.recuperaColaboracoesNaoSincronizadas(Colaboracao.this);
			
			if(c.getCount() > 0){
				
				int vetConfirma[] = new int[c.getCount()];
				int cont = 0;
	        	
	    		while(c.moveToNext()){
	        		int indexID = c.getColumnIndex("id");
	        		int indexTitulo = c.getColumnIndex("titulo");
	        		int indexResumo = c.getColumnIndex("resumo");
	        		int indexCategoria = c.getColumnIndex("categoria");
	        		int indexSubcategoria = c.getColumnIndex("subcategoria");
	        		int indexFoto = c.getColumnIndex("foto");
	        		int indexVideo = c.getColumnIndex("video");
	        		int indexURIfoto = c.getColumnIndex("urifoto");
	        		int indexURIvideo = c.getColumnIndex("urivideo");
	        		int indexLatitude = c.getColumnIndex("latitude");
	        		int indexLongitude = c.getColumnIndex("longitude");
	        		int indexIdUsuario = c.getColumnIndex("idusuario");
	        		int indexDataOcorrencia = c.getColumnIndex("dataocorrencia");
	        		int indexHoraOcorrencia = c.getColumnIndex("horaocorrencia");
	        		int indexSync = c.getColumnIndex("sync");
	        		
	        		idColaboracaoSync = c.getInt(indexID);
	        		tituloSync = c.getString(indexTitulo);
	        		resumoSync = c.getString(indexResumo);
	        		categoriaSync = c.getString(indexCategoria);
	        		subcategoriaSync = c.getString(indexSubcategoria);
	        		nomearquivofotoSync = c.getString(indexFoto);
	        		nomearquivovideoSync = c.getString(indexVideo);
	        		CaminhofotoSync = c.getString(indexURIfoto);
	        		CaminhovideoSync = c.getString(indexURIvideo);
	        		latitudeSync = c.getString(indexLatitude);
	        		longitudeSync = c.getString(indexLongitude);
	        		idSync = c.getString(indexIdUsuario);
	        		dtOcorrenciaSync = c.getString(indexDataOcorrencia);
	        		hrOcorrenciaSync = c.getString(indexHoraOcorrencia);
	        		
	        		//ENVIA FOTO-----------------------------------------
	        		if (CaminhofotoSync != null) {		
	    				Log.i("UPLOAD", "Enviando foto...");						
	    				
	    				int resp = uploadfoto.uploadFile(CaminhofotoSync, nomearquivofotoSync, "foto", TIMEOUT_CONNECTION_UPLOAD, TIMEOUT_SOCKET_UPLOAD);
	    				
	    				if(resp != 200){
	    					nomearquivofotoSync = "";
	    					Log.i("UPLOAD", "Foto não enviada!");
	    				}else{
	    					Log.i("UPLOAD", "Terminei de enviar foto!");
	    				}	
	    			}else{
	    				Log.i("UPLOAD", "Foto não existe...");
	    			}
	        		//----------------------------------------
	        		
	        		//ENVIA VIDEO------------------------------------
	        		if (CaminhovideoSync != null) {
	    				Log.i("UPLOAD", "Enviando video...");
	    				
	    				int resp = uploadvideo.uploadFile(CaminhovideoSync, nomearquivovideoSync, "video", TIMEOUT_CONNECTION_UPLOAD, TIMEOUT_SOCKET_UPLOAD);
	    				
	    				if(resp != 200){
	    					nomearquivovideoSync = "";
	    					Log.i("UPLOAD", "Vídeo não enviado!");
	    				}else{
	    					Log.i("UPLOAD", "Terminei de enviar video!");
	    				}
	    			}
	        		//--------------------------------
	        		
	        		//SALVA NO BANCO DO SERVER-----------------
	    	    	JSONObject json = userFunction.Sincronizar(tituloSync, resumoSync, categoriaSync, subcategoriaSync, nomearquivofotoSync, 
	    	    												nomearquivovideoSync, latitudeSync, longitudeSync, idSync, dtOcorrenciaSync, 
	    	    												hrOcorrenciaSync, TIMEOUT_CONNECTION, TIMEOUT_SOCKET);	    	
	    	    	
					try {
						String res = json.getString(KEY_SUCCESS);
						vetConfirma[cont] = Integer.parseInt(res); //EM ALGUM MOMENTO FALHOU AQUI! MAS PORQUE NÃO SINCRONIZA MAIS??
						
						Log.i("SINCRONIZAÇÃO", "TTULO:" + tituloSync  +
								"RESUMO: " + resumoSync +
								"CATEGORIA: " + categoriaSync +
								"SUBCATEGORIA: " + subcategoriaSync +
								"FOTO: " + nomearquivofotoSync +
								"VIDEO: " + nomearquivovideoSync +
								"LATITUDE: " + latitudeSync +
								"LONGITUDE: " + longitudeSync +
								"ID SYNC: " + idSync +
								"DATA OCORRENCIA: " + dtOcorrenciaSync +
								"HORA OCORRENCIA: " + hrOcorrenciaSync);
						
						if(Integer.parseInt(res) == 1){
							//exclui colaboração sincronizado
							userFunction.deletaColaboracaoSincronizada(Colaboracao.this, idColaboracaoSync);
													
						}else{
							Log.e("SINCRONIZAÇÃO", "Problema ao sincronizar..."); //entra aqui também! porque só essa registro não sincroniza?
						}
					}catch (Exception e) {
						e.printStackTrace();
						
						Log.e("SINCRONIZAÇÃO", "Problema ao sincronizar... (exception)");						
					}
	        		
					cont++;	        		       		
	        	}//end while
	    		
	    		boolean verificaSync = true;
	    		
	    		for(int i = 0; i < c.getCount(); i++){
	    			if(vetConfirma[i] != 1)
	    				verificaSync = false;
	    		}
	    		
	    		if(verificaSync){
	    			Log.i("SINCRONIZAÇÃO", "TODAS AS COLABORAÇÕES FORAM SINCRONIZADAS...");
	    			
					handler.post(new Runnable() {
						public void run() {
							Toast.makeText(Colaboracao.this, "Colaborações foram sincronizadas", Toast.LENGTH_LONG).show();
							Intent it5 = new Intent(Colaboracao.this,Colaboracao.class);
							startActivity(it5);
						}
					});
					finish();
					
	    		}else{
	    			Log.i("SINCRONIZAÇÃO", "NEM TODAS AS COLABORAÇÕES FORAM SINCRONIZADAS..."); 
	    			
					handler.post(new Runnable() {
						public void run() {
							Toast.makeText(Colaboracao.this, "Algumas colaborações não foram sincronizadas...", Toast.LENGTH_LONG).show();
							Intent it5 = new Intent(Colaboracao.this,Colaboracao.class);
							startActivity(it5);
						}
					});
					finish();
					
	    		}
	    		
			}//end if
  
			return null;
		}
		
		protected void onPostExecute(String file_url) {
			pDialog.dismiss();
		}
	}
	
	
	
	public void eventologout(View view) {
		btlogout = (Button) findViewById(R.id.bt_logout);
		btlogout.setBackgroundResource(R.drawable.fundobotao);
		
		/** AlertDialog com Sim e Não **/
		AlertDialog.Builder alerta = new AlertDialog.Builder(this);
		alerta.setCancelable(false);
		
		alerta.setMessage("Deseja realmente fazer logout?");
		
		// Configura Método executado se escolher Sim
		alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,  int id) {
				userFunctions.logoutUser(getApplicationContext());			
				Intent it = new Intent(getBaseContext(), Login.class);
				startActivity(it);
				ID = null;
				finish();
			}
		});
		
		// Configura Método executado se escolher Não
		alerta.setNegativeButton("Não", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,  int id) {
				dialog.cancel();
				btlogout.setBackgroundResource(R.drawable.fundobotaopadrao);
			}
		});
		
		// Exibe o alerta de confirmação
		alerta.show();
	}
	
	public void eventomapa(View view) {
		btmapa = (Button) findViewById(R.id.bt_mapa);
		btmapa.setBackgroundResource(R.drawable.fundobotao);
		
		if(longitude != null && latitude != null){
			Intent it = new Intent(getBaseContext(), Mapa.class);
			it.putExtra("latitude", Double.parseDouble(latitude));
			it.putExtra("longitude", Double.parseDouble(longitude));
			startActivity(it);
		}
		else{
			btmapa.setBackgroundResource(R.drawable.fundobotaopadrao);		
			Toast.makeText(this, "Aguarde a localização", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void eventofoto(View view) { //////////// FOTO ///////////////
		Intent it1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);	
		myFoto = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
        it1.putExtra(MediaStore.EXTRA_OUTPUT, myFoto);
        it1.putExtra(MediaStore.EXTRA_SIZE_LIMIT, 320);
        Caminhofoto = myFoto.getPath();
		startActivityForResult(it1, 100);
	}

	public void eventovideo(View view) { //////////// Video ///////////////
		
        Log.i("API LEVEL",  "api level: " + android.os.Build.VERSION.SDK_INT);       
		
		Intent it2 = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
		it2.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 10);
		it2.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
		it2.putExtra(MediaStore.EXTRA_FINISH_ON_COMPLETION, 0);
		myVideo = getOutputMediaFileUri(MEDIA_TYPE_VIDEO);
		
        it2.putExtra(MediaStore.EXTRA_OUTPUT, myVideo);
		Caminhovideo = myVideo.getPath();
		
		startActivityForResult(it2, 200);
	}

	public void eventoaudio(View view) { //////////// Audio ///////////////
		Intent it3 = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION, null);
		it3.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 10);
		startActivityForResult(it3, 300);
	}
	
	public void eventosync(View v){
		btnsync.setImageResource(R.drawable.sync_icon_blue);
		
		if(conexao == WIFI){
			/** AlertDialog com Sim e Não **/
			AlertDialog.Builder alerta = new AlertDialog.Builder(this);
			alerta.setCancelable(false);
			
			if(contaColaboracao == 1)
				alerta.setMessage("Você possui "+ contaColaboracao +" colaboração não sincronizada. Deseja sincronizá-la agora?");
			else
				alerta.setMessage("Você possui "+ contaColaboracao +" colaborações não sincronizadas. Deseja sincronizá-las agora?");
			
			// Configura Método executado se escolher Sim
			alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,  int id) {
					
					new FuncaoSincronizar().execute();
					
				}
			});
			
			// Configura Método executado se escolher Não
			alerta.setNegativeButton("Não", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,  int id) {
					dialog.cancel();
					btnsync.setImageResource(R.drawable.sync_icon_white);
				}
			});
			
			// Exibe o alerta de confirmação
			alerta.show();
		}
		else{
			Toast.makeText(this, "Necessário conexão WI-FI para sincronizar", Toast.LENGTH_LONG).show();
			btnsync.setImageResource(R.drawable.sync_icon_white);
		}
		
	}
	
	public void eventosalvar(View v){
		
		btsalvar = (Button) findViewById(R.id.bt_salvar);
		btsalvar.setBackgroundResource(R.drawable.fundobotao);
		
		int categoriaPos = Categoria.getSelectedItemPosition() + 1;
    	int subcategoriaPos = Subcategoria.getSelectedItemPosition();
    	
    	if (categoriaPos == 1){
    		subcategoriaPos += 1;
    	}else if(categoriaPos == 2){
    		subcategoriaPos += 4;
    	}else if(categoriaPos == 3 && subcategoriaPos <= 8){
    		subcategoriaPos += 17;
    	}else if(categoriaPos == 3 && subcategoriaPos > 8){ //novidade
    		subcategoriaPos += 18;
    	}else if(categoriaPos == 4){
    		subcategoriaPos += 30;
    	}else if(categoriaPos == 5){
    		subcategoriaPos += 38;
    	}else if(categoriaPos == 6){
    		subcategoriaPos += 45;
    	}else if(categoriaPos == 7){
    		subcategoriaPos += 54;
    	}else if(categoriaPos == 8){
    		subcategoriaPos += 69;
    	}
    	
    	categoria = Integer.toString(categoriaPos);
    	subcategoria = Integer.toString(subcategoriaPos);
    	
    	mytitulo = titulo.getText().toString();
    	myresumo = resumo.getText().toString();
		
		if(longitude == null || latitude == null){
			
			Toast.makeText(this, "Aguarde a localização", Toast.LENGTH_SHORT).show();
			btsalvar.setBackgroundResource(R.drawable.fundobotaopadrao);
			
		}
		else if(mytitulo.length() == 0 || myresumo.length() == 0){

			Toast.makeText(this, "Preencha o título e a descrição", Toast.LENGTH_SHORT).show();
			btsalvar.setBackgroundResource(R.drawable.fundobotaopadrao);

		}
		else{
			
			new FuncaoSalvar().execute();
			
			/*
			reducaoDeFoto();
			
			UserFunctions userFunction = new UserFunctions();    	
	    	
	    	try{
				long idCadastrado = userFunction.Salvar(mytitulo, myresumo, categoria, subcategoria, nomearquivofoto, nomearquivovideo, Caminhofoto, Caminhovideo, latitude, longitude, ID, Colaboracao.this);
		    		    	
				if(idCadastrado > 0){
					Log.i("SALVAR COLABORAÇÃO", "Colaboração foi salva com sucesso...");		
					Toast.makeText(Colaboracao.this, "Colaboração foi salva", Toast.LENGTH_LONG).show();
					
					//OBTENDO VALORES SALVOS
					userFunction.ExibeColaboracaoSalva(idCadastrado, Colaboracao.this);				
					
					Intent it4 = new Intent(Colaboracao.this,Colaboracao.class);
					startActivity(it4);
	
					finish();
				}else{
					Toast.makeText(Colaboracao.this, "Problema ao salvar a colaboração...", Toast.LENGTH_LONG).show();
					Log.e("SALVAR COLABORAÇÃO", "Problema ao tentar salvar a colaboração...");
					btsalvar.setBackgroundResource(R.drawable.fundobotaopadrao);
				}
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		Log.e("SALVAR COLABORAÇÃO", "Problema ao tentar salvar a colaboração... (exeption)");
	    		btsalvar.setBackgroundResource(R.drawable.fundobotaopadrao);
	    	}	    	
	    	*/
			
		}

	}
	
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 100) {
			if (resultCode == RESULT_OK) {
				Toast.makeText(this, "Foto Registrada", Toast.LENGTH_SHORT).show();
				Bitmap imagem1 = BitmapFactory.decodeResource(getResources(), R.drawable.cameraverde);
				foto.setImageBitmap(imagem1);
				foto.setClickable(false);			
							
			} 
			else if (resultCode == RESULT_CANCELED) {
				Caminhofoto = null;
				nomearquivofoto = "";
				Toast.makeText(this, "Foto cancelada", Toast.LENGTH_SHORT).show();
			}
		}

		else if (requestCode == 200) {
			if (resultCode == RESULT_OK) {				
				Toast.makeText(this, "Vídeo Registrado", Toast.LENGTH_SHORT).show();
				Bitmap imagem2 = BitmapFactory.decodeResource(getResources(), R.drawable.videoverde);
				video.setImageBitmap(imagem2);
				video.setClickable(false);
			} 
			else if (resultCode == RESULT_CANCELED) {
				Caminhovideo = null;
				nomearquivovideo = "";
				Toast.makeText(this, "Vídeo cancelado", Toast.LENGTH_SHORT).show();
			}
		}
		/*
		else if (requestCode == 300) {
			if (resultCode == RESULT_OK) {
				Toast.makeText(this, "çUDIO OK", Toast.LENGTH_LONG).show();
				Bitmap imagem3 = BitmapFactory.decodeResource(getResources(), R.drawable.micverde);
				audio.setImageBitmap(imagem3);
				audio.setClickable(false);
			} 
			else if (resultCode == RESULT_CANCELED) {
				Toast.makeText(this, "CANCELOU O AUDIO", Toast.LENGTH_LONG).show();
				
			}
		}
		*/
	}
	
	public class DeletaFoto implements Runnable {
		  public void run () {			
			  
				//LIMPA A REFERENCIA DO BANCO PARA A FOTO NÃO ENVIADA
				UserFunctions userFunction2 = new UserFunctions(); 
				
				JSONObject json = userFunction2.AtualizarColaboracao(nomearquivofoto,"foto", TIMEOUT_CONNECTION, TIMEOUT_SOCKET);
	    	
				try {
					String res = json.getString(KEY_SUCCESS); 
					if(Integer.parseInt(res) == 1){				
						Log.i("DELETAR MULTIMIDIA", "Foto deletada com sucesso! (AsyncTask)");
					}
				}catch (Exception e) {
					e.printStackTrace();				
					Log.i("DELETAR MULTIMIDIA", "Serviço indisponível ao tentar apagar Foto! (AsyncTask)");
				}
				//-------------------------------------------------	
		  }
	}
	
	public class DeletaVideo implements Runnable {
		  public void run () {			
			  
				//LIMPA A REFERENCIA DO BANCO PARA O VIDEO NÃO ENVIADO
				UserFunctions userFunction3 = new UserFunctions(); 
				
				JSONObject json = userFunction3.AtualizarColaboracao(nomearquivovideo,"video", TIMEOUT_CONNECTION, TIMEOUT_SOCKET);
	    	
				try {
					String res2 = json.getString(KEY_SUCCESS); 
					if(Integer.parseInt(res2) == 1){				
						Log.i("DELETAR MULTIMIDIA", "Video deletado com sucesso! (AsyncTask)");
					}
				}catch (Exception e) {
					e.printStackTrace();				
					Log.i("DELETAR MULTIMIDIA", "Serviço indisponível ao tentar apagar Video! (AsyncTask)");
				}
				//----------------------------------------------------
		  }
	}

	@Override
	public void onLocationChanged(Location loc) {
		Log.i("LOCATION", "onLocationChanged() " + loc);
		if(loc != null){
			Log.i("LOCATION", "Obteve localização. Latitude: "+ loc.getLatitude() + " Longitude: " + loc.getLongitude());
			//Toast.makeText(this, "Obteve localização. Latitude: "+ loc.getLatitude() + " Longitude: " + loc.getLongitude(), Toast.LENGTH_SHORT).show();
			
			latitude = Double.toString(loc.getLatitude());
			longitude = Double.toString(loc.getLongitude());
			TextView tvlat = (TextView) findViewById(R.id.tv_lat);
			TextView tvLong = (TextView) findViewById(R.id.tv_long);
			tvlat.setText("Latitude: " + latitude);
			tvLong.setText("Longitude: " + longitude);
		}		
	}
	
	public void onProviderDisabled(String provider) {
		Log.d("LOCATION", "Desabilitou o provedor");
	}

	public void onProviderEnabled(String provider) {
		Log.d("LOCATION", "Habilitou o provedor");
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		Log.d("LOCATION", "Provedor mudou de estado");
	}

	/*@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		Log.d("LOCATION", "Falha na conexão Google Play Service");	
	}

	@Override
	public void onConnected(Bundle arg0) {
		Log.d("LOCATION", "Google Play Service Conectado");	
		
		locationrequest = LocationRequest.create();
		locationrequest.setInterval(5000);
		locationclient.requestLocationUpdates(locationrequest, this);
	}

	@Override
	public void onDisconnected() {
		Log.d("LOCATION", "Google Play Service Desconectado");		
	}*/

}
