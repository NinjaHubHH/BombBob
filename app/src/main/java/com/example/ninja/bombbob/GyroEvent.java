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

public class GyroEvent extends AppCompatActivity implements SensorEventListener {

    private TextView textX, textY, textZ;
    private Sensor mySensor;
    private SensorManager SM;
    double sensorValue;
    public int timeCount;
    Thread thread = new Thread(new Timer());
    public int eventTime  = 10;
    boolean timerRun = true;
    boolean success = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyro_event);

        SM = (SensorManager) getSystemService(SENSOR_SERVICE);
        mySensor = SM.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);

        textX = (TextView)findViewById(R.id.textX);
        textY = (TextView)findViewById(R.id.textY);
        textZ = (TextView)findViewById(R.id.textZ);

        startCounter();

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        textX.setText("X: " + event.values[0]);
        textY.setText("Y: " + event.values[1]);
        textZ.setText("Z: " + event.values[2]);

        sensorValue = event.values[0];


        if (sensorValue > 5){

            success = true;

            Intent success = new Intent (GyroEvent.this, Successcreen.class);
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
            if (timerRun) {

                for (eventTime = eventTime; eventTime >= 0; eventTime--) {
                    try {
                        Thread.sleep(1000);
                        timeCount = eventTime;
                    } catch (Exception e) {
                    }

                }

                timerRun = false;
                eventTime = 10;

                if (success = false) {
                    Intent fail = new Intent(GyroEvent.this, Failedscreen.class);
                    startActivity(fail);
                    finish();
                }
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
