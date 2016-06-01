package com.example.ninja.bombbob;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LightEvent extends AppCompatActivity implements SensorEventListener {

    private TextView textX;
    private Sensor mySensor;
    private SensorManager SM;
    double sensorValue;
    public int timeCount;
    Thread thread = new Thread(new Timer());
    public int eventTime  = 10;
    boolean timerRunLig = true;

    double sensorCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_event);

        SM = (SensorManager) getSystemService(SENSOR_SERVICE);
        mySensor = SM.getDefaultSensor(Sensor.TYPE_LIGHT);
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);

        textX = (TextView)findViewById(R.id.textX);

        startCounter();

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        textX.setText("X: " + event.values[0]);


        if(sensorValue == 0) {
            sensorValue = event.values[0];
        }

        sensorCheck = event.values[0];

        if (sensorCheck > sensorValue + 300){

            Intent success = new Intent (LightEvent.this, Successcreen.class);
            startActivity(success);
            finish();

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void startCounter (){

        thread.start();
    }



    private class Timer implements Runnable {

        @Override
        public void run() {
            if (timerRunLig) {

                for (eventTime = eventTime; eventTime >= 0; eventTime--) {
                    try {
                        Thread.sleep(1000);
                        timeCount = eventTime;
                    } catch (Exception e) {
                    }

                }

                timerRunLig = false;
                eventTime = 10;

                    Intent fail = new Intent(LightEvent.this, Failedscreen.class);
                    startActivity(fail);
                    finish();

            }
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        SM.unregisterListener(this);
        eventTime = 10;
    }

}
