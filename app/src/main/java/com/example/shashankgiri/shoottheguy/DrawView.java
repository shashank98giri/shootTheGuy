package com.example.shashankgiri.shoottheguy;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Shashank Giri on 7/28/2017.
 */

public class DrawView extends View {
    private int width, height;
    private MissedGuy missedGuy;

    Context mContext;
    private Score score;
    // We can have multiple bullets and explosions
    // keep track of them in ArrayList
    ArrayList<Bullet> bullets;
    ArrayList<Explosion> explosions;
    Cannon cannon;
    AndroidGuy androidGuy;


    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;



        // create a cannon object
        cannon = new Cannon(Color.BLUE,mContext);

        // create arraylists to keep track of bullets and explosions
        bullets = new ArrayList<Bullet> ();
        explosions = new ArrayList<Explosion>();

        // create the falling Android Guy
        androidGuy = new AndroidGuy(Color.RED, mContext);
        score=new Score(Color.BLACK);
        missedGuy=new MissedGuy(Color.BLACK);


    }



    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        width = w;
        height = h;

        cannon.setBounds(0,0,width, height);
        androidGuy.setBounds(0,0,width,height);
        for (int i = 0; i < bullets.size(); i++ ) {
            bullets.get(i).setBounds(0,0,width,height);
        }


    }
    @Override
    protected void onDraw(Canvas canvas){
        drawGameBoard(canvas);
        try{ Thread.sleep(30);
        }
        catch(InterruptedException e){}

        invalidate();
    }


    public void drawGameBoard(Canvas canvas) {

        if (missedGuy.getMissed() > score.getScore()) {
            Intent in = new Intent();
            in.setComponent(new ComponentName("com.example.shashankgiri.shoottheguy", "com.example.shashankgiri.shoottheguy.showMessage"));
            in.putExtra("Score", score.getScore() + "");
            mContext.startActivity(in);
        }
        // Draw all the bullets
        else {canvas.drawColor(Color.WHITE);     //if you want another background color
            // Draw the cannon
            cannon.draw(canvas);
            for (int i = 0; i < bullets.size(); i++) {
                if (bullets.get(i) != null) {
                    bullets.get(i).draw(canvas);

                    if (bullets.get(i).move() == false) {
                        bullets.remove(i);
                    }
                }
            }

            // Draw all the explosions, at those locations where the bullet
            // hits the Android Guy
            for (int i = 0; i < explosions.size(); i++) {
                if (explosions.get(i) != null) {
                    if (explosions.get(i).draw(canvas) == false) {
                        explosions.remove(i);
                    }
                }
            }


            // If the Android Guy is falling, check to see if any of the bullets
            // hit the Guy
            if (androidGuy != null) {
                androidGuy.draw(canvas);

                RectF guyRect = androidGuy.getRect();

                for (int i = 0; i < bullets.size(); i++) {

                    // The rectangle surrounding the Guy and Bullet intersect, then it's a collision
                    // Generate an explosion at that location and delete the Guy and bullet. Generate
                    // a new Android Guy to fall from the top.
                    if (RectF.intersects(guyRect, bullets.get(i).getRect())) {
                        explosions.add(new Explosion(Color.RED, mContext, androidGuy.getX(), androidGuy.getY()));
                        androidGuy.reset();
                        bullets.remove(i);
                        score.incrementScore();
                        // Play the explosion sound by calling the SoundEffects class
                        SoundEffects.INSTANCE.playSound(SoundEffects.SOUND_EXPLOSION);


                        break;
                    }

                }

                if (androidGuy.move() == false) {
                    missedGuy.incrementMissed();
                }
                score.draw(canvas);
                missedGuy.draw(canvas);

            }
        }
    }
    // Move the cannon left or right
    public void moveCannonLeft() {
        cannon.moveLeft();
    }

    public void moveCannonRight() {
        cannon.moveRight();
    }

    // Whenever the user shoots a bullet, create a new bullet moving upwards
    public void shootCannon() {

        bullets.add(new Bullet(Color.RED, mContext, cannon.getPosition(), (float) (height - 40)));

    }


}
