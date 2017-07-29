package com.example.shashankgiri.shoottheguy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static com.example.shashankgiri.shoottheguy.R.id.textView;

public class showMessage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_message);
        Intent in=getIntent();
        String s=in.getStringExtra("Score");
        TextView textView=(TextView) findViewById(R.id.textView);
        textView.setText("Your Score: "+s);
    }
}
