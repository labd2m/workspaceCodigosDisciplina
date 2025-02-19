package com.example.exemploalarmenotification;

import android.os.Bundle;
import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class ExibeNotificacao extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exibe_notificacao);
		
		//RECUPERA INTENT ENVIADA AO ANDROID QUANDO USUÁRIO CLICA PARA VISUALIZAR DETALHES DA NOTIFICAÇÃO
		Intent it = getIntent();
		
		if(it != null){	
			//APAGA DA BARRA DE STATUS A NOTIFICAÇÃO EXIBIDA EM DETALHES
			NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
			String TAG = it.getStringExtra("TAG");
			int ID = it.getIntExtra("ID", 0);
			nm.cancel(TAG, ID);
			
			//EXIBE DETALHES DA NOTIFICAÇÃO EM UM TEXTVIEW
			TextView txt = (TextView) findViewById(R.id.msgNotificacao);	
			txt.setText("BARRA: " + it.getStringExtra("msgBarra"));
			txt.append("\nTÍTULO: " + it.getStringExtra("titulo"));
			txt.append("\nCORPO: " + it.getStringExtra("msgCorpo"));
			txt.append("\nID: " + ID);
			txt.append("\nTAG: " + TAG);
			
			Log.i("Alarme","Abrindo a activity com detalhes da notificação de ID: " + ID + " TAG: " + TAG);
		}
	}

}
