package com.ganta.learningandroid;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class SimpleBrowser extends Activity implements OnClickListener {
    
    WebView ourBrow;
    EditText url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simplebrowser);
        ourBrow = (WebView) findViewById(R.id.wvBrowser);
        //Enable javascript
        ourBrow.getSettings().setJavaScriptEnabled(true);
        //Completely Zoomedout or not?
        ourBrow.getSettings().setLoadWithOverviewMode(true);
        //Normal view port? Normal desktop browser.
        // false -> constrained to its own dimentions.
        ourBrow.getSettings().setUseWideViewPort(true);
        
        ourBrow.setWebViewClient(new OurViewClient());
        try {
            ourBrow.loadUrl("http://www.mybringback.com");
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        Button go = (Button) findViewById(R.id.bGO);
        Button back = (Button) findViewById(R.id.bGoBack);
        Button refresh = (Button) findViewById(R.id.bRefreshPage);
        Button forward = (Button) findViewById(R.id.bGoForward);
        Button clearHistory = (Button) findViewById(R.id.bClearHistory);
        url = (EditText) findViewById(R.id.etURL);
        
        go.setOnClickListener(this);
        back.setOnClickListener(this);
        refresh.setOnClickListener(this);
        forward.setOnClickListener(this);
        clearHistory.setOnClickListener(this);
        
        
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.bGO:
            String webURL = url.getText().toString();
            ourBrow.loadUrl(webURL);
            //Hiding the keyboard after using an EditText
            InputMethodManager iMM = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            iMM.hideSoftInputFromWindow(url.getWindowToken(), 0);
            break;
        case R.id.bGoBack:
            if(ourBrow.canGoBack()) {
                ourBrow.goBack();
            }
            break;
        case R.id.bRefreshPage:
            ourBrow.reload();
            break;
        case R.id.bGoForward:
            if(ourBrow.canGoForward()) {
                ourBrow.goForward();
            }
            break;
        case R.id.bClearHistory:
            ourBrow.clearHistory();
            break;
        }
    }
    
}
