package teste.lucasvegi.exemplosensorluminosidade;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements SensorEventListener{
    private SensorManager sensorManager;
    private Sensor sensor;
    private SeekBar barra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_main);
        //Obtem o gerenciador de sensores
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //Obtem sensor
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        //obtem referência para a barra de progresso
        barra = (SeekBar) findViewById(R.id.progresso);
        if(sensor != null){
            //obtem o valor maximo que pode ser retornado pelo sensor
            float max = sensor.getMaximumRange();
            Log.i("SENSOR_RANGE", "Range: " + max);
            //atualiza o máximo da barra
            barra.setMax((int) max);
        }else{
            Toast.makeText(this, "Sensor inexistente",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(sensor != null){
            //Começa a escutar os sensores utilizados
            sensorManager.registerListener(this, sensor,SensorManager.SENSOR_DELAY_GAME);
            Log.i("SENSOR_INICIOU", sensor.getName());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Para de escutar os sensores
        sensorManager.unregisterListener(this);
        Log.i("SENSOR_PAROU", sensor.getName());
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //lê o valor da intensidade da luz
        float valor = event.values[0];
        //atualiza o TextView como valor de luminosidade
        TextView txt = (TextView) findViewById(R.id.valor);
        txt.setText("Luz: " + valor);
        //atualiza o SeekBar
        barra.setProgress((int) valor);
        Log.i("SENSOR_MUDOU","VALOR: " + valor + " lx");
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
