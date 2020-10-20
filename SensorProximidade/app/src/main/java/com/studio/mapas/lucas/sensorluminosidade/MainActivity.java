package com.studio.mapas.lucas.sensorluminosidade;

import android.app.Activity;
import android.os.PowerManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

    private PowerManager powerManager;
    private PowerManager.WakeLock wakeLock;
    private int field = 0x00000020; //PowerManager.PROXIMITY_SCREEN_OFF_WAKE_LOCK

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            // Yeah, this is hidden field.
            field = PowerManager.class.getClass().getField("PROXIMITY_SCREEN_OFF_WAKE_LOCK").getInt(null);
        } catch (Throwable ignored) {
        }

        powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(field, getLocalClassName());

        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(wakeLock.isHeld()) {
            wakeLock.release();
            Log.i("Sensor","Sensor de proximidade Desativado");
        }
    }

    public void Ligar(View v){
        if(!wakeLock.isHeld()) {
            wakeLock.acquire();
            Log.i("Sensor", "Sensor de proximidade Ativado");
        }
    }

    public void Desligar(View v){
        if(wakeLock.isHeld()) {
            wakeLock.release();
            Log.i("Sensor", "Sensor de proximidade Desativado");
        }
    }
}
