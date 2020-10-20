package pacote.cidadaovicosa;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
//import com.google.android.gms.location.LocationClient;

import pacote.cidadaovicosa.Colaboracao.mudarSpinner;
import pacote.ferramentas.DatabaseHandler;
import pacote.ferramentas.UserFunctions;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Cadastro extends Activity {
	
	public static final  int SOBRE  = 0;
	public static final  int SAIR  = 1;
	
	public int TIMEOUT_CONNECTION;
	public int TIMEOUT_SOCKET;
	
	private String[] faixa = new String[] { "0 - 17", "18 - 25", "26 - 64", "mais de 65"};
	
	Spinner lista;
	Button btnRegister;
	Button btnLinkToLogin;
	EditText inputEmail;
	EditText inputFullName;
	EditText inputPassword;
	TextView registerErrorMsg;
	
	private ProgressDialog pDialog;
	
	public Handler handler = new Handler();
	
	private static String KEY_ID = "id";
	private static String KEY_NAME = "name";
	private static String KEY_EMAIL = "email";
	private static String KEY_SUCCESS = "success";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_cadastro);

		lista = (Spinner) findViewById(R.id.spin_idade);		
		inputFullName = (EditText) findViewById(R.id.edt_cadastroNome);
		inputEmail = (EditText) findViewById(R.id.edt_cadastroEmail);
		inputPassword = (EditText) findViewById(R.id.edt_cadastroSenha);
		btnRegister = (Button) findViewById(R.id.bt_cadastroCadastrar);
		btnLinkToLogin = (Button) findViewById(R.id.bt_cadastroLogin);
		
		//configura adaptador
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, R.layout.meu_spinner_item, faixa);
		adaptador.setDropDownViewResource(R.layout.meu_spinner_item);
		//informa qual é o adaptador
		lista.setAdapter(adaptador);
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		btnRegister.setBackgroundResource(R.drawable.fundobotaopadrao);	
		
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
	
	public void eventocadastrologin(View view) {	
		Intent i = new Intent(getApplicationContext(),Login.class);
		startActivity(i);
		finish();
	}
	
	public boolean validaEmail(String email) {
	    System.out.println("Metodo de validacao de email");
	    Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$"); 
	    Matcher m = p.matcher(email); 
	    if (m.find()){
	    	Log.i("Validação de e-mail", "O email "+email+" e válido");
	    	return true;
	    }
	    else{
	    	Log.i("Validação de e-mail", "O email "+email+" e inválido");
	    	return false;
	    }  
	 }
	
	public void eventocadastro(View view){
		
		if(inputFullName.getText().toString().length() == 0 || inputEmail.getText().toString().length() == 0 || inputPassword.getText().toString().length() == 0){
			Toast.makeText(this, "Preencha o Nome, o e-mail e a senha", Toast.LENGTH_SHORT).show();
		}else if(validaEmail(inputEmail.getText().toString())){
			btnRegister.setBackgroundResource(R.drawable.fundobotao);
			new FuncaoCadastro().execute();
		}else{
			Toast.makeText(this, "E-mail inválido", Toast.LENGTH_SHORT).show();
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super .onCreateOptionsMenu(menu);
			
		menu.add(0, SOBRE, 1, "Sobre").setIcon(R.drawable.get_info);
		menu.add(0, SAIR, 2, "Sair").setIcon(R.drawable.sair);

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
		}
		
		return  false;
	}
	
	class FuncaoCadastro extends AsyncTask<String, String, String> {

		/** Before starting background thread Show Progress Dialog **/
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Cadastro.this);
			pDialog.setMessage("Cadastrando...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		protected String doInBackground(String... args) {
			String name = inputFullName.getText().toString();
			String email = inputEmail.getText().toString();
			String password = inputPassword.getText().toString();
			String faixa = lista.getSelectedItem().toString();
			
			UserFunctions userFunction = new UserFunctions();
			
			JSONObject json = userFunction.registerUser(name, email, password, faixa, TIMEOUT_CONNECTION, TIMEOUT_SOCKET);
			
			try {
				String res = json.getString(KEY_SUCCESS); 
				if(Integer.parseInt(res) == 1){		
					//////////// BD ///////////////
					DatabaseHandler db = new DatabaseHandler(getApplicationContext());						
					userFunction.logoutUser(getApplicationContext());
					db.addUser(json.getString(KEY_NAME), json.getString(KEY_EMAIL),json.getString(KEY_ID));						
						
					////////// CHAMA COLABORAÇÃO ///////////////
					Intent it = new Intent(getApplicationContext(), Colaboracao.class);
					it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(it);
					finish();
				}
				else if(Integer.parseInt(res) == 2){
					handler.post(new Runnable() {
						public void run() {
							btnRegister.setBackgroundResource(R.drawable.fundobotaopadrao);	
							Toast.makeText(Cadastro.this, "Usuário já existente...", Toast.LENGTH_LONG).show();
						}
					});
				}else{
					handler.post(new Runnable() {
						public void run() {
							btnRegister.setBackgroundResource(R.drawable.fundobotaopadrao);	
							Toast.makeText(Cadastro.this, "Cadastro inválido", Toast.LENGTH_LONG).show();
						}
					});
				}

			}catch (Exception e) {
				e.printStackTrace();
				handler.post(new Runnable() {
					public void run() {
						btnRegister.setBackgroundResource(R.drawable.fundobotaopadrao);	
						Toast.makeText(Cadastro.this, "Serviço indisponível no momento...", Toast.LENGTH_LONG).show();
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