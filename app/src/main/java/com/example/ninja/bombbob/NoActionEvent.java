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

public class NoActionEvent extends AppCompatActivity implements SensorEventListener {

    private Sensor mySensor1;
    private SensorManager SM1;

    double sensorValue1, sensorValue2, sensorValue3;
    double sensorCheck1, sensorCheck2, sensorCheck3;

    private CountDownTimer timer = new CountDownTimer(5000, 1000) {

        public void onTick(long millisUntilFinished) {
        }

        public void onFinish() {
            Intent success = new Intent(NoActionEvent.this, Successcreen.class);
            startActivity(success);
            finish();
        }
    }.start();

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_action_event);

        mp = MediaPlayer.create(NoActionEvent.this, R.raw.piep);
        mp.start();

        SM1 = (SensorManager) getSystemService(SENSOR_SERVICE);
        mySensor1 = SM1.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        SM1.registerListener(this, mySensor1, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {


        if(sensorValue1 == 0) {
            sensorValue1 = event.values[0];
        }
        if(sensorValue2 == 0) {
            sensorValue2 = event.values[1];
        }
        if(sensorValue3 == 0) {
            sensorValue3 = event.values[2];
        }

        sensorCheck1 = event.values[0];
        sensorCheck2 = event.values[1];
        sensorCheck3 = event.values[2];


        if ((sensorValue1 - 2 > sensorCheck1 || sensorCheck1 > sensorValue1 + 2) || (sensorValue2 - 2 > sensorCheck2 || sensorCheck2 > sensorValue2 + 2) || (sensorValue3 - 2 > sensorCheck3 || sensorCheck3 > sensorValue3 + 2) ){

            Intent fail = new Intent (NoActionEvent.this, Failedscreen.class);
            startActivity(fail);
            finish();

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }



    @Override
    protected void onPause(){
        super.onPause();
        SM1.unregisterListener(this);
        timer.cancel();
        mp.stop();
    }

}
