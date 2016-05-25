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

public class AccelerationEvent extends AppCompatActivity implements SensorEventListener {

    private TextView textX, textY, textZ;
    private Sensor mySensor;
    private SensorManager SM;
    double sensorValue;
    public int timeCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceleration_event);

        SM = (SensorManager) getSystemService(SENSOR_SERVICE);
        mySensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
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



            Intent success = new Intent (AccelerationEvent.this, Successcreen.class);
            startActivity(success);
            finish();


        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void startCounter (){

        Thread thread = new Thread(new Timer());
        thread.start();
    }



    private class Timer implements Runnable {

        @Override
        public void run(){
            for(int i = 10; i >= 0; i--){
                try{
                    Thread.sleep(1000);
                    timeCount = i;
                }catch(Exception e) {}

            }

            Intent fail = new Intent (AccelerationEvent.this, Failedscreen.class);
            startActivity(fail);
            finish();

        }
    }

}
