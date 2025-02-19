package teste.lucasvegi.exemplomultisensores;

import android.app.Activity;
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
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor sensorP;
    private Sensor sensorL;
    private SeekBar barraP;
    private SeekBar barraL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_main);
        //Obtem o gerenciador de sensores
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //Obtem sensores
        sensorP = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensorL = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        //obtem referências de views
        barraP = (SeekBar) findViewById(R.id.progressoP);
        barraL = (SeekBar) findViewById(R.id.progressoL);

        if(sensorP != null && sensorL != null){
            //atualiza o máximo das barras
            float max = sensorP.getMaximumRange();
            barraP.setMax((int) max);
            max = sensorL.getMaximumRange();
            barraL.setMax((int) max);
        }else{
            Toast.makeText(this, "Sensores inexistentes", Toast.LENGTH_LONG).show();
        }
    }

    protected void onResume() {
        super.onResume();
        if(sensorP != null && sensorL != null){
            //Começa a escutar os sensores utilizados
            sensorManager.registerListener(this,sensorP,SensorManager.SENSOR_DELAY_GAME);
            sensorManager.registerListener(this,sensorL,SensorManager.SENSOR_DELAY_GAME);
            Log.i("SENSOR_INICIOU", sensorP.getName() + " e " + sensorL.getName());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Para de escutar os sensores
        sensorManager.unregisterListener(this);
        Log.i("SENSOR_PAROU", "Parou sensores");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //atualiza o TextViews
        TextView txtP = (TextView) findViewById(R.id.valorP);
        TextView txtL = (TextView) findViewById(R.id.valorL);

        Sensor s = event.sensor; //obtem o sensor que mudou
        Log.i("SENSOR_MUDOU", "Tipo: " + s.getStringType());

        if (s.getType() == Sensor.TYPE_PROXIMITY) {
            Log.i("SENSOR_MUDOU", "Proximidade: " + event.values[0] + " cm.");
            //atualiza o TextView e SeekBar
            txtP.setText("Proximidade: " + event.values[0] + " cm.");
            barraP.setProgress((int) event.values[0]);

        }
        else if(s.getType() == Sensor.TYPE_LIGHT){
            Log.i("SENSOR_MUDOU", "Luz: " + event.values[0] + "lx.");
            //atualiza o TextView e SeekBar
            txtL.setText("Luz: " + event.values[0] + "lx.");
            barraL.setProgress((int) event.values[0]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //chamado quando o sensor muda de precisão
        String prec = "";
        switch (accuracy) {
            case SensorManager.SENSOR_STATUS_ACCURACY_LOW:
                prec = "Baixa";
                break;
            case SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM:
                prec = "Média";
                break;
            case SensorManager.SENSOR_STATUS_ACCURACY_HIGH:
                prec = "Alta";
                break;
            case SensorManager.SENSOR_STATUS_UNRELIABLE:
                prec = "Sinal indisponível";
                break;
            default:
        }
        Log.i("SENSOR_PRECISAO", "NOME: " + sensor.getName() + " Precisão: " + prec);
    }
}
