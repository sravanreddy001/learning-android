package com.ganta.learningandroid;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class Splash extends Activity {

    MediaPlayer splashSound;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        splashSound = MediaPlayer.create(Splash.this, R.raw.autobots_roll_out);

        SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean musicAllowed = getPrefs.getBoolean("checkbox", true);
        if(musicAllowed) {
            splashSound.start();
        }
        
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent startActivity = new Intent("android.intent.action.MENU");
                    startActivity(startActivity);
                }
            }
        };
        timer.start();
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        splashSound.release();
        finish(); // This is activity is killed. (Removed from history in navigation)
    }
    
}
