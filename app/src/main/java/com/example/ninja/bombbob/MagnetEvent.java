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

public class MagnetEvent extends AppCompatActivity implements SensorEventListener {

    private Sensor mySensor;
    private SensorManager SM;
    double sensorValue;

    private CountDownTimer timer = new CountDownTimer(14000, 1000) {

        public void onTick(long millisUntilFinished) {
        }

        public void onFinish() {
            Intent fail = new Intent(MagnetEvent.this, Failedscreen.class);
            startActivity(fail);
            finish();
        }
    }.start();


    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magnet_event);

        mp = MediaPlayer.create(MagnetEvent.this, R.raw.piep);
        mp.start();

        SM = (SensorManager) getSystemService(SENSOR_SERVICE);
        mySensor = SM.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        sensorValue = event.values[0] + event.values[1] + event.values[2];


        if (sensorValue > 1000){
            Intent success = new Intent (MagnetEvent.this, Successcreen.class);
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
