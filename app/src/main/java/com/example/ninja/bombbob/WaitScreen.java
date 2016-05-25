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
        waitedTime = waitedTime + 3;
        startCounter();

    }


    public int waitedTime;


    public void startCounter() {
        Thread thread = new Thread(new Timer());
        thread.start();
    }

    private class Timer implements Runnable {

        @Override
        public void run() {
            for (waitedTime = waitedTime; waitedTime >= 0; waitedTime--) {
                try {

                    Thread.sleep(1000);
                } catch (Exception e) {
                }

            }

            Intent acceleration = new Intent(WaitScreen.this, AccelerationEvent.class);
            startActivity(acceleration);
            finish();

        }
    }



}
