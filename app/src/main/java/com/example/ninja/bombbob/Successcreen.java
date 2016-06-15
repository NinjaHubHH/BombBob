package com.example.ninja.bombbob;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Successcreen extends AppCompatActivity {

    Button buttonBack;
    MediaPlayer winSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successcreen);
        switchBack();
        playMusic();
    }

    private void playMusic() {
        winSound.start();
    }


    public void switchBack(){
        buttonBack = (Button)findViewById(R.id.backButton);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent toy = new Intent(Successcreen.this, WaitScreen.class);
                startActivity(toy);
                finish();
            }
        });
    }

}
