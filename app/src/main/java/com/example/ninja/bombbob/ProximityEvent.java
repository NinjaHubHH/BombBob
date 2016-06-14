package com.example.ninja.bombbob;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.CountDownTimer;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ProximityEvent extends AppCompatActivity implements SensorEventListener {

    private TextView textX;
    private Sensor mySensor;
    private SensorManager SM;
    double sensorValue;


    private CountDownTimer timer = new CountDownTimer(5000, 1000) {

        public void onTick(long millisUntilFinished) {
        }

        public void onFinish() {
            Intent fail = new Intent(ProximityEvent.this, Failedscreen.class);
            startActivity(fail);
            finish();
        }
    }.start();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity_event);

        SM = (SensorManager) getSystemService(SENSOR_SERVICE);
        mySensor = SM.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);

        textX = (TextView)findViewById(R.id.textX);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        textX.setText("X: " + event.values[0]);


        sensorValue = event.values[0];


        if (sensorValue < 1){
            Intent success = new Intent (ProximityEvent.this, Successcreen.class);
            startActivity(success);
            finish();

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    @Override
    protected void onPause(){
        super.onPause();
        SM.unregisterListener(this);
        timer.cancel();
    }

}
