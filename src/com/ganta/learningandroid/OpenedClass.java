package com.ganta.learningandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class OpenedClass extends Activity implements OnClickListener,
        OnCheckedChangeListener {

    TextView question, test;
    Button returnData;
    RadioGroup selectionList;
    String gotBread;
    String sendData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send);
        initialize();
        Bundle gotBasket = getIntent().getExtras();
        if(gotBasket != null) {
            gotBread = gotBasket.getString("key");
        }
        question.setText(gotBread);
    }

    private void initialize() {
        question = (TextView) findViewById(R.id.tvQuestion);
        test = (TextView) findViewById(R.id.tvTest);
        returnData = (Button) findViewById(R.id.bReturn);
        returnData.setOnClickListener(this);
        selectionList = (RadioGroup) findViewById(R.id.rgAnswers);
        selectionList.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
        case R.id.rCrazy:
            sendData = "Probably Right";
            break;
        case R.id.rSexy:
            sendData = "Definitely Right";
            break;
        case R.id.rBoth:
            sendData = "Spot On!";
            break;
        }
        test.setText(sendData);
    }

}
