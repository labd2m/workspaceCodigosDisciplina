package com.example.exemploalarmenotification;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class Principal extends Activity {
	public final int tempoSegundos = 5; //tempo de agendamento em segundos
	public static int codRequest = 0; 	//CODIGO DE IDENTIFICAÇÃO DAS INTENTS AGENDADAS

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);
	}
	
	public void agendar(View v){
		//INTENT AGENDADA PARA ENVIAR AO ANDROID NO FUTURO
		Intent it = new Intent("EXIBIR_ALARME"); 
		it.putExtra("msgBarra", "Mensagem da barra");
		it.putExtra("titulo", "Titulo da mensagem");
		it.putExtra("msgCorpo", "Mensagem do corpo");
		
		//MUDA CÓDIGO DAS INTENTS A CADA AGENDAMENTO
		codRequest++;
		
		//PENDIND PARA DISPARAR O BROADCAST RECEIVER QUE FARÁ A NOTIFICAÇÃO
		PendingIntent p = PendingIntent.getBroadcast(this, codRequest, it, 0);
		
		//PARA EXECUTAR O ALARME APÓS X SEGUNDOS A PARTIR DO MOMENTO ATUAL
		Calendar c = Calendar.getInstance();	
		c.setTimeInMillis(System.currentTimeMillis());
		c.add(Calendar.SECOND, tempoSegundos);
		
		//AGENDA O ALARME PARA DISPARAR INTENT NO FUTURO
		AlarmManager alarme = (AlarmManager) getBaseContext().getSystemService(ALARM_SERVICE);
		long time = c.getTimeInMillis();	
		alarme.set(AlarmManager.RTC_WAKEUP, time, p);
		
		Log.i("Alarme","Alarme agendado para daqui a " + tempoSegundos + " segundos!");
		
	}

}
