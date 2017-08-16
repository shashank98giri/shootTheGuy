package com.example.shashankgiri.shoottheguy;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;

public class shoot extends AppCompatActivity implements View.OnClickListener {

   private  MediaPlayer player;
   private DrawView drawView;private ImageButton moveRightButton,shootButton,moveLeftButton;
    private boolean play_music;Menu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoot_the_guy);
        Intent in=getIntent();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get a reference to the Custom View
        drawView = (DrawView) findViewById(R.id.drawView);

        // Get reference to the buttons and set their onClickListeners
        moveLeftButton = (ImageButton) findViewById(R.id.moveLeftButton);
        moveLeftButton.setOnClickListener(this);
        moveRightButton = (ImageButton) findViewById(R.id.moveRightButton);
        moveRightButton.setOnClickListener(this);
        shootButton = (ImageButton) findViewById(R.id.shootButton);
        shootButton.setOnClickListener(this);


        // Create a new MediaPlayer object and initialize it. We will then start playing the
        // background music when the activity resumes, and pause it when the activity pauses.
        player = MediaPlayer.create(this, R.raw.braincandy);
        player.setLooping(true);
        play_music = true;

        // Set the context fo the SoundEffects singleton class

        SoundEffects.INSTANCE.setContext(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shooting_game, menu);

        this.menu = menu;
        if (play_music) {
            menu.findItem(R.id.action_sound).setIcon(R.drawable.ic_volume_off_white_24dp);
        }
        else {
            menu.findItem(R.id.action_sound).setIcon(R.drawable.ic_volume_up_white_24dp);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.action_sound) {

            if (play_music) {
                player.pause();
                play_music=false;
                menu.findItem(R.id.action_sound).setIcon(R.drawable.ic_volume_up_white_24dp);

            }
            else {
                player.start();
                play_music=true;
                menu.findItem(R.id.action_sound).setIcon(R.drawable.ic_volume_off_white_24dp);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {


        if (play_music)
            player.pause();

        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();


        if (play_music)
            player.start();
    }

    @Override
    protected void onDestroy() {

        player.stop();
        player.reset();
        player.release();
        player = null;
        play_music = false;

        super.onDestroy();
    }

    @Override
    public void onClick(View v) {

        // Using the View's ID to distinguish which button was clicked
        switch(v.getId()) {

            case R.id.moveLeftButton:
                drawView.moveCannonLeft();
                break;

            case R.id.moveRightButton:
                drawView.moveCannonRight();
                break;
            case R.id.shootButton:
                drawView.shootCannon();
                break;
            default:
                break;
        }

    }

}