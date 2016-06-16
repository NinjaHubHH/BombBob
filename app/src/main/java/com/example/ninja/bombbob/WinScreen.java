package com.example.ninja.bombbob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WinScreen extends AppCompatActivity {

    long finalScore;
    private TextView winText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_screen);

        //stopService(new Intent(this, BigTimerService.class));

        finalScore = System.currentTimeMillis();
        finalScore = finalScore - WaitScreen.score;

        winText = (TextView)findViewById(R.id.winText);
        winText.setText("Score: " + finalScore / 1000);

    }
}
