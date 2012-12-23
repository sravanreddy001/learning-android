package com.ganta.learningandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class MyBringBack extends View {

    Bitmap gBall;
    float changingY;
    
    public MyBringBack(Context context) {
        super(context);
        gBall = BitmapFactory.decodeResource(getResources(), R.drawable.green_ball);
        changingY = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(gBall, (canvas.getWidth()/2), changingY, null);
        if(changingY < canvas.getHeight()) {
            changingY++;
        }
        else {
            changingY = 0;
        }
        Rect middleRect = new Rect();
        middleRect.set(0, 400, canvas.getWidth(), 550);
        Paint ourBlue = new Paint();
        ourBlue.setColor(Color.BLUE);
        canvas.drawRect(middleRect, ourBlue);
        invalidate();
    }
    
    

}
