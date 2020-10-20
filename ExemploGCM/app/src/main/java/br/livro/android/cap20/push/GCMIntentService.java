package br.livro.android.cap20.push;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import br.livro.android.utils.ActivityStackUtils;
import br.livro.android.utils.NotificationUtil;

import com.google.android.gms.gcm.GoogleCloudMessaging;

/**
 * IntentService responsável por tratar as mensagens do GCM (Google Cloud
 * Messaging).
 *
 * O método onRegistered é chamado quando o registro no GCM é feito com sucesso.
 * Neste momento temos o registrationId único do device.
 *
 * O método onMessage é chamado quando uma mensagem por push é recebida.
 *
 * @author Ricardo Lecheta
 *
 */
public class GCMIntentService extends IntentService {

	private static final String TAG = "gcm";

	public GCMIntentService() {
		super(Constants.PROJECT_NUMBER);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Bundle extras = intent.getExtras();

		Log.i(TAG, "GcmIntentService.onHandleIntent: " + extras);

		GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);

		// Verifica o tipo da mensagem
		String messageType = gcm.getMessageType(intent);

		if (!extras.isEmpty()) {
			/* O extras.isEmpty() precisa ser chamado para ler o bundle
			 * Verifica o tipo da mensagem, no futuro podemos ter mais tipos
			 */
			if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
				// Erro
				onError(extras);
			} else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
				// Mensagem do tipo normal. Faz a leitura do parâmetro "msg"
				// enviado pelo servidor
				onMessage(extras);
			}
		}

		// Libera o wake lock, que foi bloqueado pela classe
		// "GcmBroadcastReceiver".
		GCMBroadcastReceiver.completeWakefulIntent(intent);
	}

	private void onError(Bundle extras) {
		Log.d(TAG, "Erro: " + extras.toString());
	}

	private void onMessage(Bundle extras) {
		String msg = extras.getString("msg");
		Log.d(TAG, msg);

		// Se a aplicação está aberta, podemos passar a mensagem direto ao receiver
		if (ActivityStackUtils.isMyApplicationTaskOnTop(this)) {
			// Dispara uma Intent para o receiver configurado na Activity
			Intent intent = new Intent("RECEIVER_QUE_VAI_RECEBER_ESTA_MSG");
			intent.putExtra("msg", msg);
			sendBroadcast(intent);
		} else {
			// Se a aplicação estiver fechada, a melhor maneira de se comunicar
			// com o usuário, é por meio de uma notificação
			// Cria a notificação, e informa para abrir a activity de entrada
			Intent intent = new Intent(this, ExemploPushActivity.class);
			intent.putExtra("msg", msg);
			NotificationUtil.generateNotification(this, "Nova mensagem: "+ msg, intent);
		}

	}
}
