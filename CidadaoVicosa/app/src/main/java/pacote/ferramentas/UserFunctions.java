package pacote.ferramentas;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import pacote.cidadaovicosa.Colaboracao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class UserFunctions{
	
	private JSONParser jsonParser;
		
	private static String LOGIN = "login";
	private static String REGISTER = "register";
	private static String COLABORAR = "colaborar";
	private static String SINCRONIZAR = "sincronizar";
	private static String MAPA = "mapa";
	private static String ATUALIZAR = "atualizar";
	
	private static String URL = "http://200.235.131.170:8008/cidadaovicosa/android_index.php";
	
	public UserFunctions(){
		jsonParser = new JSONParser();
	}

	public JSONObject loginUser(String email, String password){

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("tag", LOGIN));
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("password", password));
		
		//Log.i("JASON","TAG:"+LOGIN+" email: "+email+" password: "+password);
		
		JSONObject json = jsonParser.getJSONFromUrl(URL, params);
				
		return json;
	}
	
	public JSONObject loginUser(String email, String password, int timeConnection, int timeSocket){

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("tag", LOGIN));
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("password", password));
		
		//Log.i("JASON","TAG:"+LOGIN+" email: "+email+" password: "+password);
		
		JSONObject json = jsonParser.getJSONFromUrl(URL, params, timeConnection, timeSocket);
				
		return json;
	}

	public JSONObject registerUser(String name, String email, String password, String faixa){

		List<NameValuePair> params = new ArrayList<NameValuePair>();

		params.add(new BasicNameValuePair("tag", REGISTER));
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("password", password));
		params.add(new BasicNameValuePair("faixa", faixa));
		
		JSONObject json = jsonParser.getJSONFromUrl(URL, params);
		return json;
	}
	
	public JSONObject registerUser(String name, String email, String password, String faixa, int timeConnection, int timeSocket){

		List<NameValuePair> params = new ArrayList<NameValuePair>();

		params.add(new BasicNameValuePair("tag", REGISTER));
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("password", password));
		params.add(new BasicNameValuePair("faixa", faixa));
		
		JSONObject json = jsonParser.getJSONFromUrl(URL, params, timeConnection, timeSocket);
		return json;
	}
	
	public JSONObject Colaborar(String titulo, String resumo, String categoria, String subcategoria,
								String foto, String video, String latitude, String longitude, String Id){
	
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("tag", COLABORAR));
		params.add(new BasicNameValuePair("titulo", titulo));
		params.add(new BasicNameValuePair("resumo", resumo));
		params.add(new BasicNameValuePair("categoria", categoria));
		params.add(new BasicNameValuePair("subcategoria", subcategoria));
		params.add(new BasicNameValuePair("foto", foto));
		params.add(new BasicNameValuePair("video", video));
		params.add(new BasicNameValuePair("latitude", latitude));		
		params.add(new BasicNameValuePair("longitude", longitude));
		params.add(new BasicNameValuePair("id", Id));
		
		JSONObject json = jsonParser.getJSONFromUrl(URL, params);
		return json;
	}
	
	public JSONObject Colaborar(String titulo, String resumo, String categoria, String subcategoria,
			String foto, String video, String latitude, String longitude, String Id, int timeConnection, int timeSocket){

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("tag", COLABORAR));
		params.add(new BasicNameValuePair("titulo", titulo));
		params.add(new BasicNameValuePair("resumo", resumo));
		params.add(new BasicNameValuePair("categoria", categoria));
		params.add(new BasicNameValuePair("subcategoria", subcategoria));
		params.add(new BasicNameValuePair("foto", foto));
		params.add(new BasicNameValuePair("video", video));
		params.add(new BasicNameValuePair("latitude", latitude));		
		params.add(new BasicNameValuePair("longitude", longitude));
		params.add(new BasicNameValuePair("id", Id));
		
		JSONObject json = jsonParser.getJSONFromUrl(URL, params, timeConnection, timeSocket);
		return json;
	}
	
	public JSONObject Sincronizar(String titulo, String resumo, String categoria, String subcategoria,
			String foto, String video, String latitude, String longitude, String Id, String dtOcorrencia, String hrOcorrencia, int timeConnection, int timeSocket){

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("tag", SINCRONIZAR));
		params.add(new BasicNameValuePair("titulo", titulo));
		params.add(new BasicNameValuePair("resumo", resumo));
		params.add(new BasicNameValuePair("categoria", categoria));
		params.add(new BasicNameValuePair("subcategoria", subcategoria));
		params.add(new BasicNameValuePair("foto", foto));
		params.add(new BasicNameValuePair("video", video));
		params.add(new BasicNameValuePair("latitude", latitude));		
		params.add(new BasicNameValuePair("longitude", longitude));
		params.add(new BasicNameValuePair("id", Id));
		params.add(new BasicNameValuePair("dt_ocorrencia", dtOcorrencia));
		params.add(new BasicNameValuePair("hr_ocorrencia", hrOcorrencia));
		
		JSONObject json = jsonParser.getJSONFromUrl(URL, params, timeConnection, timeSocket);
		return json;
	}
	
	public long Salvar(String titulo, String resumo, String categoria, String subcategoria,
			String foto, String video, String URIfoto, String URIvideo, String latitude, String longitude, String Id, Context ctx){
		
		String dtOcorrencia = new SimpleDateFormat("yyyy/MM/dd", Locale.US).format(new Date());
		String hrOcorrencia = new SimpleDateFormat("HH:mm:ss", Locale.US).format(new Date());
		
		BancoDados bd = new BancoDados(ctx);
		
    	ContentValues valores = new ContentValues();
    	valores.put("titulo", titulo);
    	valores.put("resumo", resumo);
    	valores.put("categoria", categoria);
    	valores.put("subcategoria", subcategoria);
    	valores.put("foto", foto);
    	valores.put("video", video);
    	valores.put("urifoto", URIfoto);
    	valores.put("urivideo", URIvideo);
    	valores.put("latitude", latitude);
    	valores.put("longitude", longitude);
    	valores.put("idusuario", Id);
    	valores.put("dataocorrencia", dtOcorrencia);
    	valores.put("horaocorrencia", hrOcorrencia);
    	valores.put("sync", 0);
    	
    	long idColaboracao = bd.inserir("colaboracao", valores);
    	bd.fechar();

		return idColaboracao;
	}
	
	public int contaColaboracoesNaoSincronizadas(Context ctx){

		BancoDados bd = new BancoDados(ctx); 
		Cursor c = bd.buscar("colaboracao c", new String[]{"c.id id"}, "c.sync = 0", "c.id asc");
		
		int qtd = c.getCount();
		
    	Log.d("SINCRONIZAÇÃO", "Existem " + qtd + " colaborações não sincronizados!");
    	c.close();
    	bd.fechar();
		    
		return qtd;
		
	}
	
	public int deletaColaboracaoSincronizada(Context ctx, int id){
		BancoDados bd = new BancoDados(ctx); 
		int excluido = bd.deletar("colaboracao", "id = " + id);
		
    	Log.d("SINCRONIZAÇÃO", excluido + " colaboração sincronizada foi excluida!");
    	bd.fechar();
		    
		return excluido;
	}
	
	public Cursor recuperaColaboracoesNaoSincronizadas(Context ctx){

		BancoDados bd = new BancoDados(ctx); 
	 	Cursor c = bd.buscar("colaboracao c", new String[]{"c.id id",
	 														"c.titulo titulo",
	 														"c.resumo resumo",
	 														"c.categoria categoria",
	 														"c.subcategoria subcategoria",
	 														"c.foto foto",
	 														"c.video video",
	 														"c.urifoto urifoto",
	 														"c.urivideo urivideo",
	 														"c.latitude latitude",
	 														"c.longitude longitude",
	 														"c.idusuario idusuario",
	 														"c.dataocorrencia dataocorrencia",
	 														"c.horaocorrencia horaocorrencia",
	 														"c.sync sync"}, "c.sync = 0", "c.id asc");
        	
	 	Log.d("SINCRONIZAÇÃO", "Existem " + c.getCount() + " colaborações não sincronizados!");
	 	bd.fechar();
		
	 	return c;	
	}
	
	public void ExibeColaboracaoSalva(long idCadastrado, Context ctx){
		//TESTE PARA EXIBIR REGISTRO CADASTRADO
		BancoDados bd = new BancoDados(ctx); 
	 	Cursor c = bd.buscar("colaboracao c", new String[]{"c.id id",
	 														"c.titulo titulo",
	 														"c.resumo resumo",
	 														"c.categoria categoria",
	 														"c.subcategoria subcategoria",
	 														"c.foto foto",
	 														"c.video video",
	 														"c.urifoto urifoto",
	 														"c.urivideo urivideo",
	 														"c.latitude latitude",
	 														"c.longitude longitude",
	 														"c.idusuario idusuario",
	 														"c.dataocorrencia dataocorrencia",
	 														"c.horaocorrencia horaocorrencia",
	 														"c.sync sync"}, "c.id = "+idCadastrado, "c.id asc");
		String aux= "";
    	
		if(c.getCount() > 0){
        	
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
        		
        		aux += "ID: " + c.getInt(indexID) + 
        				"\nTITULO: " + c.getString(indexTitulo) + 
        				"\nRESUMO: " + c.getString(indexResumo) + 
        				"\nCATEGORIA: " + c.getString(indexCategoria) + 
        				"\nSUBCATEGORIA: " + c.getString(indexSubcategoria) + 
        				"\nFOTO: " + c.getString(indexFoto) + 
        				"\nVIDEO: " + c.getString(indexVideo) + 
        				"\nURI FOTO: " + c.getString(indexURIfoto) + 
        				"\nURI VIDEO: " + c.getString(indexURIvideo) + 
        				"\nLATITUDE: " + c.getString(indexLatitude) + 
        				"\nLONGITUDE: " + c.getString(indexLongitude) + 
        				"\nID USUARIO: " + c.getString(indexIdUsuario) + 
        				"\nDATA DE OCORRENCIA: " + c.getString(indexDataOcorrencia) + 
        				"\nHORA DE OCORRENCIA: " + c.getString(indexHoraOcorrencia) + 
        				"\nSYNC: " + c.getInt(indexSync) + "\n\n";	        	
        	}
        	
        	Log.d("SALVAR COLABORAÇÃO", aux);
        	c.close();
        	bd.fechar();
		}
	}
	
	public JSONObject AtualizarColaboracao(String nomeMultimidia, String tipo, int timeConnection, int timeSocket){

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("tag", ATUALIZAR));
		params.add(new BasicNameValuePair("multimidia", nomeMultimidia));
		params.add(new BasicNameValuePair("tipo", tipo));
		
		Log.i("DELETAR MULTIMIDIA", "Tentando deletar " + tipo);
		JSONObject json = jsonParser.getJSONFromUrl(URL, params, timeConnection, timeSocket);
		return json;
	}
	
	
	public JSONObject Pontos(){

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("tag", MAPA));
	    
		JSONObject json = jsonParser.getJSONFromUrl(URL, params);
		return json;
	}
	
	public JSONObject Pontos(int timeConnection, int timeSocket){

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("tag", MAPA));
	    
		JSONObject json = jsonParser.getJSONFromUrl(URL, params, timeConnection, timeSocket);
		return json;
	}
	
	public boolean isUserLoggedIn(Context context){
		DatabaseHandler db = new DatabaseHandler(context);
		int count = db.getRowCount();
		if(count > 0){
			return true;
		}
		return false;
	}

	public boolean logoutUser(Context context){
		DatabaseHandler db = new DatabaseHandler(context);
		db.resetTables();
		return true;
	}
	
	public String recuperaID(Context context){
		DatabaseHandler db = new DatabaseHandler(context);
		HashMap<String, String> tabela = db.getUserDetails();
		String id = tabela.get("uid");
		return id;
	}
	
	public String RecuperaEmail(Context context){
		DatabaseHandler db = new DatabaseHandler(context);
		HashMap<String, String> tabela = db.getUserDetails();
		String email = tabela.get("email");
		return email;
	}

}
