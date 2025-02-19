package com.studio.mapas.lucas.simulacaomasssagem;

import android.app.Activity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;


public class Principal extends Activity implements SensorEventListener{

    private SensorManager sensorManager;
    private Sensor sensorAcelerometro;
    private Sensor sensorProximidade;
    private int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_principal);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        //Obtem sensores a serem utilizados
        sensorAcelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorProximidade = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(sensorAcelerometro != null && sensorProximidade != null){
            //Começa a escutar os sensores utilizados
            sensorManager.registerListener(this,sensorAcelerometro,SensorManager.SENSOR_DELAY_GAME);      //20 microsegundos
            sensorManager.registerListener(this,sensorProximidade,SensorManager.SENSOR_DELAY_GAME);       //20 microsegundos
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        //Para de escutar os sensores
        sensorManager.unregisterListener(this);
    }

    public void CliqueBotao(View v){
        Toast.makeText(this,"Cliquei no Botão",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        //nada
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        WindowManager.LayoutParams params = this.getWindow().getAttributes();
        params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;  //mantem tela ligada

        Sensor s = sensorEvent.sensor; //obtem o sensor que mudou

        Log.i("onSensorChanged", "Tipo: " + s.getType());

        if (s.getType() == Sensor.TYPE_PROXIMITY) {
            Log.i("onSensorChangedP", "Proximidade: " + sensorEvent.values[0] + " cm. Maximo: " + s.getMaximumRange() + " cm.");

            if(sensorEvent.values[0] <= 3.0){
                params.screenBrightness = 0; //brilho minimo
                this.getWindow().setAttributes(params);
                Log.e("onSensorChangedP", "PERTO");

                enableDisableViewGroup((ViewGroup)findViewById(R.id.gerenciador).getParent(),false);    //TODO: travar também botoes back e home
            } else {
                params.screenBrightness = -1; //brilho original
                this.getWindow().setAttributes(params);
                Log.e("onSensorChangedP","LONGE");

                enableDisableViewGroup((ViewGroup) findViewById(R.id.gerenciador).getParent(), true);   //TODO: travar também botoes back e home
            }
        }
        else if(s.getType() == Sensor.TYPE_ACCELEROMETER){
            Log.i("onSensorChangedA", "Valores: \nX: " + sensorEvent.values[0] + "\nY: " + sensorEvent.values[1] + "\nZ: " + sensorEvent.values[2]);
            ((TextView)findViewById(R.id.valorX)).setText(sensorEvent.values[0]+"");    //atualiza na tela valor de X
            ((TextView)findViewById(R.id.valorY)).setText(sensorEvent.values[1]+"");    //atualiza na tela valor de Y

            if(sensorEvent.values[2]>15.0) {
                ((TextView) findViewById(R.id.valorZ)).setText(sensorEvent.values[2] + "");    //atualiza na tela valor de Z
                ((TextView) findViewById(R.id.valorZ)).setTextColor(Color.RED);

                contador++; //conta compressões - TODO: SUBSTITUIR POR ANALISE DO ARQUIVO DE TEXTO BASEADA NO TEMPO, FREQUENCIA E INTENSIDADE

                ((TextView) findViewById(R.id.txtComp)).setText("Compressões: " + contador); //exibe quantidade de compressões
            }else{
                ((TextView) findViewById(R.id.valorZ)).setText(sensorEvent.values[2] + "");    //atualiza na tela valor de Z
                ((TextView) findViewById(R.id.valorZ)).setTextColor(Color.BLUE);
            }
        }
    }

    //desabilita todos as views
    public static void enableDisableViewGroup(ViewGroup viewGroup, boolean enabled) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = viewGroup.getChildAt(i);
            view.setEnabled(enabled);
            if (view instanceof ViewGroup) {
                enableDisableViewGroup((ViewGroup) view, enabled);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
