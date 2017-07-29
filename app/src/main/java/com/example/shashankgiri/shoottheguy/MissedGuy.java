package com.example.shashankgiri.shoottheguy;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

/**
 * Created by Shashank Giri on 7/28/2017.
 */

public class MissedGuy {
    private Paint paint;
    private int missed;

    // Constructor
    public MissedGuy(int color) {
        paint = new Paint();
        // Set the font face and size of drawing text
        paint.setTypeface(Typeface.MONOSPACE);
        paint.setTextSize(40);
        paint.setColor(color);


        missed = 0;
    }

    public void incrementMissed() {

        missed++;
    }

    public int getMissed(){return missed;}

    public void draw(Canvas canvas) {


        // draw text on the canvas. Position the text at (10,30).
        canvas.drawText("Missed : " + missed, 10, 130, paint);
    }
}
