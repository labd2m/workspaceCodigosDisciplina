package teste.lucasvegi.controlebluetooth;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;

import teste.lucasvegi.controlebluetooth.Util.ComunicationController;


public class BluetoothControleActivity extends BluetoothCheckActivity implements ComunicationController.ControleListener, SensorEventListener {
    // Precisa utilizar o mesmo UUID que o servidor utilizou para abrir o socket servidor
    protected static final UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    protected BluetoothDevice device;
    protected ComunicationController controle;
    private Sensor sensorAcelerometro;
    private SensorManager sensorManager;
    private boolean conectou = false;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_bluetooth_controle);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorAcelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Device selecionado na lista
        device = getIntent().getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
        try {
            // Faz a conexão se abriu no modo chat cliente
            if(device != null) {
               
                // Faz a conexão utilizando o mesmo UUID que o servidor utilizou
                BluetoothSocket socket = device.createRfcommSocketToServiceRecord(uuid);

                btfAdapter.cancelDiscovery();
                socket.connect();
                //conectou = true;

                // Inicia o controlador do controle
                controle = new ComunicationController(socket, this);

                //inicia escuta por resposta
                //controle.start();

            }
        } catch (IOException e) {
            error("Erro ao conectar: " + e.getMessage(), e);
            conectou = false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(sensorAcelerometro != null) {
            //Começa a escutar os sensores utilizados
            sensorManager.registerListener(this, sensorAcelerometro, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Para de escutar os sensores
        sensorManager.unregisterListener(this);
    }

    public void buzinar(View v){
        MediaPlayer mp = MediaPlayer.create(this, R.raw.buzina);
        mp.start();
    }

    private void error(final String msg, final IOException e) {
        Log.e("ERRO_CONEXÃO BLUETOOTH", e.getMessage(), e);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onMessageReceived(final String msg) {
        /*Log.d(TAG,"onMessageReceived: " + msg);
        // É chamado numa thread, portanto use o runOnUiThread
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String s = tMsgRecebidas.getText().toString();
                tMsgRecebidas.setText(s + "\n<< " + msg);
            }
        });*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(controle != null) {
            controle.stop();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        WindowManager.LayoutParams params = this.getWindow().getAttributes();
        params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;  //mantem tela ligada
        this.getWindow().setAttributes(params);

        String direcaoLateral = "";
        String direcaoVertical = "";


        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        //OBTEM VARIAÇÕES LATERAIS
        if(y >= -2.5 && y <= 2.5)
            direcaoLateral = "M";
        else if (y < -2.5)
            direcaoLateral = "E";
        else if (y > 2.5)
            direcaoLateral = "D";

        //OBTEM VARIAÇÃO VERTICAIS
        if(z >= -3.5 && z <= 3.5)
            direcaoVertical = "M";
        else if (z < -3.5)
            direcaoVertical = "R";
        else if (z > 3.5)
            direcaoVertical = "F";

        Log.i("DIREÇÃO_BLUETOOTH", "L: " + direcaoLateral + " V: " + direcaoVertical);

        //ENVIA A MENSAGEM
        String msg = direcaoVertical + direcaoLateral;
        try {
            controle.sendMessage(msg);
        } catch (IOException e) {
            error("Erro ao enviar dados: " + e.getMessage(), e);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
