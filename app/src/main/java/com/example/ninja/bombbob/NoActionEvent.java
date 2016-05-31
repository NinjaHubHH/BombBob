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

public class NoActionEvent extends AppCompatActivity implements SensorEventListener {

    private TextView textX, textY, textZ;
    private Sensor mySensor1;
    private SensorManager SM1;

    private Sensor mySensor2;
    private SensorManager SM2;
    double sensorValue1;
    double sensorValue2;
    double sensorValue3;
    public int timeCount;
    Thread thread = new Thread(new Timer());
    public int eventTime  = 10;
    boolean timerRun = true;
    boolean success = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_action_event);

        SM1 = (SensorManager) getSystemService(SENSOR_SERVICE);
        mySensor1 = SM1.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        SM1.registerListener(this, mySensor1, SensorManager.SENSOR_DELAY_NORMAL);

        SM2 = (SensorManager) getSystemService(SENSOR_SERVICE);
        mySensor2 = SM2.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        SM2.registerListener(this, mySensor2, SensorManager.SENSOR_DELAY_NORMAL);

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

        sensorValue1 = event.values[0];
        sensorValue2 = event.values[1];
        sensorValue3 = event.values[2];



        if (sensorValue1 > 5 || sensorValue2 > 5 || sensorValue3 > 5){

            success = false;

            Intent fail = new Intent (NoActionEvent.this, Failedscreen.class);
            startActivity(fail);
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

                if (success = true) {
                    Intent success = new Intent(NoActionEvent.this, Successcreen.class);
                    startActivity(success);
                    finish();
                }
            }
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        SM1.unregisterListener(this);
        SM2.unregisterListener(this);
        eventTime = 10;
    }

}
