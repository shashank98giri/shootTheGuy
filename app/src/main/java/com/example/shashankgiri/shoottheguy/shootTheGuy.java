package com.example.shashankgiri.shoottheguy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class shootTheGuy extends AppCompatActivity {
    Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
        RelativeLayout rlayout=(RelativeLayout)findViewById(R.id.activity_start_gameLayout);
         rlayout.setOnClickListener(new View.OnClickListener () {
             public void onClick(View v){
                 int view=v.getId();
                 switch(view){
                     case R.id.activity_start_gameLayout:Intent in;
                         in = new Intent(v.getContext(), shoot.class);startActivity(in);

                 }
             }
         });//start=(Button) findViewById(R.id.startGame);
        //start.setOnClickListener(this);
    }



}
