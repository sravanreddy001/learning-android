package com.ganta.learningandroid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Camera extends Activity implements View.OnClickListener {

    ImageView iv;
    ImageButton ib;
    Button b;
    Intent i;
    final static int cameraData = 0;
    Bitmap bmp;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo);
        initialize();
    }

    private void initialize() {
        iv = (ImageView) findViewById(R.id.ivReturnedPic); 
        ib = (ImageButton) findViewById(R.id.ibTakePic); 
        b = (Button) findViewById(R.id.bSetWall);
        b.setOnClickListener(this);
        ib.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.bSetWall:
            break;
        case R.id.ibTakePic:
            i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            // requestCode  If >= 0, this code will be returned in onActivityResult() when the activity exits. 
            startActivityForResult(i, cameraData);
            break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if( resultCode == RESULT_OK ) { // For safety
            Bundle extras = data.getExtras();
            bmp = (Bitmap) extras.get("data");
            iv.setImageBitmap(bmp);
        }
    }

    
    
}
