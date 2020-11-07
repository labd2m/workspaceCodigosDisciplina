package pacote.cidadaovicosa;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.json.JSONObject;

//import com.google.analytics.tracking.android.EasyTracker;

import pacote.ferramentas.DatabaseHandler;
import pacote.ferramentas.UserFunctions;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {
	
	public static final  int SOBRE  = 0;
	public static final  int SAIR  = 1;
	
	public int TIMEOUT_CONNECTION;
	public int TIMEOUT_SOCKET;
	
	Button btnLogin;
	Button btnLinkToRegister;
	EditText inputEmail;
	EditText inputPassword;
	TextView loginErrorMsg;
	
	private ProgressDialog pDialog;
	
	public Handler handler = new Handler();

	private static String KEY_SUCCESS = "success";
	private static String KEY_ID = "id";
	private static String KEY_NAME = "name";
	private static String KEY_EMAIL = "email";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_login);
		
		btnLogin = (Button) findViewById(R.id.bt_loginLogin);
		inputEmail = (EditText) findViewById(R.id.edt_loginEmail);
		inputPassword = (EditText) findViewById(R.id.edt_loginSenha);
		btnLinkToRegister = (Button) findViewById(R.id.bt_loginCadastro);
		
	}
	
	protected void onResume() {
		super.onResume();
		btnLogin.setBackgroundResource(R.drawable.fundobotaopadrao);		
		
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
		//EasyTracker.getInstance(this).activityStart(this);
	}
	
	@Override
	public void onStop() {
	    super.onStop();
	   
	    //Google Analytics
	    //EasyTracker.getInstance(this).activityStop(this);  // Add this method.
	}
	
	public void eventologinlogin (View view){
		
		if(inputEmail.getText().toString().length() == 0 || inputPassword.getText().toString().length() == 0){
			Toast.makeText(this, "Preencha o e-mail e a senha", Toast.LENGTH_SHORT).show();
		}else{
			btnLogin.setBackgroundResource(R.drawable.fundobotao);
			new FuncaoLogin().execute();
		}
	}

	public void eventologincadastro (View view){	
		Intent i = new Intent(getApplicationContext(),Cadastro.class);
		startActivity(i);
		finish();
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
	
	class FuncaoLogin extends AsyncTask<String, String, String> {
		String email;
		String password;

		/////// Before starting background thread Show Progress Dialog ///////
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Login.this);
			pDialog.setMessage("Logando...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();

			email = inputEmail.getText().toString();
			password = inputPassword.getText().toString();
			
		}

		protected String doInBackground(String... args) {
			//String email = inputEmail.getText().toString();
			//String password = inputPassword.getText().toString();
			UserFunctions userFunction = new UserFunctions();
			Log.d("Button", "Login");
			
			JSONObject json = userFunction.loginUser(email, password, TIMEOUT_CONNECTION, TIMEOUT_SOCKET);		
			try {
				String res = json.getString(KEY_SUCCESS); 
				if(Integer.parseInt(res) == 1){

					DatabaseHandler db = new DatabaseHandler(getApplicationContext());
												
					userFunction.logoutUser(getApplicationContext());
						
					db.addUser(json.getString(KEY_NAME), json.getString(KEY_EMAIL),json.getString(KEY_ID));						

					Intent it = new Intent(getApplicationContext(), Colaboracao.class);
						
					it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						
					startActivity(it);
						
					finish();
				}
				else{
					
					handler.post(new Runnable() {
						public void run() {
							btnLogin.setBackgroundResource(R.drawable.fundobotaopadrao);
							Toast.makeText(Login.this, "Login inválido", Toast.LENGTH_SHORT).show();
						}
					});
				}
			}catch (Exception e) {
				e.printStackTrace();
				handler.post(new Runnable() {
					public void run() {
						btnLogin.setBackgroundResource(R.drawable.fundobotaopadrao);
						Toast.makeText(Login.this, "Serviço indisponível no momento...", Toast.LENGTH_SHORT).show();
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
