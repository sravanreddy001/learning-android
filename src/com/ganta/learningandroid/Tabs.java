package com.ganta.learningandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

public class Tabs extends Activity implements OnClickListener {

    TabHost th;
    TabSpec specs;
    TextView showResults;
    long start, stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs);
        th = (TabHost) findViewById(R.id.tabhost);
        
        Button newTab = (Button) findViewById(R.id.bAddTab);
        Button bStart = (Button) findViewById(R.id.bStartWatch);
        Button bStop = (Button) findViewById(R.id.bStopWatch);
        showResults = (TextView) findViewById(R.id.tvShowResults);
        start = stop = 0;
        
        newTab.setOnClickListener(this);
        bStart.setOnClickListener(this);
        bStop.setOnClickListener(this);
        
        th.setup();
        // tab1
        specs = th.newTabSpec("tag1");
        specs.setContent(R.id.tab1);
        specs.setIndicator("StopWatch");
        th.addTab(specs);
        // tab1
        specs = th.newTabSpec("tag2");
        specs.setContent(R.id.tab2);
        specs.setIndicator("Tab 2");
        th.addTab(specs);
        // tab1
        specs = th.newTabSpec("tag3");
        specs.setContent(R.id.tab3);
        specs.setIndicator("Add a Tab");
        th.addTab(specs);
    }

    @Override
    public void onClick(View arg0) {
        switch(arg0.getId()) {
        case R.id.bAddTab:
            TabSpec ourSpec = th.newTabSpec("tag4");
            ourSpec.setContent(new TabHost.TabContentFactory() {
                
                @Override
                public View createTabContent(String tag) {
                    TextView text = new TextView(Tabs.this); // "this" here is TabContentFactory() impl
                    text.setText("You have created a new tab!");
                    return text;
                }
            });
            ourSpec.setIndicator("New");
            th.addTab(ourSpec);
            break;
        case R.id.bStartWatch:
            start = System.currentTimeMillis();
            break;
        case R.id.bStopWatch:
            if(start != 0) {
                stop = System.currentTimeMillis();
                long result = stop-start;
                int millis = (int) result;
                int seconds = (int) millis/1000;
                int minutes = (int) seconds/60;
                showResults.setText(String.format("%d:%02d:%03d", minutes, seconds % 60, millis % 1000));
            }
            break;
        }
    }

}
