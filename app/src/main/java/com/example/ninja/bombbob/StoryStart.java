package com.example.ninja.bombbob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StoryStart extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_start);
    }

    @Override
    public void onClick(View v) {
        Button weiterbtn  = (Button)findViewById(R.id.weiterbtn);
        Button skipbtn = (Button)findViewById(R.id.skipbtn);

        weiterbtn.setOnClickListener(this);
        skipbtn.setOnClickListener(this);

        if(v == weiterbtn) {
            Intent intent = new Intent(this, StoryTutorial.class);
            startActivity(intent);
        }
        else if(v == skipbtn){
            Intent i = new Intent(this, WaitScreen.class);
            startActivity(i);

        }
    }
}
