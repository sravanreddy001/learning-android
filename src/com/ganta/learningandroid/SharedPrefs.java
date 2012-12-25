package com.ganta.learningandroid;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPrefs extends Activity implements OnClickListener {

    EditText sharedData;
    TextView dataResults;
    public static final String fileName = "MySharedString";
    SharedPreferences someData;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharedpreferences);
        setupVariables();
        someData = getSharedPreferences(fileName, 0);
    }

    private void setupVariables() {
        Button save = (Button) findViewById(R.id.bSave);
        Button load = (Button) findViewById(R.id.bLoad);
        sharedData = (EditText) findViewById(R.id.etSharedPrefs);
        dataResults = (TextView) findViewById(R.id.tvLoadSharedPrefs);
        save.setOnClickListener(this);
        load.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.bSave:
            String stringData = sharedData.getText().toString();
            Editor editor = someData.edit();
            editor.putString("sharedString", stringData);
            editor.commit();
            break;
        case R.id.bLoad:
            // Update the variable to load the new data if exists
            someData = getSharedPreferences(fileName, 0);
            String string = someData.getString("sharedString", "Counldn't Load Data");
            dataResults.setText(string);
            break;
        }
    }
    
}
