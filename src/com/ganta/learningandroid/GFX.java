package com.ganta.learningandroid;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

public class GFX extends Activity {

    MyBringBack ourView;
    WakeLock wl;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //wake-lock
        PowerManager pM = (PowerManager) getSystemService(Context.POWER_SERVICE);
        WakeLock wl = pM.newWakeLock(PowerManager.FULL_WAKE_LOCK, "Tag string");
        
        super.onCreate(savedInstanceState);
        wl.acquire(); // Do this in wake onResume() method as well.
        ourView = new MyBringBack(this);
        setContentView(ourView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        wl.release();
    }
    
}
