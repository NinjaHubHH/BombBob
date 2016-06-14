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

    private static final String TAG = "Action: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waitscreen);
        waitedTime = new Random().nextInt(3);
        eventChooser = new Random().nextInt(6);
        waitedTime = waitedTime + 3;
        textTime = (TextView)findViewById(R.id.textTime);

        startService(new Intent(this, BroadcastService.class));


        startCounter();

    }

    private BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateGUI(intent); // or whatever method used to update your GUI fields

        }
    };

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(br, new IntentFilter(BroadcastService.COUNTDOWN_BR));
        Log.i(TAG, "Registered broacast receiver");
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(br);
        Log.i(TAG, "Unregistered broacast receiver");
    }

    @Override
    public void onStop() {
        try {
            unregisterReceiver(br);
        } catch (Exception e) {
            // Receiver was probably already stopped in onPause()
        }
        super.onStop();
    }

    @Override
    public void onDestroy() {
        stopService(new Intent(this, BroadcastService.class));
        Log.i(TAG, "Stopped service");
        super.onDestroy();
    }

    private void updateGUI(Intent intent) {
        if (intent.getExtras() != null) {
            long millisUntilFinished = intent.getLongExtra("countdown", 0);
            Log.i(TAG, "Countdown seconds remaining: " +  millisUntilFinished / 1000);
        }
    }


    private TextView textTime;
    public int waitedTime;
    protected boolean timerRunWait = true;
    protected boolean success = true;
    public int eventChooser;



    public void startCounter() {
        Thread thread = new Thread(new Timer());
        thread.start();
    }

    private class Timer implements Runnable {

        @Override
        public void run() {
            if (timerRunWait) {
                for (waitedTime = waitedTime; waitedTime >= 0; waitedTime--) {
                    try {
                        textTime.setText("Time on the Clock: " + waitedTime);
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }

                }

                timerRunWait = false;
                if(success = true) {
                    success = false;


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

            }
        }
    }




}
