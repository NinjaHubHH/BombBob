package com.example.ninja.bombbob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WaitScreen extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waitscreen);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, EventActivity.class);
        startActivity(intent);
    }

}
