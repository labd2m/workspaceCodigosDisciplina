package teste.lucasvegi.exemplosensorgiroscopio;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor sensor;
    private TextView eixoX;
    private TextView eixoY;
    private TextView eixoZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Obtem gerenciador de sensores
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //Obtem sensores a serem utilizados
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        //Recupera referências para as views
        eixoX = (TextView) findViewById(R.id.eixoX);
        eixoY = (TextView) findViewById(R.id.eixoY);
        eixoZ = (TextView) findViewById(R.id.eixoZ);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(sensor != null){
            //Começa a escutar os sensores utilizados
            sensorManager.registerListener(this, sensor,SensorManager.SENSOR_DELAY_GAME);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Para de escutar os sensores
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //Leitura de valores giroscópio - USAR QUANDO ORIENTAÇÃO FOR TRAVADA EM PORTRAIT
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        //atualiza as views
        eixoX.setText(x + " rad/s");
        eixoY.setText(y + " rad/s");
        eixoZ.setText(z + " rad/s");

        Log.i("SENSOR","x: " + x + " y: " + y + " z: " + z);

        if(y > 3)
            Log.w("SENSOR","x: " + x + " y: " + y + " z: " + z);
        if(y < -3)
            Log.e("SENSOR","x: " + x + " y: " + y + " z: " + z);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
