package com.example.ninja.bombbob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartScreen extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);

    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, StoryStart.class);
        startActivity(intent);
    }
}
