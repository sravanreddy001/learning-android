package com.ganta.learningandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HotOrNot {

    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "person_name";
    public static final String KEY_HOTNESS = "person_hotness";

    private static final String DATABASE_NAME = "HotOrNotdb";
    private static final String DATABASE_TABLE = "Person";
    private static final int DATABASE_VERSION = 1;

    private DbHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;
    
    private static class DbHelper extends SQLiteOpenHelper {

        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            
        }

        /**
         * Called very first time database is created.
         */
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" 
                    + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + KEY_NAME + " TEXT NOT NULL, "
                    + KEY_HOTNESS + " TEXT NOT NULL)"
            );
        }

        /**
         * For later upgrades etc.
         * Drop the table and create new.
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }

    public HotOrNot(Context context) {
        ourContext = context;
    }
    
    public HotOrNot open() throws SQLException {
        ourHelper = new DbHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }
    
    public void close() {
        ourHelper.close();
    }

    public long createEntry(String name, String hotness) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, name);
        cv.put(KEY_HOTNESS, hotness);
        return ourDatabase.insert(DATABASE_TABLE, null, cv);
    }

    public String getData() {
        String[] columns = new String[] {KEY_ROWID, KEY_NAME, KEY_HOTNESS };
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
        String result = "";
        int iRow = c.getColumnIndex(KEY_ROWID);
        int iName = c.getColumnIndex(KEY_NAME);
        int iHotness = c.getColumnIndex(KEY_HOTNESS);
        
        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            result = result + c.getString(iRow) + " " + c.getString(iName) + " " + c.getString(iHotness) + "\n";
        }
        
        return result;
    }

    public String getName(long l) throws SQLException {
        String[] columns = new String[] {KEY_ROWID, KEY_NAME, KEY_HOTNESS };
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" + l, null, null, null, null);
        if(c != null) {
            c.moveToFirst();
            String name = c.getString(1);
            return name;
        }
        return null;
    }

    public String getHotness(long l) throws SQLException {
        String[] columns = new String[] {KEY_ROWID, KEY_NAME, KEY_HOTNESS };
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" + l, null, null, null, null);
        if(c != null) {
            c.moveToFirst();
            String hotness = c.getString(2);
            return hotness;
        }
        return null;
    }

    public int updateEntry(long lRow, String mname, String mhotness) throws SQLException {
        ContentValues cvUpdate = new ContentValues();
        //cvUpdate.put(KEY_ROWID, lRow);
        cvUpdate.put(KEY_NAME, mname);
        cvUpdate.put(KEY_HOTNESS, mhotness);
        
        return ourDatabase.update(DATABASE_TABLE, cvUpdate, KEY_ROWID + "=" + lRow, null);
    }

    public int deleteEntry(long lRow1) throws SQLException {
        return ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + lRow1, null);
    }
    
}
