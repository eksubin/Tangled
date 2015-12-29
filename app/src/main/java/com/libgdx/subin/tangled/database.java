package com.libgdx.subin.tangled;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class database extends SQLiteOpenHelper {
    public static  final String DATABASE_NAME="tangled.db";
    public static  final String TABLE_NAME="DATA";
    public static  final String col_1="id";
    public static  final String col_2="PATH";
    public static  final String col_3="TAG";
            public String data;

    public database(Context context) {
        super(context, DATABASE_NAME, null, 5);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

                   // data="CREATE TABLE " + TABLE_NAME + " ( INDEX  INTEGER PRIMARY KEY," + col_2 +" TEXT," + col_3 + " TEXT );";
        db.execSQL(
                "create table DATA " +
                        "(id integer primary key, PATH text,TAG text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
            onCreate(db);
    }

    public boolean insert(String p, String t){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(col_2,p);
        content.put(col_3,t);
        long result= db.insert(TABLE_NAME, null, content);
        db.close();
        if(result == -1)
            return false;
        else
            return  true;
    }
}
