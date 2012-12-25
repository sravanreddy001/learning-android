package com.ganta.learningandroid;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Data deleted when application is uninstalled.
 * @author sravanku@buffalo.edu
 */
public class InternalData extends Activity implements OnClickListener {

    EditText sharedData;
    TextView dataResults;
    FileOutputStream fos;
    public static final String FILENAME = "InternalString";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharedpreferences);
        setupVariables();
    }

    private void setupVariables() {
        Button save = (Button) findViewById(R.id.bSave);
        Button load = (Button) findViewById(R.id.bLoad);
        sharedData = (EditText) findViewById(R.id.etSharedPrefs);
        dataResults = (TextView) findViewById(R.id.tvLoadSharedPrefs);
        save.setOnClickListener(this);
        load.setOnClickListener(this);
        try {
            //Making sure that the file exists to be used later.
            fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.bSave:
            String data = sharedData.getText().toString();
            try {
                fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                fos.write(data.getBytes());
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            break;
        case R.id.bLoad:
            new LoadSomeStuff().execute(FILENAME);
            break;
        }
    }
    
    // AsyncTask -> Input, ProgressBar, Output
    public class LoadSomeStuff extends AsyncTask<String, Integer, String> {

        protected void onPreExecute(String f) {
            //example setting up something
        }
        
        @Override
        protected String doInBackground(String... params) {
            try {
                FileInputStream fis = openFileInput(FILENAME);
                byte[] dataArray = new byte[fis.available()];
                while(fis.read(dataArray) != -1) {
                    
                }
                String collected = new String(dataArray);
                fis.close();
                return collected;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            //super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            //super.onPostExecute(result);
            dataResults.setText(result);
        }
    }
}
