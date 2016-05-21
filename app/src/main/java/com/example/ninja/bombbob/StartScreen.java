package com.example.ninja.bombbob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartScreen extends AppCompatActivity implements View.OnClickListener {

    private static Button button_test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);

/*        Button weiterbtn  = (Button)findViewById(R.id.weiterbtn);
        Button skipbtn = (Button)findViewById(R.id.skipbtn);

        weiterbtn.setOnClickListener(this);
        skipbtn.setOnClickListener(this);*/
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, StoryStart.class);
        startActivity(intent);
    }

    public void weiterInDerStory(){
        button_test = (Button)findViewById(R.id.weiterbtn);
        button_test.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(StartScreen.this, StoryTutorial.class);
                        startActivity(i);

                    }
                }
        );
    }


/*    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.weiterbtn:
                Intent intent = new Intent(".StoryTutorial");
                startActivity(intent);
                break;
            case R.id.skipbtn:
                Intent i = new Intent(".WaitScreen");
                startActivity(i);
                break;
        }
    }*/

}
