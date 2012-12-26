package com.ganta.learningandroid;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Accelerate extends Activity implements SensorEventListener {

    float x, y, sensorX, sensorY;
    Bitmap ball;
    SensorManager sm;
    MyBringBackSurface ourSurfaceView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Sensor available or not?
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if(sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
            Sensor s = sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            sm.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
        }
        ball = BitmapFactory.decodeResource(getResources(), R.drawable.green_ball);
        x = y = sensorX = sensorY = 0;
        
        ourSurfaceView = new MyBringBackSurface(this);
        ourSurfaceView.resume(); // resume must be invoked according to logic we copied.
        setContentView(ourSurfaceView);
    }
    
    

    @Override
    protected void onPause() {
        sm.unregisterListener(this);
        super.onPause();
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //test accuracy of change.
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // change in sensor value.
        // save some processing power. 60 FPS.
        try {
            Thread.sleep(16);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sensorX = event.values[0];
        sensorY = event.values[1];
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
                //sensor can change only by max of 40.
                Canvas canvas = ourHolder.lockCanvas();
                canvas.drawRGB(2, 2, 150);
                float centerX = canvas.getWidth()/2;
                float centerY = canvas.getHeight()/2;
                canvas.drawBitmap(ball, centerX + sensorX * 20, centerY + sensorY * 20, null);
                ourHolder.unlockCanvasAndPost(canvas);
            }
        }

    }

}
