package com.ganta.learningandroid;

import android.app.Activity;
import android.os.Bundle;

public class GFX extends Activity {

    MyBringBack ourView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ourView = new MyBringBack(this);
        setContentView(ourView);
    }
    
}
