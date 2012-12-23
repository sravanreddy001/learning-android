package com.ganta.learningandroid;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends ListActivity {
    String classes[] = { "StartScreenActivity", "TextPlay", "Email", "Camera",
            "Data", "GFX", "GFXSurface" };

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);
        String className = classes[position];
        try {
            Class ourClass = Class.forName("com.ganta.learningandroid."
                    + className);
            Intent ourIntent = new Intent(Menu.this, ourClass);
            startActivity(ourIntent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE); // No title
        //set it to full screen.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setListAdapter(new ArrayAdapter<String>(Menu.this,
                android.R.layout.simple_list_item_1, classes));
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater blowUp = getMenuInflater();
        blowUp.inflate(R.menu.cool_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
        case R.id.aboutUs:
            Intent i = new Intent("android.intent.action.ABOUT");
            startActivity(i);
            break;
        case R.id.preferences:
            Intent p = new Intent("android.intent.action.PREFS");
            startActivity(p);
            break;
        case R.id.exit:
            finish();
            break;
        }
        return false; // why false?
    }

}
