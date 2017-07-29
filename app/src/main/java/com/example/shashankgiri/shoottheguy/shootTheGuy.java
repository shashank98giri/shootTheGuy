package com.example.shashankgiri.shoottheguy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class shootTheGuy extends AppCompatActivity implements View.OnClickListener{
    Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
         start=(Button) findViewById(R.id.startGame);
        start.setOnClickListener(this);
    }
    public void onClick(View v){
        int id=v.getId();
        switch(id){
            case R.id.startGame: Intent in=new Intent(this,shoot.class);
                                    startActivity(in);break;
            default: break;
        }
    }
}
