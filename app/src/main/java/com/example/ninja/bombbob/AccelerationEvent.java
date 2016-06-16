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

public class AccelerationEvent extends AppCompatActivity implements SensorEventListener {

    private TextView textX, textY, textZ, textTime;
    private Sensor mySensor;
    private SensorManager SM;
    double sensorValue;
    long millis;

    private CountDownTimer timer = new CountDownTimer(10000, 1000) {

        public void onTick(long millisUntilFinished) {
            millis = millisUntilFinished;
            textTime.setText("Time on the Clock: " + (millisUntilFinished / 1000));
        }

        public void onFinish() {
            Intent fail = new Intent(AccelerationEvent.this, Failedscreen.class);
            startActivity(fail);
            finish();
        }
    }.start();


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
        textTime = (TextView)findViewById(R.id.textTime);


    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        textX.setText("X: " + event.values[0]);
        textY.setText("Y: " + event.values[1]);
        textZ.setText("Z: " + event.values[2]);

        sensorValue = event.values[0];


        if (sensorValue > 5 && millis >= 5){

            Intent success = new Intent (AccelerationEvent.this, Successcreen.class);
            startActivity(success);
            finish();

        }

        else if (sensorValue > 5 && millis < 5){

            Intent close = new Intent (AccelerationEvent.this, CloseScreen.class);
            startActivity(close);
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
