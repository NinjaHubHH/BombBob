package com.example.ninja.bombbob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Failedscreen extends AppCompatActivity {

    Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_failedscreen);
        switchBack();

    }

    public void switchBack(){
        buttonBack = (Button)findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent toy = new Intent(Failedscreen.this, WaitScreen.class);
                startActivity(toy);
                finish();
            }
        });
    }

}