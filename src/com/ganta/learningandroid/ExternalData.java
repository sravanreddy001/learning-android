package com.ganta.learningandroid;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

/**
 * Data saved in SD card, so data will be available when application is updated.
 * @author sravanku@buffalo.edu
 */
public class ExternalData extends Activity {

    private TextView canWrite, canRead;
    private String state;
    boolean canR, canW;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.externaldata);
        canRead = (TextView) findViewById(R.id.tvCanRead);
        canWrite = (TextView) findViewById(R.id.tvCanWrite);
        
        state = Environment.getExternalStorageState();
        if(state.equals(Environment.MEDIA_MOUNTED)) {
            // read and write
            canWrite.setText("true");
            canRead.setText("true");
            canR = canW = true;
        }
        else if(state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            // read but can't write
            canWrite.setText("false");
            canRead.setText("true");
            canR = true;
            canW = false;
        } else {
            canWrite.setText("false");
            canRead.setText("false");
            canR = canW = false;
        }
    }
    
}
