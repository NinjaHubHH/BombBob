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
    private String TAG = "Timer: ";
    public static long score = 0;
    public static int checkedEvents = 0;
    private boolean won = false;

    public static void checkEvent (){
        checkedEvents = checkedEvents + 1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waitscreen);

        if (score == 0) {score = System.currentTimeMillis();}
        if (checkedEvents > 5){
            Intent win = new Intent(WaitScreen.this, WinScreen.class);
            startActivity(win);
            finish();
            won = true;
        }
        if (won == false) {
            waitedTime = new Random().nextInt(6);
            waitedTime = waitedTime + 3;
            waitedTime = waitedTime * 1000;
            eventChooser = new Random().nextInt(6);
            waitedTime = waitedTime + 3;
            textTime = (TextView) findViewById(R.id.textTime);

            startService(new Intent(this, BigTimerService.class));
            Log.i(TAG, "Started service");

            startCounter();
        }
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
        registerReceiver(br, new IntentFilter(BigTimerService.COUNTDOWN_BR));
        Log.i(TAG, "Registered broacast receiver");
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(br);
        Log.i(TAG, "Unregistered broacast receiver");
        timer.cancel();
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
        //stopService(new Intent(this, BigTimerService.class));
        Log.i(TAG, "Stopped service");
        super.onDestroy();
    }

    private void updateGUI(Intent intent) {
        if (intent.getExtras() != null) {
            long millisUntilFinished = intent.getLongExtra("countdown", 0);
            Log.i(TAG, "Countdown seconds remaining: " +  millisUntilFinished / 1000);
        }
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
