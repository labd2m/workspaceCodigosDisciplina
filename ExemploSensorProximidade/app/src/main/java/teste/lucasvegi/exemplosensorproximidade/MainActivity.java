package teste.lucasvegi.exemplosensorproximidade;

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
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor sensor;
    private SeekBar barra;
    private TextView alcance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_main);
        //Obtem o gerenciador de sensores
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //Obtem sensor
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        //obtem referências de views
        barra = (SeekBar) findViewById(R.id.progresso);
        alcance = (TextView) findViewById(R.id.maximo);

        if(sensor != null){
            //obtem o valor maximo que pode ser retornado pelo sensor
            float max = sensor.getMaximumRange();
            //atualiza o máximo da barra e alcance
            barra.setMax((int) max);
            alcance.setText("Alcance Máximo: " + max + " cm");
        }else{
            Toast.makeText(this, "Sensor inexistente", Toast.LENGTH_LONG).show();
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
        if(sensor != null) {
            Log.i("SENSOR_PAROU", sensor.getName());
        }
    }

    public void clickTeste(View v){
        Toast.makeText(this, "CLIQUEI", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //lê proximidade em centímetros
        float valor = event.values[0];
        //atualiza o TextView com proximidade
        TextView txt = (TextView) findViewById(R.id.valor);
        txt.setText("Proximidade: " + valor + " cm");
        //atualiza o SeekBar
        barra.setProgress((int) valor);
        Log.i("SENSOR_MUDOU", "VALOR: " + valor + " cm");

        //Obtem parâmetros do layout da tela
        WindowManager.LayoutParams params = this.getWindow().getAttributes();
        params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;  //mantem tela ligada

        if(valor <= 3.0){
            //brilho minimo
            params.screenBrightness = 0;
            this.getWindow().setAttributes(params);
            //desabilita o touch da tela
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            //desabilita barras de status e navegação
            this.getWindow().getDecorView().
                    setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE //mantem dimensões
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION //mantem dimensões
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN //mantem dimensões
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION //nav bar
                            | View.SYSTEM_UI_FLAG_FULLSCREEN    //status bar
                            | View.SYSTEM_UI_FLAG_IMMERSIVE);  //evita volta no toque
            Log.e("SENSOR_MUDOU", "PERTO");
        } else {
            //brilho original
            params.screenBrightness = -1;
            this.getWindow().setAttributes(params);
            //habilita o touch da tela
            this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            //habilita barras de status e navegação
            this.getWindow().getDecorView().
                    setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            Log.e("SENSOR_MUDOU", "LONGE");
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
