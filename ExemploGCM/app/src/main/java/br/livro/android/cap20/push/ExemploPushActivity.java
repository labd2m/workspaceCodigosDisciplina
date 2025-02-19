package br.livro.android.cap20.push;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

/**
 * @author Ricardo Lecheta
 *
 */
public class ExemploPushActivity extends Activity {
	private static final String TAG = "gcm";
	// Receiver para receber a menagem do Service por Intent
	private final BroadcastReceiver mensagemReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String msg = intent.getExtras().getString("msg");
			exibirMensagem(msg);
		}
	};


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exemplo_push);

		// Verifica se o Play Services está instalado.
		if (checkPlayServices()) {

			// Configura a tela
			configurarTela();

			// Exibe o status da ativação
			boolean ativado = GCM.isAtivo(getContext());
			if (ativado) {
				String regId = GCM.getRegistrationId(getContext());
				exibirMensagem("Device já registrado: " + regId);
			} else {
				exibirMensagem("Device não registrado, clique no botão Registrar.");
			}

			// Se existe alguma mensagem enviada pela Notification do GCMIntentService, recebe aqui
			String msg = getIntent().getStringExtra("msg");
			if(msg != null) {
				exibirMensagem(msg);
			}

			// Registra o BroadcastReceiver para receber mensagens
			registerReceiver(mensagemReceiver,new IntentFilter("RECEIVER_QUE_VAI_RECEBER_ESTA_MSG"));
		} else {
			// Exiba uma msg pro usuário
			Toast.makeText(this, "Você precisa do Google Play Services!", Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * Verifica se o Google Play Services está instalado
	 */
	private boolean checkPlayServices() {
		int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
		if (resultCode != ConnectionResult.SUCCESS) {
			if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
				int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
				GooglePlayServicesUtil.getErrorDialog(resultCode, this, PLAY_SERVICES_RESOLUTION_REQUEST).show();
			} else {
				Log.e(TAG, "Este aparelho não suporta o Google Play Services.");
				finish();
			}
			return false;
		}
		Log.d(TAG, "CheckPlayServices OK");
		return true;
	}

	private void configurarTela() {
		boolean ativado = GCM.isAtivo(getContext());
		if (ativado) {
			// Já está registrado
			findViewById(R.id.btnRegistrar).setEnabled(false);
			findViewById(R.id.btnCancelar).setEnabled(true);
		} else {
			// Precisa registrar
			findViewById(R.id.btnRegistrar).setEnabled(true);
			findViewById(R.id.btnCancelar).setEnabled(false);
		}
	}

	private Context getContext() {
		return getApplicationContext();
	}

	private void exibirMensagem(String msg) {
		TextView text = (TextView) findViewById(R.id.tMsgRecebida);
		text.append(msg + "\n------------------------\n");
		Log.i(TAG, msg);
	}


	public void Registrar(View v) {
				// E importante executar o "register" em uma Thread ou AsyncTask
				new Thread() {
					public void run() {
						// Registrar
						final String regId = GCM.registrar(getContext(), Constants.PROJECT_NUMBER);
						runOnUiThread(new Runnable() {
							public void run() {
								if (regId != null && regId.trim().length() > 0) {
									// Salve o registration id nas preferências, assim você saberá que este device está ativado
									GCM.saveRegistrationId(getContext(), regId);
									// TODO: Enviar o registration id para o servidor (isso é com você)
									exibirMensagem("RegId: " + regId);
									configurarTela();
								} else {
									exibirMensagem("Erro ao regitrar");
								}
							}
						});
					}
				}.start();
	}


	public void Cancelar(View v) {
				new Thread() {
					public void run() {
						// E importante executar o "unregister" em uma Thread ou AsyncTask
						GCM.cancelar(getContext());
						runOnUiThread(new Runnable() {
							public void run() {
								exibirMensagem("Cancelamento do GCM efetuado.");
								configurarTela();
							}
						});
					}
				}.start();
	}


	@Override
	protected void onDestroy() {
		// Finaliza o receiver e o serviço do GCM
		unregisterReceiver(mensagemReceiver);
		super.onDestroy();
	}

}
