package com.example.ninja.bombbob;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.CountDownTimer;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class WaitScreen extends AppCompatActivity {

    private TextView textTime;
    public int waitedTime;
    public int eventChooser;
    private CountDownTimer timer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waitscreen);
        waitedTime = new Random().nextInt(3);
        waitedTime = waitedTime * 1000;
        eventChooser = new Random().nextInt(6);
        waitedTime = waitedTime + 3;
        textTime = (TextView)findViewById(R.id.textTime);

        startService(new Intent(this, BigTimerService.class));


        startCounter();

    }



    @Override
    public void onDestroy() {
        stopService(new Intent(this, BigTimerService.class));
        System.out.println("Stopped service");
        super.onDestroy();
    }

    public void startCounter(){

        timer = new CountDownTimer(waitedTime, 1000) {

                public void onTick(long millisUntilFinished) {
                    textTime.setText("Time on the Clock: " + (millisUntilFinished / 1000));
                }

                public void onFinish() {
                    if(eventChooser == 0) {
                        Intent acceleration = new Intent(WaitScreen.this, AccelerationEvent.class);
                        startActivity(acceleration);
                        finish();
                    }

                    if(eventChooser == 1) {
                        Intent prox = new Intent(WaitScreen.this, ProximityEvent.class);
                        startActivity(prox);
                        finish();
                    }

                    if(eventChooser == 2) {
                        Intent light = new Intent(WaitScreen.this, LightEvent.class);
                        startActivity(light);
                        finish();
                    }

                    if(eventChooser == 3) {
                        Intent mag = new Intent(WaitScreen.this, MagnetEvent.class);
                        startActivity(mag);
                        finish();
                    }

                    if(eventChooser == 4) {
                        Intent gyro = new Intent(WaitScreen.this, GyroEvent.class);
                        startActivity(gyro);
                        finish();
                    }

                    if(eventChooser == 5) {
                        Intent noAct = new Intent(WaitScreen.this, NoActionEvent.class);
                        startActivity(noAct);
                        finish();
                    }
                }
            }.start();



        }
}
