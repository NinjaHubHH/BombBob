package com.example.ninja.bombbob;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TapEvent extends AppCompatActivity implements View.OnClickListener{

    private int tapCount;
    MediaPlayer mp;

    private CountDownTimer timer = new CountDownTimer(14000, 1000) {

        public void onTick(long millisUntilFinished) {

        }

        public void onFinish() {
            Intent fail = new Intent(TapEvent.this, Failedscreen.class);
            startActivity(fail);
            finish();
        }
    }.start();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap_event);
        mp = MediaPlayer.create(TapEvent.this, R.raw.piep);
        mp.start();
    }

    @Override
    public void onClick(View v) {
        tapCount++;

        if (tapCount > 50){
            Intent success = new Intent(this, Successcreen.class);
            startActivity(success);
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        timer.cancel();
        mp.stop();
    }
}
