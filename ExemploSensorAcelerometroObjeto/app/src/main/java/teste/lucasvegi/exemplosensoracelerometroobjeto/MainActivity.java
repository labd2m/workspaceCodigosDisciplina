package teste.lucasvegi.exemplosensoracelerometroobjeto;

import android.app.Activity;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor sensor;
    public ImageView img;
    public int dimenX;  //dimensao horizontal da tela em pixel
    public int dimenY;  //dimensao vertical da tela em pixel
    public float centerX;   //centro horizontal ajustado
    public float centerY;   //centro vertical ajustado
    public float escalaX;   //usada para converter leituras em pixel
    public float escalaY;   //usada para converter leituras em pixel

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Obtem gerenciador de sensores
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //Obtem sensores a serem utilizados
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //Obtem a resolução da tela
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        dimenX = size.x;
        dimenY = size.y;

        Toast.makeText(this, "X: " + dimenX + "px Y: " + dimenY + "px", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(sensor != null){
            //Começa a escutar os sensores utilizados
            sensorManager.registerListener(this, sensor,SensorManager.SENSOR_DELAY_GAME);
        }

        img = (ImageView) findViewById(R.id.imagem);

        //garante só rodar após as views estarem na tela
        img.post(new Runnable() {
            @Override
            public void run() {
                //obtem o centro da tela
                centerX = dimenX / 2 - (img.getMeasuredWidth() / 2);
                centerY = dimenY / 2 - (img.getMeasuredHeight() / 2);

                //calcula a escala
                escalaX = centerX / (float) 9.81;
                escalaY = centerY / (float) 9.81;

                //coloca o objeto no centro
                img.setX(centerX);
                img.setY(centerY);

                Log.i("Dimensão", "X: " + dimenX + " Y: " + dimenY + " CX: " + centerX + " CY: " + centerY +
                        " IMG_X: " + img.getMeasuredWidth() + " IMG_Y: " + img.getMeasuredHeight());
            }
        });

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

        //Leitura de valores acelerômetro - USAR QUANDO ORIENTAÇÃO FOR TRAVADA EM PORTRAIT
        //float x = event.values[0];
        //float y = event.values[1];

        //Compensa a rotação do aparelho - USAR QUANDO ORIENTAÇÃO FOR FULLSENSOR
        float values[] = SensorUtil.compensaAcelerometro(this,event);
        float x = values[0];
        float y = values[1];

        Log.i("Rotação", "Rotação: " + SensorUtil.getRotationString(this));

        //calcula posição nova
        float xNovo = centerX - (escalaX * x);
        float yNovo = centerY + (escalaY * y);

        //obtem a posição anterior
        float xAnterior = img.getX();
        float yAnterior = img.getY();

        //Deixando movimentação mais suave
        xNovo = xAnterior + ( (xNovo - xAnterior) * (float)0.2 );
        yNovo = yAnterior + ( (yNovo - yAnterior) * (float)0.2 );

        Log.i("Posição", "X: " + xNovo + " Y: " + yNovo);

        img.setX(xNovo);
        img.setY(yNovo);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        //mudou a precisão
    }
}


