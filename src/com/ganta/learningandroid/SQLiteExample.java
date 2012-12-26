package com.ganta.learningandroid;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SQLiteExample extends Activity implements OnClickListener {

    Button sqlUpdate, sqlView, sqlModify, sqlGetInfo, sqlDelete;
    EditText sqlName, sqlHotness, sqlRow;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlliteexample);
        sqlUpdate = (Button) findViewById(R.id.bSQLupdate);
        sqlView = (Button) findViewById(R.id.bSQLopenView);
        sqlModify = (Button) findViewById(R.id.bSQLModify);
        sqlGetInfo = (Button) findViewById(R.id.bgetInfo);
        sqlDelete = (Button) findViewById(R.id.bSQLdelete);
        sqlName = (EditText) findViewById(R.id.etSQLName);
        sqlHotness = (EditText) findViewById(R.id.etSQLHotness);
        sqlRow = (EditText) findViewById(R.id.etSQLrowInfo);
        
        sqlUpdate.setOnClickListener(this);
        sqlView.setOnClickListener(this);
        sqlModify.setOnClickListener(this);
        sqlDelete.setOnClickListener(this);
        sqlGetInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
        case R.id.bSQLupdate:
            boolean didItWork = true;
            try {
            String name = sqlName.getText().toString();
            String hotness = sqlHotness.getText().toString();
            HotOrNot entry = new HotOrNot(SQLiteExample.this);
            entry.open();
            entry.createEntry(name, hotness);
            entry.close();
            } catch(Exception e) {
                didItWork = false;
                Dialog d = new Dialog(this);
                d.setTitle("Dialog Title");
                TextView tv = new TextView(this);
                tv.setText(e.getMessage());
                d.setContentView(tv);
                d.show();
                
            } finally {
                if(didItWork) {
                    Dialog d = new Dialog(this);
                    d.setTitle("Dialog Title");
                    TextView tv = new TextView(this);
                    tv.setText("Success");
                    d.setContentView(tv);
                    d.show();
                }
            }
            break;
        case R.id.bSQLopenView:
            Intent i = new Intent("android.intent.action.SQLVIEW");
            startActivity(i);
            break;
            
        case R.id.bgetInfo:
            String s = sqlRow.getText().toString();
            long l = Long.parseLong(s);
            HotOrNot hon = new HotOrNot(this);
            hon.open();
            String returnedName = hon.getName(l);
            String returnedHotness = hon.getHotness(l);
            sqlName.setText(returnedName);
            sqlHotness.setText(returnedHotness);
            hon.close();
            break;
        case R.id.bSQLModify:
            
            String mname = sqlName.getText().toString();
            String mhotness = sqlHotness.getText().toString();
            String sRow = sqlRow.getText().toString();
            long lRow = Long.parseLong(sRow);
            
            HotOrNot ex = new HotOrNot(this).open();
            ex.updateEntry(lRow, mname, mhotness);
            ex.close();
            //Add exception handling.
            
            break;
        case R.id.bSQLdelete:
            String sRow1 = sqlRow.getText().toString();
            long lRow1 = Long.parseLong(sRow1);
            
            HotOrNot ex1 = new HotOrNot(this).open();
            ex1.deleteEntry(lRow1);
            ex1.close();
            //Add exception handling.
            break;
        }
    }

    
    
}
