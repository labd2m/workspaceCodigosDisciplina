package com.example.lucasvegi.androidcompass;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Compass extends AppCompatActivity implements SensorEventListener {

    private static final int TEST_GRAV = Sensor.TYPE_ACCELEROMETER;
    private static final int TEST_MAG = Sensor.TYPE_MAGNETIC_FIELD;
    private static final int TEST_ORI = Sensor.TYPE_ORIENTATION;
    private final float alpha = (float) 0.8;
    private float gravity[];
    private float magnetic[];
    private float orientation[] = new float[3];

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private Sensor mGeomagnetic;
    private Sensor mOrientation;

    private TextView idX;
    private TextView idY;
    private TextView idZ;
    private TextView txtDegrees;
    private TextView txtDirecao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        mGeomagnetic = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mOrientation = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

        idX = findViewById(R.id.idX);
        idY = findViewById(R.id.idY);
        idZ = findViewById(R.id.idZ);
        txtDegrees = findViewById(R.id.degrees);
        txtDirecao = findViewById(R.id.direcao);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mGeomagnetic, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mOrientation, SensorManager.SENSOR_DELAY_NORMAL);


    }

    @Override
    protected void onStop() {
        super.onStop();

        mSensorManager.unregisterListener(this, mGeomagnetic);
        mSensorManager.unregisterListener(this, mAccelerometer);
        mSensorManager.unregisterListener(this, mOrientation);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;

        if (event.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE) {
            return;
        }else if (sensor.getType() == TEST_GRAV) {
            gravity = event.values;

            // Isolate the force of gravity with the low-pass filter.
            gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
            gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
            gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];


            /*gravity[0] = event.values[0];
            gravity[1] = event.values[1];
            gravity[2] = event.values[2];*/


        } else if (sensor.getType() == TEST_MAG) {
            magnetic = event.values;

            magnetic[0] = event.values[0];
            magnetic[1] = event.values[1];
            magnetic[2] = event.values[2];

        }else if (sensor.getType() == TEST_ORI){
            txtDegrees.setText("Degrees Orientation: " + Math.round(event.values[0]));
        }

        if (gravity != null && magnetic != null){
            float[] R = new float[9];
            float[] I = new float[9];
            boolean success = SensorManager.getRotationMatrix(R, I, gravity, magnetic);

            if (success){
                // orientation contains: azimut, pitch and roll
                SensorManager.getOrientation(R, orientation);

                long grau = Math.round((Math.toDegrees(orientation[0]) + 360) % 360);

                idX.setText("Degrees: " + grau);
                idY.setText("X: " + Math.round((Math.toDegrees(orientation[1]) + 360) % 360));
                idZ.setText("Y: " + Math.round((Math.toDegrees(orientation[2]) + 360) % 360));
                txtDirecao.setText("Direção: " + getDirecao(grau));
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public String getDirecao(long grau){
        String labelDirecao = "";

        if ((grau >= 0 && grau <= 15) || grau >= 345)
            labelDirecao = "N";
        else if (grau >= 30 && grau <= 60)
            labelDirecao = "NE";
        else if (grau >= 75 && grau <= 105)
            labelDirecao = "L";
        else if (grau >= 120 && grau <= 150)
            labelDirecao = "SE";
        else if (grau >= 165 && grau <= 195)
            labelDirecao = "S";
        else if (grau >= 210 && grau <= 240)
            labelDirecao = "SO";
        else if (grau >= 255 && grau <= 285)
            labelDirecao = "O";
        else if (grau >= 300 && grau <= 330)
            labelDirecao = "NO";

        return labelDirecao;
    }
}



            /*float [] A_D = event.values.clone();
            float [] A_W = new float[3];
            A_W[0] = R[0] * A_D[0] + R[1] * A_D[1] + R[2] * A_D[2];
            A_W[1] = R[3] * A_D[0] + R[4] * A_D[1] + R[5] * A_D[2];
            A_W[2] = R[6] * A_D[0] + R[7] * A_D[1] + R[8] * A_D[2];

            Log.d("Field","\nX :"+A_W[0]+"\nY :"+A_W[1]+"\nZ :"+A_W[2]);*/