package teste.lucasvegi.exemplosensoracelerometro;

import android.app.Activity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Obtem gerenciador de sensores
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //Obtem sensores a serem utilizados
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(sensor != null){
            //Começa a escutar os sensores utilizados
            sensorManager.registerListener(this, sensor,SensorManager.SENSOR_DELAY_NORMAL);
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
        WindowManager.LayoutParams params = this.getWindow().getAttributes();
        params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;  //mantem tela ligada
        this.getWindow().setAttributes(params);

        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        Log.i("SENSOR_MUDOU", "Valores: \nX: " + x + "\nY: " + y + "\nZ: " + z);
        if(y > 12.0)
            Log.e("SENSOR_MUDOU", "Valores: \nX: " + x + "\nY: " + y + "\nZ: " + z);

        ((TextView) findViewById(R.id.valorX)).setText(x + " m/s²");    //atualiza na tela valor de X
        ((TextView) findViewById(R.id.valorY)).setText(y + " m/s²");    //atualiza na tela valor de Y
        ((TextView) findViewById(R.id.valorZ)).setText(z + " m/s²");    //atualiza na tela valor de Z
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        //mudou a precisão
    }
}

