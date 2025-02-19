package com.example.exemploalarmenotification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AgendaAlarmsAndroidBoot extends BroadcastReceiver {
	public AgendaAlarmsAndroidBoot() {
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		//SE MÉTODO FOR DEMORADO DEMAIS, ESSE BROADCAST RECEIVER PODE DELEGAR O AGENDAMENTO DE ALARMES
		//PARA UM SERVICE
		Log.i("Alarme","BOOT DO ANDROID TERMINOU E ALARMES PODEM SER AGENDADOS NESSE MOMENTO!");
	}
}
