package com.example.ninja.bombbob;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GyroEvent extends AppCompatActivity implements SensorEventListener {

    private Sensor mySensor;
    private SensorManager SM;
    double sensorValue;

    private CountDownTimer timer = new CountDownTimer(14000, 1000) {

        public void onTick(long millisUntilFinished) {
        }

        public void onFinish() {
            Intent fail = new Intent(GyroEvent.this, Failedscreen.class);
            startActivity(fail);
            finish();
        }
    }.start();

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyro_event);

        mp = MediaPlayer.create(GyroEvent.this, R.raw.piep);
        mp.start();

        SM = (SensorManager) getSystemService(SENSOR_SERVICE);
        mySensor = SM.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);


    }

    @Override
    public void onSensorChanged(SensorEvent event) {


        sensorValue = event.values[0];


        if (sensorValue > 5){
            Intent success = new Intent (GyroEvent.this, Successcreen.class);
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
        mp.stop();
    }

}
