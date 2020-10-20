package teste.lucasvegi.exemplosensorgiroscopiototalgraus;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.icu.text.NumberFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends Activity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor sensor;

    public float grauXtotal = 0;
    public float grauYtotal = 0;
    public float grauZtotal = 0;

    public float grauXnovo = 0;
    public float grauYnovo = 0;
    public float grauZnovo = 0;

    public float grauXant = 0;
    public float grauYant = 0;
    public float grauZant = 0;

    private TextView eixoX;
    private TextView eixoY;
    private TextView eixoZ;

    private DecimalFormat nf = new DecimalFormat("0.00");

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
        //mantem tela ligada
        WindowManager.LayoutParams params = this.getWindow().getAttributes();
        params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
        this.getWindow().setAttributes(params);

        //Leitura de valores giroscópio - USAR QUANDO ORIENTAÇÃO FOR TRAVADA EM PORTRAIT
        //Converte radianos para graus
        float x = (float) (event.values[0] * 57.2958);
        float y = (float) (event.values[1] * 57.2958);
        float z = (float) (event.values[2] * 57.2958);

        //obtem graus deslocados desde a ultima medição
        grauXnovo = (float) (x * 0.02); //0.02 segundos devido ao SENSOR_DELAY_GAME
        grauYnovo = (float) (y * 0.02); //0.02 segundos devido ao SENSOR_DELAY_GAME
        grauZnovo = (float) (z * 0.02); //0.02 segundos devido ao SENSOR_DELAY_GAME

        //total de graus deslocados desde o inicio
        grauXtotal += grauXnovo;
        grauYtotal += grauYnovo;
        grauZtotal += grauZnovo;

        //elimina pequenos ruidos do sensor com dispositivo parado
        if (!(grauYtotal > grauYant + 0.03 || grauYtotal < grauYant - 0.03))
            grauYtotal = grauYant;

        //elimina pequenos ruidos do sensor com dispositivo parado
        if (!(grauXtotal > grauXant + 0.03 || grauXtotal < grauXant - 0.03))
            grauXtotal = grauXant;

        //elimina pequenos ruidos do sensor com dispositivo parado
        if (!(grauZtotal > grauZant + 0.03 || grauZtotal < grauZant - 0.03))
            grauZtotal = grauZant;

        //guarda medição para comparar com a proxima
        grauXant = grauXtotal;
        grauYant = grauYtotal;
        grauZant = grauZtotal;

        //atualiza as views
        eixoX.setText(nf.format(grauXtotal) + " º");
        eixoY.setText(nf.format(grauYtotal) + " º");
        eixoZ.setText(nf.format(grauZtotal) + " º");

        Log.i("TaxaGiro", "Taxas de giros - X: " + x + " Y: " + y + " Z: " + z);
        Log.i("GiroNovo", "Graus Novos - X: " + grauXnovo + " Y: " + grauYnovo + " Z: " + grauZnovo);
        Log.i("GiroTotal", "Giro total - X: " + grauXtotal + " Y: " + grauYtotal + " Z: " + grauZtotal);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
