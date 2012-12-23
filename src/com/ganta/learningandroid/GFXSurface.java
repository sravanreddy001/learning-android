package com.ganta.learningandroid;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class GFXSurface extends Activity implements OnTouchListener {

    MyBringBackSurface ourSurfaceView;
    float x, y, sX, sY, fX, fY;
    float dX, dY, animateX, animateY, scaledX, scaledY;
    Bitmap test, plus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ourSurfaceView = new MyBringBackSurface(this);
        ourSurfaceView.setOnTouchListener(this);
        x = 0;
        y = 0;
        sX = 0;
        sY = 0;
        fX = 0;
        fY = 0;
        dX = dY = animateX = animateY = scaledX = scaledY = 0;
        test = BitmapFactory.decodeResource(getResources(),
                R.drawable.green_ball);
        plus = BitmapFactory.decodeResource(getResources(),
                R.drawable.button);
        setContentView(ourSurfaceView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ourSurfaceView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ourSurfaceView.resume();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        x = event.getX();
        y = event.getY();
        
        try {
            Thread.sleep(50); // FPS = 20
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
            sX = event.getX();
            sY = event.getY();
            dX = dY = animateX = animateY = scaledX = scaledY = fX = fY = 0;
            break;
        case MotionEvent.ACTION_UP:
            fX = event.getX();
            fY = event.getY();
            dX = fX-sX;
            dY = fY-sY;
            scaledX = dX/30;
            scaledY = dY/30;
            x = 0; // avoid prining first label.
            y = 0;
            break;
        }

        return true; // follow the curser when dragging
    }

    public class MyBringBackSurface extends SurfaceView implements Runnable {

        SurfaceHolder ourHolder;
        Thread ourThread;
        boolean isRunning = false;

        public MyBringBackSurface(Context context) {
            super(context);
            ourHolder = getHolder();
        }

        public void pause() {
            isRunning = false;
            while (true) {
                try {
                    ourThread.join();
                } catch (InterruptedException e) {
                    // TODO Add LOG statement here.
                    e.printStackTrace();
                }
                break;
            }
            ourThread = null;
        }

        // called form GFXSurface
        public void resume() {
            isRunning = true;
            ourThread = new Thread(this);
            ourThread.start();
        }

        @Override
        public void run() {
            while (isRunning) {
                if (!ourHolder.getSurface().isValid()) {
                    continue;
                }

                Canvas canvas = ourHolder.lockCanvas();
                canvas.drawRGB(2, 2, 150);
                if (x != 0 && y != 0) {
                    canvas.drawBitmap(test, x - (test.getWidth() / 2), y- (test.getHeight() / 2), null);
                }
                if (sX != 0 && sY != 0) {
                    canvas.drawBitmap(plus, sX - (plus.getWidth() / 2), sY- (plus.getHeight() / 2), null);
                }
                if (fX != 0 && fY != 0) {
                    canvas.drawBitmap(test, fX - (test.getWidth() / 2)-animateX, fY- (test.getHeight()/2)-animateY, null);
                    canvas.drawBitmap(plus, fX - (plus.getWidth() / 2), fY- (plus.getHeight() / 2), null);
                }
                animateX = animateX + scaledX;
                animateY = animateY + scaledY;
                ourHolder.unlockCanvasAndPost(canvas);
            }
        }

    }

}
