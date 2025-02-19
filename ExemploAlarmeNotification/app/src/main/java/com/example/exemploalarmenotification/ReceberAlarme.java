package com.example.exemploalarmenotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ReceberAlarme extends BroadcastReceiver {
	
	public static int ID = 0;  					//ID de cada notificação
	public String tag = "TAG_NOTIFICATION";		//TAG de cada notificação
	
	public ReceberAlarme() {
		//EXECUTA EM SEGUNDO PLANO QUANDO ALARME É AGENDADO
		//EXIBE NOTIFICAÇÃO QUANDO EXECUTADO
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		
		String txtBarra = intent.getStringExtra("msgBarra");
		String titulo = intent.getStringExtra("titulo");
		String msgCorpo = intent.getStringExtra("msgCorpo");
		
		//MUDA ID A CADA NOTIFICAÇÃO
		ID++;
		
		//CHAMA MÉTODO QUE ENCAPSULA CRIAÇÃO DA NOTIFICAÇÃO
		criaNotificacao(context, txtBarra, titulo, msgCorpo, tag, ExibeNotificacao.class);	
	}

	public void criaNotificacao(Context ctx, String msgBarra, String titulo, String msgCorpo, String TAG, Class<?> activity){
		
		//RECUPERA O SERVIÇO DE NOTIFICAÇÕES E CONFIGURA UMA NOTIFICAÇÃO COM IMAGEM, MSG DE BARRA E TEMPO ATUAL
		NotificationManager nm = (NotificationManager) ctx.getSystemService(ctx.NOTIFICATION_SERVICE);
		Notification n = new Notification(R.drawable.alarme, msgBarra, System.currentTimeMillis());
		
		//CONFIGURA A INTENT QUE NAVEGARÁ ATÉ UMA ACTIVITY CASO O USUÁRIO ABRA A NOTIFICAÇÃO
		Intent it = new Intent(ctx, activity);
		it.putExtra("msgBarra", msgBarra);
		it.putExtra("titulo", titulo);
		it.putExtra("msgCorpo", msgCorpo);
		it.putExtra("TAG", TAG);
		it.putExtra("ID", ID);
		
		//PENDIND PARA EXECUTAR A ACTIVITY SE O USUÁRIO SELECIONAR A NOTIFICAÇÃO
		PendingIntent p = PendingIntent.getActivity(ctx, ID, it, 0);
		
		//CONFIGURA DETALHES DA NOTIFICAÇÃO
		n.setLatestEventInfo(ctx, titulo, msgCorpo, p);
		
		//espera 100ms e vibra 250ms. Espera mais 100ms e vibra por mais 500ms. 
		//NECESSÁRIO PERMISSÃO android.permission.VIBRATE
		n.vibrate = new long[] {100, 250, 100, 500}; 
		
		//DISPARA NOTIFICAÇÃO COM (Tag, ID, notification)
		nm.notify(TAG, ID, n);
		
		Log.i("Alarme","Mostrando notificação de ID: " + ID + " TAG: " + TAG);
	}
}
