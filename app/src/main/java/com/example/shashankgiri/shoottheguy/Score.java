package com.example.shashankgiri.shoottheguy;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

/**
 * Created by Shashank Giri on 7/28/2017.
 */

public class Score {
    private Paint paint;
    private int score;

    // Constructor
    public Score(int color) {
        paint = new Paint();
        // Set the font face and size of drawing text
        paint.setTypeface(Typeface.MONOSPACE);
        paint.setTextSize(40);
        paint.setColor(color);


        score = 0;
    }

    public void incrementScore() {

        score++;
    }
    public int getScore(){return score;}


    public void draw(Canvas canvas) {


        // draw text on the canvas. Position the text at (10,30).
        canvas.drawText("Score : " + score, 10, 40, paint);
    }
}
