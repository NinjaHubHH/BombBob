package com.example.ninja.bombbob;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class WaitScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waitscreen);
        waitedTime = new Random().nextInt(3);
        eventChooser = new Random().nextInt(1);
        waitedTime = waitedTime + 3;
        textTime = (TextView)findViewById(R.id.textTime);

        startCounter();

    }

    private TextView textTime;
    public int waitedTime;
    protected boolean timerRun = true;
    protected boolean success = true;
    public int eventChooser;


    public void startCounter() {
        Thread thread = new Thread(new Timer());
        thread.start();
    }

    private class Timer implements Runnable {

        @Override
        public void run() {
            if (timerRun) {
                for (waitedTime = waitedTime; waitedTime >= 0; waitedTime--) {
                    try {
                        textTime.setText("Time on the Clock: " + waitedTime);
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }

                }

                timerRun = false;
                if(success = true) {
                    success = false;


                    if(eventChooser == 0) {
                        Intent acceleration = new Intent(WaitScreen.this, AccelerationEvent.class);
                        startActivity(acceleration);
                        finish();
                    }

                    if(eventChooser == 1) {
                        Intent acceleration = new Intent(WaitScreen.this, ProximityEvent.class);
                        startActivity(acceleration);
                        finish();
                    }

                    if(eventChooser == 2) {
                        Intent acceleration = new Intent(WaitScreen.this, LightEvent.class);
                        startActivity(acceleration);
                        finish();
                    }

                    if(eventChooser == 3) {
                        Intent acceleration = new Intent(WaitScreen.this, MagnetEvent.class);
                        startActivity(acceleration);
                        finish();
                    }

                    if(eventChooser == 4) {
                        Intent acceleration = new Intent(WaitScreen.this, GyroEvent.class);
                        startActivity(acceleration);
                        finish();
                    }

                    if(eventChooser == 5) {
                        Intent acceleration = new Intent(WaitScreen.this, NoActionEvent.class);
                        startActivity(acceleration);
                        finish();
                    }

                }

            }
        }
    }



}
