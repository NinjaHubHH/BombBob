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

public class ProximityEvent extends AppCompatActivity implements SensorEventListener {

    private TextView textX;
    private Sensor mySensor;
    private SensorManager SM;
    double sensorValue;
    public int timeCount;
    Thread thread = new Thread(new Timer());
    public int eventTime  = 10;
    boolean timerRunProx = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity_event);

        SM = (SensorManager) getSystemService(SENSOR_SERVICE);
        mySensor = SM.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);

        textX = (TextView)findViewById(R.id.textX);


        startCounter();

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

    public void startCounter (){

        thread.start();
    }



    private class Timer implements Runnable {

        @Override
        public void run() {
            if (timerRunProx) {

                for (eventTime = eventTime; eventTime >= 0; eventTime--) {
                    try {
                        Thread.sleep(1000);
                        timeCount = eventTime;
                    } catch (Exception e) {
                    }

                }

                timerRunProx = false;
                eventTime = 10;

                Intent fail = new Intent(ProximityEvent.this, Failedscreen.class);
                startActivity(fail);
                finish();

            }
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        SM.unregisterListener(this);
        //eventTime = 10;
    }

}
